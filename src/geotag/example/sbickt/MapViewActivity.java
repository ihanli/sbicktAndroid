package geotag.example.sbickt;

import geotag.core.GeoTag;
import geotag.core.Point3D;
import geotag.core.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapViewActivity extends MapActivity {
	
	private final int GPS_REFRESH_TIME_IN_MS = 60000;
	private final int GPS_REFRESH_DISTANCE_IN_M = 50;

	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;
	private SbicktMessagesOverlay sbicktMessagesOverlay;

	private Drawable sbicktMessageDrawable;
	
	private LocationManager locationManager;
	private Location currentLocation;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(R.layout.map_view);
		
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		initLocationManager();

		//initMyLocationOverlay();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, GPS_REFRESH_TIME_IN_MS, GPS_REFRESH_DISTANCE_IN_M, locationListener);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		new InitSbicktMessageOverlay().execute(currentLocation);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		locationManager.removeUpdates(locationListener);
	}
	
	private void initLocationManager(){
		sbicktMessageDrawable = this.getResources().getDrawable(R.drawable.map_item);
		sbicktMessagesOverlay = new SbicktMessagesOverlay(sbicktMessageDrawable, this);
		
		locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	}
	
	private void initMyLocationOverlay(){
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		myLocationOverlay.enableMyLocation();
		mapView.getOverlays().add(myLocationOverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private final LocationListener locationListener = new LocationListener() {
		
		public void onStatusChanged(String provider, int status, Bundle extras) {}
		
		public void onProviderEnabled(String provider) {}
		
		public void onProviderDisabled(String provider) {}
		
		public void onLocationChanged(Location location) {
			new UpdateSbicktMessageOverlay().execute(location);
			currentLocation = location;
		}
	};
	
	private class UpdateSbicktMessageOverlay extends AsyncTask<Location, Void, Queue<GeoTag>>{

		@Override
		protected Queue<GeoTag> doInBackground(Location... params) {
			Queue<GeoTag> geoTags = new LinkedList<GeoTag>();
			
			try {
				geoTags = SbicktAPI.getGeoTags(new Point3D(params[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return geoTags;
		}
		
		@Override
		protected void onPostExecute(Queue<GeoTag> result) {
			if(result.isEmpty()){
				Toast.makeText(getBaseContext(), "no messages received", Toast.LENGTH_LONG).show();
			}
			else {				
				List<Overlay> overlays = mapView.getOverlays();
				overlays.clear();
				
				sbicktMessagesOverlay.clear();
				
				for(GeoTag g : result){
					GeoPoint point = new GeoPoint((int) (g.getX() * 1E6), (int) (g.getY() * 1E6));
					OverlayItem overlayItem = new OverlayItem(point, g.getOwner(), g.getContent());
					
					sbicktMessagesOverlay.addOverlay(overlayItem);
				}
				
				overlays.add(sbicktMessagesOverlay);
				mapView.invalidate();
			}
		}
		
	}
	
	private class InitSbicktMessageOverlay extends UpdateSbicktMessageOverlay {

		private ProgressDialog sbicktMessagesDialog = new ProgressDialog(MapViewActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			sbicktMessagesDialog.setMessage("lade s'bickt Nachrichten...");
			sbicktMessagesDialog.show();
		}
		
		@Override
		protected void onPostExecute(Queue<GeoTag> result) {
			super.onPostExecute(result);
			sbicktMessagesDialog.dismiss();
		}
		
	}
}

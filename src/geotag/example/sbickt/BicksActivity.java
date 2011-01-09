/**
 * #################################################
 * #            s'bickt Android Client             #
 * # Bildungseinrichtung:  Fachhochschule Salzburg #
 * #         Studiengang:  MultiMediaTechnology    #
 * #               Zweck:  Qualifikationsprojekt   #
 * #################################################
 *
 * This is the client for the augmented reality and social community app s'bickt
 * Copyright Alexander Flatscher, Ismail Hanli
 * 
 * This file is part of s'bickt.
 * 
 * S'bickt is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * S'bickt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with s'bickt.  If not, see <http://www.gnu.org/licenses/>.
 */

package geotag.example.sbickt;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import geotag.core.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class BicksActivity extends Activity {
	
	private EditText editTextBicks;
	private ImageButton buttonBicks;
	private RadioGroup radioGroupCategory;
	private RadioButton radioButtonPrivate, radioButtonFriends, radioButtonPublic;
	private LocationManager locationManager;
	private Location currentLocation;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bicks_view);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/LiteraEF-Medium.otf");		editTextBicks = (EditText) findViewById(R.id.edit_text_bicks);
		buttonBicks = (ImageButton) findViewById(R.id.button_bicks);
		radioGroupCategory = (RadioGroup) findViewById(R.id.radio_group_category);
		radioButtonPrivate = (RadioButton) findViewById(R.id.radio_category_private);
		radioButtonFriends = (RadioButton) findViewById(R.id.radio_category_friends);
		radioButtonPublic = (RadioButton) findViewById(R.id.radio_category_public);
		
		editTextBicks.setTypeface(tf);
		editTextBicks.setEnabled(false);
		buttonBicks.setEnabled(false);
		radioButtonPrivate.setTag("private");
		radioButtonFriends.setTag("protected");
		radioButtonPublic.setTag("public");
		
		radioGroupCategory.setOnCheckedChangeListener(radioGroupCategoryOnCheckedChangeListener);
		buttonBicks.setOnClickListener(buttonBicksOnClickListener);
		
		locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, locationListener);
		
	}
	
	private LocationListener locationListener = new LocationListener() {
		
		public void onStatusChanged(String provider, int status, Bundle extras) {}
		
		public void onProviderEnabled(String provider) {}
		
		public void onProviderDisabled(String provider) {
			Toast.makeText(getBaseContext(), "GPS provider dislabled" ,Toast.LENGTH_LONG).show();
		}
		
		public void onLocationChanged(Location location) {
			currentLocation = location;
		}
	};
	
	private class UploadSbicktMessage extends AsyncTask<Void, Void, Void> {
		
		private ProgressDialog sbicktUploadDialog = new ProgressDialog(BicksActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			sbicktUploadDialog.setMessage("Nachricht wird auf den Server geladen...");
			sbicktUploadDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			RadioButton selected = (RadioButton) findViewById(radioGroupCategory.getCheckedRadioButtonId());
			
			List<NameValuePair> myData = new ArrayList<NameValuePair>();
			
			myData.add(new BasicNameValuePair("sbickerl[owner]", "sbickt"));
			myData.add(new BasicNameValuePair("sbickerl[content]", editTextBicks.getText().toString()));
			myData.add(new BasicNameValuePair("sbickerl[visibility]", selected.getTag().toString()));
			myData.add(new BasicNameValuePair("geotag[x]", Double.toString(currentLocation.getLatitude())));
			myData.add(new BasicNameValuePair("geotag[y]", Double.toString(currentLocation.getLongitude())));
			myData.add(new BasicNameValuePair("geotag[z]", Double.toString(currentLocation.getAltitude())));
			
			try {
				SbicktAPI.newGeoTag(myData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			sbicktUploadDialog.dismiss();
			//startActivity(new Intent(getBaseContext(), CameraActivity.class));
			finish();
		}
		
	}
	
	private OnCheckedChangeListener radioGroupCategoryOnCheckedChangeListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			editTextBicks.setEnabled(true);
			buttonBicks.setEnabled(true);
			
			radioGroupCategory.setOnCheckedChangeListener(null);
		}
	};
	
	private OnClickListener buttonBicksOnClickListener = new OnClickListener() {
		
		public void onClick(View v) {
			
			if(currentLocation == null){
				Toast.makeText(getBaseContext(), "GPS location not available" ,Toast.LENGTH_SHORT).show();
			}
			else {
				new UploadSbicktMessage().execute();
			}
		}
	};
}

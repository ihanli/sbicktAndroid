package geotag.example.sbickt;

import geotag.core.GeoTag;
import geotag.core.HttpHelper;
import geotag.core.KmlParser;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.http.NameValuePair;

public class SbicktAPI {
	public void newGeoTag(List<NameValuePair> geoTagData) throws Exception{
		HttpHelper httpConnection = new HttpHelper(Strings.getString("SbicktAPI.1"));
		
		if(!httpConnection.POSTRequest(geoTagData)){
			throw new Exception("SbicktAPI -> newGeoTag: Failed to add new geo tag");
		}
	}
	
	public Queue<GeoTag> getGeoTags() throws Exception{
		KmlParser kp = new KmlParser();
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		Exception up = new Exception("SbicktAPI -> getGeoTags: No data from server");
		
		kp.requestKml(Strings.getString("SbicktAPI.0"));
		
		listOfGeoTags = kp.generateObjects();
		
		if(listOfGeoTags == null){
			throw up; //ha ha
		}
		else if(listOfGeoTags.isEmpty()){
			throw new Exception("SbicktAPI -> getGeoTags: Object list is empty");
		}
		else{
			return listOfGeoTags;
		}
	}
}

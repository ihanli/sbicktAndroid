package geotag.example.sbickt;

import geotag.core.GeoTag;
import geotag.core.HttpHelper;
import geotag.core.KmlParser;
import geotag.example.sbickt.Strings;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.http.NameValuePair;

public class SbicktAPI {
	public static void newGeoTag(List<NameValuePair> geoTagData) throws Exception{
		HttpHelper httpConnection = new HttpHelper(Strings.getString("SbicktAPI.2"));
		
		if(!httpConnection.POSTRequest(geoTagData)){
			throw new Exception("SbicktAPI -> newGeoTag: Failed to add new geotag");
		}
	}
	
	public static Queue<GeoTag> getGeoTags() throws Exception{
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
	
	public static void deleteGeoTag(Integer geoTagId) throws Exception{
		String deleteURL = Strings.getString("SbicktAPI.2") + geoTagId.toString();
		HttpHelper httpConnection = new HttpHelper(deleteURL);
		
		if(!httpConnection.DELETERequest(geoTagId)){
			throw new Exception("SbicktAPI -> deleteGeoTag: Failed to delete geotag");
		}
	}
}

package geotag.example.sbickt;

import static org.junit.Assert.*;

import geotag.core.GeoTag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class SbicktAPITest {

	@Test
	public void testNewGeoTag() {
		List<NameValuePair> myData = new ArrayList<NameValuePair>();

		myData.add(new BasicNameValuePair("sbickerl[owner]", "ismail"));
		myData.add(new BasicNameValuePair("sbickerl[content]", "test post"));
		myData.add(new BasicNameValuePair("sbickerl[visibility]", "private"));
		myData.add(new BasicNameValuePair("geotag[x]", "2.548"));
		myData.add(new BasicNameValuePair("geotag[y]", "2.548"));
		myData.add(new BasicNameValuePair("geotag[z]", "2.548"));
		
		try {
			SbicktAPI.newGeoTag(myData);
		}
		catch (Exception e) {
			fail("SbicktAPITest -> newGeoTag: No data from server (started?).");
		}
	}

	@Test
	public void testGetGeoTags() {
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		
		try {
			listOfGeoTags = SbicktAPI.getGeoTags();
			
			assertNotNull(listOfGeoTags);
			
			for(int i = 0;i < listOfGeoTags.size();i++){
				listOfGeoTags.poll().prettyPrint();
			}
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testDeleteGeoTag(){
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		
		try {
			listOfGeoTags = SbicktAPI.getGeoTags();

			for(int i = 0;i < listOfGeoTags.size();i++){
				SbicktAPI.deleteGeoTag(listOfGeoTags.poll().getId());
			}
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}

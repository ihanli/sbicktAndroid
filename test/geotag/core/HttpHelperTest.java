package geotag.core;

import static org.junit.Assert.*;

import geotag.example.sbickt.Strings;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Test;

public class HttpHelperTest {

//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void testGETRequest() {
		HttpHelper myHelper = new HttpHelper("http://localhost:3000/geotags.kml");
		
		assertNotNull(myHelper.GETRequest());
	}

//	@Test
	public void testPOSTRequest() {
		HttpHelper myHelper = new HttpHelper(Strings.getString("SbicktAPI.2"));
		List<NameValuePair> myData = new ArrayList<NameValuePair>();
		
		myData.add(new BasicNameValuePair("geotag[x]", "2.548"));
		myData.add(new BasicNameValuePair("geotag[y]", "2.548"));
		myData.add(new BasicNameValuePair("geotag[z]", "2.548"));
		
		assertTrue(myHelper.POSTRequest(myData));
	}

}

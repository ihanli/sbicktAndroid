package geotag.core;

import geotag.example.sbickt.Strings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpHelper {
	private String serverURL;
	private HttpClient client = new DefaultHttpClient();
	private HttpGet clientGetRequest;
	private HttpPost clientPostRequest;
	private HttpDelete clientDeleteRequest;
	private HttpResponse serverResponse;
	
	public HttpHelper(String url){
		clientGetRequest = new HttpGet(url);
		clientPostRequest = new HttpPost(url);
		
		serverURL = url;
	}

	public String getURL() {
		return serverURL;
	}

	public void setURL(String url) {
		if(url == serverURL) {
			return;
		}
		
		serverURL = url;
		
		try {
			clientGetRequest.setURI(new URI(serverURL));
			clientPostRequest.setURI(new URI(serverURL));
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public String GETRequest() {
		BufferedReader reader;
		InputStream serverStream;
		String currentLine = null;
		StringBuilder stringStream = new StringBuilder();
		
        try {
        	serverResponse = client.execute(clientGetRequest);
			serverStream = serverResponse.getEntity().getContent();
			reader = new BufferedReader(new InputStreamReader(serverStream));

			while((currentLine = reader.readLine()) != null){
			    stringStream.append(currentLine + "\n");
			}
			
			serverStream.close();
		}
        catch (Exception e) {
        	return null;
		}
		
		return stringStream.toString();
	}
	
	public Boolean POSTRequest(List<NameValuePair> geoTagData){
		//TODO: Check if data was inserted correctly (HTTP Status Code)
		
		try {
			clientPostRequest.setEntity(new UrlEncodedFormEntity(geoTagData, HTTP.UTF_8));
			serverResponse = client.execute(clientPostRequest);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public Boolean DELETERequest(Integer geoTagId){
		try{
			String deleteURL = Strings.getString("SbicktAPI.2") + geoTagId.toString();
			List<NameValuePair> geoTagData = new ArrayList<NameValuePair>();
			geoTagData.add(new BasicNameValuePair("id", geoTagId.toString()));
			
			clientDeleteRequest = new HttpDelete(deleteURL);
			serverResponse = client.execute(clientDeleteRequest);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
}

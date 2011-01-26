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

package geotag.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class HttpHelper {
	private static String serverURL;
	private static HttpPost clientPostRequest;
	private static HttpDelete clientDeleteRequest;
	private static HttpClient client = new DefaultHttpClient();
	private static HttpHelper helperInstance = null;

	private HttpHelper(String url){
		clientPostRequest = new HttpPost(url);
		serverURL = url;
	}
	
	public static HttpHelper getInstance(String url) {
		if (helperInstance == null) {
			helperInstance = new HttpHelper(url);
		}
		else{
			clientPostRequest = new HttpPost(url);
			serverURL = url;
		}

		return helperInstance;
	}

	private HttpHelper(String url, String queryString){
		clientDeleteRequest = new HttpDelete(url + queryString);
		clientPostRequest = new HttpPost(url);
		serverURL = url;
	}
	
	public static HttpHelper getInstance(String url, String queryString) {
		if (helperInstance == null) {
			helperInstance = new HttpHelper(url, queryString);
		}
		else{
			clientDeleteRequest = new HttpDelete(url + queryString);
			clientPostRequest = new HttpPost(url);
			serverURL = url;
		}

		return helperInstance;
	}

	public String getURL() {
		return serverURL;
	}

	public static void setURL(String url) throws Exception {
		if(url == serverURL) {
			return;
		}
		
		serverURL = url;
		
		try {
			clientPostRequest.setURI(new URI(serverURL));
			clientDeleteRequest.setURI(new URI(serverURL));
		}
		catch (URISyntaxException e) {
			throw new Exception("HttpHelper -> setURL: Syntax error in URI");
		}
	}

	public Boolean POSTRequest(List<NameValuePair> geoTagData){
		try {
			clientPostRequest.setEntity(new UrlEncodedFormEntity(geoTagData, HTTP.UTF_8));

			checkForRequestError(clientPostRequest);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public Boolean DELETERequest(){
		try{
			checkForRequestError(clientDeleteRequest);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	//TODO: the check for errors is not sufficient. work it over!
	
	private void checkForRequestError(HttpUriRequest request) throws Exception {		
		HttpResponse serverResponse = client.execute(request);
		HttpEntity entity = serverResponse.getEntity();
		
		if(entity != null){
			entity.consumeContent();
		}

		Header headers[] = serverResponse.getAllHeaders();
		String ignoreCase = Properties.getUrlIndex().substring(0,Properties.getUrlIndex().length() - 1);
		
		for (Header h : headers) {
			if (h.getName().equalsIgnoreCase("Location")) {
				if (h.getValue().equalsIgnoreCase(ignoreCase) || h.getValue().equalsIgnoreCase(Properties.getUrl() + "/index.html#section_wo")) {
					return;
				}
			}
		}
		
		throw new Exception("HttpHelper -> checkForRequestError: " + request.getMethod().toString() + " request failed");
	}
}

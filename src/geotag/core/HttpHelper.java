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
	private String serverURL;
	private HttpPost clientPostRequest;
	private HttpDelete clientDeleteRequest;
	
	public HttpHelper(String url){
		clientPostRequest = new HttpPost(url);
		serverURL = url;
	}
	
	public HttpHelper(String url, String queryString){
		clientDeleteRequest = new HttpDelete(url + queryString);
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
			clientPostRequest.setURI(new URI(serverURL));
			clientDeleteRequest.setURI(new URI(serverURL));
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
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

	private void checkForRequestError(HttpUriRequest request) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpResponse serverResponse = client.execute(request);
		Header headers[] = serverResponse.getAllHeaders();
		
		String ignoreCase = Properties.getUrlIndex().substring(0, Properties.getUrlIndex().length() - 1);
		
		for(Header h:headers){
			if(h.getName().equalsIgnoreCase("Location") && h.getValue().equalsIgnoreCase(ignoreCase)){
				return;
			}
		}
		
		throw new Exception("HttpHelper -> checkForRequestError: " + request.getMethod() + " request failed");
	}
}

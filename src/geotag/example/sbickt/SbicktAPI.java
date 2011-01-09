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

import geotag.core.GeoTag;
import geotag.core.HttpHelper;
import geotag.core.KmlParser;
import geotag.core.Point3D;
import geotag.core.Properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.http.NameValuePair;

public class SbicktAPI {
	public static void newGeoTag(List<NameValuePair> geoTagData) throws Exception{
		String url = Properties.getUrlIndex();
		HttpHelper httpConnection = new HttpHelper(url);
		
		if(!httpConnection.POSTRequest(geoTagData)){
			throw new Exception("SbicktAPI -> newGeoTag: Failed to add new geotag");
		}
	}
	
	public static Queue<GeoTag> getGeoTags(Point3D me) throws Exception{
		KmlParser kp = new KmlParser();
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		Exception up = new Exception("SbicktAPI -> getGeoTags: No data from server");
		
		kp.requestKml(Properties.URL_PROTOCOL, Properties.URL_HOST, Properties.URL_PORT, Properties.URL_FOLDER_LIST + "?coordinates[x]=" + me.x + "&coordinates[y]=" + me.y);
		
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
//		String deleteURL = Strings.getString("SbicktAPI.2") + geoTagId.toString();
		String url = Properties.getUrlIndex();
		HttpHelper httpConnection = new HttpHelper(url, geoTagId.toString());
		
		if(!httpConnection.DELETERequest()){
			throw new Exception("SbicktAPI -> deleteGeoTag: Failed to delete geotag");
		}
	}
}

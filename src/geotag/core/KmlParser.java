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

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

public class KmlParser {
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document kml;
	
	public KmlParser() throws Exception{
		try{
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
		}
		catch(Exception e){
			throw new Exception("KmlParser -> Constructor: " + e);
		}
	}
	
	public void requestKml(String protocol, String host, int port, String folder) throws Exception{
		try {
			
			kml = db.parse(new URL(protocol, host, port, folder).openConnection().getInputStream());
			kml.getDocumentElement().normalize();
		}
		catch (Exception e) {
			Log.v("alex", e.toString());
			throw new Exception("KmlParser -> requestKml: " + e);
		}
	}

	public Queue<GeoTag> generateObjects(){
		String user, content, coordinates;
		Integer id;
		TagVisibility visibility;
		double[] geoData = new double[3];
		Element listElement;
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		Element pointElement;
		NodeList objectList = kml.getElementsByTagName("Placemark");

		for(int i = 0;i < objectList.getLength();i++){
			listElement = (Element) objectList.item(i);
			
			id = Integer.parseInt(listElement.getAttribute("id"));

			if(listElement.getAttribute("visibility").toString().equalsIgnoreCase(Properties.VISIBILITY_0)){
				visibility = TagVisibility.PRIVATE;
			}
			else if(listElement.getAttribute("visibility").equalsIgnoreCase(Properties.VISIBILITY_1)){
				visibility = TagVisibility.PROTECTED;
			}
			else{
				visibility = TagVisibility.PUBLIC;
			}

			user = listElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();

			content = listElement.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();

			pointElement = (Element) listElement.getElementsByTagName("Point").item(0);
			coordinates = pointElement.getElementsByTagName("coordinates").item(0).getFirstChild().getNodeValue();

			try {
				geoData[1] = Double.parseDouble(coordinates.substring(0, coordinates.indexOf(",")));
				geoData[0] = Double.parseDouble(coordinates.substring(coordinates.indexOf(",") + 1, coordinates.lastIndexOf(",")));
				geoData[2] = Double.parseDouble(coordinates.substring(coordinates.lastIndexOf(",") + 1, coordinates.length()));
			}
			catch (NumberFormatException e) {
				continue;
			}

			listOfGeoTags.add(new GeoTag(id, new Point3D(geoData), user, content, visibility));
		}
		
		return listOfGeoTags;
	}
}

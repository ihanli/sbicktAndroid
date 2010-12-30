package geotag.core;

import geotag.example.sbickt.Strings;

import java.util.LinkedList;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

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
	
	public void requestKml(String url) throws Exception{
		try {
			kml = db.parse(url);
			kml.getDocumentElement().normalize();
		}
		catch (Exception e) {
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

			if(listElement.getAttribute("visibility").toString().equalsIgnoreCase(Strings.getString("Visibility.0"))){
				visibility = TagVisibility.PRIVATE;
			}
			else if(listElement.getAttribute("visibility").equalsIgnoreCase(Strings.getString("Visibility.1"))){
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
				geoData[0] = Double.parseDouble(coordinates.substring(0, coordinates.indexOf(",")));
				geoData[1] = Double.parseDouble(coordinates.substring(coordinates.indexOf(",") + 1, coordinates.lastIndexOf(",")));
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

package geotag.core;

import java.util.LinkedList;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class KmlParser {
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document kml;
	
	public KmlParser(){
		try{
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
		}
		catch(Exception e){
			System.out.println("KmlParser -> Constructor: " + e);
		}
	}
	
	public void requestKml(String url){
		try {
			kml = db.parse(url);
			kml.getDocumentElement().normalize();
		}
		catch (Exception e) {
			System.out.println("KmlParser -> requestKml: " + e);
		}
	}
	
	public Queue<GeoTag> generateObjects(){
		String user, content, coordinates;
		double[] geoData = new double[3];
		Node object;
		Element listElement;
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		NodeList pointList;
		NodeList objectList = kml.getElementsByTagName("Placemark");
		
		for(int i = 0;i < objectList.getLength();i++){
			object = objectList.item(i);

			listElement = (Element) object;

			user = listElement.getElementsByTagName("name").item(0).getNodeValue();
			content = listElement.getElementsByTagName("description").item(0).getNodeValue();
			
			pointList = listElement.getElementsByTagName("Point");
			coordinates = pointList.item(0).getNodeValue();
			
			geoData[0] = Double.parseDouble(coordinates.substring(0, coordinates.indexOf(",")));
			geoData[1] = Double.parseDouble(coordinates.substring(coordinates.indexOf(",") + 1, coordinates.lastIndexOf(",")));
			geoData[2] = Double.parseDouble(coordinates.substring(coordinates.lastIndexOf(",") + 1, coordinates.length()));
			
			listOfGeoTags.add(new GeoTag(new Point3D(geoData), content, TagVisibility.PRIVATE));
		}
		
		return listOfGeoTags;
	}
}

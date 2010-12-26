package geotag.core;

import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.Test;

import junit.framework.Assert;
import static org.junit.Assert.*;

public class KmlParserTest {
	
	@Test
	public void testRequestKml(){
		try{
			KmlParser myParser = new KmlParser();
			myParser.requestKml("http://localhost:3000/geotags.kml");
		}
		catch(Exception e){
			Assert.fail(e.toString());
		}
	}
	
	@Test
	public void testGenerateObjects(){
		KmlParser myParser;
		
		try {
			myParser = new KmlParser();
			myParser.requestKml("http://localhost:3000/geotags.kml");

			Queue<GeoTag> myObjects = myParser.generateObjects();
			
			assertNotNull(myObjects);
			assertFalse(myObjects.isEmpty());
			
			GeoTag temp = new GeoTag();
			
			assertNotNull(temp);
			
			while (true) {
				try {
					temp = myObjects.remove();
					System.out.println(temp.getOwner());
					System.out.println(temp.getContent());
					System.out.println(temp.getCoordinates());
					System.out.println(temp.getVisibility());
					System.out.print("\n");
				}
				catch (NoSuchElementException e) {
					break;
				}
			}
		}
		catch (Exception e) {
			Assert.fail(e.toString());
		}
	}
}

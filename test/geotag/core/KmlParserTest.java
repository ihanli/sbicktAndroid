package geotag.core;

import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.Test;

import junit.framework.Assert;
import static org.junit.Assert.*;

public class KmlParserTest {
	private KmlParser myParser;
	
	@Test
	public void testKmlParser(){
		try{
			myParser = new KmlParser();
		}
		catch(Exception e){
			Assert.fail(e.toString());
		}
	}
	
	public void testRequestKml(){
		try{
			myParser.requestKml("http://localhost:3000/geotags.kml");
		}
		catch(Exception e){
			Assert.fail(e.toString());
		}
	}
	
	public void testGenerateObjects(){
		Queue<GeoTag> myObjects = myParser.generateObjects();
		GeoTag temp = new GeoTag();
		
		assertNotNull(myObjects);
		
		while (true) {
			try {
				temp = myObjects.remove();
				System.out.println(temp.getContent());
				System.out.println(temp.getCoordinates());
				System.out.println(temp.getVisibility());
			}
			catch (NoSuchElementException e) {
				Assert.fail(e.toString());
			}
		}
	}
}

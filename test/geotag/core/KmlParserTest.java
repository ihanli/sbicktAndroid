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
			myParser.requestKml(Strings.getString("TestURL.0"));
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
			myParser.requestKml(Strings.getString("TestURL.0"));

			Queue<GeoTag> myObjects = myParser.generateObjects();
			
			assertNotNull(myObjects);
			
			while (true) {
				try {
					myObjects.remove().prettyPrint();
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

package geotag.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoTagTest {
	GeoTag sbickerl;

	@Test
	public void testGeoTag() {
		sbickerl = new GeoTag();
		
		assertEquals(0, sbickerl.getX(), 0);
		assertEquals(0, sbickerl.getY(), 0);
		assertEquals(0, sbickerl.getZ(), 0);
		assertNull(sbickerl.getContent());
		assertNull(sbickerl.getOwner());
		assertEquals(TagVisibility.PRIVATE, sbickerl.getVisibility());
	}

	public void testGetCoordinates(){
		assertEquals("X: " + sbickerl.getX() + ", Y: " + sbickerl.getY() + ", Z: " + sbickerl.getZ(), sbickerl.getCoordinates());
	}
}

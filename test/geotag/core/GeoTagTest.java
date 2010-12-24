package geotag.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoTagTest {

	@Test
	public void testGeoTag() {
		GeoTag sbickerl = new GeoTag();
		
		assertEquals(0, sbickerl.getX(), 0);
		assertEquals(0, sbickerl.getY(), 0);
		assertEquals(0, sbickerl.getZ(), 0);
		assertNull(sbickerl.getText());
		assertEquals(TagVisibility.PRIVATE, sbickerl.getVisibility());
	}

}

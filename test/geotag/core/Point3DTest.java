package geotag.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class Point3DTest {

	@Test
	public void testPoint3D() {
		Point3D coordinates = new Point3D(0.0, 1.0, 2.0);
		
		assertEquals(0, coordinates.x, 0);
		assertEquals(1, coordinates.y, 0);
		assertEquals(2, coordinates.z, 0);
	}

}

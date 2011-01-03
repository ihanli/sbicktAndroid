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

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoTagTest {

	@Test
	public void testGeoTag() {
		GeoTag sbickerl = new GeoTag();
		
		assertEquals(0, sbickerl.getX(), 0);
		assertEquals(0, sbickerl.getY(), 0);
		assertEquals(0, sbickerl.getZ(), 0);
		assertNull(sbickerl.getContent());
		assertNull(sbickerl.getOwner());
		assertEquals(TagVisibility.PRIVATE, sbickerl.getVisibility());
	}

	@Test
	public void testGetCoordinates(){
		GeoTag sbickerl = new GeoTag();

		assertEquals("X: " + sbickerl.getX() + ", Y: " + sbickerl.getY() + ", Z: " + sbickerl.getZ(), sbickerl.coordinatesToString());
	}
}

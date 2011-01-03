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

package geotag.example.sbickt;

import static org.junit.Assert.*;

import geotag.core.GeoTag;
import geotag.core.Point3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class SbicktAPITest {

	@Test
	public void testNewGeoTag() {
		List<NameValuePair> myData = new ArrayList<NameValuePair>();

		myData.add(new BasicNameValuePair("sbickerl[owner]", "ismail"));
		myData.add(new BasicNameValuePair("sbickerl[content]", "test post"));
		myData.add(new BasicNameValuePair("sbickerl[visibility]", "private"));
		myData.add(new BasicNameValuePair("geotag[x]", "2.548"));
		myData.add(new BasicNameValuePair("geotag[y]", "2.548"));
		myData.add(new BasicNameValuePair("geotag[z]", "2.548"));
		
		try {
			SbicktAPI.newGeoTag(myData);
		}
		catch (Exception e) {
			fail("SbicktAPITest -> newGeoTag: No data from server (started?).");
		}
	}

	@Test
	public void testGetGeoTags() {
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		
		try {
			listOfGeoTags = SbicktAPI.getGeoTags(new Point3D(2.548, 2.548, 0));
			
			assertNotNull(listOfGeoTags);
			
			while(!listOfGeoTags.isEmpty()){
				listOfGeoTags.poll().prettyPrint();
			}
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testDeleteGeoTag(){
		Queue<GeoTag> listOfGeoTags = new LinkedList<GeoTag>();
		
		try {
			listOfGeoTags = SbicktAPI.getGeoTags(new Point3D(2.548, 2.548, 0));

			while(!listOfGeoTags.isEmpty()){
				SbicktAPI.deleteGeoTag(listOfGeoTags.poll().getId());
			}
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}

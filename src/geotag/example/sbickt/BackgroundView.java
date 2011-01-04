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

import geotag.core.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class BackgroundView extends RelativeLayout {
	
	private LayoutInflater inflater;

	public BackgroundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.background, this, true);
		
		((ImageButton)this.findViewById(R.id.button_back)).setOnClickListener(backButtonOnClickListener);
		
	}
	
	private OnClickListener backButtonOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(getContext(),"Not yet implemented...",Toast.LENGTH_SHORT).show();
			
		}
	};

}

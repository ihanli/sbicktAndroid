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
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MenuView extends RelativeLayout {
	
	private LayoutInflater inflater;

	public MenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.menu, this, true);
		
		((RelativeLayout)this.findViewById(R.id.out)).setVisibility(INVISIBLE);
		
		
		((ImageButton)this.findViewById(R.id.archive)).setOnClickListener(archiveOnClickListener);
		((ImageButton)this.findViewById(R.id.bicks)).setOnClickListener(bicksOnClickListener);
		((ImageButton)this.findViewById(R.id.friends)).setOnClickListener(friendsOnClickListener);
		((ImageButton)this.findViewById(R.id.profile)).setOnClickListener(profileOnClickListener);
		
		((ImageButton)this.findViewById(R.id.screenshot)).setOnClickListener(notYetImplementedOnClickListener);		
		((ImageButton)this.findViewById(R.id.live_view)).setOnClickListener(notYetImplementedOnClickListener);		
		((ImageButton)this.findViewById(R.id.map_view)).setOnClickListener(mapViewOnClickListener);
		
		((ImageButton)this.findViewById(R.id.menu_in)).setOnClickListener(menuInOnClickListener);
		((ImageButton)this.findViewById(R.id.menu_out)).setOnClickListener(menuOutOnClickListener);
		
	}
	
	private OnClickListener notYetImplementedOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(getContext(),"Not yet implemented...",Toast.LENGTH_SHORT).show();
			
		}
	};	
	
	private OnClickListener mapViewOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			getContext().startActivity(new Intent(getContext(), MapViewActivity.class));
			
		}
	};
	
	private OnClickListener menuOutOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			((RelativeLayout)findViewById(R.id.out)).setVisibility(INVISIBLE);
			((RelativeLayout)findViewById(R.id.in)).setVisibility(VISIBLE);
		}
	};
	
	private OnClickListener menuInOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			((RelativeLayout)findViewById(R.id.in)).setVisibility(INVISIBLE);
			((RelativeLayout)findViewById(R.id.out)).setVisibility(VISIBLE);
		}
	};
	
	private OnClickListener archiveOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			getContext().startActivity(new Intent(getContext(), ArchiveActivity.class));
		}
	};
	
	private OnClickListener bicksOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			getContext().startActivity(new Intent(getContext(), BicksActivity.class));
		}
	};
	
	private OnClickListener friendsOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			getContext().startActivity(new Intent(getContext(), FriendsActivity.class));
		}
	};
	
	private OnClickListener profileOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			getContext().startActivity(new Intent(getContext(), ProfileActivity.class));
		}
	};

}

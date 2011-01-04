package geotag.example.sbickt;

import geotag.core.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MenuView extends RelativeLayout {
	
	private LayoutInflater inflater;

	public MenuView(Context context) {
		super(context);
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.menu, this, true);
		
		((RelativeLayout)this.findViewById(R.id.out)).setVisibility(INVISIBLE);
		
		
		((ImageButton)this.findViewById(R.id.archive)).setOnClickListener(notYetImplementedOnClickListener);
		((ImageButton)this.findViewById(R.id.bicks)).setOnClickListener(notYetImplementedOnClickListener);
		((ImageButton)this.findViewById(R.id.friends)).setOnClickListener(notYetImplementedOnClickListener);
		((ImageButton)this.findViewById(R.id.live_view)).setOnClickListener(notYetImplementedOnClickListener);
		((ImageButton)this.findViewById(R.id.profile)).setOnClickListener(notYetImplementedOnClickListener);
		((ImageButton)this.findViewById(R.id.screenshot)).setOnClickListener(notYetImplementedOnClickListener);
		
		((ImageButton)this.findViewById(R.id.menu_in)).setOnClickListener(menuInOnClickListener);
		((ImageButton)this.findViewById(R.id.menu_out)).setOnClickListener(menuOutOnClickListener);
		
	}
	
	private OnClickListener notYetImplementedOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(getContext(),"Not yet implemented...",Toast.LENGTH_SHORT).show();
			
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

}

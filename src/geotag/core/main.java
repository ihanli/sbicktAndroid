package geotag.core;

import geotag.core.R;
import geotag.example.sbickt.CameraView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent cameraView = new Intent(this, CameraView.class);
		startActivity(cameraView);
    }
}
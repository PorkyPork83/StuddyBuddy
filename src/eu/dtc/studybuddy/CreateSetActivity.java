package eu.dtc.studybuddy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class CreateSetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_create_set);
		
		Fragment fragment = new CreateSetNameFragment();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame_set, fragment)
				.commit();
		
		super.onCreate(savedInstanceState);
	}		
}

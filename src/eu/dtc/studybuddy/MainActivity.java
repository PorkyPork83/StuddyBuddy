package eu.dtc.studybuddy;

import eu.dtc.studybuddy.adapter.NavDrawerListAdapter;
import eu.dtc.studybuddy.db.helper.DbHelper;
import eu.dtc.studybuddy.db.model.Subject;
import eu.dtc.studybuddy.helpers.FilesystemHelper;
import eu.dtc.studybuddy.model.NavDrawerItem;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private FilesystemHelper mHelper;

	private CharSequence mDrawerTitle;	

	private CharSequence mTitle;

	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mHelper = new FilesystemHelper();
		mHelper.createMainFolder();

		mTitle = mDrawerTitle = getTitle();
		
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// Quiz starten
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// F�her verwalten
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Sets verwalten
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		
		// DB Temp Patch START
		DbHelper db = new DbHelper(this);
		ArrayList<Subject> db_subjects = db.getAllSubjects();
		for (Subject subj : db_subjects) {
			navDrawerItems.add(new NavDrawerItem(subj.getName(), navMenuIcons.getResourceId(0, -1)));
		}
		
		// DB Temp Patch END
		
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// ActionBar Icon als Toggle Button einstellen
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //Icon des Nav Drawers
				R.string.app_name,
				R.string.app_name 
		) {
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// Aufruf von onPrepareOptionsMenu() um ActionBar Icons anzuzeigen
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// Aufruf von onPrepareOptionsMenu() um ActionBar Icons zu verbergen
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// Element, das beim ersten Aufruf angezeigt wird (Hier: Quiz erstellen)
			displayView(0);
		}
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.add_subject:
			startActivity(new Intent(this, CreateSubjectActivity.class));
			return true;
		case R.id.add_set:
			startActivity(new Intent(this, CreateSetActivity.class));
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void displayView(int position) {

		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new ChooseQuizFragment();
			break;
		case 1:
			fragment = new SubjectManagerFragment();
			break;
		case 2:
			fragment = new SetManagerFragment();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment)
					.commit();
			
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
			
		} else {

			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}

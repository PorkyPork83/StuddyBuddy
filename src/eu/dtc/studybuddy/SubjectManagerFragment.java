package eu.dtc.studybuddy;

import java.util.ArrayList;

import eu.dtc.studybuddy.helpers.FilesystemHelper;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SubjectManagerFragment extends ListFragment {
	
	ArrayList<String> mSubjectList;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		
		return super.onCreateView(inflater, container, savedInstanceState);
    }

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.edit_subject, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}	

	@Override
	public void onResume() {
		
		final FilesystemHelper subjectHelper = new FilesystemHelper();

		mSubjectList = new ArrayList<String>();	
		mSubjectList = subjectHelper.loadSubjectIndex(getActivity().getBaseContext());
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), 
				android.R.layout.simple_list_item_1, 
				mSubjectList);		

		setListAdapter(adapter);
		
		final ListView lv = getListView();
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> av, View v,
					int position, long id) {
				
				String name = mSubjectList.get(position);
				
				subjectHelper.deleteSubject(getActivity().getBaseContext(),mSubjectList, position);
				
				Toast.makeText(getActivity().getBaseContext(), "Das Fach " + name + " wurde erfolgreich entfernt" , Toast.LENGTH_SHORT).show();
				adapter.notifyDataSetChanged();
				return false;
			}
		});
		
		lv.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> av, View v,
					int position, long id) {
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> view) {
			}
		});
		
		super.onResume();
	}	
}
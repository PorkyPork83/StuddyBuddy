package eu.dtc.studybuddy;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import eu.dtc.studybuddy.helpers.FilesystemHelper;

public class SetManagerFragment extends Fragment {
	
	FilesystemHelper mHelper;
	ArrayList<String> subjects;
	ArrayList<String> sets;
	ListView lv;
	Spinner spinner;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		setHasOptionsMenu(true);
		
		mHelper = new FilesystemHelper();
		
		subjects = new ArrayList<String>();
		subjects = mHelper.loadSubjectIndex(getActivity().getBaseContext());
		
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, subjects);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
        final View rootView = inflater.inflate(R.layout.fragment_set_manager, container, false);
        
        spinner = (Spinner)rootView.findViewById(R.id.spinner);
		spinner.setAdapter(spinnerAdapter);	
		
		sets = new ArrayList<String>();
		sets = mHelper.loadSetIndex(getActivity().getBaseContext(), String.valueOf(spinner.getSelectedItem()));
		
		final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, sets);
		
		lv = (ListView)rootView.findViewById(R.id.listViewSet);
		lv.setAdapter(listAdapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> av, View view,
					int position, long id) {
				
				sets = mHelper.loadSetIndex(getActivity().getBaseContext(), String.valueOf(spinner.getSelectedItem()));
				
				if (sets.isEmpty()) {
					
					refreshList();
					
				} else {
					
					refreshList();
				}		
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
        return rootView;
    }
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.edit_set, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	public void refreshList() {
		
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, sets);		
		
		lv = (ListView)getActivity().findViewById(R.id.listViewSet);
		lv.setAdapter(listAdapter);
	}
}

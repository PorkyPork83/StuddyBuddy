package eu.dtc.studybuddy;

import java.util.ArrayList;
import java.util.Map;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import eu.dtc.studybuddy.R;
import eu.dtc.studybuddy.helpers.FilesystemHelper;

public class ChooseQuizFragment extends Fragment {
	
	ArrayList<String> subjects;
	ArrayList<String> sets;
	FilesystemHelper mHelper;
	Map<String, ArrayList<String>> myCollection;
	ExpandableListView list;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
	 
        View rootView = inflater.inflate(R.layout.fragment_choose_quiz, container, false);
        setHasOptionsMenu(true);
		
        return rootView;
    }
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}	
}

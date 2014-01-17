package eu.dtc.studybuddy;

import java.util.ArrayList;

import eu.dtc.studybuddy.helpers.FilesystemHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class CreateSubjectActivity extends Activity{
	
	EditText enterSubject;
	FilesystemHelper mHelper;
	ArrayList<String> subjects;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.fragment_subject_creator);
		setTitle(R.string.titleSubjectCreator);
		super.onCreate(savedInstanceState);
		
		enterSubject = (EditText)findViewById(R.id.textfieldSubject);
		mHelper = new FilesystemHelper();
		subjects = mHelper.loadSubjectIndex(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.confirm_cancel, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.confirm:
			
			String subject =  new String();
			subject = enterSubject.getText().toString();
			
			if(subject.length()>=3){
			
				if (mHelper.ifSubjectAlreadyExists(subject)) {
				
				Toast.makeText(this, 
						getResources().getText(R.string.subject) + " \"" 
						+ subject + "\" "
						+ getResources().getText(R.string.not_added),
						Toast.LENGTH_SHORT).show();
				} else {
					
					subjects.add(subject);
					mHelper.saveSubjectIndex(this, subjects);
					mHelper.createSubjectFolder(subject);
				
					Toast.makeText(this, 
							getResources().getText(R.string.subject) + " \"" 
							+ subject + "\" "
							+ getResources().getText(R.string.added),
							Toast.LENGTH_SHORT).show();
					this.finish();
				}
			
			} else if (subject.length()<3){
				
				Toast.makeText(this, getResources().getText(R.string.no_name_given), Toast.LENGTH_SHORT).show();
			}
			
			return true;
		case R.id.cancel:
			
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
}

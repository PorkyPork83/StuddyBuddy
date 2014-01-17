package eu.dtc.studybuddy.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class FilesystemHelper {

	public void createSubjectFolder(String folderName) {
		
		File folder = new File(Environment.getExternalStorageDirectory() + "/StudyBuddy/" + folderName);
		if (!folder.exists()) {
		   folder.mkdir();
		}
	}
	
	public void createMainFolder() {
		File folder = new File(Environment.getExternalStorageDirectory() + "/StudyBuddy/");
		if (!folder.exists()) {
		    folder.mkdir();
		}
	}
	
	public boolean isExternalStorageWritable() {
		
		String state = Environment.getExternalStorageState();
		
		if(Environment.MEDIA_MOUNTED.equals(state)) {
			
			return true;
			
		} else {
			
			return false;
		}
	}
	
	public void saveSubjectIndex(Context context, ArrayList<String> subject){
		
		String operatingDirectory = Environment.getExternalStorageDirectory() + "/StudyBuddy/";
		String fileName = "Subjects.sub";
		File file = new File(Environment.getExternalStorageDirectory() + "/StudyBuddy/" + fileName);
		String preparedSubjectList = null;		
		
		if(isExternalStorageWritable() == true) {
			
			if (subject.size()==1){
				
				preparedSubjectList = new String();
				preparedSubjectList = subject.get(0);
				
			} else if (subject.size() >=1){
				
				preparedSubjectList= new String();		
				preparedSubjectList = subject.get(0);
				
				for (int i = 1; i< subject.size();i++){
					
					preparedSubjectList = preparedSubjectList + "\n" + subject.get(i);
					
				}
			} else {
				subject.clear();
			}
			
			if (!subject.isEmpty()) {
		
				try {
					
					File f = new File(operatingDirectory + fileName);
			
					FileOutputStream outStream = new FileOutputStream(f);
					outStream.write(preparedSubjectList.getBytes());
					outStream.flush();
					outStream.close();
				
			
				} catch (IOException ex) {
			
					ex.printStackTrace();		
				}
				
			}else {
				file.delete();
			}
			
		} else {
			
			Toast.makeText(context, "Media mounted", Toast.LENGTH_SHORT).show();
		}
	}
	
	public ArrayList<String> loadSubjectIndex(Context context) {
		
		ArrayList<String> mIndex = new ArrayList<String>();
		String folder = Environment.getExternalStorageDirectory()+"/StudyBuddy/";
		String fileName = "Subjects.sub";
		String line = null;
		File file = new File(folder+fileName);
				
		if (isExternalStorageWritable() == true ) {
			
			if(file.exists()) {
			
				try {
					FileReader fileReader = new FileReader(file);
					BufferedReader reader = new BufferedReader(fileReader);
				
					while((line = reader.readLine()) != null) {
						mIndex.add(line);
					}
					reader.close();
				} catch (IOException e) {
				}
			} else {
				Toast.makeText(context, "Bitte ein Fach anlegen", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(context, "Media mounted", Toast.LENGTH_SHORT).show();
		}
		
		return mIndex;
	}
	
	public boolean ifSubjectAlreadyExists(String folderName) {
		
		File folder = new File(Environment.getExternalStorageDirectory() + "/StudyBuddy/" + folderName + "/");
		
		if (folder.exists()) {
		   return true;
		   
		} else {
			return false;
			
		}
	}
	
	public void deleteSubject(Context context,ArrayList<String> list, int position) {
		
		File file = new File(Environment.getExternalStorageDirectory() + "/StudyBuddy/" + list.get(position));
		list.remove(position);
		file.delete();
		saveSubjectIndex(context, list);
	}
	
	public void saveSetIndex(Context context, String subject, ArrayList<String> sets){
		
		String operatingDirectory = Environment.getExternalStorageDirectory() + "/StudyBuddy/" + subject + "/";
		String fileName = "Sets.set";
		String preparedSubjectList = null;
		
		if(isExternalStorageWritable() == true) {
			
			if (sets.size()==1){
				
				preparedSubjectList = new String();
				preparedSubjectList = sets.get(0);
				
			} else if (sets.size() >=1){
				
				preparedSubjectList= new String();		
				preparedSubjectList = sets.get(0);
				
				for (int i = 1; i< sets.size();i++){
					
					preparedSubjectList = preparedSubjectList + "\n" + sets.get(i);
					
				}
			}
		
			try {
				File f = new File(operatingDirectory + fileName);
			
				FileOutputStream outStream = new FileOutputStream(f);
				outStream.write(preparedSubjectList.getBytes());
				outStream.flush();
				outStream.close();
			
			} catch (IOException ex) {
			
				ex.printStackTrace();		
			}
			
		} else {
			
			Toast.makeText(context, "Media mounted", Toast.LENGTH_SHORT).show();
		}
	}
	
	public ArrayList<String> loadSetIndex(Context context, String subject) {
		
		ArrayList<String> mIndex = new ArrayList<String>();
		String folder = Environment.getExternalStorageDirectory()+"/StudyBuddy/" + subject + "/";
		String fileName = "Sets.set";
		String line = null;
		File file = new File(folder+fileName);
				
		if (isExternalStorageWritable() == true ) {
			
			if(file.exists()) {
			
				try {
					FileReader fileReader = new FileReader(file);
					BufferedReader reader = new BufferedReader(fileReader);
				
					while((line = reader.readLine()) != null) {
						mIndex.add(line);
					}
					reader.close();
				} catch (IOException e) {
				}
			} else {
				
				Toast.makeText(context, "Noch kein Set für dieses Fach vorhanden", Toast.LENGTH_SHORT).show();
			}
			
		} else {
			
			Toast.makeText(context, "Media mounted", Toast.LENGTH_SHORT).show();
		}
		
		return mIndex;
	}
	
	public boolean ifSetAlreadyExists(String subjectName, String setName) {
		
		File folder = new File(Environment.getExternalStorageDirectory() + "/StudyBuddy/" + subjectName + "/" + setName + "/");
		
		if (folder.exists()) {
		   return true;
		   
		} else {
			return false;
			
		}
	}
	
}

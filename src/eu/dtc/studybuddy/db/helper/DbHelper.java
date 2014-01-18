package eu.dtc.studybuddy.db.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import eu.dtc.studybuddy.db.model.Subject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	// database settings
	private static final String DB_NAME = "StudyBuddy.db";
	private static final int DB_VERSION = 1;
	
	// table names
	private static final String TABLE_SUBJECT = "subjects";
	private static final String TABLE_SET = "sets";
	private static final String TABLE_SUBJECT_SET = "joint_subject_set";
	
	// common columns
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";
	
	// SUBJECTS table
	private static final String KEY_SUBJECT_NAME = "name";
	
	// SETS table
	private static final String KEY_SET_NAME = "name";
	
	// column connectors tables
	private static final String KEY_SUBJECT_ID = "subject_id";
	private static final String KEY_SET_ID = "set_id";
	
	// Table-CreateStatements
	
	// subject create statement
	private static final String CREATE_TABLE_SUBJECT = "CREATE TABLE "
			+ TABLE_SUBJECT + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_SUBJECT_NAME + " TEXT, " + KEY_CREATED_AT + " DATETIME)";
	
	// set create statement
	private static final String CREATE_TABLE_SET = "CREATE TABLE "
			+ TABLE_SET + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_SET_NAME + " TEXT, " + KEY_CREATED_AT + " DATETIME)";
	
	// subject_set create statement
	private static final String CREATE_TABLE_SUBJECT_SET = "CREATE TABLE "
			+ TABLE_SUBJECT_SET + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_SUBJECT_ID + " INTEGER, " + KEY_SET_ID + " INTEGER)";
	
	// constructor
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	// Create tables on first time
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// create required tables
		db.execSQL(CREATE_TABLE_SUBJECT);
		db.execSQL(CREATE_TABLE_SET);
		db.execSQL(CREATE_TABLE_SUBJECT_SET);

	}
	
	// Drop old tables on database upgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// onUpgrade drop old tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SET);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT_SET);
		
		onCreate(db);
	}
	
	// Closing Database
	public void closeDb() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen()) {
			db.close();
		}
	}
	
	// Get Datetime
	public String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	// ----- Subject Methods -----
	
	/*
	 * Creating a Subject
	 */
	public long addSubject(Subject subject) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_SUBJECT_NAME, subject.getName());
		values.put(KEY_CREATED_AT, getDateTime());
		
		// insert row
		long subject_id = db.insert(TABLE_SUBJECT, null, values);
		
		return subject_id;
	}
	
	/*
	 * Fetching All Subjects
	 */
	public ArrayList<Subject> getAllSubjects() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		String dbQuery = "SELECT * FROM " + TABLE_SUBJECT;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(dbQuery, null);
		
		if (c.moveToFirst()) {
			do {
				Subject subj = new Subject();
				subj.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				subj.setName(c.getString(c.getColumnIndex(KEY_SUBJECT_NAME)));
				
				subjects.add(subj);
			} while (c.moveToNext());
		}
		
		return subjects;
	}
	
	/*
	 * Updating a Subject
	 */
	public int updateSubject(Subject subject) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_SUBJECT_NAME, subject.getName());
		
		return db.update(TABLE_SUBJECT, values, KEY_ID + " = ?",
				new String[] { String.valueOf(subject.getId()) });
	}
	
	/*
	 * Deleting a Subject
	 */
	public void deleteSubject(Subject subject, boolean should_delete_all_sub_sets) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		/*
		// Delete Sets linked with this Subject
		if (should_delete_all_sub_sets) {
			List<Set> allSubjectSets = getAllSetsBySubject(subject.getName());
			
			for (Set set : allSubjectSets) {
				deleteSet(set.getId());
			}
		}
		*/
		
		// Delete Subject
		db.delete(TABLE_SUBJECT, KEY_ID + " = ?",
				new String[] { String.valueOf(subject.getId()) });
	}
	
	// ----- Set Methods -----
	
	/*
	 * Creating a Set
	 */
	
	/*
	 * Fetching a Set
	 */
	
	/*
	 * Fetching All Sets
	 */
	
	/*
	 * Updating a Set
	 */
	
	/*
	 * Deleting a Set
	 */
	
	
	
}
package eu.dtc.studybuddy.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
	
	// database settings
	private static final String DB_NAME = "StudyBuddy.db";
	private static final int DB_VERSION = 1;
	
	// table names
	private static final String TABLE_SUBJECT = "";
	private static final String TABLE_SET = "";
	private static final String TABLE_SUBJECT_SET = "";
	
	// common columns
	private static final String KEY_ID = "_id";
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

	
	
	public DbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// create required tables
		db.execSQL(CREATE_TABLE_SUBJECT);
		db.execSQL(CREATE_TABLE_SET);
		db.execSQL(CREATE_TABLE_SUBJECT_SET);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// onUpgrade drop old tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SET);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT_SET);
		
		onCreate(db);
	}

}

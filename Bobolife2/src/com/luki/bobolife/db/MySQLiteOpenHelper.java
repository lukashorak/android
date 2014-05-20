package com.luki.bobolife.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String TAG = MySQLiteOpenHelper.class.getSimpleName();

	public static final String TABLE_BOBO_LIFE = "bobolife";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DAY = "day";
	public static final String COLUMN_TOC_VALUE = "toc_value";
	public static final String COLUMN_TOC_DATE = "toc_data";
	public static final String COLUMN_WORK_VALUE = "work_value";
	public static final String COLUMN_WORK_DATE = "toc_date";

	private static final String DATABASE_NAME = "bobolife.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_BOBO_LIFE + "(" + COLUMN_ID
			+ " integer primary key autoincrement," + COLUMN_DAY
			+ " text not null, " + COLUMN_TOC_VALUE + " text,"
			+ COLUMN_TOC_DATE + " INTEGER, " + COLUMN_WORK_VALUE + " text,"
			+ COLUMN_WORK_DATE + " INTEGER" + " );";

	public MySQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOBO_LIFE);
		onCreate(db);
	}

}
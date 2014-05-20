package com.luki.bobolife.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.luki.bobolife.model.AnswerDataLogItem;

public class AnswerDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteOpenHelper dbHelper;
	private String[] allColumns = { MySQLiteOpenHelper.COLUMN_ID,
			MySQLiteOpenHelper.COLUMN_DAY, MySQLiteOpenHelper.COLUMN_TOC_VALUE,
			MySQLiteOpenHelper.COLUMN_TOC_DATE,
			MySQLiteOpenHelper.COLUMN_WORK_VALUE,
			MySQLiteOpenHelper.COLUMN_WORK_DATE };

	public AnswerDataSource(Context context) {
		dbHelper = new MySQLiteOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public AnswerDataLogItem createAnswerItem(String day, String tocValue,
			Long tocDate, String workValue, Long workDate) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(MySQLiteOpenHelper.COLUMN_DAY, day);
		values.put(MySQLiteOpenHelper.COLUMN_TOC_VALUE, tocValue);
		values.put(MySQLiteOpenHelper.COLUMN_TOC_DATE, tocDate);
		values.put(MySQLiteOpenHelper.COLUMN_WORK_VALUE, workValue);
		values.put(MySQLiteOpenHelper.COLUMN_WORK_DATE, workDate);

		long insertId = database.insert(MySQLiteOpenHelper.TABLE_BOBO_LIFE,
				null, values);
		Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_BOBO_LIFE,
				allColumns, MySQLiteOpenHelper.COLUMN_ID + " = " + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		AnswerDataLogItem newItem = cursorToAnswerDataLogItem(cursor);
		cursor.close();
		this.close();
		return newItem;
	}

	public void deleteAnswerItem(AnswerDataLogItem item) {
		long id = item.getId();
		this.open();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteOpenHelper.TABLE_BOBO_LIFE,
				MySQLiteOpenHelper.COLUMN_ID + " = " + id, null);
		this.close();
	}

	public AnswerDataLogItem updateAnswerItem(AnswerDataLogItem item) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(MySQLiteOpenHelper.COLUMN_DAY, item.getDay());
		values.put(MySQLiteOpenHelper.COLUMN_TOC_VALUE,
				item.getAnswerTeaOrCoffeeValue());
		values.put(MySQLiteOpenHelper.COLUMN_TOC_DATE,
				item.getAnswerTeaOrCoffeeDate());
		values.put(MySQLiteOpenHelper.COLUMN_WORK_VALUE,
				item.getAnswerWork1Value());
		values.put(MySQLiteOpenHelper.COLUMN_WORK_DATE,
				item.getAnswerWork1Date());
		database.update(MySQLiteOpenHelper.TABLE_BOBO_LIFE, values,
				MySQLiteOpenHelper.COLUMN_ID + " = " + item.getId(), null);
		this.close();
		return null;
	}

	public AnswerDataLogItem getItemByDay(String day) {
		AnswerDataLogItem item = null;
		this.open();
		synchronized (database) {
			Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_BOBO_LIFE,
					allColumns, MySQLiteOpenHelper.COLUMN_DAY + " = " + day,
					null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				item = cursorToAnswerDataLogItem(cursor);
				cursor.moveToNext();
			}
			// make sure to close the cursor
			cursor.close();
		}
		this.close();
		return item;
	}

	public List<AnswerDataLogItem> getAllAnswerItems() {
		List<AnswerDataLogItem> items = new ArrayList<AnswerDataLogItem>();
		this.open();

		synchronized (database) {
			Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_BOBO_LIFE,
					allColumns, null, null, null, null,
					MySQLiteOpenHelper.COLUMN_DAY + " DESC");

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				AnswerDataLogItem item = cursorToAnswerDataLogItem(cursor);
				items.add(item);
				cursor.moveToNext();
			}
			// make sure to close the cursor
			cursor.close();
		}
		this.close();
		return items;
	}

	private AnswerDataLogItem cursorToAnswerDataLogItem(Cursor cursor) {
		AnswerDataLogItem item = new AnswerDataLogItem();
		item.setId(cursor.getLong(0));
		item.setDay(cursor.getString(1));
		item.setAnswerTeaOrCoffeeValue(cursor.getString(2));
		item.setAnswerTeaOrCoffeeDate(cursor.getLong(3));
		item.setAnswerWork1Value(cursor.getString(4));
		item.setAnswerWork1Date(cursor.getLong(5));
		return item;
	}
}
package com.luki.bobolife;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.luki.bobolife.db.AnswerDataSource;

public class BoboLifeApplication extends Application {

	public static final String SHARED_PREFERENCES = "com.luki.bobolife";
	public static final String SHARED_PREFERENCES_LOG = "com.luki.bobolife.log";

	public static final String SHARED_PREFERENCES_TOC_ALARM_HOUR = "com.luki.bobolife.tocAlarmHour";
	public static final String SHARED_PREFERENCES_TOC_ALARM_MINUTE = "com.luki.bobolife.tocAlarmMinute";
	public static final String SHARED_PREFERENCES_WORK_ALARM_HOUR = "com.luki.bobolife.workAlarmHour";
	public static final String SHARED_PREFERENCES_WORK_ALARM_MINUTE = "com.luki.bobolife.workAlarmMinute";
	public static final String SHARED_PREFERENCES_WORK_ALARM_DAY_OF_WEEK = "com.luki.bobolife.workAlarmDayOfWeek";

	public Integer tocAlarmHour;
	public Integer tocAlarmMinute;
	public Integer workAlarmMinute;
	public Integer workAlarmHour;
	public Set<String> workAlarmDayOfWeek;

	// public AnswerDataLog log;
	// public AnswerDataLogItem todayData;
	public AnswerDataSource datasource;

	// public AnswerDataDao dao;

	private static BoboLifeApplication singleton;

	public static BoboLifeApplication getInstance() {
		return singleton;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;

		this.loadSharedPreferences();
		// this.dao = new AnswerDataDao(getBaseContext());

	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();

	}

	public void saveSharedPreferences() {
		SharedPreferences prefs = this.getSharedPreferences(BoboLifeApplication.SHARED_PREFERENCES,
				Context.MODE_PRIVATE);

		// Gson gson = new Gson();
		// String json = gson.toJson(this.log);
		// .putString(SHARED_PREFERENCES_LOG, json)
		prefs.edit().putInt(SHARED_PREFERENCES_TOC_ALARM_HOUR, this.tocAlarmHour)
				.putInt(SHARED_PREFERENCES_TOC_ALARM_MINUTE, this.tocAlarmMinute)
				.putInt(SHARED_PREFERENCES_WORK_ALARM_HOUR, this.workAlarmHour)
				.putInt(SHARED_PREFERENCES_WORK_ALARM_MINUTE, this.workAlarmMinute)
				.putStringSet(SHARED_PREFERENCES_WORK_ALARM_DAY_OF_WEEK, this.workAlarmDayOfWeek).commit();
	}

	public void loadSharedPreferences() {
		SharedPreferences prefs = this.getSharedPreferences(BoboLifeApplication.SHARED_PREFERENCES,
				Context.MODE_PRIVATE);
		// Gson gson = new Gson();
		// String json = prefs.getString(SHARED_PREFERENCES_LOG, null);
		this.tocAlarmHour = prefs.getInt(SHARED_PREFERENCES_TOC_ALARM_HOUR, 10);
		this.tocAlarmMinute = prefs.getInt(SHARED_PREFERENCES_TOC_ALARM_MINUTE, 30);
		this.workAlarmHour = prefs.getInt(SHARED_PREFERENCES_WORK_ALARM_HOUR, 19);
		this.workAlarmMinute = prefs.getInt(SHARED_PREFERENCES_WORK_ALARM_MINUTE, 30);
		this.workAlarmDayOfWeek = prefs.getStringSet(SHARED_PREFERENCES_WORK_ALARM_DAY_OF_WEEK, new HashSet<String>(
				Arrays.asList("2", "3", "4", "5", "6")));
		// this.log = gson.fromJson(json, AnswerDataLog.class);
		// if (this.log == null) {
		// this.log = new AnswerDataLog();
		// }
	}
	// public AnswerDataLogItem getTodayAnswerData() {
	// Date now = new Date();
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
	// String dateKey = sdf.format(now);
	//
	// if (todayData != null) {
	// if (!dateKey.equals(todayData.getDay())) {
	// this.log.log.put(todayData.getDay(), todayData);
	// todayData = new AnswerDataLogItem();
	// todayData.setDay(dateKey);
	// }
	// } else {
	// todayData = this.log.log.get(dateKey);
	// if (todayData == null) {
	// todayData = new AnswerDataLogItem();
	// }
	// }
	// return this.todayData;
	// }

	// public void updateTodayAnswerData(AnswerDataLogItem item) {
	// this.todayData = item;
	//
	// this.log.log.remove(item.getDay());
	// this.log.log.put(item.getDay(), item);
	// }
}

package com.luki.bobolife;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.luki.bobolife.db.AnswerDataDao;
import com.luki.bobolife.model.AnswerDataLogItem;

public class AlarmScheduler {

	private static final String TAG = AlarmScheduler.class.getSimpleName();

	public AlarmScheduler(Context context) {
		this.context = context;
	}

	Context context;

	public void setAlarm(Class<?> target, Calendar time) {
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		Intent intent = new Intent(context, target);
		intent.putExtra("AlarmType", "TeaOrCoffee");
		PendingIntent nextTeaOrCoffeePendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

		alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), nextTeaOrCoffeePendingIntent);
		Log.i(TAG, "Schedule alarm:" + time.getTime() + " To:" + target.getSimpleName());
	}

	public void setTeaOrCoffeeAlarm() {

		Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.HOUR, 10);
		// cal.set(Calendar.MINUTE, 30);
		//
		// // Check if answered
		// // TODO
		// if (cal.before(Calendar.getInstance())) {
		// cal.add(Calendar.DAY_OF_MONTH, 1);
		// }

		BoboLifeApplication app = BoboLifeApplication.getInstance();
		AnswerDataDao dao = new AnswerDataDao(context);

		cal.set(Calendar.HOUR_OF_DAY, app.tocAlarmHour);
		cal.set(Calendar.MINUTE, app.tocAlarmMinute);
		cal.set(Calendar.SECOND, 0);
		AnswerDataLogItem today = dao.getToday();
		if (cal.before(Calendar.getInstance()) && today != null && today.getAnswerTeaOrCoffeeDate() != null) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		this.setAlarm(TeaOrCoffeeActivity.class, cal);
	}

	public void setWorkReviewAlarm() {

		Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.HOUR, 20);
		// cal.set(Calendar.MINUTE, 30);
		//
		// if (cal.before(Calendar.getInstance())) {
		// cal.add(Calendar.DAY_OF_MONTH, 1);
		// }

		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.SECOND, 25);
		BoboLifeApplication app = BoboLifeApplication.getInstance();
		AnswerDataDao dao = new AnswerDataDao(context);

		cal.set(Calendar.HOUR_OF_DAY, app.workAlarmHour);
		cal.set(Calendar.MINUTE, app.workAlarmMinute);
		cal.set(Calendar.SECOND, 0);

		AnswerDataLogItem today = dao.getToday();
		if (cal.before(Calendar.getInstance()) && today != null && today.getAnswerWork1Date() != null) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		// TODO - check if enabled
		if (!app.workAlarmDayOfWeek.isEmpty() && app.workAlarmDayOfWeek.contains(cal.get(Calendar.DAY_OF_WEEK))) {

		}

		this.setAlarm(WorkReviewActivity.class, cal);
	}

	// public void setAlarm() {
	// AlarmManager alarmManager = (AlarmManager) context
	// .getSystemService(Context.ALARM_SERVICE);
	//
	// Calendar cal = Calendar.getInstance();
	// cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + 30);
	//
	// Intent intent = new Intent(context, TeaOrCoffeeActivity.class);
	// intent.putExtra("AlarmType", "TeaOrCoffee");
	// PendingIntent nextTeaOrCoffeePendingIntent = PendingIntent.getActivity(
	// context, 0, intent, 0);
	//
	// alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
	// nextTeaOrCoffeePendingIntent);
	// Log.i(TAG, "Schedule alarm:" + cal.getTime());
	// }

	@SuppressWarnings("deprecation")
	public void cancelAlarm() {
		Intent intent = new Intent(context, TeaOrCoffeeActivity.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
		Log.i(TAG, "Cancel alarm:" + sender.getTargetPackage());
	}

}

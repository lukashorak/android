package com.luki.bobolife;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.luki.bobolife.db.AnswerDataDao;
import com.luki.bobolife.model.AnswerDataLogItem;
import com.luki.bobolife.tasks.DataPostTask;

public class WorkReviewActivity extends Activity {
	private static final String TAG = WorkReviewActivity.class.getSimpleName();
	public static final String GOOD = "Good";
	public static final String OK = "Ok";
	public static final String BAD = "Bad";

	AlarmScheduler alarmScheduler;

	boolean isGood, isOk, isBad;

	ImageView imageButtonWorkGood;
	ImageView imageButtonWorkOk;
	ImageView imageButtonWorkBad;

	private AnswerDataDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_review);
		dao = new AnswerDataDao(getBaseContext());

		alarmScheduler = new AlarmScheduler(getBaseContext());

		this.imageButtonWorkGood = (ImageView) findViewById(R.id.imageButtonWorkGood);
		this.imageButtonWorkOk = (ImageView) findViewById(R.id.imageButtonWorkOk);
		this.imageButtonWorkBad = (ImageView) findViewById(R.id.imageButtonWorkBad);

		// If day not enabled - then just finish
		BoboLifeApplication app = BoboLifeApplication.getInstance();
		Calendar c = Calendar.getInstance();
		if (!app.workAlarmDayOfWeek.contains(c.get(Calendar.DAY_OF_WEEK))) {
			setResult(RESULT_OK);
			alarmScheduler.setWorkReviewAlarm();
			finish();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("isGood", isGood);
		outState.putBoolean("isOk", isOk);
		outState.putBoolean("isBad", isBad);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		this.isGood = savedInstanceState.getBoolean("isGood");
		this.isOk = savedInstanceState.getBoolean("isOk");
		this.isBad = savedInstanceState.getBoolean("isBad");

		if (this.isGood) {
			this.changeButtons(true, false, false);
		}
		if (this.isOk) {
			this.changeButtons(false, true, false);
		}
		if (this.isBad) {
			this.changeButtons(false, false, true);
		}
	}

	public void clickWorkGood(View view) {
		this.changeButtons(true, false, false);
	}

	public void clickWorkOk(View view) {
		this.changeButtons(false, true, false);
	}

	public void clickWorkBad(View view) {
		this.changeButtons(false, false, true);
	}

	public void changeButtons(boolean isGood, boolean isOk, boolean isBad) {
		Log.i(TAG, isGood + "|" + isOk + "|" + isBad);
		this.isGood = isGood;
		this.isOk = isOk;
		this.isBad = isBad;
		this.changeSelection(isGood, this.imageButtonWorkGood);
		this.changeSelection(isOk, this.imageButtonWorkOk);
		this.changeSelection(isBad, this.imageButtonWorkBad);
	}

	public void changeSelection(boolean value, View view) {
		if (value) {
			view.setBackgroundColor(getResources().getColor(R.color.pomegranate));
		} else {
			view.setBackgroundColor(getResources().getColor(R.color.clouds));
		}
	}

	public void clickConfirm(View view) {
		if (isGood || isOk || isBad) {

			AnswerDataLogItem today = dao.getToday();
			Log.i(TAG, "Before :" + today);

			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
			String dateKey = sdf.format(now);

			AnswerDataLogItem object = today;
			if (object == null) {
				object = new AnswerDataLogItem();
			}
			object.setDay(dateKey);

			object.setAnswerWork1Date(now.getTime());

			String resultValue = null;
			if (isGood) {
				resultValue = GOOD;
			} else if (isOk) {
				resultValue = OK;
			} else if (isBad) {
				resultValue = BAD;
			}

			today = dao.updateToday(null, null, now.getTime(), resultValue);
			// app.todayData = object;
			// app.log.log.remove(dateKey);
			// app.log.log.put(dateKey, object);
			// app.saveSharedPreferences();
			setResult(RESULT_OK);
			alarmScheduler.setWorkReviewAlarm();

			DataPostTask dataPostTask = new DataPostTask(getBaseContext());
			dataPostTask.execute(object);
			Log.i(TAG, "Final :" + today);
			this.finish();
		} else {
			Toast.makeText(getBaseContext(), "Please select one option", Toast.LENGTH_SHORT).show();

		}
	}
}

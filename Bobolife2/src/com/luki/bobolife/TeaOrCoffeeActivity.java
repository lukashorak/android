package com.luki.bobolife;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.luki.bobolife.db.AnswerDataDao;
import com.luki.bobolife.model.AnswerDataLogItem;
import com.luki.bobolife.tasks.DataPostTask;

public class TeaOrCoffeeActivity extends Activity {
	private static final String TAG = TeaOrCoffeeActivity.class.getSimpleName();
	public static final String TEA = "Tea";
	public static final String COFFEE = "Coffee";

	boolean isTea, isCoffee;

	AlarmScheduler alarmScheduler;

	ImageView imageButtonTea;
	ImageView imageButtonCoffee;

	private AnswerDataDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tea_or_coffee);
		dao = new AnswerDataDao(getBaseContext());

		alarmScheduler = new AlarmScheduler(getBaseContext());

		this.imageButtonTea = (ImageView) findViewById(R.id.imageButtonTea);
		this.imageButtonCoffee = (ImageView) findViewById(R.id.imageButtonCoffee);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("isTea", isTea);
		outState.putBoolean("isCoffee", isCoffee);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		this.isTea = savedInstanceState.getBoolean("isTea");
		this.isCoffee = savedInstanceState.getBoolean("isCoffee");

		if (this.isTea) {
			this.clickTea(null);
		}
		if (this.isCoffee) {
			this.clickCoffee(null);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void clickTea(View view) {
		isTea = true;
		isCoffee = false;

		this.changeSelection(isTea, this.imageButtonTea);
		this.changeSelection(isCoffee, this.imageButtonCoffee);
	}

	public void clickCoffee(View view) {
		isCoffee = true;
		isTea = false;

		this.changeSelection(isTea, this.imageButtonTea);
		this.changeSelection(isCoffee, this.imageButtonCoffee);
	}

	public void changeSelection(boolean value, View view) {
		if (value) {
			view.setBackgroundColor(getResources()
					.getColor(R.color.pomegranate));
		} else {
			view.setBackgroundColor(getResources().getColor(R.color.clouds));
		}
	}

	public void clickConfirm(View view) {

		if (isTea || isCoffee) {

			AnswerDataLogItem today = dao.getToday();

			Log.i(TAG, "Before :" + today);
			Date now = new Date();

			AnswerDataLogItem object = dao.getToday();
			Log.i(TAG, "After :" + today);

			object.setAnswerTeaOrCoffeeDate(now.getTime());
			object.setAnswerTeaOrCoffeeValue(isTea ? TEA : COFFEE);

			today = dao.updateToday(now.getTime(), isTea ? TEA : COFFEE, null,
					null);
			// app.todayData = object;
			// app.log.log.remove(dateKey);
			// app.log.log.put(dateKey, object);
			// app.saveSharedPreferences();
			setResult(RESULT_OK);

			alarmScheduler.setTeaOrCoffeeAlarm();

			DataPostTask dataPostTask = new DataPostTask(getBaseContext());
			dataPostTask.execute(object);
			Log.i(TAG, "Final :" + today);
			this.finish();
		} else {
			Toast.makeText(getBaseContext(), "Please select one option",
					Toast.LENGTH_SHORT).show();

		}
	}

}

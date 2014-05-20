package com.luki.bobolife;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.luki.bobolife.adapters.DataLogListAdapter;
import com.luki.bobolife.db.AnswerDataDao;
import com.luki.bobolife.model.AnswerDataLogItem;
import com.luki.bobolife.tasks.DataSyncTask;

public class MainActivity extends Activity {

	protected static final String TAG = MainActivity.class.getSimpleName();
	AlarmScheduler alarmScheduler;
	private ListView listview;
	public ArrayList<AnswerDataLogItem> list;
	public DataLogListAdapter adapter;
	public ProgressBar progressBarSync;
	private AnswerDataDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		setTheme(R.style.AppTheme);

		dao = new AnswerDataDao(getBaseContext());

		this.progressBarSync = (ProgressBar) findViewById(R.id.progressBarSync);
		this.listview = (ListView) findViewById(R.id.listViewData);
		list = new ArrayList<AnswerDataLogItem>();
		list.addAll(dao.getAll());

		adapter = new DataLogListAdapter(this, list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final AnswerDataLogItem item = (AnswerDataLogItem) parent
						.getItemAtPosition(position);
				Log.i(TAG, "User Clicked on item :" + position + " Value:"
						+ item.getDay());
			}

		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		alarmScheduler = new AlarmScheduler(getBaseContext());
		alarmScheduler.setTeaOrCoffeeAlarm();
		alarmScheduler.setWorkReviewAlarm();

		this.refreshList();
		this.refreshTodayDate();
	}

	@Override
	protected void onPause() {
		super.onPause();
		dao.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void refreshList() {
		listview.invalidate();
		list.clear();
		final Collection<AnswerDataLogItem> itemList = dao.getAll();
		Log.i(TAG, "List:" + Arrays.toString(itemList.toArray()));
		list.addAll(itemList);
		((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();
	}

	public void refreshTodayDate() {
		AnswerDataLogItem todayData = dao.getToday();
		// AnswerDataLogItem todayData = list.get(0);
		final AnswerDataLogItem dataToUi = todayData;

		Log.i(TAG, "Today:" + dataToUi);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				TextView textViewToday = (TextView) findViewById(R.id.textViewToday);
				textViewToday.setText(dataToUi.getDay());

				ImageView imageViewTocIcon = (ImageView) findViewById(R.id.toc_icon);
				ImageView imageViewWorkIcon = (ImageView) findViewById(R.id.work_icon);

				if (TeaOrCoffeeActivity.TEA.equals(dataToUi
						.getAnswerTeaOrCoffeeValue())) {
					imageViewTocIcon.setImageResource(R.drawable.tea);
				} else if (TeaOrCoffeeActivity.COFFEE.equals(dataToUi
						.getAnswerTeaOrCoffeeValue())) {
					imageViewTocIcon.setImageResource(R.drawable.coffee);
				}
				if (WorkReviewActivity.GOOD.equals(dataToUi
						.getAnswerWork1Value())) {
					imageViewWorkIcon.setImageResource(R.drawable.creativity);
				} else if (WorkReviewActivity.OK.equals(dataToUi
						.getAnswerWork1Value())) {
					imageViewWorkIcon.setImageResource(R.drawable.work_hard);
				} else if (WorkReviewActivity.BAD.equals(dataToUi
						.getAnswerWork1Value())) {
					imageViewWorkIcon
							.setImageResource(R.drawable.procrastination);
				}

				TextView toc_name = (TextView) findViewById(R.id.toc_name);
				TextView work_name = (TextView) findViewById(R.id.work_name);

				DateFormat tf = DateFormat.getTimeInstance();

				if (dataToUi.getAnswerTeaOrCoffeeDate() != null) {
					toc_name.setText(dataToUi.getAnswerTeaOrCoffeeValue()
							+ " "
							+ tf.format(new Date(dataToUi
									.getAnswerTeaOrCoffeeDate())));
				}
				if (dataToUi.getAnswerWork1Date() != null) {
					work_name
							.setText(dataToUi.getAnswerWork1Value()
									+ " "
									+ tf.format(new Date(dataToUi
											.getAnswerWork1Date())));
				}
			}
		});
	}

	public void clickSync(View view) {
		progressBarSync.setVisibility(View.VISIBLE);
		DataSyncTask task = new DataSyncTask(getBaseContext()) {
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				progressBarSync.setVisibility(View.INVISIBLE);
			}
		};
		// FIXME
		task.execute(dao.getAll());
	}

	public void clickTeaOrCoffee(View view) {
		Intent intent = new Intent(getBaseContext(), TeaOrCoffeeActivity.class);
		startActivity(intent);
	}

	public void clickWorkReview(View view) {
		Intent intent = new Intent(getBaseContext(), WorkReviewActivity.class);
		startActivity(intent);
	}

	public void menuSettings(MenuItem item) {
		Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
		startActivity(intent);
	}

	public void menuStatistics(MenuItem item) {
		Intent intent = new Intent(getBaseContext(), StatisticsActivity.class);
		startActivity(intent);
	}

	public void menuF2Checkin(MenuItem item) {
		Intent intent = new Intent(getBaseContext(),
				FoursquareCheckinActivity.class);
		startActivity(intent);
	}
}

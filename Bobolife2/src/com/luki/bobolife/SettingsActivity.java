package com.luki.bobolife;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;

public class SettingsActivity extends Activity {

	TimePicker timePickerToc;
	TimePicker timePickerWork;

	CheckBox checkBoxMonday;
	CheckBox checkBoxTuesday;
	CheckBox checkBoxWednesday;
	CheckBox checkBoxThursday;
	CheckBox checkBoxFriday;
	CheckBox checkBoxSaturday;
	CheckBox checkBoxSunday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		timePickerToc = (TimePicker) findViewById(R.id.timePickerToc);
		timePickerWork = (TimePicker) findViewById(R.id.timePickerWork);

		checkBoxMonday = (CheckBox) findViewById(R.id.checkBoxMonday);
		checkBoxTuesday = (CheckBox) findViewById(R.id.checkBoxTuesday);
		checkBoxWednesday = (CheckBox) findViewById(R.id.checkBoxWednesday);
		checkBoxThursday = (CheckBox) findViewById(R.id.checkBoxThursday);
		checkBoxFriday = (CheckBox) findViewById(R.id.checkBoxFriday);
		checkBoxSaturday = (CheckBox) findViewById(R.id.checkBoxSaturday);
		checkBoxSunday = (CheckBox) findViewById(R.id.checkBoxSunday);
	}

	@Override
	protected void onResume() {
		super.onResume();

		BoboLifeApplication app = BoboLifeApplication.getInstance();

		timePickerToc.setCurrentHour(app.tocAlarmHour);
		timePickerToc.setCurrentMinute(app.tocAlarmMinute);
		timePickerWork.setCurrentHour(app.workAlarmHour);
		timePickerWork.setCurrentMinute(app.workAlarmMinute);

		checkBoxMonday.setChecked(app.workAlarmDayOfWeek.contains("2"));
		checkBoxTuesday.setChecked(app.workAlarmDayOfWeek.contains("3"));
		checkBoxWednesday.setChecked(app.workAlarmDayOfWeek.contains("4"));
		checkBoxThursday.setChecked(app.workAlarmDayOfWeek.contains("5"));
		checkBoxFriday.setChecked(app.workAlarmDayOfWeek.contains("6"));
		checkBoxSaturday.setChecked(app.workAlarmDayOfWeek.contains("7"));
		checkBoxSunday.setChecked(app.workAlarmDayOfWeek.contains("1"));
	}

	public void clickTimeToc(View view) {

	}

	public void clickTimeWork(View view) {

	}

	public void clickConfirm(View view) {
		BoboLifeApplication app = BoboLifeApplication.getInstance();
		app.tocAlarmHour = timePickerToc.getCurrentHour();
		app.tocAlarmMinute = timePickerToc.getCurrentMinute();
		app.workAlarmHour = timePickerWork.getCurrentHour();
		app.workAlarmMinute = timePickerWork.getCurrentMinute();
		app.saveSharedPreferences();
		setResult(RESULT_OK);
		this.finish();
	}

	public Set<String> readWorkDayOfWeek() {
		HashSet<String> set = new HashSet<String>();

		if (checkBoxMonday.isChecked()) {
			set.add("2");
		}
		if (checkBoxTuesday.isChecked()) {
			set.add("3");
		}
		if (checkBoxWednesday.isChecked()) {
			set.add("4");
		}
		if (checkBoxThursday.isChecked()) {
			set.add("5");
		}
		if (checkBoxFriday.isChecked()) {
			set.add("6");
		}
		if (checkBoxSaturday.isChecked()) {
			set.add("7");
		}
		if (checkBoxSunday.isChecked()) {
			set.add("1");
		}

		return set;
	}

}

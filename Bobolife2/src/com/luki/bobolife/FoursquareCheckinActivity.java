package com.luki.bobolife;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FoursquareCheckinActivity extends Activity {

	private static final String TAG = FoursquareCheckinActivity.class
			.getSimpleName();
	boolean isLatte, isEspesso, isOther, isSandwich, isBagel;
	Integer moneySpend;

	EditText editTextMoneySpend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foursquarecheckin);

		this.editTextMoneySpend = (EditText) findViewById(R.id.editTextMoneySpend);
	}

	public void clickConfirmCheckin(View view) {

		if (!(isLatte || isEspesso || isOther || isSandwich || isBagel)) {
			Toast.makeText(getBaseContext(), "Please select one option",
					Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			this.moneySpend = Integer.valueOf(this.editTextMoneySpend.getText()
					.toString());
		} catch (Exception e) {

		}
		if (!(this.moneySpend != null && this.moneySpend > 0)) {
			Toast.makeText(getBaseContext(), "Please write value",
					Toast.LENGTH_SHORT).show();
			return;
		}

		// DataPostTask dataPostTask = new DataPostTask(getBaseContext());
		// dataPostTask.execute(object);
		Log.i(TAG, "Final :" + this.moneySpend + "\t" + isLatte + "|"
				+ isEspesso + "|" + isOther + "|" + isSandwich + "|" + isBagel);
		this.finish();
	}

	public void clickCheckinView(View view) {

		switch (view.getId()) {

		case R.id.imageButtonLatte:
			isLatte = !isLatte;
			this.changeSelection(isLatte, view);
			break;
		case R.id.imageButtonEspresso:
			isEspesso = !isEspesso;
			this.changeSelection(isEspesso, view);
			break;
		case R.id.imageButtonToC:
			isOther = !isOther;
			this.changeSelection(isOther, view);
			break;
		case R.id.imageButtonSandwich:
			isSandwich = !isSandwich;
			this.changeSelection(isSandwich, view);
			break;
		case R.id.imageButtonBagel:
			isBagel = !isBagel;
			this.changeSelection(isBagel, view);
			break;
		}
	}

	public void changeSelection(boolean value, View view) {
		if (value) {
			view.setBackgroundColor(getResources()
					.getColor(R.color.pomegranate));
		} else {
			view.setBackgroundColor(getResources().getColor(R.color.clouds));
		}
	}
}

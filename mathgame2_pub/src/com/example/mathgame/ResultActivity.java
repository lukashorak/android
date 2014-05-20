package com.example.mathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ResultActivity extends Activity {

	private static final String TAG = ResultActivity.class.getSimpleName();
	public ImageView imageViewResult;
	public Button buttonContinue;
	boolean isCorrect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent i = getIntent();
		this.isCorrect = i.getBooleanExtra("ResultCorrect", true);
		setContentView(R.layout.result_page);

		Log.i(TAG, "Show result:" + isCorrect);

		this.imageViewResult = (ImageView) findViewById(R.id.imageViewResult);
		this.buttonContinue = (Button) findViewById(R.id.buttonContinue);
		this.activateResultView();
	}

	public void activateResultView() {
		if (this.isCorrect) {
			this.imageViewResult.setImageResource(R.drawable.congratulations);
			this.buttonContinue.setText(R.string.result_button_congratulations);
		} else {
			this.imageViewResult.setImageResource(R.drawable.sorry);
			this.buttonContinue.setText(R.string.result_button_sorry);
		}
	}

	public void onClickResultContinue(View view) {
		Intent targetIntent = new Intent(getBaseContext(), QuestionActivity.class);
		startActivity(targetIntent);
	}

}

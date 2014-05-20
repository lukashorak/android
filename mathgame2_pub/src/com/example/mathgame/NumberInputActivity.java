package com.example.mathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mathgame.helpers.MathExample;
import com.example.mathgame.helpers.NumberSpeakerMediaPlayer;

public class NumberInputActivity extends Activity {
	private static final String TAG = NumberInputActivity.class.getSimpleName();
	Integer currentCountValue = 0;
	public Integer numberUserInput = null;
	NumberSpeakerMediaPlayer numberSpeakerMediaPlayer;
	MathExample e;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.number_screen2);
		Intent i = getIntent();
		this.currentCountValue = i.getIntExtra("CountInput", 0);

		this.activateNumberView();
		numberSpeakerMediaPlayer = new NumberSpeakerMediaPlayer(this);
	}

	private void activateNumberView() {

		MathExample e = MathgameApplication.getInstance().e;

		this.numberUserInput = null;

		TextView numberDisplay = (TextView) findViewById(R.id.editTextDisplay);
		if (numberDisplay != null) {
			numberDisplay.setText("");
		}

		TextView mathExample = (TextView) findViewById(R.id.textViewCountMathExample);
		if (mathExample != null) {
			mathExample.setText(e.toString());
		}

	}

	public void onClickNumberContinue(View view) {

		if (this.numberUserInput == null) {
			return;
		}

		MathgameApplication app = MathgameApplication.getInstance();
		this.e = app.e;

		Intent showResultIntent = new Intent(getBaseContext(), ResultActivity.class);
		// TODO - check if it's all correct

		Log.i(TAG, this.e.toString() + " | " + this.currentCountValue + " | " + this.numberUserInput);

		if (this.e.resultCorrect.equals(this.currentCountValue) && this.e.resultCorrect.equals(this.numberUserInput)) {
			// Correct
			showResultIntent.putExtra("ResultCorrect", true);
		} else {
			// Wrong
			showResultIntent.putExtra("ResultCorrect", false);
		}
		startActivity(showResultIntent);
	}

	public void onClickNumber(View view) {

		switch (view.getId()) {
		case R.id.numberOK:

			break;
		case R.id.numberClear:
			this.numberUserInput = null;
			break;

		case R.id.number1:
			this.updateNumber(1);
			break;
		case R.id.number2:
			this.updateNumber(2);
			break;
		case R.id.number3:
			this.updateNumber(3);
			break;
		case R.id.number4:
			this.updateNumber(4);
			break;
		case R.id.number5:
			this.updateNumber(5);
			break;
		case R.id.number6:
			this.updateNumber(6);
			break;
		case R.id.number7:
			this.updateNumber(7);
			break;
		case R.id.number8:
			this.updateNumber(8);
			break;
		case R.id.number9:
			this.updateNumber(9);
			break;
		case R.id.number0:
			this.updateNumber(0);
			break;
		}

		TextView numberDisplay = (TextView) findViewById(R.id.editTextDisplay);
		if (numberDisplay != null) {
			if (this.numberUserInput == null) {
				numberDisplay.setText("");
			} else {
				numberDisplay.setText("" + this.numberUserInput);
			}
		}
	}

	private void updateNumber(Integer number) {

		if (this.numberUserInput == null || this.numberUserInput == 0) {
			this.numberUserInput = number;
		} else {
			if (this.numberUserInput < 10) {
				this.numberUserInput = 10 * this.numberUserInput + number;
			}
		}
	}

	public void onClickExample(View view) {
		this.numberSpeakerMediaPlayer.play(this.e);
	}
}

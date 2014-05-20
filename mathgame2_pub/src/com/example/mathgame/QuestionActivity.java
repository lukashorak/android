package com.example.mathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mathgame.helpers.MathExample;

public class QuestionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_screen);

		this.activateQuestionView();
	}

	public void activateQuestionView() {

		MathExample e = ((MathgameApplication) getApplication()).e;
		e.init();

		TextView mathExample = (TextView) findViewById(R.id.textViewCountMathExample);
		if (mathExample != null) {
			mathExample.setText(e.toString());
		}
	}

	public void onClickQuestion(View view) {
		// TODO - continue to icon selection

		Intent iconIntent = new Intent(getBaseContext(), IconSelectionActivity.class);
		startActivity(iconIntent);
		// this.activateIconSelectView();
	}

}

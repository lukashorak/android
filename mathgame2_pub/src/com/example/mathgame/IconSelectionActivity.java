package com.example.mathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mathgame.helpers.IconHelper;
import com.example.mathgame.helpers.IconHelper.Icon;

public class IconSelectionActivity extends Activity {

	IconHelper iconHelper = new IconHelper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.icon_selection2);
	}

	/**
	 * In menu click on a icon with theme
	 * 
	 * @param view
	 */
	public void onClickIconSelection(View view) {

		switch (view.getId()) {

		case R.id.imageButtonAquarium:
			this.iconHelper.init(Icon.AQUARIUM);
			break;
		case R.id.imageButtonBaloons:
			this.iconHelper.init(Icon.BALOONS);
			break;
		case R.id.imageButtonCupcakes:
			this.iconHelper.init(Icon.CUPCAKES);
			break;
		case R.id.imageButtonFlowers:
			this.iconHelper.init(Icon.FLOWERS);
			break;
		}

		Intent countIntent = new Intent(getBaseContext(), CountActivity.class);
		countIntent.putExtra("ImageType", view.getId());
		startActivity(countIntent);
	}
}

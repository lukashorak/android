package com.example.mathgame;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mathgame.helpers.IconHelper;
import com.example.mathgame.helpers.IconHelper.Icon;
import com.example.mathgame.helpers.MathExample;
import com.example.mathgame.helpers.NumberSpeaker;
import com.example.mathgame.helpers.NumberSpeakerMediaPlayer;

public class CountActivity extends Activity {

	private static final String TAG = CountActivity.class.getSimpleName();
	Integer imageType = 0;

	public Boolean isPlusCount = false;
	public Boolean isMinusCount = false;

	public Integer numberUserInput = null;
	Integer currentCountValue = 0;

	public static final Integer minCountValue = 0;
	public static final Integer maxCountValue = 18;

	IconHelper iconHelper = new IconHelper();
	NumberSpeaker numberSpeaker;
	NumberSpeakerMediaPlayer numberSpeakerMediaPlayer;
	MathExample e;

	List<Bitmap> preparedImages = new ArrayList<Bitmap>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count_screen5);
		// this.prepareBitmaps();

		Intent i = getIntent();
		this.imageType = i.getIntExtra("ImageType", 0);
		Log.i(TAG, "Start with ImageType: " + this.imageType);

		this.activateCountView(imageType);

		// Set the hardware buttons to control the music
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		numberSpeaker = new NumberSpeaker(this);
		numberSpeakerMediaPlayer = new NumberSpeakerMediaPlayer(this);

	}

	private void activateCountView(Integer imageType) {
		switch (imageType) {

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

		this.e = MathgameApplication.getInstance().e;

		TextView mathExample = (TextView) findViewById(R.id.textViewCountMathExample);
		if (mathExample != null) {
			mathExample.setText(this.e.toString());
		}

		ImageButton mainImage = (ImageButton) findViewById(R.id.imageButtonIcon);

		if (mainImage != null) {
			mainImage.setImageResource(iconHelper.emptyImage);
		}
		this.currentCountValue = 0;
		changeIconItemsCount(this.currentCountValue);
	}

	public void onClickCountChange(View view) {

		Button pressedButton = (Button) view;
		Button buttonPlus = (Button) findViewById(R.id.buttonCountPlus);
		Button buttonMinus = (Button) findViewById(R.id.buttonCountMinus);
		if (pressedButton != null) {
			if (buttonPlus != null && pressedButton.equals(buttonPlus)) {
				// this.currentCountValue = Math.min(maxCountValue,
				// this.currentCountValue + 1);

				if (!this.isPlusCount) {
					this.isPlusCount = true;
					this.isMinusCount = false;
					buttonPlus.setBackgroundResource(R.drawable.custom_btn_orange);
					buttonMinus.setBackgroundResource(R.drawable.custom_btn_grey);
				} else {
					this.isPlusCount = false;
					// view.setBackgroundResource(R.color.gray);
					view.setBackgroundResource(R.drawable.custom_btn_grey);
				}
			}
			if (buttonMinus != null && pressedButton.equals(buttonMinus)) {
				// this.currentCountValue = Math.max(minCountValue,
				// this.currentCountValue - 1);

				if (!this.isMinusCount) {
					this.isMinusCount = true;
					this.isPlusCount = false;
					buttonMinus.setBackgroundResource(R.drawable.custom_btn_orange);
					buttonPlus.setBackgroundResource(R.drawable.custom_btn_grey);
				} else {
					this.isMinusCount = false;
					// view.setBackgroundResource(R.color.gray);
					view.setBackgroundResource(R.drawable.custom_btn_grey);
				}
			}
		}
	}

	// This method is called at button click because we assigned the name to the
	// "OnClick property" of the button
	public void onClickCount(View view) {

		if (this.isMinusCount) {
			this.currentCountValue = Math.max(minCountValue, this.currentCountValue - 1);
		} else {
			this.currentCountValue = Math.min(maxCountValue, this.currentCountValue + 1);
		}
		this.numberSpeaker.playNumberSound(this.currentCountValue);
		changeIconItemsCount(this.currentCountValue);
	}

	// FIXME - faster
	public void prepareBitmap() {
		this.preparedImages.clear();

		// First prepare background
		Bitmap mBitmapBackground = BitmapFactory.decodeResource(getResources(), iconHelper.emptyImage);
		this.preparedImages.add(mBitmapBackground);
		for (int i = 1; i <= MathExample.maxCountValue; i++) {
			Bitmap b = generateBitmap(i);
			this.preparedImages.add(b);
		}
	}

	// FIXME - faster
	public Bitmap generateBitmap(Integer number) {

		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), iconHelper.emptyImage);
		Bitmap mBitmapOverlay = null;

		Bitmap bmOverlay = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap.getConfig());
		Canvas canvas = new Canvas();
		canvas.setBitmap(bmOverlay);
		canvas.drawBitmap(mBitmap, new Matrix(), null);

		for (int i = number; i > 0; i--) {
			Integer img = iconHelper.currentImages.get(i);
			if (img == null) {
				continue;
			}
			mBitmapOverlay = BitmapFactory.decodeResource(getResources(), img);
			canvas.drawBitmap(mBitmapOverlay, new Matrix(), null);
		}

		return (bmOverlay);

	}

	public void changeIconItemsCountFaster(Integer number) {

		ImageButton ib = (ImageButton) findViewById(R.id.imageButtonIcon);
		ib.setImageBitmap(this.preparedImages.get(number));
	}

	/**
	 * Change image in count view according to the desired number of items
	 * 
	 * @param number
	 */
	public void changeIconItemsCount(Integer number) {

		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), iconHelper.emptyImage);
		Bitmap mBitmapOverlay = null;

		Bitmap bmOverlay = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap.getConfig());
		Canvas canvas = new Canvas();
		canvas.setBitmap(bmOverlay);
		canvas.drawBitmap(mBitmap, new Matrix(), null);

		for (int i = number; i > 0; i--) {
			Integer img = iconHelper.currentImages.get(i);
			if (img == null) {
				continue;
			}
			mBitmapOverlay = BitmapFactory.decodeResource(getResources(), img);
			canvas.drawBitmap(mBitmapOverlay, new Matrix(), null);
		}

		ImageButton ib = (ImageButton) findViewById(R.id.imageButtonIcon);
		ib.setImageBitmap(bmOverlay);

	}

	public void onClickCountExit(View view) {
		// this.activateNumberView();
		Intent openNumberInputIntent = new Intent(getBaseContext(), NumberInputActivity.class);
		openNumberInputIntent.putExtra("CountInput", this.currentCountValue);
		startActivity(openNumberInputIntent);
	}

	public void onClickExample(View view) {
		this.numberSpeakerMediaPlayer.play(this.e);
	}
}

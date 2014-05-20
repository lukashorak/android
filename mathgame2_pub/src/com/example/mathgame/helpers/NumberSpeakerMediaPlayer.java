package com.example.mathgame.helpers;

import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

import com.example.mathgame.R;
import com.example.mathgame.helpers.MathExample.Operator;

public class NumberSpeakerMediaPlayer {

	public NumberSpeakerMediaPlayer(Activity parent) {
		this.parent = parent;

		numberRawId.put(0, R.raw.s_0_zero);
		numberRawId.put(1, R.raw.s_1_one);
		numberRawId.put(2, R.raw.s_2_two);
		numberRawId.put(3, R.raw.s_3_three);
		numberRawId.put(4, R.raw.s_4_four);
		numberRawId.put(5, R.raw.s_5_five);
		numberRawId.put(6, R.raw.s_6_six);
		numberRawId.put(7, R.raw.s_7_seven);
		numberRawId.put(8, R.raw.s_8_eight);
		numberRawId.put(9, R.raw.s_9_nine);
		numberRawId.put(10, R.raw.s_10_ten);
		numberRawId.put(11, R.raw.s_11_eleven);
		numberRawId.put(12, R.raw.s_12_twelve);
		numberRawId.put(13, R.raw.s_13_thirteen);
		numberRawId.put(14, R.raw.s_14_fourteen);
		numberRawId.put(15, R.raw.s_15_fifteen);
		numberRawId.put(16, R.raw.s_16_sixteen);
		numberRawId.put(17, R.raw.s_17_seventeen);
		numberRawId.put(18, R.raw.s_18_eighteen);
		numberRawId.put(19, R.raw.s_19_nineteen);
		numberRawId.put(20, R.raw.s_20_twenty);
	}
	
	public final String LOG_TAG = "NumberSpeakerMediaPlayer";

	Activity parent;
	MediaPlayer mediaPlayer;

	Integer[] myMusic = new Integer[3];
	int mCompleted = 0;

	HashMap<Integer, Integer> numberRawId = new HashMap<Integer, Integer>();

	public void play(MathExample e) {

		myMusic[0] = numberRawId.get(e.num1);
		if (e.operation == Operator.PLUS) {
			myMusic[1] = R.raw.s_plus;
		} else {
			myMusic[1] = R.raw.s_minus;
		}
		myMusic[2] = numberRawId.get(e.num2);

		mCompleted = 0;
		MediaPlayer mp = MediaPlayer.create(parent, myMusic[0]);
		
		Log.i(LOG_TAG, Arrays.toString(myMusic));

		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mCompleted++;
				mp.reset();
				if (mCompleted < myMusic.length) {
					try {
						AssetFileDescriptor afd = parent.getResources()
								.openRawResourceFd(myMusic[mCompleted]);
						if (afd != null) {
							mp.setDataSource(afd.getFileDescriptor(),
									afd.getStartOffset(), afd.getLength());
							afd.close();
							mp.prepare();
							mp.start();
						}
					} catch (Exception ex) {
						// report a crash
					}
				} else {
					// done with media player
					mp.release();
					mp = null;
				}
			}
		});

		mp.start();
	}
}

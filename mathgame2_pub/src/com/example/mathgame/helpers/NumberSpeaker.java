package com.example.mathgame.helpers;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;

import com.example.mathgame.R;

public class NumberSpeaker {

	public NumberSpeaker(Activity parent) {

		this.parent = parent;
		// Load the sound
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});

		soundIds.put(0, soundPool.load(parent, R.raw.s_0_zero, 1));
		soundIds.put(1, soundPool.load(parent, R.raw.s_1_one, 1));
		soundIds.put(2, soundPool.load(parent, R.raw.s_2_two, 1));
		soundIds.put(3, soundPool.load(parent, R.raw.s_3_three, 1));
		soundIds.put(4, soundPool.load(parent, R.raw.s_4_four, 1));
		soundIds.put(5, soundPool.load(parent, R.raw.s_5_five, 1));
		soundIds.put(6, soundPool.load(parent, R.raw.s_6_six, 1));
		soundIds.put(7, soundPool.load(parent, R.raw.s_7_seven, 1));
		soundIds.put(8, soundPool.load(parent, R.raw.s_8_eight, 1));
		soundIds.put(9, soundPool.load(parent, R.raw.s_9_nine, 1));
		soundIds.put(10, soundPool.load(parent, R.raw.s_10_ten, 1));
		soundIds.put(11, soundPool.load(parent, R.raw.s_11_eleven, 1));
		soundIds.put(12, soundPool.load(parent, R.raw.s_12_twelve, 1));
		soundIds.put(13, soundPool.load(parent, R.raw.s_13_thirteen, 1));
		soundIds.put(14, soundPool.load(parent, R.raw.s_14_fourteen, 1));
		soundIds.put(15, soundPool.load(parent, R.raw.s_15_fifteen, 1));
		soundIds.put(16, soundPool.load(parent, R.raw.s_16_sixteen, 1));
		soundIds.put(17, soundPool.load(parent, R.raw.s_17_seventeen, 1));
		soundIds.put(18, soundPool.load(parent, R.raw.s_18_eighteen, 1));
		soundIds.put(19, soundPool.load(parent, R.raw.s_19_nineteen, 1));
		soundIds.put(20, soundPool.load(parent, R.raw.s_20_twenty, 1));

	}

	private Activity parent;

	private SoundPool soundPool;
	private HashMap<Integer, Integer> soundIds = new HashMap<Integer, Integer>();
	boolean loaded = false;

	/**
	 * Play sound according to the desired number (0-10)
	 * 
	 * @param number
	 */
	public void playNumberSound(Integer number) {
		// Getting the user sound settings
		AudioManager audioManager = (AudioManager) parent
				.getSystemService(Context.AUDIO_SERVICE);
		float actualVolume = (float) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = (float) audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;
		// Is the sound loaded already?

		Integer soundId = soundIds.get(number);
		if (loaded && soundId != null) {
			soundPool.play(soundId, volume, volume, 1, 0, 1f);
			Log.e("TTS", "Played sound :" + number);
		}

	}
}

package com.example.mathgame;

import android.app.Application;
import android.content.res.Configuration;

import com.example.mathgame.helpers.MathExample;

public class MathgameApplication extends Application {

	public static final String SHARED_PREFERENCES = "com.example.mathgame";

	private static MathgameApplication singleton;

	public MathExample e = new MathExample();

	public static MathgameApplication getInstance() {
		return singleton;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;

	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

}

package com.example.mathgame.helpers;

import java.util.HashMap;

import com.example.mathgame.R;

public class IconHelper {

	public IconHelper() {

		this.initAquarium();
	}

	public HashMap<Integer, Integer> currentImages;

	public Integer emptyImage;

	public enum Icon {
		AQUARIUM, FLOWERS, BALOONS, CUPCAKES;
	}

	public void init(Icon icon) {
		switch (icon) {
		case AQUARIUM:
			this.initAquarium();
			break;
		case FLOWERS:
			this.initFlowers();
			break;
		case BALOONS:
			this.initBaloons();
			break;
		case CUPCAKES:
			this.initCupcakes();
			break;
		}
	}

	public void initAquarium() {
		emptyImage = R.drawable.empty_fishbowl_m;
		currentImages = new HashMap<Integer, Integer>();

		currentImages.put(1, R.drawable.fish1_m);
		currentImages.put(2, R.drawable.fish2_m);
		currentImages.put(3, R.drawable.fish3_m);
		currentImages.put(4, R.drawable.fish4_m);
		currentImages.put(5, R.drawable.fish5_m);
		currentImages.put(6, R.drawable.fish6_m);
		currentImages.put(7, R.drawable.fish7_m);
		currentImages.put(8, R.drawable.fish8_m);
		currentImages.put(9, R.drawable.fish9_m);
		currentImages.put(10, R.drawable.fish10_m);
		currentImages.put(11, R.drawable.fish11_m);
		currentImages.put(12, R.drawable.fish12_m);
		currentImages.put(13, R.drawable.fish13_m);
		currentImages.put(14, R.drawable.fish14_m);
		currentImages.put(15, R.drawable.fish15_m);
		currentImages.put(16, R.drawable.fish16_m);
		currentImages.put(17, R.drawable.fish17_m);
		currentImages.put(18, R.drawable.fish18_m);
	}

	public void initFlowers() {
		emptyImage = R.drawable.empty_flowerpot_m;
		currentImages = new HashMap<Integer, Integer>();

		currentImages.put(1, R.drawable.tulip1_m);
		currentImages.put(2, R.drawable.tulip2_m);
		currentImages.put(3, R.drawable.tulip3_m);
		currentImages.put(4, R.drawable.tulip4_m);
		currentImages.put(5, R.drawable.tulip5_m);
		currentImages.put(6, R.drawable.tulip6_m);
		currentImages.put(7, R.drawable.tulip7_m);
		currentImages.put(8, R.drawable.tulip8_m);
		currentImages.put(9, R.drawable.tulip9_m);
		currentImages.put(10, R.drawable.tulip10_m);
		currentImages.put(11, R.drawable.tulip11_m);
		currentImages.put(12, R.drawable.tulip12_m);
		currentImages.put(13, R.drawable.tulip13_m);
		currentImages.put(14, R.drawable.tulip14_m);
		currentImages.put(15, R.drawable.tulip15_m);
		currentImages.put(16, R.drawable.tulip16_m);
		currentImages.put(17, R.drawable.tulip17_m);
		currentImages.put(18, R.drawable.tulip18_m);
	}

	public void initBaloons() {
		emptyImage = R.drawable.empty_balloon_m;
		currentImages = new HashMap<Integer, Integer>();

		currentImages.put(1, R.drawable.balloon1_m);
		currentImages.put(2, R.drawable.balloon2_m);
		currentImages.put(3, R.drawable.balloon3_m);
		currentImages.put(4, R.drawable.balloon4_m);
		currentImages.put(5, R.drawable.balloon5_m);
		currentImages.put(6, R.drawable.balloon6_m);
		currentImages.put(7, R.drawable.balloon7_m);
		currentImages.put(8, R.drawable.balloon8_m);
		currentImages.put(9, R.drawable.balloon9_m);
		currentImages.put(10, R.drawable.balloon10_m);
		currentImages.put(11, R.drawable.balloon11_m);
		currentImages.put(12, R.drawable.balloon12_m);
		currentImages.put(13, R.drawable.balloon13_m);
		currentImages.put(14, R.drawable.balloon14_m);
		currentImages.put(15, R.drawable.balloon15_m);
		currentImages.put(16, R.drawable.balloon16_m);
		currentImages.put(17, R.drawable.balloon17_m);
		currentImages.put(18, R.drawable.balloon18_m);

	}

	public void initCupcakes() {
		emptyImage = R.drawable.empty_cupcake_m;
		currentImages = new HashMap<Integer, Integer>();

		currentImages.put(1, R.drawable.cupcake1_m);
		currentImages.put(2, R.drawable.cupcake2_m);
		currentImages.put(3, R.drawable.cupcake3_m);
		currentImages.put(4, R.drawable.cupcake4_m);
		currentImages.put(5, R.drawable.cupcake5_m);
		currentImages.put(6, R.drawable.cupcake6_m);
		currentImages.put(7, R.drawable.cupcake7_m);
		currentImages.put(8, R.drawable.cupcake8_m);
		currentImages.put(9, R.drawable.cupcake9_m);
		currentImages.put(10, R.drawable.cupcake10_m);
		currentImages.put(11, R.drawable.cupcake11_m);
		currentImages.put(12, R.drawable.cupcake12_m);
		currentImages.put(13, R.drawable.cupcake13_m);
		currentImages.put(14, R.drawable.cupcake14_m);
		currentImages.put(15, R.drawable.cupcake15_m);
		currentImages.put(16, R.drawable.cupcake16_m);
		currentImages.put(17, R.drawable.cupcake17_m);
		currentImages.put(18, R.drawable.cupcake18_m);
	}

}

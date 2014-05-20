package com.luki.bobolife;

import java.util.Arrays;
import java.util.List;

import com.luki.bobolife.db.AnswerDataDao;
import com.luki.bobolife.model.AnswerDataLogItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class StatisticsActivity extends Activity {
	private static final String TAG = StatisticsActivity.class.getSimpleName();
	/** Called when the activity is first created. */
	float dataToC[] = { 5, 7 };
	float dataWork[] = { 5, 7, 9 };
	float valuesToC[] = { 5, 7 };
	float valuesWork[] = { 5, 7, 9 };
	private AnswerDataDao dao;
	LinearLayout linearToc;
	LinearLayout linearWork;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		dao = new AnswerDataDao(getBaseContext());
		linearToc = (LinearLayout) findViewById(R.id.linearToC);
		linearWork = (LinearLayout) findViewById(R.id.linearWork);
		this.loadData();
		this.valuesToC = calculateData(dataToC);
		this.valuesWork = calculateData(dataWork);
		Log.i(TAG, "ToC" + Arrays.toString(this.valuesToC));
		Log.i(TAG, "Work" + Arrays.toString(this.valuesWork));
		linearToc.addView(new MyGraphview(this, this.valuesToC, new int[] {
				getResources().getColor(R.color.green_sea),
				getResources().getColor(R.color.carrot) }));
		linearWork.addView(new MyGraphview(this, this.valuesWork, new int[] {
				getResources().getColor(R.color.concrete),
				getResources().getColor(R.color.belize_hole),
				getResources().getColor(R.color.red) }));

	}

	private void loadData() {

		dataToC = new float[2];
		dataWork = new float[3];
		List<AnswerDataLogItem> list = dao.getAll();
		for (AnswerDataLogItem i : list) {
			if (TeaOrCoffeeActivity.TEA.equals(i.getAnswerTeaOrCoffeeValue())) {
				dataToC[0]++;
			} else {
				dataToC[1]++;
			}
			if (WorkReviewActivity.GOOD.equals(i.getAnswerWork1Value())) {
				dataWork[0]++;
			}
			if (WorkReviewActivity.OK.equals(i.getAnswerWork1Value())) {
				dataWork[1]++;
			}
			if (WorkReviewActivity.BAD.equals(i.getAnswerWork1Value())) {
				dataWork[2]++;
			}
		}
	}

	private float[] calculateData(float[] data) {
		// TODO Auto-generated method stub
		float total = 0;
		for (int i = 0; i < data.length; i++) {
			total += data[i];
		}
		for (int i = 0; i < data.length; i++) {
			data[i] = 360 * (data[i] / total);
		}
		return data;

	}

	public class MyGraphview extends View {
		private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		private float[] value_degree;
		private int[] COLORS = { getResources().getColor(R.color.green_sea),
				getResources().getColor(R.color.carrot),
				getResources().getColor(R.color.concrete),
				getResources().getColor(R.color.belize_hole),
				getResources().getColor(R.color.red) };
		RectF rectf = new RectF(10, 10, 200, 200);
		int temp = 0;

		public MyGraphview(Context context, float[] values) {
			super(context);
			value_degree = new float[values.length];
			for (int i = 0; i < values.length; i++) {
				value_degree[i] = values[i];
			}
		}

		public MyGraphview(Context context, float[] values, int[] colors) {
			super(context);
			value_degree = new float[values.length];
			for (int i = 0; i < values.length; i++) {
				value_degree[i] = values[i];
			}
			this.COLORS = colors;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);

			for (int i = 0; i < value_degree.length; i++) {// values2.length;
															// i++) {
				if (i == 0) {
					paint.setColor(COLORS[i]);
					canvas.drawArc(rectf, 0, value_degree[i], true, paint);
				} else {
					temp += (int) value_degree[i - 1];
					paint.setColor(COLORS[i]);
					canvas.drawArc(rectf, temp, value_degree[i], true, paint);
				}
			}
		}

	}
}
package com.luki.bobolife.adapters;

import java.text.DateFormat;
import java.text.Format;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luki.bobolife.R;
import com.luki.bobolife.model.AnswerDataLogItem;

public class DataLogListAdapter extends ArrayAdapter<AnswerDataLogItem> {

	private final Context context;
	private List<AnswerDataLogItem> values;

	public DataLogListAdapter(Context context, List<AnswerDataLogItem> objects) {
		super(context, R.layout.list_adapter_datalog, objects);
		this.context = context;
		// this.values = new ArrayList<CalendarEvent>();
		// this.values.addAll(objects);
		this.values = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_adapter_datalog, parent, false);
		TextView textViewName = (TextView) rowView.findViewById(R.id.toc_name);
		TextView textViewSecondLine = (TextView) rowView.findViewById(R.id.toc_second_line);
		ImageView imageViewTocIcon = (ImageView) rowView.findViewById(R.id.toc_icon);
		ImageView imageViewWorkIcon = (ImageView) rowView.findViewById(R.id.work_icon);

		if (position < values.size()) {
			AnswerDataLogItem logItem = values.get(position);
			Format tf = DateFormat.getTimeInstance();

			String answerTeaOrCoffeeText = "";
			Date answerTeaOrCoffeeDate;
			String answerTeaOrCoffeeDateText = "";
			String answerWork1Text = "";
			Date answerWork1Date;
			String answerWork1DateText = "";
			if (logItem.getAnswerTeaOrCoffeeDate() != null) {
				answerTeaOrCoffeeText = logItem.getAnswerTeaOrCoffeeValue();
				answerTeaOrCoffeeDate = new Date(logItem.getAnswerTeaOrCoffeeDate());
				answerTeaOrCoffeeDateText = tf.format(answerTeaOrCoffeeDate);
			}
			if (logItem.getAnswerWork1Date() != null) {

				answerWork1Text = logItem.getAnswerWork1Value();
				answerWork1Date = new Date(logItem.getAnswerWork1Date());
				answerWork1DateText = tf.format(answerWork1Date);
			}

			textViewName.setText(answerTeaOrCoffeeText + " | " + answerWork1Text);

			textViewSecondLine.setText(logItem.getId() + ">" + logItem.getDay() + " | ToC:" + answerTeaOrCoffeeDateText
					+ " | Work1:" + answerWork1DateText);

			if ("Tea".equals(logItem.getAnswerTeaOrCoffeeValue())) {
				imageViewTocIcon.setImageResource(R.drawable.tea);
			} else if ("Coffee".equals(logItem.getAnswerTeaOrCoffeeValue())) {
				imageViewTocIcon.setImageResource(R.drawable.coffee);
			}
			if ("Good".equals(logItem.getAnswerWork1Value())) {
				imageViewWorkIcon.setImageResource(R.drawable.creativity);
			} else if ("Ok".equals(logItem.getAnswerWork1Value())) {
				imageViewWorkIcon.setImageResource(R.drawable.work_hard);
			} else if ("Bad".equals(logItem.getAnswerWork1Value())) {
				imageViewWorkIcon.setImageResource(R.drawable.procrastination);
			}

		}
		return rowView;

	}
}

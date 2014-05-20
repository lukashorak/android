package com.luki.bobolife.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;

import com.luki.bobolife.model.AnswerDataLogItem;

public class AnswerDataDao {

	private AnswerDataSource dataSource;

	public AnswerDataDao(Context context) {
		this.dataSource = new AnswerDataSource(context);
		this.dataSource.open();
	}

	public void open() {
		this.dataSource.open();
	}

	public void close() {
		this.dataSource.close();
	}

	public AnswerDataLogItem getToday() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
		String dateKey = sdf.format(now);

		AnswerDataLogItem today = this.dataSource.getItemByDay(dateKey);
		if (today == null) {
			today = this.dataSource.createAnswerItem(dateKey, null, null, null,
					null);
		}

		return today;
	}

	public AnswerDataLogItem updateToday(Long answerTeaOrCoffeeDate,
			String answerTeaOrCoffeeValue, Long answerWork1Date,
			String answerWork1Value) {
		AnswerDataLogItem today = this.getToday();

		if (answerTeaOrCoffeeDate != null && answerTeaOrCoffeeValue != null) {
			today.setAnswerTeaOrCoffeeDate(answerTeaOrCoffeeDate);
			today.setAnswerTeaOrCoffeeValue(answerTeaOrCoffeeValue);
		}
		if (answerWork1Date != null && answerWork1Value != null) {
			today.setAnswerWork1Date(answerWork1Date);
			today.setAnswerWork1Value(answerWork1Value);
		}

		// PERSIST Object
		this.dataSource.updateAnswerItem(today);
		return today;
	}

	public List<AnswerDataLogItem> getAll() {
		return this.dataSource.getAllAnswerItems();
	}

}

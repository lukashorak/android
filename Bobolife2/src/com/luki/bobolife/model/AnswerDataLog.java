package com.luki.bobolife.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class AnswerDataLog implements Serializable {

	private static final long serialVersionUID = 1L;

	public AnswerDataLog() {

	}

	// public ArrayList<AnswerDataLogItem> log = new
	// ArrayList<AnswerDataLogItem>();
	public LinkedHashMap<String, AnswerDataLogItem> log = new LinkedHashMap<String, AnswerDataLogItem>();

}

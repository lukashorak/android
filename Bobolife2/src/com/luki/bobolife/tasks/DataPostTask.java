package com.luki.bobolife.tasks;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.luki.bobolife.model.AnswerDataLogItem;

public class DataPostTask extends AsyncTask<AnswerDataLogItem, Void, String> {

	private static final String TAG = DataPostTask.class.getSimpleName();

	public DataPostTask(Context context) {
		this.context = context;
	}

	Context context;

	@Override
	protected String doInBackground(AnswerDataLogItem... vars) {
		Log.i(TAG, "Start");
		if (!HttpTaskUtils.hasConnection(this.context)) {
			Log.i(TAG, "No network available");
			// return null;
		}

		if (vars.length < 1) {
			Log.i(TAG, "No data to send");
			return null;
		}
		String data = null;
		try {
			String url = "http://bobolife-gae.appspot.com/bobolifeweb";
			HttpClient httpClient = new DefaultHttpClient();
			// Prepare a request object
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			Gson gson = new Gson();
			String json = gson.toJson(vars[0]);
			String dataDay = vars[0].getDay();
			nameValuePairs.add(new BasicNameValuePair("update", json));
			nameValuePairs.add(new BasicNameValuePair("dataDay", dataDay));
			nameValuePairs.add(new BasicNameValuePair("userAccount", HttpTaskUtils.getUserEmail(context)));
			Log.i(TAG, json);

			UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(nameValuePairs);
			httpPost.setEntity(urlEntity);

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			Log.i(TAG, "" + statusCode);

			data = HttpTaskUtils.convertStreamToString(entity.getContent());
			Log.i(TAG, data);
			Log.i(TAG, "------------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
}

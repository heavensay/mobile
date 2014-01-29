package com.example.practice.remote;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.example.practice.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class HttpUtils {
	
	public static void postRequest(final Handler handler,final Activity activity,final String requestUrl, final List<NameValuePair> parmMap){
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				final String result = post2(requestUrl, parmMap);
				
				handler.post(new Runnable() {
					@Override
					public void run() {
						//NetworkOnMainThreadException
						//String result = post2(requestUrl, parmMap);
						new AlertDialog.Builder(activity)
								.setTitle("弹出框").setTitle(result)
								.setNegativeButton("确定", null).show();
					}
				});
			}
		});
		thread.start();
	}
	
	
	private static  void post(final String requestUrl, final List<NameValuePair> parmMap) {
		try {
			HttpPost httpRequest = new HttpPost(requestUrl);
			HttpEntity entity = new UrlEncodedFormEntity(parmMap);
			httpRequest.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			// 请求超时
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
			// 读取超时
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					15000);
			HttpResponse response = client.execute(httpRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				String str = EntityUtils.toString(response.getEntity(),
						"GB2312");
				Log.d(HttpUtils.class.getName(), "http response data:" + str);
			} else {
				Log.d(HttpUtils.class.getName(), "http request fail:"
						+ response.getStatusLine().getStatusCode());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	private static String post2(final String requestUrl, final List<NameValuePair> parmMap) {
		String result = null;
		try {
			HttpPost httpRequest = new HttpPost(requestUrl);
//			HttpEntity entity = new UrlEncodedFormEntity(parmMap);
//			httpRequest.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			// 请求超时
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
			// 读取超时
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					15000);
			HttpResponse response = client.execute(httpRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(),
						"GB2312");
				Log.d(HttpUtils.class.getName(), "http response data:" + result);
			} else {
				Log.d(HttpUtils.class.getName(), "http request fail:"
						+ response.getStatusLine().getStatusCode());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return result;
	}
}

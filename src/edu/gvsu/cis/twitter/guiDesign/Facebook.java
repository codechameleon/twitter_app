package edu.gvsu.cis.twitter.guiDesign;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class Facebook {

	// Url to prompt 
	public final String FBURL = "https://www.facebook.com/dialog/oauth?client_id=144697825708137&redirect_uri=https://www.facebook.com/connect/login_success.html&scope=publish_actions,offline_access&response_type=token";
	
	public Facebook() {}
	
	/***********************************************************
	 * Parse url for access token. 
	 * @param url
	 * @return
	 **********************************************************/
	public String getAccessToken(String url) {
		String token = url.substring(url.indexOf("=") + 1, url.indexOf("&"));
		return token;
	}
	
	
	/***********************************************************
	 * Execute Http Post to facebook to update status 
	 * @param message
	 * @param token
	 * @throws UnsupportedEncodingException
	 **********************************************************/
	public void sendToFacebook(String message, String token) throws UnsupportedEncodingException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("https://graph.facebook.com/me/feed");
	
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("access_token", token));
		params.add(new BasicNameValuePair("message",message));
	
		httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
		
		// Execute and get the response
		try {
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			      String line = "";
			      while ((line = rd.readLine()) != null) {
			        System.out.println(line);
			      }
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
}



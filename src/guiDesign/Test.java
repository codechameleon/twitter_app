package guiDesign;


import javax.swing.JOptionPane;

import TwitterLogic.TweetUtils;
import TwitterLogic.TwitterEngine;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class Test {
	public static void main(String args[]){
	 TweetUtils ut;
		try{
			TwitterEngine accounts = new TwitterEngine();
			Twitter twitter = new TwitterFactory().getInstance();
			if(!(accounts.loadFile()) || (accounts.getSize() ==0)){
				LoginDialog login = new LoginDialog();
				Boolean logState = true;
				try{
					twitter.setOAuthConsumer("2Z6f5DKh5pyQUMskyi9wg", "kL7THP2PQz7d50nnYsro3AOBTOo5pgdzoOrCqb3PU");
					AccessToken accessToken = login.getAccessToken();
					twitter.setOAuthAccessToken(accessToken);
					twitter.verifyCredentials().getId();
					accounts.addElement(twitter);
					accounts.saveFile();
					JOptionPane.showMessageDialog(null, "Login Successful!");
				} catch(TwitterException e){
					JOptionPane.showMessageDialog(null, "Unable to Login");
					logState = false;
				}
				if(logState){
					ut = new TweetUtils(twitter);
					new TwitterAppGUI(ut);
				}
			}
			else{
				twitter = accounts.getAccountAt(0);
				ut = new TweetUtils(twitter);
				new TwitterAppGUI(ut);
			}
		}catch(Exception e){
			//Nothing Happy!
			e.printStackTrace();
		}
	}


}

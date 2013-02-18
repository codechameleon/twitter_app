package edu.gvsu.cis.twitter.guiDesign;


import javax.swing.JOptionPane;

import edu.gvsu.cis.twitter.twitterLogic.TweetUtils;
import edu.gvsu.cis.twitter.twitterLogic.TwitterConstants;
import edu.gvsu.cis.twitter.twitterLogic.TwitterAccounts;


import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/********************************************************
 * Main class for testing the Twitter Application
 *
 *******************************************************/
public final class Test {
	
	/************************************************
	 * Default constructor 
	 ***********************************************/
	private Test() { };
	
	/************************************************
	 * Main method
	 ***********************************************/
	public static void main(final String[] args) {
		
	 // Tweet utilities
	 TweetUtils ut;
		try {
			TwitterAccounts accounts = new TwitterAccounts();
			Twitter twitter = new TwitterFactory().getInstance();
			if (!(accounts.loadFile()) || (accounts.getSize() == 0)) {
				LoginDialog login = new LoginDialog();
				Boolean logState = true;
				try {
					twitter.setOAuthConsumer(TwitterConstants.CONSUMER_KEY,
							TwitterConstants.CONSUMER_SECRET);
					AccessToken accessToken = login.getAccessToken();
					twitter.setOAuthAccessToken(accessToken);
					twitter.verifyCredentials().getId();
					accounts.addElement(twitter);
					accounts.saveFile();
					JOptionPane.showMessageDialog(null, "Login Successful!");
				} catch (TwitterException e) {
					JOptionPane.showMessageDialog(null, "Unable to Login");
					logState = false;
				}
				if (logState) {
					ut = new TweetUtils(twitter);
					new TwitterAppGUI(ut);
				}
			} else {
				twitter = accounts.getAccountAt(0);
				ut = new TweetUtils(twitter);
				new TwitterAppGUI(ut);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

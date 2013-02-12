import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
public class Test {
	public static void main(String args[]){
		try{
			TwitterEngine accounts = new TwitterEngine();
			Twitter twitter = new TwitterFactory().getInstance();
			if(!(accounts.loadFile()) || (accounts.getSize() ==0)){
				LoginDialog login = new LoginDialog();
				//Twitter twitter = new TwitterFactory().getInstance();
				Boolean logState = true;
				try{
					//Twitter twitter = new TwitterFactory().getInstance();
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
				if(logState)
					new TwitterAppGUI(twitter);
			}
			else{
				twitter = accounts.getAccountAt(0);
				new TwitterAppGUI(twitter);
			}
		}catch(Exception e){
			//Nothing Happy!
		}
	}


}

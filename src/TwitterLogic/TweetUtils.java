package TwitterLogic;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

/********************************************************************
 * Tweet methods that are used for Twitter. Extends Base class so 
 * it uses the Base created Twitter object with its' configuration
 * 
 * @author Michael Torres
 *
 *******************************************************************/
public class TweetUtils extends Base {
	
	/*********************************************************
	 * Constructor 
	 ********************************************************/
	public TweetUtils(){};
	
	/*********************************************************
	 * Sends a tweet
	 ********************************************************/
	public void sendTweet(String msg){

		try {
			twitter.updateStatus(msg);
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}
	
	/*********************************************************
	 * Delete a tweet
	 ********************************************************/
	public void deleteTweet(Status tweet) {
		try {
			twitter.destroyStatus(tweet.getId());
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}
	
	/*********************************************************
	 * Retweet a Tweet
	 ********************************************************/
	public void reTweet(Status tweet) {
		try {
			twitter.retweetStatus(tweet.getId());
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}
	
	/*********************************************************
	 * Show a Tweet Message
	 ********************************************************/
	public void showTweet(Status tweet) {
		try {
			twitter.showStatus(tweet.getId());
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}
	
	/*********************************************************
	 * Get reTweets
	 ********************************************************/
	public void getReTweets(Status tweet) {
		try {
			List<Status> statuses = twitter.getRetweets(tweet.getId());
			
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	

}

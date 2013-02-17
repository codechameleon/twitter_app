package TwitterLogic;


import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/********************************************************************
 * Tweet methods that are used for Twitter. Extends Base class so 
 * it uses the Base created Twitter object with its' configuration
 * 
 * @author Michael Torres
 *
 *******************************************************************/
public class TweetUtils{
	Twitter twitter;

	/*********************************************************
	 * Constructor 
	 ********************************************************/
	public TweetUtils(Twitter t){
		this.twitter =t;
	};

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
	
	/*********************************************************
	 * Add to favorite
	 ********************************************************/
	public void favorite(Status tweet){
		try {
			twitter.createFavorite(tweet.getId());
		} catch (TwitterException e1) {
			e1.printStackTrace();
		}
		
	}
	
	/*********************************************************
	 * Get The Users own TimeLine
	 * @return list of Statuses
	 ********************************************************/
	public List<Status> getUserTimeline(){
		List<Status> statusList;
		try {
			statusList = twitter.getUserTimeline();
		} catch (TwitterException e) {
			return null;
			//e.printStackTrace();
		}
		return statusList;
	}
	
	/*********************************************************
	 * Get The main TimeLine
	 * @return list of statuses
	 ********************************************************/
	public List<Status> getTimeLine(){
		List<Status> statusList;
		try {
			statusList = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			return null;
			//e.printStackTrace();
		}
		return statusList;
	}

	/*********************************************************
	 * Get The mentions TimeLine
	 * @return list of statuses
	 ********************************************************/
	public List<Status> getMentionsTimeLine(){
		List<Status> statusList;
		try {
			statusList = twitter.getMentionsTimeline();
		} catch (TwitterException e) {
			return null;
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return statusList;
	}
	
	/*********************************************************
	 * Get the Account holders screen name
	 * @return ScreenName
	 ********************************************************/
	public String getScreenName(){
		try {
			return twitter.getScreenName();
		} catch (TwitterException e) {
			return null;
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	/*********************************************************
	 * Get the uesers followers
	 * @return first 20 followers
	 ********************************************************/
	public List<User> getFollowers(){
		try {
			return twitter.getFollowersList(twitter.getScreenName(), -1);
		} catch (TwitterException e) {
			return null;
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	

}
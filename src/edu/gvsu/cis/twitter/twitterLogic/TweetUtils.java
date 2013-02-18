package edu.gvsu.cis.twitter.twitterLogic;


import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/********************************************************************
 * Tweet methods that are used for Twitter. Extends Base class so 
 * it uses the Base created Twitter object with its' configuration.
 *
 *******************************************************************/
public class TweetUtils {
	
	/** Twitter. */
	private Twitter twitter;

	/*********************************************************
	 * Constructor.
	 * @param t twitter
	 ********************************************************/
	public TweetUtils(final Twitter t) {
		this.twitter = t;
	};

	/*********************************************************
	 * Sends a tweet.
	 * @param msg string for tweet
	 ********************************************************/
	public final void sendTweet(final String msg) {

		try {
			twitter.updateStatus(msg);
		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}

	/*********************************************************
	 * Delete a tweet.
	 * @param tweet deleted one
	 ********************************************************/
	public final void deleteTweet(final Status tweet) {
		
		try {
			twitter.destroyStatus(tweet.getId());
		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}

	/*********************************************************
	 * Retweet a Tweet.
	 * @param tweet retweet status
	 ********************************************************/
	public final void reTweet(final Status tweet) {
		
		try {
			twitter.retweetStatus(tweet.getId());
		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}

	/*********************************************************
	 * Show a Tweet Message.
	 * @param tweet show status
	 ********************************************************/
	public final void showTweet(final Status tweet) {
		
		try {
			twitter.showStatus(tweet.getId());
		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}

	/*********************************************************
	 * Get reTweets.
	 * @param tweet retweet status
	 ********************************************************/
	public final void getReTweets(final Status tweet) {
		
		try {
			List<Status> statuses = twitter.getRetweets(tweet.getId());

		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}
	
	/*********************************************************
	 * Add to favorite.
	 * @param tweet favorite status
	 ********************************************************/
	public final void favorite(final Status tweet) {
		
		try {
			twitter.createFavorite(tweet.getId());
		} catch (TwitterException e1) {
			e1.printStackTrace();
		}
		
	}
	
	/*********************************************************
	 * Get The Users own TimeLine.
	 * @return list of Statuses
	 ********************************************************/
	public final List<Status> getUserTimeline() {
		List<Status> statusList;
		try {
			statusList = twitter.getUserTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
		return statusList;
	}
	
	/*********************************************************
	 * Get The main TimeLine.
	 * @return list of statuses
	 ********************************************************/
	public final List<Status> getTimeLine() {
		List<Status> statusList;
		try {
			statusList = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
		return statusList;
	}

	/*********************************************************
	 * Get The mentions TimeLine.
	 * @return list of statuses
	 ********************************************************/
	public final List<Status> getMentionsTimeLine() {
		List<Status> statusList;
		try {
			statusList = twitter.getMentionsTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
		return statusList;
	}
	
	/*********************************************************
	 * Get the Account holders screen name.
	 * @return ScreenName
	 ********************************************************/
	public final String getScreenName() {
		try {
			return twitter.getScreenName();
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*********************************************************
	 * Get the uesers followers.
	 * @return first 20 followers
	 ********************************************************/
	public final List<User> getFollowers() {
		try {
			return twitter.getFollowersList(twitter.getScreenName(), -1);
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
package edu.gvsu.cis.twitter.twitterLogic;


import java.io.File;
import java.util.List;

import twitter4j.AccountSettings;
import twitter4j.DirectMessage;
import twitter4j.Location;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
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
	
	public final List<DirectMessage> getMessages() {
		try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
            	
                messages = twitter.getDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
            System.out.println("done.");
            return messages;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get messages: " + te.getMessage());
            return null;
        }
	
	}
	
	public final void destroyMessage(long messageID) {
		try {
  
            twitter.destroyDirectMessage(messageID);
            System.out.println("Successfully deleted messag");
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to delete message: " + te.getMessage());
          
        }
	}
	
	public final List<DirectMessage> getSentMessages() {
		List<DirectMessage> directMessages;
		try {
            Paging page = new Paging(1);
            do {
                directMessages = twitter.getSentDirectMessages(page);
                for (DirectMessage message : directMessages) {
                    System.out.println("To: @" + message.getRecipientScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                }
                page.setPage(page.getPage() + 1);
            } while (directMessages.size() > 0 && page.getPage() < 10);
            System.out.println("done.");
            return getMessages();
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get sent messages: " + te.getMessage());
            return null;
        }
	}
	
	public final void sendMessage(String to, String text) {
		
		try {
            DirectMessage message = twitter.sendDirectMessage(to, text);
            System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
           
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to send a direct message: " + te.getMessage());
           
        }
	}
	
	public final void showMessage(long messageID) {
		try {
            DirectMessage message = twitter.showDirectMessage(messageID);
            System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                    + message.getText());
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get message: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	public final List<Status> searchTweet (String text) {
		List<Status> tweets;
		 try {
	            Query query = new Query(text);
	            QueryResult result;
	            do {
	                result = twitter.search(query);
	                tweets = result.getTweets();
	                for (Status tweet : tweets) {
	                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	                }
	               
	            } while ((query = result.nextQuery()) != null);
	            return tweets;
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            return null;
	        }
	}
	
	public final ResponseList<User> searchUser(String user) {
		 int page = 1;
         ResponseList<User> users;
         try {
	         do {
	             users = twitter.searchUsers(user, page);
	             for (User u : users) {
	                 if (u.getStatus() != null) {
	                     System.out.println("@" + u.getScreenName() + " - " + u.getStatus().getText());
	                 } else {
	                     // the user is protected
	                     System.out.println("@" + u.getScreenName());
	                 }
	             }
	             page++;
	         } while (users.size() != 0 && page < 50);
	         return users;
     } catch (TwitterException te) {
         te.printStackTrace();
         return null;
         
     }
	}

	
	public final void getProfileSettings() {
		  try {
	            
	            AccountSettings settings = twitter.getAccountSettings();
	           
	            System.out.println("Sleep time enabled: " + settings.isSleepTimeEnabled());
	            System.out.println("Sleep end time: " + settings.getSleepEndTime());
	            System.out.println("Sleep start time: " + settings.getSleepStartTime());
	            System.out.println("Geo enabled: " + settings.isGeoEnabled());
	            System.out.println("Listing trend locations:");
	            Location[] locations = settings.getTrendLocations();
	            for (Location location : locations) {
	                System.out.println(" " + location.getName());
	            }
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get account settings: " + te.getMessage());
	           
	        }
	}
	
	public final void updateProf(String name, String url, String locat, String description) {
		 try {
	           
	            twitter.updateProfile(name, url, locat, description);
	            System.out.println("Successfully updated profile.");
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to update profile: " + te.getMessage());
	        }
	}
	
	public final void updatePic (String path) {
		 try {
	            twitter.updateProfileImage(new File(path));
	            System.out.println("Successfully updated profile image.");
	            
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to update profile image: " + te.getMessage());
	            
	        }
	}

}
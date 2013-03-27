package edu.gvsu.cis.twitter.twitterLogic;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import twitter4j.AccountSettings;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trends;
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
			//System.out.println(twitter.getFollowersList
			//(twitter.getScreenName(), -1));
			return twitter.getFollowersList(twitter.getScreenName(), -1);
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*********************************************************
	 * Get users messages.
	 * @return list of messages
	 ********************************************************/
	public final List<DirectMessage> getMessages() {
		try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
            	
                messages = twitter.getDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("From: @" 
                    		+ message.getSenderScreenName() + " id:" 
                    		+ message.getId() + " - "
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
	
	/*********************************************************
	 * Destroys message.
	 * @param messageID number
	 ********************************************************/
	public final void destroyMessage(final long messageID) {
		try {
  
            twitter.destroyDirectMessage(messageID);
            System.out.println("Successfully deleted messag");
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to delete message: " + te.getMessage());
          
        }
	}
	
	/*********************************************************
	 * Gets messages user has sent.
	 * @return list of messages
	 ********************************************************/
	public final List<DirectMessage> getSentMessages() {
		List<DirectMessage> directMessages;
		try {
            Paging page = new Paging(1);
            do {
                directMessages = twitter.getSentDirectMessages(page);
                for (DirectMessage message : directMessages) {
                    System.out.println("To: @" 
                    		+ message.getRecipientScreenName() 
                    		+ " id:" + message.getId() + " - "
                            + message.getText());
                }
                page.setPage(page.getPage() + 1);
            } while (directMessages.size() > 0 && page.getPage() < 10);
            System.out.println("done.");
            return getMessages();
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get sent messages: " 
            				+ te.getMessage());
            return null;
        }
	}
	
	/*********************************************************
	 * Sends a message to a specific user.
	 * @param to user sending to
	 * @param text text for message
	 ********************************************************/
	public final void sendMessage(final String to, final String text) {
		
		try {
            DirectMessage message = twitter.sendDirectMessage(to, text);
            System.out.println("Direct message successfully sent to " 
            			+ message.getRecipientScreenName());
           
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to send a direct message: " 
            				+ te.getMessage());
           
        }
	}
	
	/*********************************************************
	 * Shows user a message.
	 * @param messageID number of message
	 ********************************************************/
	public final void showMessage(final long messageID) {
		try {
            DirectMessage message = twitter.showDirectMessage(messageID);
            System.out.println("From: @" + message.getSenderScreenName() 
            		+ " id:" + message.getId() + " - "
                    + message.getText());
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get message: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	/*********************************************************
	 * Searches for a specific tweet.
	 * @return list of tweets
	 * @param text text searching for
	 ********************************************************/
	public final List<Status> searchTweet(final String text) {
		List<Status> tweets;
		 try {
	            Query query = new Query(text);
	            QueryResult result;
	            int count = 0;
	            do {
	                result = twitter.search(query);
	                tweets = result.getTweets();
	                count++;
	                for (Status tweet : tweets) {
	                    System.out.println("@" 
	                    		+ tweet.getUser().getScreenName() 
	                    		+ " - " + tweet.getText());
	                }
	               
	            } while ((query = result.nextQuery()) != null && count < 15);
	            return tweets;
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            return null;
	        }
	}
	
	/*********************************************************
	 * Search for a specific user.
	 * @return list of users
	 * @param user user searching for
	 ********************************************************/
	public final ResponseList<User> searchUser(final String user) {
		 int page = 1;
         ResponseList<User> users;
         try {
	         do {
	             users = twitter.searchUsers(user, page);
	             for (User u : users) {
	                 if (u.getStatus() != null) {
	                     System.out.println("@" + u.getScreenName() 
	                    		 + " - " + u.getStatus().getText());
	                 } else {
	                     // the user is protected
	                     System.out.println("@" + u.getScreenName());
	                 }
	             }
	             page++;
	         } while (users.size() != 0 && page < 25);
	         return users;
     } catch (TwitterException te) {
         te.printStackTrace();
         return null;
         
     }
	}

	/*********************************************************
	 * Gets users profile settings.
	 * @return user account settings
	 ********************************************************/
	public final AccountSettings getProfileSettings() {
		  try {
	            
	            AccountSettings settings = twitter.getAccountSettings();
	            return settings;
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get account settings: " 
	            					+ te.getMessage());
	            return null;
	           
	        }
	}
	
	/*********************************************************
	 * Updates users profile.
	 * @param name user name
	 * @param url user url
	 * @param locat user location
	 * @param description user location 
	 ********************************************************/
	public final void updateProf(final String name, final String url, 
				final String locat, final String description) {
		 try {
	           
	            twitter.updateProfile(name, url, locat, description);
	            System.out.println("Successfully updated profile.");
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to update profile: " 
	            				+ te.getMessage());
	        }
	}
	
	/*********************************************************
	 * Updates user profile picture.
	 * @param path path for user picture
	 ********************************************************/
	public final void updatePic(final String path) {
		 try {
	            twitter.updateProfileImage(new File(path));
	            System.out.println("Successfully updated profile image.");
	            
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to update profile image: " 
	            				+ te.getMessage());
	            
	        }
	}
	
	/*********************************************************
	 * Gets current trends.
	 * @return String returns trends
	 ********************************************************/
	public String [] getTrends() { 
		Trends trends;
		try {
			trends = twitter.getLocationTrends(0);
	      String[] trendsArr = new String[trends.getTrends().length];
	      for (int i = 0; i < trends.getTrends().length; i++) {
	          trendsArr[i] = trends.getTrends()[i].getName();
	          
	          // prints trends
	          System.out.println(trends.getTrends()[i].getName());
	          return trendsArr;
	      } 
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*********************************************************
	 * Gets user name.
	 * @return user name
	 * @param ID user ID
	 ********************************************************/
	public final String getUserName(final long ID) {
		
		try {
			User user = twitter.showUser(ID);
			return user.getName();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return null;
		}
	}
	
	/*********************************************************
	 * Getus user ID.
	 * @return user ID
	 ********************************************************/
	public final long getMyID() {	
			
		try {
			
			return twitter.getId();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return 0;
		}
		
	}
	
	/*********************************************************
	 * Gets user screen name.
	 * @return user screen name.
	 * @param ID user ID
	 ********************************************************/
	public final String getUserScreenName(final long ID) {
		
		
		try {
			User user = twitter.showUser(ID);
			return user.getScreenName();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return null;
		}
	}
	
	/*********************************************************
	 * Gets how many followers the user has.
	 * @return number of followers
	 * @param ID user ID
	 ********************************************************/
	public final int getFollowersNum(final long ID) {
		try {
			User user = twitter.showUser(ID);
			return user.getFollowersCount();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return 0;
		}
		
	}
	
	/*********************************************************
	 * Gets how many people user is following.
	 * @return number of followings for user
	 * @param ID user ID
	 ********************************************************/
	public final int getFollowingNum(final long ID) {
		try {
			User user = twitter.showUser(ID);
			
			return user.getFriendsCount();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return 0;
		}
	}
	
	/*********************************************************
	 * Gets users description.
	 * @return user description
	 * @param ID user ID
	 ********************************************************/
	public final String getUserDescription(final long ID) {
		try {
			User user = twitter.showUser(ID);
			
			return user.getDescription();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return null;
		}
	}
	
	/*********************************************************
	 * Gets how many tweets user has favorited.
	 * @return number of favorited tweets by user
	 * @param ID user ID
	 ********************************************************/
	public final int getFavoritesNum(final long ID) {
		
		
		try {
			User user = twitter.showUser(ID);
			return user.getFollowersCount();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return 0;
		}
	}
	
	/*********************************************************
	 * Gets users location.
	 * @return users location
	 * @param ID user ID
	 ********************************************************/
	public final String getUserLocation(final long ID) {
		
		
		try {
			User user = twitter.showUser(ID);
			return user.getLocation();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return null;
		}
	}
	
	/*********************************************************
	 * Gets users picture.
	 * @return users picture
	 * @throws MalformedURLException exception
	 * @param ID user ID
	 ********************************************************/
	public final URL getUserPicture(final long ID) throws 
								MalformedURLException {
		
		
		try {
			User user = twitter.showUser(ID);
			String urlString = user.getProfileImageURL();
			URL url = new URL(urlString);
			return url;
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return null;
		}
	}
	
	/*********************************************************
	 * Gets number of tweets user has done.
	 * @return number of tweets by user
	 * @param ID user ID
	 ********************************************************/
	public final int getUserTweetNum(final long ID) {
		
		
		try {
			User user = twitter.showUser(ID);
			return user.getStatusesCount();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			return 0;
		}
	}
	
	/*********************************************************
	 * Looks if user is being followed by specific user.
	 * @return ture if followed by user, false if not
	 * @param one first user 
	 * @param two second user
	 ********************************************************/
	public final boolean isFollowedBy(final String one, final String two) {
		
			try {
				Relationship relation = twitter.showFriendship(one, two);
				return relation.isSourceFollowedByTarget();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
	}
	
	/**********************************************************
	 * Shows the rate limits left for everything
	 ********************************************************/
	public final void showRateLimitRemaining() {
		
		try {
			Map<String,RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
			
			 for (String endpoint : rateLimitStatus.keySet()) {
	                RateLimitStatus status = rateLimitStatus.get(endpoint);
	                System.out.println("Endpoint: " + endpoint);
	                System.out.println(" Limit: " + status.getLimit());
	                System.out.println(" Remaining: " + status.getRemaining());
	                System.out.println(" ResetTimeInSeconds: " + status.getResetTimeInSeconds());
	                System.out.println(" SecondsUntilReset: " + status.getSecondsUntilReset());
	            }
	           
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            
	        }
	}

}
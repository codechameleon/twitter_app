package TwitterLogic;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/********************************************************************
 * Base class that contains Twitter Object and Configuration. Login 
 * starts here.
 * @author Michael Torres
 *
 *******************************************************************/
public class Base {

	/** Twitter Object */
	protected Twitter twitter;
	
	/** Twitter factorey for twitter object */
	TwitterFactory tf;
	
	/** Configuration for Twitter */
	private ConfigurationBuilder configuration;
	
	/**********************************************************
	 * Login and set up twitter object and configuration
	 *********************************************************/
	public Base() {
		GetAccessToken t = new GetAccessToken();
		configuration = new ConfigurationBuilder();
		configuration.setDebugEnabled(true);
		configuration.setOAuthConsumerKey(TwitterConstants.CONSUMER_KEY);
		configuration.setOAuthConsumerSecret(TwitterConstants.CONSUMER_SECRET);
		
		AccessToken aToken = t.getToken();
			
		configuration.setOAuthAccessToken(aToken.getToken());
		configuration.setOAuthAccessTokenSecret(aToken.getTokenSecret());
		tf = new TwitterFactory(configuration.build());
		twitter = tf.getInstance();
	}
	
}

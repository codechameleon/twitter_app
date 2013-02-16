package Mocking;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import twitter4j.TwitterException;





import TwitterLogic.TweetUtils;

public class TwitterTest extends TestCase {
	
	private Status statusMock;
	
	private TweetUtils tweet;

	//@Before
	public void setUp(){
		statusMock = EasyMock.createMock(Status.class);
		tweet = new TweetUtils();
		statusMock.setStatus("I am a tweet");
		statusMock.setId(100);
		
	}
	
	//@Test
	public void testStatusUpdate() throws TwitterException {
		//tweet.deleteTweet(statusMock);
		EasyMock.expect(statusMock.getId()).andReturn((long) 100);
		
	}
}

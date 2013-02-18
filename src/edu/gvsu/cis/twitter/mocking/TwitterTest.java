package edu.gvsu.cis.twitter.mocking;


import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.gvsu.cis.twitter.twitterLogic.TweetUtils;

/**********************************************************
 * Testing mocking
 *
 *******************************************************/
public class TwitterTest extends TestCase {
	
	/** Status Mock. */
	private Status statusMock;
	
	/** Tweet engine. */
	private TweetUtils tweet;

	@Override
	@Before
	public final void setUp() {
		statusMock = EasyMock.createMock(Status.class);
		//tweet = new TweetUtils();
		statusMock.setStatus("I am a tweet");
		
	}
	
	@Test
	public final void testStatusUpdate() {
	
		EasyMock.expect(statusMock.getId()).andReturn((long) 100);
		
	}
}

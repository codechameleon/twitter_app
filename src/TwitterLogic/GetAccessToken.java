package TwitterLogic;



import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
/********************************************************************
 * Gets the access token and stores it to a file. Returns the access
 * token
 *******************************************************************/
public class GetAccessToken {
   
    public AccessToken getToken() {
        File file = new File("twitter4j.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        String akey, aSecret;
        AccessToken aToken;
        
        try {
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);
                akey = prop.getProperty("oauth.accessToken");
                aSecret = prop.getProperty("oauth.accessTokenSecret");
                if (!akey.equals("") && !aSecret.equals("")) {
                	aToken = new AccessToken(akey,aSecret);
                	return aToken;
                }
                
            }
            
                prop.setProperty("oauth.consumerKey", TwitterConstants.CONSUMER_KEY);
                prop.setProperty("oauth.consumerSecret", TwitterConstants.CONSUMER_SECRET);
                os = new FileOutputStream("twitter4j.properties");
                prop.store(os, "twitter4j.properties");
            
        } catch (IOException ioe) {
        	System.out.println("error here");
            ioe.printStackTrace();
            System.exit(-1);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignore) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ignore) {
                }
            }
        }
        
        
	        try {
	            Twitter twitter = new TwitterFactory().getInstance();
	            RequestToken requestToken = twitter.getOAuthRequestToken();
	            System.out.println("Got request token.");
	            System.out.println("Request token: " + requestToken.getToken());
	            System.out.println("Request token secret: " + requestToken.getTokenSecret());
	            AccessToken accessToken = null;
	            
	         
	            while (null == accessToken) {
	         
	                try {
	                    Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	                } catch (UnsupportedOperationException ignore) {
	                } catch (IOException ignore) {
	                } catch (URISyntaxException e) {
	                    throw new AssertionError(e);
	                }
	               // System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
	                
	                String pin = JOptionPane.showInputDialog(null,"Please enter pin number:");
	               
	                try {
	                    if (pin.length() > 0) {
	                        accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	                    } else {
	                        accessToken = twitter.getOAuthAccessToken(requestToken);
	                    }
	                } catch (TwitterException te) {
	                    if (401 == te.getStatusCode()) {
	                        System.out.println("Unable to get the access token.");
	                    } else {
	                        te.printStackTrace();
	                    }
	                }
	            }
	            System.out.println("Got access token.");
	            System.out.println("Access token: " + accessToken.getToken());
	            System.out.println("Access token secret: " + accessToken.getTokenSecret());
	            aToken = new AccessToken(accessToken.getToken(),accessToken.getTokenSecret());
	
	            try {
	                prop.setProperty("oauth.accessToken", accessToken.getToken());
	                prop.setProperty("oauth.accessTokenSecret", accessToken.getTokenSecret());
	                os = new FileOutputStream(file);
	                prop.store(os, "twitter4j.properties");
	                os.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	                System.exit(-1);
	            } finally {
	                if (os != null) {
	                    try {
	                        os.close();
	                    } catch (IOException ignore) {
	                    }
	                }
	            }
	            System.out.println("Successfully stored access token to " + file.getAbsolutePath() + ".");
	            return aToken;
	            
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get accessToken: " + te.getMessage());
	            System.exit(-1);
	        }
	       
	      return null;
        
    }
    
    
}

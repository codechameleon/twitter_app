package edu.gvsu.cis.twitter.guiDesign;


import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import edu.gvsu.cis.twitter.twitterLogic.TwitterConstants;

/*****************************************************************
 * Login Dialog.
 ****************************************************************/
@SuppressWarnings("serial")
public class LoginDialog extends JDialog 
	implements ActionListener, java.awt.event.MouseListener {
	
	/** Label for Oauth link. */
	private JLabel oAuthLinkTxt;
	
	/** Label for prompt Text. */
	private JLabel promtTxt;
	
	/** Label for pin Request. */
	private JLabel pinRequest;
	
	/** Text field for pin. */
	private JTextField pin;
	
	/** Button for login. */
	private JButton okButton;
	
	/** Twitter object. */
	private Twitter twitter;
	
	/** Request Token. */
	private RequestToken requestToken;
	
	/** Access Token. */
	private AccessToken accessToken;
	
	/**************************************************
	 * Login dialog prompt.
	 * @throws TwitterException bad twitter
	 *************************************************/
	public LoginDialog() throws TwitterException {
		setModalityType(DEFAULT_MODALITY_TYPE);
		promtTxt = new JLabel("Click the following URL"
		+ " and grant access to your account:");
		oAuthLinkTxt = new JLabel("", SwingConstants.CENTER);
		pinRequest = new JLabel("Enter the PIN(if aviailable)"
		+ " or just hit enter.[PIN]:");
		pin = new JTextField();
		okButton = new JButton("Authunticate");
	twitter = new TwitterFactory().getInstance();
	
    twitter.setOAuthConsumer(TwitterConstants.CONSUMER_KEY, 
    		TwitterConstants.CONSUMER_SECRET);
		requestToken = twitter.getOAuthRequestToken();
		oAuthLinkTxt.setText("<html><a href=" + "" + ">Get Pin</a></html>.");
    this.accessToken = null;
    setLayout(new GridLayout(5,1));
    getContentPane().add(promtTxt);
    getContentPane().add(oAuthLinkTxt);
    getContentPane().add(pinRequest);
    getContentPane().add(pin);
    getContentPane().add(okButton);
    
    okButton.addActionListener(this);
    oAuthLinkTxt.addMouseListener(this);
    setSize(400,300);
	setVisible(true);
	}
	
	/******************************************************
	 * Login action.
	 * @param e the ActionEvent
	 *****************************************************/
	@Override
	public final void actionPerformed(final ActionEvent e) {
	
		if (e.getSource() == okButton) {
			oAuthLinkTxt.setText("<html><a href=" + "" + ">link</a></html>.");
			String input = pin.getText();
			try {
				if (input.length() > 0) {
					accessToken = twitter.getOAuthAccessToken(requestToken,
							input);
					dispose();
				} else {
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					oAuthLinkTxt.setText("Unable to get the access token.");
				}
			}
		}

	}

	/*****************************************************
	 * Mouse click listener.
	 * @param e mouse event
	 ****************************************************/
	@Override
	public final void mouseClicked(final MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == oAuthLinkTxt) {
			try {
			URI uri = new URI(requestToken.getAuthorizationURL());
				Desktop.getDesktop().browse(uri);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/*************************************
	 * Get access token.
	 * @return accessToken.getToken()
	 ************************************/
	public final String getAccessTokenKey() {
		return accessToken.getToken();
	}
	
	/*************************************
	 * Get access token secret.
	 * @return accessToken.getTockenSecret()
	 ************************************/
	public final String getAccessTokenSecret() {
		return accessToken.getTokenSecret();
	}
	
	/*************************************
	 * Get access token.
	 * @return accessToken
	 ************************************/
	public final AccessToken getAccessToken() {
		return accessToken;
	}
	
	/*************************************
	 * Overide unimplimented methods.
	 * @param arg0 mouse event
	 ************************************/
	@Override
 	public void mouseEntered(final MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*************************************
	 * Overide unimplimented methods.
	 * @param arg0 mouse event
	 ************************************/
	@Override
	public void mouseExited(final MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*************************************
	 * Overide unimplimented methods.
	 * @param e mouse event
	 ************************************/
	@Override
	public void mousePressed(final MouseEvent e) {
		
	}

	/*************************************
	 * Overide unimplimented methods.
	 * @param e mouse event
	 ************************************/
	@Override
	public void mouseReleased(final MouseEvent e) {

	}
}

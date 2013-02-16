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


public class LoginDialog extends JDialog implements ActionListener, java.awt.event.MouseListener {
	
	private JLabel oAuthLinkTxt;
	private JLabel promtTxt;
	private JLabel pinRequest;
	private JTextField pin;
	private JButton okButton;
	private Twitter twitter;
	private RequestToken requestToken;
	private AccessToken accessToken;
	
	public LoginDialog() throws TwitterException{
		setModalityType(DEFAULT_MODALITY_TYPE);
		promtTxt = new JLabel("Click the following URL and grant access to your account:");
		oAuthLinkTxt = new JLabel("", SwingConstants.CENTER);
		pinRequest = new JLabel("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
		pin = new JTextField();
		okButton = new JButton("Authunticate");
	twitter = new TwitterFactory().getInstance();
	//requestToken = twitter.getOAuthRequestToken();
	// The factory instance is re-useable and thread safe.
    //twitter = TwitterFactory.getSingleton();
    //this.accessToken = null;
    twitter.setOAuthConsumer("2Z6f5DKh5pyQUMskyi9wg", "kL7THP2PQz7d50nnYsro3AOBTOo5pgdzoOrCqb3PU");
		requestToken = twitter.getOAuthRequestToken();
		oAuthLinkTxt.setText("<html><a href=" +"" +">Get Pin</a></html>.");
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == okButton){
			oAuthLinkTxt.setText("<html><a href=" +"" +">link</a></html>.");
			String input = pin.getText();
			try{
				if(input.length() > 0){
					accessToken = twitter.getOAuthAccessToken(requestToken, input);
					dispose();
				}else{
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if(401 == te.getStatusCode()){
					oAuthLinkTxt.setText("Unable to get the access token.");
				}
			}
		}

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == oAuthLinkTxt)
		{
			try {
			URI uri = new URI(requestToken.getAuthorizationURL());
				Desktop.getDesktop().browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public String getAccessTokenKey(){
		return accessToken.getToken();
	}
	public String getAccessTokenSecret(){
		return accessToken.getTokenSecret();
	}
	public AccessToken getAccessToken(){
		return accessToken;
	}
	@Override
 	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(e.getSource() == oAuthLinkTxt)
//		{
//			try {
//			URI uri = new URI(requestToken.getAuthorizationURL());
//				Desktop.getDesktop().browse(uri);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (URISyntaxException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(e.getSource() == oAuthLinkTxt)
//		{
//			try {
//			URI uri = new URI(requestToken.getAuthorizationURL());
//				Desktop.getDesktop().browse(uri);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (URISyntaxException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
	}
	}

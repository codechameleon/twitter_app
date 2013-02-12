import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class TweetPanel extends JPanel{
	
	
	
	private JButton deleteButton;
    private JButton favoriteButton;
    private JButton replayButton;
    private JButton retweetButton;
    private JLabel tweetersNameLabel;
    private JLabel tweetersPicLabel;
    private JLabel theTweetLabel;
    private Twitter twitter;
    private long tweetID;
	private JLabel tweetDateLabel;
	private JTextArea tweetText;
	/**
	 * Create the panel.
	 * @throws TwitterException 
	 * @throws IllegalStateException 
	 * @throws MalformedURLException 
	 */
	public TweetPanel(Twitter t,String screenName, String tweetersName , String imgUrl, String date ,String tweet, long tweetID) throws IllegalStateException, TwitterException, MalformedURLException {
		this.twitter = t;
//		this.setSize(350, 300);
//		this.setPreferredSize(new Dimension(350, 300));
//		this.setMinimumSize(new Dimension(350, 300));
		this.tweetID = tweetID;
		
		URL img = new URL(imgUrl);
        tweetersNameLabel = new JLabel(tweetersName);
        tweetersPicLabel = new JLabel(new ImageIcon(img));
        theTweetLabel = new JLabel(tweet);
        favoriteButton = new JButton("Favorite");
        replayButton = new JButton("Replay");
        retweetButton = new JButton("Retweet");
        deleteButton = new JButton("Delete");
        tweetDateLabel = new JLabel(date);
        
        
        

        tweetText = new JTextArea(4, 22);

        tweetText.setEditable(false);

        tweetText.setWrapStyleWord(true);

        tweetText.setLineWrap(true);

        tweetText.setBackground(new Color(255, 255, 255));

        //tweetText.setForeground(new Color(255, 255, 255));

        tweetText.setFont((Font) UIManager.get("Label.font"));
        tweetText.setText(tweet);
        
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        this.setMaximumSize(new Dimension(300, 200));
        this.setMinimumSize(new Dimension(300, 125));
        this.setPreferredSize(new Dimension(300, 125));
        this.setSize(new Dimension(300, 125));

      
        
        tweetersPicLabel.setSize(50, 50);
        tweetersPicLabel.setPreferredSize(new Dimension(50, 50));
        tweetersPicLabel.setMaximumSize(new Dimension(50, 50));
        //theTweetLabel.setMaximumSize(new Dimension(250, 75));
        
        this.setLayout(new MigLayout("wrap 4"));
        this.add(tweetersNameLabel);
        this.add(favoriteButton);
        this.add(replayButton);
        if(screenName.equals(tweetersName))
        {
        	this.add(deleteButton);
        }
        else{
        	this.add(retweetButton);
        	}
        this.add(tweetersPicLabel, "span 1 1");
        this.add(tweetText, "span 3 2");
        
        this.add(tweetDateLabel);
        validate();
        repaint();
        setVisible(true);
        
	}

}

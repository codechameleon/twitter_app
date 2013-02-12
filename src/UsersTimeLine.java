import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.*;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class UsersTimeLine extends JPanel {
	private JButton jButton1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private Twitter twitter;
    private String screenName;
	/**
	 * Create the panel.
	 * @throws TwitterException 
	 * @throws IllegalStateException 
	 * @throws MalformedURLException 
	 */
	public UsersTimeLine(Twitter t) throws IllegalStateException, TwitterException, MalformedURLException {
		this.twitter = t;
		screenName = twitter.getScreenName();
		this.setSize(350, 450);
		
		//jScrollPane1 = new JScrollPane();
        jPanel1 = new JPanel();
        jButton1 = new JButton("Refresh");
        //jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
//        jPanel1.setMaximumSize(new Dimension(300, 200));
//        jPanel1.setMinimumSize(new Dimension(300, 100));
//        jPanel1.setPreferredSize(new Dimension(300, 200));
//        jPanel1.setSize(new Dimension(300, 200));
        
        updatePanel();
        
	}
	
	private void updatePanel() throws TwitterException, IllegalStateException, MalformedURLException{
		List<Status> statusList = twitter.getUserTimeline();
		TweetPanel a[] = new TweetPanel[statusList.size()];
		jPanel1.setLayout(new GridLayout(statusList.size(), 1));
		for (int i = 0; i < statusList.size(); i++) {
			Date tweetDate = statusList.get(i).getCreatedAt();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm");
			String date = formatter.format(tweetDate);
			a[i]= new TweetPanel(twitter, screenName, statusList.get(i).getUser().getScreenName(), statusList.get(i).getUser().getProfileImageURL(), 
					date, statusList.get(i).getText(), statusList.get(i).getId());
			jPanel1.add(a[i]);
		}
		
		
		
		JScrollPane scrollPane = new JScrollPane(jPanel1,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // vertical bar
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		validate();
        repaint();
        setVisible(true);
		//jPanel1.add(scrollPane);
	}
	
	public JPanel getTimeLinePanel() {
		return jPanel1;
	}

}

package guiDesign;

import java.awt.Color;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class MainTimeLine extends JPanel {
//	private JButton jButton1;
	private JPanel jPanel1;
	private Twitter twitter;
	private String screenName;
	/**
	 * Create the panel.
	 * @throws TwitterException 
	 * @throws IllegalStateException 
	 * @throws MalformedURLException 
	 */
	public MainTimeLine(Twitter t) throws IllegalStateException, TwitterException, MalformedURLException {
		this.twitter = t;
		screenName = twitter.getScreenName();
		jPanel1 = new JPanel();
		jPanel1.setBackground(new Color(255, 255, 255));
		jPanel1.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));

	}

	public void updatePanel() throws TwitterException, IllegalStateException, MalformedURLException{
		this.removeAll();
		List<Status> statusList = twitter.getHomeTimeline();
		TweetPanel a[] = new TweetPanel[statusList.size()];
		jPanel1.removeAll();
		jPanel1.setLayout(new MigLayout("wrap 1", "0 [] 0", " 0 [] 0"));
		for (int i = 0; i < statusList.size(); i++) {
			Date tweetDate = statusList.get(i).getCreatedAt();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm");
			String date = formatter.format(tweetDate);
			a[i]= new TweetPanel(twitter, screenName, statusList.get(i).getUser().getScreenName(), statusList.get(i).getUser().getProfileImageURL(), 
					date, statusList.get(i).getText(), statusList.get(i).getId());
			jPanel1.add(a[i]);
		}


		
		JScrollPane scrollPane = new JScrollPane(jPanel1,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setMaximumSize(new Dimension(427, 400));
		//		validate();
		//        repaint();
		//        setVisible(true);
		this.setLayout(new MigLayout("wrap 1", "0 [] 0", ""));
		this.add(scrollPane, "dock north");
		//this.add(jButton1);
	}

	public JPanel getTimeLinePanel() {
		return this;
	}

}

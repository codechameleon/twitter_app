package guiDesign;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.*;

import TwitterLogic.TweetUtils;
import net.miginfocom.swing.MigLayout;
import twitter4j.Status;
import twitter4j.TwitterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/********************************************************************
 * A Test GUI for the TweetUtils. 
 * 
 * @author Ali Aljishi
 *
 *******************************************************************/
public class TwitterAppGUI extends JFrame implements ActionListener {
	//Main Client Components
	private TweetUtils engine;
	private JTextArea tweetText;
	private JButton tweetButton;
	private JButton refreshButton;
	private JPanel tweetPanel;
	private JPanel timeLinePanel;
	private JButton timeLineButton;
	private JButton usersTimeLineButton;
	private JButton myTimeLineButton;
	public boolean login = false;
	private TimeLine usersTimeLine;
	private TimeLine homeTimeLine;
	private TimeLine mentionsTimeLine;
	//private Followers followersPanel;
	private String screenName;

	/********************************************************************
	 * TwitterAppGUI constructor, takes a TweetUtils object
	 * and creates the main GUI for a user to use the Application
	 * @param TweetUtils
	 *******************************************************************/
	public TwitterAppGUI (TweetUtils tu) throws TwitterException, IllegalStateException, MalformedURLException {
		this.engine = tu;
		this.screenName = tu.getScreenName();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 700);
		this.setMaximumSize(new Dimension(400, 700));
		//this.setPreferredSize(getSize());
		//this.setMaximizedBounds(new Rectangle(450, 800));
		this.setResizable(false);
		setTitle("Twitter");
		usersTimeLine = new TimeLine(engine.getUserTimeline());
		homeTimeLine = new TimeLine(engine.getTimeLine());
		mentionsTimeLine = new TimeLine(engine.getMentionsTimeLine());
		setLayout(new MigLayout("wrap 1", "0 [] 0", "0 [] 0"));
		//followersPanel = new Followers(twitter);
		//this.add(followersPanel, "dock east");
		tweetPanel();
		navigationPanel();
		timeLinePanel();
		this.pack();
		setVisible(true);

	}
	
	/*********************************************************
	 * Creates a panel with button that displays the TimeLines
	 * panels
	 ********************************************************/
	public void navigationPanel(){
		JPanel naviPanel = new JPanel();
		usersTimeLineButton = new JButton("Me!");
		usersTimeLineButton.addActionListener(this);
		timeLineButton = new JButton("Tweets");
		timeLineButton.addActionListener(this);
		myTimeLineButton = new JButton("@ Me");
		myTimeLineButton.addActionListener(this);
		JButton followingButton = new JButton("Following");
		naviPanel.setLayout(new FlowLayout());
		naviPanel.add(usersTimeLineButton);
		naviPanel.add(timeLineButton);
		naviPanel.add(myTimeLineButton);
		naviPanel.add(followingButton);
		naviPanel.setBackground(Color.BLACK);
		naviPanel.setSize(450,38);
		naviPanel.setMinimumSize(naviPanel.getSize());
		naviPanel.setMaximumSize(naviPanel.getSize());
		add(naviPanel);
	}

	/*********************************************************
	 * Top panel where you have a TextArea that you could
	 * write a tweet, also creats a tweet button to tweet 
	 * the input.
	 ********************************************************/
	public void tweetPanel() throws TwitterException{
		tweetPanel = new JPanel();
		tweetText = new JTextArea(3,27);
		tweetText.setLineWrap(true);
		tweetText.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(tweetText);
		tweetButton = new JButton("Tweet!");
		tweetButton.addActionListener(this);
		tweetPanel.setLayout(new MigLayout("wrap 2"));
		tweetPanel.add(scroll);
		tweetPanel.add(tweetButton);
		getContentPane().add(tweetPanel, "dock north");
	}

	/*********************************************************
	 * creates a TimeLine panel which is the users own TimeLine.
	 * runs when the GUI first is lanched
	 ********************************************************/
	public void timeLinePanel(){
		timeLinePanel = new JPanel();
		try {
			usersTimeLine.updatePanel(engine.getUserTimeline());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		timeLinePanel.add(usersTimeLine.getTimeLinePanel());
		getContentPane().add(timeLinePanel, "grow");
		validate();

	}
	
	/*********************************************************
	 * creates a panel with the main TimeLine that view all 
	 * the users tweets and tweets from whom they are following
	 * and displays it if its not the panel displayed
	 ********************************************************/
	public void mainTimeLine(){
		timeLinePanel.removeAll();
		try {
			homeTimeLine.updatePanel(engine.getTimeLine());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		timeLinePanel.add(homeTimeLine.getTimeLinePanel());
		timeLinePanel.revalidate();
	}

	/*********************************************************
	 * creates a TimeLine panel that display the tweets directed
	 * at the user and displays the panel if its not the one
	 * displayed
	 ********************************************************/
	public void mentionsTimeLine(){
		timeLinePanel.removeAll();
		try {
			mentionsTimeLine.updatePanel(engine.getMentionsTimeLine());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		timeLinePanel.add(mentionsTimeLine.getTimeLinePanel());
		timeLinePanel.revalidate();
	}

	/*********************************************************
	 * Updates the users own TimeLine and displays it if its
	 * not the one displayed
	 ********************************************************/
	public void timeLinePanelRefresh(){
		timeLinePanel.removeAll();

		try {
			usersTimeLine.updatePanel(engine.getUserTimeline());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		timeLinePanel.add(usersTimeLine.getTimeLinePanel());
		timeLinePanel.revalidate();

	}


	//	public static void waiting (int n){
	//
	//		long t0, t1;
	//
	//		t0 =  System.currentTimeMillis();
	//
	//		do{
	//			t1 = System.currentTimeMillis();
	//		}
	//		while (t1 - t0 < n);
	//	}

	/*********************************************************
	 * ActionPerform method that controls what each Button
	 * in the naviPanel and TweetPanel do
	 * @param ActionEvent
	 ********************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tweetButton){
			engine.sendTweet(tweetText.getText());
			tweetText.setText("");
			//waiting(2000);
			//mainTimeLine();
		}

		if (e.getSource() == refreshButton || e.getSource()==usersTimeLineButton){
			timeLinePanelRefresh();
		}

		if (e.getSource()== timeLineButton){
			mainTimeLine();
		}

		if(e.getSource()== myTimeLineButton){
			mentionsTimeLine();
		}
	}

	/*********************************************************
	 * A class representing the Tweet and actions that can be
	 * done with them
	 ********************************************************/
	public class TweetPanel extends JPanel implements ActionListener{
		private JButton deleteButton;
		private JButton favoriteButton;
		private JButton replayButton;
		private JButton retweetButton;
		private JLabel tweetersNameLabel;
		private JLabel tweetersPicLabel;
		private JLabel tweetDateLabel;
		private JTextArea tweetText;
		private Status state;
		/**
		 * Create the panel.
		 * @throws TwitterException 
		 * @throws IllegalStateException 
		 * @throws MalformedURLException 
		 */
		
		/*********************************************************
		 * TweetPanel Constructor that takes a Status object to 
		 * represent a tweet
		 * @param Status
		 ********************************************************/
		public TweetPanel(Status s) throws IllegalStateException, TwitterException, MalformedURLException {
			this.state = s;
			s.getId();
			Date tweetDate = s.getCreatedAt();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm");
			String date = formatter.format(tweetDate);

			URL img = new URL(s.getUser().getProfileImageURL());
			tweetersNameLabel = new JLabel(s.getUser().getScreenName());

			Font tweetersNameFont =new Font(tweetersNameLabel.getFont().getName(),Font.ITALIC+Font.BOLD, tweetersNameLabel.getFont().getSize());  
			tweetersNameLabel.setFont(tweetersNameFont);
			tweetersPicLabel = new JLabel(new ImageIcon(img));
			favoriteButton = new JButton("Favorite");
			replayButton = new JButton("Replay");
			retweetButton = new JButton("Retweet");
			deleteButton = new JButton("Delete");
			tweetDateLabel = new JLabel(date);

			deleteButton.addActionListener(this);
			favoriteButton.addActionListener(this);
			retweetButton.addActionListener(this);

			tweetText = new JTextArea(4, 22);
			tweetText.setEditable(false);
			tweetText.setWrapStyleWord(true);
			tweetText.setLineWrap(true);
			tweetText.setBackground(new Color(255, 255, 255));
			//tweetText.setForeground(new Color(255, 255, 255));
			tweetText.setFont((Font) UIManager.get("Label.font"));
			tweetText.setText(s.getText());

			this.setBackground(new Color(255, 255, 255));
			this.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
			this.setMaximumSize(new Dimension(405, 200));
			this.setMinimumSize(new Dimension(405, 125));
			this.setPreferredSize(new Dimension(405, 125));
			this.setSize(new Dimension(405, 125));

			tweetersPicLabel.setSize(60, 60);
			tweetersPicLabel.setPreferredSize(new Dimension(50, 50));
			tweetersPicLabel.setMaximumSize(new Dimension(50, 50));
			//theTweetLabel.setMaximumSize(new Dimension(250, 75));

			this.setLayout(new MigLayout("wrap 4", "5 []", ""));
			this.add(tweetersNameLabel);
			this.add(favoriteButton);
			this.add(replayButton);
			//Check if a tweet belongs to the user or not
			// if it did belong to the user it shows the delete button
			// if not it shows the replay button
			if(screenName.equals(state.getUser().getScreenName()))
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
		
		/*********************************************************
		 * actionPerformed class for the tweetPanel, controlling
		 * what each Button in the tweetPanel do
		 * @param ActionEvent
		 ********************************************************/
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == deleteButton){
				engine.deleteTweet(state);
			}
			if(e.getSource() == retweetButton){
				engine.reTweet(state);
			}
			if(e.getSource()== favoriteButton){
				engine.favorite(state);
			}
		}

	}

	/*********************************************************
	 * TimeLine class creates the TimeLine Panels and fills
	 * them with tweetPanels
	 ********************************************************/
	private class TimeLine extends JPanel{
		private JPanel jPanel1;
		private List<Status> statusList;
		/**
		 * Create the panel.
		 * @throws TwitterException 
		 * @throws IllegalStateException 
		 * @throws MalformedURLException 
		 */
		/*********************************************************
		 * TimeLine class constructor, takes a List of Statuses
		 * @param List<Status>
		 ********************************************************/
		public TimeLine(List<Status> l) throws IllegalStateException, TwitterException, MalformedURLException {
			jPanel1 = new JPanel();
			jPanel1.setBackground(new Color(255, 255, 255));
			jPanel1.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
			this.statusList = l;

		}
		
		/*********************************************************
		 * Updates TimeLine panel with a new List
		 ********************************************************/
		public void updatePanel(List<Status> l) throws TwitterException, IllegalStateException, MalformedURLException{
			this.removeAll();
			this.statusList =l;
			TweetPanel a[] = new TweetPanel[statusList.size()];
			jPanel1.removeAll();
			jPanel1.setLayout(new MigLayout("wrap 1", "0 [] 0", " 0 [] 0"));
			for (int i = 0; i < statusList.size(); i++) {
				a[i]= new TweetPanel(statusList.get(i));
				jPanel1.add(a[i]);
			}

			JScrollPane scrollPane = new JScrollPane(jPanel1,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setMaximumSize(new Dimension(427, 400));
			this.setLayout(new MigLayout("wrap 1", "0 [] 0", ""));
			this.add(scrollPane, "dock north");
		}

		/*********************************************************
		 * returns a TimeLine panel
		 * @return TimeLinePanel
		 ********************************************************/
		public JPanel getTimeLinePanel() {
			return this;
		}

	}

}

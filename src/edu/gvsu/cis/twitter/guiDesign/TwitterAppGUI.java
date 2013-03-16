package edu.gvsu.cis.twitter.guiDesign;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import twitter4j.Status;
import twitter4j.TwitterException;
import edu.gvsu.cis.twitter.twitterLogic.TweetUtils;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/********************************************************************
 * A Test GUI for the TweetUtils. 
 *
 *******************************************************************/
@SuppressWarnings("serial")
public class TwitterAppGUI extends JFrame implements ActionListener {
	
	/** TweetUtils engine. */
	private TweetUtils engine;
	
	/** JTextArea for tweet text.*/
	private JTextArea tweetText;
	
	/** Buttons. */
	private JButton tweetButton, refreshButton, timeLineButton,
		usersTimeLineButton, myTimeLineButton;
	
	/** Pannels. */
	private JPanel tweetPanel, timeLinePanel;
	
	/** Booolean for login. */
	private boolean login = false;
	
	/** TimeLines. */
	private TimeLine usersTimeLine, homeTimeLine, mentionsTimeLine;

	/** Screen name. */
	private String screenName;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JMenuItem mntmSignOut;
	private JMenu mnEdit;
	private JMenuItem mntmTheme;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenu mnProfile;
	private JMenuItem mntmDirectMessages;
	private JMenuItem mntmSettings;
	private JMenu mnSearch;
	private JMenuItem mntmUser;
	private JMenuItem mntmHashtag;
	private JMenuItem mntmTweets;

	/********************************************************************
	 * TwitterAppGUI constructor, takes a TweetUtils object
	 * and creates the main GUI for a user to use the Application.
	 * @param tu tweetUtils
	 * @throws MalformedURLException error
	 * @throws TwitterException twitter error
	 *******************************************************************/
	public TwitterAppGUI(final TweetUtils tu) throws TwitterException, 
		 MalformedURLException {
		this.engine = tu;
		this.screenName = tu.getScreenName();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 700);
		this.setMaximumSize(new Dimension(400, 700));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Twitter");
		usersTimeLine = new TimeLine(engine.getUserTimeline());
		homeTimeLine = new TimeLine(engine.getTimeLine());
		mentionsTimeLine = new TimeLine(engine.getMentionsTimeLine());
		getContentPane().setLayout(new MigLayout("wrap 1", "0 [] 0", "0 [] 0"));
		tweetPanel();
		navigationPanel();
		timeLinePanel();
		this.pack();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSignOut = new JMenuItem("Sign Out");
		
		mnFile.add(mntmSignOut);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		mnFile.add(mntmExit);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmTheme = new JMenuItem("Theme");
		mnEdit.add(mntmTheme);
		
		mnProfile = new JMenu("Profile");
		menuBar.add(mnProfile);
		
		mntmDirectMessages = new JMenuItem("Direct Messages");
		mntmDirectMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectMessaging messages = new DirectMessaging();
				
			}
		});
		mnProfile.add(mntmDirectMessages);
		
		mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TwitterProfile profile = new TwitterProfile();
			}
		});
		mnProfile.add(mntmSettings);
		
		mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		mntmUser = new JMenuItem("User");
		mntmUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = JOptionPane.showInputDialog(null, "Enter user name");
				engine.searchUser(user);
			}
		});
		mnSearch.add(mntmUser);
		
		mntmHashtag = new JMenuItem("Hashtags");
		mntmHashtag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hash = JOptionPane.showInputDialog(null, "Enter Hashtag");
				engine.searchTweet("#" + hash);
			}
		});
		mnSearch.add(mntmHashtag);
		
		mntmTweets = new JMenuItem("Tweets");
		mntmTweets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "Enter tweet");
				engine.searchTweet(text);
			}
		});
		mnSearch.add(mntmTweets);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Twitter Application version 0.2\n\n Created by:\n " +
						"Michael Torres\n Ali Aljishi\n Tori Letwinksi");
			}
		});
		mnHelp.add(mntmAbout);
		setVisible(true);

	}
	
	/*********************************************************
	 * Creates a panel with button that displays the TimeLines
	 * panels.
	 ********************************************************/
	public final void navigationPanel() {
		JPanel naviPanel = new JPanel();
		usersTimeLineButton = new JButton("My Tweets");
		usersTimeLineButton.addActionListener(this);
		timeLineButton = new JButton("All Tweets");
		timeLineButton.addActionListener(this);
		myTimeLineButton = new JButton("@ Me");
		myTimeLineButton.addActionListener(this);
		JButton followingButton = new JButton("Following");
		naviPanel.setLayout(new FlowLayout());
		naviPanel.add(usersTimeLineButton);
		naviPanel.add(timeLineButton);
		naviPanel.add(myTimeLineButton);
		naviPanel.add(followingButton);
		naviPanel.setBackground(new Color(51, 153, 153));
		naviPanel.setSize(450,38);
		naviPanel.setMinimumSize(naviPanel.getSize());
		naviPanel.setMaximumSize(naviPanel.getSize());
		getContentPane().add(naviPanel);
	}

	/*********************************************************
	 * Top panel where you have a TextArea that you could
	 * write a tweet, also creats a tweet button to tweet 
	 * the input.
	 * @throws TwitterException twitter error
	 ********************************************************/
	public final void tweetPanel() throws TwitterException {
		tweetPanel = new JPanel();
		tweetText = new JTextArea(3,27);
		tweetText.setToolTipText("");
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
	 * creates a TimeLine panel which is the users own TimeLine
	 * runs when the GUI first is lanched.
	 ********************************************************/
	public final void timeLinePanel() {
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
	 * and displays it if its not the panel displayed.
	 ********************************************************/
	public final void mainTimeLine() {
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
	 * displayed.
	 ********************************************************/
	public final void mentionsTimeLine() {
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
	 * not the one displayed.
	 ********************************************************/
	public final void timeLinePanelRefresh() {
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


	/*********************************************************
	 * ActionPerform method that controls what each Button
	 * in the naviPanel and TweetPanel do.
	 * @param e actionevent
	 ********************************************************/
	@Override
	public final void actionPerformed(final ActionEvent e) {
		
		// Find source of button pressed
		if (e.getSource() == tweetButton) {
			engine.sendTweet(tweetText.getText());
			tweetText.setText("");
		}

		if (e.getSource() == refreshButton || e.getSource() 
				== usersTimeLineButton) {
			timeLinePanelRefresh();
		}

		if (e.getSource() == timeLineButton) {
			mainTimeLine();
		}

		if (e.getSource() == myTimeLineButton) {
			mentionsTimeLine();
		}
	}

	/*******************************************
	 * Setter for boolean login.
	 * @return login
	 *****************************************/
	public final boolean isLogin() {
		return login;
	}

	/*******************************************
	 * Getter for boolean login.
	 * @param login bool
	 ******************************************/
	public final void setLogin(final boolean login) {
		this.login = login;
	}

	/*********************************************************
	 * A class representing the Tweet and actions that can be
	 * done with them.
	 ********************************************************/
	public class TweetPanel extends JPanel implements ActionListener {
		
		/** Buttons. */
		private JButton deleteButton, favoriteButton,
			replayButton, retweetButton;
		
		/** Labels. */
		private JLabel tweetersNameLabel, tweetersPicLabel, tweetDateLabel;
		
		/** text area. */
		private JTextArea tweetText;
		
		/** Status. */
		private Status state;
		/**
		 * Create the panel.
		 * @throws TwitterException 
		 * @throws IllegalStateException 
		 * @throws MalformedURLException 
		 */
		
		/*********************************************************
		 * TweetPanel Constructor that takes a Status object to 
		 * represent a tweet.
		 * @param s status
		 * @throws IllegalStateException error
		 * @throws TwitterException twitter error
		 * @throws MalformedURLException error
		 ********************************************************/
		public TweetPanel(final Status s) throws IllegalStateException,
			TwitterException, MalformedURLException {
			this.state = s;
			s.getId();
			Date tweetDate = s.getCreatedAt();
			SimpleDateFormat formatter = new 
					SimpleDateFormat("dd-MMM-yy HH:mm");
			String date = formatter.format(tweetDate);

			URL img = new URL(s.getUser().getProfileImageURL());
			tweetersNameLabel = new JLabel(s.getUser().getScreenName());

			Font tweetersNameFont = new Font(
					tweetersNameLabel.getFont().getName(),
					Font.ITALIC + Font.BOLD,
					tweetersNameLabel.getFont().getSize());  
			
			tweetersNameLabel.setFont(tweetersNameFont);
			tweetersPicLabel = new JLabel(new ImageIcon(img));
			favoriteButton = new JButton("Favorite");
			replayButton = new JButton("Reply");
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
			tweetText.setFont((Font) UIManager.get("Label.font"));
			tweetText.setText(s.getText());

			this.setBackground(new Color(255, 255, 255));
			this.setBorder(BorderFactory.createLineBorder(
					new Color(204, 204, 204)));
			this.setMaximumSize(new Dimension(405, 200));
			this.setMinimumSize(new Dimension(405, 125));
			this.setPreferredSize(new Dimension(405, 125));
			this.setSize(new Dimension(405, 125));

			tweetersPicLabel.setSize(60, 60);
			tweetersPicLabel.setPreferredSize(new Dimension(50, 50));
			tweetersPicLabel.setMaximumSize(new Dimension(50, 50));

			this.setLayout(new MigLayout("wrap 4", "5 []", ""));
			this.add(tweetersNameLabel);
			this.add(favoriteButton);
			this.add(replayButton);
			
			//Check if a tweet belongs to the user or not
			// if it did belong to the user it shows the delete button
			// if not it shows the replay button
			if (screenName.equals(state.getUser().getScreenName())) {
				this.add(deleteButton);
			} else {
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
		 * what each Button in the tweetPanel do.
		 * @param e actionevent
		 ********************************************************/
		@Override
		public final void actionPerformed(final ActionEvent e) {
			
			if (e.getSource() == deleteButton) {
				engine.deleteTweet(state);
			}
			else if (e.getSource() == retweetButton) {
				engine.reTweet(state);
			}
			else if (e.getSource() == favoriteButton) {
				engine.favorite(state);
			}
			else if (e.getSource() == mntmExit) {
				System.out.println("exit");
				System.exit(0);
				
			}
		}

	}

	/*********************************************************
	 * TimeLine class creates the TimeLine Panels and fills
	 * them with tweetPanels.
	 ********************************************************/
	private class TimeLine extends JPanel {
		
		/** Panel. */
		private JPanel jPanel1;
		
		/** List. */
		private List<Status> statusList;
		
		/**
		 * Create the panel.
		 * @throws TwitterException 
		 * @throws IllegalStateException 
		 * @throws MalformedURLException 
		 */
		/*********************************************************
		 * TimeLine class constructor, takes a List of Statuses.
		 * @param l status list 
		 * @throws TwitterException error 
		 * @throws MalformedURLException error 
		 ********************************************************/
		public TimeLine(final List<Status> l) throws
			TwitterException, MalformedURLException {
			jPanel1 = new JPanel();
			jPanel1.setBackground(new Color(255, 255, 255));
			jPanel1.setBorder(BorderFactory.createLineBorder(
					new Color(204, 204, 204)));
			this.statusList = l;

		}
		
		/*********************************************************
		 * Updates TimeLine panel with a new List.
		 * @param l statuses
		 * @throws TwitterException 
		 * @throws MalformedURLException 
		 * @throws IllegalStateException 
		 ********************************************************/
		public void updatePanel(final List<Status> l) throws 
			IllegalStateException, MalformedURLException, TwitterException {
			this.removeAll();
			this.statusList = l;
			TweetPanel[] a = new TweetPanel[statusList.size()];
			jPanel1.removeAll();
			jPanel1.setLayout(new MigLayout("wrap 1", "0 [] 0", " 0 [] 0"));
			for (int i = 0; i < statusList.size(); i++) {
				a[i] = new TweetPanel(statusList.get(i));
				jPanel1.add(a[i]);
			}

			JScrollPane scrollPane = new JScrollPane(jPanel1,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setMaximumSize(new Dimension(427, 400));
			this.setLayout(new MigLayout("wrap 1", "0 [] 0", ""));
			this.add(scrollPane, "dock north");
		}

		/*********************************************************
		 * returns a TimeLine panel.
		 * @return TimeLinePanel
		 ********************************************************/
		public JPanel getTimeLinePanel() {
			return this;
		}

	}

}

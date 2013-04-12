package edu.gvsu.cis.twitter.guiDesign;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import edu.gvsu.cis.twitter.twitterLogic.TweetUtils;
import edu.gvsu.cis.twitter.twitterLogic.TwitterAccounts;
import edu.gvsu.cis.twitter.twitterLogic.TwitterConstants;

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

	private Followers followersPanel;
	
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
	private JPanel mainFollowersPanel;
	private JPanel naviPanel;
	private Color newColor;
	static Preferences prefs;
	private TrendsPanel trendsView;
	private JMenuItem mntmFacebook;
	private JMenuItem mntmNotifications;
	private JMenuItem mntmRefresh;
	private JMenuItem mntmTweet;

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
		this.setSize(750, 900);
		this.setMaximumSize(getSize());
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Twitter");
		usersTimeLine = new TimeLine(engine.getUserTimeline());
		homeTimeLine = new TimeLine(engine.getTimeLine());
		mentionsTimeLine = new TimeLine(engine.getMentionsTimeLine());
		mainFollowersPanel = new JPanel();
		mainFollowersPanel.setSize(220,440);
		mainFollowersPanel.setPreferredSize(mainFollowersPanel.getSize());
		followersPanel = new Followers(engine.getFollowers());
//		trendsView = new TrendsPanel(engine.getTrends());
		
		final TwitterAccounts accounts = new TwitterAccounts();
		accounts.loadFile();
		accounts.setName(tu.getScreenName(), TwitterConstants.selectedAccount);
		accounts.setPicURl(TwitterConstants.selectedAccount, tu.getUserPicture(tu.getMyID()));
		accounts.saveFile();
		
		getContentPane().setLayout(new MigLayout("wrap 1", "0 [] 0", "0 [] 0"));
		// Start Adding Panels!
		getContentPane().add(mainFollowersPanel, "dock east");
		tweetPanel();
		navigationPanel();
		timeLinePanel();
		
		followersPanel();
		this.pack();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSignOut = new JMenuItem("Sign Out");
		mntmSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signOut();
			}
		});
		
		
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
		mntmTheme.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				newColor = JColorChooser.showDialog(null, 
						"Choose theme color", getBackground());
				
				if (newColor != null) {
					naviPanel.setBackground(newColor);
					prefs.putInt("Red",newColor.getRed());
					prefs.putInt("Green", newColor.getGreen());
					prefs.putInt("Blue", newColor.getBlue());
				}
			}
		});

		mnEdit.add(mntmTheme);
		
		mntmNotifications = new JMenuItem("Notifications");
		mnEdit.add(mntmNotifications);
		
		mntmRefresh = new JMenuItem("Refresh Time");
		mnEdit.add(mntmRefresh);
		
		mnProfile = new JMenu("Profile");
		menuBar.add(mnProfile);
		
		mntmDirectMessages = new JMenuItem("Direct Messages");
		mntmDirectMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DirectMsgDialog(engine.getFollowers());
			}
		});
		mnProfile.add(mntmDirectMessages);
		
		mntmSettings = new JMenuItem("Profile Info");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long id = tu.getMyID();
				try {
					ShowProfile profile = new ShowProfile(tu.getUserName(id), tu.getUserPicture(id),
							tu.getScreenName(), tu.getFollowersNum(id), tu.getFollowingNum(id),
							tu.getFavoritesNum(id), tu.getUserTweetNum(id), tu.getUserDescription(id),
							tu.getUserLocation(id));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		mntmFacebook = new JMenuItem("Facebook Your Tweet");
		mntmFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Facebook fb = new Facebook();
				Desktop desktop = Desktop.getDesktop();
				
				// Save access token 
				//String access = prefs.get("Facebook", "");
				
				String access = accounts.getFB(TwitterConstants.selectedAccount);
				
				// Token not saved and must run access prompt once 
				if (access == "" || access == null) {
				
					try {
						desktop.browse(new URI(fb.FBURL));
						String url = JOptionPane.showInputDialog(null, "Please paste URL here. " +
								"We do not share your" +
								" information with third parties." );
						
						if (url != null && !url.equals("")) {
							String tok = fb.getAccessToken(url);
							//prefs.put("Facebook", tok);
							accounts.addFB(tok, TwitterConstants.selectedAccount);
							accounts.saveFile();
							if (tweetText.getText() != null && !tweetText.getText().equals("")) {
								fb.sendToFacebook(tweetText.getText(), tok);
								JOptionPane.showMessageDialog(null,"Tweet Posted to Facebook!");
							} else {
								JOptionPane.showMessageDialog(null, "Please Enter a Tweet First");
							}
								
							}
					} catch (IOException e) {
						
						e.printStackTrace();
					} catch (URISyntaxException e) {
						
						e.printStackTrace();
					}

				} else {
					if (tweetText.getText() != null && !tweetText.getText().equals("")) {
						try {
							fb.sendToFacebook(tweetText.getText(), access);
							JOptionPane.showMessageDialog(null,"Tweet Posted to Facebook!");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please Enter a Tweet First");
					}
				}
			}
			
		});
		mnProfile.add(mntmFacebook);
		
		mntmTweet = new JMenuItem("Tweet From All Accounts");
		mntmTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tweetText.getText() != null && !tweetText.getText().equals("")) {
					int number = accounts.getSize();
				
					for (int i = 0; i < number; i++) {
						TweetUtils tempE = new TweetUtils(accounts.getAccountAt(i));
						tempE.sendTweet(tweetText.getText());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter a Tweet First");
				}
			}
		});
		mnProfile.add(mntmTweet);
		mnProfile.add(mntmSettings);
		
		mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		mntmUser = new JMenuItem("User");
		mntmUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = JOptionPane.showInputDialog(null, "Enter user name");
				//engine.searchUser(user);
				try {
					new UserSearchR(engine.searchUser(user));
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnSearch.add(mntmUser);
		
		mntmHashtag = new JMenuItem("Hashtags");
		mntmHashtag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hash = JOptionPane.showInputDialog(null, "Enter Hashtag");
				//engine.searchTweet("#" + hash);
				try {
					new TweetSearchR(engine.searchTweet("#"+hash));
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnSearch.add(mntmHashtag);
		
		mntmTweets = new JMenuItem("Tweets");
		mntmTweets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "Enter tweet");
				//engine.searchTweet(text);
				try {
					new TweetSearchR(engine.searchTweet(text));
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		naviPanel = new JPanel();
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

		// Define a node in which preferences are stored
		prefs = Preferences.userRoot().node(this.getClass().getName());
		int r, g, b = 0;
		r = prefs.getInt("Red", 51);
		g = prefs.getInt("Green", 153);
		b = prefs.getInt("Blue", 153);

		naviPanel.setBackground(new Color(r, g, b));
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
		timeLinePanel.setSize(450,440);
		timeLinePanel.setPreferredSize(timeLinePanel.getSize());
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
			scrollPane.setMaximumSize(new Dimension(427, 420));
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
	
	/*********************************************************
	 * Followers class creates the Followers Panels and fills
	 * it with the people a user is following
	 ********************************************************/
	public class Followers extends JPanel {
		/** JPanel to hold the followrs panel */
		private JPanel jPanel1;
		
		/** List of Users */
	    private List<User> followers;
	    
		/** the avatar URL */
	    private URL imageUrl;
	    
		/** Followers Panel width */
	    private int panelWidth = 250;
	    
		/** Sending Button */
	    private JLabel followerName;
	    
		/** Label holding the avatar */
	    private JLabel followerImg;
	    
		/** Panel holding the list */
	    private JPanel followerPanel;
	    
		/** Followers Name Label */
	    private JLabel followerTitleLabel;
	    
		/** Scroll Panel in case needed */
	    private JScrollPane followerJScrollPane;
	    
	    public Followers(List<User> l) throws TwitterException {
	    	this.setSize(250, 400);
	    	this.setPreferredSize(this.getSize());
	    	followers = l;
	    	updateFollowers(followers);
	    }
	    public void updateFollowers(List<User> users){
	    	this.removeAll();
	    	this.setSize(250, 400);
	    	this.setPreferredSize(this.getSize());
	    	this.followers = users;
	        followerPanel = new JPanel();
	        followerPanel.setLayout(new MigLayout("wrap 1"));
	        this.setLayout(new MigLayout("wrap 1"));

	        
	        JLabel followersT = new JLabel("Followers!");
	        this.add(followersT);
	        for (User u : followers) {
	            try {
					imageUrl = new URL(u.getProfileImageURL());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}

	            followerName = new JLabel(u.getName());

	            followerImg = new JLabel(new ImageIcon(imageUrl));
	            followerImg.setSize(50, 50);
	            followerImg.setPreferredSize(new Dimension(50, 50));
	            followerImg.setMaximumSize(new Dimension(50, 50));
	            JPanel thisFollowerPanel = new JPanel(new MigLayout("wrap 2"));
	            thisFollowerPanel.add(followerImg);
	            thisFollowerPanel.add(followerName);
	            followerPanel.add(thisFollowerPanel);
	        }
	        followerJScrollPane = new JScrollPane(followerPanel,
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        this.add(followerJScrollPane);
	    }
	    /*********************************************************
		 * returns a TimeLine panel.
		 * @return FollowersPanel
		 ********************************************************/
	    public JPanel getFollowersPanel(){
	    	return this;
	    }

	}
	
	public final void followersPanel() {
		mainFollowersPanel.removeAll();
		try {
			followersPanel.updateFollowers(engine.getFollowers());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		mainFollowersPanel.add(followersPanel.getFollowersPanel());
//		mainFollowersPanel.add(trendsView.getTrendsPanel());
		validate();
		mainFollowersPanel.revalidate();
	}
	
	/*********************************************************
	 * Creates a Panel to display the current Trends
	 ********************************************************/
	public class TrendsPanel extends JPanel {
		   public TrendsPanel(String [] t) throws TwitterException 
		   { 
		      super(new FlowLayout());
		      JLabel trendsLabel = new JLabel("Trends");
		      JList trendsList = new JList(t);
		      trendsList.setSize(new Dimension(200, 200));
		      this.add(trendsLabel);
		      this.add(trendsList); 
		   } 
		   /*********************************************************
			 * returns the Trends Panel
			 * @return JPanel
			 ********************************************************/
		   public JPanel getTrendsPanel(){
			   return this;
		   }
		}
	
	/*********************************************************
	 * SignOut and account and exits
	 ********************************************************/
	public void signOut(){
		Accounts login = new Accounts();
		this.dispose();
		//Multiple Accounts process
	}
	
	/**************************************************
	 * Direct Message dialog prompt.
	 *************************************************/
	public class DirectMsgDialog extends JDialog implements ActionListener{

		/** Sending Button */
		private JButton okButton;
		/** Cancel Button */
		private JButton cancelButton;
		/** List of users to send a message to*/
		private JComboBox followersComboBox;
		/** TextField where the message is written */
		private JTextField messageTextField;
		/**************************************************
		 * Direct Message dialog prompt.
		 * @param List<Users>
		 *************************************************/
		public DirectMsgDialog(List<User> u) {
			setModalityType(DEFAULT_MODALITY_TYPE);
			this.setTitle("Direct Message");
			setLayout(new FlowLayout());
	        setSize(new Dimension(620, 100));
	        setPreferredSize(new Dimension(480, 100));
	        setLocationRelativeTo(null);
	        
	        messageTextField = new JTextField();
	        messageTextField.setSize(new Dimension(460, 20));
	        messageTextField.setPreferredSize(new Dimension(460, 20));
	        okButton = new JButton("Send");
	        okButton.addActionListener(this);
	        cancelButton = new JButton("Cancel");
	        
	        followersComboBox = new JComboBox();
	            String[] followerArr = new String[u.size()];
	            for (int i = 0; i < u.size(); i++) {
	                followerArr[i] = u.get(i).getScreenName();
	            }
	            followersComboBox = new JComboBox(followerArr);

	        add(messageTextField);
	        add(followersComboBox);
	        add(okButton);
	        add(cancelButton);
	        cancelButton.addActionListener(this);
	        setVisible(true);
	         
		}

		/******************************************************
		 * Sending actiona.
		 * @param e the ActionEvent
		 *****************************************************/
		@Override
		public final void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton){
	             engine.sendMessage(followersComboBox.getSelectedItem().toString(), 
	                  messageTextField.getText());
	             JOptionPane.showMessageDialog(this, "Message Sent Successfully");
	             messageTextField.setText("");
	             this.dispose();
			}
			else if (e.getSource() == cancelButton)
				this.dispose();
		}

	}

	
	
	/**************************************************
	 * Direct Message dialog prompt.
	 *************************************************/
	public class ProfileEditor extends JDialog implements ActionListener{

		/** ok Button */
		private JButton okButton;
		
		/** Cancel Button */
		private JButton cancelButton;

		/** TextField where the message is written */
		private JTextField messageTextField;
		/**************************************************
		 * Direct Message dialog prompt.
		 * @param List<Users>
		 *************************************************/
		public ProfileEditor() {
			setModalityType(DEFAULT_MODALITY_TYPE);
			this.setTitle("Profile");
			setLayout(new FlowLayout());
	        setSize(new Dimension(400, 400));
	        setPreferredSize(new Dimension(400, 100));
	        setLocationRelativeTo(null);
	        setLayout(new MigLayout("wrap 2"));
	        
	        
	        okButton = new JButton("Send");
	        okButton.addActionListener(this);
	        cancelButton = new JButton("Cancel");

	        add(messageTextField);
	        add(okButton);
	        add(cancelButton);
	        cancelButton.addActionListener(this);
	        setVisible(true);
	         
		}

		/******************************************************
		 * Sending actiona.
		 * @param e the ActionEvent
		 *****************************************************/
		@Override
		public final void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton){
	             
	             this.dispose();
			}
			else if (e.getSource() == cancelButton)
				this.dispose();
		}

	}
	
	/**************************************************
	 * Search Results window
	 *************************************************/
	public class TweetSearchR extends JDialog{

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
		public TweetSearchR(final List<Status> l) throws
		IllegalStateException, TwitterException, MalformedURLException {
			jPanel1 = new JPanel();
			this.setResizable(false);
			jPanel1.setBackground(new Color(255, 255, 255));
			jPanel1.setBorder(BorderFactory.createLineBorder(
					new Color(204, 204, 204)));
			this.statusList = l;
			this.setSize(427, 450);
			TweetPanel[] a = new TweetPanel[statusList.size()];
			jPanel1.setLayout(new MigLayout("wrap 1", "0 [] 0", " 0 [] 0"));
			for (int i = 0; i < statusList.size(); i++) {
				a[i] = new TweetPanel(statusList.get(i));
				jPanel1.add(a[i]);
			}

			JScrollPane scrollPane = new JScrollPane(jPanel1,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setMaximumSize(new Dimension(427, 420));
			getContentPane().setLayout(new MigLayout("wrap 1", "0 [] 0", ""));
			getContentPane().add(scrollPane, "dock north");
			 setVisible(true);
		}

	}
	
	public class UserSearchR extends JDialog{

		/** JPanel to hold the followrs panel */
		private JPanel jPanel1;
		
		/** List of Users */
	    private List<User> followers;
	    
		/** the avatar URL */
	    private URL imageUrl;
	    
		/** Followers Panel width */
	    private int panelWidth = 250;
	    
		/** Sending Button */
	    private JLabel followerName;
	    
		/** Label holding the avatar */
	    private JLabel followerImg;
	    
		/** Panel holding the list */
	    private JPanel followerPanel;
	    
		/** Followers Name Label */
	    private JLabel followerTitleLabel;
	    
		/** Scroll Panel in case needed */
	    private JScrollPane followerJScrollPane;
	    
	    public UserSearchR(List<User> l) throws TwitterException {
	    	this.setSize(290, 400);
	    	this.setPreferredSize(this.getSize());
	    	followers = l;
	    	updateFollowers(followers);
	    }
	    public void updateFollowers(List<User> users){
	    	this.setSize(290, 400);
	    	this.setPreferredSize(this.getSize());
	    	this.followers = users;
	        followerPanel = new JPanel();
	        followerPanel.setLayout(new MigLayout("wrap 1"));
	        getContentPane().setLayout(new MigLayout("wrap 1"));

	        
	        JLabel followersT = new JLabel("Users!");
	        getContentPane().add(followersT);
	        for (User u : followers) {
	            try {
					imageUrl = new URL(u.getProfileImageURL());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}

	            followerName = new JLabel(u.getName());

	            followerImg = new JLabel(new ImageIcon(imageUrl));
	            followerImg.setSize(50, 50);
	            followerImg.setPreferredSize(new Dimension(50, 50));
	            followerImg.setMaximumSize(new Dimension(50, 50));
	            JPanel thisFollowerPanel = new JPanel(new MigLayout("wrap 2"));
	            thisFollowerPanel.add(followerImg);
	            thisFollowerPanel.add(followerName);
	            followerPanel.add(thisFollowerPanel);
	        }
	        followerJScrollPane = new JScrollPane(followerPanel,
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        getContentPane().add(followerJScrollPane);
	        this.setVisible(true);
	        this.pack();
	    }
	}
	
	
	public class ProFileEd extends JDialog implements ActionListener{

		private final JPanel contentPanel = new JPanel();
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JButton okButton;
		private JButton cancelButton;


		/**
		 * Create the dialog.
		 */
		public ProFileEd() {
			setBounds(100, 100, 450, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][grow]"));
			{
				JLabel lblNewLabel_4 = new JLabel("Photo");
				contentPanel.add(lblNewLabel_4, "cell 1 0 2 2");
			}
			{
				JCheckBox chckbxNewCheckBox = new JCheckBox("Remove Photo");
				contentPanel.add(chckbxNewCheckBox, "flowx,cell 3 1");
			}
			{
				JLabel lblNewLabel = new JLabel("Name");
				contentPanel.add(lblNewLabel, "cell 1 2");
			}
			{
				textField = new JTextField();
				contentPanel.add(textField, "cell 3 2,growx");
				textField.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Location");
				contentPanel.add(lblNewLabel_1, "cell 1 3");
			}
			{
				textField_1 = new JTextField();
				contentPanel.add(textField_1, "cell 3 3,growx");
				textField_1.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Website");
				contentPanel.add(lblNewLabel_2, "cell 1 4");
			}
			{
				textField_2 = new JTextField();
				contentPanel.add(textField_2, "cell 3 4,growx");
				textField_2.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Bio");
				contentPanel.add(lblNewLabel_3, "cell 1 5");
			}
			{
				JButton btnNewButton = new JButton("Upload a photo");
				contentPanel.add(btnNewButton, "cell 3 1");
			}
			{
				JTextPane textPane = new JTextPane();
				contentPanel.add(textPane, "cell 3 5,grow");
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					okButton = new JButton("OK");
					okButton.addActionListener(this);
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(this);
					buttonPane.add(cancelButton);
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == okButton){
				//engine.updateProf(name, url, locat, textPane)
			}
			
			if (e.getSource()== cancelButton)
				this.dispose();
		}

	}
	
	
	

}

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.List;

public class TwitterAppGUI extends JFrame implements ActionListener {
	//Main Client Components
	private TwitterEngine twitterEngine;
	private JTextArea timeLine;
	private JTextArea tweetText;
	private JButton tweetButton;
	private JButton getTimeLineButton;
	private Twitter twitter;
	private List<Status> statuses;
	private JPanel mainClient;
	private LoginDialog x;
	private JButton refreshButton;
	private JPanel tweetPanel;
	private JPanel timeLinePanel;
	public boolean login = false;
	//private TweetsTimeLine tweetsTimeLine;
	private UsersTimeLine usersTimeLine;


	public TwitterAppGUI (Twitter t) throws TwitterException, IllegalStateException, MalformedURLException {	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 700);
		this.setMaximumSize(new Dimension(400, 700));
		//this.setPreferredSize(getSize());
		//this.setMaximizedBounds(new Rectangle(450, 800));
		this.setResizable(false);
		setTitle("Twitter");
		this.twitter = t;
		//tweetsTimeLine = new TweetsTimeLine(twitter);

		usersTimeLine = new UsersTimeLine(twitter);
		//timeLinePanel = new JPanel(new BorderLayout());
		setLayout(new MigLayout("wrap 1"));
		tweetPanel();
		timeLinePanel();

		refreshButton = new JButton("Resfresh");
		refreshButton.addActionListener(this);
		this.add(refreshButton);
		this.pack();
		setVisible(true);

	}
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

	public void timeLinePanel(){
		timeLinePanel = new JPanel();
		try {
			usersTimeLine.updatePanel();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeLinePanel.add(usersTimeLine.getTimeLinePanel());
		getContentPane().add(timeLinePanel);
		validate();

	}

	public void timeLinePanelRefresh(){
		timeLinePanel.removeAll();

		try {
			usersTimeLine.updatePanel();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeLinePanel.add(usersTimeLine.getTimeLinePanel());
		//timeLinePanel.validate();
		timeLinePanel.revalidate();
		//timeLinePanel.invalidate();
		//timeLinePanel.repaint();
		//validate();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tweetButton){
			try {
				Status status = twitter.updateStatus(tweetText.getText());
				tweetText.setText("");
			} catch (TwitterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == refreshButton){
			timeLinePanelRefresh();
		}
	}

}

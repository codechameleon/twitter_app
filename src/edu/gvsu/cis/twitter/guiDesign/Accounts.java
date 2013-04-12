package edu.gvsu.cis.twitter.guiDesign;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import edu.gvsu.cis.twitter.twitterLogic.TweetUtils;
import edu.gvsu.cis.twitter.twitterLogic.TwitterAccounts;
import edu.gvsu.cis.twitter.twitterLogic.TwitterConstants;

public class Accounts extends JFrame {
	
	public Accounts() {
		
		final TwitterAccounts accounts = new TwitterAccounts();
		accounts.loadFile();
		
		final int num = accounts.getSize();
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		
		
		JButton loginAdd = new JButton("Add");
		loginAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (num < 4) {
					LoginDialog login;
					Boolean logState = true;
					Twitter twitter;
					try {
						login = new LoginDialog();
					
						twitter = new TwitterFactory().getInstance();
					
						twitter.setOAuthConsumer(TwitterConstants.CONSUMER_KEY,
								TwitterConstants.CONSUMER_SECRET);
						AccessToken accessToken = login.getAccessToken();
						twitter.setOAuthAccessToken(accessToken);
						twitter.verifyCredentials().getId();
						accounts.addElement(twitter);
						accounts.saveFile();
						//JOptionPane.showMessageDialog(null, "Login Successful!");
						TwitterConstants.selectedAccount = num;
						TweetUtils ut = new TweetUtils(twitter);
						new TwitterAppGUI(ut);
						setVisible(false);
						dispose();
					} catch (TwitterException e1) {
						JOptionPane.showMessageDialog(null, "Unable to Login");
						logState = false;
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Maximum Accounts Available is 3");
				}
			}
		});

		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		JLabel pic_1 = new JLabel("");
		pic_1.setBounds(6, 33, 61, 16);
		panel_3.add(pic_1);
		
		JLabel login_name_1 = new JLabel("");
		login_name_1.setBorder(null);
		login_name_1.setBackground(UIManager.getColor("InternalFrame.background"));
		login_name_1.setBounds(6, 124, 140, 16);
		panel_3.add(login_name_1);
		
		JLabel login_name_0 = new JLabel("");
		login_name_0.setBorder(null);
		login_name_0.setBackground(UIManager.getColor("InternalFrame.background"));
		login_name_0.setBounds(193, 124, 135, 16);
		panel_3.add(login_name_0);
		
		JLabel pic_0 = new JLabel("");
		pic_0.setBounds(202, 33, 61, 16);
		panel_3.add(pic_0);
		
		JLabel pic_2 = new JLabel("");
		pic_2.setBounds(340, 33, 61, 16);
		panel_3.add(pic_2);
		
		JTextField login_name_2 = new JTextField("New label");
		JLabel login_name_2_1 = new JLabel("");
		login_name_2_1.setBorder(null);
		login_name_2_1.setBackground(UIManager.getColor("Button.background"));
		login_name_2_1.setBounds(340, 124, 135, 16);
		panel_3.add(login_name_2_1);
		
		final JRadioButton radioButton0 = new JRadioButton("");
		radioButton0.setSelected(true);
		radioButton0.setBounds(202, 152, 61, 23);
		panel_3.add(radioButton0);
		
		final JRadioButton radioButton1 = new JRadioButton("");
		radioButton1.setBounds(16, 152, 141, 23);
		panel_3.add(radioButton1);
		
		final JRadioButton radioButton2 = new JRadioButton("");
		radioButton2.setBounds(350, 152, 54, 23);
		panel_3.add(radioButton2);
		setSize(481,250);
		
		final ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton0);
		

		// Show number of accounts
		if (num == 1) {
			login_name_2.setVisible(false);
			login_name_1.setVisible(false);
			pic_2.setVisible(false);
			pic_1.setVisible(false);
			radioButton1.setVisible(false);
			radioButton2.setVisible(false);
			
			login_name_0.setText(accounts.getName(0));
			URL img0;
			img0 = accounts.getPicURL(0);
			pic_0.setIcon(new ImageIcon(img0));
			pic_0.setSize(100, 100);
			
		} else if (num == 2) {
			login_name_2.setVisible(false);
			login_name_1.setVisible(true);
			login_name_0.setVisible(true);
			pic_2.setVisible(false);
			pic_1.setVisible(true);
			pic_0.setVisible(true);
			radioButton1.setVisible(true);
			radioButton2.setVisible(false);
			
			login_name_0.setText(accounts.getName(0));
			URL img0;
			img0 = accounts.getPicURL(0);
			pic_0.setIcon(new ImageIcon(img0));
			pic_0.setSize(100, 100);
			
			login_name_1.setText(accounts.getName(1));
			URL img1;
			img1 = accounts.getPicURL(1);
			pic_1.setIcon(new ImageIcon(img1));
			pic_1.setSize(100,100);
		} else {
			login_name_2.setVisible(true);
			login_name_1.setVisible(true);
			pic_2.setVisible(true);
			pic_1.setVisible(true);
			radioButton1.setVisible(true);
			
			login_name_0.setText(accounts.getName(0));
			URL img0;
			img0 = accounts.getPicURL(0);
			pic_0.setIcon(new ImageIcon(img0));
			pic_0.setSize(100, 100);
			
			login_name_1.setText(accounts.getName(1));
			URL img1;
			img1 = accounts.getPicURL(1);
			pic_1.setIcon(new ImageIcon(img1));
			pic_1.setSize(100,100);
			
			login_name_2.setText(accounts.getName(2));
			URL img2;
			img2 = accounts.getPicURL(2);
			pic_2.setIcon(new ImageIcon(img2));
			pic_2.setSize(100,100);
		}
		
		JButton loginDelete = new JButton("Delete");
		loginDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (radioButton0.isSelected()) {
					TwitterConstants.selectedAccount = 0;
				} else if (radioButton1.isSelected()) {
					TwitterConstants.selectedAccount = 1;
				} else {
					TwitterConstants.selectedAccount = 2;
				}
				
				accounts.removeAccount(TwitterConstants.selectedAccount);
				accounts.saveFile();
				JOptionPane.showMessageDialog(null, "Account removed from Application!");
				accounts.loadFile();
				if (accounts.getSize() > 0) {
					
					new Accounts();
					dispose();
				} else {
					System.exit(0);
				}
			}
		});
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (radioButton0.isSelected()) {
					TwitterConstants.selectedAccount = 0;
				} else if (radioButton1.isSelected()) {
					TwitterConstants.selectedAccount = 1;
				} else {
					TwitterConstants.selectedAccount = 2;
				}
				
				Twitter twitter = accounts.getAccountAt(TwitterConstants.selectedAccount);
				TweetUtils ut = new TweetUtils(twitter);
				try {
					new TwitterAppGUI(ut);
					setVisible(false);
				} catch (MalformedURLException e) {
			
					e.printStackTrace();
				} catch (TwitterException e) {
				
					e.printStackTrace();
				}
			}
		});
		panel_1.add(loginButton);
		panel_1.add(loginAdd);
		panel_1.add(loginDelete);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_1.add(btnNewButton);
		setVisible(true);
	}
	
}

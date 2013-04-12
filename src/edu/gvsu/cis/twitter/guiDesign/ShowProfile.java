package edu.gvsu.cis.twitter.guiDesign;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/******************************************************************
* Showing Profile.
******************************************************************/
@SuppressWarnings("serial")
public class ShowProfile extends JFrame {

	/*****************************************************************
	 * Setting up profile.
	 * @param name user name
	 * @param img user image
	 * @param sname user screen name
	 * @param followers users followers
	 * @param following users followings
	 * @param fav users favorites
	 * @param tweets users tweets
	 * @param desc user description
	 * @param location user location
	 ****************************************************************/
	public ShowProfile(final String name, final URL img, final String sname, 
			final int followers, final int following, final int fav, 
			final int tweets, final String desc, final String location) {
		
	
		setSize(400, 600);
		setResizable(false);
		setTitle("My Profile");
		getContentPane().setLayout(null);
		
		JLabel lblMyProfileName = new JLabel("");
		lblMyProfileName.setText(name);
		lblMyProfileName.setBounds(160, 142, 141, 15);
		getContentPane().add(lblMyProfileName);
		
		JLabel lblMyProfileImage = new JLabel(new ImageIcon(img));
		lblMyProfileImage.setBounds(125, 26, 136, 115);
		lblMyProfileImage.setSize(136, 115);
		lblMyProfileImage.setPreferredSize(new Dimension(136, 115));
		lblMyProfileImage.setMaximumSize(new Dimension(136, 115));
		
		getContentPane().add(lblMyProfileImage);
		
		JLabel lblMyProfileScreenName = new JLabel("");
		lblMyProfileScreenName.setText(sname);
		lblMyProfileScreenName.setBounds(160, 169, 141, 15);
		getContentPane().add(lblMyProfileScreenName);
		
		JLabel lblMyProfileFollowers = new JLabel("");
		lblMyProfileFollowers.setText("Number of followers: " + followers);
		lblMyProfileFollowers.setBounds(37, 287, 171, 15);
		getContentPane().add(lblMyProfileFollowers);
		
		JLabel lblMyProfileFollowing = new JLabel("");
		lblMyProfileFollowing.setText("Number of following: " + following);
		lblMyProfileFollowing.setBounds(37, 333, 171, 15);
		getContentPane().add(lblMyProfileFollowing);
		
		JLabel lblMyProfileTweets = new JLabel("");
		lblMyProfileTweets.setText("Number of tweets: " + tweets);
		lblMyProfileTweets.setBounds(37, 382, 171, 15);
		getContentPane().add(lblMyProfileTweets);
		
		JLabel lblMyProfileDescrip = new JLabel("");
		lblMyProfileDescrip.setText(desc);
		lblMyProfileDescrip.setBounds(37, 453, 315, 95);
		getContentPane().add(lblMyProfileDescrip);
		
		JLabel lblMyProfileLoc = new JLabel("");
		lblMyProfileLoc.setText(location);
		lblMyProfileLoc.setBounds(160, 199, 141, 15);
		getContentPane().add(lblMyProfileLoc);
		
		JLabel lblMyProfileFavorites = new JLabel("");
		lblMyProfileFavorites.setText("Number of Favorites: " + fav);
		lblMyProfileFavorites.setBounds(37, 245, 207, 15);
		getContentPane().add(lblMyProfileFavorites);
		setVisible(true);
	
	}
	
}

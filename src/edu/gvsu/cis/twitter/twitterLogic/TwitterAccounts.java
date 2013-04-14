package edu.gvsu.cis.twitter.twitterLogic;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import twitter4j.Twitter;


/*************************************************
 * Accounts for twitter application.
 *
 ************************************************/
public class TwitterAccounts {
	
	/** Arraylist of accounts. */
	private ArrayList<Twitter> accounts;
	
	/** Maps each facebook key to twitter account */
	private Map<Integer, String> fbMap;
	
	/** Maps each facebook key to twitter account */
	private Map<Integer, String> nameMap;
	
	/** Maps each facebook key to twitter account */
	private Map<Integer, URL> picMap;
	/****************************************
	 * Constructor.
	 ***************************************/
	public TwitterAccounts() {
		accounts = new ArrayList<Twitter>();
		fbMap = new HashMap<Integer, String>();
		nameMap = new HashMap<Integer, String>();
		picMap = new HashMap<Integer, URL>();
		
	}
	
	/***************************************
	 * Get account.
	 * @param index account number
	 * @return Twitter
	 **************************************/
	public final Twitter getAccountAt(final int index) {
		return accounts.get(index);
	}
	
	/**************************************
	 * Remove account. 
	 * @param index account number
	 **************************************/
	public final void removeAccount(final int index) {
		accounts.remove(index);
		fbMap.put(index, "");
		//picMap.remove(index);
		//nameMap.remove(index);
		
		if (index == 0) {
			picMap.put(0, picMap.get(1));
			picMap.remove(1);
			nameMap.put(0, nameMap.get(1));
			nameMap.remove(1);
			if (picMap.containsKey(2)) {
				picMap.put(1, picMap.get(2));
				picMap.remove(2);
				nameMap.put(1, nameMap.get(2));
				nameMap.remove(2);
			}
		} else if (index == 1) {
			
			if (picMap.containsKey(2)) {
				picMap.put(1, picMap.get(2));
				picMap.remove(2);
				nameMap.put(1, nameMap.get(2));
				nameMap.remove(2);
			}
			
		}
	}
	
	/****************************************
	 * Add element. 
	 * @param t twitter
	 ***************************************/
	public final void addElement(final Twitter t) {
		try {
		accounts.add(t);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	/*****************************************
	 * Get number of accounts. 
	 * @return accounts.size();
	 ****************************************/
	public final int getSize() {
		return accounts.size();
	}
	
	/******************************************
	 * Update the twitter element for account.
	 * @param t twitter
	 * @param i int
	 ******************************************/
	public final void updateElement(final Twitter t, final int i) {
		
		try {
			accounts.add(i, t);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}	
	}
	
	/********************************************
	 * Saves the file. 
	 *******************************************/
	public final void saveFile() {
		
		try {
			FileOutputStream fos = new FileOutputStream("accounts");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(accounts);
			os.writeObject(fbMap);
			os.writeObject(nameMap);
			os.writeObject(picMap);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*********************************************
	 * File loaded check.
	 * @return boolean
	 ********************************************/
	@SuppressWarnings("unchecked")
	public final boolean loadFile() {
		
		try {
			FileInputStream fis = new FileInputStream("accounts");
			ObjectInputStream is = new ObjectInputStream(fis);
			accounts = (ArrayList<Twitter>) is.readObject();
			fbMap = (HashMap<Integer,String>) is.readObject();
			nameMap = (HashMap<Integer, String>) is.readObject();
			picMap = (HashMap<Integer, URL>) is.readObject();
			is.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/***********************************************
	 * Adds the facebook access key to the account 
	 * @param key
	 * @param index
	 **********************************************/
	public final void addFB(String key, int index) {
		fbMap.put(index, key );
	}
	
	/***********************************************
	 * Gets the facebook key from the account
	 * @param key
	 * @param index
	 **********************************************/
	public final String getFB(int account) {
		return fbMap.get(accounts);
	}
	
	/***********************************************
	 * Sets the name of the account
	 * @param key
	 * @param index
	 **********************************************/
	public final void setName(String name, int index) {
		nameMap.put(index, name );
	}
	
	/***********************************************
	 * Sets the picture url of the account
	 * @param key
	 * @param index
	 **********************************************/
	public final void setPicURl(int account, URL url) {
		picMap.put(account, url);
	}
	
	/***********************************************
	 * Gets the name of the account
	 * @param key
	 * @param index
	 **********************************************/
	public final String getName(int account) {
		return nameMap.get(account);
	}
	
	/***********************************************
	 * Gets the picture url from the account
	 * @param key
	 * @param index
	 **********************************************/
	public final URL getPicURL(int account) {
		return picMap.get(account);
	}
}

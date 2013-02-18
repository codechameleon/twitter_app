package edu.gvsu.cis.twitter.twitterLogic;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import twitter4j.Twitter;


/*************************************************
 * Accounts for twitter application.
 *
 ************************************************/
public class TwitterAccounts {
	
	/** Arraylist of accounts. */
	private ArrayList<Twitter> accounts;
	
	/****************************************
	 * Constructor.
	 ***************************************/
	public TwitterAccounts() {
		accounts = new ArrayList<Twitter>();
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
			is.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}

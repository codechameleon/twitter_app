package TwitterLogic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import twitter4j.Twitter;



	public class TwitterAccounts {
	private ArrayList<Twitter> accounts;
	
	public TwitterAccounts(){
		accounts = new ArrayList<Twitter>();
	}
	
	public Twitter getAccountAt(int index) {
		return accounts.get(index);
	}
	
	public void removeAccount(int index){
		accounts.remove(index);
	}
	
	public void addElement(Twitter t){
		try {
		accounts.add(t);
		}
		catch (Throwable ex){
		}
	}
	
	public int getSize(){
		return accounts.size();
	}
	
	public void updateElement(Twitter t, int i){
		try{
		accounts.add(i,t);
		}
		catch (Throwable ex){
		}	
	}
	
	
	public void saveFile(){
		try{
			FileOutputStream fos = new FileOutputStream("accounts");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(accounts);
			os.close();
		}catch (Exception e){
			//e.printStackTrace();
		}
	}
	
	
	public boolean loadFile(){
		try{
			FileInputStream fis = new FileInputStream("accounts");
			ObjectInputStream is = new ObjectInputStream(fis);
			accounts = (ArrayList<Twitter>) is.readObject();
			is.close();
			return true;
		}catch (Exception e){
			return false;
		}
	}
	
}

package guiDesign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import TwitterLogic.Base;
import TwitterLogic.TweetUtils;
import TwitterLogic.TwitterAccounts;

public class LoginPanel extends JPanel {
	

	public LoginPanel() {
	

	setBackground(new Color(102, 102, 102));
	setLayout(new MigLayout("", "[grow][][grow 50][][grow]", "[grow][][][][grow]"));
	
	JLabel lblLogin_userName = new JLabel("User Name");
	lblLogin_userName.setForeground(new Color(0, 204, 255));
	lblLogin_userName.setAlignmentX(Component.RIGHT_ALIGNMENT);
	add(lblLogin_userName, "cell 1 1,growx");
	
	JTextField login_uNameField = new JTextField();
	add(login_uNameField, "cell 2 1,growx");
	login_uNameField.setColumns(10);
	
	JButton btnSignIn = new JButton("Sign In");
	btnSignIn.setForeground(new Color(0, 204, 255));
	btnSignIn.setBackground(new Color(51, 51, 51));
	btnSignIn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// Login credentials
			Base start = new Base();
			TweetUtils ut = new TweetUtils();
			
			// Add user acount for storage
			TwitterAccounts account = new TwitterAccounts();
		}
	});
	btnSignIn.setVerticalAlignment(SwingConstants.TOP);
	add(btnSignIn, "cell 2 4,growx");
	}
}
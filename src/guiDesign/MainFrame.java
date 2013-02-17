package guiDesign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import twitter4j.TwitterException;

import net.miginfocom.swing.MigLayout;
import TwitterLogic.Base;
import TwitterLogic.TweetUtils;
import TwitterLogic.TwitterAccounts;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

public class MainFrame extends JPanel {

	private JFrame frame;
	private final JPanel panel_main = new JPanel();
	private JTextField login_uNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(0, 0, 0));
		frame.setMinimumSize(new Dimension(600, 500));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel_main.setBackground(new Color(51, 51, 51));
		frame.getContentPane().add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(new BorderLayout(0, 0));
		
		final JPanel panel_sidebar = new JPanel();
		panel_sidebar.setBorder(new LineBorder(new Color(0, 204, 255), 1, true));
		panel_sidebar.setBackground(new Color(51, 51, 51));
		panel_main.add(panel_sidebar, BorderLayout.WEST);
		panel_sidebar.setLayout(new GridLayout(0, 1, 0, 0));
		
		final LoginPanel panel_login = new LoginPanel();
		panel_main.add(panel_login,BorderLayout.CENTER);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_main.removeAll();
				panel_main.add(panel_sidebar,BorderLayout.WEST);
				panel_main.add(panel_login,BorderLayout.CENTER);
				frame.validate();
			}
		});
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblLogin);
		
		JLabel lblblank_9 = new JLabel("");
		panel_sidebar.add(lblblank_9);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblAccount.setForeground(new Color(0, 204, 255));
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblAccount);
		
		JLabel label_blank = new JLabel("");
		label_blank.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_blank.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label_blank);
		
		JLabel lblTimeline = new JLabel("Timeline");
		
		
		lblTimeline.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTimeline.setForeground(new Color(0, 204, 255));
		lblTimeline.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblTimeline);
		
		JLabel lblBlank_2 = new JLabel("");
		lblBlank_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlank_2.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblBlank_2);
		
		JLabel lblMentions = new JLabel("Mentions");
		lblMentions.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblMentions.setForeground(new Color(0, 204, 255));
		lblMentions.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblMentions);
		
		JLabel lblBlank_3 = new JLabel("");
		lblBlank_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlank_3.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblBlank_3);
		
		JLabel lblMesssages = new JLabel("Messages");
		lblMesssages.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblMesssages.setForeground(new Color(0, 204, 255));
		lblMesssages.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblMesssages);
		
		JLabel lblBlank_5 = new JLabel("");
		lblBlank_5.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlank_5.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblBlank_5);
		
		JLabel lblFavorites = new JLabel("Favorites");
		lblFavorites.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblFavorites.setForeground(new Color(0, 204, 255));
		lblFavorites.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblFavorites);
		
		JLabel lblBlank_6 = new JLabel("");
		lblBlank_6.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlank_6.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblBlank_6);
		
		JLabel lblMyTweets = new JLabel("My Tweets");
		lblMyTweets.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblMyTweets.setForeground(new Color(0, 204, 255));
		lblMyTweets.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblMyTweets);
		
		JLabel lblBlank_7 = new JLabel("");
		lblBlank_7.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlank_7.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblBlank_7);
		
		JLabel lblRetweets = new JLabel("Retweets");
		lblRetweets.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblRetweets.setForeground(new Color(0, 204, 255));
		lblRetweets.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblRetweets);
		
		JLabel lblBlank_8 = new JLabel("");
		lblBlank_8.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlank_8.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblBlank_8);
		
		JLabel lblPhotos = new JLabel("Photos");
		lblPhotos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblPhotos.setForeground(new Color(0, 204, 255));
		lblPhotos.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblPhotos);
		
		JLabel lblBlank_9 = new JLabel("");
		lblBlank_9.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBlank_9.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblBlank_9);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(0, 204, 255), 1, true));
		menuBar.setBackground(new Color(51, 51, 51));
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setForeground(new Color(0, 204, 255));
		menuBar.add(mnNewMenu);
		
		JMenu mnSettings = new JMenu("Settings");
		mnSettings.setForeground(new Color(0, 204, 255));
		menuBar.add(mnSettings);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setForeground(new Color(0, 204, 255));
		menuBar.add(mnHelp);
	
	}
}

package guiDesign;

import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JInternalFrame;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPasswordField;
import java.awt.Panel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Testlogin extends JPanel {

	private JFrame frame;
	private final JPanel panel_main = new JPanel();
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Testlogin window = new Testlogin();
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
	public Testlogin() {
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
		
		JPanel panel_sidebar = new JPanel();
		panel_sidebar.setBorder(new LineBorder(new Color(0, 204, 255), 1, true));
		panel_sidebar.setBackground(new Color(51, 51, 51));
		panel_main.add(panel_sidebar, BorderLayout.WEST);
		panel_sidebar.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Accounts");
		lblNewLabel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_1.setForeground(new Color(0, 204, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label);
		
		JLabel lblNewLabel = new JLabel("Timeline");
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel.setForeground(new Color(0, 204, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("");
		label_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_1.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mentions");
		lblNewLabel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_2.setForeground(new Color(0, 204, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("");
		label_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_2.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label_2);
		
		JLabel lblNewLabel_4 = new JLabel("Messages");
		lblNewLabel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_4.setForeground(new Color(0, 204, 255));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblNewLabel_4);
		
		JLabel label_3 = new JLabel("");
		label_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_3.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label_3);
		
		JLabel lblNewLabel_5 = new JLabel("Favorites");
		lblNewLabel_5.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_5.setForeground(new Color(0, 204, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblNewLabel_5);
		
		JLabel label_4 = new JLabel("");
		label_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_4.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label_4);
		
		JLabel lblMyTweets = new JLabel("My Tweets");
		lblMyTweets.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblMyTweets.setForeground(new Color(0, 204, 255));
		lblMyTweets.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblMyTweets);
		
		JLabel label_5 = new JLabel("");
		label_5.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_5.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label_5);
		
		JLabel lblNewLabel_6 = new JLabel("Retweets");
		lblNewLabel_6.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_6.setForeground(new Color(0, 204, 255));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblNewLabel_6);
		
		JLabel label_6 = new JLabel("");
		label_6.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_6.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(label_6);
		
		JLabel lblNewLabel_7 = new JLabel("Photos");
		lblNewLabel_7.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_7.setForeground(new Color(0, 204, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_sidebar.add(lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_3.setForeground(new Color(0, 204, 255));
		panel_sidebar.add(lblNewLabel_3);
		
		JPanel panel_login = new JPanel();
		panel_login.setBackground(new Color(102, 102, 102));
		panel_main.add(panel_login, BorderLayout.CENTER);
		panel_login.setLayout(new MigLayout("", "[grow][][grow 50][][grow]", "[grow][][][][grow]"));
		
		JLabel lblNewLabel_8 = new JLabel("User Name");
		lblNewLabel_8.setForeground(new Color(0, 204, 255));
		lblNewLabel_8.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_login.add(lblNewLabel_8, "cell 1 1,growx");
		
		textField = new JTextField();
		panel_login.add(textField, "cell 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Password");
		lblNewLabel_9.setForeground(new Color(0, 204, 255));
		panel_login.add(lblNewLabel_9, "cell 1 2,alignx trailing");
		
		textField_1 = new JPasswordField();
		panel_login.add(textField_1, "cell 2 2,growx");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setForeground(new Color(0, 204, 255));
		btnNewButton.setBackground(new Color(51, 51, 51));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		panel_login.add(btnNewButton, "cell 2 4,growx");
		
		Panel panel = new Panel();
		panel.setEnabled(false);
		panel.setVisible(false);
		panel_main.add(panel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
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
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		mnNewMenu_1.setForeground(new Color(0, 204, 255));
		menuBar.add(mnNewMenu_1);
		
	
	}
}

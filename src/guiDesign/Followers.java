package guiDesign;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import twitter4j.TwitterException;
import twitter4j.User;


public class Followers extends JPanel {
	URL imageUrl;
    int panelWidth = 250;
    List<User> followers;
    JLabel followerName;
    JLabel followerImg;
    JPanel followerPanel;
    JLabel followerTitleLabel;
    JScrollPane followerJScrollPane;

    public Followers(List<User> l) throws TwitterException {
    	followers = l;
        this.setLayout(new GridLayout(1, followers.size()));

        followerPanel = new JPanel();
        followerPanel.setLayout(new MigLayout("wrap 1"));

        
        int subPanelHeight = 50;
        JLabel followersT = new JLabel("Followers!");
        followerPanel.add(followersT);
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
            followerImg.setPreferredSize(new Dimension(50, subPanelHeight));
            followerImg.setMaximumSize(new Dimension(50, subPanelHeight));
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

}

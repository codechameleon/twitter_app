
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.Status;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.text.SimpleDateFormat;

public class TweetsTimeLine implements ActionListener{


	Twitter twitter;
	JPanel timeLinePanel;
	JPanel updatePanel;
	JButton updateButton;

	public TweetsTimeLine(Twitter twitter) throws TwitterException {
		this.twitter = twitter;
		timeLinePanel = new JPanel();
		//updateField = new JTextField();
//
//		updatePanel = new JPanel(new FlowLayout());
//		updatePanel.setSize(800, 30);
//		updatePanel.setPreferredSize(new Dimension(800, 30));
//		updatePanel.setMaximumSize(new Dimension(800, 30));
//
//		updateButton = new JButton("Update");
//		updateButton.setSize(90, 20);
//		updateButton.setPreferredSize(new Dimension(90, 20));
//		updateButton.setMaximumSize(new Dimension(90, 20));
//		updateButton.addActionListener(this);
//
//		updatePanel.add(updateButton);
//		timeLinePanel.add(updatePanel);
		updateTimePanel();

	}

	public void updateTimePanel() throws TwitterException {
		java.util.List<Status> statusList = twitter.getUserTimeline();
		
		String statusArr[] = new String[statusList.size()];
		timeLinePanel.setLayout(new BoxLayout(timeLinePanel, BoxLayout.Y_AXIS));
		
		for (int i = 0; i < statusList.size(); i++) {
			Date tweetDate = statusList.get(i).getCreatedAt();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm");
			statusArr[i] = formatter.format(tweetDate) + "-" + statusList.get(i).getText() + "//Tweet id =" + statusList.get(i).getId();
		}
		
		JList statusJList = new JList(statusArr);
		statusJList.setFixedCellHeight(20);
		JScrollPane scrollPane = new JScrollPane(statusJList,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, // vertical bar
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		timeLinePanel.add(scrollPane);
	}

	public JPanel getTimeLinePanel() {
		return timeLinePanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			updateTimePanel();
			timeLinePanel.remove(1);
			timeLinePanel.updateUI();
			timeLinePanel.validate();
		} catch (TwitterException exception) {
			JOptionPane.showMessageDialog(null, "An error has occurred while refreshing.");
		}
	}
}

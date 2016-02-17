package userInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LeaderboardUI extends Panel
{
	private JPanel panel = null;
	private PanelManager panelMgr = null;
	private String[] typeOptions = {"Global","By Clan","Your Clan"};
	private String[] sortOptions = {"Ascending Kills","Descending Kills","Ascending Wins","Descending Wins"};
	private String[] columnHeaders = {"Username","Kills","Deaths","Wins","Losses"};
	private ArrayList<String[]> leaderboard = null;
	private DefaultTableModel tableModel = null;
	private JTable table = null;
	
	public LeaderboardUI()
	{
		showLeaderboard();
	}
	
	public void showLeaderboard()
	{
		JPanel leaderboardPanel = new JPanel();
		BorderLayout leaderboardLayout = new BorderLayout();
		leaderboardPanel.setLayout(leaderboardLayout);
		
		JPanel topPanel = new JPanel();
		FlowLayout topPanelLayout = new FlowLayout();
		topPanelLayout.setAlignment(FlowLayout.RIGHT);
		topPanelLayout.setHgap(20);
		topPanel.setLayout(topPanelLayout);
		final JLabel errorLabel = new JLabel("");
		final JTextField searchClan = new JTextField("",32);
		searchClan.setVisible(false);
		topPanel.add(searchClan);
		final JButton searchClanButton = new JButton("Search");
		
		searchClanButton.setVisible(false);
		topPanel.add(searchClanButton);
		final JComboBox filterLeaderboardType = new JComboBox(typeOptions);
		topPanel.add(filterLeaderboardType);
		final JComboBox filterLeaderboardSort = new JComboBox(sortOptions);
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				/* Filter by clan entered by user */
				if(filterLeaderboardType.getSelectedIndex() == 1)
				{
					searchClan.setVisible(true);
					searchClanButton.setVisible(true);
				}
				else
				{
					searchClan.setVisible(false);
					searchClanButton.setVisible(false);
					int type = filterLeaderboardType.getSelectedIndex();
					int sort = filterLeaderboardSort.getSelectedIndex();
					
					/* Get global leaderboard */
					if(type == 0)
					{
						leaderboard = processInput.getLeaderboards(0, sort);
					}
					/* Get user clan leaderboard */
					else
					{
						/* User is in clan */
						if(processInput.getPlayerClanID() != 0)
						{
							leaderboard = processInput.getLeaderboards(1, sort);
						}
						/* User is not in clan */
						else
						{
							errorLabel.setText("Not registered in clan");
						}
					}
				}
			}
		});
		
		searchClanButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String clanName = searchClan.getText().toLowerCase();
				int doesClanExist = processInput.doesClanExist(clanName);
				int sort = filterLeaderboardSort.getSelectedIndex();
				if(doesClanExist != 0)
				{
					leaderboard = processInput.getLeaderboards(doesClanExist,sort);
				}
				else
				{
					errorLabel.setText("Clan does not exist");
				}
			}
		});
		
		topPanel.add(refreshButton);
		JButton exitLeaderboardButton = new JButton("Exit To Menu");
		exitLeaderboardButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelMgr.getPanelFromFactory(2);
			}
		});
		topPanel.add(exitLeaderboardButton);
		leaderboardPanel.add(topPanel,leaderboardLayout.NORTH);
		
		
		JPanel mainLeaderboardPanel = new JPanel();
		
		tableModel = new DefaultTableModel(columnHeaders,0);
		table = new JTable(tableModel);
		
		leaderboardPanel.add(mainLeaderboardPanel,leaderboardLayout.CENTER);
		this.panel = leaderboardPanel;
	}
	
	@Override
	public JPanel sendToWindow() {
		return this.panel;
	}

	@Override
	public void setPanelManager(PanelManager panelMgr) {
		this.panelMgr = panelMgr;
	}
}

package userInterface;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuUI extends Panel
{
	private JPanel panel = null;
	private PanelManager panelMgr = null;
	
	
	public MenuUI()
	{
		showMainMenu();
	}
	
	public void showMainMenu()
	{
		JPanel mainMenuPanel = new JPanel();
		BorderLayout mainMenuLayout = new BorderLayout();
		mainMenuPanel.setLayout(mainMenuLayout);
		
		JPanel topBarPanel = new JPanel();
		FlowLayout topBarLayout = new FlowLayout();
		topBarLayout.setAlignment(FlowLayout.RIGHT);
		topBarPanel.setLayout(topBarLayout);
		JLabel welcomeLabel = new JLabel("Welcome " + processInput.getPlayerName());
		topBarPanel.add(welcomeLabel);
		JLabel spacer = new JLabel("          ");
		topBarPanel.add(spacer);
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processInput.logPlayerOut();
				panelMgr.getPanelFromFactory(1);
				
			}
		});
		topBarPanel.add(logoutButton);
		mainMenuPanel.add(topBarPanel,mainMenuLayout.NORTH);
		
		JPanel centerMenuPanel = new JPanel();
		BorderLayout centerMenuLayout = new BorderLayout();
		centerMenuPanel.setLayout(centerMenuLayout);
		centerMenuPanel.setBackground(Color.blue);
		
		JPanel centerMenuButtonsPanel = new JPanel();								
		GridLayout centerMenuButtonsLayout = new GridLayout(3,3);
		centerMenuButtonsPanel.setLayout(centerMenuButtonsLayout);
		JButton joinGameButton = new JButton("Join Game");
		joinGameButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//panelMgr.getPanelFromFactory(4);		/* Nothing returned from server as of yet so not implemented */
				processInput.sendPlayerToMatchMakingService();
				JOptionPane.showMessageDialog(null, "Player send to matchmaking server");
			}
		});
		centerMenuButtonsPanel.add(joinGameButton);
		JButton invitesButton = new JButton("Invites");
		invitesButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Component not integrated");
			}
		});
		centerMenuButtonsPanel.add(invitesButton);
		JButton leaderboardsButton = new JButton("LeaderBoards");
		leaderboardsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panelMgr.getPanelFromFactory(5);
			}
		});
		centerMenuButtonsPanel.add(leaderboardsButton);
		JButton chatButton = new JButton("Chat");
		chatButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null, "Component not integrated");
			}
		});
		centerMenuButtonsPanel.add(chatButton);
		JButton clanMgmtButton = new JButton("Clan");
		clanMgmtButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelMgr.getPanelFromFactory(3);
			}
		});
		centerMenuButtonsPanel.add(clanMgmtButton);
		centerMenuPanel.add(centerMenuButtonsPanel,centerMenuLayout.CENTER);		
		
		JPanel centerMenuRightPanel = new JPanel();
		centerMenuRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JTextArea newsArea = new JTextArea();
		newsArea.setBackground(Color.white);
		newsArea.setText("News Area");
		newsArea.setPreferredSize(new Dimension(300,centerMenuRightPanel.getHeight()));		
		centerMenuRightPanel.add(newsArea);
		newsArea.setEditable(false);
		centerMenuPanel.add(centerMenuRightPanel,centerMenuLayout.EAST);
		
		mainMenuPanel.add(centerMenuPanel,mainMenuLayout.CENTER);
		
		this.panel = mainMenuPanel;
		
	}
	
	@Override
	public JPanel sendToWindow()
	{ 
		return this.panel;
	}
	
	@Override
	public void setPanelManager(PanelManager panelMgr)
	{
		this.panelMgr = panelMgr;
	}
	
}

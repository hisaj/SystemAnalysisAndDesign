package userInterface;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClanUI extends Panel
{
	private PanelManager panelMgr = null;		
	private JPanel panel = null;
	
	public ClanUI()
	{
		
		if(processInput == null)
		{
			processInput = processInput.getInstance();		
		}
		showClanMenu();
	}
	
	public void showClanMenu()
	{
		JPanel mainClanMenuPanel = new JPanel();
		BorderLayout clanMenuLayout = new BorderLayout();
		mainClanMenuPanel.setLayout(clanMenuLayout);
		
		JPanel clanTopPanel = new JPanel();
		FlowLayout topPanelLayout = new FlowLayout();
		topPanelLayout.setAlignment(FlowLayout.RIGHT);
		clanTopPanel.setLayout(topPanelLayout);
		JLabel clanName = new JLabel("Clan: " + processInput.getPlayerClanName());
		clanTopPanel.add(clanName);
		JLabel spacer = new JLabel("        ");
		clanTopPanel.add(spacer);
		JLabel playerRank = new JLabel("Rank: " + processInput.getPlayerRank());
		clanTopPanel.add(playerRank);
		clanTopPanel.add(spacer);
		JButton exitClanButton = new JButton("Exit To Menu");
		exitClanButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				panelMgr.getPanelFromFactory(2);
			}
		});
		clanTopPanel.add(exitClanButton);
		
		mainClanMenuPanel.add(clanTopPanel,clanMenuLayout.NORTH);
		
		JPanel clanCenterPanel = new JPanel();
		GridLayout clanCenterPanelLayout = new GridLayout(0,2);
		clanCenterPanel.setLayout(clanCenterPanelLayout);
		
		JButton createClanButton = new JButton("Create Clan");
		createClanButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Object acceptWarning = JOptionPane.showConfirmDialog(null,"You will be removed from current clan","Warning",JOptionPane.WARNING_MESSAGE);
				/* User clicked yes */
				if(acceptWarning.toString().equals("0"))
				{
					createClanMenu();
				}
			}
		});
		clanCenterPanel.add(createClanButton);
		
		JButton addClanMemberButton = new JButton("Add Clan Member");
		addClanMemberButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(processInput.getPlayerRank() == 1)
				{
					Object acceptWarning = JOptionPane.showConfirmDialog(null, "Player will be removed from current clan","Warning",JOptionPane.WARNING_MESSAGE);
					if(acceptWarning.toString().equals("0"))
					{
						addClanMemberMenu();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Cannot add members with current rank","Error",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		clanCenterPanel.add(addClanMemberButton);
		
		mainClanMenuPanel.add(clanCenterPanel,clanMenuLayout.CENTER);
		
		this.panel = mainClanMenuPanel;
	}
	
	@Override
	public JPanel sendToWindow()
	{
		panel = this.panel;		
		return panel;
	}
	
	@Override
	public void setPanelManager(PanelManager panelMgr)
	{
		this.panelMgr = panelMgr;
	}
	
	private void createClanMenu()
	{
		final JFrame newClanPopUp = new JFrame("New Clan");
		newClanPopUp.setSize(300, 350);
		newClanPopUp.setLocationRelativeTo(null);
		newClanPopUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		final JPanel newClanPanel = new JPanel();
		GridLayout newClanLayout = new GridLayout(0,2);
		newClanPanel.setLayout(newClanLayout);
		
		final JLabel newClanErrorLabel = new JLabel("");
		newClanErrorLabel.setForeground(Color.red);
		newClanPanel.add(newClanErrorLabel);
		
		final JLabel newClanBlankLabel = new JLabel("");
		newClanPanel.add(newClanBlankLabel);
		
		final JLabel newClanClanNameLabel = new JLabel("Clan Name: ");
		newClanPanel.add(newClanClanNameLabel);
		
		final JTextField newClanGetClanName = new JTextField();
		newClanPanel.add(newClanGetClanName);
		
		final JLabel newClanRankOneLabel = new JLabel("Rank 1 Name: ");
		newClanPanel.add(newClanRankOneLabel);
		
		final JTextField newClanGetRankOne = new JTextField();
		newClanPanel.add(newClanGetRankOne);
		
		final JLabel newClanRankTwoLabel = new JLabel("Rank 2 Name: ");
		newClanPanel.add(newClanRankTwoLabel);
		
		final JTextField newClanGetRankTwo = new JTextField();
		newClanPanel.add(newClanGetRankTwo);
		
		final JLabel newClanRankThreeLabel = new JLabel("Rank 3 Name: ");
		newClanPanel.add(newClanRankThreeLabel);
		
		final JTextField newClanGetRankThree = new JTextField();
		newClanPanel.add(newClanGetRankThree);
		
		final JLabel newClanRankFourLabel = new JLabel("Rank 4 Name: ");
		newClanPanel.add(newClanRankFourLabel);
		
		final JTextField newClanGetRankFour = new JTextField();
		newClanPanel.add(newClanGetRankFour);
		
		final JButton newClanCreateClanButton = new JButton("Create");
		newClanCreateClanButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					String clanName = newClanGetClanName.getText().toString().toLowerCase();
					String rankOne = newClanGetRankOne.getText().toString().toLowerCase();
					String rankTwo = newClanGetRankTwo.getText().toString().toLowerCase();
					String rankThree = newClanGetRankThree.getText().toString().toLowerCase();
					String rankFour = newClanGetRankFour.getText().toString().toLowerCase();
					
					if(!clanName.equals("") && !rankOne.equals("") && !rankTwo.equals("") && !rankThree.equals("") && !rankFour.equals(""))
					{
						if(processInput.createNewClan(clanName, rankOne, rankTwo, rankThree, rankFour))
						{
							JOptionPane.showMessageDialog(null, "Clan Created!");
							newClanPopUp.setVisible(false);
						}
						else
						{
							newClanErrorLabel.setText("Clan exists");
						}
					}
					else
					{
						newClanErrorLabel.setText("Fields Blank");
					}
			}
		});
		newClanPanel.add(newClanCreateClanButton);
		
		final JButton newClanCancelCreateButton = new JButton("Cancel");
		newClanCancelCreateButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newClanPopUp.dispose();
			}
		});
		newClanPanel.add(newClanCancelCreateButton);
	
		newClanPopUp.add(newClanPanel);
		newClanPopUp.setVisible(true);
	}
	
	private void addClanMemberMenu()
	{
		final String[] ranksArray = { "1","2","3","4" };
		final JFrame newMemberFrame = new JFrame("Add new member");
		newMemberFrame.setSize(200,150);
		newMemberFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		newMemberFrame.setLocationRelativeTo(null);
		
		final JPanel newMemberPanel = new JPanel();
		GridLayout newMemberLayout = new GridLayout(0,2);
		newMemberPanel.setLayout(newMemberLayout);
		
		final JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.red);
		newMemberPanel.add(errorLabel);
		
		final JLabel blankLabel = new JLabel("");
		newMemberPanel.add(blankLabel);
		
		final JLabel newMemberUsernameLabel = new JLabel("Username: ");
		newMemberPanel.add(newMemberUsernameLabel);
		
		final JTextField newMemberGetUsername = new JTextField();
		newMemberPanel.add(newMemberGetUsername);
		
		final JLabel newMemberRankLabel = new JLabel("Rank: ");
		newMemberPanel.add(newMemberRankLabel);
		
		final JComboBox ranks = new JComboBox(ranksArray);
		newMemberPanel.add(ranks);
		
		final JButton addNewMemberButton = new JButton("Add");
		addNewMemberButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					String username = newMemberGetUsername.getText().toString().toLowerCase();
					int rank = Integer.parseInt(ranks.getItemAt(ranks.getSelectedIndex()).toString());
					if(!username.equals(""))
					{
						if(processInput.doesPlayerExist(username))
						{
							processInput.addPlayerToClan(username, rank);
							JOptionPane.showMessageDialog(null, "User added to clan","Success",JOptionPane.INFORMATION_MESSAGE);
							newMemberFrame.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"User does not exist","Error",JOptionPane.ERROR_MESSAGE);
							newMemberGetUsername.setText("");
						}
					}
					else
					{
						errorLabel.setText("Field blank");
					}
			}
		});
		newMemberPanel.add(addNewMemberButton);
		
		final JButton cancelAddNewMemberButton = new JButton("Cancel");
		cancelAddNewMemberButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					newMemberFrame.dispose();
			}
		});
		newMemberPanel.add(cancelAddNewMemberButton);
		
		newMemberFrame.add(newMemberPanel);
		newMemberFrame.setVisible(true);
	}
}

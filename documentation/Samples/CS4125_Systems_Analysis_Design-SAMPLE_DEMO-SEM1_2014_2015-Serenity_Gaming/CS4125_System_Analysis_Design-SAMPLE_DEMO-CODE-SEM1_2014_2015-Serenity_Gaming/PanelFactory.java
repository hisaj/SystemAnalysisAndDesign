package userInterface;
import javax.swing.JPanel;

public class PanelFactory 
{
	int SCREEN_WIDTH = 0;		
	int SCREEN_HEIGHT = 0;
	
	private ProcessInput processInput = null;
	
	public PanelFactory()
	{
		//Default constructor
	}
	
	public PanelFactory(ProcessInput processInput)
	{
		this.processInput = processInput;	
	}
	
	public Panel getPanel(int panelID,PanelManager panelMgr)
	{
		Panel panel = null;		
		
		switch(panelID)
		{
		/* Login Menu */
		case 1:
			panel = new HomeUI(this.SCREEN_WIDTH,this.SCREEN_HEIGHT);
			panel.setPanelManager(panelMgr);
			
			break;
		/* Main Menu */
		case 2:
			panel = new MenuUI();
			panel.setPanelManager(panelMgr);
			
			break;
		/* Clan Menu */
		case 3:
			panel = new ClanUI();
			panel.setPanelManager(panelMgr);
			
			break;
		/* Join Game Menu */
		case 4:
			panel = new JoinGameUI();
			panel.setPanelManager(panelMgr);
			
			break;
		/* Show Leaderboards */
		case 5:
			panel = new LeaderboardUI();
			panel.setPanelManager(panelMgr);
			
			break;
		}
		panel.setProcessInput(processInput);
		panel.setPanelManager(panelMgr);
		return panel;
	}
	
	public void setScreenWidth(int SCREEN_WIDTH)
	{
		this.SCREEN_WIDTH = SCREEN_WIDTH;
	}
	
	public void setScreenHeight(int SCREEN_HEIGHT)
	{
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
	}
}

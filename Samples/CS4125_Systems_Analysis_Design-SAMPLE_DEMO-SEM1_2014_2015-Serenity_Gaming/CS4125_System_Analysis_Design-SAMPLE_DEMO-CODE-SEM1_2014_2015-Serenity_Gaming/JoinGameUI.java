package userInterface;

import java.awt.*;
import javax.swing.*;

public class JoinGameUI extends Panel 
{

	private JPanel panel = null;
	private PanelManager panelMgr = null;
	
	public JoinGameUI()
	{
		showJoinGameScreen();
	}
	
	public void showJoinGameScreen()
	{
		JPanel joinGameMenu = new JPanel();
		
		
		
		this.panel = joinGameMenu;
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

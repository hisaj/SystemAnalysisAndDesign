package userInterface;

import java.util.*;
import javax.swing.*;

/*This implements the Observer Design Pattern with this being the Subject and Panel being the observer*/

public class PanelManager implements Subject 
{
	private PanelFactory panelFac;		
	private Window window;
	private Panel currentPanel;
	
	public PanelManager(PanelFactory panelFac)
	{
		this.panelFac = panelFac;		
	}
	
	@Override
	public void registerObserver(Window window) 
	{
		this.window = window;
		panelFac.setScreenHeight(window.getScreenHeight());
		panelFac.setScreenWidth(window.getScreenWidth());
		
		getPanelFromFactory(1);
		notifyObserver();
	}

	@Override
	public void removeObserver() 
	{
		this.window = null;
	}

	@Override
	public void notifyObserver()
	{
		window.update(this);
	}
	
	public void getPanelFromFactory(int panelID)
	{
		this.currentPanel = panelFac.getPanel(panelID,this);
		notifyObserver();
	}
	
	public Panel getCurrentPanel()
	{
		return this.currentPanel;
	}
	
	public void setCurrentPanel(Panel panel)
	{
		this.currentPanel = panel;
	}
	
	
}

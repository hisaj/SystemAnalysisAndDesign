package userInterface;

import javax.swing.*;
import db.*;

public class BootUI 
{	
	
	public BootUI()
	{
		
	}
	
	public void run()
	{
		try
		{
			/* Creates window to display GUI components */
			/* Observer to PanelManager */
			Window window = new Window();
			/* Starts database connection */
			DatabaseInterface dbConn = new DatabaseAccess();
			/* Will process input taken from client GUI */
			ProcessInput processInput = ProcessInput.getInstance();
			processInput.setDbConn(dbConn);
			/* Panel factory to display panels on window */
			PanelFactory panelFac = new PanelFactory(processInput);
			/* Subject in observer pattern */		
			PanelManager panelMgr = new PanelManager(panelFac);
			panelMgr.registerObserver(window);
		}
		catch(Exception e)
		{
			System.out.println("Error bootingUI: " + e.toString());
		}
		
	}
	
}

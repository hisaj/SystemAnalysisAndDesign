package userInterface;

import javax.swing.JPanel;

public abstract class Panel 
{
	protected PanelManager panelMgr;
	protected ProcessInput processInput;
	protected JPanel panel;
	
	public Panel()
	{
		
	}
	
	public abstract JPanel sendToWindow();
	public void setPanelManager(PanelManager panelMgr)
	{
		this.panelMgr = panelMgr;
	}
	public void setProcessInput(ProcessInput processInput)
	{
		this.processInput = processInput;
	}
}


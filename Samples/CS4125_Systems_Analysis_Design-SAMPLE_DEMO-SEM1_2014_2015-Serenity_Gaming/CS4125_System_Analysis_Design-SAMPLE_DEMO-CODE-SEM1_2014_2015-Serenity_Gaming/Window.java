package userInterface;
import javax.swing.*;
import java.awt.*;

/* Implements the observer pattern */
public class Window extends JFrame implements Observer 
{

	private JFrame window = null;
	private JPanel viewCurrentPanel = null;
	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;
	
	public Window()
	{
		initialiseWindow();
	}
	
	/* Gets screen width and height and draws window based on those sizes */
	private void initialiseWindow()
	{
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		SCREEN_WIDTH = toolKit.getScreenSize().width;
		SCREEN_HEIGHT = toolKit.getScreenSize().height;
		window = new JFrame("Game Framework Prototype");
		window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		window.setResizable(false); 									
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/* Updates window by applying new panels to window */
	@Override
	public void update(Subject subject) 
	{
		/* Removes panel on window and applys new panel */
		removeCurrentPanelFromWindow();
		Panel currentPanel = subject.getCurrentPanel();
		
		
		viewCurrentPanel = currentPanel.sendToWindow();
		
		window.add(viewCurrentPanel);
		window.setVisible(true);
	}
	
	@Override
	public int getScreenWidth()
	{
		return this.SCREEN_WIDTH;
	}
	
	@Override
	public int getScreenHeight()
	{
		return this.SCREEN_HEIGHT;
	}
	
	public void removeCurrentPanelFromWindow()
	{
		if(viewCurrentPanel != null)
		{
			this.window.remove(viewCurrentPanel);
			viewCurrentPanel = null;
		}
	}
}

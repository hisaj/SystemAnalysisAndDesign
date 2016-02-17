package userInterface;

import javax.swing.JPanel;

/* Subject interface for observer pattern */
public interface Subject {		
	
	void registerObserver(Window window);
	void removeObserver();
	void notifyObserver();
	Panel getCurrentPanel();
}

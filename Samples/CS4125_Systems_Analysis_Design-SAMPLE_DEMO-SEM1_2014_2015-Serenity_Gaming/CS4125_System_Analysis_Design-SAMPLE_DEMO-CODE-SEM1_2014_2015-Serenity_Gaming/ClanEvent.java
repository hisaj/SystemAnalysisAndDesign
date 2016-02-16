package common;

import java.util.ArrayList;
import java.util.Date;

public class ClanEvent 
{
	private int id;
	private String eventName;
	private Date startDate;//dd-mm-yyyy
	private ArrayList<Integer> playerIDs;
	
	public ClanEvent()
	{
		
	}
	
	

	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public ArrayList<Integer> getPlayerIDs() {
		return playerIDs;
	}

	public void setPlayerIDs(ArrayList<Integer> playerIDs) {
		this.playerIDs = playerIDs;
	}
	
	
}

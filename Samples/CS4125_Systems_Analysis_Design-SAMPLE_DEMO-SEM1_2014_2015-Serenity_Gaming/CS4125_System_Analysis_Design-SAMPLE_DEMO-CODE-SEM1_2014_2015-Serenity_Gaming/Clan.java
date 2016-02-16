package common;
import java.util.ArrayList;

public class Clan 
{
	private int id;
	private String name;
	private ArrayList<Integer> playerIDs;
	private int clanLeaderID;
	private String[] rankNames;//Max 4 ranks.
	
	public int getClanLeaderID() {
		return clanLeaderID;
	}
	public void setClanLeaderID(int clanLeaderID) {
		this.clanLeaderID = clanLeaderID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Integer> getPlayerIDs() {
		return playerIDs;
	}
	public void setPlayerIDs(ArrayList<Integer> playerIDs) {
		this.playerIDs = playerIDs;
	}
	public String[] getRankNames() {
		return rankNames;
	}
	public void setRankNames(String[] rankNames) {
		this.rankNames = rankNames;
	}
}

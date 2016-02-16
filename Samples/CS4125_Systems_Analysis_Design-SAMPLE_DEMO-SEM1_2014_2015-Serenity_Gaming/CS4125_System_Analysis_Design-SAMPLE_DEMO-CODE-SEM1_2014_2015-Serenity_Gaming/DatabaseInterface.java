package db;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SysAnalysis.src.sysAnalysis.CommonClasses.Clan;
import SysAnalysis.src.sysAnalysis.CommonClasses.ClanEvent;
import SysAnalysis.src.sysAnalysis.CommonClasses.Player;

//Program Files\MySQL\MySQL Server 5.6\bin
//mysql -h localhost -u root -r
//password root
// players (playerID, wins, losses, kills, deaths)
//Just have it in its own class with an interface to the class so the client can query for the information but wont have 
//to worry about any sql statements itll just take in whatever parameters that are needed and return the information then


public interface DatabaseInterface {

	
	boolean canLogin(String username, String password) throws Exception;
	
	ArrayList<String[]> getLeaderboardDetails(int playerID, int clanID) throws Exception;
	
	/* References the local Player class */
	String getPlayerDetails(String username) throws Exception;
	
	Clan getClanDetails(int clanID) throws Exception;
	
	String[] getRankNames(int clanID) throws Exception;
	
	ClanEvent getClanEvent(int eventID) throws Exception;
	
	void createEvent(String eventName, Date startDate) throws Exception;
	
	void createPlayer(String username,String email, String password) throws Exception;
	
	int createClan(String clanName,String creatorUsername) throws Exception;
	
	void addPlayerToClan(String username, int clanID,int rank) throws Exception;
	
	void updatePlayerClan(String username,int clanID,int rank) throws Exception;
	
	void removePlayerFromClan(int playerID, int clanID) throws Exception;
	
	void addPlayerToEvent(int playerID, int eventID) throws Exception;

	int checkUserNameAndEmail(String username, String email) throws SQLException;
	
	void updatePlayerName(String name, int playerID) throws SQLException;

	void updatePlayerEmail(String name, int playerID) throws SQLException;
	
	void updatePlayerWins(int wins, int playerID) throws SQLException;
	
	void updatePlayerLosses(int losses, int playerID) throws SQLException;
	
	void updatePlayerKills(int kills, int playerID) throws SQLException;
	
	void updatePlayerDeaths(int deaths, int playerID) throws SQLException;
	
	void updatePlayerELO(int elo, int playerID) throws SQLException;
	
	void updatePlayerTier(int tier, int playerID) throws SQLException;
	
	void updatePlayerBanned(boolean banned, int playerID) throws SQLException;

	int doesClanExist(String clanName) throws SQLException;
	
	boolean doesPlayerExist(String username) throws SQLException;
	
	boolean isPlayerInClan(String username) throws SQLException;
	
}

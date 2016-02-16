package test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import CommonClasses.*;
public interface I_RemoteDatabaseMgr extends Remote
{
	boolean canLogin(String username, String password) throws Exception;
	
	ArrayList<String[]> getLeaderboardDetails(int playerID, int clanID) throws Exception;	
	/* References the local Player class */
	String getPlayerDetails(String username) throws Exception;	
	Clan getClanDetails(int clanID) throws Exception;	
	String[] getRankNames(int clanID) throws Exception;	
	ClanEvent getClanEvent(int eventID) throws Exception;	
	void createEvent(String eventName, Date startDate) throws Exception;	
	void createPlayer(String username,String email, String password) throws Exception;	
	void createClan(String clanName,int clanLeaderID) throws Exception;	
	void addPlayerToClan(int playerID, int clanID) throws Exception;	
	void removePlayerFromClan(int playerID, int clanID) throws Exception;	
	void addPlayerToEvent(int playerID, int eventID) throws Exception;
	int checkUserNameAndEmail(String username, String email) throws Exception;	
	void updatePlayerName(String name, int playerID) throws Exception;
	void updatePlayerEmail(String name, int playerID) throws Exception;	
	void updatePlayerWins(int wins, int playerID) throws Exception;	
	void updatePlayerLosses(int losses, int playerID) throws Exception;	
	void updatePlayerKills(int kills, int playerID) throws Exception;	
	void updatePlayerDeaths(int deaths, int playerID) throws Exception;	
	void updatePlayerELO(int elo, int playerID) throws Exception;	
	void updatePlayerTier(int tier, int playerID) throws Exception;	
	void updatePlayerBanned(boolean banned, int playerID) throws Exception;
	int doesClanExist(String clanName) throws Exception;
}

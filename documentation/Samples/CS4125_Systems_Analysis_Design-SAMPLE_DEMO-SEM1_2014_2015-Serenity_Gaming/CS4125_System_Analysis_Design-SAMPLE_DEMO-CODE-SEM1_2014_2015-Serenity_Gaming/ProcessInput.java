package userInterface;

import java.util.ArrayList;

import db.*;
import SysAnalysis.src.sysAnalysis.CommonClasses.*;

/* Implements the singleton pattern */
public class ProcessInput 
{
	private static ProcessInput processInput = null;
	private DatabaseInterface dbConn;
	private Player player = null;
	
	/* Private constructor so only can be instanciated in this class */
	private ProcessInput()
	{
		setPlayer();
	}
	
	public static ProcessInput getInstance()
	{
		if(processInput == null)
		{
			processInput = new ProcessInput();
		}
		
		return processInput;
	}
	
	/* Gets player object */
	public void setPlayer()
	{
		player = Player.getInstance();
	}
	
	/* Sets database connection object */
	public void setDbConn(DatabaseInterface dbConn)
	{
		this.dbConn = dbConn;
	}
	
	/* Checks database connection object is set */
	public boolean isDbConnSet()
	{
		boolean isSet = false;
		if(this.dbConn != null)
		{
			isSet = true;
		}
		return isSet;
	}
	
	/* Validates user login details */
	public boolean canUserLogin(String username,String password)
	{
		boolean canLogin = false;
		
		try
		{
			canLogin = dbConn.canLogin(username, password);
			if(canLogin)
			{
				getPlayerDetails(username);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error logging in: " + e.toString());
		}
		
		return canLogin;
	}
	
	/* Checks if a player exists */
	public boolean doesPlayerExist(String username)
	{
		boolean exists = false;
		
		try
		{
			exists = dbConn.doesPlayerExist(username);
		}
		catch(Exception e)
		{
			System.out.println("doesPlayerExist Error: " + e.toString());
		}
		
		return exists;
	}
	
	/* Checks if username/email are registered on system */
	public int checkUsernameEmail(String username,String email)
	{
		int existsType = -1;
		
		try
		{
			existsType = dbConn.checkUserNameAndEmail(username, email);
		}
		catch(Exception e)
		{
			System.out.println("Error validaing username/email: " + e.toString());
		}
		
		return existsType;
	}
	
	/* Creates new player in system */
	public void createPlayer(String username,String email,String password)
	{
		
		try
		{
			dbConn.createPlayer(username, email, password);
		}
		catch(Exception e)
		{
			System.out.println("Error creating player: " + e.toString());
		}
	}
	
	/* Gets player details from database */
	public void getPlayerDetails(String username)
	{
		try
		{
			String[] details = dbConn.getPlayerDetails(username).split(",");

			this.player.setId(Integer.parseInt(details[0]));
			this.player.setName(details[1]);
			this.player.setEmail(details[2]);
			this.player.setTier(Byte.parseByte(details[3]));
			this.player.setElo(Short.parseShort(details[4]));
			this.player.setBanned(Boolean.parseBoolean(details[5]));
			this.player.setKills(Integer.parseInt(details[6]));
			this.player.setDeaths(Integer.parseInt(details[7]));
			this.player.setWins(Integer.parseInt(details[8]));
			this.player.setLosses(Integer.parseInt(details[9]));
			this.player.setClanID(Integer.parseInt(details[10]));
			this.player.setRank(Byte.parseByte(details[11]));
		}
		catch(Exception e)
		{
			System.out.println("Error getting player details: " + e.toString());
		}
	}
	
	/* Gets players clan id */
	public int getPlayerClanID()
	{
		return player.getClanID();
	}
	
	/* Gets players clan rank */
	public int getPlayerRank()
	{
		return this.player.getRank();
	}
	
	/* Gets players clan name */
	public String getPlayerClanName()
	{
		String clanName = "";
		if(player.getClanID() != 0)
		{
			try
			{
				clanName = dbConn.getClanDetails(player.getClanID()).getName().toString();
			}
			catch(Exception e)
			{
				System.out.println("Error getting clan name: " + e.toString());
			}
		}
		else
		{
			clanName = "Not in clan";
		}
		
		return clanName;
	}
	
	/* Checks if a clan exists in system */
	public int doesClanExist(String clanName)
	{
		/* Will return 0 if clan does not exist else will return clanID */
		int clanExists = 0;
		
		try
		{
			clanExists = dbConn.doesClanExist(clanName);
		}
		catch(Exception e)
		{
			System.out.println("Error checking clan exists: " + e.toString());
		}
		
		return clanExists;
	}
	
	/* Gets leaderboards from database */
	public ArrayList<String[]> getLeaderboards(int clanID,int sort)
	{
		ArrayList<String[]> leaderboard = null;
		
		try
		{
			if(clanID == 1)
			{
				clanID = player.getClanID();
			}
			leaderboard = sortList(dbConn.getLeaderboardDetails(player.getId(), clanID),sort);
		}
		catch(Exception e)
		{
			System.out.println("Error retreiving leaderboards: " + e.toString());
		}
		
		return leaderboard;
	}
	
	/* Sorts leaderboards to users requirements */
	public ArrayList<String[]> sortList(ArrayList<String[]>leaderboard,int type)
	{
		ArrayList<String[]> lBoard = leaderboard;
		
		/* Sort ascending kills */
		if(type == 0)
		{
			
		}
		/* Sort descending kills */
		else if(type == 1)
		{
			
		}
		/* Sort ascending wins */
		else if(type == 2)
		{
			
		}
		/* Sort descending wins */
		else
		{
			
		}
		
		return lBoard;
	}
	
	/* Creates a new clan */
	public boolean createNewClan(String clanName,String rankOne,String rankTwo,String rankThree,String rankFour)
	{
		boolean created = false;
		try
		{
			if(doesClanExist(clanName) == 0)
			{
				int newClanID = dbConn.createClan(clanName, player.getName());
				player.setClanID(newClanID);
				byte rank = 1;
				player.setRank(rank);
				created = true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Error creating clan: " + e.toString());
		}
		
		return created;
	}
	
	/* Adds a player to a clan */
	public void addPlayerToClan(String username,int rank)
	{
		try
		{
			int clanID = player.getClanID();
			/* Checks if player is in any clan */
			boolean isInClan = dbConn.isPlayerInClan(username);
			if(isInClan)
			{
				dbConn.updatePlayerClan(username, clanID, rank);
			}
			else
			{
				dbConn.addPlayerToClan(username, clanID,rank);
			}
		}
		catch(Exception e)
		{
			System.out.println("isPlayerInClan Error: " + e.toString());
		}
	}
	
	/* Sends player to match making service to be put into game */
	public void sendPlayerToMatchMakingService()
	{
		SysAnalysis.src.sysAnalysis.CommonClasses.Player sendPlayer = new SysAnalysis.src.sysAnalysis.CommonClasses.Player();
		sendPlayer.setName(player.getName());
		sendPlayer.setKVal(player.getKVal());
		sendPlayer.setElo(player.getElo());
		sendPlayer.setTier(player.getTier());
		NetworkManager netMgr = new NetworkManager();
		netMgr.connectToService(sendPlayer);
	}
	
	/*Gets players username from player object*/
	public String getPlayerName()
	{
		return player.getName();
	}
	
	/* Resets player object values */
	public void logPlayerOut()
	{
		player.resetPlayerValues();
	}
}

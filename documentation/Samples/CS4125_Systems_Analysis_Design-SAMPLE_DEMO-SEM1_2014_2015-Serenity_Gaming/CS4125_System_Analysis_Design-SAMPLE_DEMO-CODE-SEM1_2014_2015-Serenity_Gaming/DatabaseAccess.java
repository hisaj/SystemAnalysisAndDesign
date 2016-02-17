package db;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Take these out and have them in their own packages...

import SysAnalysis.src.sysAnalysis.CommonClasses.Clan;
import SysAnalysis.src.sysAnalysis.CommonClasses.ClanEvent;

public class DatabaseAccess implements Serializable, DatabaseInterface {
	
	
	private Connection con;
	private PreparedStatement statement;
	private ResultSet result;
	private String url;
	private String dbName;
	private String driver;
	private String userName;
	
	
	public DatabaseAccess() throws Exception
	{
		//specifying the location, name, driver and username of the database you wish to use
		url = "jdbc:mysql://localhost:3306/"; 
		dbName = "gameframework";
		driver = "com.mysql.jdbc.Driver";
		userName = "root"; 	
		connect();
	}
	
	protected void connect() throws Exception
	{
		//connecting to the database
		Class.forName(driver).newInstance(); 
		con = DriverManager.getConnection(url+dbName,userName,""); 
		
	}
	
	protected void close() throws SQLException
	{
		//closing the connection to the databse
		con.close();
	}
	
	
	
	
	@Override
	//works
	public boolean canLogin(String username, String password) throws Exception {
		
		//String hashed = BCrypt.hashpw(pw, BCrypt.gensalt());
		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
		//String hashed = BCrypt.hashpw(pw, BCrypt.gensalt(10));
		// Check that an unencrypted password matches one that has
		// previously been hashed
		
		//checking if the password passed in is identical to the one stored in the database for the username provided. it has to be decrypted first
		statement = con.prepareStatement("select password from players where username = ?");
		statement.setString(1, username);
		result = statement.executeQuery();
		while(result.next())
		{
			if (BCrypt.checkpw(password, result.getString(1)))
			{
				//password matches
				
				return true;
			}
			else{
				//password doesn't match
				return false;
			}
		}
		
		return false;
			
	}

	
	
	@Override
	//works
	public ArrayList<String[]> getLeaderboardDetails(int playerID, int clanID)throws Exception 
	{
		// returns leaderboard details
		// if  a clanID is passed in it only returns leaderboard results for members of the clan
		// if 0 is passed in for clanID it returns leaderboard results for all players
		if(clanID != 0)
		{
			statement = con.prepareStatement("select * from players where playerID = (select playerID from clanmembers where clanID =?)");
			statement.setInt(1, playerID);
			result = statement.executeQuery();
		
		}
		
		else
		{
			statement = con.prepareStatement("select * from players");
		
			result = statement.executeQuery();
		
		}
		
		ArrayList<String[]> output = new ArrayList<String[]>(); 
		
		while(result.next())
		{
			String[] line = {result.getString("username"),result.getString("kills"),result.getString("deaths"),result.getString("wins"),result.getString("losses")};
			output.add(line);
			
		}
		return output;
	}
	
	
	

	@Override
	//works
	public String getPlayerDetails(String  username) throws Exception {
		// returns the details of the player whose username was passed in
		statement = con.prepareStatement("select * from players where username = ?");
		statement.setString(1, username);
		result = statement.executeQuery();
		String playerDetails = "";
		int playerID = 0;
		while(result.next())
		{
			playerID = result.getInt("playerID");
			playerDetails += result.getInt("playerID") + ",";
			playerDetails += result.getString("username") + ",";
			playerDetails += result.getString("email") + ",";
			playerDetails += result.getByte("tier") + ",";
			playerDetails += result.getShort("elo") + ",";
			playerDetails += result.getBoolean("banned") + ",";
			playerDetails += result.getInt("kills") + ",";
			playerDetails += result.getInt("deaths") + ",";
			playerDetails += result.getInt("wins") + ",";
			playerDetails += result.getInt("losses") + ",";
		
		}
		
		statement = con.prepareStatement("select * from clanMembers where playerID = ?");
		statement.setInt(1, playerID);
		result = statement.executeQuery();
		boolean hasResults = false;
		while(result.next())
		{
			hasResults = true;
			playerDetails += result.getInt("clanID") + ",";
			playerDetails += result.getByte("rank");
			//player.setClanID(result.getInt("clanID"));
			//player.setRank(result.getByte("rank"));
		}
		if(!hasResults)
		{
			playerDetails += "0,0";
		}
		
		
		return playerDetails;
	}

	
	@Override
	//works
	public Clan getClanDetails(int clanID) throws Exception {
		//returns a Clan object based on the clanID that was passed in. The object contains the details of the clan
		statement = con.prepareStatement("select * from clans where clanID = ?");
		statement.setInt(1, clanID);
		result = statement.executeQuery();
		Clan clan = new Clan();
		while(result.next())
		{
			clan.setId(result.getInt("clanID"));
			clan.setName(result.getString("clanName"));
		}
		
		statement = con.prepareStatement("select * from ranknames where clanID = ?");
		statement.setInt(1, clanID);
		result = statement.executeQuery();
		
		String[] ranks = new String[4];
		
			while(result.next())
			{
				ranks[0] = result.getString("rank1");
				ranks[1] = result.getString("rank2");
				ranks[2] = result.getString("rank3");
				ranks[3] = result.getString("rank4");
			}
		
		
		clan.setRankNames(ranks);
		
		statement = con.prepareStatement("select * from clanmembers where clanID = ?");
		statement.setInt(1, clanID);
		result = statement.executeQuery();
		
		ArrayList<Integer> playerIDs = new ArrayList<Integer>();
		while(result.next())
		{
			playerIDs.add(result.getInt("playerID"));
		}
		
		clan.setPlayerIDs(playerIDs);
		
		return clan;
	}

	@Override
	//works
	public String[] getRankNames(int clanID) throws Exception {
		//returns the rank names for the clan whose ID was passed in. There are 4 rank names for each clan.
		statement = con.prepareStatement("select * from ranks where clanID = ?");
		statement.setInt(1, clanID);
		result = statement.executeQuery();
		
		String[] ranks = new String[4];
			while(result.next())
			{
				ranks[0] = result.getString("rank1");
				ranks[1] = result.getString("rank2");
				ranks[2] = result.getString("rank3");
				ranks[3] = result.getString("rank4");
			}
		
		return ranks;
	}

	@Override
	//works
	public ClanEvent getClanEvent(int eventID) throws Exception {
		// returns a Clan Event Object whose eventID was passed in. 
		statement = con.prepareStatement("select * from events where eventID = ?");
		statement.setInt(1, eventID);
		result = statement.executeQuery();
		ClanEvent event = new ClanEvent();
		event.setId(eventID);
		while(result.next())
		{
			event.setEventName(result.getString("eventName"));
			event.setStartDate(result.getDate("startDate"));
		}
		statement = con.prepareStatement("select * from eventplayers where eventID = ?");
		statement.setInt(1, eventID);
		result = statement.executeQuery();
		
		ArrayList<Integer> playerIDs = new ArrayList<Integer>();
		while(result.next())
		{
			playerIDs.add(result.getInt("playerID"));
		}
		
		event.setPlayerIDs(playerIDs);
		
		return event;
	}
	
	public void updatePlayerName(String name, int playerID) throws SQLException {
		
		//updates players username
		statement = con.prepareStatement("UPDATE `players` SET `username`=? WHERE playerID = ?");
		statement.setString(1, name);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	public void updatePlayerEmail(String email, int playerID) throws SQLException {
	
		//updates players email
		statement = con.prepareStatement("UPDATE `players` SET `email`=? WHERE playerID = ?");
		statement.setString(1, email);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}


	public void updatePlayerKills(int kills, int playerID) throws SQLException {
		
		//updates players kills
		statement = con.prepareStatement("UPDATE `players` SET `kills`=? WHERE playerID = ?");
		statement.setInt(1, kills);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}
	
	public void updatePlayerDeaths(int deaths, int playerID) throws SQLException {
		
		//updates players deaths
		statement = con.prepareStatement("UPDATE `players` SET `deaths`=? WHERE playerID = ?");
		statement.setInt(1, deaths);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	public void updatePlayerWins(int wins, int playerID) throws SQLException {
		
		//updates players wins
		statement = con.prepareStatement("UPDATE `players` SET `wins`=? WHERE playerID = ?");
		statement.setInt(1, wins);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	public void updatePlayerLosses(int losses, int playerID) throws SQLException {
		
		//updates players losses
		statement = con.prepareStatement("UPDATE `players` SET `losses`=? WHERE playerID = ?");
		statement.setInt(1, losses);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}
	
	public void updatePlayerBanned(boolean banned, int playerID) throws SQLException {
		
		//changes the players banned status. True = player is banned. False = player isnt banned
		statement = con.prepareStatement("UPDATE `players` SET `banned`=? WHERE playerID = ?");
		statement.setBoolean(1, banned);
		statement.setInt(2, playerID);
		statement.executeUpdate();
	}

	public void updatePlayerTier(int tier, int playerID) throws SQLException {
		
		//updates the players tier
		statement = con.prepareStatement("UPDATE `players` SET `tier`=? WHERE playerID = ?");
		statement.setInt(1, tier);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	
	public void updatePlayerELO(int elo, int playerID) throws SQLException {
		
		//updates the players ELO
		statement = con.prepareStatement("UPDATE `players` SET `username`=? WHERE playerID = ?");
		statement.setInt(1, elo);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}


	
	@Override
	//tested
	public void createEvent(String eventName, Date startDate) throws Exception {
		// adds a new event to the database 
		statement = con.prepareStatement("INSERT INTO events (eventName, startDate) VALUES (?,?)");
		statement.setString(1, eventName);
		statement.setDate(2, startDate);
		statement.executeUpdate();
	}

	@Override
	//works
	public void createPlayer(String username,String email,String password) throws Exception {
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
		// adds a new player to the database
		statement = con.prepareStatement("INSERT INTO players (username, password, email) VALUES (?,?,?)");
		statement.setString(1, username);
		statement.setString(2, hashed);
		statement.setString(3, email);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public int createClan(String clanName,String creatorUsername) throws Exception {
		//adds a new clan to the database
		statement = con.prepareStatement("INSERT INTO clans (clanName, clanLeaderID) VALUES (?,(SELECT playerID FROM players WHERE username = ?))");
		statement.setString(1, clanName);
		statement.setString(2, creatorUsername);
		statement.executeUpdate();
		
		statement = con.prepareStatement("DELETE FROM clanmembers WHERE playerID = (SELECT playerID FROM players WHERE username = ?)");
		statement.setString(1,creatorUsername);
		statement.executeUpdate();
		
		statement = con.prepareStatement("INSERT INTO clanmembers (clanID,playerID,rank) VALUES ((SELECT clanID FROM clans WHERE clanLeaderID = (SELECT playerID FROM players WHERE username = ?)),(SELECT playerID FROM players WHERE username = ?),1)");
		statement.setString(1, creatorUsername);
		statement.setString(2,creatorUsername);
		statement.executeUpdate();
		
		int clanID = 0;
		
		statement = con.prepareStatement("SELECT clanID FROM clans WHERE clanLeaderID=(SELECT playerID FROM players WHERE username = ?)");
		statement.setString(1, creatorUsername);
		result = statement.executeQuery();
		while(result.next())
		{
			clanID = result.getInt("clanID");
		}
		System.out.println("ClanID: " + clanID);
		return clanID;
	}

	@Override
	//tested
	public void addPlayerToClan(String username, int clanID, int rank) throws Exception {
		// adds a player to a clan. This is done by adding a new row in the clanmembers table
		statement = con.prepareStatement("INSERT INTO clanMembers (playerID, clanID, rank) values ((SELECT playerID FROM players WHERE username=?),?,?)");
		statement.setString(1, username);
		statement.setInt(2, clanID);
		statement.setInt(3, rank);
		statement.executeUpdate();
		
	}
	
	@Override
	public void updatePlayerClan(String username,int clanID,int rank) throws Exception
	{
		// changes a players current clan
		statement = con.prepareStatement("UPDATE clanmembers SET clanID = ?,rank=? WHERE playerID = (SELECT playerID FROM players WHERE username=?)");
		statement.setInt(1, clanID);
		statement.setInt(2, rank);
		statement.setString(3, username);
		statement.executeUpdate();
	}

	@Override
	//tested
	public void removePlayerFromClan(int playerID, int clanID) throws Exception {
		// removes a player from a clan. done by deleting his entry in clanmembers table
		statement = con.prepareStatement("delete from clanMembers where playerID =?");
		statement.setInt(1, playerID);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public void addPlayerToEvent(int playerID, int eventID) throws Exception {
		// adds player to an event
		statement = con.prepareStatement("INSERT INTO eventplayers (playerID, eventID) values (?,?)");
		statement.setInt(1, playerID);
		statement.setInt(2, eventID);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public int checkUserNameAndEmail(String username, String email) throws SQLException {
		// checks whether an email address or username already exists in the system. if it does the player cannot register using those details
		//returns 0 if username is already in system
		statement = con.prepareStatement("select * from players where username = ?");
		statement.setString(1, username);
		result = statement.executeQuery();
		if (result.isBeforeFirst() ) 
		{    
			 return 0;
	    } 
		
		//returns 1 if email is already in the system
		statement = con.prepareStatement("select * from players where email = ?");
		statement.setString(1, email);
		result = statement.executeQuery();
		if (result.isBeforeFirst() ) 
		{    
			 return 1;
	    } 
		
		//returns 2 if neither are
		return 2;
		
	}
	
	@Override
	public int doesClanExist(String clanName) throws SQLException
	{
		/* Will return 0 if clan does not exist else will return clanID */
		// checks if a clan exists in the clan table by searching for its clanID
		int clanExists = 0;
		
		statement = con.prepareStatement("select clanID from clans where clanName=?");
		statement.setString(1,clanName);
		result = statement.executeQuery();
		while(result.next())
		{
			clanExists = result.getInt("clanID");
		}
		
		return clanExists;
	}
	
	@Override
	public boolean doesPlayerExist(String username) throws SQLException
	{
		boolean exists = false;
		// checks does a player with the username passed in exists
		statement = con.prepareStatement("SELECT * FROM players WHERE username = ?");
		statement.setString(1, username);
		result = statement.executeQuery();
		while(result.next())
		{
			exists = true;
		}
		
		return exists;
	}
	
	@Override
	public boolean isPlayerInClan(String username) throws SQLException
	{
		boolean isInClan = false;
		// checks is user in a clan
		statement = con.prepareStatement("SELECT * FROM clanmembers WHERE playerID=(SELECT playerID FROM players WHERE username = ?)");
		statement.setString(1, username);
		result = statement.executeQuery();
		while(result.next())
		{
			isInClan = true;
		}
		
		return isInClan;
	}

}


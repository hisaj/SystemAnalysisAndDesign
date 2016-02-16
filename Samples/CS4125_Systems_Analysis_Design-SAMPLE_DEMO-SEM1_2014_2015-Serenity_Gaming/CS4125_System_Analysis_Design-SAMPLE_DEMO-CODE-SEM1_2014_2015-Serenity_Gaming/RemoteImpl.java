package test.server;

import java.sql.*;

import CommonClasses.*;
import test.rmi.I_RemoteDatabaseMgr;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

public class RemoteImpl extends UnicastRemoteObject implements I_RemoteDatabaseMgr
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	private PreparedStatement statement;
	private ResultSet result;
	private String url;
	private String dbName;
	private String driver;
	private String userName;

	protected RemoteImpl() throws RemoteException
	{
		super();
		url = "jdbc:mysql://localhost:3306/"; //3306 is default, i have two mysql ports
		dbName = "cs4125";
		driver = "com.mysql.jdbc.Driver";
		userName = "root"; 	
		try {
			connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void connect() throws Exception
	{
		Class.forName(driver).newInstance(); 
		con = DriverManager.getConnection(url+dbName,userName,"");		
	}
	
	protected void close() throws SQLException
	{
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
		
		
		statement = con.prepareStatement("select password from players where username = ?");
		statement.setString(1, username);
		result = statement.executeQuery();
		while(result.next())
		{
			if (BCrypt.checkpw(password, result.getString(1)))
			{
				System.out.println("It matches");
				return true;
			}
			else{
				System.out.println("It does not match");
				return false;
			}
		}
		
		return false;
			
	}

	
	
	@Override
	//works
	public ArrayList<String[]> getLeaderboardDetails(int playerID, int clanID)throws Exception 
	{
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
			//SHOULDNT NEED ANYTHING ELSE FROM BELOW
			//player.setId(result.getInt("playerID")); //
			//player.setKills(result.getInt("kills")); //
			//player.setDeaths(result.getInt("deaths")); //
			//player.setWins(result.getInt("wins")); //
			//player.setLosses(result.getInt("losses")); //
			//player.setName(result.getString("username")); //
			//player.setEmail(result.getString("email")); //
			//player.setTier(result.getByte("tier")); //
			//player.setElo(result.getShort("elo")); //
			//player.setBanned(result.getBoolean("banned")); //
		
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
		
		System.out.println("Player Details: " + playerDetails);
		return playerDetails;
	}

	
	@Override
	//works
	public Clan getClanDetails(int clanID) throws Exception {
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
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `username`=? WHERE playerID = ?");
		statement.setString(1, name);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	public void updatePlayerEmail(String email, int playerID) throws SQLException {
	
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `email`=? WHERE playerID = ?");
		statement.setString(1, email);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}


	public void updatePlayerKills(int kills, int playerID) throws SQLException {
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `kills`=? WHERE playerID = ?");
		statement.setInt(1, kills);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}
	
	public void updatePlayerDeaths(int deaths, int playerID) throws SQLException {
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `deaths`=? WHERE playerID = ?");
		statement.setInt(1, deaths);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	public void updatePlayerWins(int wins, int playerID) throws SQLException {
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `wins`=? WHERE playerID = ?");
		statement.setInt(1, wins);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	public void updatePlayerLosses(int losses, int playerID) throws SQLException {
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `losses`=? WHERE playerID = ?");
		statement.setInt(1, losses);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}
	
	public void updatePlayerBanned(boolean banned, int playerID) throws SQLException {
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `banned`=? WHERE playerID = ?");
		statement.setBoolean(1, banned);
		statement.setInt(2, playerID);
		statement.executeUpdate();
	}

	public void updatePlayerTier(int tier, int playerID) throws SQLException {
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `tier`=? WHERE playerID = ?");
		statement.setInt(1, tier);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}

	
	public void updatePlayerELO(int elo, int playerID) throws SQLException {
		
		//to write
		statement = con.prepareStatement("UPDATE `players` SET `username`=? WHERE playerID = ?");
		statement.setInt(1, elo);
		statement.setInt(2, playerID);

		statement.executeUpdate();
	}


	
	

	@Override
	//works
	public void createPlayer(String username,String email,String password) throws Exception {
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
		statement = con.prepareStatement("INSERT INTO players (username, password, email) VALUES (?,?,?)");
		statement.setString(1, username);
		statement.setString(2, hashed);
		statement.setString(3, email);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public void createClan(String clanName,int clanLeaderID) throws Exception {
		statement = con.prepareStatement("INSERT INTO clans (clanName, clanLeaderID) VALUES (?,?)");
		statement.setString(1, clanName);
		statement.setInt(2, clanLeaderID);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public void addPlayerToClan(int playerID, int clanID) throws Exception {
		statement = con.prepareStatement("INSERT INTO clanMembers (playerID, clanID) values (?,?)");
		statement.setInt(1, playerID);
		statement.setInt(2, clanID);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public void removePlayerFromClan(int playerID, int clanID) throws Exception {

		statement = con.prepareStatement("delete from clanMembers where playerID =?");
		statement.setInt(1, playerID);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public void addPlayerToEvent(int playerID, int eventID) throws Exception {
		statement = con.prepareStatement("INSERT INTO eventplayers (playerID, eventID) values (?,?)");
		statement.setInt(1, playerID);
		statement.setInt(2, eventID);
		statement.executeUpdate();
		
	}

	@Override
	//tested
	public int checkUserNameAndEmail(String username, String email) throws Exception {
		
		statement = con.prepareStatement("select * from players where username = ?");
		statement.setString(1, username);
		result = statement.executeQuery();
		if (result.isBeforeFirst() ) 
		{    
			 return 0;
	    } 
		
		statement = con.prepareStatement("select * from players where email = ?");
		statement.setString(1, email);
		result = statement.executeQuery();
		if (result.isBeforeFirst() ) 
		{    
			 return 1;
	    } 
		
		
		return 2;
		
	}
	
	@Override
	public int doesClanExist(String clanName) throws Exception
	{
		/* Will return 0 if clan does not exist else will return clanID */
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
	public void createEvent(String eventName, java.sql.Date startDate)
			throws Exception {
		statement = con.prepareStatement("INSERT INTO events (eventName, startDate) VALUES (?,?)");
		statement.setString(1, eventName);
		statement.setDate(2, startDate);
		statement.executeUpdate();
		
	}

}

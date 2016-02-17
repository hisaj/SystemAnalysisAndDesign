package userInterface;

/* Implements the singleton pattern as only one instance is needed in client system */
public class Player 
{
	private static Player player = null;
	private int id;
	private String name;
	private String email;
	private int clanID;
	private int kills = 0;
	private int deaths = 0;
	private int wins = 0;
	private int losses = 0;
	private short elo = 1200;		//Matchmaking rating
	private int kVal = 50;
	private boolean banned;
	private byte tier = 3;		//Matchmaking tier
	private byte rank;		//Clan rank (1 - 4)
	
	/* Private constructor so no instances can be created outside of this class */
	private Player()
	{
		
	}
	
	/* Returns the single instance of the player object */
	public static Player getInstance()
	{
		if(player == null)
		{
			player = new Player();
		}
		return player;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getClanID() {
		return clanID;
	}
	public void setClanID(int clanID) {
		this.clanID = clanID;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public short getElo() {
		return elo;
	}
	public void setElo(short elo) {
		this.elo = elo;
	}
	
	public void setKVal(int kVal)
	{
		this.kVal = kVal;
	}
	
	public int getKVal()
	{
		return this.kVal;
	}
	
	public boolean isBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	public byte getTier() {
		return tier;
	}
	public void setTier(byte tier) {
		this.tier = tier;
	}
	public byte getRank() {
		return rank;
	}
	public void setRank(byte rank) {
		this.rank = rank;
	}
	
	public void resetPlayerValues()
	{
		this.id = 0;
		this.name = "";
		this.email = "";
		this.clanID = 0;
		this.kills = 0;
		this.deaths = 0;
		this.wins = 0;
		this.losses = 0;
		this.elo = 0;
		this.banned = false;
		this.tier = 0;
		this.rank = 0;
	}
	
}

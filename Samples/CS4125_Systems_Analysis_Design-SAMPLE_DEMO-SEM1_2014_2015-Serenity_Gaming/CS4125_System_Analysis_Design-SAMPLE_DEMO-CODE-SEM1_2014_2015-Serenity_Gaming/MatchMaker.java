package SysAnalysis.src.matchmaking;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import SysAnalysis.src.sysAnalysis.CommonClasses.*;

public class MatchMaker extends JFrame implements I_MatchMakingMgr, ActionListener
{
	//Last search point for ports.
	private final int MAX_PORT_NUMBER = 10000;
	//First search point for ports.
	private final int MIN_PORT_NUMBER = 2000;
	//Max number of threads depends on distribution of tiers among gamer population.
	private int maxNumberOfGameThreads = 10;
	//Reccieves players.
	private ServerSocket ss = null;
	//The selected port.
	private int port;
	private ObjectInputStream input;	
	//Each thread populates the teams for a game.
	private ArrayList<gameCreationThread> threads;
	//List of players added by main process.
	private LinkedList<Object[]> t1PlayerQueue;
	private LinkedList<Object[]> t2PlayerQueue;
	private LinkedList<Object[]> t3PlayerQueue;
	private LinkedList<Object[]> t4PlayerQueue;
	private LinkedList<Object[]> t5PlayerQueue;
	private ServerSocket server = null;
    private Socket client = null;
    private JPanel framePanel;
    private JTextArea results;
	//Proportion of threads to tiers
	private short[] tierDistribution;
	
	public Server2()
	{
				super("Match Making Server");
				
		    	framePanel = new JPanel();
		    	
		    	results = new JTextArea();
		    	results.setSize(400, 400);
		    	results.setLineWrap(true);
		        results.setEditable(false);
		        results.setVisible(true);
		       
		        JScrollPane scroll = new JScrollPane (results);
		        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		              scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		    	add(scroll);
		    	setSize(400, 400);
		    	setResizable(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	setVisible(true);
		//Queue of players to be continually filled.
				t1PlayerQueue = new LinkedList<Object[]>();
				t2PlayerQueue = new LinkedList<Object[]>();
				t3PlayerQueue = new LinkedList<Object[]>();
				t4PlayerQueue = new LinkedList<Object[]>();
				t5PlayerQueue = new LinkedList<Object[]>();
				//Default values for tiers...
				tierDistribution = new short[5];
				tierDistribution[0] = 4;
				tierDistribution[1] = 3;
				tierDistribution[2] = 1;
				tierDistribution[3] = 1;
				tierDistribution[4] = 1;
				
				/*
				 * tierDistribution[0] = Math.ceil(maxThreads / popDistT5);
				 * tierDistribution[1] = Math.ceil(maxThreads / popDistT4);
				 * tierDistribution[2] = Math.ceil(maxThreads / popDistT3);
				 * tierDistribution[3] = Math.ceil(maxThreads / popDistT2);
				 * tierDistribution[4] = Math.ceil(maxThreads / popDistT1);
				 */
				//List of game threads.
				threads = new ArrayList<gameCreationThread>();
				boolean assigned;
				//Initialize all the threads with a certain tier assigned so they only accept players from that tier
				for(int i = 0; i < maxNumberOfGameThreads; i++)
				{
					assigned = false;
					threads.add(new gameCreationThread());
					for(int j = 0; j < tierDistribution.length && !assigned; j++)
					{
						if(tierDistribution[j] > 0)
						{
							threads.get(i).setTier((short) (j+1));
							tierDistribution[j] -= 1;
							assigned = true;
						}
					}
					threads.get(i).start();
				}
				
			
				
	}
	
	@Override
	public void runServer() 
	{
		//Search for the first available port.
		if(searchForPort())
		{
			//Accept a client.
			try {
	        	//Open server socket for client-server interaction, client applications open sockets on 4444 to send/receive with server
	            server = new ServerSocket(5432);
	        } catch (IOException e) {
	            System.out.println("Could not listen on port 5432");//Happens if port in use (usually this server class has been run already)
	            System.exit(-1);
	        }
			//Change from true to a condition based on max connections allowed/threads
			while(true)
			{
				try
				{							
					client = server.accept();
					results.append("Client Found\n");
					//Get socket input stream for client.
					input = new ObjectInputStream(client.getInputStream());
					//Object array containing details sent from client.
					Object[] details = new Object[3];
					details[0] = input.readObject();//Player details.
					//details[1] = client.getInetAddress();//Inet Address.
					details[1] = client;
					System.out.println("Client name is: " + (((Player) details[0]).getFirstName()));
					results.append("Client name is: " + (((Player) details[0]).getFirstName()) + "\n");
					//details[2] = input.readObject();//Client port number (?)
					//Add to the queue.
					Player receivedPlayer = (Player)details[0];
					switch (receivedPlayer.getTier())
					{
						case 1: t1PlayerQueue.add(details);break;
						case 2: t2PlayerQueue.add(details);break;
						case 3: t3PlayerQueue.add(details);break;
						case 4: t4PlayerQueue.add(details);break;
						case 5: t5PlayerQueue.add(details);break;
					}			
				}
				catch(IOException e)
				{
					System.out.println(e);
					System.out.println("Socket connection error.");
				} 
				catch (ClassNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (NullPointerException e) 
				{
					// TODO Auto-generated catch block
					//System.out.println("No Client Found");
				}	
			}
		}
		else
		{
			System.out.println("Could not find port.");
		}
	}
	
	private synchronized Object[] getPlayer(short tier)
	{
		switch (tier)
		{
			case 1: return (!t1PlayerQueue.isEmpty() ? t1PlayerQueue.pop() : null);
			case 2: return (!t2PlayerQueue.isEmpty() ? t2PlayerQueue.pop() : null);
			case 3: return (!t3PlayerQueue.isEmpty() ? t3PlayerQueue.pop() : null);
			case 4: return (!t4PlayerQueue.isEmpty() ? t4PlayerQueue.pop() : null);
			case 5: return (!t5PlayerQueue.isEmpty() ? t5PlayerQueue.pop() : null);
		}
		return null;
	}
	
	//Look for an empty port.
	private boolean searchForPort()
	{
		this.port = 5432;
		return true;
	}
	
	private class gameCreationThread extends Thread
	{
		private ArrayList<Object[]> teamOne = new ArrayList<Object[]>();
		private ArrayList<Object[]> teamTwo= new ArrayList<Object[]>();
		private double expectedWinT1;
		private double expectedWinT2;
		private double avgEloT1;
		private double avgEloT2;
		private short tier;
		private int eloCap;
		private int eloBase;
		private boolean ready;
		private ObjectOutputStream localOutput;
		
		public gameCreationThread()
		{
			ready = false;
			results.append("Thread/Game Server " + this.getId() + " ready!   ");
		}
		
		public void setTier(short tier)
		{
			//NOTE: no defensive programming needs pre-condition tier 1 -> 5
			this.tier = tier;
			switch (this.tier)
			{
				case 1: eloCap = 2000; eloBase = 1601; break;
				case 2: eloCap = 1600; eloBase = 1201; break;
				case 3:	eloCap = 1200; eloBase = 801; break;
				case 4:	eloCap = 800; eloBase = 401; break;
				case 5: eloCap = 400; eloBase = 0; break;
			}
			results.append("Serving tier: " + this.tier + "\n");
		}
		
		@Override
		public void run() 
		{
			while(!ready)
			{
				//System.out.println("Looking for player!");
				//Wait/organise teams
				Object[] o = getPlayer(this.tier);
				if(o == null){
					//Do nothing
				}	
				else
				{
					results.append("Looked and found player by tier: " + this.tier + " Player Name: " + ((Player) o[0]).getFirstName() + "\nAdded on thread: " + this.getId() + "\n");
					//System.out.println(this.getId() + " Found a player!");
					//If team one has no players give it one 
					if(teamOne.isEmpty())
					{
						teamOne.add(o);
					}
					//if team two has no players give it one
					else if(teamTwo.isEmpty())
					{
						teamTwo.add(o);
					}
					else
					{
						/*
						 * Team1 and Team2 have at least one player each - next logical check is which team has lowest total Elo
						 * Alos need check if a team is full of players and still lower elo than other team with space for receiving new players
						 */
						if(getTotElo(teamOne) < getTotElo(teamTwo) && teamOne.size() < 1)
						{
							teamOne.add(o);
						}
						else if(getTotElo(teamOne) < getTotElo(teamTwo))
						{
							/*
							 * Team two has higher elo with 1 less player than a full team 1
							 * Swap the highest elo player in t2 with lowest in t1 -> drastically increases team balance.
							 */
							swapHighLow(teamOne, teamTwo);
						}
						else
						{
							teamTwo.add(o);
						}
					}
					//Once player added check list size vs game size requirements and set is ready accordingly
					if(teamOne.size() == 1 && teamTwo.size() == 1)
					{
						ready = true;
					}
				}
				for(int i = 0; i < teamOne.size(); i++)
				{
					 try {
						localOutput = new ObjectOutputStream(((Socket) teamOne.get(i)[1]).getOutputStream());
						Player player = (Player) teamOne.get(i)[0];
						//localOutput.writeObject(new String("In Queue: " + player.getFirstName()));
						//results.append("In Queue: " + player.getFirstName() + "\n");
						//System.out.println("Writing: " + new String("In Queue: " + player.getFirstName()));
					} 
					 catch (SocketException e) {
							// TODO Auto-generated catch block
							results.append("Lost client - removing From: " + this.getId() + "\n");
							teamOne.remove(i);
							ready = false;
						}
					 catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i = 0; i < teamTwo.size(); i++)
				{
					try {
						localOutput = new ObjectOutputStream(((Socket) teamTwo.get(i)[1]).getOutputStream());
						Player player = (Player) teamTwo.get(i)[0];
						//localOutput.writeObject(new String("In Queue: " + player.getFirstName()));
						//results.append("In Queue: " + player.getFirstName() + "\n");
						//System.out.println("Writing: " + new String("In Queue: " + player.getFirstName()));
					}
					catch (SocketException e) {
						// TODO Auto-generated catch block
						results.append("Lost client - removing from thread: " + this.getId() + "\n");
						teamTwo.remove(i);
						ready = false;
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					this.sleep(1000);//mb rem
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ready)
				{
					//send players to game servers.
					//For now left empty can do cmd line print out representation
					//will also need listen for input from server once game ended
					//For now can just simulate received results by generating random winner from t1 and t2 then applying elo update to players.
					
					//Need getAverageRating of both teams and calculate expected wins at this point for elo updating.
					results.append("2 players present so match ready\n");
					for(int i = 0; i < teamOne.size(); i ++)
					{
						results.append("Player Name T1: " + ((Player) teamOne.get(i)[0]).getFirstName() + "\n");
						results.append("Player Name T2: " + ((Player) teamTwo.get(i)[0]).getFirstName() + "\n");
						results.append("Created on thread: " + this.getId() + "  Tier: " + this.tier + "\n");
					}
					avgEloT1 = getTotElo(teamOne)/teamOne.size();
					avgEloT2 = getTotElo(teamTwo)/teamTwo.size();
					if(avgEloT1 == avgEloT2)
					{
						expectedWinT1 = 0.5;
						expectedWinT2 = 0.5;
					}
					else
					{
						expectedWinT1 = 1/(1 + ((Math.pow(10, avgEloT2 - avgEloT1))/400));
						expectedWinT2 = 1/(1 +  ((Math.pow(10, avgEloT1 - avgEloT2))/400));
					}
					if(Math.random() * 2 == 0)
					{
						//Team One won
						for(int i = 0; i < teamOne.size(); i++)
						{
							updateElo((Player)teamOne.get(i)[0], true, (byte) 1);
							updateElo((Player)teamTwo.get(i)[0], false, (byte) 2);
						}
					}
					else
					{
						//Team Two won
						for(int i = 0; i < teamTwo.size(); i++)
						{
							updateElo((Player)teamTwo.get(i)[0], true, (byte) 2);
							updateElo((Player)teamOne.get(i)[0], false, (byte) 1);
						}
					}
					//TODO send info back to player
					//TODO PRINT ELO CHANGES
					results.append("Game completed elos updated\n");
					teamOne.clear();
					teamTwo.clear();
					ready = false;
				}
					
			}
			
			
		}
		//Returns total elo of all players in given team
		private int getTotElo(ArrayList<Object[]> aTeam)
		{
			int totElo = 0;
			Player aPlayer;
			for(int i = 0; i < aTeam.size(); i++)
			{
				aPlayer = (Player)aTeam.get(i)[0];
				totElo += aPlayer.getElo();
			}
			return totElo;
		}
		//Method to swap lowest elo player in team1 with highest in team2
		private void swapHighLow(ArrayList<Object[]> team1, ArrayList<Object[]> team2)
		{
			int minElo = eloCap, maxElo = eloBase;
			byte t1PlayerIndex = 0, t2PlayerIndex = 0;
			Player t1Player;
			Player t2Player;
			//Find lowest elo player in t1 (first player at lowest elo taken).
			for(int i =0; i < team1.size(); i++)
			{
				t1Player = (Player) team1.get(i)[0];
				if(t1Player.getElo() < minElo)
				{
					minElo = t1Player.getElo();
					t1PlayerIndex = (byte) i;
				}
			}
			//Find highest elo player in t2 (first player at highest elo taken).
			for(int i = 0; i < team2.size(); i++)
			{
				t2Player = (Player) team2.get(i)[0];
				if(t2Player.getElo() > maxElo)
				{
					maxElo = t2Player.getElo();
					t2PlayerIndex = (byte) i;
				}
			}
			//The swap
			team1.add(team2.get(t2PlayerIndex));
			team2.add(team1.get(t1PlayerIndex));
			team1.remove(t1PlayerIndex);
			team2.remove(t2PlayerIndex);
		}
		private void updateElo(Player aPlayer, boolean won, byte team)
		{
			if(won)
			{
				if(aPlayer.getElo() == 2000);
				else
				{
					if (team == 1)
					{
						double newElo = aPlayer.getElo() + (aPlayer.getKVal() * (1 - expectedWinT1));
						aPlayer.setElo((int) newElo);
					}
					else
					{
						double newElo = aPlayer.getElo() + (aPlayer.getKVal() * (1 - expectedWinT2));
						aPlayer.setElo((int) newElo);
					}
				}
				//Increment wins at end because this method will alter kVal which in turn alters elo calculations
				aPlayer.incrememntWins();
			}
			else
			{
				if (team == 1)
				{
					double newElo = aPlayer.getElo() - (aPlayer.getKVal() * (1 - expectedWinT1));
					aPlayer.setElo((int) newElo);
				}
				else
				{
					double newElo = aPlayer.getElo() - (aPlayer.getKVal() * (1 - expectedWinT2));
					aPlayer.setElo((int) newElo);
				}
				aPlayer.incrementLoss();
			}
		}
		private void sendToGame()
		{			
			Socket socket = new Socket();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
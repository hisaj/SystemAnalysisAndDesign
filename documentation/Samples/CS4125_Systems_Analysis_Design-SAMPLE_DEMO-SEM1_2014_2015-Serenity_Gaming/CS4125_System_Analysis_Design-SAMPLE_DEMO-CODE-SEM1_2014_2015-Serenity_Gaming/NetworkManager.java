package userInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import SysAnalysis.src.sysAnalysis.CommonClasses.Player;

/* Connects to matchmaking service through a socket */
public class NetworkManager 
{
	private Socket socket = null;
	private ObjectOutputStream out = null;
	
	public NetworkManager()
	{
		
	}
	
	/* No return from server in this implementation, hence void */
	public void connectTo(SysAnalysis.src.sysAnalysis.CommonClasses.Player player)
	{
		try
		{
			 /* Creates a player class from common classes package */
			 socket = new Socket("localhost", 5432); 
	         /* Checks if the client is connected to the server through the socket */
			 if(socket.isConnected())
	         {
	        	 out = new ObjectOutputStream(socket.getOutputStream());
		         out.writeObject(player);
	         }
	         else
	         {
	        	 System.out.println("Error sending player, socket not connected");
	         }
		}
		catch(IOException e)
		{
			System.out.println("Error connecting to server: " + e.toString());
		}
	}
}

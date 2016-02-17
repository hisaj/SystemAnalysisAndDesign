package SysAnalysis.src.matchmaking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

	class TestServer{
		private Socket socket = null;
	    private PrintWriter out = null;
	    private ObjectInputStream in = null;
		
	}
	public class TestMatchMaker {
		
		public static void main(String[] args) {
			MatchMaker 2 myServer = new MatchMaker();
			myServer.runServer();
			
			//aTestServer.listenSocket();

		}
}

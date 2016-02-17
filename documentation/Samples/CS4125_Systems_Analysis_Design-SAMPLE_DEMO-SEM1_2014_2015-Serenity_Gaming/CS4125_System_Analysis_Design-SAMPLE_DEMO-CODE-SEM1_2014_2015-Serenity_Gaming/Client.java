package SysAnalysis.src.matchmaking;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import SysAnalysis.src.sysAnalysis.CommonClasses.Player;

public class Client extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7198285155318348542L;
	private JButton joinGame = new JButton("Join Game");
	private JButton getStatus = new JButton("Get status");
	private JTextField messages = new JTextField("");
	private JPanel clientScreen;
	private JPanel buttons;
	private Socket socket = null;
	private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
	private boolean queued = false;
	private Player thePlayer;
	private Object object;
	Client()
	{
		super("A Player");
		System.out.println("Enter player name");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		thePlayer = new Player();
		thePlayer.setName(name);
		JFrame.setDefaultLookAndFeelDecorated(true);
		joinGame.addActionListener(this);
		clientScreen = new JPanel();
		clientScreen.setLayout(new GridLayout(1,1));
		clientScreen.add(messages);
		add(clientScreen);
	}
	public void joinGame()
	{
		//queued = true;
		messages.setText(thePlayer.getFirstName() + " Joined Queue");
		try {
			out.writeObject(thePlayer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
	}
	public void listenSocket() {
        //Create socket connection
        try {
        	//Create socket on 4444 where server is currently opening socket for client-server communication
            socket = new Socket("localhost", 5432); 
            out = new ObjectOutputStream(socket.getOutputStream());
           // joinGame();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost");
            System.exit(1);
        } catch  (IOException e) {
        	System.out.println(e);
            System.out.println("No I/O (Check Server is running)");
            //System.exit(1);
        }
    }
	public static void main (String[]args)
	 {
	    Client myApp = new Client();
	    myApp.listenSocket();
	    myApp.setSize(100, 100);
	    myApp.setLocationRelativeTo(null);
	    myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    myApp.setResizable(false);
	    myApp.setVisible(true);
	    myApp.joinGame();
	    
	 }

}

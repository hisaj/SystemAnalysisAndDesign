package test.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import test.rmi.Constant;

public class RMIServer {
	
	/**
	 * 
	 * @param args
	 * @throws RemoteException
	 * @throws AlreadyBoundException 
	 */
	public static void main(String[] args) throws RemoteException, AlreadyBoundException
	{
		RemoteImpl impl = new RemoteImpl();
		Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
		registry.bind(Constant.RMI_ID, impl);
		System.out.println("Server has started");
	}
}

package test.client;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

import test.rmi.Constant;
import test.rmi.I_RemoteDatabaseMgr;

public class TestClient {
	public static void main(String[] args) throws Exception
	{
		Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
		I_RemoteDatabaseMgr lookup = (I_RemoteDatabaseMgr) registry.lookup(Constant.RMI_ID);
		//Call the methods required - lookup.myMethod();
		System.out.println(lookup.getPlayerDetails("cianosull"));
	}
}

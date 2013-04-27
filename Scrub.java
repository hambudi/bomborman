package bomborman;
import java.awt.event.KeyEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Scrub extends Remote {
	public void map(String[][] s, int[][] c, int[][] r) throws RemoteException ;
	public void players(int i,String s,int x,int y) throws RemoteException;
	public int move() throws RemoteException;
	public void connect() throws RemoteException;
	public KeyEvent get() throws RemoteException;
}

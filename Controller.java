package bomborman;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

public class Controller implements Scrub {
	public String[][] image = new String[16][16];
	public boolean conn = false;
	public String s1;
	public int x1;
	public int y1;
	public String s2;
	public int x2;
	public int y2;
	public int[][] c;
	public int[][] r;
	public KeyEvent e;
	public void map(String[][] s, int[][] _c, int[][] _r) throws RemoteException {
		String[] ss;
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				if(s[i][j] != null){
					ss= s[i][j].split("/");
					image[i][j] = ss[ss.length-1];

				}else{
					image[i][j] = null;
				}
			}
		}
		c = _c;
		r = _r;
	}

	public void players(int i,String s, int x, int y) throws RemoteException {
		if(i==1){
			x1 = x-16;
			y1 = y-16;
			String[] ss = s.split("/");
			s1 = ss[ss.length-1];
		}else{
			x2 = x-16;
			y2 = y-16;
			String[] ss = s.split("/");
			s2= ss[ss.length-1];
		}	
	}

	public int move() throws RemoteException {
		return 0;
	}
	public void connect() throws RemoteException{
		conn = true;
	}

	public KeyEvent get() throws RemoteException{
		KeyEvent e1 = e;
		e = null;
		return e1;
	}
}

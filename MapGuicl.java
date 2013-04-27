package bomborman;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapGuicl extends JPanel{
	private Graphics2D g;
	private BufferedImage draw;
	public Controller c;
	public MapGuicl(){
		try{
			draw = new BufferedImage(32*16,32*16,BufferedImage.TYPE_INT_RGB);
			g = draw.createGraphics();
			c = new Controller();
			System.setProperty("java.rmi.server.codebase", Scrub.class.getProtectionDomain().getCodeSource().getLocation().toString());
			Scrub s= (Scrub)UnicastRemoteObject.exportObject(c,0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("scrub", s);
			System.err.println("Connection done");

		}catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Connection failed");
		}
		while(c.conn == false){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public void render(){
		BufferedImage backgroundimage = null;
		try {
			URI backgroundurl = getClass().getResource("background.png").toURI();
			backgroundimage = ImageIO.read(new File(backgroundurl));
		} catch (Exception e) {
			System.out.println("Could not load file");
		}
		g.drawImage(backgroundimage,0,0,null);
    	for(int i=0;i<16;i++){
    		for(int j=0;j<16;j++){
    			if(c.image[i][j] != null){
        			BufferedImage temp = null;
        			try {
        		        URI uri = getClass().getResource(c.image[i][j]).toURI();
        			    temp = ImageIO.read(new File(uri));
        			} catch (Exception e) {
        				System.err.println("Could not load file "+ c.image[i][j]);
        			}
        			if(c.image[i][j] != null){
            			g.drawImage(temp,c.c[i][j],c.r[i][j],null);
        			}
    			}
    		}
    	}
		BufferedImage temp1 = null;
		BufferedImage temp2 = null;
    	try {
	        URI uri1 = getClass().getResource(c.s1).toURI();
	        URI uri2 = getClass().getResource(c.s2).toURI();

		    temp1 = ImageIO.read(new File(uri1));
		    temp2 = ImageIO.read(new File(uri2));
		} catch (Exception e) {
			System.err.println("Could not load file ");
		}
		g.drawImage(temp1,c.x1,c.y1,null);
		g.drawImage(temp2,c.x2,c.y2,null);

	}
	public void paint(Graphics g)
	{
		((Graphics2D)g).drawImage(draw,0,0,null);
	}

}


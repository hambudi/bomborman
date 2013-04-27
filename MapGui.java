/*
 * This class must have all the gui implementation of the game.
 */
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

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapGui extends JPanel{
	private Graphics2D g;
	private Map map;
	private BufferedImage draw;
	private int sizex;
	private int sizey;
	private int dx;
	private int dy;
	private Player p;
	private Player p2;

	MapBasicBlock[][] m;
    public MapGui(){
    	map = Map.getInstance(0, 0);
    	dx = 32;
    	dy = 32;
    	sizex = map.cols*dx;
    	sizey = map.rows*dy;
    	draw = new BufferedImage(sizex,sizey,BufferedImage.TYPE_INT_RGB);
    	m = map.map;
    	p = map.player;
    	p2 = map.player2;
    	g = draw.createGraphics();
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
    	for(int i=0;i<map.rows;i++){
    		for(int j=0;j<map.cols;j++){
    			if(m[i][j] == null){
    				map.im[i][j] = null;
    			}else if(m[i][j].getBlockType() == Types.BlockType.ENEMY){
    				int c = ((Enemy)m[i][j]).getX() -16;
    				int r = ((Enemy)m[i][j]).getY()-16;
        			g.drawImage(m[i][j].getImage(),c,r,null);
        			map.c[i][j] = c;
        			map.r[i][j] = r;
    			}else{
    				int c = m[i][j].getPosition().getColumn()*dx;
    				int r = m[i][j].getPosition().getRow()*dy;
        			g.drawImage(m[i][j].getImage(),c,r,null);
        			map.c[i][j] = c;
        			map.r[i][j] = r;
    			}
    		}
    	}
    	if(map.player.isAlive() == true){
    		g.drawImage(p.getImage(),p.getX()-16,p.getY()-16,null);	
    	}
    	if(map.player2.isAlive() == true){
    		g.drawImage(p2.getImage(),p2.getX()-16,p2.getY()-16,null);    		
    	}
    	
    	try {
			map.scr.map(map.im, map.c, map.r);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		try {
				map.move(map.scr.get(), 2);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	try {
			map.scr.connect();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void paint(Graphics g)
    {
    	((Graphics2D)g).drawImage(draw,0,0,null);
    }

}


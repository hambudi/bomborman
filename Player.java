/*
 * this the player class, it will maintain the data of player and have 
 * approperiate methods.
 * 
 */
package bomborman;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import bomborman.Types.*;


public class Player extends MapBasicBlock {
	ArrayList<BufferedImage> im;
	ArrayList<URI> u;
	int index;
    private boolean alive;
    private int x;
    private int y;
    private Map map = Map.getInstance(0,0);
    Types.Move mov;
    private int timer;
    private boolean dying;
    public int vel;
    public int avail;
    public int rad;
    public int id;
    
    public Player(Types.BlockType _blockType, Position _position,int ind){
        
        super( _blockType,_position);
        x = _position.getColumn()*32+16;
        y = _position.getRow()*32+16;
        setAlive(true);
        dying =true;
        id = ind;
        im = new ArrayList<BufferedImage>();
        u = new ArrayList<URI>();
        
    	try {
    		u.add(getClass().getResource("down1.png").toURI());
    		u.add(getClass().getResource("down2.png").toURI());
    		u.add(getClass().getResource("down3.png").toURI());
    		u.add(getClass().getResource("left1.png").toURI());
    		u.add(getClass().getResource("left2.png").toURI());
    		u.add(getClass().getResource("left3.png").toURI());
    		u.add(getClass().getResource("right1.png").toURI());
    		u.add(getClass().getResource("right2.png").toURI());
    		u.add(getClass().getResource("right3.png").toURI());
    		u.add(getClass().getResource("up1.png").toURI());
    		u.add(getClass().getResource("up2.png").toURI());
    		u.add(getClass().getResource("up3.png").toURI());
    		u.add(getClass().getResource("bmand1.png").toURI());
    		u.add(getClass().getResource("bmand2.png").toURI());
    		u.add(getClass().getResource("bmand3.png").toURI());
    		u.add(getClass().getResource("bmand4.png").toURI());
    		u.add(getClass().getResource("bmand5.png").toURI());
    		u.add(getClass().getResource("bmand6.png").toURI());
    		
            im.add(ImageIO.read(new File(u.get(0))));
            im.add(ImageIO.read(new File(u.get(1))));
            im.add(ImageIO.read(new File(u.get(2))));
            im.add(ImageIO.read(new File(u.get(3))));
            im.add(ImageIO.read(new File(u.get(4))));
            im.add(ImageIO.read(new File(u.get(5))));
            im.add(ImageIO.read(new File(u.get(6))));
            im.add(ImageIO.read(new File(u.get(7))));
            im.add(ImageIO.read(new File(u.get(8))));
            im.add(ImageIO.read(new File(u.get(9))));
            im.add(ImageIO.read(new File(u.get(10))));
            im.add(ImageIO.read(new File(u.get(11))));
            im.add(ImageIO.read(new File(u.get(12))));
            im.add(ImageIO.read(new File(u.get(13))));
            im.add(ImageIO.read(new File(u.get(14))));
            im.add(ImageIO.read(new File(u.get(15))));
            im.add(ImageIO.read(new File(u.get(16))));
            im.add(ImageIO.read(new File(u.get(17))));
    		 
    	} catch (Exception e) {
    		System.out.println("Could not load file");
    	}
    	index = 0;
        mov = Types.Move.DOWN;
        timer =0;
        vel =3;
        avail=1;
        rad = 1;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public void move(Move m){
    	index++;
    	if(m == Move.DOWN){
    		y+=vel;
    		if(y-10>(position.getRow()+1)*32){
    			position = new Position(position.getRow()+1,position.getColumn());

    		}
    		mov=m;
    	}else if(m == Move.UP){
    		y-=vel;
    		if(y+10<(position.getRow())*32){
    			position = new Position(position.getRow()-1,position.getColumn());
    		}
    		mov=m;
    	}else if(m == Move.LEFT){
    		x-=vel;
    		if(x+10<(position.getColumn())*32){
    			position = new Position(position.getRow(),position.getColumn()-1);
    		}
    		mov=m;
    	}else if(m == Move.RIGHT){
    		x+=vel;
    		if(x-10>(position.getColumn()+1)*32){
    			position = new Position(position.getRow(),position.getColumn()+1);
    		}
    		mov=m;
    	}else if(m == Move.PLACE_BOMB){
			map.map[y/32][x/32] = new Bomb(BlockType.BOMB,new Position(y/32,x/32),rad,id);
			avail--;
			
    	}
    }
    public void explode(){
    	System.out.println("endgame");
    	this.dying = false;
    }
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		
		this.alive = alive;
	}
	public Image getImage(){
    	if(dying == false){
        	timer++;
        	if(timer<(Map.getInstance(1, 1).antime)){
        		index = timer/(Map.getInstance(1, 1).antime/6);;
        		if(id ==1 ){
        			map.p1 = u.get(index+12).getPath();
        		}else if(id ==2){
        			map.p2 = u.get(index+12).getPath();
        		}
        		try {
					Map.getInstance(1, 1).scr.players(id,u.get(index+12).getPath(),x,y);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return im.get(index +12);
        	}
        	alive = false;
        	Map m = Map.getInstance(1, 1);
        	m.map[position.getRow()][position.getColumn()] =null;
    	}
		if(mov == Move.UP){
			if(id ==1 ){
    			map.p1 = u.get(9+index%3).getPath();
    		}else if(id ==2){
    			map.p2 = u.get(9+index%3).getPath();
    		}

    		try {
				Map.getInstance(1, 1).scr.players(id,u.get(9+index%3).getPath(),x,y);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return im.get(9+index%3);
		}else if(mov == Move.DOWN){
			if(id ==1 ){
    			map.p1 = u.get(index%3).getPath();
    		}else if(id ==2){
    			map.p2 = u.get(index%3).getPath();
    		}
			try {
				Map.getInstance(1, 1).scr.players(id,u.get(index%3).getPath(),x,y);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return im.get(index%3);
		}else if(mov == Move.LEFT){
			if(id ==1 ){
    			map.p1 = u.get(3+index%3).getPath();
    		}else if(id ==2){
    			map.p2 = u.get(3+index%3).getPath();
    		}
			try {
				Map.getInstance(1, 1).scr.players(id,u.get(3+index%3).getPath(),x,y);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return im.get(3+index%3);
		}else if(mov == Move.RIGHT){
			if(id ==1 ){
    			map.p1 = u.get(6+index%3).getPath();
    		}else if(id ==2){
    			map.p2 = u.get(6+index%3).getPath();
    		}
			try {
				Map.getInstance(1, 1).scr.players(id,u.get(6+index%3).getPath(),x,y);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return im.get(6+index%3);
		}else{
			return null;
		}
	}
}

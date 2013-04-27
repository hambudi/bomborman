/*
 * their are alos enemies to the player that will keep on moving randomly for 
 * ( You are welcome to make them intelligent and give yourself a tough time ;) )
 * if the player touches them then player will die. When the die by the fire/explosion
 * they will also give a powerup but on random basis.
 */
package bomborman;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import bomborman.Types.Move;




public class Enemy extends MapBasicBlock implements Runnable{
	ArrayList<BufferedImage> im;
	int index;
	private boolean alive;
	private int x;
	private int y;
	private Map map = Map.getInstance(0,0);
	Types.Move mov;
	private int timer;
	private boolean dying;
	public int vel;
	ArrayList<URI> u;

	public Enemy(Types.BlockType _blockType, Position _position){

		super( _blockType,_position);
		x = _position.getColumn()*32+16;
		y = _position.getRow()*32+16;
		setAlive(true);
		dying =true;
		u = new ArrayList<URI>();

		im = new ArrayList<BufferedImage>();
		try {
			u.add(getClass().getResource("enemy1.png").toURI());
			u.add(getClass().getResource("enemy1d.png").toURI());
			im.add(ImageIO.read(new File(u.get(0))));
			im.add(ImageIO.read(new File(u.get(1))));
		} catch (Exception e) {
			System.out.println("Could not load file");
		}
		index = 0;
		mov = Types.Move.DOWN;
		timer =0;
		vel =1;
		
		Thread t = new Thread(this);
		t.start();
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
			if(y-12>(position.getRow()+1)*32){
				map.map[position.getRow()][position.getColumn()] = null;
				position = new Position(position.getRow()+1,position.getColumn());
				map.map[position.getRow()][position.getColumn()]=this;

				x = position.getColumn()*32+16;
				y = position.getRow()*32+16;
			}
			mov=m;
		}else if(m == Move.UP){
			y-=vel;
			if(y+12<(position.getRow())*32){
				map.map[position.getRow()][position.getColumn()] = null;
				position = new Position(position.getRow()-1,position.getColumn());
				map.map[position.getRow()][position.getColumn()]=this;

				x = position.getColumn()*32+16;
				y = position.getRow()*32+16;
			}
			mov=m;
		}else if(m == Move.LEFT){
			x-=vel;
			if(x+12<(position.getColumn())*32){
				map.map[position.getRow()][position.getColumn()] = null;
				position = new Position(position.getRow(),position.getColumn()-1);
				map.map[position.getRow()][position.getColumn()]=this;
				x = position.getColumn()*32+16;
				y = position.getRow()*32+16;
			}
			mov=m;
		}else if(m == Move.RIGHT){
			x+=vel;
			if(x-12>(position.getColumn()+1)*32){
				map.map[position.getRow()][position.getColumn()] = null;
				position = new Position(position.getRow(),position.getColumn()+1);
				map.map[position.getRow()][position.getColumn()]=this;
				x = position.getColumn()*32+16;
				y = position.getRow()*32+16;
			}
			mov=m;
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
		Map m = Map.getInstance(1, 1);
		if(dying == false){
			timer++;
			if(timer<(Map.getInstance(1, 1).antime)){
				m.im[position.getRow()][position.getColumn()] = u.get(1).getPath();
				return im.get(1);
			}
			alive = false;
			m.map[position.getRow()][position.getColumn()] =null;
		}
		m.im[position.getRow()][position.getColumn()] = u.get(0).getPath();
		return im.get(0);
	}
	public void run(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(timer<(Map.getInstance(1, 1).antime)){
			try {
				Thread.sleep(Map.getInstance(1, 1).getTime());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random rand = new Random();
			int r = rand.nextInt(4);
			MoveEvaluator move = new MoveEvaluator();
			
			if(r==0){
				move.isValidMove(this,this.mov);
			}else if(r==1){
				move.isValidMove(this,this.mov);
			}else if(r==2){
				move.isValidMove(this,this.mov);
			}else if(r==3){
				move.isValidMove(this,this.mov);
			}
		}
	}
}

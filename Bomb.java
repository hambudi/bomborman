/*
 * This class will contain the implementation of bomb, you have to keep in mind
 * the following points.
 * 1- there is a time duration between the bomb is placed and it explodes
 * 2- if an other bomb is in the range of the bomb that has just exploded
 *    then the other one will also explode.
 * 3- the range of bomb can change depending upon the powerup of the player.
 * 4- You have to extend this assignment for multiplayer afterwards so you do 
 *    have a system to identify that who installed this bomb but for this part 
 *    you may leave this implemntation 
 */
package bomborman;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bomb extends MapBasicBlock implements Runnable {
	public int range;
	public int tick;
	ArrayList<BufferedImage> im;
	ArrayList<URI> u;
	boolean alive; 
	public int index;
	public int timer;
	public int player;

	public Bomb(Types.BlockType _blockType, Position _position,int r,int p){
		super( _blockType,_position);
		range =r;
		tick =125;
		im = new ArrayList<BufferedImage>();
		u = new ArrayList<URI>();
		alive = true;
		player = p;
		index = 0;
		timer = 0;
		try {
			u.add(getClass().getResource("bomb1.png").toURI());
			u.add(getClass().getResource("fcenter1.png").toURI());
			u.add(getClass().getResource("fcenter2.png").toURI());
			u.add(getClass().getResource("fcenter3.png").toURI());
			im.add(ImageIO.read(new File(u.get(0))));
			im.add(ImageIO.read(new File(u.get(1))));
			im.add(ImageIO.read(new File(u.get(2))));
			im.add(ImageIO.read(new File(u.get(3))));

		} catch (Exception e) {
			System.out.println("Could not load file");
		}
		Thread t = new Thread(this);
		t.start();
	}
	public void explode(){
		if (alive == false){
			return;
		}
		alive = false;
		
		Map m = Map.getInstance(1, 1);
		for(int i=0;i<range;i++){
			if(m.map[position.getRow()][position.getColumn()+i+1] != null){
				m.map[position.getRow()][position.getColumn()+i+1].explode();
				break;
			}else{
				if(i==range-1){
					m.map[position.getRow()][position.getColumn()+i+1] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow(),position.getColumn()+i+1),4);
				}else{
					m.map[position.getRow()][position.getColumn()+i+1] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow(),position.getColumn()+i+1),6);	
				}
			}
			if(m.player.getPosition().getRow()==position.getRow() && m.player.getPosition().getColumn()==position.getColumn()+i+1){
				m.player.explode();
				break;
			}
			if(m.player2.getPosition().getRow()==position.getRow() && m.player2.getPosition().getColumn()==position.getColumn()+i+1){
				m.player2.explode();
				break;
			}
		}
		for(int i=0;i<range;i++){
			if(m.map[position.getRow()][position.getColumn()-i-1] != null){
				m.map[position.getRow()][position.getColumn()-i-1].explode();
				break;
			}else{
				if(i==range-1){
					m.map[position.getRow()][position.getColumn()-i-1] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow(),position.getColumn()-i-1),3);
				}else{
					m.map[position.getRow()][position.getColumn()-i-1] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow(),position.getColumn()-i-1),6);
					
				}
			}
			if(m.player.getPosition().getRow()==position.getRow() && m.player.getPosition().getColumn()==position.getColumn()-i-1){
				m.player.explode();
				break;
			}
			if(m.player2.getPosition().getRow()==position.getRow() && m.player2.getPosition().getColumn()==position.getColumn()-i-1){
				m.player2.explode();
				break;
			}
		}
		for(int i=0;i<range;i++){
			if(m.map[position.getRow()-i-1][position.getColumn()] != null){
				m.map[position.getRow()-i-1][position.getColumn()].explode();
				break;
			}else{
				if(i==range-1){
					m.map[position.getRow()-i-1][position.getColumn()] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow()-i-1,position.getColumn()),1);
				}else{
					m.map[position.getRow()-i-1][position.getColumn()] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow()-i-1,position.getColumn()),5);
					
				}
			}
			if(m.player.getPosition().getRow()==position.getRow()-i-1 && m.player.getPosition().getColumn()==position.getColumn()){
				m.player.explode();
				break;
			}
			if(m.player2.getPosition().getRow()==position.getRow()-i-1 && m.player2.getPosition().getColumn()==position.getColumn()){
				m.player2.explode();
				break;
			}
		}
		for(int i=0;i<range;i++){
			if(m.map[position.getRow()+i+1][position.getColumn()] != null){
				m.map[position.getRow()+i+1][position.getColumn()].explode();
				break;
			}else{
				if(i==range-1){
					m.map[position.getRow()+i+1][position.getColumn()] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow()+i+1,position.getColumn()),2);
				}else{
					m.map[position.getRow()+i+1][position.getColumn()] = new EmptyBlock(Types.BlockType.EMPTY,new Position(position.getRow()+i+1,position.getColumn()),5);
					
				}
			}
			if(m.player.getPosition().getRow()==position.getRow()+i+1 && m.player.getPosition().getColumn()==position.getColumn()){
				m.player.explode();
				break;
			}
			if(m.player2.getPosition().getRow()==position.getRow()+i+1 && m.player2.getPosition().getColumn()==position.getColumn()){
				m.player2.explode();
				break;
			}
		}
		if(m.player.getPosition().getRow()==position.getRow() && m.player.getPosition().getColumn()==position.getColumn()){
			m.player.explode();
		}
		if(m.player2.getPosition().getRow()==position.getRow() && m.player2.getPosition().getColumn()==position.getColumn()){
			m.player2.explode();
		}
		alive = false;
	}
	public Image getImage(){
		Map m = Map.getInstance(1, 1);

		if(alive == false){

			timer++;
			if(timer<Map.getInstance(1, 1).antime){
				index = timer/(Map.getInstance(1, 1).antime/3);
				m.im[position.getRow()][position.getColumn()] = u.get(1+index).getPath();
				return im.get(1+index);
			}

			m.map[position.getRow()][position.getColumn()] = null;
			if(player == 1){
				m.player.avail++;				
			}else if(player == 2){
				m.player2.avail++;
			}else{
				//herp a derp a derp
			}
		}
		m.im[position.getRow()][position.getColumn()] = u.get(index%3).getPath();
		return im.get(index%3);
	}
	public void run(){
		
		while(true){
			try {
				Thread.sleep(Map.getInstance(1, 1).getTime()*2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.tick<=0){
				this.explode();
			}else{
				this.tick--;
			}
		}
	}
}

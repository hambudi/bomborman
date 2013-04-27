/*
 * 
 * This class will holde all the information of the map and will have approperiate
 * methods to perform diffrent tasks on map.
 */
package bomborman;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import javax.imageio.ImageIO;

import bomborman.Types.BlockType;


public class Map {

	static Map mapinst;

	public int rows, cols;
	public MapBasicBlock[][] map; 
	public Player player;
	public Player player2;
	MoveEvaluator move;
	MoveExecutor exec;
	public int antime;
	public String p1;
	public String p2;
	public String[][] im;
	public int[][] c;
	public int[][] r;
	public Scrub scr;

	private Map(int _rows, int _cols){
		rows = _rows;
		cols = _cols;
		map = new MapBasicBlock[rows][cols];
		antime = 12;
		im = new String[rows][cols];
		c = new int[rows][cols];
		r = new int[rows][cols];
		
    	try{
    		Registry register = LocateRegistry.getRegistry();
    		scr = (Scrub)register.lookup("scrub");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
	}

	boolean isFree(Position pos){
		if(map[pos.getRow()][pos.getColumn()] == null){
			return true;
		}else{
			return false;
		}
	}

	public static Map getInstance(int i, int j){
		if (mapinst == null){
			mapinst = new Map(i,j);
		}
		return mapinst;
	}

	public void generateMap(){

		move = new MoveEvaluator();
		exec = new MoveExecutor();


		Random rand = new Random();
		BufferedImage wlimage = null;
		player = new Player(BlockType.PLAYER,new Position(1,1),1);
		player2 = new Player(BlockType.PLAYER,new Position(13,13),2);

//		try {
//			URI wlurl = getClass().getResource("ublock.png").toURI();
//			wlimage = ImageIO.read(new File(wlurl));
//		} catch (Exception e) {
//			System.out.println("Could not load file");
//		}
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
//				System.out.println("rows " + i + " cols " + j);
				if(i==0 || j==0 || (i%2 == 0 && j%2 == 0) || (j==cols-1)||(i==rows-1)){
					map[i][j] = new UnbreakableBlock(BlockType.UNBREKABLE,new Position(i,j));
				}else if(i == 1 && j == 1){

				}else if(i == 1 && j == 1){

				}else if((i==2&&j==1)||(i==1&&j==2)){
					continue;
				}else if((i==14&&j==13)||(i==13&&j==14)||(i==12&&j==13)||(i==13&&j==12)||(i==13&&j==13)){
					continue;
				}else if(rand.nextInt(15)<7){
					map[i][j] = new BreakableBlock(BlockType.BREAKABLE,new Position(i,j));
				}else if((rand.nextInt(25)<2)&& (i>2) &&(j>2)){
					map[i][j] = new Enemy(BlockType.ENEMY,new Position(i,j));
					
					map[i-1][j] = null;
					map[i][j-1] = null;
				}
			}
		}
	}

	public void move(KeyEvent e,int i){
		if(e==null){
			return;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(move.isValidMove(player,Types.Move.LEFT) && i==1){
				exec.executeMove(player,Types.Move.LEFT);
			}
			if(move.isValidMove(player2,Types.Move.LEFT) && i==2){
				exec.executeMove(player2,Types.Move.LEFT);
			}
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(move.isValidMove(player,Types.Move.RIGHT) && i==1){
				exec.executeMove(player,Types.Move.RIGHT);
			}
			if(move.isValidMove(player2,Types.Move.RIGHT) && i==2){
				exec.executeMove(player2,Types.Move.RIGHT);
			}
		}else if(e.getKeyCode()==KeyEvent.VK_UP){
			if(move.isValidMove(player,Types.Move.UP) && i==1){
				exec.executeMove(player,Types.Move.UP);
			}
			if(move.isValidMove(player2,Types.Move.UP) && i==2){
				exec.executeMove(player2,Types.Move.UP);
			}
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			if(move.isValidMove(player,Types.Move.DOWN) && i==1){
				exec.executeMove(player,Types.Move.DOWN);
			}
			if(move.isValidMove(player2,Types.Move.DOWN) && i==2){
				exec.executeMove(player2,Types.Move.DOWN);
			}
		}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
			if(move.isValidMove(player,Types.Move.PLACE_BOMB) && i==1){
				exec.executeMove(player,Types.Move.PLACE_BOMB);
			}
			if(move.isValidMove(player2,Types.Move.PLACE_BOMB) && i==2){
				exec.executeMove(player2,Types.Move.PLACE_BOMB);
			}
		}else{
			return;
		}
	}
	public int getTime(){
		return 10;
	}

}

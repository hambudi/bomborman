/*
 * This is for breakable block of the map. This lock can be exploded by the 
 * explosion of bomb and it may contain the powerup that can be used once 
 * it's exploded. 
 */
package bomborman;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class BreakableBlock extends MapBasicBlock {    
	ArrayList<BufferedImage> im;
	int index;
	int timer;
	boolean alive;
	ArrayList<URI> u;
    public BreakableBlock(Types.BlockType _blockType, Position _position){
        super( _blockType,_position);
        im = new ArrayList<BufferedImage>();
        u = new ArrayList<URI>();
    	try {
    		u.add(getClass().getResource("bblock.png").toURI());
    		u.add(getClass().getResource("bblockd1.png").toURI());
    		u.add(getClass().getResource("bblockd2.png").toURI());
    		u.add(getClass().getResource("bblockd3.png").toURI());
    		u.add(getClass().getResource("bblockd4.png").toURI());
    		u.add(getClass().getResource("bblockd5.png").toURI());
    		u.add(getClass().getResource("bblockd6.png").toURI());
    		im.add(ImageIO.read(new File(u.get(0))));
    		im.add(ImageIO.read(new File(u.get(1))));
    		im.add(ImageIO.read(new File(u.get(2))));
    		im.add(ImageIO.read(new File(u.get(3))));
    		im.add(ImageIO.read(new File(u.get(4))));
    		im.add(ImageIO.read(new File(u.get(5))));
    		im.add(ImageIO.read(new File(u.get(6))));
    	} catch (Exception e) {
    		System.out.println("Could not load file");
    	}
    	index = 0;
    	timer = 0;
    	alive = true;
    }
    public Image getImage(){
     Map m = Map.getInstance(1,1);
    	if(alive == false){
        	timer++;
        	if(timer<Map.getInstance(1, 1).antime){
        		index = timer/(Map.getInstance(1, 1).antime/6);
        		m.im[position.getRow()][position.getColumn()] = u.get(index+1).getPath();

        		return im.get(index+1);
        	}

        	m.map[position.getRow()][position.getColumn()] =null;
    		Random rand = new Random();
    		int r = rand.nextInt(15);
    		if(r<3){
            	m.map[position.getRow()][position.getColumn()] = new PowerUp(Types.BlockType.POWER_UP,new Position(position.getRow(),position.getColumn()), Types.PowerUps.BOMBS_UP);
    		}else if(r>=3 && r<6){
            	m.map[position.getRow()][position.getColumn()] = new PowerUp(Types.BlockType.POWER_UP,new Position(position.getRow(),position.getColumn()), Types.PowerUps.RANGE_UP);
    		}else if(r>=6 && r<9){
            	m.map[position.getRow()][position.getColumn()] = new PowerUp(Types.BlockType.POWER_UP,new Position(position.getRow(),position.getColumn()), Types.PowerUps.SPEED_UP);
    		}
    	}
		m.im[position.getRow()][position.getColumn()] = u.get(index).getPath();

    	return im.get(index);
    }
    public void explode(){
    	alive = false;
    }
}

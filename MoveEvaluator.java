/**
 * This class will focus on the implementation of the whether the move picked by
 * a player is valid ( It must not contain any gui related component and
 * nothing of gui can be changed from here). you can define other methods 
 * to assist your self and manage your code.
 * 
 * You can make constructor of this class if you feel need for it.
 * 
 */
package bomborman;


import java.util.Random;

import bomborman.Types.*;


public class MoveEvaluator {
    public MoveEvaluator(){
    	
    }
	Map map = Map.getInstance(0,0);
    public boolean isValidMove( Player player, Move move){
    		if(move != Move.PLACE_BOMB){
    			Position next = player.getPosition().increment(move);
    			if(map.map[next.getRow()][next.getColumn()] == null){
    				return true;
    			}else if(map.map[next.getRow()][next.getColumn()].isofType(BlockType.POWER_UP)){
    				((PowerUp)map.map[next.getRow()][next.getColumn()]).pickup(player.id);
    				return true;
    			}
    			else if(map.map[next.getRow()][next.getColumn()].isofType(BlockType.ENEMY)){
    				player.explode();
    			}
    			if(map.isFree(next)){
    				return true;
    			}else{
    				return false;
    			}
    		}else if(move == Move.PLACE_BOMB){
    			if(player.avail >0){;
    				return true;
    			}
    		}
            return false;
    }
    public synchronized boolean isValidMove( Enemy player, Move move){
		if(move != Move.PLACE_BOMB){
			Position next = player.getPosition().increment(move);
			if(map.isFree(next)){
				player.move(move);
				return true;
			}else{
				Random rand = new Random();
				int r = rand.nextInt(4);
				if(r==0){
					next = player.getPosition().increment(Types.Move.DOWN);
					if(player.getPosition().getColumn() == map.player.getPosition().getColumn() && player.getPosition().getRow() == map.player.getPosition().getRow()){
						map.player.explode();
					}
					if(map.isFree(next)){
						player.move(Types.Move.DOWN);
						return true;
					}
				}else if(r==1){
					next = player.getPosition().increment(Types.Move.UP);
					if(player.getPosition().getColumn() == map.player.getPosition().getColumn() && player.getPosition().getRow() == map.player.getPosition().getRow()){
						map.player.explode();
					}
					if(map.isFree(next)){
						player.move(Types.Move.UP);
						return true;
					}
				}else if(r==2){
					next = player.getPosition().increment(Types.Move.LEFT);
					if(player.getPosition().getColumn() == map.player.getPosition().getColumn() && player.getPosition().getRow() == map.player.getPosition().getRow()){
						map.player.explode();
					}
					if(map.isFree(next)){
						player.move(Types.Move.LEFT);
						return true;
					}
				}else if(r==3){
					next = player.getPosition().increment(Types.Move.RIGHT);
					if(player.getPosition().getColumn() == map.player.getPosition().getColumn() && player.getPosition().getRow() == map.player.getPosition().getRow()){
						map.player.explode();
					}
					if(map.isFree(next)){
						player.move(Types.Move.RIGHT);
						return true;
					}
				}
				return false;
			}
		}
        return false;
    }
}

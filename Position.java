/*
 * This class assist in handling with the positions of diffrent objects. 
 */
package bomborman;

import bomborman.Types.Move;




public class Position {
    
    private int row, col;
    
    public Position(int _row, int _col){
        row = _row;
        col = _col;
    }
    
    public boolean equals(Position position){
    
        if( position.getColumn() == col && position.getRow() == row){
            return true;
        }else{
            return false;
        }
    }
    
    public int getColumn(){ return col;}
    
    public int getRow(){ return row;}
    
    public void setPosition(Position position){
    
            row = position.getRow();
            col = position.getColumn();
    }
    
    public void setPosition(int _row, int _col){
    
            row = _row;
            col = _col;
    }
    
    public Position increment(Move m){
    	Position temp;
    	if(m == Move.UP){
    		temp = new Position(row-1,col);
    	}else if(m==Move.DOWN){
    		temp = new Position(row+1,col);
    	}else if(m==Move.RIGHT){
    		temp = new Position(row,col+1);
    	}else if(m==Move.LEFT){
    		temp = new Position(row,col-1);    		
    	}else{
    		temp = new Position(row,col);
    	}
    	return temp;
    }
}

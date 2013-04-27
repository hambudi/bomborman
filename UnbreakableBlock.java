/*
 * This is for unbreakable block of the map. This kind of block cannot be broken 
 * by any kind of explosion. 
 */
package bomborman;

import java.awt.Image;
import java.io.File;
import java.net.URI;

import javax.imageio.ImageIO;




public class UnbreakableBlock extends MapBasicBlock {
    URI u;
    public UnbreakableBlock(Types.BlockType _blockType, Position _position){
        
        super( _blockType,_position);
        try {
			u = getClass().getResource("ublock.png").toURI();
			image = ImageIO.read(new File(u));
		} catch (Exception e) {
			System.out.println("Could not load file");
		}
    }
    
    public Image getImage(){
		Map m = Map.getInstance(1, 1);
		m.im[position.getRow()][position.getColumn()] = u.getPath();

		return image;
    }
}

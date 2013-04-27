/*
 * This is for empty block of the map i-e the paths on the map will actually be
 * empty blocks.
 */
package bomborman;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class EmptyBlock extends MapBasicBlock {
	ArrayList<BufferedImage> im;
	int index;
	int timer;
	int dir;
	ArrayList<URI> u;
	public EmptyBlock(Types.BlockType _blockType, Position _position, int _dir){
		super( _blockType,_position);
		im = new ArrayList<BufferedImage>();
		u = new ArrayList<URI>();
		dir = _dir;
		if (dir == 1){
			try {
				u.add(getClass().getResource("fup2.png").toURI());
				u.add(getClass().getResource("fup1.png").toURI());
				u.add(getClass().getResource("fup3.png").toURI());
				im.add(ImageIO.read(new File(u.get(0))));

				im.add(ImageIO.read(new File(u.get(1))));
				im.add(ImageIO.read(new File(u.get(2))));

			} catch (Exception e) {
				System.out.println("3");
			}
		}else if(dir == 2){
			try {
				u.add(getClass().getResource("fdown2.png").toURI());
				u.add(getClass().getResource("fdown1.png").toURI());
				u.add(getClass().getResource("fdown3.png").toURI());
				im.add(ImageIO.read(new File(u.get(0))));

				im.add(ImageIO.read(new File(u.get(1))));
				im.add(ImageIO.read(new File(u.get(2))));
			} catch (Exception e) {
				System.out.println("2");
			}
		}else if(dir == 3){
			try {
				u.add(getClass().getResource("fleft2.png").toURI());
				u.add(getClass().getResource("fleft1.png").toURI());
				u.add(getClass().getResource("fleft3.png").toURI());
				im.add(ImageIO.read(new File(u.get(0))));

				im.add(ImageIO.read(new File(u.get(1))));
				im.add(ImageIO.read(new File(u.get(2))));

			} catch (Exception e) {
				System.out.println("1");
			}
		}else if(dir == 4){
			try {
				u.add(getClass().getResource("fright2.png").toURI());
				u.add(getClass().getResource("fright1.png").toURI());
				u.add(getClass().getResource("fright3.png").toURI());
				im.add(ImageIO.read(new File(u.get(0))));
				im.add(ImageIO.read(new File(u.get(1))));
				im.add(ImageIO.read(new File(u.get(2))));

			} catch (Exception e) {
				System.out.println("4");
			}
		}else if(dir == 5){
			try {
				u.add(getClass().getResource("fvert2.png").toURI());
				u.add(getClass().getResource("fvert1.png").toURI());
				u.add(getClass().getResource("fvert3.png").toURI());
				im.add(ImageIO.read(new File(u.get(0))));
				im.add(ImageIO.read(new File(u.get(1))));
				im.add(ImageIO.read(new File(u.get(2))));

			} catch (Exception e) {
				System.out.println("5");
			}
		}else if(dir == 6){
			try {
				u.add(getClass().getResource("fhor2.png").toURI());
				u.add(getClass().getResource("fhor1.png").toURI());
				u.add(getClass().getResource("fhor3.png").toURI());
				im.add(ImageIO.read(new File(u.get(0))));
				im.add(ImageIO.read(new File(u.get(1))));
				im.add(ImageIO.read(new File(u.get(2))));

			} catch (Exception e) {
				System.out.println("6");
			}
		}

		index = 0;
		timer = 0;
	}
	public Image getImage(){
		Map m = Map.getInstance(1, 1);

		timer++;
		if(timer<Map.getInstance(1, 1).antime){
			index = timer/(Map.getInstance(1, 1).antime/3);
			m.im[position.getRow()][position.getColumn()] = u.get(index).getPath();

			return im.get(index);
		}
		m.map[position.getRow()][position.getColumn()] =null;
		m.im[position.getRow()][position.getColumn()] = u.get(index%3).getPath();
		return im.get(index%3);
 
	}
}

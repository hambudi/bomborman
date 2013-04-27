/*
 * Power ups are kind of bonuses or upgrade that palyer gets randomly when a 
 * breakble block is exploded by the explosion of bomb. Threre are three 
 * different types of power one increase your speed, the second one increases the 
 * range of the explosion of the bomb installed by the player and the third one 
 * increases the numbers of bombs a player can place/installed simultaneously.
 * 
 * If you want you can make three diffrent classes for each of the powerup.
 */
package bomborman;

import java.awt.Image;
import java.io.File;
import java.net.URI;

import javax.imageio.ImageIO;

import bomborman.Types.*;





public class PowerUp extends MapBasicBlock{
	URI u1;
	private PowerUps powerUp;
	public PowerUp( BlockType _blockType, Position _position, PowerUps _powerUp){

		super( _blockType,_position);

		if (_powerUp == PowerUps.SPEED_UP){
			try {
				u1 = getClass().getResource("speedUp.png").toURI();
				image = (ImageIO.read(new File(u1)));

			} catch (Exception e) {
				System.out.println("3");
			}
		}else if(_powerUp == PowerUps.BOMBS_UP){
			try {
				u1 = getClass().getResource("multibombs.png").toURI();
				image = (ImageIO.read(new File(u1)));

			} catch (Exception e) {
				System.out.println("3");
			}
		}else if(_powerUp == PowerUps.RANGE_UP){
			try {
				u1 = getClass().getResource("fireup.png").toURI();
				image = (ImageIO.read(new File(u1)));

			} catch (Exception e) {
				System.out.println("3");
			}
		}
		powerUp = _powerUp;
	}
	public Image getImage(){
		Map m = Map.getInstance(1, 1);
		m.im[position.getRow()][position.getColumn()] = u1.getPath();

		return image;
	}
	public void pickup(int id){
		if(id ==1){
			if(powerUp == PowerUps.SPEED_UP){
				Map.getInstance(1, 1).player.vel = 8;
			}else if(powerUp == PowerUps.BOMBS_UP){
				Map.getInstance(1, 1).player.avail =2;
			}else if(powerUp == PowerUps.RANGE_UP){
				Map.getInstance(1, 1).player.rad =3;
			}
			explode();
		}else if(id ==2){
			if(powerUp == PowerUps.SPEED_UP){
				Map.getInstance(1, 1).player2.vel = 8;
			}else if(powerUp == PowerUps.BOMBS_UP){
				Map.getInstance(1, 1).player2.avail =2;
			}else if(powerUp == PowerUps.RANGE_UP){
				Map.getInstance(1, 1).player2.rad =3;
			}
			explode();
		}
		
	}
}

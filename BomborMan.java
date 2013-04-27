package bomborman;

import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BomborMan extends JFrame{
	 private class MyDispatcher implements KeyEventDispatcher {
	        @Override
	        public boolean dispatchKeyEvent(KeyEvent e) {
	            if (e.getID() == KeyEvent.KEY_PRESSED) {
	                map.move(e,1);
	            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
	            } else if (e.getID() == KeyEvent.KEY_TYPED) {
	            }
	            return false;
	        }
	    }
	BufferedImage bb;
	MapGui mp;
	Map map;
	JPanel main;
	JButton play;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BomborMan();
	}
	public BomborMan(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("BomberMan by Muhammad Zaman");
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        JPanel main = new JPanel(new FlowLayout());
        JButton play = new JButton("Press to Play");
        main.add(play);
		runGame();
	}
	
	public void runGame(){
		getContentPane().removeAll();
        int r=16;
		int c= 16;
		map = Map.getInstance(r, c);
		map.generateMap();
		mp = new MapGui();
		mp.render();
		mp.repaint();
		add(mp);
		setSize(c*32+50,r*32+50);
		
		while(map.player.isAlive() && map.player2.isAlive()){
			
    		try{
    			Thread.sleep(Map.getInstance(1, 1).getTime());
    		}
    		catch(InterruptedException e)
    		{
    			e.printStackTrace();
    		}
    		mp.render();
    		mp.repaint();
    		add(mp);
    		setVisible(true);
		}
	}

}

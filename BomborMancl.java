package bomborman;
import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BomborMancl extends JFrame{
	MapGuicl mp;

	 private class MyDispatcher implements KeyEventDispatcher {
	        @Override
	        public boolean dispatchKeyEvent(KeyEvent e) {
	            if (e.getID() == KeyEvent.KEY_PRESSED) {
	            	mp.c.e = e;
	            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
	            } else if (e.getID() == KeyEvent.KEY_TYPED) {
	            }
	            return false;
	        }
	    }
	BufferedImage bb;
	
	JPanel main;
	JButton play;
	public static void main(String[] args) {
		new BomborMancl();
	}
	public BomborMancl(){
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
		mp = new MapGuicl();
		mp.render();
		mp.repaint();
		add(mp);
		setSize(16*32+50,16*32+50);
		while(true){
    		try{
    			Thread.sleep(13);
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

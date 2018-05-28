package window;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class DragHandeler extends MouseInputAdapter
{
	
	 	Point p;
	    MouseEvent pressed;
	 
	    public void mousePressed(MouseEvent me)
	    {
	        pressed = me;
	    }
	 
	    public void mouseDragged(MouseEvent me)
	    {
	        Component c = me.getComponent();
	        p = c.getLocation(p);
	        int y = p.y - pressed.getY() + me.getY();
	        int x = p.x - pressed.getX() + me.getX();
	        c.setLocation(x, y);
	     }

}

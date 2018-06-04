package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPlay implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Removes Elements From Window
		Window.frmMidiMusic.getContentPane().removeAll();
		
		//Adds The Elements For the Play Tab
		Window.frmMidiMusic.getContentPane().add(Window.menu);
		Window.frmMidiMusic.getContentPane().add(Window.MenuCreate);
		Window.frmMidiMusic.getContentPane().add(Window.MenuPlay);
		Window.menu.add(Window.MenuCreate);
		Window.menu.add(Window.MenuPlay);
		Window.frmMidiMusic.getContentPane().add(Window.panel);
		Window.frmMidiMusic.getContentPane().add(Window.btnPlayPause);
		Window.frmMidiMusic.getContentPane().add(Window.TimeLine);
		Window.frmMidiMusic.getContentPane().add(Window.lblSelectFile);
		Window.frmMidiMusic.getContentPane().add(Window.MidFileSelect);
		Window.frmMidiMusic.getContentPane().add(Window.btnBrowseMidFile);
		
		
		
		//Updates the Frame
		Window.frmMidiMusic.repaint();
		
		
	}

	

}

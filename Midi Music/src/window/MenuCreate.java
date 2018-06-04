package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCreate implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Window.frmMidiMusic.getContentPane().removeAll();
		Window.frmMidiMusic.getContentPane().setLayout(null);
		Window.frmMidiMusic.getContentPane().add(Window.btnCreateFile);
		Window.frmMidiMusic.getContentPane().add(Window.lblSelectFile);
		Window.frmMidiMusic.getContentPane().add(Window.lblCreateFile);
		Window.frmMidiMusic.getContentPane().add(Window.FileCreate);
		Window.frmMidiMusic.getContentPane().add(Window.FileSelect);
		Window.frmMidiMusic.getContentPane().add(Window.btnBrowse);
		Window.frmMidiMusic.getContentPane().add(Window.btnCreate);
		Window.frmMidiMusic.getContentPane().add(Window.lblNewLabel);
		Window.frmMidiMusic.getContentPane().add(Window.panel);
		Window.frmMidiMusic.getContentPane().add(Window.CreationProgress);
		Window.frmMidiMusic.getContentPane().add(Window.menu);
		Window.menu.add(Window.MenuCreate);
		Window.menu.add(Window.MenuPlay);
		Window.frmMidiMusic.validate();
		Window.frmMidiMusic.repaint();
		
	}

}

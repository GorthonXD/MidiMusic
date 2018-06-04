package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowseMidFile implements ActionListener
{
	
	public static String selectedMidFile = "";
	@Override
	public void actionPerformed(ActionEvent e) {
	
			//Choose File
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Midi Files", "mid");
			Window.fc.setFileFilter(filter);
			Window.fc.setCurrentDirectory(new File("."));
			int val = Window.fc.showOpenDialog(null);
			if(val == JFileChooser.APPROVE_OPTION)
			{
				System.out.println("Mid File Chosen: " + Window.fc.getSelectedFile().getName());
				selectedMidFile = Window.fc.getSelectedFile().getName();
			}
			
			Window.MidFileSelect.setText(selectedMidFile);
	        Window.TimeLine.setEnabled(true);
	}

}

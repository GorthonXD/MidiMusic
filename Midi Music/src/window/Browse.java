package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Browse implements ActionListener {

	public static String selectedFile = "";
	@Override
	public void actionPerformed(ActionEvent e) {
		
	
	
			//Choose File
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt Files", "txt");
			Window.fc.setFileFilter(filter);
			Window.fc.setCurrentDirectory(new File("."));
			int val = Window.fc.showOpenDialog(null);
			if(val == JFileChooser.APPROVE_OPTION)
			{
				System.out.println("File Chosen: " + Window.fc.getSelectedFile().getName());
				selectedFile = Window.fc.getSelectedFile().getName();
			}
			
			Window.FileSelect.setText(selectedFile);
			
	}

}

package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play implements ActionListener {

	//When Play Button is pressed Try to play the selected song.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			midi.Read.countLines();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnPlay implements ActionListener {
public static long Time;
public static boolean isPlaying = false;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(isPlaying == true && midi.Play.sequencer.isOpen() == true)
		{
			Window.btnPlayPause.setText("Play");
			Window.btnPlayPause.setForeground(new Color(0, 128, 0));
			Time = midi.Play.sequencer.getMicrosecondPosition();
			midi.Play.sequencer.stop();
			midi.Play.sequencer.close();
			isPlaying = false;

		}else
		{
			Window.btnPlayPause.setText("Stop");
			Window.btnPlayPause.setForeground(new Color(128, 0, 0));

					try {
						midi.Play.play(Time);
					} catch (Exception e) {
						e.printStackTrace();
					}
			          isPlaying = true;
			  		Window.TimeLine.setMaximum((int) midi.Play.sequencer.getMicrosecondLength());
		}
		
		
		
		
	}

}

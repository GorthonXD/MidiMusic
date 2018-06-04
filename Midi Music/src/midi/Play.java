package midi;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import window.TimeLineProgress;
import window.Window;
import window.btnPlay;

public class Play {
public static int fin;
public static long time;
public static boolean done;
	

public static InputStream stream;
public static Sequencer sequencer;

	@SuppressWarnings("deprecation")
	public static void play(long time) throws Exception
	{
		System.out.println("Trying to Play Music. YEET");
		sequencer = MidiSystem.getSequencer();
		sequencer.open();
		stream = new BufferedInputStream(new FileInputStream(window.BrowseMidFile.selectedMidFile));
		sequencer.setSequence(stream);
		sequencer.setMicrosecondPosition(time);
	
		
		TimeLineProgress tlp = new TimeLineProgress();
		sequencer.start();
		System.out.println(time);
		tlp.start();
		System.out.println("Music Started. LMAO");
		System.out.println("Length: " + sequencer.getMicrosecondLength());
		
		Thread thread = new Thread(new Runnable() {
		
			public void run()
			{
				Window.TimeLine.setMaximum((int) sequencer.getMicrosecondLength());
				
				while(sequencer.isOpen() == true)
				{
					Window.TimeLine.setValue((int) sequencer.getMicrosecondPosition());
				
				if(sequencer.getMicrosecondLength() == sequencer.getMicrosecondPosition())
				{
					//Play.tlp.stop();
					Play.reset();
					sequencer.close();
			}
				}
			}
			
		});
        
		thread.start();
	}
	

	
	//Resets all of the Variables to play another song
	public static void reset()
	{
		btnPlay.Time = 0;
		btnPlay.isPlaying = false;
		Window.btnPlayPause.setText("Play");
		Window.btnPlayPause.setForeground(new Color(0, 128, 0));
		Read.sections = 0;
		Read.sectionsLength.clear();
		Read.sectionsInstrument.clear();
		Read.sectionsVolume.clear();
	}
}

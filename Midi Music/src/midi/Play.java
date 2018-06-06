package midi;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

import window.ScrollingText;
import window.Time;
import window.Window;
import window.btnPlay;

public class Play {


	

public static InputStream stream;
public static Sequencer sequencer;

	public static void play(long time) throws Exception
	{
		Time t = new Time();
		ScrollingText st = new ScrollingText();
		System.out.println("Trying to Play Music. YEET");
		sequencer = MidiSystem.getSequencer();
		sequencer.open();
		stream = new BufferedInputStream(new FileInputStream(window.BrowseMidFile.selectedMidFile));
		sequencer.setSequence(stream);
		sequencer.setMicrosecondPosition(time);
	
		
		sequencer.start();
		t.start();
		st.start();
		System.out.println(time);
		System.out.println("Music Started. LMAO");
		System.out.println("Length: " + sequencer.getMicrosecondLength());
		
		
		
		//Creates new thread to set the Slider to the current time
		
		Thread thread = new Thread(new Runnable() {
		
			@SuppressWarnings("deprecation")
			public void run()
			{
				Window.TimeLine.setMaximum((int) sequencer.getMicrosecondLength());
				
				while(sequencer.isRunning() == true)
				{
					Window.TimeLine.setValue((int) sequencer.getMicrosecondPosition());
				
				if(sequencer.getMicrosecondLength() == sequencer.getMicrosecondPosition())
				{
					Play.reset();
					sequencer.close();
					t.stop();
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

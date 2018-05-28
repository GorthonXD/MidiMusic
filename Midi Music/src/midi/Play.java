package midi;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class Play {

	
	public static void play() throws Exception
	{
		System.out.println("Trying to Play Music. YEET");
		Sequencer sequencer = MidiSystem.getSequencer();
		sequencer.open();
		InputStream stream = new BufferedInputStream(new FileInputStream("Notes.mid"));
		sequencer.setSequence(stream);
		sequencer.start();
		System.out.println("Music Started. LMAO");
		
		//Terminate Once Sequence is finished
		long end = sequencer.getMicrosecondLength()/1000;
		System.out.println(end);
		Thread.sleep(end+1000);
		System.out.println("ENDED");
		reset();
	}

	
	//Resets all of the Variables to play another song
	public static void reset()
	{
		Read.sections = 0;
		Read.sectionsLength.clear();
		Read.sectionsInstrument.clear();
	}
}

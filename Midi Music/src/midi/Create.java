package midi;

import java.io.File;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

public class Create {
	
	
	public static void createMidi() throws Exception
	{
			System.out.println("Midi File Creation Has Begun");
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,60);
			Track t = s.createTrack();
		
			//Turn on sound Set
			byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
			SysexMessage sm = new SysexMessage();
			sm.setMessage(b, 6);
			MidiEvent midi = new MidiEvent(sm,(long)0);
			t.add(midi);
			
			//Tempo
			MetaMessage mt = new MetaMessage();
	        byte[] bt = {0x02, (byte)0x00, 0x00};
			mt.setMessage(0x51 ,bt, 3);
			midi = new MidiEvent(mt,(long)0);
			t.add(midi);
			
			//Omni
			ShortMessage mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7D,0x00);
			midi = new MidiEvent(mm,(long)0);
			t.add(midi);
			
			
			int skip = 1;
			int time = 1;
			int inst = 1;
			
			//Add Each Note of the song
			for(int i = 0; i < Read.sections; i++)
			{
				time = 1;
				inst = Read.sectionsInstrument.get(i);
				System.out.println("Instrument set to: " + inst);
				
			for(int j = 1; j < Read.sectionsLength.get(i)+1; j++)
			{	
				
				if(Read.getNote(j+skip).toUpperCase().equals("WAIT"))
				{
					System.out.println("Wait! " + Read.getLength(j+skip));
					time+=Read.getLength(j+skip);
				}else
				{	
					
				int note = Notes.getKey(Read.getNote(j+skip));
				System.out.println("Adding: " + Read.getNote(j+skip));
				
				//Setting Instrument
				mm.setMessage(ShortMessage.PROGRAM_CHANGE, 0, inst, 0);
				t.add(new MidiEvent(sm, -1));
				
				//0x90 Start Key
				mm = new ShortMessage();
				mm.setMessage(0x90,note,0x40);
				midi = new MidiEvent(mm,(long)time);
				t.add(midi);
			
				//0x80 Ends Key
				mm = new ShortMessage();
				mm.setMessage(0x80,note,0x00);
				midi = new MidiEvent(mm,(long)time+Read.getLength(j+skip));
				t.add(midi);
				time+=Read.getLength(j);
				
				
				}
			}
			skip += Read.sectionsLength.get(i)+1;
			}
			
			
			//End Track
			mt = new MetaMessage();
	        byte[] bet = {};
			mt.setMessage(0x2F,bet,0);
			midi = new MidiEvent(mt, (long)time+200);
			t.add(midi);
			
			
			//Write File
			System.out.println("Midi File is being Written!");
			File f = new File("Notes.mid");
			MidiSystem.write(s,1,f);
			System.out.println("Midi File Creation has Finished! XD");
			
			//Play Music Yo!
			Play.play();
		}
}
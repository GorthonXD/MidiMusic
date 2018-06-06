package midi;

import java.io.File;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

import window.CreationProgress;

public class Write {
	
	
	public static int CreationProgressMax;
	public static int Progress = 0;
	
	@SuppressWarnings("deprecation")
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
			
			
			//Calculates the progressbar's Max Value
			for(int p = 0; p < Read.sectionsLength.size(); p++)
			{
				CreationProgressMax+=Read.sectionsLength.get(p);
			}
			CreationProgressMax-=1;
			
			CreationProgress cp = new CreationProgress();
			
			cp.start();
			
			int skip = 2;
			int time = 1;
			int inst = 1;
			int volume = 120;
			
			//Add Each Note of the song
			for(int i = 0; i < Read.sections; i++)
			{
				System.out.println("Skip: " + skip);
				time = 1;
				inst = Read.sectionsInstrument.get(i);
				volume = Read.sectionsVolume.get(i);
				System.out.println("Volume set to: " + volume);
				System.out.println("Instrument set to: " + inst);
				
			for(int j = 1; j < Read.sectionsLength.get(i)+1; j++)
			{	
				
				if(Read.getNote(j+skip).toUpperCase().equals("WAIT"))
				{
					System.out.println("Wait! " + Read.getLength(j+skip));
					time+=Read.getLength(j+skip);
					Progress++;
				}else
				{	
					
				int note = Notes.getKey(Read.getNote(j+skip));
				System.out.println("Adding: " + Read.getNote(j+skip));
				
				//Setting Instrument
				mm.setMessage(ShortMessage.PROGRAM_CHANGE, 0, inst, 0);
				t.add(new MidiEvent(sm, -1));
				
				//0x90 Start Key
				mm = new ShortMessage();
				mm.setMessage(0x90,note,volume);
				midi = new MidiEvent(mm,(long)time);
				t.add(midi);
				
				
				time+=Read.getLength(j);
				
				//0x80 Ends Key
				mm = new ShortMessage();
				mm.setMessage(0x80,note,0x00);
				midi = new MidiEvent(mm,(long)time);
				t.add(midi);
				
				Progress++;
				}
			}
			skip += Read.sectionsLength.get(i)+2;
			}
			
			cp.stop();
			
			
			//End Track
			mt = new MetaMessage();
	        byte[] bet = {};
			mt.setMessage(0x2F,bet,0);
			midi = new MidiEvent(mt, (long)time+200);
			t.add(midi);
			
			
			//Write File
			System.out.println("Midi File is being Written!");
			File f = new File(Read.getFileName());
			MidiSystem.write(s,1,f);
			System.out.println("Midi File Creation has Finished! XD");
			Play.reset();
		}
	
	}
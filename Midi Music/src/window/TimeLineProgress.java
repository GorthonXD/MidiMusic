package window;

public class TimeLineProgress extends Thread
{
	public static long TimeLineMax = midi.Play.sequencer.getMicrosecondLength();
	public static long TimeLinePos;
	
	public void Run()
	{
		System.out.println("TimeLine!");
		TimeLinePos =  midi.Play.sequencer.getMicrosecondPosition();
		
		while(TimeLineMax > TimeLinePos)
		{
			TimeLinePos =  midi.Play.sequencer.getMicrosecondPosition();
			Window.TimeLine.setValue((int) TimeLinePos);
		}
		
	}

}

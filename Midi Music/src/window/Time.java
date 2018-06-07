package window;

public class Time extends Thread{
	
	private static int EndTime;
	private static int EndMinutes = 0;
	private static int EndSeconds = 0;
	private static int Minutes = 0;
	private static int Seconds = 0;
	
	
	public void run()
	{
		EndTime = (int) (midi.Play.sequencer.getMicrosecondLength()/1000000);
		Window.frmMidiMusic.getContentPane().add(Window.lblEndTime);
		Window.frmMidiMusic.getContentPane().add(Window.lblTime);
		
		int xd = EndTime;
		
		for(int i = 0; i < xd/60; i++)
		{
			EndTime -= 60;
			EndMinutes++;
		}
		
		EndSeconds = EndTime;
		
		if(EndMinutes > 0)
		{
			Window.lblEndTime.setText("" + EndMinutes + ":" + EndSeconds);
		}else
		{
			Window.lblEndTime.setText("00" + ":" + EndSeconds);
		}
		
		while(midi.Play.sequencer.isRunning() == true)
		{
			Seconds = (int) ((midi.Play.sequencer.getMicrosecondPosition()/1000000) - (Minutes*60));
			if(Seconds > 59)
			{
				Minutes++;
			}
			if(Seconds < 10 && Minutes < 10)
			{
				Window.lblTime.setText("0" + Minutes + ":0" + Seconds);
			}
			if(Seconds > 9 && Minutes > 9)
			{
				Window.lblTime.setText("" + Minutes + ":" + Seconds);	
			}
			if(Seconds < 10 && Minutes > 9)
			{
				Window.lblTime.setText("" + Minutes + ":0" + Seconds);	
			}
			if(Seconds > 9 && Minutes < 10)
			{
				Window.lblTime.setText("0" + Minutes + ":" + Seconds);	
			}
		}
	}
	
	

}

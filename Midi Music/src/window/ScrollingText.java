package window;

public class ScrollingText extends Thread{
	private int width = 200;
	private int height = 50;
	private double x = 350;
	private double y = 170;
	
	public void run()
	{
		Window.frmMidiMusic.getContentPane().add(Window.lblTime);
		Window.frmMidiMusic.getContentPane().add(Window.lblEndTime);
		Window.lblSongName.setBounds((int) x, (int) y, width, height);
		Window.frmMidiMusic.getContentPane().add(Window.lblSongName);
		
		while(midi.Play.sequencer.isRunning() == true)
		{
			Window.lblSongName.setText("Now Playing: " + BrowseMidFile.selectedMidFile);
			if(x < 0-width)
			{
				x=350;
				Window.lblSongName.setBounds((int) x, (int) y, width, height);
			}else
			{
				x-=.2;
				Window.lblSongName.setBounds( (int) x, (int) y, width, height);
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}

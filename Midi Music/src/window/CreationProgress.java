package window;

public class CreationProgress extends Thread
{
	public static int notes;
	public void run()
	{
		Window.CreationProgress.setMinimum(0);
		Window.CreationProgress.setMaximum(midi.Write.CreationProgressMax);
		
	while(Window.CreationProgress.getValue() < midi.Write.CreationProgressMax)
	{
		Window.CreationProgress.setValue(midi.Write.Progress);
	}
}

}

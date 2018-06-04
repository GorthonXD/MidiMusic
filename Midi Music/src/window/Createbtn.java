package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Createbtn implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Window.CreationProgress.setValue(0);
		
		Thread thread = new Thread(new Runnable() {

		    @Override
		    public void run()
		    {
				try {
					midi.Read.countLines();
				} catch (Exception e) {
					e.printStackTrace();
				}
		                
		    }
		            
		});
		        
		thread.start();
	}

}

package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Create implements ActionListener {

	
	//When Create Button is pressed Make a txt file with the given name
	public static String file = Window.FileCreate.getText();
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		midi.Read.createFile.add(name());
		try {
			midi.Read.MakeText();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	//If File Name doesn't have .txt at the end, add it.
	public static String name()
	{
		String xd;
		String textName = Window.FileCreate.getText();
		StringBuilder sb = new StringBuilder();
		char c;
		
		for(int i = 0; i < textName.length(); i++)
		{
			c = textName.charAt(i);
			if(sb.length() > 4)
			{
				sb.append(c);
				sb.deleteCharAt(0);
			}else
			{
				sb.append(c);
			}
		}
		sb.deleteCharAt(0);
		xd = sb.toString();
		if(xd.equals(".txt"))
		{
			return textName;
		}
		return textName + ".txt";
	}

}

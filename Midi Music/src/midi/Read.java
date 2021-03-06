package midi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import window.Window;

public class Read {
	
	
	public static List<Integer> sectionsLength = new ArrayList<Integer>();
	public static List<String> createFile = new ArrayList<String>();
	public static List<String> fileList = new ArrayList<String>();
	public static List<Integer> sectionsInstrument = new ArrayList<Integer>();
	public static List<Integer> sectionsVolume = new ArrayList<Integer>();
	private static int numLines;
	static int sections = 0;
	public static String newline = System.getProperty("line.separator");
	public static void MakeText() throws Exception
	{
		System.out.println(window.Create.file);
		try {
		FileOutputStream os = new FileOutputStream(createFile.get(createFile.size()-1));
		OutputStreamWriter osw = new OutputStreamWriter(os);
		Writer write = new BufferedWriter(osw);
		write.write("Note(Letter Octive #. Sharp = S. Negitive = N):Length(ms)"  + newline + "SECTION:(Instrument #)" + newline + "Volume:(0 - 120)");
		write.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Window.FileCreate.setText("");
	}
	
	@SuppressWarnings("resource")
	public static String readLine(int line) throws IOException
	{
			BufferedReader oBuffer = new BufferedReader(new FileReader(window.Browse.selectedFile));
			String Line;
			int count = 0;
			while ((Line = oBuffer.readLine()) != null)
			{
			  count++;
			  if(count > line) 
				  break;
			}
			return Line;
	}
	
	public static int countLines()  throws Exception
	{
	    LineNumberReader reader = null;
	    try 
	    {
	        reader = new LineNumberReader(new FileReader(window.Browse.selectedFile));
	        while ((reader.readLine()) != null);
	        numLines = reader.getLineNumber();
	        return reader.getLineNumber();
	    } catch (Exception ex)
	    {
	        return -1;
	    }finally
	    { 
	        if(reader != null)
	        {
	            reader.close();
	        }
			getSections();
	    }
	}
	
	public static String getNote(int line) throws IOException
	{
		String res = "";
		char note;
		String Line = readLine(line);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Line.length(); i++)
		{
			note = Line.charAt(i);
			if(note == ':')
			{
				break;
			}else
			{
				sb.append(note);
				res = sb.toString();
			}
		}
		return res;
	}
	
	public static int getLength(int line) throws IOException
	{
		int result;
		String res = "";
		char note;
		String Line = readLine(line);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Line.length(); i++)
		{
			note = Line.charAt(i);
			if(note == ':')
			{
				int count = i+1;
				
				for(int j = 0; j < Line.length() - count; j++)
				{
					i++;
					note = Line.charAt(i);
					sb.append(note);
					res = sb.toString();
				}
			}
		}
		result = Integer.parseInt(res);
		return result;
	}
	
	public static void getSections() throws Exception
	{
		int sectionLength = 0;
		String res = "";
		char note;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < numLines; i++)
		{
			String Line = readLine(i);
			sectionLength++;
			for(int j = 0; j < Line.length(); j++)
			{
			note = Line.charAt(j);
			if(note == ':')
			{
				break;
			}else
			{
				sb.append(note);
				res = sb.toString();
			}
			}

			if(res.toUpperCase().equals("SECTION"))
			{
				System.out.println("Section Length: " + sectionLength);
				sectionsLength.add(sectionLength-2);
				sectionLength = 0;
				sections++;
				sb.delete(0, sb.length());
			}else
			{
				sb.delete(0, sb.length());
			}
		}
		sectionsLength.add(sectionLength-1);
		sectionsLength.remove(0);
		sectionsLength.add(0, 0);
		System.out.println(sectionsLength);
		System.out.println(sections);
		setInstrument();
	}
	
	public static void setInstrument() throws Exception
	{
		String res = "";
		int skip = 1;
		char note;
		String Line;
		StringBuilder sb= new StringBuilder();
		for(int i = 0; i < sections; i++)
		{
			Line = readLine(sectionsLength.get(i)+skip);
			System.out.print("Line: " + Line);
			for(int j = 0; j < Line.length();  j++)
			{
				note = Line.charAt(j);
				if(note == ':')
				{
					System.out.println(j+1);
					int count = j+1;
					for(int k = 0; k < Line.length() - count; k++)
					{
						j++;
						note = Line.charAt(j);
						sb.append(note);
						res = sb.toString();
					}
					System.out.println(res);
					int inst = Integer.parseInt(res);
					sectionsInstrument.add(inst);
					sb.delete(0, sb.length());
				}
			}
			skip+=sectionsLength.get(i)+2;
		}
		System.out.println(sectionsInstrument);
		sectionsLength.remove(0);
		Read.setVolume();
	}
	
	public static String getFileName()
	{
		String xd;
		String fileName = window.Browse.selectedFile;
		StringBuilder sb = new StringBuilder();
		char c;
		
		for(int i = 0; i < fileName.length(); i++)
		{
			c = fileName.charAt(i);
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
			sb.delete(0, sb.length());
			for(int j = 0; j < fileName.length() - 4; j++)
			{
				c = fileName.charAt(j);
				sb.append(c);
			}
			xd = sb.toString();
			return xd  + ".mid";
		}
		return fileName + ".mid";
	}
	
	public static void setVolume() throws Exception
	{
		int Volume = 0;
		int skip = 0;
		String res = "";
		char c;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < numLines; i++)
		{
			String Line = readLine(i);
			
			for(int j = 0; j < Line.length(); j++)
			{
			c = Line.charAt(j);
			if(c == ':')
			{
				skip = j+1;
				break;
			}else
			{
				sb.append(c);
				res = sb.toString();
			}
			}

			if(res.toUpperCase().equals("VOLUME"))
			{
				System.out.println("VOLUME");
				sb.delete(0, sb.length());
				
				for(int k = 0; k < Line.length() - 7; k++)
				{
					c = Line.charAt(skip);
					sb.append(c);
					System.out.println("SB: " + sb);
					skip++;
					
				}
				Volume = Integer.parseInt(sb.toString());
				sectionsVolume.add(Volume);
				
			}else
			{
				sb.delete(0, sb.length());
			}
		}
		System.out.println(sectionsVolume);
		Write.createMidi();
	}

}

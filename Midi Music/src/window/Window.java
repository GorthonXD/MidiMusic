package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

public class Window {

	//Initialize Frame
	public static  JFrame frmMidiMusic;
	
	//Initialize MenuBar
	public static JMenuBar menu = new JMenuBar();
	
	//Initialize Panel
	public static JPanel panel = new JPanel();
	
	//Initialize Menu
	public static MenuPlay mp = new MenuPlay();
	
	//Initialize Slider
	public static JSlider TimeLine = new JSlider();
	
	//Initialize ProgressBar
	public static JProgressBar CreationProgress = new JProgressBar();
	
	//Initialize FileChoosers
	public static JFileChooser midifc = new JFileChooser();
	static final JFileChooser fc = new JFileChooser();
	
	//Initialize Buttons
	public static JButton btnBrowseMidFile = new JButton("Browse");
	public static JButton btnBrowse = new JButton("Browse");
	public static JButton btnCreate = new JButton("Create");
	public static JButton MenuCreate = new JButton("Create");
	public static JButton MenuPlay = new JButton("Play");
	public static JButton btnCreateFile = new JButton("Create");
	public static JButton btnPlayPause = new JButton("Play");
	
	//Initialize TextFields
	public static JTextField MidFileSelect = new JTextField();
	public static JTextField FileCreate = new JTextField();
	static JTextField FileSelect;
	
	//Initialize Labels
	public static JLabel lblSelectFile = new JLabel("Select File");
	public static JLabel lblNewLabel = new JLabel("");
	public static JLabel lblCreateFile = new JLabel("Create File");
	public static JLabel lblSongName = new JLabel();
	public static JLabel lblTime = new JLabel();
	public static JLabel lblEndTime = new JLabel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Sets the design of the Window
		try
		{
			for(LookAndFeelInfo info:UIManager.getInstalledLookAndFeels())
			{
				if("Nimbus".equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		
		catch(Exception e)
		{
			
		}
		
		//Updates the Elements to the selected "Look and Feel"
		fc.updateUI();
		FileCreate.updateUI();
		MidFileSelect.updateUI();
		TimeLine.updateUI();
		
		
		//Makes a new Window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					Window.frmMidiMusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
	}
	
	
	//Creates the Window
	public Window() {
		initialize();
	}

	//Initialized the stuff in the Frame
	private void initialize() {
		
		//Frame Setup
		frmMidiMusic = new JFrame();
		frmMidiMusic.setResizable(false);
		frmMidiMusic.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmMidiMusic.setTitle("Midi Music");
		frmMidiMusic.setBounds(100, 100, 350, 300);
		frmMidiMusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu.setBounds(0, 38, 350, 30);

		//Makes the Frame Dragable
		DragHandeler drag = new DragHandeler();
		frmMidiMusic.addMouseListener(drag);
		frmMidiMusic.addMouseMotionListener(drag);
		
		//Labels
		lblSelectFile.setBounds(10, 93, 60, 15);
		lblCreateFile.setBounds(10, 151, 60, 15);
		
		lblTime.setBounds(10, 205, 50, 10);
		lblEndTime.setBounds(303, 205, 50, 10);
		
		
		FileCreate.setBounds(80, 146, 147, 25);
		FileCreate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		FileCreate.setColumns(10);
		
		MidFileSelect.setBounds(80, 88, 147, 25);
		MidFileSelect.setEditable(false);
		MidFileSelect.setColumns(10);
		

		btnCreate.setBounds(245, 146, 80, 25);
		
		
		//Time Line
		TimeLine.setBounds(50, 200, 250, 20);
		TimeLine.setBackground(Color.LIGHT_GRAY);
		TimeLine.setEnabled(false);
		TimeLine.setValue(0);
		
			
		
		btnBrowseMidFile.setBounds(245, 88, 80, 25);
		btnBrowseMidFile.setToolTipText("Select a Mid File to be Played");
		btnBrowseMidFile.addActionListener(new BrowseMidFile());
		
		btnBrowse.setBounds(245, 88, 80, 25);
		btnBrowse.setToolTipText("Select a Text File to be Played");
		//When clicked go to Browse.java
		btnBrowse.addActionListener(new Browse());
		FileSelect = new JTextField();
		FileSelect.setBounds(80, 88, 147, 25);
		FileSelect.setEditable(false);
		FileSelect.setColumns(10);
		btnCreateFile.setBounds(109, 182, 118, 37);
		btnCreateFile.setForeground(new Color(0, 128, 0));
		btnCreateFile.setBackground(Color.WHITE);
		btnCreateFile.setToolTipText("Creates Midi File");
		
		
		btnPlayPause.setBounds(109, 230, 118, 37);
		btnPlayPause.setForeground(new Color(0, 128, 0));
		btnPlayPause.setBackground(Color.WHITE);
		btnPlayPause.setToolTipText("Plays Selected Midi File");
		btnPlayPause.addActionListener(new btnPlay());
		
		//ProgressBar
		CreationProgress.setBounds(10, 230, 315, 30);
		CreationProgress.setStringPainted(true);
		
		//When clicked go to Respective Classes
		btnCreateFile.addActionListener(new Createbtn());
		MenuPlay.addActionListener(new MenuPlay());
			
		
		
		
		btnCreate.addActionListener(new Create());
		MenuCreate.addActionListener(new MenuCreate());
		

		
		
		

		panel.setBounds(0, 0, 394, 37);
		panel.setBackground(new Color(128, 128, 128));
		panel.setLayout(null);
		
		JLabel lblMidiMusicCreation = new JLabel("Midi Music Creation");
		lblMidiMusicCreation.setBounds(123, 11, 109, 14);
		lblMidiMusicCreation.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		//Adds Everything to the Window
		panel.add(lblMidiMusicCreation);
		frmMidiMusic.getContentPane().setLayout(null);
		frmMidiMusic.getContentPane().add(btnCreateFile);
		frmMidiMusic.getContentPane().add(lblSelectFile);
		frmMidiMusic.getContentPane().add(lblCreateFile);
		frmMidiMusic.getContentPane().add(FileCreate);
		frmMidiMusic.getContentPane().add(FileSelect);
		frmMidiMusic.getContentPane().add(btnBrowse);
		frmMidiMusic.getContentPane().add(btnCreate);
		frmMidiMusic.getContentPane().add(lblNewLabel);
		frmMidiMusic.getContentPane().add(panel);
		frmMidiMusic.getContentPane().add(CreationProgress);
		frmMidiMusic.getContentPane().add(menu);
		
		//Adds stuff to the Menu
		menu.add(MenuCreate);
		menu.add(MenuPlay);
		
		
		
		
		/*Exit Button
		JButton btnXButton = new JButton("X");
		btnXButton.setBounds(300, 8, 25, 25);
		panel.add(btnXButton);
		btnXButton.setForeground(new Color(255, 255, 255));
		btnXButton.setBackground(new Color(178, 34, 34));
		btnXButton.addActionListener(new ActionListener() {
			
			//When pressed Exit
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnXButton.setFont(new Font("Calibri", Font.BOLD, 7));
		
		*/

	}
}

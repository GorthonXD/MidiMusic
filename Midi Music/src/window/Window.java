package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.geom.RoundRectangle2D;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Window {

	private  JFrame frmMidiMusic;
	public static JTextField FileCreate;
	static final JFileChooser fc = new JFileChooser();
	static JTextField FileSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Sets the design of the Window
		try
		{
			for(LookAndFeelInfo info:UIManager.getInstalledLookAndFeels())
			{
				System.out.println(info.getName());
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
		
		//Updates the File Chooser to the slected "Look and Feel"
		fc.updateUI();
		
		
		//Makes a new Window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmMidiMusic.setVisible(true);
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
		frmMidiMusic.setUndecorated(true);
		frmMidiMusic.setShape(new RoundRectangle2D.Double(0, 0, 340, 180,  80, 80));
		frmMidiMusic.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmMidiMusic.setTitle("Midi Music");
		frmMidiMusic.setBounds(100, 100, 335, 218);
		frmMidiMusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Makes the Frame Dragable
		DragHandeler drag = new DragHandeler();
		frmMidiMusic.addMouseListener(drag);
		frmMidiMusic.addMouseMotionListener(drag);
		
		//Initialize Labels
		JLabel lblSelectFile = new JLabel("Select File");
		lblSelectFile.setBounds(10, 45, 60, 15);
		
		JLabel lblCreateFile = new JLabel("Create File");
		lblCreateFile.setBounds(10, 86, 60, 15);
		
		
		FileCreate = new JTextField();
		FileCreate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		FileCreate.setBounds(80, 81, 140, 25);
		lblCreateFile.setLabelFor(FileCreate);
		FileCreate.setColumns(10);
			
		
		
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(245, 41, 80, 25);
		btnBrowse.setToolTipText("Select a Text File to be Played");
		//When clicked go to Browse.java
		btnBrowse.addActionListener(new Browse());
		FileSelect = new JTextField();
		FileSelect.setBounds(80, 40, 140, 25);
		lblSelectFile.setLabelFor(FileSelect);
		FileSelect.setEditable(false);
		FileSelect.setColumns(10);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(111, 131, 118, 37);
		btnPlay.setForeground(new Color(0, 128, 0));
		btnPlay.setBackground(Color.WHITE);
		btnPlay.setToolTipText("Plays Selected Text File");
		//When clicked go to Play.java
		btnPlay.addActionListener(new Play());
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(245, 82, 80, 25);
		//When clicked go to Create.java
		btnCreate.addActionListener(new Create());
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(321, 113, 0, 0);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 335, 35);
		panel.setBackground(new Color(128, 128, 128));
		panel.setLayout(null);
		
		JLabel lblMidiMusicCreation = new JLabel("Midi Music Creation");
		lblMidiMusicCreation.setBounds(123, 11, 109, 14);
		lblMidiMusicCreation.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblMidiMusicCreation);
		frmMidiMusic.getContentPane().setLayout(null);
		frmMidiMusic.getContentPane().add(btnPlay);
		frmMidiMusic.getContentPane().add(lblSelectFile);
		frmMidiMusic.getContentPane().add(lblCreateFile);
		frmMidiMusic.getContentPane().add(FileCreate);
		frmMidiMusic.getContentPane().add(FileSelect);
		frmMidiMusic.getContentPane().add(btnBrowse);
		frmMidiMusic.getContentPane().add(btnCreate);
		frmMidiMusic.getContentPane().add(lblNewLabel);
		frmMidiMusic.getContentPane().add(panel);
		
		
		//Exit Button
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
	}
	
}

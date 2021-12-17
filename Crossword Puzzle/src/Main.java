import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.awt.event.*;

public class Main extends InfoExtractor implements MainInterface {
	
	/* The Txt file opener GUI */
	
	File txtFile;
	static JTextField solutionField;
	String solution="southpark";
	String fileName;
	// Get current Directory for files
	static Path currentRelativePath = Paths.get("");
	String pathString = currentRelativePath.toAbsolutePath().toString();
	
	
	public static void main(String[] args) {
		
		// Initialize the UI
		Main mainWindow=new Main();
		mainWindow.createOpenerUI();
		
		
	}
	
	public void createOpenerUI() {
		
		if (!pathString.contains("src")) {
			pathString=pathString+"/src/Files/";
		}else
		{
			pathString=pathString+"/Files/";
		}
		
		System.out.println(pathString);

		// Customize Frame
		fileOpenerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileOpenerFrame.setSize(400,400);
		fileOpenerFrame.setLayout(new GridLayout(4, 1));
		fileOpenerFrame.setResizable(false);
		fileOpenerFrame.setLocationRelativeTo(null);
		
		// Customize Panels
		welcPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		welcPanel.setBackground(new Color(73,172,117));
		
		choosePanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		choosePanel.setBackground(new Color(73,172,117));
		
		openPanel.setLayout(new FlowLayout(FlowLayout.CENTER,200,0));
		openPanel.setBackground(new Color(73,172,117));
		
		solPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		solPanel.setBackground(new Color(73,172,117));
		
		// Labels
		JLabel fileOpenerLabel=new JLabel("Welcome to the Crossword Puzzle Game");
		JLabel helper=new JLabel("Please Select a txt File");
		JLabel solLabel=new JLabel("Please Enter the Solution word/phrase (Default: southpark)");
		welcPanel.add(fileOpenerLabel);
		
		// Button
		
		// Button for opening a file dialog box
		fileOpenerButton.setSize(10,10);
		fileOpenerButton.setFocusable(false);
		fileOpenerButton.setToolTipText("Choose a txt file which will be used to build the game");
		
		// Button for default mode
		defaultButton.setSize(10,10);
		defaultButton.setFocusable(false);
		defaultButton.setToolTipText("Chooses the default txt for the game. Please check if there is any txt file in the directory where the game is stored. Please note that choosing default will also automatically choose default solution word which is 'southpark'");
		
		// Button for manual mode
		manualButton.setSize(10,10);
		manualButton.setFocusable(false);
		manualButton.setToolTipText("Manually choose the txt file and the solution word");
		
		// Button for back
		ImageIcon backImage=new ImageIcon(pathString+"back.png");
		backButton.setSize(10,10);
		backButton.setFocusable(false);
		backButton.setToolTipText("Go back to choosing the mode for txt selection");
		backButton.setIcon(backImage);

		
		
		// Button for starting the game
		gameStartButton.setSize(10,10);
		gameStartButton.setFocusable(false);
		gameStartButton.setToolTipText("Start the game");
		
		// Solution Textfield
		solutionField=new JTextField();
		solutionField.setPreferredSize(new Dimension(300,50));
		solutionField.setToolTipText("Enter the solution word which will be used for final evaluation.");
		
		// Add the labels and the buttons in the panels
		choosePanel.add(helper);
		choosePanel.add(manualButton);
		choosePanel.add(defaultButton);
		
		//openPanel.add(helper);
		openPanel.add(backButton);
		openPanel.add(fileOpenerButton);
		
		solPanel.add(solLabel);
		solPanel.add(solutionField);
		solPanel.add(gameStartButton);
		
		// Activate the add by adding actionListener.
		buttonListeners();
		
		fileOpenerFrame.add(welcPanel);
		fileOpenerFrame.add(choosePanel);
		fileOpenerFrame.add(openPanel,BorderLayout.CENTER);
		fileOpenerFrame.add(solPanel,BorderLayout.SOUTH);
		openPanel.setVisible(false);
		solPanel.setVisible(false);
		
		//fileOpenerFrame.add(fileOpenerButton);
		fileOpenerFrame.setVisible(true);
		
		
	
	}
	
	
	public void buttonListeners() {
		
		// ActionListener for 
		ActionListener fileListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==fileOpenerButton) {
					
					JFileChooser fileChooser=new JFileChooser();
					int option = fileChooser.showOpenDialog(fileOpenerFrame);
		            if(option == JFileChooser.APPROVE_OPTION){
		            	// Get Path Name
		               File filePath = fileChooser.getSelectedFile();
		               
		               // Get the name of the txt file
		               fileName=filePath.getPath();
		              // Check if the extension is txt or not
		               String[] splitFileName=fileName.split("\\.",-1);
		               
		               if (splitFileName[splitFileName.length-1].equals("txt")) {
		            	   System.out.println("File Selected: " + filePath);
		            	//If the file is not txt give a warning message   
		               }else {
		            	   JOptionPane.showMessageDialog(null, "Please select a txt file","Warning",JOptionPane.WARNING_MESSAGE);
		            	   
		               }
		               
		               
		            } 
			}
		}};
		
		ActionListener gameListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource()==gameStartButton) {            	
	            	String getTexts=solutionField.getText();
	            	
	            	if (!getTexts.matches("")) 
	            	{
	            		solution=getTexts;
	            	}

	         	   //Extract Information
	         	   try {
						TxtExtractor(fileName,solution);
					} catch (IOException e1) {
						// Give a warning message
						e1.printStackTrace();
					}		
	         	   fileOpenerFrame.dispose();
			
		}
				
		
		
	}};
	
	ActionListener defaultListener=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==defaultButton) {
				
				try {
					
					fileName=pathString+"default puzzle text file.txt";
					TxtExtractor(fileName,solution);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Txt not found. Please Select a Txt file","Warning",JOptionPane.WARNING_MESSAGE);
				}
				fileOpenerFrame.dispose();
			}
			
		}
	};
	
	ActionListener manualListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource()==manualButton) {
					openPanel.setVisible(true);
					solPanel.setVisible(true);
					welcPanel.setVisible(false);
					choosePanel.setVisible(false);
		
				}
				
			}
		};
	
	ActionListener backListener=new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (e.getSource()==backButton) {
						openPanel.setVisible(false);
						solPanel.setVisible(false);
						welcPanel.setVisible(true);
						choosePanel.setVisible(true);
			
					}
					
				}
			};
		
	
	
	
	
	
	// Add actionlistener to each button
	fileOpenerButton.addActionListener(fileListener);
	gameStartButton.addActionListener(gameListener);
	defaultButton.addActionListener(defaultListener);
	manualButton.addActionListener(manualListener);
	backButton.addActionListener(backListener);
	
	}


	

}

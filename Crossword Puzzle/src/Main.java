import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

public class Main extends InfoExtractor {
	
	/* The Txt file opener GUI */
	
	JButton fileOpenerButton;
	JButton gameStartButton;
	JFrame fileOpenerFrame;
	File txtFile;
	static JTextField solutionField;
	String solution="southpark";
	String fileName;
	
	public static void main(String[] args) {
		
		
		// Initialize the UI
		Main mainWindow=new Main();
		mainWindow.createOpenerUI();
		
		
	}
	
	public void createOpenerUI() {
		
		// Create Frame
		fileOpenerFrame=new JFrame("Crossword Puzzle");
		fileOpenerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileOpenerFrame.setSize(400,400);
		fileOpenerFrame.setLayout(new GridLayout(3, 1));
		fileOpenerFrame.setResizable(false);
		fileOpenerFrame.setLocationRelativeTo(null);
		
		//Panels
		JPanel welcPanel=new JPanel(); // Panel for Welcome label
		welcPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel openPanel=new JPanel(); // Panel for Button
		openPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));
		
		JPanel solPanel=new JPanel(); // Panel for Solution
		solPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		// Labels
		JLabel fileOpenerLabel=new JLabel("Welcome to the Crossword Puzzle Game");
		JLabel helper=new JLabel("Please Select a txt File");
		JLabel solLabel=new JLabel("Please Enter the Solution word/phrase (Default: southpark)");
		welcPanel.add(fileOpenerLabel);
		
		// Button
		fileOpenerButton=new JButton("Open File");
		fileOpenerButton.setSize(10,10);
		fileOpenerButton.setFocusable(false);
		
		gameStartButton=new JButton("Start Game");
		gameStartButton.setSize(10,10);
		gameStartButton.setFocusable(false);
		
		// Solution Textfield
		solutionField=new JTextField();
		solutionField.setPreferredSize(new Dimension(300,50));
		
		// Add the label and the button in the panel
		openPanel.add(helper);
		openPanel.add(fileOpenerButton);
		
		solPanel.add(solLabel);
		solPanel.add(solutionField);
		solPanel.add(gameStartButton);
		
		// Activate the add by adding actionListener.
		buttonListeners();
		
		fileOpenerFrame.add(welcPanel);
		fileOpenerFrame.add(openPanel,BorderLayout.CENTER);
		fileOpenerFrame.add(solPanel,BorderLayout.SOUTH);
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
	
	
	// Add actionlistener to each button
	fileOpenerButton.addActionListener(fileListener);
	gameStartButton.addActionListener(gameListener);
	
	}


	

}

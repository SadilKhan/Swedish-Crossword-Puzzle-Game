import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.*;

public class Main extends InfoExtractor implements ActionListener {
	
	JButton fileOpenerButton;
	JFrame fileOpenerFrame;
	File txtFile;
	
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
		
		// Labels
		JLabel fileOpenerLabel=new JLabel("Welcome to the Crossword Puzzle Game");
		JLabel helper=new JLabel("Please Select a txt File");
		welcPanel.add(fileOpenerLabel);
		
		// Button
		fileOpenerButton=new JButton("Open File");
		fileOpenerButton.setSize(10,10);
		fileOpenerButton.setFocusable(false);
		fileOpenerButton.addActionListener(this);
		
		// Add the label and the button in the panel
		openPanel.add(helper);
		openPanel.add(fileOpenerButton);
		
		
		fileOpenerFrame.add(welcPanel);
		fileOpenerFrame.add(openPanel,BorderLayout.SOUTH);
		//fileOpenerFrame.add(fileOpenerButton);
		fileOpenerFrame.setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fileOpenerButton) {
			
			JFileChooser fileChooser=new JFileChooser();
			int option = fileChooser.showOpenDialog(fileOpenerFrame);
            if(option == JFileChooser.APPROVE_OPTION){
            	// Get Path Name
               File filePath = fileChooser.getSelectedFile();
               
               // Get the name of the txt file
               String fileName=filePath.getPath();
              // Check if the extension is txt or not
               String[] splitFileName=fileName.split("\\.",-1);
               
               if (splitFileName[splitFileName.length-1].equals("txt")) {
            	   System.out.println("File Selected: " + filePath);
            	   
            	   //Extract Information
            	   try {
					super.TxtExtractor(fileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
            	   fileOpenerFrame.dispose();
	            	
	   
            	//If the file is not txt give a warning message   
               }else {
            	   JOptionPane.showMessageDialog(null, "Please select a txt file","Warning",JOptionPane.WARNING_MESSAGE);
            	   
               }
               
               
            }else{
            	System.out.println("..");
            }
				
			
		}
		
	}
	
	

}

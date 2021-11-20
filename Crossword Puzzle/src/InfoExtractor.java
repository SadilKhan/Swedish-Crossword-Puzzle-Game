import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class InfoExtractor {
	
	// Custom Class to extract relevant information from txt file
	
	BufferedReader txtFile; 
	JFrame mainFrame;

	
	public void TxtExtractor(String path) throws IOException {
		// The method to extract information from txt file
		
		BufferedReader file=new BufferedReader(new FileReader(new File(path)));// Read the TXT file

		String[] shapeInfo=file.readLine().split(" ",-1); // The shape information of the playing field
		
		try {
		int row=Integer.parseInt(shapeInfo[0]); // Get the number of rows of the puzzle
		int column=Integer.parseInt(shapeInfo[1]); // Get the number of columns of the puzzle
		
		// Extract the box types
		String[][] field= new String[row][column];
		// The clues in the Across Column
		ArrayList<String> acrossClues = new ArrayList<String>();
		// The clues in the Down Column
		ArrayList<String> downClues = new ArrayList<String>();
		// Current sentence from file.readline()
		String curSentence="";
		
		for(int i=0;i<row;i++) {
			field[i]=file.readLine().split(" ",-1);
		}
		while(!curSentence.equals("DOWN")) {
			curSentence=file.readLine();
			acrossClues.add(curSentence);
		}
		acrossClues.remove(acrossClues.size()-1);// Remove the DOWN keyword
		while(curSentence!=null) {
			downClues.add(curSentence);
			curSentence=file.readLine();
		}
		
		// Initialize the GameUI
		new Game(row,column,field,acrossClues,downClues);
		
		
		}
		catch(Exception e){
			// If wrong txt is provided then open the Main File Opener again.
			JOptionPane.showMessageDialog(null, "Wrong Txt file!","Warning",JOptionPane.WARNING_MESSAGE);
			Main mainWindow=new Main();
			mainWindow.createOpenerUI();
			
		}
		
		
		
	}
}

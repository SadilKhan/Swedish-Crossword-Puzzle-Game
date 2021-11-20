import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Panel {
	/* Custom Class to create Panels for Across Statement and Down Statement */
	
	ArrayList<String> clues; // Contains all the clues for Across or Down
	JPanel aPanel; // The Main Panel
	
	Panel(ArrayList<String> lst ){
		// Constructor
		clues=lst;
		createPanel();
		
	}
	
	public void createPanel() {
		
		// Panel for Across clues and Down Clues
		aPanel=new JPanel();
		aPanel.setLayout(new BoxLayout(aPanel,BoxLayout.Y_AXIS)); // Box Layout is used for vertical positioning of labels
		
		// Add the clues in the aPanel
		for(int i=0;i<clues.size();i++) {
			/* Every item in the clues except the first item is of the form 
			 * n.clue where n is a number which is colored differently than clue.
			 * The First item is either Across or Down.
			 * A panel is created for every item.
			 * So, the item is split and positioned separately in a panel.
			 * To efficiently position the two strings, borderlayout is used.
			 * 
			 */
			String[] splitClues=clues.get(i).split("\\.",-1);
			// Add a dot to the number. n--> n.
			if(splitClues.length>1) {splitClues[0]=splitClues[0]+".";};
			// For every item in the Across/Down lists, a panel is created
			JPanel tempPanel=new JPanel(new BorderLayout());
			
			for (int j=0;j<splitClues.length;j++) {
				JLabel tempLabel=new JLabel(splitClues[j]);
				
				if(j==0) {
					// Change the color for Across word and the bullets
					tempLabel.setForeground(new Color(76,0,153));
					tempLabel.setFont(new Font("Arial",Font.BOLD,12));
					tempPanel.add(tempLabel,BorderLayout.WEST);
				}
				else {
					tempLabel.setFont(new Font("Arial",Font.PLAIN,12));
					tempPanel.add(tempLabel,BorderLayout.CENTER);
				}

			}
			
			// Add each clue statements in the main panels
			aPanel.add(tempPanel);
		}
		
		
		
	}

}

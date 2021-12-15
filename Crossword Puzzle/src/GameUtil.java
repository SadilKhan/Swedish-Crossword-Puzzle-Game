import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUtil {
	
	/* Utility Class */
	
	public static char[] getRandomKeys(char c) {
		/* Generates an array of characted which contains user-provided character
		 * and four distinct characters
		 * 
		 */
		ArrayList<Character> hintKeys=new ArrayList<Character>();
		hintKeys.add(c);
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		
		
		while (hintKeys.size()<5) {
			Random rd= new Random();
			char letter = abc.charAt(rd.nextInt(abc.length()));
			if (!hintKeys.contains(letter)) {
				hintKeys.add(letter);
				
			}
			
		}
		
		// Transform the ArrayList to Array
		char[] hintKeyArrays=new char[hintKeys.size()];
		
		for(int i=0;i<hintKeys.size();i++) {
			hintKeyArrays[i]=hintKeys.get(i);
		}
		return hintKeyArrays;
		}
	
	
	public static boolean containsValue(char[] list,char value) {
		// Checks if a value is in a list
		
		for(char a:list) {
			if(a==value) {
				return true;
			}
		}
		if(list.length==0) {
			return true;
		}
		return false;
	}
	
	public static JPanel createPanel(ArrayList<String> clues ) {
			
			// Panel for Across clues and Down Clues
			JPanel aPanel=new JPanel();
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
			return aPanel;
	
	}
	

}

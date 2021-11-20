import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Panel {
	
	ArrayList<String> clues;
	JPanel aPanel;

	public static void main(String[] args) {
		

	}
	
	Panel(ArrayList<String> lst ){
		clues=lst;
		createPanel();
		
	}
	
	public void createPanel() {
		
		// Panel for Across clues and Down Clues
		aPanel=new JPanel();
		aPanel.setLayout(new BoxLayout(aPanel,BoxLayout.Y_AXIS));
		
		
		for(int i=0;i<clues.size();i++) {
			String[] splitClues=clues.get(i).split("\\.",-1);
			JPanel tempPanel=new JPanel(new BorderLayout());
			
			for (int j=0;j<splitClues.length;j++) {
				JLabel tempLabel=new JLabel();
				//tempLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				
				if(j==0) {
					tempLabel.setForeground(new Color(76,0,153));
					if (splitClues.length==1) {
						tempLabel.setText(splitClues[j]);
						tempLabel.setFont(new Font("Arial",Font.BOLD,12));
					}else {
						tempLabel.setText(splitClues[j]+".");
						tempLabel.setFont(new Font("Arial",Font.BOLD,10));
					}
					
					tempPanel.add(tempLabel,BorderLayout.WEST);
				}
				else {
					tempLabel.setText(splitClues[j]);
					tempLabel.setFont(new Font("Arial",Font.PLAIN,10));
					tempPanel.add(tempLabel,BorderLayout.CENTER);
				}
				
					
				
			}
			aPanel.add(tempPanel);
		}
		
		
		
	}

}

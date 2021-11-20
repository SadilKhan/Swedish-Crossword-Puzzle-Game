import javax.swing.*;
import java.awt.*;
import java.util.*;


public class OnScreenKeyboard{
	
	/* Custom Class for On Screen Keyboard */
	
	JFrame keyboardFrame; // Main Keyboard Frame
	HashMap<Character,JButton> charButtonMap; // The map between alphabet characters and button
	int position_x=-1; // The x coordinate of the position of keyboard in the screen.
	int position_y=-1; // The y coordinate of the position of keyboard in the screen.
	
	OnScreenKeyboard(int x,int y){
		char[] ekey= {};
		position_x=x;
		position_y=y;
		// Start the UI
		createKeyboardUI(ekey);
	}
	OnScreenKeyboard(char[] enabledKey,int x,int y){
		position_x=x;
		position_y=y;
		// Start the UI
		createKeyboardUI(enabledKey);

	}
	
	
	
	private void createKeyboardUI(char[] enabledKey) {
		/* The Keyboard UI*/
		
		// Create a Frame
		keyboardFrame=new JFrame("On-Screen Keyboard");
		keyboardFrame.setSize(800,150);
		
		keyboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		keyboardFrame.setLayout(new FlowLayout());
		keyboardFrame.setResizable(false);
		if (position_x>0 & position_y>0) {
			keyboardFrame.setLocation(position_x,position_y);
		}else {
			keyboardFrame.setLocationRelativeTo(null);
		}
		
		
		Map<Character,JButton> charButtonMap=createCBMap(enabledKey);
		
		// Add button in Azerty Style
		String azerty="AZERTYUIOPQSDFGHJKLMWXCVBN";
		for(int i=0;i<azerty.length();i++) {
			JButton b=charButtonMap.get(azerty.charAt(i));
			
			keyboardFrame.add(b);
		}
		
		keyboardFrame.setVisible(true);
		
		
	}
	
	private HashMap<Character, JButton> createCBMap(char[] enabledKey) {
		
		//Creates a Map between alphabet and the button associated with it .
		
		charButtonMap=new HashMap<Character,JButton>();

		
		for(char i='A';i<='Z';i++) {
			// Add button with the alphabet as its name 
			JButton button=new JButton(Character.toString(i));
			button.setName(Character.toString(i));
			button.setForeground(Color.black);
			button.setFont(new Font("Helvetica",Font.BOLD,14));
			button.setFocusable(false);
			// Disable those keys which is not in the enabledKey lists.
			if(GameUtil.containsValue(enabledKey,i)) {
				button.setEnabled(true);
			}else {
				button.setEnabled(false);
			}
			charButtonMap.put(i, button);
		}
		
		return charButtonMap;
		
	}
	
	
	
	public void dispose() {
		// Dispose the keyboard frame
		keyboardFrame.dispose();
	}
	

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class OnScreenKeyboard{
	
	JFrame keyboardFrame;
	HashMap<Character,JButton> charButtonMap;
	int position_x=-1;
	int position_y=-1;

	public static void main(String[] args) {
		//char[] ekey= {'A','B','C'};
		//OnScreenKeyboard keyboard=new OnScreenKeyboard(ekey);
		//keyboard.createKeyboardUI(ekey);

	}
	
	OnScreenKeyboard(int x,int y){
		char[] ekey= {};
		position_x=x;
		position_y=y;
		createKeyboardUI(ekey);
	}
	OnScreenKeyboard(char[] enabledKey,int x,int y){
		position_x=x;
		position_y=y;
		createKeyboardUI(enabledKey);

	}
	
	
	
	private void createKeyboardUI(char[] enabledKey) {
		/* The Keyboard UI*/
		
		// Create a Frame
		keyboardFrame=new JFrame("On-Screen Keyboard");
		keyboardFrame.setSize(800,150);
		//keyboardFrame.setLocationRelativeTo(null);
		
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
	
	private HashMap createCBMap(char[] enabledKey) {
		
		//Creates a Map between character and button.
		
		charButtonMap=new HashMap<Character,JButton>();

		
		for(char i='A';i<='Z';i++) {
			// Add button with the name 
			JButton button=new JButton(Character.toString(i));
			button.setName(Character.toString(i));
			button.setForeground(Color.black);
			button.setFont(new Font("Helvetica",Font.BOLD,14));
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setVerticalAlignment(SwingConstants.CENTER);
			//button.addActionListener(this);
			button.setFocusable(false);
			// Disable those keys which is not in the enabledKey lists.
			if(containsValue(enabledKey,i)) {
				button.setEnabled(true);
			}else {
				button.setEnabled(false);
			}
			charButtonMap.put(i, button);
		}
		
		return charButtonMap;
		
	}
	
	private boolean containsValue(char[] list,char value) {
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
	
	public void dispose() {
		keyboardFrame.dispose();
	}
	

}

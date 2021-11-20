import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextBox implements ActionListener,MouseListener {
	/* Custom Class for a text box in the playing field
	 * TextBox class contains JTextField and OnScreenKeyboard object
	 *  */
	
	OnScreenKeyboard kyb;
	JTextField textBox;
	boolean textBoxEnabled=true;
	
	char[] enabledKey= {};


	public static void main(String[] args) {
		
		
	}
	
	TextBox(){
		createUI();
	}
	
	TextBox(char[] ekey){
		enabledKey=ekey;
		createUI();
	}
	
	private void createUI() {
		
		// Create a Text Field
		
		textBox=new JTextField();
		textBox.setPreferredSize(new Dimension(100,100));
		textBox.setHorizontalAlignment(JTextField.CENTER);
		textBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textBox.addMouseListener(this);	// When mouse is clicked on the textfield
		
				
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the string associated with the button that is pressed in the on-screen keyboard
		String s=((Component) e.getSource()).getName();
		if(textBoxEnabled)
			{textBox.setText(s);
			// After a button is pressed, the on-screen keyboard disappears
			kyb.dispose();};	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/* When a mouse is clicked in the text field
		 * 1. The previous keyboard is disposed. So pressing a textfield multiple times won't
		 * create multiple keyboards.
		 * 2. If no keyboard object is generated before i.e when the text field is clicked for the first time,
		 * a keyboard object is generated.
		 * 
		 */
		if (e.getSource()==textBox) {
			if(kyb!=null) {
				kyb.dispose();
			}
			// When a textfield is clicked, the keyboard shouldn't cover the textfield.
			// So a position relative to the field is calculated.
			Point location = textBox.getLocationOnScreen();
            int x = location.x%600+100;
            int y = location.y%600+100;
			kyb=new OnScreenKeyboard(enabledKey,x,y);
			for(char i='A';i<='Z';i++) {
				// Add ActionListener to every key in the keyboard
				kyb.charButtonMap.get(i).addActionListener(this);
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

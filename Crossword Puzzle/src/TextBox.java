import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextBox implements ActionListener,MouseListener {
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
		
		// Create a textextBoxield
		
		textBox=new JTextField();
		textBox.setPreferredSize(new Dimension(100,100));
		textBox.setHorizontalAlignment(JTextField.CENTER);
		textBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textBox.addMouseListener(this);	
		
				
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the string from the 
		String s=((Component) e.getSource()).getName();
		if(textBoxEnabled)
			{textBox.setText(s);
			kyb.dispose();};	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==textBox) {
			if(kyb!=null) {
				kyb.dispose();
			}
			Point location = textBox.getLocationOnScreen();
            int x = location.x%600+100;
            int y = location.y%600+100;
			kyb=new OnScreenKeyboard(enabledKey,x,y);
			for(char i='A';i<='Z';i++) {
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game implements FocusListener{
	private int row;
	private int column;
	private String[][] field;
	private ArrayList<String> acrossClues;
	private ArrayList<String> downClues;
	OnScreenKeyboard curKeyboard;
	JFrame gameFrame;
	TextBox curTextBox;
	ArrayList<ArrayList<TextBox>> textBoxes;
	ArrayList<JLabel> allLabels;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Game game=new Game();

	}
	
	Game(int r, int c, String[][] f,ArrayList<String> a,ArrayList<String> d) {
		row=r;
		column=c;
		field=f;
		acrossClues=a;
		downClues=d;
		
		// Find the Hint Keys
		
		gameUI();
		
	}
	Game(){
		gameUI();
	}
	
	public void gameUI() {
		// Frame
		gameFrame=new JFrame("Swedish-Style Crossword Puzzle");
		gameFrame.setSize(1200,1000);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(null);
		gameFrame.setLocationRelativeTo(null);
		
		// Create the Playing Field
		createField();
		
		// Create the Statement Field
		createStatement();
		
		// Create the Check Field
		createCheck();
		
		
		gameFrame.setVisible(true);

		
		
	}
	private void createStatement() {
		
		JPanel statementPanel=new JPanel();
		statementPanel.setLayout(new BoxLayout(statementPanel,BoxLayout.Y_AXIS));
		statementPanel.setBounds(10,10,250,700);
		statementPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//statementPanel.setBackground(Color.red);
		
		// Labels
		JPanel firstPanel=new JPanel(new BorderLayout());
		JLabel firstLabel=new JLabel("Crossword Puzzle Instructions:");
		firstLabel.setForeground(new Color(76,0,153));
		firstLabel.setFont(new Font("Arial",Font.BOLD,15));
		firstPanel.add(firstLabel);
		//allLabels.add(firstLabel);
		
		JPanel acrossPanel=new Panel(acrossClues).aPanel;
		JPanel downPanel=new Panel(downClues).aPanel;
		
		
		statementPanel.add(firstPanel);
		statementPanel.add(acrossPanel);
		statementPanel.add(downPanel);
		
		
		
		gameFrame.add(statementPanel);

		
	}
	
	private void createCheck() {
		JPanel checkPanel=new JPanel();
		checkPanel.setBounds(260,700,740,100);
		checkPanel.setBackground(Color.black);
		
		gameFrame.add(checkPanel);

		
	}
	
	private void createField() {
		
		// Create the Playing field
		
		// Panel
		JPanel fieldPanel=new JPanel(new GridLayout(row,column));
		fieldPanel.setBounds(260,5,740,695);
		
		
		// Add textfield in the fieldPanel
		textBoxes=new ArrayList<ArrayList<TextBox>>();
		
		for(int i=0;i<row;i++) {
			
			ArrayList<TextBox> boxes=new ArrayList<TextBox>();
			for(int j=0;j<column;j++) {
				TextBox tb;
				
				if (field[i][j].contains("H")) {
					// Hint Boxes
					char[] hintKeys=getRandomKeys(field[i][j].charAt(2));
					tb=new TextBox(hintKeys);
					tb.textBox.setBackground(new Color(37,150,190));
					tb.textBox.addFocusListener(this);
				}else if(field[i][j].contains("X")) {
					// Black Boxes
					tb=new TextBox();
					tb.textBox.setBackground(Color.black);
					tb.textBox.setEnabled(false);
					tb.textBoxEnabled=false;
					tb.textBox.removeMouseListener(tb);
				}else if(field[i][j].contains("S")) {
					// Special Boxes
					tb=new TextBox();
					tb.textBox.setBackground(Color.gray);
					tb.textBox.addFocusListener(this);
				}else {
					// Normal Boxes
					
					tb=new TextBox();
					tb.textBox.addFocusListener(this);
				}
				
				boxes.add(tb);
				// Add the textBox in the panel
				fieldPanel.add(boxes.get(j).textBox);
				
			}
			textBoxes.add(boxes);
			
		}
		
		// Add the panel to the frame
		gameFrame.add(fieldPanel);

		
	}
	
	

	private char[] getRandomKeys(char c) {
		ArrayList<Character> hintKeys=new ArrayList<Character>();
		hintKeys.add(c);
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		
		
		while (hintKeys.size()<5) {
			Random rd= new Random();
			char letter = abc.charAt(rd.nextInt(abc.length()));
			if (letter!=c) {
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

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<row;i++) {
			
			for(int j=0;j<column;j++) { 
				//System.out.println(e.getSource());
				
				if(e.getSource()==textBoxes.get(i).get(j).textBox) {
					if(curKeyboard!=null) {
						curKeyboard.dispose();
						curKeyboard=textBoxes.get(i).get(j).kyb;
					}else {
						curKeyboard=textBoxes.get(i).get(j).kyb;
						
					}
				}
				
			}
		
	}
		}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}

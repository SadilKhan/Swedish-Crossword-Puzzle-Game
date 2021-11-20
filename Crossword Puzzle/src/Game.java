import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements ActionListener, FocusListener,GameInterface{
	/* Class for Main Puzzle Game */
	
	private int row; // Number of Rows in the Playing Field.
	private int column; // Number of Columns in the Playing Field.
	private String[][] field; // The Information about the different types of squares.
	private ArrayList<String> acrossClues; // The clues for horizontal box fields.
	private ArrayList<String> downClues; // The clues for vertical box fields.
	OnScreenKeyboard curKeyboard; // Current Keyboard associated with the text field.
	JFrame gameFrame; // Main Game JFrame
	ArrayList<ArrayList<TextBox>> textBoxes; // All the text boxes in the playing field.
	ArrayList<JLabel> allLabels; // All the labels in the clues panel.
	int totalGraySquare=0; // Total number of Gray Squares.
	JButton checkButton; // Button for checking.
	
	Game(int r, int c, String[][] f,ArrayList<String> a,ArrayList<String> d) {
		/* Constructor */
		row=r;
		column=c;
		field=f;
		acrossClues=a;
		downClues=d;
		
		// Start the UI
		
		gameUI();
		
	}
	
	public void gameUI() {
		/* The Interface for the game*/
		
		// Create Frame
		createGameFrame();
		
		// Create the Playing Field
		createField();
		
		// Create the Statement Field
		createStatement();
		
		// Create the Check Field. Contains the final gray squares and the check button
		createCheck();
		
		
		
		gameFrame.setVisible(true);

		
		
	}
	
	public void createGameFrame() {
		/* Create a JFrame upon which we will add component for UI */
		
		gameFrame=new JFrame("Swedish-Style Crossword Puzzle");
		gameFrame.setSize(1000,800); // Set the size of the frame
		gameFrame.setResizable(false); // gameFrame isn't resizable.
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(null);
		gameFrame.setLocationRelativeTo(null);// Makes the frame appear in the center of the screen.
	}
	public void createStatement() {
		
		/* Creates the left panel for clues . It contains one Main Panel and three sub Panels added to 
		 the main Panel. The three sub-panels are firstPanel(which contains only one label) and acrossPanel
		 for across clues and downPanel for down clues
		 */
		
		// Main Panel
		JPanel statementPanel=new JPanel();
		statementPanel.setLayout(new BoxLayout(statementPanel,BoxLayout.Y_AXIS));
		statementPanel.setBounds(10,10,250,700);
		statementPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//statementPanel.setBackground(Color.red);
		
		// The First Panel which contains only one label.
		JPanel firstPanel=new JPanel();
		JLabel firstLabel=new JLabel("Crossword Puzzle Instructions:");
		firstLabel.setForeground(new Color(76,0,153));// Change the color of the label
		firstLabel.setFont(new Font("Arial",Font.BOLD,15)); // Change the font style of the label
		firstPanel.add(firstLabel);
		//allLabels.add(firstLabel);
		
		// Across Panel for across statements
		JPanel acrossPanel=new Panel(acrossClues).aPanel;
		// Down Panel for down statements
		JPanel downPanel=new Panel(downClues).aPanel;
		
		// Add the sub-panels in the Main Panel statementPanel
		statementPanel.add(firstPanel);
		statementPanel.add(acrossPanel);
		statementPanel.add(downPanel);
		
		
		// Add the Main Panel in the main Frame gameFrame
		gameFrame.add(statementPanel);

		
	}
	
	public void createCheck() {
		/* Creates the bottom gray squares and check button. It has one Main Panel and two panels. One for the 
		 squares and one for the button */
		
		//Main Panel
		JPanel mainPanel=new JPanel(new FlowLayout());
		mainPanel.setBounds(340,700,660,50);
		
		// Sub-Panel for gray squares
		JPanel checkPanel=new JPanel(new GridLayout(1,totalGraySquare+1));
		
		// Number of Gray squares is saved in the totalGraySquare. Add that many gray squares.
		ArrayList<TextBox> boxes=new ArrayList<TextBox>();
		for(int i=0;i<totalGraySquare;i++) {
			TextBox tb=new TextBox();
			tb.textBox.setPreferredSize(new Dimension(50,50));
			tb.textBox.setBackground(Color.gray);
			// When a text box is selected, we need to dispose the other keyboard
			tb.textBox.addFocusListener(this); 
			// Add text boxes in the sub-panel for gray boxes
			checkPanel.add(tb.textBox);
			boxes.add(tb);
			
		}
		// Add the text field to textBoxes variable.
		textBoxes.add(boxes);
		
		// The sub panel for the button.
		JPanel checkButtonPanel= new JPanel(new FlowLayout(FlowLayout.CENTER));
		checkButton=new JButton("Check"); // The check button
		checkButton.setFocusable(false); // Gets rid of the border
		checkButton.setSize(10,10);
		checkButton.addActionListener(this);
		checkButtonPanel.add(checkButton);
		checkPanel.add(checkButtonPanel);
		
		// Add the sub-panels to the main panels.
		mainPanel.add(checkPanel);
		mainPanel.add(checkButtonPanel);
		// Add the main panel to the main game frame.
		gameFrame.add(mainPanel);

		
	}
	
	public void createField() {
		
		/* Creates the Playing field. Contains one Main Panel and row x Column numbers of text fields with
		 modified on-screen keyboard attached with each one of them.
		  */
		
		// Main Panel
		JPanel fieldPanel=new JPanel(new GridLayout(row,column));
		fieldPanel.setBounds(260,5,740,695);
		
		
		// Add row x column textfields in the fieldPanel
		textBoxes=new ArrayList<ArrayList<TextBox>>();
		
		for(int i=0;i<row;i++) {
			
			ArrayList<TextBox> boxes=new ArrayList<TextBox>();
			for(int j=0;j<column;j++) {
				TextBox tb;
				/* We have 4 types of boxes.
				 O --> Normal boxes ( All the keys in the keyboard is enabled)
				 H --> Help Boxes ( The keyboard has only four random keys and the answer key enabled)
				 X --> Black Boxes (No keyboard)
				 S --> Special Boxes(All the keys in the keyboard is enabled)
				 *  */
				if (field[i][j].contains("H")) {
					/* Help Boxes*/
					//Generate an array with four random keys and the answer key. Only these keys will be enabled.
					char[] hintKeys=GameUtil.getRandomKeys(field[i][j].charAt(2));
					tb=new TextBox(hintKeys);// Create an object of TextBox object with modified keyboard.
					tb.textBox.setBackground(new Color(37,150,190));
					tb.textBox.addFocusListener(this);// When a text box is selected, dispose the other keyboard
				}else if(field[i][j].contains("X")) {
					/* Black Boxes. Contains no keyboard*/
					tb=new TextBox();
					tb.textBox.setBackground(Color.black);
					tb.textBox.setEnabled(false);// Disable the text field
					tb.textBoxEnabled=false;
					tb.textBox.removeMouseListener(tb); // mouse click on the black boxes will be ignored
				}else if(field[i][j].contains("S")) {
					/*Special Boxes*/
					tb=new TextBox();
					tb.textBox.setBackground(Color.gray);
					tb.textBox.addFocusListener(this);
					totalGraySquare+=1; // When a gray box is created, increase the number of gray squares
				}else {
					/*Normal Boxes*/
					tb=new TextBox();
					tb.textBox.addFocusListener(this);
				}
				
				// boxes is an array which contains the textboxes for every row.
				boxes.add(tb);
				/* After the text box type is identified and object of that type is created ,
				 * add the textBox to the panel
				 */
				fieldPanel.add(boxes.get(j).textBox);
				
			}
			// textBoxes variable contains all the text boxes.
			textBoxes.add(boxes);
			
		}
		
		// Add the panel to the frame
		gameFrame.add(fieldPanel);

		
	}
	
	
	@Override
	public void focusGained(FocusEvent e) {
		// When a text box is selected, this method is called.
		outerloop:
		for(int i=0;i<textBoxes.size();i++) {
			ArrayList<TextBox> temptextBoxes=textBoxes.get(i);
			
			for(int j=0;j<temptextBoxes.size();j++) { 
				//System.out.println(e.getSource());
				
				if(e.getSource()==temptextBoxes.get(j).textBox) {
					// If there is a keyboard on the screen, it disposes
					if(curKeyboard!=null) {
						curKeyboard.dispose();
						curKeyboard=temptextBoxes.get(j).kyb; // Assign a new keyboard
						break outerloop;
					}else {
						curKeyboard=temptextBoxes.get(j).kyb;
						break outerloop;
						
					}
				}
				
			}
		
	}
		}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	
	

}

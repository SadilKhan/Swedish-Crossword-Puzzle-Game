import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface MainInterface {
	
	public static final JButton fileOpenerButton = new JButton("Open File"); // Button for opening a txt file
	public static final JButton gameStartButton = new JButton("Start Game"); // Button for starting the game
	public static final JButton defaultButton = new JButton("Default"); // Button for choosing default txt selection
	public static final JButton manualButton = new JButton("Manual"); // Button for manually choosing txt file
	public static final JButton backButton = new JButton(); // Typical back button
	public static final JPanel solPanel = new JPanel(); // Panel for solution word
	public static final JPanel openPanel = new JPanel();// Panel for Opening Buttons
	public static final JPanel welcPanel = new JPanel();// Panel for Welcome label
	public static final JPanel choosePanel = new JPanel(); // Panel for default and manual txt selection
	public static final JFrame fileOpenerFrame = new JFrame("Crossword Puzzle");

}

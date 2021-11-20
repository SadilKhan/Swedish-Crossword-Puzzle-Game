import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

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

}

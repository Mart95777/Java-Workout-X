/**
 * 
 */
package javaworkoutx;

import javax.swing.JFrame;

/**
 * @author marcin
 *
 */
public class JavaWorkoutX extends JFrame{
	
	public static final String VERSION = "1.0.0";

	/**
	 * Constructor
	 */
	public JavaWorkoutX() {
		// TODO Auto-generated constructor stub
		super("JAVA Workout X, [after change] ver. " + VERSION);
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.setVisible(true);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaWorkoutX frame = new JavaWorkoutX();
		

	}

}

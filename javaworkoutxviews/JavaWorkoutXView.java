/**
 * 
 */
package javaworkoutxviews;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author marcin
 *
 */
public class JavaWorkoutXView extends JFrame {
	/**
	 * Fields
	 */
	JPanel mainPanel;
	JSplitPane splitPaneMain;

	/**
	 * CONSTRUCTOR
	 */
	public JavaWorkoutXView() {
		// TODO Auto-generated constructor stub
		super("Java Workout X program, ...");
		this.setPreferredSize(new Dimension(700,500));
		// temporary
		this.setSize(1600, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		
		splitPaneMain = new JSplitPane();
		mainPanel.add(splitPaneMain);
		
		
		this.add(mainPanel);
		this.pack();
	}

}

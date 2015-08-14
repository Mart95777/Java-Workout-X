/**
 * 
 */
package javaworkoutxviews;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

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
	JSplitPane splitPaneRight;
	JScrollPane scrollLeft;
	JTree jtree;

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
		mainPanel.setLayout(new GridBagLayout());
		
		// tree on the left, starting from the last
		jtree = new JTree();
		
		// on the right, another split pane, this time horizontal
		// a
		splitPaneRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT,null,null);
		
		splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jtree,splitPaneMain);
		mainPanel.add(splitPaneMain);
		
		
		
		
		this.add(mainPanel);
		//this.pack();
	}
	
	// pn - where to add; cmp - what to add; 
	// gridx - x of grid; gridy - y of grid; gridwidth - how wide; gridheight - how high;
	// 
	private void addComponent (JPanel pn, JComponent cmp, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill, 
			int insetT, int insetB, int insetL, int insetR, int ipadx, int ipady, double weightx, double weighty){
		GridBagConstraints gridBC = new GridBagConstraints();
		gridBC.gridx = gridx;
		gridBC.gridy = gridy;
		gridBC.gridwidth = gridwidth;
		gridBC.gridheight = gridheight;
		gridBC.anchor = anchor;
		gridBC.fill = fill;
		gridBC.insets = new Insets(insetT,insetB,insetL,insetR);
		gridBC.ipadx = ipadx;
		gridBC.ipady = ipady;
		gridBC.weightx = weightx;
		gridBC.weighty = weighty;
		pn.add(cmp,gridBC);
	}
	
//	private void addcomponent(JPanel pn, JComponent cmp, int xpos, int ypos, int w, int h, int place, int stretch){
//		GridBagConstraints gridcns = new GridBagConstraints();
//		//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
//		gridcns.gridx = xpos;
//		gridcns.gridy = ypos;
//		gridcns.gridwidth = w;
//		gridcns.gridheight = h;
//		gridcns.weightx = 100;
//		gridcns.weighty = 100;
//		gridcns.insets = new Insets(5,5,5,5);
//		gridcns.anchor = place;
//		gridcns.fill = stretch;
//		
//		pn.add(cmp, gridcns);
//	} // end private void addcomponent

}

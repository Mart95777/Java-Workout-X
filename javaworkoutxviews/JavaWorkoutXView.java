/**
 * 
 */
package javaworkoutxviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JMenuBar;

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
	JPanel rightInfoPanel;
	JTextArea jText1;
	JTextArea jText2;
	Color colorBKG;
	
	JMenuBar menuBar;
	
	JButton buttonPrevious = new JButton("<");
	JButton buttonNext  = new JButton(">");
	JButton buttonOpenCode = new JButton("Open Code");
	
	static File NOTEPAD_PP_PATH = null;
	
	static String WIN_PROGRAMFILES = System.getenv("programfiles");
	static String WIN_PROGRAMFILES86 = System.getenv("ProgramFiles(X86)");
	
	//System.getenv("%programfiles% (x86)"); 
    static String FILE_SEPARATOR   = System.getProperty("file.separator");

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
		//mainPanel.setLayout(new GridBagLayout());
		//mainPanel.setPreferredSize(new Dimension(680,480));
		//mainPanel.setLayout(new GridBagLayout());
		colorBKG = mainPanel.getBackground();
		
		// tree on the left, starting from the last
		jtree = new JTree();
		jtree.setPreferredSize(new Dimension(280,1250));
		
		// on the right, another split pane, this time horizontal
		// and for testing
		
		rightInfoPanel = new JPanel();
		rightInfoPanel.setLayout(new GridBagLayout());
		
		
		
		jText1 = new JTextArea("test...1");
		jText1.setEditable(false);
		jText1.setBackground(colorBKG);
		jText1.setBorder(BorderFactory.createEmptyBorder());
		jText1.setPreferredSize(new Dimension(880,200));
		
		addComponent(rightInfoPanel,jText1,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,2,2,2,2,3,3,100,100);
		addComponent(rightInfoPanel,buttonPrevious,1,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,2,2,2,2,3,3,70,70);
		addComponent(rightInfoPanel,buttonNext,2,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,2,2,2,2,3,3,70,70);
		addComponent(rightInfoPanel,buttonOpenCode,1,1,2,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,2,2,2,2,3,3,70,70);
		
		buttonOpenCode.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	                //System.out.println("You clicked the button");
	            	JOptionPane.showMessageDialog(null, WIN_PROGRAMFILES);
	            	JOptionPane.showMessageDialog(null, WIN_PROGRAMFILES86);
	            	
	            }
	        });    
		
		
		jText2 = new JTextArea("test...2");
		
		splitPaneRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT,rightInfoPanel,jText2);
		//splitPaneRight.setBorder(BorderFactory.createEmptyBorder());
		
		splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jtree,splitPaneRight);
		//splitPaneMain.setLayout(new GridBagLayout());
		mainPanel.add(splitPaneMain);
		
		// menu bar =================== >
		menuBar = new JMenuBar();
		
		
		// File Menu, F - Mnemonic
	    JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(fileMenu);
	    
	    // Settings Menu, S - Mnemonic
	    JMenu settingsMenu = new JMenu("Settings");
	    settingsMenu.setMnemonic(KeyEvent.VK_S);
	    menuBar.add(settingsMenu);
	    
	    //JMenuItem newMenuItem = new JMenuItem("Notepad++ Path", KeyEvent.VK_N);
	    JMenuItem notepadppMenuItem = new JMenuItem("Notepad++ Path");
	    notepadppMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "You can set or change path\nto Notepad++ application");
	    		File notepadFolder = setNotepadPPPath();
	    		String s = notepadFolder.toString() + "\\notepad++.exe";
	    		NOTEPAD_PP_PATH = new File(s);
	    		
	    		JOptionPane.showMessageDialog(null, NOTEPAD_PP_PATH);
	    		
	    		//}
	    		
	        }
	    });
	    settingsMenu.add(notepadppMenuItem);

	    
	    
	    
	    
	    setJMenuBar(menuBar);
	    
	    
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
	
	private File setNotepadPPPath(){
	// this method finds string which is path to folder, where Notepadd++ is
		JFileChooser f = new JFileChooser();
		f.setCurrentDirectory(new java.io.File(".")); // start at application current directory
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = f.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return f.getSelectedFile();
		} else {
			return null;
		}
		}//end of private void setNotepadPPPath(){

}

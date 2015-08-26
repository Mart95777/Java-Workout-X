/**
 * 
 */
package javaworkoutxviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javaworkoutx.JavaWorkoutX_old;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

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
    
    File runningFolder = null;
    String currentUser = null;

	/**
	 * CONSTRUCTOR
	 */
	public JavaWorkoutXView() {
		// TODO Auto-generated constructor stub
		super("Java Workout X program, ...");
		//this.setPreferredSize(new Dimension(700,500));
		// getting "client" area
		
		// temporary
		// get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int screenW = screenSize.width;
		//int screenH = screenSize.height;
		this.setSize(screenSize.width-80, screenSize.height-100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		//mainPanel.setLayout(new GridBagLayout());
		//mainPanel.setPreferredSize(new Dimension(680,480));
		//mainPanel.setLayout(new GridBagLayout());
		colorBKG = mainPanel.getBackground();
		
		// tree on the left, starting from the last
		jtree = new JTree();

		
		// on the right, another split pane, this time horizontal
		// and for testing
		
		rightInfoPanel = new JPanel();
		rightInfoPanel.setLayout(new GridBagLayout());
		
		
		
		jText1 = new JTextArea("test...1");
		jText1.setEditable(false);
		jText1.setBackground(colorBKG);
		jText1.setBorder(BorderFactory.createEmptyBorder());
		// finding sizes


		
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
		// Resizing ... ================================================================================
		Rectangle clientRectangle = this.getContentPane().getBounds();
		clientRectangle = this.getBounds();
				//JOptionPane.showMessageDialog(null, "clientRectangle.height: "+clientRectangle.getHeight());
				//JOptionPane.showMessageDialog(null, "clientRectangle.width: "+clientRectangle.getWidth());
		jtree.setPreferredSize(new Dimension(300,clientRectangle.height-75));
		int w = clientRectangle.width-500;
		jText1.setPreferredSize(new Dimension(w,200));
		//this.pack();
		// running folder =====================================================================================
		StringBuilder str1 = new StringBuilder();
		str1.append(new File(".").getAbsolutePath());
		runningFolder = new File(str1.toString()).getParentFile();
		//this.checkForNewInstal(this, runningFolder);
		// Factory builder document ... ================================================================================
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			OpenJWX frameOpenJWX = new OpenJWX(this,builder,runningFolder);
			this.toFront();
			this.requestFocus();
			this.repaint();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	

}// end of class JavaWorkoutX

class OpenJWX extends JFrame {
	JPanel mainPanel;
	JTextArea textUserSelection;
	JTextArea labelNewUser;
	JTextArea textNewUser;
	JButton userCreate;
	JButton userOK;
	
	File newUserFolder = null;
	
	DefaultListModel<String> model;
	JList<String> userList;
	
	
	/**
	 * Methods for the constructor - OpenJWX
	 */
	
	private void addcomponent(JPanel pn, JComponent cmp, int xpos, int ypos, int w, int h, int place, int stretch){
		GridBagConstraints gridcns = new GridBagConstraints();
		//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
		gridcns.gridx = xpos;
		gridcns.gridy = ypos;
		gridcns.gridwidth = w;
		gridcns.gridheight = h;
		gridcns.weightx = 100;
		gridcns.weighty = 100;
		gridcns.insets = new Insets(5,5,5,5);
		gridcns.anchor = place;
		gridcns.fill = stretch;
		
		pn.add(cmp, gridcns);
	} // end private void addcomponent
	
	/**
	 * CONSTRUCTOR!
	 */
	public OpenJWX(JavaWorkoutXView frame, DocumentBuilder builder, File runningFolder){
		super("JAVA Workout - Selecting User");
		this.setSize(450,200);
		this.setLocationRelativeTo(null);
		
		boolean success;
		File[] usersFolders = null;
		
		StringBuilder str2 = new StringBuilder();
		//StringBuilder str1 = new StringBuilder();
					
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		textUserSelection = new JTextArea();
		//textUserSelection.setLineWrap(true);
		//textUserSelection.setWrapStyleWord(true);
		//textUserSelection.setText("Select user: (This will close this dialog box)");
		textUserSelection.setText("Select user: ");
		textUserSelection.setEditable(false);
		textUserSelection.setOpaque(false);
		addcomponent(mainPanel, textUserSelection, 0,0,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		labelNewUser = new JTextArea();
		labelNewUser.setText("New user: ");
		labelNewUser.setEditable(false);
		labelNewUser.setOpaque(false);
		addcomponent(mainPanel, labelNewUser, 1,0,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		textNewUser = new JTextArea();
		textNewUser.setBackground(new Color(255,255,255));
		textNewUser.setPreferredSize(new Dimension(150,17));
		//textNewUser.setText("...");
		textNewUser.setEditable(true);
		textNewUser.setOpaque(true);
		addcomponent(mainPanel, textNewUser, 1,1,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		model = new DefaultListModel<>();
		userList = new JList<>();
		userList.setModel(model);
		JScrollPane jscrl = new JScrollPane(userList);
		Dimension dim = new Dimension(200,120);
		jscrl.setPreferredSize(dim);
		userList.setPreferredSize(dim);
		//jscrl.setPreferredSize(new Dimension(200,120));
		//test 15 elements
//		for (int i = 0; i < 15; i++)
//		      model.addElement("Element " + i);
		// Finding all folders with users
		str2.setLength(0);
		str2.append(runningFolder.toString());
		str2.append("\\data\\users\\");
		
		listFoldersOfUsers(usersFolders,model,str2.toString());
		
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addcomponent(mainPanel, jscrl, 0,1,1,6, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		userCreate = new JButton("Create user");
		userCreate.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  // 
				  boolean success;
				  
				  str2.setLength(0);
				  str2.append(runningFolder.toString());
				  str2.append('\\');
				  newUserFolder = new File(str2.toString(),"data\\users\\"+textNewUser.getText());
				  success = newUserFolder.mkdir();
				  JOptionPane.showMessageDialog(null, "success(new users folder created): "+success);
				  str2.setLength(0);
				  str2.append(runningFolder.toString());
				  str2.append("\\data\\users\\");
				  listFoldersOfUsers(usersFolders,model,str2.toString());
			  }
		});
		addcomponent(mainPanel, userCreate, 1,2,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		userOK = new JButton("OK, Start Program");
		userOK.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				// 
				frame.currentUser = userList.getSelectedValue().toString();
				//frame.textSelection.append(" "+frame.currentUser);
				// dealing with user
				String s = frame.currentUser;
				// parsing xml
				//DOMparseJWX parser1 = new DOMparseJWX((DocumentBuilder) frame.document, frame, runningFolder, s);
				try {
					//frame.mDOMparse(builder, runningFolder,frame.currentUser);
					JOptionPane.showMessageDialog(null, "Parsing will be inserted here");
				} catch (Exception e) {
//				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			  }
		});
		addcomponent(mainPanel, userOK, 0,8,2,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		
		this.add(mainPanel);
		this.pack();
		this.setVisible(true);
		//setVisible(true);
		this.toFront();
		this.requestFocus();
		this.repaint();
		// TESTING !!!!!!!!!!!!!!!!!!!!
		//frame.appendTextSelection(" addition!");
		
	
	}//end of Constructor OpenJWX
	
	/**
	 * Other methods of OpenJWX
	 */
	private void listFoldersOfUsers(File[] usersFolders, DefaultListModel<String> model,String str){
		StringBuilder str2 = new StringBuilder("");
		model.clear();
		try {
			usersFolders = new File(str).listFiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Problem with listing user Folders files");
			e.printStackTrace();
		}
		for (File temp : usersFolders){
			if(temp.isDirectory()){
				str2.setLength(0);
				str2.append(temp.getName().toString());
				model.addElement(str2.toString());
			}
		}
	}
	
	
	
}// end of class OpenJWX

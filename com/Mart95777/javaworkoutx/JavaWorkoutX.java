package com.Mart95777.javaworkoutx;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author marcin
 *
 */

public class JavaWorkoutX extends JFrame {


	/**
	 * @param args
	 */
	public static final String VERSION = "1.0.0";
	JPanel mainPanel;
	JTextArea textSelection;
	
	File dataFolder = null;
	File topicsFile = null;
	File usersFolder = null;
	
	String currentUser = null;
	
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaWorkoutX frame = new JavaWorkoutX();
		//
		StringBuilder str1 = new StringBuilder();
		str1.append(new File(".").getAbsolutePath());
		File runningFolder = null;
		runningFolder = new File(str1.toString()).getParentFile();
		//
		frame.checkForNewInstal(runningFolder);
		OpenJWX frameOpenJWX = new OpenJWX(frame,runningFolder);
		
		
	}
	/**
	 * ACCESSORS
	 */
	
	public void appendTextSelection(String s){
		this.textSelection.append(s);
	}
	
	/**
	 * Methods for the constructor
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
	public JavaWorkoutX(){
		super("JAVA Workout X, ver. " + VERSION);
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		textSelection = new JTextArea();
		textSelection.setText("Select main topic and subtopic below: ");
		textSelection.setEditable(false);
		textSelection.setOpaque(false);
		addcomponent(mainPanel, textSelection, 0,0,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		
		this.add(mainPanel);
		this.setVisible(true);
	}
	
	/**
	 * checkForNewInstal method is performing verification of program install state:
	 * - if folder with xml is in place
	 * - if code files with examples are in place
	 * - ...
	 */
	private void checkForNewInstal(File runningFolder){
		//...
		boolean success;
		StringBuilder str2 = new StringBuilder();
					
		str2.setLength(0);
		str2.append(runningFolder.toString());
		str2.append('\\');

		// checking if data folder exists
		dataFolder = new File(str2.toString(),"data");
		success = dataFolder.mkdir();
		//JOptionPane.showMessageDialog(null, "success: "+success);
		//JOptionPane.showMessageDialog(null, "File dataFolder: "+ dataFolder.toString());
		//
		topicsFile = new File(str2.toString(),"data\\topics.xml");
		if(!topicsFile.exists()){
			JOptionPane.showMessageDialog(null, "topics.xml file not found\nPlease, place the 'topics.xml' file in 'data' folder\nQuiting...");
			System.exit(0);
		}
		//
		usersFolder = new File(str2.toString(),"data\\users");
		success = usersFolder.mkdir();
		//JOptionPane.showMessageDialog(null, "success(users folder): "+success);
		//
		
	}

}

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
	public OpenJWX(JavaWorkoutX frame, File runningFolder){
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
				DOMparseJWX parser1 = new DOMparseJWX(frame, runningFolder, s);
				dispose();
			  }
		});
		addcomponent(mainPanel, userOK, 0,8,2,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		
		this.add(mainPanel);
		this.pack();
		this.setVisible(true);
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
	
	
}

class DOMparseJWX {
	
	
	public DOMparseJWX(JavaWorkoutX frame, File runningFolder, String currentUser){
		StringBuilder str2 = new StringBuilder();
		File topicsFile = null;
		
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		//
		str2.setLength(0);
		str2.append(runningFolder.toString());
		//str2.append("\\data\\users\\"+currentUser+"\\topics.xml"); 
		
		topicsFile = new File(str2.toString(),"\\data\\users\\"+currentUser+"\\topics.xml");
		//JOptionPane.showMessageDialog(null, topicsFile.toString());
		if(!topicsFile.exists()){
			JOptionPane.showMessageDialog(null, "no topics.xml in this user folder");
			System.exit(0);
		}
//		if(topicsFile.exists()){
//			JOptionPane.showMessageDialog(null, "topics.xml found!");
//		}
		//
		Document document = builder.parse(topicsFile);
		
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {

			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				//JOptionPane.showMessageDialog(null, "node: "+ elem.toString());
				echo(node);
				


			}
		}// for
		
		
		
		
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		
	}// end constructor
	
	/**
	 *  Methods
	 *  echo - this method from example Oracle - testing
	 */
	private void echo(Node n) {
	    int type = n.getNodeType();

	    switch (type) {
	        case Node.ATTRIBUTE_NODE:
	            System.out.print("ATTR:");
	            System.out.println(n);
	            break;

	        case Node.CDATA_SECTION_NODE:
	            System.out.print("CDATA:");
	            System.out.println(n);
	            break;

	        case Node.COMMENT_NODE:
	            System.out.print("COMM:");
	            System.out.println(n);
	            break;

	        case Node.DOCUMENT_FRAGMENT_NODE:
	            System.out.print("DOC_FRAG:");
	            System.out.println(n);
	            break;

	        case Node.DOCUMENT_NODE:
	            System.out.print("DOC:");
	            System.out.println(n);
	            break;

	        case Node.DOCUMENT_TYPE_NODE:
	            System.out.print("DOC_TYPE:");
	            System.out.println(n);
//	            NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
//	            for (int i = 0; i < nodeMap.getLength(); i++) {
//	                Entity entity = (Entity)nodeMap.item(i);
//	                echo(entity);
//	            }
	            break;

	        case Node.ELEMENT_NODE:
	            System.out.print("ELEM:");
	            System.out.println(n);

	            NamedNodeMap atts = n.getAttributes();
	            for (int i = 0; i < atts.getLength(); i++) {
	                Node att = atts.item(i);
	                echo(att);
	            }
	            break;

	        case Node.ENTITY_NODE:
	            System.out.print("ENT:");
	            System.out.println(n);
	            break;

	        case Node.ENTITY_REFERENCE_NODE:
	            System.out.print("ENT_REF:");
	            System.out.println(n);
	            break;

	        case Node.NOTATION_NODE:
	            System.out.print("NOTATION:");
	            System.out.println(n);
	            break;

	        case Node.PROCESSING_INSTRUCTION_NODE:
	            System.out.print("PROC_INST:");
	            System.out.println(n);
	            break;

	        case Node.TEXT_NODE:
	            System.out.print("TEXT:");
	            System.out.println(n);
	            break;

	        default:
	            System.out.print("UNSUPPORTED NODE: " + type);
	            System.out.println(n);
	            break;
	    }

	    for (Node child = n.getFirstChild(); child != null;
	         child = child.getNextSibling()) {
	        echo(child);
	    }
	}// end of echo
	
}//end class DOMparseJWX

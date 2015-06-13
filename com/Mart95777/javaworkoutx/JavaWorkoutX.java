package com.Mart95777.javaworkoutx;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
	
	File newUserFolder = null;
	
	DefaultListModel model;
	JList userList;
	
	
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
		super("JAVA Workout Opening ...");
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		
		boolean success;
		File[] usersFolders = null;
		
		StringBuilder str2 = new StringBuilder();
		//StringBuilder str1 = new StringBuilder();
					
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		textUserSelection = new JTextArea();
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
		
		model = new DefaultListModel();
		userList = new JList();
		userList.setModel(model);
		JScrollPane jscrl = new JScrollPane(userList);
		jscrl.setPreferredSize(new Dimension(200,120));
		//test 15 elements
//		for (int i = 0; i < 15; i++)
//		      model.addElement("Element " + i);
		// Finding all folders with users
		str2.setLength(0);
		str2.append(runningFolder.toString());
		str2.append("\\data\\users\\");
		try {
			//newUserFolder = new File(str2.toString(),"data\\users\\"+textNewUser.getText());
			usersFolders = new File(str2.toString()).listFiles();
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
		
		this.userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addcomponent(mainPanel, jscrl, 0,1,1,6, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		userList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lsEvent) {
				if (! lsEvent.getValueIsAdjusting())
				{
//					inifacsetX = facsets.get(userList.getSelectedIndex());
//					StringBuilder strB1 = new StringBuilder("");
//					//strB1 = "";
//					for(int j=1;j<7;++j){
//						strB1.append(inifacsetX.get(j));
//						strB1.append("\n");
//					}
//					strB1.append(inifacsetX.get(7));
//					ta4.setText(strB1.toString());
					
				}
				
			}
		});
		
		
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
			  }
		});
		addcomponent(mainPanel, userCreate, 1,2,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		this.add(mainPanel);
		this.setVisible(true);
		// TESTING !!!!!!!!!!!!!!!!!!!!
		//frame.appendTextSelection(" addition!");
		
	
	}
	
	
}

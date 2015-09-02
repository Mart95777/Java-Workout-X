package javaworkoutxmodels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javaworkoutx.JavaWorkoutX_old;
import javaworkoutxcontrollers.JavaWorkoutXController;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPathExpressionException;

public class CurrentUserGetter extends JFrame {
	JPanel mainPanel;
	JTextArea textUserSelection;
	JTextArea labelNewUser;
	JTextArea textNewUser;
	JButton userCreate;
	JButton userOK;
	
	File newUserFolder = null;
	File runningFolder = null;
	
	DefaultListModel<String> modelDLM;
	JList<String> userList;
	
	JavaWorkoutXModel modelRef = null;
	JavaWorkoutXController controller = null;
	
	
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
	public CurrentUserGetter(JavaWorkoutXModel model, JavaWorkoutXController controller){
		super("JAVA Workout - Selecting User");
		
		this.controller = controller;
		this.modelRef = model;
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
		
		modelDLM = new DefaultListModel<>();
		userList = new JList<>();
		// moved !!! -> userList.setModel(modelDLM);
		
		// Finding running folder
				StringBuilder str1 = new StringBuilder();
				str1.append(new File(".").getAbsolutePath());
				runningFolder = new File(str1.toString()).getParentFile();
				
				
				str2.setLength(0);
				if (runningFolder != null){
					str2.append(runningFolder.toString());
					str2.append("\\data\\users\\");
				}
				else{
					JOptionPane.showMessageDialog(null, "ERROR! Running folder string not set.");
				}
				
				
				listFoldersOfUsers(usersFolders,modelDLM,str2.toString());
//				mainPanel.validate();
//				mainPanel.repaint();
				userList.setModel(modelDLM);
				userList.setPreferredSize(new Dimension(300,300));
		
		
		JScrollPane jscrl = new JScrollPane(userList);
		Dimension dim = new Dimension(320,320);
		jscrl.setPreferredSize(dim);
		userList.setPreferredSize(dim);
		//jscrl.setPreferredSize(new Dimension(200,120));
		//test 15 elements
//		for (int i = 0; i < 15; i++)
//		      model.addElement("Element " + i);
		// Finding all folders with users
		
		
		
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	if (e.getClickCount() == 2) {
		    		userOK.doClick();
		    	}
		    }
		};
		userList.addMouseListener(mouseListener);
		
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
				  listFoldersOfUsers(usersFolders,modelDLM,str2.toString());
			  }
		});
		addcomponent(mainPanel, userCreate, 1,2,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		userOK = new JButton("OK, Start Program");
		userOK.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				// 
				
				String str = userList.getSelectedValue().toString();
				//modelRef.setCurrentUser(str);
				setUser(userList.getSelectedValue().toString());
				//frame.textSelection.append(" "+frame.currentUser);
				// dealing with user
				//JOptionPane.showMessageDialog(null, "About to dispose");
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
	private void listFoldersOfUsers(File[] usersFolders, DefaultListModel<String> modelDLM,String str){
		StringBuilder str2 = new StringBuilder("");
		modelDLM.clear();
		try {
			usersFolders = new File(str).listFiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Problem with listing user Folders files");
			e.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null, "modelDLM size is: "+ modelDLM.getSize());
		for (File temp : usersFolders){
			if(temp.isDirectory()){
				str2.setLength(0);
				str2.append(temp.getName().toString());
				modelDLM.addElement(str2.toString());
			}
		}
		//
	}// end of private void listFoldersOfUsers(File[] usersFolders, ...
	
	private void setUser(String str){
		//JOptionPane.showMessageDialog(null, "in setUser");
		if (modelRef != null){
		modelRef.setCurrentUser(str);
		}else{
			JOptionPane.showMessageDialog(null, "Error: modelRef is null");
		}
		controller.loadDocument(str);
		controller.updateView();
		
	}//end of private void setUser(String str){
}


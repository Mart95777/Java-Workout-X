/**
 * 
 */
package javaworkoutxmodels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 * @author marcin
 *
 */
public class JavaWorkoutXModel {
	
	
	File dataFolder = null;
	File topicsFile = null;
	File usersFolder = null;
	File runningFolder = null;
	
	String currentUser = null;
	
	JavaWorkoutXController controller = null;
	

	/**
	 * 
	 */
	public JavaWorkoutXModel() {
		// TODO Auto-generated constructor stub
		StringBuilder str1 = new StringBuilder();
		str1.append(new File(".").getAbsolutePath());
		runningFolder = new File(str1.toString()).getParentFile();
		//JOptionPane.showMessageDialog(null, "running folder: "+runningFolder);
		//currentUser = getCurrentUser();
		//JOptionPane.showMessageDialog(null, "current user: "+currentUser);
	}
	
	public void readTopics(){
		JOptionPane.showMessageDialog(null, "Inside readTopics method");
		// getting current user
		
		
	}
	
	// not needed ???????????
	public String getCurrentUser(){
		return currentUser;

	}
	
	public void setCurrentUser(String str){
		this.currentUser = str;
	}
	
	public void setController(JavaWorkoutXController controller){
		this.controller = controller;
	}
	
	public String jText1Set(){
		StringBuilder sb = new StringBuilder();
		sb.append("User: "+this.getCurrentUser());
		sb.append("\nTopic: ");
		
		return sb.toString();
	}

}

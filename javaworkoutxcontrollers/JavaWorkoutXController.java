/**
 * 
 */
package javaworkoutxcontrollers;

import javax.swing.JOptionPane;

import javaworkoutxviews.*;
import javaworkoutxmodels.*;

/**
 * @author marcin
 *
 */
public class JavaWorkoutXController {
	private JavaWorkoutXModel model;
	private JavaWorkoutXView view;
	
//	public void startApplication(){
//		// set view instance here
//		
//	}

	/**
	 * 
	 */
	public JavaWorkoutXController(JavaWorkoutXModel model, JavaWorkoutXView view) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.view = view;
		
		view.setVisible(true);
		updateView();
	}// end of constructor
	
	//constructor2
	//public JavaWorkoutXController(){};
	
	public void updateView(){
		//JOptionPane.showMessageDialog(null, "Marker for updating view");
		
		// TESTING !!!!!!!!!!!!!!!!!!!!!!!!!
		view.updateText1(model.jText1Set());
	}// end public void updateView(){
	
	public void getUser(){
		//JOptionPane.showMessageDialog(null, "Marker for controller getUser");
		if (this.model == null){
			JOptionPane.showMessageDialog(null, "Error: model is null");
		}
		CurrentUserGetter curUserGetter = new CurrentUserGetter( model,this);
	}
	
	public void loadDocument(String str){
		model.loadDocument(str);
	}
	
	

}

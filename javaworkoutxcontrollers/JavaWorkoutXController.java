/**
 * 
 */
package javaworkoutxcontrollers;

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
	}

}

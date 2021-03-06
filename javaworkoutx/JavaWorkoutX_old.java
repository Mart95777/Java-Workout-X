package javaworkoutx;

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
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.w3c.dom.xpath.XPathExpression;
import org.xml.sax.SAXException;

/**
 * @author marcin
 *
 */

public class JavaWorkoutX_old extends JFrame {


	/**
	 * @param args
	 */
	public static final String VERSION = "1.0.0";
	JPanel mainPanel;
	JTextArea textSelection;
	
	File dataFolder = null;
	File topicsFile = null;
	File usersFolder = null;
	File runningFolder = null;
	
	String currentUser = null;
	// document current for the user, gets parsed in DOMparser class
	Document document = null;
	JTree treeOfUser = null;
	DefaultTreeModel myModelTree = null;
	DefaultMutableTreeNode root = null;
	NodeList listOfCurrentTopics = null;
	
	
	static final String[] typeName = {
		  "none",
		  "Element",
		  "Attr",
		  "Text",
		  "CDATA",
		  "EntityRef",
		  "Entity",
		  "ProcInstr",
		  "Comment",
		  "Document",
		  "DocType",
		  "DocFragment",
		  "Notation",
		}; 
	
	
//	@SuppressWarnings("resource")
//	public static void main(String[] args) {
//		JavaWorkoutX_old frame = new JavaWorkoutX_old();
//		// testing
//		//JOptionPane.showMessageDialog(null, "after construction (in main now) currentUser: "+frame.currentUser);		
//	}
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
	public JavaWorkoutX_old(){
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
		
		
		
		
		treeOfUser = new JTree();
		
		// Build left-side view
		JScrollPane treeView = new JScrollPane(treeOfUser);
		treeView.setPreferredSize(new Dimension( 350, 500 ));
		
		
		// Build right-side view
		  JEditorPane htmlPane = new JEditorPane("text/html","");
		  htmlPane.setEditable(false);
		  JScrollPane htmlView = new JScrollPane(htmlPane);
		  htmlView.setPreferredSize(  new Dimension( 350, 500 ));
		  
		// Build split-pane view
		  JSplitPane splitPane =
		    new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeView, htmlView );
		  splitPane.setContinuousLayout( true );
		  splitPane.setDividerLocation( 350 );
		  splitPane.setPreferredSize( 
		    new Dimension( 700 + 10, 500+10 ));
		// adding split pane to 
		addcomponent(mainPanel, splitPane, 0,1,1,3, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		JButton fixIt;
		fixIt = new JButton("Fix - update JTree");
		fixIt.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				// 
				//treeOfUser.invalidate();
				//treeOfUser.validate();
				//treeOfUser.repaint();
				//treeOfUser.setModel(modelTree);
				//modelTree.reload();
				mainPanel.updateUI();
				JOptionPane.showMessageDialog(null, "So?...");
				
			  }
		});
		addcomponent(mainPanel, fixIt, 0,8,2,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		
		this.add(mainPanel);
		//this.pack();
		this.setVisible(true);
		
		StringBuilder str1 = new StringBuilder();
		str1.append(new File(".").getAbsolutePath());
		runningFolder = new File(str1.toString()).getParentFile();
		this.checkForNewInstal(this, runningFolder);
		
		// testing
		//JOptionPane.showMessageDialog(null, "currentUser: "+this.currentUser);
		// 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			OpenJWX frameOpenJWX = new OpenJWX(this,builder,runningFolder);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * checkForNewInstal method is performing verification of program install state:
	 * - if folder with xml is in place
	 * - if code files with examples are in place
	 * - ...
	 */
	private void checkForNewInstal(JavaWorkoutX_old frame, File runningFolder){
		//...
		boolean success;
		StringBuilder str2 = new StringBuilder();
					
		str2.setLength(0);
		str2.append(runningFolder.toString());
		str2.append('\\');

		// checking if data folder exists
		frame.dataFolder = new File(str2.toString(),"data");
		success = dataFolder.mkdir();
		//JOptionPane.showMessageDialog(null, "success: "+success);
		//JOptionPane.showMessageDialog(null, "File dataFolder: "+ dataFolder.toString());
		//
		frame.topicsFile = new File(str2.toString(),"data\\topics.xml");
		if(!frame.topicsFile.exists()){
			JOptionPane.showMessageDialog(null, "topics.xml file not found\nPlease, place the 'topics.xml' file in 'data' folder\nQuiting...");
			System.exit(0);
		}
		//
		frame.usersFolder = new File(str2.toString(),"data\\users");
		success = frame.usersFolder.mkdir();
		//JOptionPane.showMessageDialog(null, "success(users folder): "+success);
		//
		
	}
	
	private void docChecker(Document document){
		// ADDITIONAL TESTS !!!!!!!!!!!!!!
		
				document.getDocumentElement().normalize();
				//System.out.println("Doctype: "+document.getDoctype());
				System.out.println("Root element: " + document.getDocumentElement().getNodeName());
				System.out.println("We are in root element now...");
				
				int indent = 1;
				//Element element = null;
				//element = document.getDocumentElement();
				Node n = (Node)document.getDocumentElement();
				System.out.println("element: "+n);
				nodePrint(n,indent);
				
				// by tag name
				NodeList n1 = document.getElementsByTagName("exercise");
				System.out.println("element: "+n1);
				//nodePrint(n1,indent);
				for (int i=0, len=n1.getLength();i<len;++i){
					
					Node node = n1.item(i);
					System.out.println("---"+i);
					System.out.println("Local Name: "+node.getLocalName());
					System.out.println("Node Name: "+node.getNodeName());
					System.out.println("Node Type: "+node.getNodeType());
					System.out.println("Node Value: \""+node.getNodeValue()+"\"");
					System.out.println("Node Owner doc: "+node.getOwnerDocument());
					System.out.println("Node Parent Node: "+node.getParentNode());
					
				}
				
				
				
				// SAVING ======================================================
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer;
				DOMSource source = new DOMSource(document);
				StreamResult result = new StreamResult(new File("file.xml"));
				try {
					transformer = transformerFactory.newTransformer();
					transformer.transform(source, result);
				} catch (TransformerConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		 
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);

				System.out.println("File \"file.xml\" saved!");
				
				
				
				
				
				
				
//				NodeList nodeList = document.getDocumentElement().getChildNodes();
//				for (int i = 0; i < nodeList.getLength(); i++) {
//					System.out.println("Item "+i+": "+nodeList.item(i));
		//
//					Node node = nodeList.item(i);
		//
////					if (node.getNodeType() == Node.ELEMENT_NODE) {
////						System.out.print("ELEM:");
////			            System.out.println(node);
////						//Element elem = (Element) node;
////						//JOptionPane.showMessageDialog(null, "node: "+ elem.toString());
////						//echo(node);
////					}
//					
//					//if (node.getNodeType() == Node.ELEMENT_NODE) {
//						System.out.print("ELEM:");
//			            //System.out.println(node);
//			            
//			            if (node.getNodeValue().equals("\n")){
//			    			System.out.println("newline");
//			    		}else{
//			    			System.out.println(node);
//			    		}
		//
//					//}
//				}// for
				
				
				
				
				
//				//NodeList nList = document.getElementsByTagName("exercise");
//				
//				System.out.println("element: "+element);
//				NodeList nodeList = document.getDocumentElement().getChildNodes();
//				for (int i = 0; i < nodeList.getLength(); i++) {
//					System.out.println("Item "+i+": "+nodeList.item(i));
		//
////					Node node = nodeList.item(i);
		////
////					if (node.getNodeType() == Node.ELEMENT_NODE) {
////						//Element elem = (Element) node;
////						//JOptionPane.showMessageDialog(null, "node: "+ elem.toString());
////						//echo(node);
////					}
//				}// for
//				// checking other Document stuff
//				Node node = nodeList.item(0);
//				System.out.println("Checking the first node...");
//				System.out.println("Local Name: "+node.getLocalName());
//				System.out.println("Node Name: "+node.getNodeName());
//				System.out.println("Node Type: "+node.getNodeType());
//				System.out.println("Node Value: \""+node.getNodeValue()+"\"");
//				NodeList nodeList1 = node.getChildNodes();
//				System.out.println("Node children: "+nodeList1.getLength());
//				
//				// ... and so on, this is just newline
//				// node i=1
//				node = nodeList.item(1);
//				System.out.println("Checking the node i=1...");
//				System.out.println("Local Name: "+node.getLocalName());
//				System.out.println("Node Name: "+node.getNodeName());
//				System.out.println("Node Type: "+node.getNodeType());
//				System.out.println("Node Value: \""+node.getNodeValue()+"\"");
//				System.out.println("Node Owner doc: "+node.getOwnerDocument());
//				System.out.println("Node Parent Node: "+node.getParentNode());
//				nodeList1 = node.getChildNodes();
//				System.out.println("Node children: "+nodeList1.getLength());
//				// loop over all children nodes
//				for (int i=0, len=nodeList1.getLength();i<len;++i){
//					
//					node = nodeList1.item(i);
//					System.out.println("---"+i);
//					System.out.println("Local Name: "+node.getLocalName());
//					System.out.println("Node Name: "+node.getNodeName());
//					System.out.println("Node Type: "+node.getNodeType());
//					System.out.println("Node Value: \""+node.getNodeValue()+"\"");
//					System.out.println("Node Owner doc: "+node.getOwnerDocument());
//					System.out.println("Node Parent Node: "+node.getParentNode());
//					
//				}
//				
//				
//				// checking null response
//				System.out.println("Checking null response...");
//				if (node.getNodeValue()==null){
//					System.out.println("Null detected, which is as it is");
//				}
//				else{
//					System.out.println("Null not, value: \""+node.getNodeValue()+"\"");
//				}
	}//end of private void docChecker()
	
	/**
	 *  Methods
	 *  echo - this method from example Oracle - testing
	 */
	private void echo(Node n) {
	    int type = n.getNodeType();

	    switch (type) {
//	        case Node.ATTRIBUTE_NODE:
//	            System.out.print("ATTR:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.CDATA_SECTION_NODE:
//	            System.out.print("CDATA:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.COMMENT_NODE:
//	            System.out.print("COMM:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.DOCUMENT_FRAGMENT_NODE:
//	            System.out.print("DOC_FRAG:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.DOCUMENT_NODE:
//	            System.out.print("DOC:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.DOCUMENT_TYPE_NODE:
//	            System.out.print("DOC_TYPE:");
//	            System.out.println(n);
////	            NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
////	            for (int i = 0; i < nodeMap.getLength(); i++) {
////	                Entity entity = (Entity)nodeMap.item(i);
////	                echo(entity);
////	            }
//	            break;

	        case Node.ELEMENT_NODE:
	            System.out.print("ELEM:");
	            System.out.println(n);

	            NamedNodeMap atts = n.getAttributes();
	            for (int i = 0; i < atts.getLength(); i++) {
	                Node att = atts.item(i);
	                echo(att);
	            }
	            break;

//	        case Node.ENTITY_NODE:
//	            System.out.print("ENT:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.ENTITY_REFERENCE_NODE:
//	            System.out.print("ENT_REF:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.NOTATION_NODE:
//	            System.out.print("NOTATION:");
//	            System.out.println(n);
//	            break;
//
//	        case Node.PROCESSING_INSTRUCTION_NODE:
//	            System.out.print("PROC_INST:");
//	            System.out.println(n);
//	            break;

	        case Node.TEXT_NODE:
	            System.out.print("TEXT:");
	            //System.out.println(n);
	            System.out.println("\""+n+"\"");
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
	}// end of echo method
	
	private void nodePrint(Node n,int indent){
		for (int i = 0; i< indent; ++i){
			System.out.print("  ");
		}
//		if (n.getNodeValue().equals("\n")){
//			System.out.println("newline");
//		}else{
			System.out.println("element: "+n);
//		}
		
		NodeList nL = n.getChildNodes();
		++indent;
		for (int i=0, len=nL.getLength();i<len;++i){
			nodePrint(nL.item(i),indent);
		}
		--indent;
	}
	// method for parsing the document, currentUser should be known when calling it
	protected void mDOMparse(DocumentBuilder builder, File runningFolder, String currentUser) throws XPathExpressionException{
		StringBuilder str2 = new StringBuilder();
		File topicsFile = null;
		
	
	//factory.setIgnoringElementContentWhitespace(true);
	//factory.setSchema(schema);
	//factory.setNamespaceAware(true);
	try {
		
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
			//JOptionPane.showMessageDialog(null, "topics.xml found!");
//		}
		//
		Document document = builder.parse(topicsFile);
		//Node root = (Node) document.getDocumentElement();
		//NodeList listOfCurrentTopics  = getNodesFrom(root, "/people/student");
		this.root = new DefaultMutableTreeNode();
        this.myModelTree = new DefaultTreeModel(root);
        //loadXMLDocument(root);
        // insert of loadXML functionality
        Node root = (Node) document.getDocumentElement();
        this.listOfCurrentTopics = getNodesFrom(root, "/topics/topic");
        
        
        this.treeOfUser = new JTree(this.myModelTree);
        this.treeOfUser.setRootVisible(false);
        this.treeOfUser.setShowsRootHandles(true);
        this.treeOfUser.expandPath(new TreePath(root));
		
		
		
		
		
		
		
		
		
		//JOptionPane.showMessageDialog(null, "Still ok!");
		//////////////////////////////////////////////////////////
		//((DocumentBuilder) document).parse(topicsFile);
		// now passing 
		this.document = document;
		//this.docChecker(document);
		// launching method that runs workout exercises
		this.workout();
			
		
		
		
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	//	return;
	}// end method for parsing
	
	private void workout() {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "starting workout... currentUser: "+this.currentUser);	
		//testing
		this.docChecker(document);
		//this.textSelection.append("...addition");
		//this.appendTextSelection("...append test");
		
		
		
		
		//rootNode = new DefaultMutableTreeNode("Root Node");
		//this.modelTree = new DefaultTreeModel(rootNode);
		//treeModel.addTreeModelListener(new MyTreeModelListener());
		
		
		//this.modelTree = new DomToTreeModelAdapter();
		
		//this.treeOfUser.setModel(this.modelTree);
		JOptionPane.showMessageDialog(null, "we have new JTree.");	
		
		
		
	}
	
	/**
	 *  SETTERS !!!!!!!!!!!!!!!
	 */
	protected Node getNodeFrom(Node node, String query) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression xExpress = xPath.compile(query);
        return (Node)xExpress.evaluate(node, XPathConstants.NODE);

    }

    protected NodeList getNodesFrom(Node node, String query) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression xExpress = xPath.compile(query);
        return (NodeList)xExpress.evaluate(node, XPathConstants.NODESET);

    }

    protected void loadXMLDocument(DefaultMutableTreeNode parent) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(getClass().getResourceAsStream("/People.xml"));
            Node root = (Node) doc.getDocumentElement();

            NodeList students = getNodesFrom(root, "/people/student");
            for (int index = 0; index < students.getLength(); index++) {

                Node studentNode = students.item(index);
                DefaultMutableTreeNode studentTreeNode = new DefaultMutableTreeNode("Student");
                Node name = getNodeFrom(studentNode, "name");
                Node course = getNodeFrom(studentNode, "course");
                Node semester = getNodeFrom(studentNode, "semester");
                Node scheme = getNodeFrom(studentNode, "scheme");

                studentTreeNode.setUserObject(name.getTextContent());
                studentTreeNode.add(new DefaultMutableTreeNode("Course: " + course.getTextContent()));
                studentTreeNode.add(new DefaultMutableTreeNode("Semester: " + semester.getTextContent()));
                studentTreeNode.add(new DefaultMutableTreeNode("Scheme: " + scheme.getTextContent()));

                parent.add(studentTreeNode);

            }

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            ex.printStackTrace();
        }

    }
	
	

	

}// end of public class JavaWorkoutX extends JFrame

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
	public OpenJWX(JavaWorkoutX_old frame, DocumentBuilder builder, File runningFolder){
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
					frame.mDOMparse(builder, runningFolder,frame.currentUser);
				} catch (XPathExpressionException e) {
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



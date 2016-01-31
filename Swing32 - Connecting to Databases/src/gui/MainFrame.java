package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame{
	
	private ToolBar toolBar;
	private FormPanel formPanel;
	private ContentPanel contentPanel;
	
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	
	private Preferences prefs;
	
	public MainFrame(){
		super("Loose Coupling Swing | Dialogs");
			
		setMinimumSize(new Dimension(500,400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		toolBar = new ToolBar();
		contentPanel = new ContentPanel();
		formPanel = new FormPanel();		
		tablePanel = new TablePanel();
		prefsDialog = new PrefsDialog(this);
		
		
		prefs = Preferences.userRoot().node("db");
		
		controller = new Controller();
		fileChooser = new JFileChooser();
		setJMenuBar(createMenuBar());
		
		tablePanel.setData(controller.getPeople());
		
		tablePanel.setPersonTableListerner(new PersonTableListener(){
			public void rowDeleted(int row){
				controller.removePerson(row);
			}
		});
		
		prefsDialog.setPrefsListener(new PrefsListener(){

			@Override
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
				
			}
			
		});
		
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		prefsDialog.setDefault(user, password, port);
		
		toolBar.attachToolBarListener(
			new ItoolBarListener(){
				public void emit(String text){
					contentPanel.appendText(text);
				}
			}
		);
		
		formPanel.setFormListener(new IFormListener(){
			public void formEventOccurred(FormEvent e){		 		
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});
		
		add(toolBar,BorderLayout.NORTH);
		//add(contentPanel,BorderLayout.CENTER);
		add(tablePanel,BorderLayout.CENTER);
		
		add(formPanel, BorderLayout.WEST);
	}
	
	private JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data..");
		JMenuItem importDataItem = new JMenuItem("Import Data..");
		JMenuItem exitItem = new JMenuItem("Exit");		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");		
		JMenuItem prefsItem = new JMenuItem("Prefences...");		
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		prefsItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				prefsDialog.setVisible(true);
			}
		});		
		
		importDataItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					//System.out.println(fileChooser.getSelectedFile());
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file" ,"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		exportDataItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					//System.out.println(fileChooser.getSelectedFile());
					try {						
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file" ,"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		showFormItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
				
			}
			
		});
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		prefsItem.setMnemonic(KeyEvent.VK_P);
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			/*	
				String text = JOptionPane.showInputDialog(MainFrame.this, 
						"Enter your username",
						" Enter Username",
						JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE);
				
				System.out.println(text);*/
				
				int action = JOptionPane.showConfirmDialog(MainFrame.this, 
						"Do you want to do it?",
						" Confirm Exit!",
						JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION){
					System.exit(0);
				}
							
			}
			
		});
		return menuBar;
	}
}

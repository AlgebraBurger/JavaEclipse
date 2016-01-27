import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame{
	
	private ToolBar toolBar;
	private FormPanel formPanel;
	private ContentPanel contentPanel;
	
	private JFileChooser fileChooser;
	
	
	public MainFrame(){
		super("Loose Coupling Swing | Part 2");
		setMinimumSize(new Dimension(500,400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		toolBar = new ToolBar();
		contentPanel = new ContentPanel();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		setJMenuBar(createMenuBar());
		
		toolBar.attachToolBarListener(
			new ItoolBarListener(){
				public void emit(String text){
					contentPanel.appendText(text);
				}
			}
		);
		
		formPanel.setFormListener(new IFormListener(){
			public void formEventOccurred(FormEvent e){
				String name = e.getName();
				String occupation = e.getOccupation();	
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmploymentCategory();
				String taxId = e.getTaxId();
				contentPanel.appendText(name + ": " + occupation + ": " + ageCat + ", " + empCat + ": " + taxId + " : Gender:" + e.getGender() + " \n");
			}
		});
		
		add(toolBar,BorderLayout.NORTH);
		add(contentPanel,BorderLayout.CENTER);
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
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		
		importDataItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println(fileChooser.getSelectedFile());
				}
			}
			
		});
		
		exportDataItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println(fileChooser.getSelectedFile());
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
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
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

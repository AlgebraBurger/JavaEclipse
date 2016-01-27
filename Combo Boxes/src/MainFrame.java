import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	private ToolBar toolBar;
	private FormPanel formPanel;
	private ContentPanel contentPanel;
	public MainFrame(){
		super("Loose Coupling Swing | Part 2");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		toolBar = new ToolBar();
		contentPanel = new ContentPanel();
		formPanel = new FormPanel();
		contentPanel.appendText("Test!!!");
		
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
				contentPanel.appendText(name + ": " + occupation + ": " + ageCat + ", " + empCat + " \n");
			}
		});
		
		add(toolBar,BorderLayout.NORTH);
		add(contentPanel,BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
	}
}

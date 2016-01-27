import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel;
	private Toolbar toolbar;
	private NukePanel nukepanel;
	private AttackPanel attackPanel;
	private PowerPanel powerPanel;
	public MainFrame(){
		super("Loose Coupling Swing");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		
		// set the components
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		nukepanel = new NukePanel();
		attackPanel = new AttackPanel();
		powerPanel = new PowerPanel();
		
		toolbar.setStringListener(new StringListener(){

			@Override
			public void textEmitted(String text) {
				textPanel.appendText(text);				
			}
			
		});
		
		nukepanel.setNukeHandler(new NukeHandler(){

			@Override
			public void commandEmitted(String text) {
				System.out.println(text);				
			}
			
		});
		
		attackPanel.setAttachHandler(new AttackHandler(){
			public void attackEmitted(String text){
				System.out.println(text);
			}
		});
		
		powerPanel.attachEventListener(new PowerListener(){
			public void emit(String text){
				textPanel.appendText(text);
			}
		});
		
		add(toolbar,BorderLayout.NORTH);
		add(textPanel,BorderLayout.CENTER);
		add(nukepanel,BorderLayout.SOUTH);
		add(attackPanel,BorderLayout.EAST);
		add(powerPanel,BorderLayout.WEST);
	}
}

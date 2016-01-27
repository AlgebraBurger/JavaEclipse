import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame{
	
	private TextPanel textPanel;
	//private JTextArea textArea;
	
	private JButton btn;
	private Toolbar toolbar;
	
	public MainFrame(){
		super("Hello World");
		
		setLayout(new BorderLayout());
		//textArea = new JTextArea();
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		
		btn = new JButton("Click Me");
		
		toolbar.setTextPanel(textPanel);
		
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//textArea.append("Hello\n");
				textPanel.appendText("Hello \n");
			}			
		});
		
		//add(textArea, BorderLayout.CENTER);		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

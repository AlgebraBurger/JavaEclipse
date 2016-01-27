import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ContentPanel extends JPanel {
	private JTextArea textArea;
	public ContentPanel(){
		setLayout(new BorderLayout());
		textArea = new JTextArea();		
		add(textArea,BorderLayout.CENTER);
	}
	
	public void appendText(String text){
		
		
		textArea.append(text);
	}
}

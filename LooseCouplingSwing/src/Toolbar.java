import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{
	
	private JButton helloButton;
	private JButton goodbyeButton;
	
	private StringListener textListener;
	
	public Toolbar(){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		helloButton = new JButton("Hello!");
		goodbyeButton = new JButton("Good Bye!");
		
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);
		
		add(helloButton);
		add(goodbyeButton);
		
	}
	
	public void setStringListener(StringListener listener){
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if(clicked == helloButton){
			if(textListener!=null){
				textListener.textEmitted("Hello\n");
			}
		}
		else if(clicked == goodbyeButton){
			if(textListener!=null){
				textListener.textEmitted("Goodbye\n");
			}
		}
	}
}

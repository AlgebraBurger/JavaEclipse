import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class NukePanel extends JPanel implements ActionListener{
	
	private JButton nukebutton;
	
	private NukeHandler nukelistener;
	
	public NukePanel(){
		setLayout(new BorderLayout());		
		nukebutton = new JButton("Nuke");
		nukebutton.addActionListener(this);
		add(nukebutton,BorderLayout.CENTER);		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		nukelistener.commandEmitted("Fire!!!");
		
	}

	public void setNukeHandler(NukeHandler nukelistener) {
		this.nukelistener = nukelistener;
		
	}
}

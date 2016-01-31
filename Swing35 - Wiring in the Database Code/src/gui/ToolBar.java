package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener{
	
	private JButton fireButton;
	
	private JButton saveButton;
	private JButton refreshButton;
	
	private ToolbarListener toolBarListener;
	
	
	public ToolBar(){
		
		setBorder(BorderFactory.createEtchedBorder());
		
		setLayout(new BorderLayout());
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(saveButton);
		add(refreshButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == saveButton){
			if(toolBarListener!=null){
				toolBarListener.saveEventOccured();
			}
		}else if(clicked == refreshButton){
			if(toolBarListener!=null){
				toolBarListener.refreshEventOccured();
			}
		}
		
		//itoolBarListener.emit("Fire!!!\n");
	}

	public void attachToolBarListener(ToolbarListener toolBarListener) {
		this.toolBarListener = toolBarListener;		
	}
}

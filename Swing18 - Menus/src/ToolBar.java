import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener{
	
	private JButton fireButton;
	private ItoolBarListener itoolBarListener;
	public ToolBar(){
		
		setBorder(BorderFactory.createEtchedBorder());
		
		setLayout(new BorderLayout());
		fireButton = new JButton("Fire");
		fireButton.addActionListener(this);
		add(fireButton,BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		itoolBarListener.emit("Fire!!!\n");
	}

	public void attachToolBarListener(ItoolBarListener itoolBarListener) {
		this.itoolBarListener = itoolBarListener;		
	}
}

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AttackPanel extends JPanel implements ActionListener{
	private JButton attackButton;
	private AttackHandler attackHandler;
	public AttackPanel(){
		setLayout(new BorderLayout());
		
		attackButton = new JButton("Attack");
		attackButton.addActionListener(this);
		add(attackButton,BorderLayout.CENTER);
		
	}
	public void setAttachHandler(AttackHandler attackHandler) {
		this.attackHandler = attackHandler;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		attackHandler.attackEmitted("Attack!!!");
	}
}

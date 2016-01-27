import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PowerPanel extends JPanel implements ActionListener{
	
	private JButton powerButton;
	private PowerListener powerListener;
	public PowerPanel(){
		
		setLayout(new BorderLayout());
		powerButton = new JButton("Power Button");
		powerButton.addActionListener(this);
		add(powerButton,BorderLayout.CENTER);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		powerListener.emit("Power Power!!!\n");
	}
	public void attachEventListener(PowerListener powerListener) {
		this.powerListener = powerListener;
	}
}

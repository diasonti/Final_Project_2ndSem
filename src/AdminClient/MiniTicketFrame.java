package AdminClient;

import common.Ticket;
import common.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir Danilov on 17/05/2017 : 00:50.
 */
public class MiniTicketFrame extends MiniFrame {
	
	private JLabel nameLabel, surnamelLabel, passLabel, esLabel;
	private JTextField name;
	private JTextField surname;
	private JTextField pass;
	private JButton back, submit;
	private JFrame current;
	
	int ready = 0;
	
	MiniTicketFrame(Ticket edit){
		init();
		name.setText(edit.getName());
		surname.setText(edit.getSurname());
		pass.setText(edit.getPassportNo());
		submit.addActionListener((ActionEvent ae) -> {
			ready = 1;
			Ticket newone = new Ticket(name.getText(), surname.getText(), pass.getText(), edit.getClassToString().equals("business"), edit.getFlight());
			newone.setId(edit.getId());
			AdminApp.editData("ticket", new Data[]{edit, newone});
			current.setVisible(false);
			current.dispose();
		});
	}
	
	
	private void init(){
		current = this;
		setBounds(400,220,450,370);
		
		nameLabel = new JLabel("Name:");
		surnamelLabel = new JLabel("Model:");
		passLabel = new JLabel("Business Seats:");
		esLabel = new JLabel("Economy Seats:");
		nameLabel.setBounds(15, 30, 100, 20);
		surnamelLabel.setBounds(15, 95, 100, 20);
		passLabel.setBounds(15, 160,100,20);
		esLabel.setBounds(15, 225,100,20);
		
		name = new JTextField();
		surname = new JTextField();
		pass = new JTextField();
		economySeats = new JTextField();
		name.setBounds(105, nameLabel.getY() - 12,315,45);
		surname.setBounds(105, surnamelLabel.getY() - 12,315,45);
		pass.setBounds(105, passLabel.getY() - 12,315,45);
		economySeats.setBounds(105, esLabel.getY() - 12,315,45);
		
		back = new JButton("Back");
		submit = new JButton("Submit");
		back.setBounds(20,290,150,50);
		submit.setBounds(270,290,150,50);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ready = -1;
				current.setVisible(false);
				current.dispose();
			}
		});
		
		add(nameLabel);
		add(surnamelLabel);
		add(passLabel);
		add(esLabel);
		add(name);
		add(surname);
		add(pass);
		add(economySeats);
		add(back);
		add(submit);
		
		setVisible(true);
	}
	
}

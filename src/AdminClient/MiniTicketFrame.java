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
	
	private JLabel nameLabel, surnamelLabel, passLabel;
	private JTextField name;
	private JTextField surname;
	private JTextField pass;
	private JButton back, submit, delete;
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
			AdminApp.editData("ticket", new Data[]{newone});
			current.setVisible(false);
			current.dispose();
		});
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminApp.editData("deleteTicket", new Data[]{edit});
				current.setVisible(false);
				current.dispose();
			}
		});
	}
	
	
	private void init(){
		current = this;
		setBounds(400,220,450,370);
		
		nameLabel = new JLabel("Name:");
		surnamelLabel = new JLabel("Surname:");
		passLabel = new JLabel("PassportNo:");
		nameLabel.setBounds(15, 30, 100, 20);
		surnamelLabel.setBounds(15, 95, 100, 20);
		passLabel.setBounds(15, 160,100,20);
		
		name = new JTextField();
		surname = new JTextField();
		pass = new JTextField();
		name.setBounds(105, nameLabel.getY() - 12,315,45);
		surname.setBounds(105, surnamelLabel.getY() - 12,315,45);
		pass.setBounds(105, passLabel.getY() - 12,315,45);
		
		back = new JButton("Back");
		submit = new JButton("Submit");
		delete = new JButton("X");
		back.setBounds(20,290,150,50);
		delete.setBounds(195, 290, 50, 50);
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
		add(name);
		add(surname);
		add(pass);
		add(back);
		add(delete);
		add(submit);
		
		setVisible(true);
	}
	
}

package CashierClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir Danilov on 10/05/2017 : 00:26.
 */
class PassengerDataFrame extends JFrame {
	
	private JLabel nameLabel, surnameLabel, passLabel, number;
	private JTextField name, surname, pass;
	private JButton back, submit;
	private JFrame current;
	
	int ready = 0;
	
	PassengerDataFrame(){
	
		current = this;
		setUndecorated(true);
		setLayout(null);
		//setSize(450, 300);
		setBounds(500,500,450,300);
		
		number = new JLabel("Passenger ");
		nameLabel = new JLabel("First name:");
		surnameLabel = new JLabel("Last name:");
		passLabel = new JLabel("Passport No:");
		number.setBounds(12, 5, 100, 20);
		nameLabel.setBounds(15, 30, 100, 20);
		surnameLabel.setBounds(15, 95, 100, 20);
		passLabel.setBounds(15, 160,100,20);
		
		name = new JTextField();
		surname = new JTextField();
		pass = new JTextField();
		name.setBounds(105, nameLabel.getY() - 12,315,45);
		surname.setBounds(105, surnameLabel.getY() - 12,315,45);
		pass.setBounds(105, passLabel.getY() - 12,315,45);
		
		back = new JButton("Back");
		submit = new JButton("Submit");
		back.setBounds(20,220,150,50);
		submit.setBounds(270,220,150,50);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ready = -1;
				current.setVisible(false);
			}
		});
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ready = 1;
			}
		});
		
		
		add(number);
		add(nameLabel);
		add(surnameLabel);
		add(passLabel);
		add(name);
		add(surname);
		add(pass);
		add(back);
		add(submit);
		
		setVisible(true);
	}
	
	String getPassengersName(){
		return name.getText();
	}
	String getPassengersSurname(){
		return surname.getText();
	}
	String getPassengersPassNo(){
		return pass.getText();
	}
	
	void setCount(int n){
		number.setText("Passenger " + n);
	}
	
	void reset(){
		ready = 0;
		name.setText("");
		surname.setText("");
		pass.setText("");
	}
	
}

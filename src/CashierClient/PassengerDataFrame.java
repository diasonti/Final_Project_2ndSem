package CashierClient;

import common.Packet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir Danilov on 10/05/2017 : 00:26.
 */
class PassengerDataFrame extends JFrame {
	
	private JLabel nameLabel, surnameLabel, passLabel, number;
	private JTextField name, surname, pass;
	private JButton back, submit;
	private PassengerDataFrame current;
	private int ticketNo;
	private int tickets;
	private List<String> names, surnames, passes;
	int ready = 0;
	
	PassengerDataFrame(int amount){
		ticketNo = 1;
		tickets = amount;
			names = new ArrayList<String>();
			surnames = new ArrayList<String>();
			passes = new ArrayList<String>();
		current = this;
		setUndecorated(true);
		setLayout(null);
		//setSize(450, 300);
		setBounds(500,500,450,300);
		
		number = new JLabel("Passenger " + ticketNo);
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
				current.getNames().add(name.getText());
				current.getSurnames().add(surname.getText());
				current.getPasses().add(pass.getText());
				if(ticketNo < tickets){
					setCount(ticketNo + 1);
					reset();
				}else{
					String[] names, surnames, passes;
					names = new String[tickets];
					surnames = new String[tickets];
					passes = new String[tickets];
					for(int i = 0; i < tickets; i++){
						names[i] = current.getNames().get(i);
						surnames[i] = current.getSurnames().get(i);
						passes[i] = current.getPasses().get(i);
					}
					int response = App.buyTickets(names, surnames, passes, Frame.selectedFlight, Frame.businessClass);
					if(response == 0){
						JOptionPane.showMessageDialog(App.frame, "Success");
					}else{
						JOptionPane.showMessageDialog(App.frame, "Error. Code:" + response);
						return;
					}
					current.setVisible(false);
					current.dispose();
				}
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
		ticketNo = n;
		number.setText("Passenger " + ticketNo);
	}
	
	public List<String> getNames() {
		return names;
	}
	
	public List<String> getSurnames() {
		return surnames;
	}
	
	public List<String> getPasses() {
		return passes;
	}
	
	void reset(){
		ready = 0;
		name.setText("");
		surname.setText("");
		pass.setText("");
	}
	
}

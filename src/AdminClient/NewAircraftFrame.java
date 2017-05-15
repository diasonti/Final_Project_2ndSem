package AdminClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir Danilov on 15/05/2017 : 11:18.
 */
public class NewAircraftFrame extends JFrame {
	
	private JLabel nameLabel, modelLabel, bsLabel, esLabel;
	private JTextField name, model, businessSeats, economySeats;
	private JButton back, submit;
	private JFrame current;
	
	int ready = 0;
	
	NewAircraftFrame(){
		
		current = this;
		setUndecorated(true);
		setLayout(null);
		setBounds(400,220,450,370);
		
		nameLabel = new JLabel("Name:");
		modelLabel = new JLabel("Model:");
		bsLabel = new JLabel("Business Seats:");
		esLabel = new JLabel("Economy Seats:");
		nameLabel.setBounds(15, 30, 100, 20);
		modelLabel.setBounds(15, 95, 100, 20);
		bsLabel.setBounds(15, 160,100,20);
		esLabel.setBounds(15, 225,100,20);
		
		name = new JTextField();
		model = new JTextField();
		businessSeats = new JTextField();
		economySeats = new JTextField();
		name.setBounds(105, nameLabel.getY() - 12,315,45);
		model.setBounds(105, modelLabel.getY() - 12,315,45);
		businessSeats.setBounds(105, bsLabel.getY() - 12,315,45);
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
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ready = 1;
				AdminApp.newAircraft(getAircraftsName(), getAircraftsModel(), getAircraftsBS(), getAircraftsES());
				current.setVisible(false);
				current.dispose();
			}
		});
		
		
		add(nameLabel);
		add(modelLabel);
		add(bsLabel);
		add(esLabel);
		add(name);
		add(model);
		add(businessSeats);
		add(economySeats);
		add(back);
		add(submit);
		
		setVisible(true);
	}
	
	String getAircraftsName(){
		return name.getText();
	}
	String getAircraftsModel(){
		return model.getText();
	}
	int getAircraftsBS(){
		int i = 0;
		try {
			i = Integer.parseInt(businessSeats.getText());
		}catch(Exception e){
			e.printStackTrace();
			i = -1;
		}finally {
			return i;
		}
	}
	int getAircraftsES(){
		int i = 0;
		try {
			i = Integer.parseInt(economySeats.getText());
		}catch(Exception e){
			e.printStackTrace();
			i = -1;
		}finally {
			return i;
		}
	}
	
	void reset(){
		ready = 0;
		name.setText("");
		model.setText("");
		businessSeats.setText("");
		economySeats.setText("");
	}
	
}

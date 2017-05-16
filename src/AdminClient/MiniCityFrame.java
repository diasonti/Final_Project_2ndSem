package AdminClient;

import common.City;
import common.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir Danilov on 17/05/2017 : 00:50.
 */
public class MiniCityFrame extends MiniFrame {
	
	private JLabel nameLabel;
	private JLabel countryLabel;
	private JLabel codeLabel;
	private JTextField name;
	private JTextField country;
	private JTextField code;
	private JButton back, submit;
	private JFrame current;
	
	int ready = 0;
	
	MiniCityFrame(){
		init();
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ready = 1;
				AdminApp.newCity(name.getText(), country.getText(), code.getText());
				current.setVisible(false);
				current.dispose();
			}
		});
	} // New City
	MiniCityFrame(City edit){
		init();
		name.setText(edit.getName());
		country.setText(edit.getCountry());
		code.setText(edit.getCode());
		submit.addActionListener((ActionEvent ae) -> {
			ready = 1;
			City newone = new City(name.getText(), country.getText(), edit.getCode());
			AdminApp.editData("city", new Data[]{edit, newone});
			current.setVisible(false);
			current.dispose();
		});
	}
	
	
	private void init(){
		current = this;
		setBounds(400,220,450,355);
		
		nameLabel = new JLabel("Name:");
		countryLabel = new JLabel("Country:");
		codeLabel = new JLabel("ICAO code:");
		nameLabel.setBounds(15, 35, 100, 20);
		countryLabel.setBounds(15, 100, 100, 20);
		codeLabel.setBounds(15, 165,100,20);
		
		name = new JTextField();
		country = new JTextField();
		code = new JTextField();
		name.setBounds(105, nameLabel.getY() - 12,315,45);
		country.setBounds(105, countryLabel.getY() - 12,315,45);
		code.setBounds(105, codeLabel.getY() - 12,315,45);
		
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
		add(countryLabel);
		add(codeLabel);
		add(name);
		add(country);
		add(code);
		add(back);
		add(submit);
		
		setVisible(true);
	}
	
	String getCityName(){
		return name.getText();
	}
	String getCityCountry(){
		return country.getText();
	}
	String getCityCode(){
		return code.getText();
	}
	
	void reset(){
		ready = 0;
		name.setText("");
		country.setText("");
		code.setText("");
	}
	
}

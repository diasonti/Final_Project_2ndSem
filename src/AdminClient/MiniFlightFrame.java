package AdminClient;

import common.Aircraft;
import common.City;
import common.Flight;
import common.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir Danilov on 17/05/2017 : 00:49.
 */
public class MiniFlightFrame extends MiniFrame {
	
	private JLabel fromLabel, toLabel, aircraftLabel, epriceLabel, bpriceLabel, dateLabel;
	private JComboBox from, to, aircraft;
	private JTextField eprice;
	private JTextField bprice;
	private JTextField date;
	private JButton back, submit;
	private JFrame current;
	
	private City[] listOfCities;
	
	int ready = 0;
	
	MiniFlightFrame(){
		init();
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ready = 1;
				AdminApp.newFlight((Aircraft) aircraft.getSelectedItem(), (City)from.getSelectedItem(), (City)to.getSelectedItem(), Integer.parseInt(eprice.getText()), Integer.parseInt(bprice.getText()), date.getText());
				current.setVisible(false);
				current.dispose();
			}
		});
	} // New Flight
	MiniFlightFrame(Flight edit){
		init();
		for(int i = 1; i < from.getItemCount(); i++){
			if(from.getItemAt(i).equals(edit.getDeparture())){
				from.setSelectedIndex(i);
				break;
			}
		}
		for(int i = 1; i < to.getItemCount(); i++){
			if(to.getItemAt(i).equals(edit.getArrival())){
				to.setSelectedIndex(i);
				break;
			}
		}
		for(int i = 1; i < aircraft.getItemCount(); i++){
			if(aircraft.getItemAt(i).equals(edit.getAircraft())){
				aircraft.setSelectedIndex(i);
				break;
			}
		}
		eprice.setText(edit.getEconomyPrice() + "");
		bprice.setText(edit.getBusinessPrice() + "");
		date.setText(edit.getDate());
		submit.addActionListener((ActionEvent ae) -> {
			ready = 1;
			Flight newone = new Flight((Aircraft) aircraft.getSelectedItem(), (City)from.getSelectedItem(), (City)to.getSelectedItem(), Integer.parseInt(eprice.getText()), Integer.parseInt(bprice.getText()), date.getText());
			newone.setId(edit.getId());
			AdminApp.editData("flight", new Data[]{newone});
			current.setVisible(false);
			current.dispose();
		});
	}
	
	
	private void init(){
		current = this;
		setBounds(400,220,450,500);
		
		fromLabel = new JLabel("From:");
		toLabel = new JLabel("To:");
		aircraftLabel = new JLabel("Aircraft:");
		epriceLabel = new JLabel("Economy price:");
		bpriceLabel = new JLabel("Business price:");
		dateLabel = new JLabel("Date:");
		fromLabel.setBounds(15, 30, 100, 20);
		toLabel.setBounds(15, 95, 100, 20);
		aircraftLabel.setBounds(15, 160,100,20);
		epriceLabel.setBounds(15, 225,100,20);
		bpriceLabel.setBounds(15, 290,100,20);
		dateLabel.setBounds(15, 355,100,20);
		
		
		
		from = new JComboBox();
		to = new JComboBox();
		aircraft = new JComboBox();
		from.setBounds(105, fromLabel.getY() - 12,315,45);
		to.setBounds(105, toLabel.getY() - 12,315,45);
		aircraft.setBounds(105, aircraftLabel.getY() - 12,315,45);
		
		
		
		listOfCities = AdminApp.getAllCities();
		from.addItem(null);
		to.addItem(null);
		for(City c : listOfCities){
			from.addItem(c);
			to.addItem(c);
		}
		
		Aircraft[] listOfAircraft = AdminApp.getAllAircraft();
		aircraft.addItem(null);
		for(Aircraft a : listOfAircraft){
			aircraft.addItem(a);
		}
		
		from.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(from.getSelectedIndex() == 0){
					submit.setEnabled(false);
				}else{
					submit.setEnabled(true);
				}
				if(to.getSelectedIndex() == from.getSelectedIndex()){
					to.setSelectedItem(null);
				}
			}
		});
		to.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(to.getSelectedIndex() == 0){
					submit.setEnabled(false);
				}else{
					submit.setEnabled(true);
				}
				if(to.getSelectedIndex() == from.getSelectedIndex()){
					from.setSelectedItem(null);
				}
			}
		});
		
		eprice = new JTextField();
		bprice = new JTextField();
		date = new JTextField();
		eprice = new JTextField();
		eprice.setBounds(105, epriceLabel.getY() - 12,315,45);
		bprice.setBounds(105, bpriceLabel.getY() - 12,315,45);
		date.setBounds(105, dateLabel.getY() - 12,315,45);
		
		back = new JButton("Back");
		submit = new JButton("Submit");
		back.setBounds(20,440,150,50);
		submit.setBounds(270,440,150,50);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ready = -1;
				current.setVisible(false);
				current.dispose();
			}
		});
		
		add(fromLabel);
		add(toLabel);
		add(aircraftLabel);
		add(epriceLabel);
		add(bpriceLabel);
		add(dateLabel);
		add(from);
		add(to);
		add(aircraft);
		add(eprice);
		add(bprice);
		add(date);
		add(eprice);
		add(back);
		add(submit);
		
		setVisible(true);
	}
	
}

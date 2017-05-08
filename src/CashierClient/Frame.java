package CashierClient;

import common.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir on 05.05.2017.
 */
class Frame extends JFrame {
    
    private JTabbedPane tabs;
    private JPanel ticket, flightsList;
    
    private JLabel title, departure, arrival, classLabel, passengers;
    private City[] cities;
    private JComboBox from, to;
	private ButtonGroup flightClass;
	private JRadioButton economy, business;
    private JTextField who;
    private JScrollPane foundTableContainer;
    private JTable flights;
	private String[] foundFlightsHeader = {"Aircraft", "Business price", "Economy price"};
	private Flight[] foundFlights = new Flight[0];
	private Object[][] foundFlightsTableData = new Object[0][0];
    private JButton buy, find, reset;
	
	private Flight selectedFlight;
    private boolean businessClass;
    private int number;
	
    private String[] allFlightsHeader = {"Date", "From", "To", "Aircraft", "Business Price", "Economy Price", "Business available", "Economy available"};
	private JScrollPane allFlightsTableContainer;
	private JTable allFlights;
    private JButton refreshTable;
	
    Frame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setResizable(false);
        setTitle("Cashier");
        
        tabs = new JTabbedPane();
        tabs.setBounds(0,0, getWidth(), getHeight());
       
        
        //Elements' dimensions
        int horizontalIndent1 = 40;
        int horizontalIndent2 = 370;
        int verticalIndent1 = 80;
        int verticalIndent2 = 50;
        int comboBoxWidth = 255;
        int comboBoxHeight = 45;
        int textFieldWidth = 230;
        int textFieldHeight = 45;
        int foundTableWidth = getWidth() - 50;
        int foundTableHeight = 300;
        
        //Find flight panel
        ticket = new JPanel();
        ticket.setLayout(null);
        
        //Labels
        title = new JLabel("Find flights");
        departure = new JLabel("From:");
        arrival = new JLabel("To:");
        classLabel = new JLabel("Class:");
        passengers = new JLabel("N of passengers:");
    
        title.setBounds(350, 20, 80, 15);
        departure.setBounds(horizontalIndent1, verticalIndent1, 80, 15);
        arrival.setBounds(horizontalIndent1 + horizontalIndent2, verticalIndent1, 80, 15);
        classLabel.setBounds(horizontalIndent1, verticalIndent1 + verticalIndent2 * 2, 80, 15);
        passengers.setBounds(horizontalIndent1 + horizontalIndent2, verticalIndent1 + verticalIndent2 * 2, 100, 15);
        
        //ComboBoxes
		cities = App.getAllCities();
        from = new JComboBox(cities);
        to = new JComboBox(cities);
		
        
        from.addItemListener(new ItemChangeListener());
        to.addItemListener(new ItemChangeListener());
        
        from.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(to.getSelectedIndex() == from.getSelectedIndex()){
					to.setSelectedItem(null);
				}
			}
		});
		to.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(to.getSelectedIndex() == from.getSelectedIndex()){
					from.setSelectedItem(null);
				}
			}
		});
    
        from.setBounds(horizontalIndent1 + departure.getWidth(), verticalIndent1 - 15, comboBoxWidth, comboBoxHeight);
        to.setBounds(horizontalIndent1 + horizontalIndent2 + passengers.getWidth() + 15, verticalIndent1 - 15, comboBoxWidth, comboBoxHeight);
		
        
        //Class RadioButtons
        flightClass = new ButtonGroup();
        economy = new JRadioButton("Economy");
        business = new JRadioButton("Business");
        
        economy.setBounds(horizontalIndent1 + classLabel.getWidth(), verticalIndent1 + verticalIndent2 * 2 - 15, textFieldWidth, 15);
		business.setBounds(horizontalIndent1 + classLabel.getWidth(), verticalIndent1 + verticalIndent2 * 2 + 15, textFieldWidth, 15);
        
        flightClass.add(economy);
        flightClass.add(business);
		
        
        //TextFields
        who = new JTextField();
		
        who.setBounds(horizontalIndent1 + horizontalIndent2 + passengers.getWidth() + 15, verticalIndent1 + verticalIndent2 * 2 - 17, textFieldWidth, textFieldHeight);
        
        //Found Flights Table
        flights = new JTable(foundFlightsTableData, foundFlightsHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
        flights.setRowHeight(40);
        foundTableContainer = new JScrollPane(flights);
        foundTableContainer.setBounds(horizontalIndent1 - 17, verticalIndent1 + verticalIndent2 * 3, foundTableWidth, foundTableHeight);
        
        
        //Buttons
		buy = new JButton("Buy tickets");
		buy.setEnabled(false);
		reset = new JButton("Reset");
		find = new JButton("Find flights");
	
		buy.setBounds(horizontalIndent1 + 500, verticalIndent1 + verticalIndent2 * 10, textFieldWidth, textFieldHeight);
		reset.setBounds(horizontalIndent1, verticalIndent1 + verticalIndent2 * 10, textFieldWidth, textFieldHeight);
		find.setBounds(horizontalIndent1 + reset.getWidth() + 20, verticalIndent1 + verticalIndent2 * 10, textFieldWidth, textFieldHeight);
  
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetFields();
			}
		});
		find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(from.getSelectedItem() != null && to.getSelectedItem() != null && (economy.isSelected() || business.isSelected()) && who.getText() != ""){
					try{
						int passengers = Integer.parseInt(who.getText());
						foundFlights = App.findFlights((City)from.getSelectedItem(), (City)to.getSelectedItem(), business.isSelected(), passengers);
						
						foundFlightsTableData = new Object[foundFlights.length][3];
						for(int i = 0; i < foundFlights.length; i++){
							foundFlightsTableData[i][0] = foundFlights[i].getAircraft();
							foundFlightsTableData[i][1] = foundFlights[i].getBusinessPrice();
							foundFlightsTableData[i][2] = foundFlights[i].getEconomyPrice();
						}
						flights = new JTable(foundFlightsTableData, foundFlightsHeader){
							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						flights.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						flights.setColumnSelectionAllowed(false);
						flights.setRowHeight(40);
						
						flights.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								selectedFlight = foundFlights[flights.getSelectedRow()];
								businessClass = business.isSelected();
								number = Integer.parseInt(who.getText());
								buy.setEnabled(true);
							}
						});
						
						foundTableContainer.setViewportView(flights);
					}catch(NumberFormatException exception){
						exception.printStackTrace();
					}
				}
			}
		});
		buy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = App.buyTickets(selectedFlight, businessClass, number);
				if(response == 0){
					JOptionPane.showMessageDialog(App.frame, "Success");
					resetFields();
				}else{
					JOptionPane.showMessageDialog(App.frame, "Error. Code:" + response);
				}
			}
		});
        
        ticket.add(title);
        ticket.add(departure);
        ticket.add(arrival);
        ticket.add(classLabel);
        ticket.add(passengers);
        ticket.add(from);
        ticket.add(to);
        ticket.add(economy);
        ticket.add(business);
        ticket.add(who);
        ticket.add(foundTableContainer);
        ticket.add(buy);
        ticket.add(reset);
        ticket.add(find);
        
        
        //Display all flights panel
        flightsList = new JPanel();
        flightsList.setLayout(null);
        allFlights = new JTable(new Object[0][8], allFlightsHeader){
        	@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		allFlightsTableContainer = new JScrollPane(allFlights);
		allFlightsTableContainer.setBounds(0, 0, getWidth(), getHeight() - 120);
	
		refreshTable = new JButton("Refresh table");
		refreshTable.setBounds(getWidth()/2 - 100, getHeight() - 118, 200, 60);
		refreshTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Flight[] allFlightsList = App.getAllFlights();
				Object[][] tableData = new Object[allFlightsList.length][8];
				for(int i = 0; i < allFlightsList.length; i++){
					tableData[i][0] = allFlightsList[i].getDate();
					tableData[i][1] = allFlightsList[i].getDeparture();
					tableData[i][2] = allFlightsList[i].getArrival();
					tableData[i][3] = allFlightsList[i].getAircraft();
					tableData[i][4] = allFlightsList[i].getBusinessPrice();
					tableData[i][5] = allFlightsList[i].getEconomyPrice();
					tableData[i][6] = allFlightsList[i].getBusinessPlacesFree();
					tableData[i][7] = allFlightsList[i].getEconomyPlacesFree();
				}
				allFlights = new JTable(tableData, allFlightsHeader);
				allFlightsTableContainer.setViewportView(allFlights);
			}
		});
		
		flightsList.add(refreshTable);
        flightsList.add(allFlightsTableContainer);
        
        tabs.addTab("Find flight", ticket);
        tabs.addTab("All flights", flightsList);
        add(tabs);
        setVisible(true);
    }
    
    private void resetFields(){
    	cities = App.getAllCities();
    	from.removeAllItems();
    	to.removeAllItems();
    	from.addItem(null);
    	to.addItem(null);
    	for(City c : cities){
    		from.addItem(c);
    		to.addItem(c);
		}
    	//from.setSelectedIndex(0);
    	//to.setSelectedIndex(0);
    	flightClass.clearSelection();
    	who.setText("");
    	flights = new JTable(new Object[][]{}, foundFlightsHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		foundTableContainer.setViewportView(flights);
		buy.setEnabled(false);
	}
	
	private class ItemChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				if(buy.isEnabled()) {
					buy.setEnabled(false);
				}
				if(flights.getRowCount() > 0) {
					flights = new JTable(new Object[][]{}, foundFlightsHeader){
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					foundTableContainer.setViewportView(flights);
				}
			}
		}
	}

}

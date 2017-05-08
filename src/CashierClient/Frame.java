package CashierClient;

import common.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir on 05.05.2017.
 */
class Frame extends JFrame {
    
    private JTabbedPane tabs;
    private JPanel ticket, flightsList;
    
    private JLabel title, departure, arrival, classLabel, passengers;
    private List<City> cities = new ArrayList<City>();
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
		cities.add(null); //DEBUG //TODO Remove debug lines
		cities.add(new City("Almaty", "Kazakhstan", "UAAA")); // DEBUG
		cities.add(new City("Astana", "Kazakhstan", "UACC")); // DEBUG
		cities.add(new City("London Heathrow", "United Kingdom", "EGLL")); // DEBUG
        from = new JComboBox(cities.toArray());
        to = new JComboBox(cities.toArray());
        
        from.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(to.getSelectedIndex() == from.getSelectedIndex()){
					to.setSelectedIndex(0);
				}
			}
		});
		to.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(to.getSelectedIndex() == from.getSelectedIndex()){
					from.setSelectedIndex(0);
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
        flights = new JTable(foundFlightsTableData, foundFlightsHeader);
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
						flights = new JTable(foundFlightsTableData, foundFlightsHeader);
						//flights.setCellSelectionEnabled(false);
						flights.setColumnSelectionAllowed(false);
						flights.setRowHeight(40);
						foundTableContainer.setViewportView(flights);
					}catch(NumberFormatException exception){
						exception.printStackTrace();
					}
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
        
        tabs.addTab("Find flight", ticket);
        tabs.addTab("All flights", flightsList);
        add(tabs);
        setVisible(true);
    }
    
    private void resetFields(){
    	from.setSelectedIndex(0);
    	to.setSelectedIndex(0);
    	flightClass.clearSelection();
    	who.setText("");
    	flights = new JTable(new Object[][]{}, foundFlightsHeader);
		buy.setEnabled(false);
		repaint();
	}

}

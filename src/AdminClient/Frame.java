package AdminClient;

import common.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladimir Danilov on 10/05/2017 : 01:53.
 */

class Frame extends JFrame {
	
	private JTabbedPane tabs;
	private Tab aircrafts, cities, flights, tickets;
	private Table aircraftsTable, citiesTable, flightsTable, ticketsTable;
	private JButton refresh1, refresh2, refresh3, refresh4;
	private JButton edit1, edit2, edit3, edit4;
	private JButton add1, add2, add3, add4;
	
	Frame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setResizable(false);
		setTitle("Admin");
		
		//Elements' dimensions
		int horizontalIndent1 = 20;
		int verticalIndent = 577;
		
		tabs = new JTabbedPane();
		tabs.setBounds(0, 0, getWidth(), getHeight());
		
		
		aircrafts = new Tab();
		aircraftsTable = new Table(new Object[0][0], new String[]{"Name", "Model", "Business Class Seats", "Economy Class Seats"});
		aircraftsTable.setBounds(0, 0, getWidth(), getHeight() - 130);
		aircrafts.add(aircraftsTable);
		
		refresh1 = new JButton("Refresh");
		refresh1.setBounds(horizontalIndent1, verticalIndent, 250, 60);
		refresh1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				refreshAircraft();
			}
		});
		aircrafts.add(refresh1);
		
		edit1 = new JButton("Edit");
		edit1.setBounds(horizontalIndent1 + 350, verticalIndent, 250, 60);
		//edit1.setEnabled(false);
		edit1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(aircraftsTable.getSelectedObject() != null){
					new MiniAircraftFrame((Aircraft) aircraftsTable.getSelectedObject());
				}
			}
		});
		aircrafts.add(edit1);
		
		
		add1 = new JButton("Add");
		add1.setBounds(horizontalIndent1 + 700, verticalIndent, 250, 60);
		add1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MiniAircraftFrame();
			}
		});
		aircrafts.add(add1);
		
		
		cities = new Tab();
		citiesTable = new Table(new Object[0][0], new String[]{"Name", "Country", "ICAO Airport Code"});
		citiesTable.setBounds(0, 0, getWidth(), getHeight() - 130);
		cities.add(citiesTable);
		
		refresh2 = new JButton("Refresh");
		refresh2.setBounds(horizontalIndent1, verticalIndent, 250, 60);
		refresh2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshCities();
			}
		});
		cities.add(refresh2);
		
		edit2 = new JButton("Edit");
		edit2.setBounds(horizontalIndent1 + 350, verticalIndent, 250, 60);
		//edit2.setEnabled(false);
		edit2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(citiesTable.getSelectedObject() != null) {
					new MiniCityFrame((City) citiesTable.getSelectedObject());
				}
			}
		});
		cities.add(edit2);
		
		
		add2 = new JButton("Add");
		add2.setBounds(horizontalIndent1 + 700, verticalIndent, 250, 60);
		add2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MiniCityFrame();
			}
		});
		cities.add(add2);
		
		
		flights = new Tab();
		flightsTable = new Table(new Object[0][0], new String[]{"ID", "Date", "From", "To", "Aircraft", "Economy Price", "Business Price", "Economy Free", "Business Free"});
		flightsTable.setBounds(0, 0, getWidth(), getHeight() - 130);
		flights.add(flightsTable);
		
		refresh3 = new JButton("Refresh");
		refresh3.setBounds(horizontalIndent1, verticalIndent, 250, 60);
		refresh3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshFlights();
			}
		});
		flights.add(refresh3);
		
		edit3 = new JButton("Edit");
		edit3.setBounds(horizontalIndent1 + 350, verticalIndent, 250, 60);
		//edit3.setEnabled(false);
		edit3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(flightsTable.getSelectedObject() != null) {
					new MiniFlightFrame((Flight) flightsTable.getSelectedObject());
				}
			}
		});
		flights.add(edit3);
		
		
		add3 = new JButton("Add");
		add3.setBounds(horizontalIndent1 + 700, verticalIndent, 250, 60);
		add3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MiniFlightFrame();
			}
		});
		flights.add(add3);
		
		
		tickets = new Tab();
		ticketsTable = new Table(new Object[0][0], new String[]{"Name", "Surname", "Passport", "Class", "Flight"});
		ticketsTable.setBounds(0, 0, getWidth(), getHeight() - 130);
		tickets.add(ticketsTable);
		
		refresh4 = new JButton("Refresh");
		refresh4.setBounds(horizontalIndent1, verticalIndent, 250, 60);
		refresh4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTickets();
			}
		});
		tickets.add(refresh4);
		
		edit4 = new JButton("Edit");
		edit4.setBounds(horizontalIndent1 + 350, verticalIndent, 250, 60);
		//edit4.setEnabled(false);
		edit4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ticketsTable.getSelectedObject() != null) {
					new MiniTicketFrame((Ticket) ticketsTable.getSelectedObject());
				}
			}
		});
		tickets.add(edit4);
		
		
		tabs.addTab("Aircraft", aircrafts);
		tabs.addTab("Cities", cities);
		tabs.addTab("Flights", flights);
		tabs.addTab("Tickets", tickets);
		
		add(tabs);
		setVisible(true);
		
		refreshAircraft();
		refreshCities();
		refreshFlights();
		refreshTickets();
	}
	
	
	
	private void refreshAircraft() {
		Aircraft[] aircraftList = AdminApp.getAllAircraft();
		Object[][] tableData = new Object[aircraftList.length][4];
		for(int i = 0; i < aircraftList.length; i++) {
			tableData[i][0] = aircraftList[i].getName();
			tableData[i][1] = aircraftList[i].getModel();
			tableData[i][2] = aircraftList[i].getBusinessSeats();
			tableData[i][3] = aircraftList[i].getEconomySeats();
		}
		aircraftsTable.setItems(aircraftList);
		aircraftsTable.setData(tableData);
	}
	
	private void refreshCities() {
		City[] list = AdminApp.getAllCities();
		Object[][] tableData = new Object[list.length][8];
		for(int i = 0; i < list.length; i++) {
			tableData[i][0] = list[i].getName();
			tableData[i][1] = list[i].getCountry();
			tableData[i][2] = list[i].getCode();
		}
		citiesTable.setItems(list);
		citiesTable.setData(tableData);
	}
	
	private void refreshFlights() {
		Flight[] allFlightsList = AdminApp.getAllFlights();
		Object[][] tableData = new Object[allFlightsList.length][9];
		for(int i = 0; i < allFlightsList.length; i++) {
			tableData[i][0] = allFlightsList[i].getId();
			tableData[i][1] = allFlightsList[i].getDate();
			tableData[i][2] = allFlightsList[i].getDeparture().toString();
			tableData[i][3] = allFlightsList[i].getArrival().toString();
			tableData[i][4] = allFlightsList[i].getAircraft().toString();
			tableData[i][5] = allFlightsList[i].getBusinessPrice();
			tableData[i][6] = allFlightsList[i].getEconomyPrice();
			tableData[i][7] = allFlightsList[i].getBusinessPlacesFree();
			tableData[i][8] = allFlightsList[i].getEconomyPlacesFree();
		}
		flightsTable.setItems(allFlightsList);
		flightsTable.setData(tableData);
	}
	
	private void refreshTickets() {
		Ticket[] list = AdminApp.getAllTickets();
		Object[][] tableData = new Object[list.length][8];
		for(int i = 0; i < list.length; i++) {
			tableData[i][0] = list[i].getName();
			tableData[i][1] = list[i].getSurname();
			tableData[i][2] = list[i].getPassportNo();
			tableData[i][3] = list[i].getClassToString();
			tableData[i][4] = list[i].getFlight();
		}
		ticketsTable.setItems(list);
		ticketsTable.setData(tableData);
	}
	
	
	
	
	
	private class Tab extends JPanel {
		Tab() {
			setLayout(null);
		}
	}
	private class Table extends JScrollPane {
		private JTable table;
		private String[] header;
		private Object[][] data;
		private Data[] items;
		private boolean cellsEditable = false;
		
		Table() {
			this.header = new String[0];
			this.data = new Object[0][0];
			table = new JTable(data, header);
			init();
			setViewportView(table);
		}
		
		Table(String[] header) {
			this.header = header;
			this.data = new Object[0][0];
			table = new JTable(data, header);
			init();
			setViewportView(table);
		}
		
		Table(Object[][] data, String[] header) {
			this.header = header;
			this.data = data;
			table = new JTable(this.data, this.header);
			init();
			setViewportView(table);
		}
		
		private void init() {
			table.setRowHeight(40);
			table.getTableHeader().setReorderingAllowed(false);
		}
		
		public String[] getHeader() {
			return header;
		}
		
		public void setHeader(String[] header) {
			this.header = header;
			table = new JTable(data, this.header) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return cellsEditable;
				}
			};
			init();
			setViewportView(table);
		}
		
		public Object[][] getData() {
			return data;
		}
		
		public void setData(Object[][] data) {
			this.data = data;
			table = new JTable(this.data, header) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return cellsEditable;
				}
			};
			init();
			setViewportView(table);
		}
		
		public Data[] getItems() {
			return items;
		}
		
		public void setItems(Data[] items) {
			this.items = items;
		}
		
		public void setCellsEditable(boolean editable) {
			cellsEditable = editable;
			table = new JTable(data, header) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return cellsEditable;
				}
			};
			init();
			setViewportView(table);
		}
		
		public Data getSelectedObject(){
			if(table.getSelectedRow() == -1) return null;
			return items[table.getSelectedRow()];
		}
		
	}
	
}

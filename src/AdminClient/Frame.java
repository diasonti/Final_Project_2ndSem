package AdminClient;

import common.*;

import javax.swing.*;

/**
 * Created by Vladimir Danilov on 10/05/2017 : 01:53.
 */
	
class Frame extends JFrame {
	
	private JTabbedPane tabs;
	private Tab aircrafts, cities, flights, tickets;
	private Table aircraftsTable, citiesTable, flightsTable, ticketsTable;
	private JButton refresh1, refresh2, refresh3, refresh4;
	private JButton edit1, edit2, edit3, edit4; //TODO Add 3 Buttons for each tab
	private JButton add1, add2, add3, add4;
	
	Frame(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setResizable(false);
		setTitle("Admin");
		
		tabs = new JTabbedPane();
		tabs.setBounds(0,0, getWidth(), getHeight());
		
		aircrafts = new Tab();
		aircraftsTable = new Table(new Object[0][0], new String[]{"Name", "Model", "Business Class Seats", "Economy Class Seats"});
		aircraftsTable.setBounds(0,0,getWidth(),getHeight() - 130);
		aircrafts.add(aircraftsTable);
		
		cities = new Tab();
		citiesTable = new Table(new Object[0][0], new String[]{"Name", "Country", "ICAO Airport Code"});
		citiesTable.setBounds(0,0,getWidth(),getHeight() - 130);
		cities.add(citiesTable);
		
		flights = new Tab();
		flightsTable = new Table(new Object[0][0], new String[]{"ID", "Date", "From", "To", "Aircraft","Economy Price", "Business Price", "Economy Free", "Business Free"});
		flightsTable.setBounds(0,0,getWidth(),getHeight() - 130);
		flights.add(flightsTable);
		
		tickets = new Tab();
		ticketsTable = new Table(new Object[0][0], new String[]{"Name", "Surname", "Passport", "Class", "Flight"});
		ticketsTable.setBounds(0,0,getWidth(),getHeight() - 130);
		tickets.add(ticketsTable);
		
		
		//Elements' dimensions
		int horizontalIndent1 = 100;
		int horizontalIndent2 = 600;
		int verticalIndent = 600;
		
		
		tabs.addTab("Aircraft", aircrafts);
		tabs.addTab("Cities", cities);
		tabs.addTab("Flights", flights);
		tabs.addTab("Tickets", tickets);
		
		add(tabs);
		setVisible(true);
	}
	
	private class Tab extends JPanel{
		Tab(){
			setLayout(null);
		}
	}
	private class Table extends JScrollPane{
		private JTable table;
		private String[] header;
		private Object[][] data;
		private boolean cellsEditable = true;
		Table(){
			this.header = new String[0];
			this.data = new Object[0][0];
			table = new JTable(data, header);
			init();
			setViewportView(table);
		}
		Table(String[] header){
			this.header = header;
			this.data = new Object[0][0];
			table = new JTable(data, header);
			init();
			setViewportView(table);
		}
		Table(Object[][] data, String[] header){
			this.header = header;
			this.data = data;
			table = new JTable(this.data, this.header);
			init();
			setViewportView(table);
		}
		private void init(){
			table.setRowHeight(30);
		}
		
		public void setHeader(String[] header){
			this.header = header;
			table = new JTable(data, this.header){
				@Override
				public boolean isCellEditable(int row, int column) {
					return cellsEditable;
				}
			};
			setViewportView(table);
		}
		public void setData(Object[][] data){
			this.data = data;
			table = new JTable(this.data, header){
				@Override
				public boolean isCellEditable(int row, int column) {
					return cellsEditable;
				}
			};
			setViewportView(table);
		}
		public String[] getHeader(){
			return header;
		}
		public Object[][] getData(){
			return data;
		}
		
		public void setCellsEditable(boolean editable){
			cellsEditable = editable;
			table = new JTable(data, header){
				@Override
				public boolean isCellEditable(int row, int column) {
					return cellsEditable;
				}
			};
			setViewportView(table);
		}
		
	}
	
}

package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.SwingConstants;

public class Main implements ActionListener {
	
	static JFrame frame = new JFrame("Flight Destination");
	static JLabel clockL = new JLabel();
	static Thread clockT;
	static int second;
	static int minute;
	static int hour;

	JButton addCapital = new JButton("Add Capital City");
	JButton delCapital = new JButton("Delete Capital City");
	JButton addDest = new JButton("Add Destination");
	JButton delDest = new JButton("Delete Destination");
	JButton upDest = new JButton("Upgrade Destination");
	JButton timeStart = new JButton("Start time");
	JButton timePause = new JButton("Pause Time");
	JButton timeResume = new JButton("Resume Time");
	JButton timeStop = new JButton("Stop Time");
	
	static LinkedList<CapitalCity> cities = new LinkedList<CapitalCity>();
	static LinkedList<Destination> tracks = new LinkedList<Destination>();
	static Scanner in = new Scanner(System.in);
	
	public JPanel createContentPane() {
		JPanel GUI = new JPanel();
		GUI.setLayout(null);
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/screen.png"));
		JLabel screenImage = new JLabel("", img, JLabel.CENTER);
		screenImage.setLocation(0, 0);
		screenImage.setSize(600, 337);
		GUI.add(screenImage);
		
		JLabel flight = new JLabel("Flight Destinations Management");
		flight.setForeground(Color.BLACK);
		flight.setLocation(200, 10);
		flight.setSize(300, 30);
		screenImage.add(flight);
		
		JLabel time = new JLabel("System Time");
		time.setForeground(Color.BLACK);
		time.setLocation(260, 335);
		time.setSize(200, 30);
		GUI.add(time);
		
		clockL.setForeground(Color.BLACK);
		clockL.setHorizontalAlignment(SwingConstants.CENTER);
		clockL.setLocation(216, 385);
		clockL.setSize(358, 105);
		clockL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GUI.add(clockL);
		
		addCapital.setLocation(10, 100);
		addCapital.setSize(160, 25);
		addCapital.addActionListener(this);
		screenImage.add(addCapital);
		
		delCapital.setLocation(10, 140);
		delCapital.setSize(160, 25);
		delCapital.addActionListener(this);
		screenImage.add(delCapital);
		
		addDest.setLocation(235, 100);
		addDest.setSize(160, 25);
		addDest.addActionListener(this);
		screenImage.add(addDest);
		
		delDest.setLocation(415, 100);
		delDest.setSize(160, 25);
		delDest.addActionListener(this);
		screenImage.add(delDest);
		
		upDest.setLocation(325, 140);
		upDest.setSize(160, 25);
		upDest.addActionListener(this);
		screenImage.add(upDest);
		
		timeStart.setLocation(10, 370);
		timeStart.setSize(160, 25);
		timeStart.setForeground(Color.green);
		timeStart.addActionListener(this);
		GUI.add(timeStart);
		
		timePause.setLocation(10, 410);
		timePause.setSize(160, 25);
		timePause.addActionListener(this);
		GUI.add(timePause);
		
		timeResume.setLocation(10, 450);
		timeResume.setSize(160, 25);
		timeResume.addActionListener(this);
		GUI.add(timeResume);
		
		timeStop.setLocation(10, 490);
		timeStop.setSize(160, 25);
		timeStop.setForeground(Color.red);
		timeStop.addActionListener(this);
		GUI.add(timeStop);
		
		return GUI;
	}

	public static void main(String[] args) {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		Main temp = new Main();
		frame.setContentPane(temp.createContentPane());
		frame.setSize(600, 580);
		frame.setLocation(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		CapitalCity london = new CapitalCity("London");
		CapitalCity abu = new CapitalCity("Abu Dhabi");
		CapitalCity canberra = new CapitalCity("Canberra");
		CapitalCity washington = new CapitalCity("Washington");
		CapitalCity beijing = new CapitalCity("Beijing");	
		cities.add(london);  cities.addLast(abu);  cities.addLast(canberra);  cities.addLast(washington);  cities.addLast(beijing);
		
		Destination one = new Destination("LW8","BOE777","Tuesday","10:50","16:00","British Airways");
		Destination two = new Destination("BC1","A380","Sunday","14:00","19:00","Beijing Capital Airlines");
		Destination three = new Destination("CW0","EX20","Wednesday","08:20","18:00","Fly Emirates");
		Destination four = new Destination("AL6","A350","Friday","11:00","17:45","EVA Air");
		Destination five = new Destination("CB2","BOE767","Monday","18:00","23:00","Tigerair");
		Destination six = new Destination("WB9","A320N","Thursday","12:15","17:00","Qantas");
		Destination seven = new Destination("WL5","BOE787D","Saturday","06:30","13:10","Alaska Airlines");
		Destination eight = new Destination("CA3","BOE777","Tuesday","15:35","21:00","Singapore Airlines");
		Destination nine = new Destination("LB7","EX20","Sunday","16:00","23:00","Star Alliance");
		Destination ten = new Destination("BA4","A380","Monday","11:00","17:20","Beijing Capital Airways");
		tracks.add(one);	tracks.addLast(two);	tracks.addLast(three);	tracks.addLast(four);	tracks.addLast(five);
		tracks.addLast(six);	tracks.addLast(seven);	tracks.addLast(eight);	tracks.addLast(nine);	tracks.addLast(ten);
		
	}
	
	public static void addCapital() {
		String name = JOptionPane.showInputDialog("Enter the name of capital city to add: ");	
		CapitalCity newCity = new CapitalCity(name);
		cities.addLast(newCity);
		try {
			File file = new File("capital.txt");
			FileWriter fWrite = new FileWriter(file,true);
			BufferedWriter bWrite = new BufferedWriter(fWrite);
			bWrite.write("\n" + cities.getLast().getName());
			bWrite.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File couldnt read", "File Read", JOptionPane.ERROR_MESSAGE);
		}
		String print = "";
		for(int i=0; i<cities.size(); i++) {
			print += cities.get(i).getName() +"\n";
		}
		JOptionPane.showMessageDialog(null, print, "Capital Cities", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void delCapital() {
		String name = JOptionPane.showInputDialog(null, "Enter the name of capital city to delete:");
		int i=0;
		while(i<cities.size() && cities.get(i).getName().compareToIgnoreCase(name)!=0 ) 
			i++;
		if(i == cities.size())
			JOptionPane.showMessageDialog(null, "There is no destination to this city", "Warning", JOptionPane.WARNING_MESSAGE);
		else {
			cities.remove(i);
			try {
				File file = new File("capital.txt");
				File temp = File.createTempFile("capital", ".txt", file.getParentFile());
				String charSet = "UTF-8", line;
				BufferedReader bRead = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet));
				BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp), charSet));
				while((line = bRead.readLine()) != null) {
					if(line.compareToIgnoreCase(name) == 0)
						bWrite.write("");
					else
						bWrite.write(line + "\n");
				}
				bRead.close();
				bWrite.close();
				file.delete();
				temp.renameTo(file);		
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File couldnt read", "File Read", JOptionPane.ERROR_MESSAGE);
			}
		}
		String print = "";
		for(i=0; i<cities.size(); i++) {
			print += cities.get(i).getName() +"\n";
		}
		JOptionPane.showMessageDialog(null, print, "Capital Cities", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void addDest() {
		JPanel inputs = new JPanel();
		inputs.setLayout(new GridLayout(0, 2, 2, 2));
		JTextField number = new JTextField(5);
		JTextField model = new JTextField(5);
		JTextField day = new JTextField(10);
		JTextField takingOff = new JTextField(5);
		JTextField landing = new JTextField(5);
		JTextField airlines = new JTextField(20);
		inputs.add(new JLabel("Enter the number of flight: "));
		inputs.add(number);
		inputs.add(new JLabel("Enter the model of aircraft: "));
		inputs.add(model);
		inputs.add(new JLabel("Enter the day of flight: "));
		inputs.add(day);
		inputs.add(new JLabel("Enter the taking off time: "));
		inputs.add(takingOff);
		inputs.add(new JLabel("Enter the landing time:"));
		inputs.add(landing);
		inputs.add(new JLabel("Enter the airlines name:"));
		inputs.add(airlines);
		JOptionPane.showConfirmDialog(frame, inputs, "Flight Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		Destination track = new Destination(number.getText(),model.getText(),day.getText(),takingOff.getText(),landing.getText(),airlines.getText());
		tracks.addLast(track);
		try {
			File file = new File("destination.txt");
			FileWriter fWrite = new FileWriter(file,true);
			BufferedWriter bWrite = new BufferedWriter(fWrite);
			bWrite.write("\n" + tracks.getLast().getNumber() + " - " + tracks.getLast().getModel() + " - " + tracks.getLast().getDay() + " - " + tracks.getLast().getTakingOff() + " - " + tracks.getLast().getLanding() + " - " + tracks.getLast().getAirlines());
			bWrite.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File couldnt read", "File Read", JOptionPane.ERROR_MESSAGE);
		}
		String print = "";
		for(int i=0; i<tracks.size(); i++) {
			print += tracks.get(i).getNumber() + " - " + tracks.get(i).getModel() + " - " + tracks.get(i).getDay() + " - " + tracks.get(i).getTakingOff() + " - " + tracks.get(i).getLanding() + " - " + tracks.get(i).getAirlines() + "\n";
		}
		JOptionPane.showMessageDialog(null, print, "Flights", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void delDest() {
		String number = JOptionPane.showInputDialog("Enter the number of destination to delete:");
		int i=0;
		while(i<tracks.size() && tracks.get(i).getNumber().compareToIgnoreCase(number)!=0)
			i++;
		if(i == tracks.size())
			JOptionPane.showMessageDialog(null, "There is no destination with this number", "Warning", JOptionPane.WARNING_MESSAGE);
		else {
			tracks.remove(i);
			try {
				File file = new File("destination.txt");
				File temp = File.createTempFile("destination", ".txt", file.getParentFile());
				String charSet = "UTF-8", line;
				BufferedReader bRead = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet));
				BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp), charSet));
				while((line = bRead.readLine()) != null) { 
					if(line.indexOf(number) == 0)
						bWrite.write("");
					else
						bWrite.write(line + "\n");
				}
				bRead.close();
				bWrite.close();
				file.delete();
				temp.renameTo(file);		
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File couldnt read", "File Read", JOptionPane.ERROR_MESSAGE);
			}
		}
		String print = "";
		for(i=0; i<tracks.size(); i++) {
			print += tracks.get(i).getNumber() + " - " + tracks.get(i).getModel() + " - " + tracks.get(i).getDay() + " - " + tracks.get(i).getTakingOff() + " - " + tracks.get(i).getLanding() + " - " + tracks.get(i).getAirlines() + "\n";
		}
		JOptionPane.showMessageDialog(null, print, "Flights", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void upDest() {
		String number = JOptionPane.showInputDialog("Enter the number of destination to upgrade:");
		int i=0;
		String day = null, takingOff = null, landing = null;
		while(i<tracks.size() && tracks.get(i).getNumber().compareToIgnoreCase(number)!=0)
			i++;
		if(i == tracks.size())
			JOptionPane.showMessageDialog(null, "There is no destination with this number", "Warning", JOptionPane.WARNING_MESSAGE);
		else {
			JPanel inputs = new JPanel();
			inputs.setLayout(new GridLayout(0, 2, 2, 2));
			JTextField day2 = new JTextField(5);
			JTextField takingOff2 = new JTextField(5);
			JTextField landing2 = new JTextField(10);
			inputs.add(new JLabel("Enter the new day:"));
			inputs.add(day2);
			inputs.add(new JLabel("Enter the new taking off time:"));
			inputs.add(takingOff2);
			inputs.add(new JLabel("Enter the new landing time:"));
			inputs.add(landing2);
			JOptionPane.showConfirmDialog(frame, inputs, "New Flight Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		
			day = tracks.get(i).getDay();
			tracks.get(i).setDay(day2.getText());		
			takingOff = tracks.get(i).getTakingOff();
			tracks.get(i).setTakingOff(takingOff2.getText());			
			landing = tracks.get(i).getLanding();
			tracks.get(i).setLanding(landing2.getText());
			try {
				File file = new File("destination.txt");
				File temp = File.createTempFile("destination", ".txt", file.getParentFile());
				String charSet = "UTF-8", line;
				BufferedReader bRead = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet));
				BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp), charSet));
				while((line = bRead.readLine()) != null) { 
					if(line.indexOf(number) == 0) {
						line = line.replace(day, day2.getText());
						line = line.replace(takingOff, takingOff2.getText());
						line = line.replace(landing, landing2.getText());
						bWrite.write(line + "\n");
					}
						
					else
						bWrite.write(line + "\n");
				}
				bRead.close();
				bWrite.close();
				file.delete();
				temp.renameTo(file);		
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File couldnt read", "File Read", JOptionPane.ERROR_MESSAGE);
			}
		}	
		String print = "";
		for(i=0; i<tracks.size(); i++) {
			print += tracks.get(i).getNumber() + " - " + tracks.get(i).getModel() + " - " + tracks.get(i).getDay() + " - " + tracks.get(i).getTakingOff() + " - " + tracks.get(i).getLanding() + " - " + tracks.get(i).getAirlines() + "\n";
		}
		JOptionPane.showMessageDialog(null, print, "Flights", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void timeStart() {
		JPanel inputs = new JPanel();
		inputs.setLayout(new GridLayout(0, 2, 2, 2));
		JTextField hour2 = new JTextField(10);
		JTextField minute2 = new JTextField(1);
		JTextField second2 = new JTextField(1);
		inputs.add(new JLabel("Set the hour: "));
		inputs.add(hour2);
		inputs.add(new JLabel("Set the minute: "));
		inputs.add(minute2);
		inputs.add(new JLabel("Set the second: "));
		inputs.add(second2);
		JOptionPane.showConfirmDialog(frame, inputs, "System Clock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		hour = Integer.parseInt(hour2.getText());
		minute = Integer.parseInt(minute2.getText());
		second = Integer.parseInt(second2.getText());
		clock();
		JOptionPane.showMessageDialog(null, "Time is started", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void timePause() {
		clockT.interrupt();
		JOptionPane.showMessageDialog(null, "Time is paused", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void timeResume() {
		clock();
		JOptionPane.showMessageDialog(null, "Time is resumed", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void timeStop() {
		clockT.interrupt();
		JOptionPane.showMessageDialog(null, "Time is stopped", "Information", JOptionPane.INFORMATION_MESSAGE);
		hour = 0; minute = 0; second = 0;
	}
	
	public static void clock() {
		clockT= new Thread(){
			public void run() {
				try {
					for(;;) {
						Calendar cal = new GregorianCalendar();
						int day = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH);
						int year = cal.get(Calendar.YEAR);
						String timeStr = String.format("%02d:%02d  %02d/%02d/%d", hour, minute, day, month+1, year);
						second = second + 60;
						minute = minute + second/60;
						hour = hour + minute/60;
						second = second%60;
						minute = minute%60;
						hour = hour%24;
						clockL.setText(timeStr);
						sleep(1000);
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		clockT.start();
	}
	
	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == addCapital) {
			addCapital();
		}
		else if(click.getSource() == delCapital) {
			delCapital();
		}
		else if(click.getSource() == addDest) {
			addDest();
		}
		else if(click.getSource() == delDest) {
			delDest();
		}
		else if(click.getSource() == upDest) {
			upDest();
		}
		else if(click.getSource() == timeStart) {
			timeStart();
		}
		else if(click.getSource() == timePause) {
			timePause();
		}
		else if(click.getSource() == timeResume) {
			timeResume();
		}
		else if(click.getSource() == timeStop) {
			timeStop();
		}
	}
	
}

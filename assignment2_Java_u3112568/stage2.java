// Author: 				Yulai Miao

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.JList;


public class stage2 {
	//declarations
	private JFrame frame;
	private JTextField textField_BirthDay;
	private JTextField textField_BirthMonth;
	private JTextField textField_BirthYear;
	private JTextField textField_CurrentDay;
	private JTextField textField_CurrentMonth;
	private JTextField textField_CurrentYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stage2 window = new stage2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public stage2() {
		initialize();
	}
	//calculate the days between birth year and current year
	public static int DaysBetweenBirthANDCurrent(int iYearBirth ,int iYearCurrent)
	{  
		
		
		int iYearMembers,iLeapYearCount=0;
		int iYearsBetween,iDaysBetween = 0;
		
		
		for(iYearMembers = iYearBirth + 1; iYearMembers < iYearCurrent; iYearMembers++)
		{
			if(iYearMembers % 4 == 0)
				if(iYearMembers % 100 != 0)
				   iLeapYearCount++;
				
				else if(iYearMembers % 400 == 0)
					iLeapYearCount++;
						
		}
		
		if(iYearCurrent != iYearBirth)
		{
		iYearsBetween = iYearCurrent - iYearBirth - 1;
		iDaysBetween = iYearsBetween * 365 + iLeapYearCount;
		}
		return iDaysBetween;
		
	}
	//calculate of the days of current year
	public static int DaysOfCurrentYear(int iYearCurrent, int iMonthCurrent, int iDayCurrent)
	{
		//declaration
	    int[] iMonth={31,28,31,30,31,30,31,31,30,31,30,31};		
		int iMonthMembers,iDaysOfMonth=0;
		
	
		
		if(iYearCurrent % 4 == 0)
			if(iYearCurrent % 100 != 0)
			   iMonth[1]++;
			
			else if(iYearCurrent % 400 == 0)
			   iMonth[1]++;
	
		
		for(iMonthMembers = iMonthCurrent - 2; iMonthMembers >=0 ; iMonthMembers--)
		{
			iDaysOfMonth += iMonth[iMonthMembers];			
		}
		iDaysOfMonth += iDayCurrent; 	
		iMonth[1] = 28;
		System.out.println("the days of months is " + iMonth[1]);
		return iDaysOfMonth;
	}
	
	//Calculate the days of Birth Year
	public static int DaysOfBirthYear(int iYearBirth, int iMonthBirth, int iDayBirth)
	{
		int[] iMonth={31,28,31,30,31,30,31,31,30,31,30,31};
		int iDaysOfAYear = 365;
		int iDaysAfterBirthDay;
		int iMonthMembers,iDaysOfMonth=0;

		if(iYearBirth % 4 == 0)
			if(iYearBirth % 100 != 0)
			   {
				iMonth[1]++;
				iDaysOfAYear++;
			   }
			else if(iYearBirth % 400 == 0)
			{
				iMonth[1]++;
				iDaysOfAYear++;
			}
		
		for(iMonthMembers = iMonthBirth - 2; iMonthMembers >=0 ; iMonthMembers--)
		{
			iDaysOfMonth += iMonth[iMonthMembers];			
		}
		iDaysOfMonth += iDayBirth; 
		iDaysAfterBirthDay = iDaysOfAYear - iDaysOfMonth + 1;
		
		iMonth[1] = 28;
		System.out.println("the days of months is " + iMonth[1]);
	    
	    return iDaysAfterBirthDay; 
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 573, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDaysAliveCalculation = new JLabel("Days Alive Calculation");
		lblDaysAliveCalculation.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDaysAliveCalculation.setBounds(200, 10, 147, 25);
		frame.getContentPane().add(lblDaysAliveCalculation);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Calibri", Font.BOLD, 12));
		lblDateOfBirth.setBounds(23, 57, 76, 25);
		frame.getContentPane().add(lblDateOfBirth);
		
		final JLabel lblDaysAlive = new JLabel("Days Alive:");
		lblDaysAlive.setFont(new Font("Calibri", Font.BOLD, 12));
		lblDaysAlive.setBounds(250, 59, 97, 21);
		frame.getContentPane().add(lblDaysAlive);
		
		JLabel lblBirthday = new JLabel("BirthDay:");
		lblBirthday.setBounds(23, 91, 54, 15);
		frame.getContentPane().add(lblBirthday);
		
		JLabel lblBirthmonth = new JLabel("BirthMonth:");
		lblBirthmonth.setBounds(23, 116, 66, 15);
		frame.getContentPane().add(lblBirthmonth);
		
		JLabel lblBirthyear = new JLabel("BirthYear:");
		lblBirthyear.setBounds(23, 141, 66, 15);
		frame.getContentPane().add(lblBirthyear);
		
		JLabel lblCurrentDate = new JLabel("Current Date");
		lblCurrentDate.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCurrentDate.setBounds(23, 188, 76, 15);
		frame.getContentPane().add(lblCurrentDate);
		
		JLabel lblDays = new JLabel("Days:");
		lblDays.setBounds(23, 213, 54, 15);
		frame.getContentPane().add(lblDays);
		
		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setBounds(23, 238, 54, 15);
		frame.getContentPane().add(lblMonth);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(23, 263, 54, 15);
		frame.getContentPane().add(lblYear);
		
		textField_BirthDay = new JTextField();
		textField_BirthDay.setBounds(87, 88, 66, 21);
		frame.getContentPane().add(textField_BirthDay);
		textField_BirthDay.setColumns(10);
		
		textField_BirthMonth = new JTextField();
		textField_BirthMonth.setBounds(87, 113, 66, 21);
		frame.getContentPane().add(textField_BirthMonth);
		textField_BirthMonth.setColumns(10);
		
		textField_BirthYear = new JTextField();
		textField_BirthYear.setBounds(87, 141, 66, 21);
		frame.getContentPane().add(textField_BirthYear);
		textField_BirthYear.setColumns(10);
		
		textField_CurrentDay = new JTextField();
		textField_CurrentDay.setBounds(87, 210, 66, 21);
		frame.getContentPane().add(textField_CurrentDay);
		textField_CurrentDay.setColumns(10);
		
		textField_CurrentMonth = new JTextField();
		textField_CurrentMonth.setBounds(87, 235, 66, 21);
		frame.getContentPane().add(textField_CurrentMonth);
		textField_CurrentMonth.setColumns(10);
		
		textField_CurrentYear = new JTextField();
		textField_CurrentYear.setBounds(87, 260, 66, 21);
		frame.getContentPane().add(textField_CurrentYear);
		textField_CurrentYear.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
					//put the textfield data into each variable for calculation
					int iYearCurrent = Integer.parseInt(textField_CurrentYear.getText()) ;
					int iMonthCurrent = Integer.parseInt(textField_CurrentMonth.getText()) ;
					int iDayCurrent = Integer.parseInt(textField_CurrentDay.getText()) ;
				
			
					int iYearBirth = Integer.parseInt(textField_BirthYear.getText()) ;	
					int iMonthBirth = Integer.parseInt(textField_BirthMonth.getText()) ;	
					int iDayBirth = Integer.parseInt(textField_BirthDay.getText()) ;	
					
					
					//Calculate total days
				    int iDaysBetween = DaysBetweenBirthANDCurrent(iYearBirth,iYearCurrent);
					///System.out.println("the Days Between Birth Year and Current Year is " + iDaysBetween);
					
					int iDaysOfCurrentYear = DaysOfCurrentYear(iYearCurrent,iMonthCurrent,iDayCurrent);
					//System.out.println("the Days of Current Year is "+ iDaysOfCurrentYear);
					
					int iDaysAfterBirthDay = DaysOfBirthYear(iYearBirth,iMonthBirth,iDayBirth);
					//System.out.println("the days after birthday is " + iDaysAfterBirthDay);
					
					int iTotal = iDaysBetween + iDaysOfCurrentYear + iDaysAfterBirthDay;
					//System.out.println("The Days Alive is " + iTotal);
					
					String sTotal = Integer.toString(iTotal);
					
					lblDaysAlive.setText("Days Alive: " + sTotal);
				
				
			}
		});
		btnCalculate.setBounds(267, 259, 93, 23);
		frame.getContentPane().add(btnCalculate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	//open a messagebox for alerming click yes to quit
				if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
		            System.exit(0);
				}
			}
		});
		btnExit.setBounds(401, 259, 93, 23);
		frame.getContentPane().add(btnExit);
	}
}

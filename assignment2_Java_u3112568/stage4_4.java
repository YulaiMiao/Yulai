// Author: 				Yulai Miao
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;





import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

//actually it is stage4
public class stage4_4 {

	private JFrame frame;
	private JTextField textField_BirthYear;
	private JTextField textField_BirthMonth;
	private JTextField textField_BirthDay;
	private JTextField textField_Year;
	private JTextField textField_Month;
	private JTextField textField_Day;
	public static Vector<dates> VecDates = new Vector<dates>();
	private static final int MAX_LINES_PER_PAGE = 3;
	//private JPanel jContentPane = null;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stage4_4 window = new stage4_4();
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
	public stage4_4() {
		initialize();
	}
	
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
		//System.out.println("the days of months is " + iMonth[1]);
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
		//System.out.println("the days of months is " + iMonth[1]);
	    
	    return iDaysAfterBirthDay; 
		
	}
	
	public  int DataBaseOfAliveDays(
			int iYearCurrent , 
			int iMonthCurrent ,	
			int iDayCurrent , 
			int iYearBirth ,	
			int iMonthBirth, 
			int iDayBirth 	 )
	
	{
		
		
			int iDaysBetween = DaysBetweenBirthANDCurrent(iYearBirth,iYearCurrent);
			///System.out.println("the Days Between Birth Year and Current Year is " + iDaysBetween);
			
			int iDaysOfCurrentYear = DaysOfCurrentYear(iYearCurrent,iMonthCurrent,iDayCurrent);
			//System.out.println("the Days of Current Year is "+ iDaysOfCurrentYear);
			
			int iDaysAfterBirthDay = DaysOfBirthYear(iYearBirth,iMonthBirth,iDayBirth);
			//System.out.println("the days after birthday is " + iDaysAfterBirthDay);
			
			int iTotal = iDaysBetween + iDaysOfCurrentYear + iDaysAfterBirthDay;
			//System.out.println("The Days Alive is " + iTotal);
			
			
			return iTotal; 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 582, 305);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(303, 200, 93, 23);
		frame.getContentPane().add(btnPrint);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(frame, 
			            "Are you sure to close this window?", "Really Closing?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					{
			            System.exit(0);
					}
				
			}
		});
		btnExit.setBounds(419, 200, 93, 23);
		frame.getContentPane().add(btnExit);
		
		JLabel lblBirthyear = new JLabel("BirthYear");
		lblBirthyear.setBounds(290, 38, 54, 15);
		frame.getContentPane().add(lblBirthyear);
		
		JLabel lblBirthmonth = new JLabel("BirthMonth");
		lblBirthmonth.setBounds(290, 63, 60, 15);
		frame.getContentPane().add(lblBirthmonth);
		
		JLabel lblBirthday = new JLabel("BirthDay");
		lblBirthday.setBounds(290, 88, 54, 15);
		frame.getContentPane().add(lblBirthday);
		
		textField_BirthYear = new JTextField();
		textField_BirthYear.setBounds(377, 35, 66, 21);
		frame.getContentPane().add(textField_BirthYear);
		textField_BirthYear.setColumns(10);
		
		textField_BirthMonth = new JTextField();
		textField_BirthMonth.setBounds(377, 60, 66, 21);
		frame.getContentPane().add(textField_BirthMonth);
		textField_BirthMonth.setColumns(10);
		
		textField_BirthDay = new JTextField();
		textField_BirthDay.setBounds(377, 85, 66, 21);
		frame.getContentPane().add(textField_BirthDay);
		textField_BirthDay.setColumns(10);
		
		JLabel lblCurrentyear = new JLabel("CurrentYear");
		lblCurrentyear.setBounds(290, 112, 74, 15);
		frame.getContentPane().add(lblCurrentyear);
		
		textField_Year = new JTextField();
		textField_Year.setBounds(377, 109, 66, 21);
		frame.getContentPane().add(textField_Year);
		textField_Year.setColumns(10);
		
		JLabel lblCurrentmonth = new JLabel("CurrentMonth");
		lblCurrentmonth.setBounds(290, 137, 74, 15);
		frame.getContentPane().add(lblCurrentmonth);
		
		textField_Month = new JTextField();
		textField_Month.setBounds(377, 134, 66, 21);
		frame.getContentPane().add(textField_Month);
		textField_Month.setColumns(10);
		
		JLabel lblCurrentday = new JLabel("CurrentDay");
		lblCurrentday.setBounds(290, 162, 74, 15);
		frame.getContentPane().add(lblCurrentday);
		
		textField_Day = new JTextField();
		textField_Day.setBounds(377, 159, 66, 21);
		frame.getContentPane().add(textField_Day);
		textField_Day.setColumns(10);
		
		final JLabel lblTotalDays = new JLabel("Total Days:");
		lblTotalDays.setBounds(419, 233, 113, 15);
		frame.getContentPane().add(lblTotalDays);
		
		final JList  Jlist_DDB = new JList();
		Jlist_DDB.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//set data into textfields from vector
				textField_BirthDay.setText(VecDates.get(Jlist_DDB.getSelectedIndex()).sDayBirth);
				textField_BirthMonth.setText(VecDates.get(Jlist_DDB.getSelectedIndex()).sMonthBirth);
				textField_BirthYear.setText(VecDates.get(Jlist_DDB.getSelectedIndex()).sYearBirth);
				textField_Day.setText(VecDates.get(Jlist_DDB.getSelectedIndex()).sDayCurrent);
				textField_Month.setText(VecDates.get(Jlist_DDB.getSelectedIndex()).sMonthCurrent);
				textField_Year.setText(VecDates.get(Jlist_DDB.getSelectedIndex()).sYearCurrent);	
			
			}
		});
		Jlist_DDB.setBounds(24, 37, 256, 197);
		frame.getContentPane().add(Jlist_DDB);
		
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// click print to print a report 
				PrintWriter pw = null; 
				//get data from vector
				 String sName = VecDates.get(Jlist_DDB.getSelectedIndex()).sName;      
				 String sBirthDay = VecDates.get(Jlist_DDB.getSelectedIndex()).sDayBirth;
				 String sBirthMonth = VecDates.get(Jlist_DDB.getSelectedIndex()).sMonthBirth;
				 String sBirthYear = VecDates.get(Jlist_DDB.getSelectedIndex()).sYearBirth;
				 String sCurrentDay = VecDates.get(Jlist_DDB.getSelectedIndex()).sDayCurrent;
				 String sCurrentMonth = VecDates.get(Jlist_DDB.getSelectedIndex()).sMonthCurrent;
				 String sCurrentYear = VecDates.get(Jlist_DDB.getSelectedIndex()).sYearCurrent;
				
				try{
					//report format
					pw = new PrintWriter(new BufferedWriter(new FileWriter("report.txt")));
					pw.printf("%s",sName);
					pw.println(); 
					pw.printf("  =====");
					pw.println(); 
					pw.printf("BirthDay:%s",sBirthDay);
					pw.println(); 
					pw.printf("BirthMonth:%s",sBirthMonth);
					pw.println(); 
					pw.printf("BirthYear:%s",sBirthYear);
					pw.println(); 
					pw.printf("CurrentDay:%s",sCurrentDay);
					pw.println(); 
					pw.printf("CurrentMonth:%s",sCurrentMonth);
					pw.println(); 
					pw.printf("CurrentYear:%s",sCurrentYear);
					pw.println(); 
					pw.printf("-----REPORT-----");
	   
					pw.println();     
					//pw.write();
					
				}catch(IOException  e) {
					e.printStackTrace(); 
		        } finally {
		        	pw.close();
		            }
		        

			}
		});
		
		// Connect to database
		java.sql.Connection conn = null;
        try {
        	
            conn = DDBConnection.connection("dates.mdb");
            //creat a statement to select the data from ddb
    		Statement st = conn.createStatement();
    		//creat resultset to store data
    		ResultSet rs = st.executeQuery("SELECT * FROM tblDates");
    		
    		//curcor go to next line
    		 while (rs.next()) 
    		 	{
    			 //store date into vector
    			 VecDates.addElement(new dates(rs.getString(1).toString(),
    					 rs.getString(5).toString(), 
    					 rs.getString(6).toString(), 
    					 rs.getString(7).toString(),
    					 rs.getString(2).toString(),
    					 rs.getString(3).toString(),
    					 rs.getString(4).toString()));
    			 
    	         
    	        }
    		 
    		 String[] saNames = new String[VecDates.size()];
    		 //print the names on the Jlist
 			 for (int iI = 0; iI < VecDates.size(); iI++)
 				saNames[iI] = VecDates.get(iI).sName;
    		 Jlist_DDB.setListData(saNames);
    		 Jlist_DDB.setSelectedIndex(0);
    		 
    		 JButton btnCalculate = new JButton("Calculate");
    		 btnCalculate.addActionListener(new ActionListener() {
    		 	public void actionPerformed(ActionEvent e) {
    		 		//click to calculate
    		 		int iYearCurrent = Integer.parseInt(textField_Year.getText()) ;
    				int iMonthCurrent = Integer.parseInt(textField_Month.getText()) ;
    				int iDayCurrent = Integer.parseInt(textField_Day.getText()) ;
    			
    		
    				int iYearBirth = Integer.parseInt(textField_BirthYear.getText()) ;	
    				int iMonthBirth = Integer.parseInt(textField_BirthMonth.getText()) ;	
    				int iDayBirth = Integer.parseInt(textField_BirthDay.getText()) ;	
    			    int	iTotal = DataBaseOfAliveDays(iYearCurrent , iMonthCurrent ,	iDayCurrent , iYearBirth ,	
    						iMonthBirth, 
    						iDayBirth);
    			    lblTotalDays.setText("Total Days:" + iTotal);
    		 		
    		 	}
    		 });
    		 btnCalculate.setBounds(303, 228, 93, 23);
    		 frame.getContentPane().add(btnCalculate);
    		 
    		
            
          
        } catch(SQLException s) {
            System.out.println(s);
        } finally {
            if(conn != null) {
                try {
                    conn.close();		// Close connection to database
                } catch(SQLException ignore) {}
            }
        }
    	
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"



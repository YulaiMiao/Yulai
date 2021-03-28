// Author: 				Yulai Miao
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JRadioButton;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.UIManager;



import java.awt.Font;

//actually it is stage3
//actually it is stage3
//actually it is stage3
//actually it is stage3

public class stage4 {

	private JFrame frame;
	private JTextField textField_BirthDay;
	private JTextField textField_BirthMonth;
	private JTextField textField_BirthYear;
	private JTextField textField_Day;
	private JTextField textField_Month;
	private JTextField textField_Year;
    private Vector<dates> Vecdates;
    private JRadioButton rdbtnManualInput;
    private JRadioButton rdbtnAutoInput;
    private ButtonGroup elementGroup = new ButtonGroup();
    
    private myJPanel panel;
	private BufferedImage img;
	private Graphics2D g2dImg;


	int iX, iY, iWidth, iHeight;
	int[] iTotal = new int[100] ;
    		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stage4 window = new stage4();
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
	public stage4() {
		initialize();
	}
	
	//calculate the days between birth date and current date
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
	
	//calculate the days of current  year
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
	// calculate total alive days 
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
		frame.getContentPane().setFont(new Font("Calibri", Font.BOLD, 12));
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 746, 347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(24, 40, 85, 22);
		frame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblBirthDay = new JLabel("Birth Day");
		lblBirthDay.setBounds(24, 71, 54, 15);
		frame.getContentPane().add(lblBirthDay);
		
		JLabel lblBirthMonth = new JLabel("Birth Month");
		lblBirthMonth.setBounds(24, 96, 75, 15);
		frame.getContentPane().add(lblBirthMonth);
		
		JLabel lblBirthYear = new JLabel("Birth Year");
		lblBirthYear.setBounds(24, 121, 75, 15);
		frame.getContentPane().add(lblBirthYear);
		
		JLabel lblCurrentDate = new JLabel("Current Date");
		lblCurrentDate.setBounds(24, 146, 75, 15);
		frame.getContentPane().add(lblCurrentDate);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setBounds(24, 172, 54, 15);
		frame.getContentPane().add(lblDay);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(24, 196, 54, 15);
		frame.getContentPane().add(lblMonth);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(24, 221, 54, 15);
		frame.getContentPane().add(lblYear);
		
		textField_BirthDay = new JTextField();
		textField_BirthDay.setBounds(99, 72, 66, 21);
		frame.getContentPane().add(textField_BirthDay);
		textField_BirthDay.setColumns(10);
		
		textField_BirthMonth = new JTextField();
		textField_BirthMonth.setBounds(99, 93, 66, 21);
		frame.getContentPane().add(textField_BirthMonth);
		textField_BirthMonth.setColumns(10);
		
		textField_BirthYear = new JTextField();
		textField_BirthYear.setBounds(99, 118, 66, 21);
		frame.getContentPane().add(textField_BirthYear);
		textField_BirthYear.setColumns(10);
		
		textField_Day = new JTextField();
		textField_Day.setBounds(99, 169, 66, 21);
		frame.getContentPane().add(textField_Day);
		textField_Day.setColumns(10);
		
		textField_Month = new JTextField();
		textField_Month.setBounds(99, 193, 66, 21);
		frame.getContentPane().add(textField_Month);
		textField_Month.setColumns(10);
		
		textField_Year = new JTextField();
		textField_Year.setBounds(99, 218, 66, 21);
		frame.getContentPane().add(textField_Year);
		textField_Year.setColumns(10);
		
		final JList list = new JList();	
		list.setBounds(184, 43, 223, 205);
		frame.getContentPane().add(list);
		
		JLabel lblTotalDays = new JLabel("Total Days:");
		lblTotalDays.setBounds(33, 262, 66, 15);
		frame.getContentPane().add(lblTotalDays);
		
		final JLabel TotalDays = new JLabel("");
		TotalDays.setBounds(99, 262, 54, 15);
		frame.getContentPane().add(TotalDays);
		
		//radio botton
		rdbtnManualInput = new JRadioButton("manual input current date");
		rdbtnManualInput.addMouseListener(new MouseAdapter() {
			@Override
			//click clear current date textfields
			public void mouseClicked(MouseEvent arg0) 
			{
				textField_Year.setText("");
				textField_Month.setText("");
				textField_Day.setText("");
			}
		});
		rdbtnManualInput.setBounds(140, 14, 187, 23);
		frame.getContentPane().add(rdbtnManualInput);
			
		rdbtnAutoInput = new JRadioButton("auto input");
		rdbtnAutoInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//click to put all the data into textfields
				textField_BirthDay.setText(Vecdates.get(list.getSelectedIndex()).sDayBirth);
				textField_BirthMonth.setText(Vecdates.get(list.getSelectedIndex()).sMonthBirth);
				textField_BirthYear.setText(Vecdates.get(list.getSelectedIndex()).sYearBirth);
				textField_Day.setText(Vecdates.get(list.getSelectedIndex()).sDayCurrent);
				textField_Month.setText(Vecdates.get(list.getSelectedIndex()).sMonthCurrent);
				textField_Year.setText(Vecdates.get(list.getSelectedIndex()).sYearCurrent);
			}
		});
		rdbtnAutoInput.setBounds(17, 14, 92, 23);
		frame.getContentPane().add(rdbtnAutoInput);
		
		//group botton to combine two radio botton
		elementGroup.add(rdbtnManualInput);
		elementGroup.add(rdbtnAutoInput);
		
		//creat vector named dates
		Vecdates = new Vector<dates>();
		
		try
		{	
			//read file
			BufferedReader in = new BufferedReader(new FileReader("dates.txt"));
			String sLine,sName;
			String[] saLineElementsByS;
			String[] sDOB;
			String[] sCurrentDate;
		
			String sDayBirth,sMonthBirth,sYearBirth;
			String sDayCurrent,sMonthCurrent,sYearCurrent;
			
			//split the line into 7 parts and store the data in vector<dates>
			while((sLine = in.readLine()) != null)
			{   
				//split data
				saLineElementsByS = sLine.split(";");
				sName = saLineElementsByS[0];
				sDOB = saLineElementsByS[1].split(",");
				sCurrentDate = saLineElementsByS[2].split(",");
				
				sDayBirth = sDOB[0];
				sMonthBirth = sDOB[1];
				sYearBirth = sDOB[2];
				
				sDayCurrent = sCurrentDate[0];
				sMonthCurrent = sCurrentDate[1];
				sYearCurrent = sCurrentDate[2];
				
				//store data 
				Vecdates.addElement(new dates(
						sName,
						sDayBirth,
						sMonthBirth,
						sYearBirth,
						sDayCurrent,
						sMonthCurrent,
						sYearCurrent));
				
			}
			// Set names in List Box
			String[] saNames = new String[Vecdates.size()];
			for (int iI = 0; iI < Vecdates.size(); iI++)
				saNames[iI] = Vecdates.get(iI).sName;
			list.setListData(saNames);
			list.setSelectedIndex(0);
			
		}
		catch (IOException e)
		{
			System.out.println("ERROR: Could not read text file!");
		}
		
		for (int iI = 0; iI < Vecdates.size(); iI++)
		{
			int iYearCurrent = Integer.parseInt(Vecdates.get(iI).sYearCurrent) ;
			int iMonthCurrent = Integer.parseInt(Vecdates.get(iI).sMonthCurrent) ;
			int iDayCurrent = Integer.parseInt(Vecdates.get(iI).sDayCurrent) ;
	

			int iYearBirth = Integer.parseInt(Vecdates.get(iI).sYearBirth) ;	
			int iMonthBirth = Integer.parseInt(Vecdates.get(iI).sMonthBirth) ;	
			int iDayBirth = Integer.parseInt(Vecdates.get(iI).sDayBirth) ;
			
			iTotal[iI] = DataBaseOfAliveDays(iYearCurrent , iMonthCurrent ,	iDayCurrent , iYearBirth ,	
				iMonthBirth, 
				iDayBirth);
		
		System.out.println(iTotal[iI]);
		}
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if(rdbtnAutoInput.isSelected())
				{	
					//click auto input, set all data from specific vector
					textField_BirthDay.setText(Vecdates.get(list.getSelectedIndex()).sDayBirth);
					textField_BirthMonth.setText(Vecdates.get(list.getSelectedIndex()).sMonthBirth);
					textField_BirthYear.setText(Vecdates.get(list.getSelectedIndex()).sYearBirth);
					textField_Day.setText(Vecdates.get(list.getSelectedIndex()).sDayCurrent);
					textField_Month.setText(Vecdates.get(list.getSelectedIndex()).sMonthCurrent);
					textField_Year.setText(Vecdates.get(list.getSelectedIndex()).sYearCurrent);
				}
				else if(rdbtnManualInput.isSelected())
				{	
					//click manual input, clean current date , set data of birth date to textfields
					textField_BirthDay.setText(Vecdates.get(list.getSelectedIndex()).sDayBirth);
					textField_BirthMonth.setText(Vecdates.get(list.getSelectedIndex()).sMonthBirth);
					textField_BirthYear.setText(Vecdates.get(list.getSelectedIndex()).sYearBirth);
					textField_Year.setText("");
					textField_Month.setText("");
					textField_Day.setText("");
					
				}
		iY=25;iWidth=25;
		//drawing all the bar depending the data of all alive days which has been calculated
		for(int iCount = 0;iCount < 10; iCount++)
			{	
				
				iHeight = (iTotal[iCount]/300);
				g2dImg.setPaint(Color.black);
				g2dImg.fill(new Rectangle2D.Double(iX,iY,iWidth,iHeight) );
				panel.repaint();
				iX += 30 ;
				
			}

			}
		});
		
	
		
		
		JButton btnCalculate = new JButton("calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int iYearCurrent = Integer.parseInt(textField_Year.getText()) ;
				int iMonthCurrent = Integer.parseInt(textField_Month.getText()) ;
				int iDayCurrent = Integer.parseInt(textField_Day.getText()) ;
			
		
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
				
				TotalDays.setText(sTotal);

				
			}
		});
		btnCalculate.setBounds(184, 258, 93, 23);
		frame.getContentPane().add(btnCalculate);
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//open a messagebox , then select yes to quit
				if (JOptionPane.showConfirmDialog(frame, 
			            "Are you sure to close this window?", "Really Closing?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					{
			            System.exit(0);
					}
				
			}
		});
		btnExit.setBounds(314, 258, 93, 23);
		frame.getContentPane().add(btnExit);
		
		//AffineTransform at = AffineTransform.getQuadrantRotateInstance(1);
		//AffineTransform atDefault = AffineTransform.getQuadrantRotateInstance(0);
		
		panel = new myJPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(413, 40, 290, 210);
		frame.getContentPane().add(panel);
		
		JLabel lblW = new JLabel("W");
		lblW.setFont(new Font("Calibri", Font.BOLD, 12));
		lblW.setBounds(418, 20, 20, 15);
		frame.getContentPane().add(lblW);
		
		JLabel lblP = new JLabel("P");
		lblP.setFont(new Font("Calibri", Font.BOLD, 12));
		lblP.setBounds(448, 18, 19, 19);
		frame.getContentPane().add(lblP);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Calibri", Font.BOLD, 12));
		lblA.setBounds(477, 20, 13, 15);
		frame.getContentPane().add(lblA);
		
		JLabel lblNewLabel = new JLabel("S");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 12));
		lblNewLabel.setBounds(509, 20, 19, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAd = new JLabel("AD");
		lblAd.setFont(new Font("Calibri", Font.BOLD, 12));
		lblAd.setBounds(538, 20, 20, 15);
		frame.getContentPane().add(lblAd);
		
		JLabel lblNewLabel_1 = new JLabel("SH");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 12));
		lblNewLabel_1.setBounds(568, 20, 20, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblN = new JLabel("N");
		lblN.setFont(new Font("Calibri", Font.BOLD, 12));
		lblN.setBounds(600, 20, 13, 15);
		frame.getContentPane().add(lblN);
		
		JLabel lblJ = new JLabel("J");
		lblJ.setFont(new Font("Calibri", Font.BOLD, 12));
		lblJ.setBounds(633, 20, 13, 15);
		frame.getContentPane().add(lblJ);
		
		JLabel lblI = new JLabel("I");
		lblI.setFont(new Font("Calibri", Font.BOLD, 12));
		lblI.setBounds(664, 20, 13, 15);
		frame.getContentPane().add(lblI);
		
		JLabel lblL = new JLabel("L");
		lblL.setFont(new Font("Calibri", Font.BOLD, 12));
		lblL.setBounds(687, 20, 13, 15);
		frame.getContentPane().add(lblL);
		
		// Instantiate the BufferedImage object and give it the same width 
		// and height as that of the drawing area JPanel
		img = new BufferedImage(panel.getWidth(), 
                panel.getHeight(), 
                BufferedImage.TYPE_INT_RGB);
		// Get its graphics context. A graphics context of a particular object allows us to draw on it.
		g2dImg = (Graphics2D)img.getGraphics();

		// Draw a filled white coloured rectangle on the entire area to clear it.
		g2dImg.setPaint(Color.WHITE);
		g2dImg.fill(new Rectangle2D.Double(0, 0, img.getWidth(), img.getHeight()));

	}

	class myJPanel extends JPanel
	{
		 private Rectangle2D.Double rectangle;
		 
		 public void paintComponent(Graphics g) 
		 {
			super.paintComponent(g);
			rectangle = new Rectangle2D.Double(iX, iY, iWidth, iHeight);
			g2dImg.setPaint(Color.black);
			g2dImg.fill(new Rectangle2D.Double(iX,iY,iWidth,iHeight));
			g.drawImage(img, 0, 0, null);
		 }
	}
}



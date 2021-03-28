// Author: 				Yulai Miao

import java.util.Scanner;

public class Stage1 {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int iDayBirth,iMonthBirth,iYearBirth;
		int iDayCurrent,iMonthCurrent,iYearCurrent;
		int iDaysBetween,iDaysOfCurrentYear,iDaysAfterBirthDay;
		int iTotal;
		
		//input elemental members for calculation
		System.out.println("Please input your birth year:");
		Scanner inConsole = new Scanner(System.in);
		iYearBirth = inConsole.nextInt();
		
		System.out.println("Please input your birth month:");
		iMonthBirth = inConsole.nextInt();
		
		System.out.println("Please input your birth day:");
		iDayBirth = inConsole.nextInt();
		
		System.out.println("Please input the current year:");
		iYearCurrent = inConsole.nextInt();
		
		System.out.println("Please input the current month:");
		iMonthCurrent = inConsole.nextInt();
		
		System.out.println("Please input the current day:");
		iDayCurrent = inConsole.nextInt();
		
		//Calculate total days
	    iDaysBetween = DaysBetweenBirthANDCurrent(iYearBirth,iYearCurrent);
		System.out.println("the Days Between Birth Year and Current Year is " + iDaysBetween);
		
		iDaysOfCurrentYear = DaysOfCurrentYear(iYearCurrent,iMonthCurrent,iDayCurrent);
		System.out.println("the Days of Current Year is "+ iDaysOfCurrentYear);
		
		iDaysAfterBirthDay = DaysOfBirthYear(iYearBirth,iMonthBirth,iDayBirth);
		System.out.println("the days after birthday is " + iDaysAfterBirthDay);
		
		iTotal = iDaysBetween + iDaysOfCurrentYear + iDaysAfterBirthDay;
		System.out.println("The Days Alive is " + iTotal);
	}



	//Calculate the days between BYear and CYear
	public static int DaysBetweenBirthANDCurrent(int iYearBirth ,int iYearCurrent)
	{  
		
		
		int iYearMembers,iLeapYearCount=0;
		int iYearsBetween,iDaysBetween = 0;
		
	// determine leap years  
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
		// calculation of years between
		iYearsBetween = iYearCurrent - iYearBirth - 1;
		iDaysBetween = iYearsBetween * 365 + iLeapYearCount;
		}
		return iDaysBetween;
		
	}
	
	//Calculate the days of Current Year
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
		//add days of months together
		for(iMonthMembers = iMonthBirth - 2; iMonthMembers >=0 ; iMonthMembers--)
		{
			iDaysOfMonth += iMonth[iMonthMembers];			
		}
		iDaysOfMonth += iDayBirth; 
		iDaysAfterBirthDay = iDaysOfAYear - iDaysOfMonth+1;
		
		iMonth[1] = 28;
		System.out.println("the days of months is " + iMonth[1]);
	    
	    return iDaysAfterBirthDay; 
		
	}
	
}

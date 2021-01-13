package Exercises.ex7.software1.date;

public class TestDate {

	public static void main(String[] args) {
		String d1 = "2/11/2019";
		int d2 = 390;
		int[] d3 = {1986, 9, 24};
		String d4 = "28/12/2019";
		int[] d5 = {2, 1, 4};
		
		
		Date date1 = DateFactory.createDate(d1); 
		Date date2 = DateFactory.createDate(d2); 
		Date date3 = DateFactory.createDate(d3);
		Date date4 = DateFactory.createDate(d4);
		Date date5 = DateFactory.createDate(d5);

		System.out.println("date2 day: " + date2.getDay()); // 26
		System.out.println("date2 month: " + date2.getMonth()); // 1
		System.out.println("date2 year: " + date2.getYear()); // 2
		
		System.out.println("date3: " + date3); // 24/9/1986
		
		System.out.println("April: " + Date.getDaysInMonth(4)); // 30 
		System.out.println("February: " + Date.getDaysInMonth(2)); // 28
		System.out.println("July: " + Date.getDaysInMonth(7)); // 31		
		
		System.out.println("is date1 between date2 and date3: " + date1.isBetweenDates(date2, date3));	// false
		System.out.println("is date2 between date1 and date3: : " + date2.isBetweenDates(date1, date3)); // false 
		System.out.println("is date3 between date2 and date1: : " + date3.isBetweenDates(date2, date1)); // true
		
		System.out.println("Difference in days between date1 and date3: "  + date1.differenceInDays(date3)); //-12084
		
		date2.addDays(-400);
		System.out.println("date2 after subtracting 400 days: " + date2); // 1/1/1
		
		date3.addDays(12084);
		System.out.println("date3 after adding 12084 days: " + date3); // 2/11/2019
		
		date1.addDays(-12084);
		System.out.println("date1 after subtracting 12084 days: " + date1); // 24/9/1986

		date4.addDays(370);
		System.out.println("date4 after adding 4 days: " + date4); // 2/1/2021

		date4.addDays(-4);
		System.out.println("date1 after subtracting 4 days: " + date4); // 29/12/2020

		date4.addDays(-365);
		System.out.println("date1 after subtracting 4 days: " + date4); // 29/12/2019

		date4.addDays(-29);
		System.out.println("date1 after subtracting 4 days: " + date4); // 29/12/2019

		date5.addDays(-200000);
		System.out.println("date5 after subtracting 4 days: " + date5); // 1/1/1
	}

}

package pl.entpoint.harmony.util;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 31 sie 2020
 * 
 */

public class ConvertData {
	
	private ConvertData() {}
	
	public static LocalDate getFirstDayOfMonth(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		
		return LocalDate.of(year, month, 1);
	}
	
}

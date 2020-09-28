package pl.entpoint.harmony.util;

import java.time.LocalDate;
import java.time.Month;

import org.exparity.hamcrest.date.LocalDateMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mateusz Dąbek
 * @created 28 wrz 2020
 * 
 */

public class ConvertDataTest {
	
	@Test
	void givenStringDateExceptFirstDay() {
		//given
		String date = "2020-02-15";
		LocalDate firstDayExcept = LocalDate.of(2020, Month.FEBRUARY, 1);
		
		//when
		LocalDate firstDay = ConvertData.getFirstDayOfMonth(date);
		
		//then
		assertThat(firstDayExcept, LocalDateMatchers.sameDay(firstDay));
	}
	
	@Test
	void givingWrongDateShouldThrowExeption( ) {
		//given
		String date = "2020-02-5";			
		
		//when	
		//then
		assertThrows(IllegalArgumentException.class, () -> ConvertData.getFirstDayOfMonth(date));
	}

}

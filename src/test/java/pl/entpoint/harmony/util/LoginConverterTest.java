package pl.entpoint.harmony.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Mateusz Dąbek
 * @created 29 lip 2020
 * 
 */

class LoginConverterTest {
	
	@Test
	void givingNameAndLastnameShouldReturnValidLogin() {
		//given 
		String name = "Łukasz";
		String surname = "Żółćoch";
		
        //then
		assertThat(LoginConverter.createLogin(name, surname), equalTo("zolcoch_l"));		
	}
	
	@Test
	void givingTwoPartsLastnameShouldReturnValidLogin() {
		//given 
		String name = "Łukasz";
		String surname = "Żółćoch - Klimczak";
		
        //then
		assertThat(LoginConverter.createLogin(name, surname), equalTo("zolcoch_l"));
	}

}

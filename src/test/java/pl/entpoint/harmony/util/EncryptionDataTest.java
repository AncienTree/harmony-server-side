package pl.entpoint.harmony.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Mateusz Dąbek
 * @created 29 lip 2020
 * 
 */

public class EncryptionDataTest {

	@Test
	void givingStringToEncryptIsNotEqualsToOriginalString() {
		//givne
		String text = "qwErtY122!";
		
		//when
		String encrypt = EncryptionData.encrypt(text);
		
		//then
		assertThat(encrypt, is(not(equalTo(text))));
	}

	@Test
	void givingEncyptedStringIsEqualsToDecryptedString(){
		//given
		String secret = "Bardzo Trudny hasło";
		String encrypted = EncryptionData.encrypt(secret);
		
		//then
		assertThat(secret, equalTo(EncryptionData.decrypt(encrypted)));
	}

}

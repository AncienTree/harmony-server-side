package pl.entpoint.harmony;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
	public static void main(String[] args) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		
		for (int i = 1; i <=10; i++) {
			String encoded = bc.encode("abc123");
			System.out.println(encoded);
		}
		
		
	}
}

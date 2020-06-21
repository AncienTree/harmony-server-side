package pl.entpoint.harmony.util;

import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Dąbek
 * @created 27 kwi 2020
 * 
 */

@Slf4j
public class BlowfishEncryption {
	// TODO Zmienić i ukryć w finalnej wersji
	private static final String KEY = "Test";

    public static String encrypt(String stringToEncrypt) {
		log.info("Rozpoczęcie szyfrowania.");
		try {
			byte[] keyData = (KEY).getBytes();
			SecretKeySpec keySpec = new SecretKeySpec(keyData, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] hasil = cipher.doFinal(stringToEncrypt.getBytes());

			return new String(Base64.getEncoder().encode(hasil));
		} catch (Exception e) {
			log.error("Błąd w trakcie szyfrowania. " + e.getMessage());
			e.printStackTrace();

			return null;
		}
	}

	public static String decrypt(String encryptedString) {
		log.info("Rozpoczęcie deszyforwania.");
		try {
			byte[] keyData = (KEY).getBytes();
			SecretKeySpec keySpec = new SecretKeySpec(keyData, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] hasil = cipher.doFinal(Base64.getDecoder().decode(encryptedString));

			return new String(hasil);

		} catch (Exception e) {
			log.error("Błąd w trakcie deszyforwania. " + e.getMessage());
			e.printStackTrace();

			return null;
		}
	}
}

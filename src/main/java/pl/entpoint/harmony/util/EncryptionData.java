package pl.entpoint.harmony.util;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Dąbek
 * @created 27 kwi 2020
 * 
 */

@Slf4j
public class EncryptionData {
	
	// TODO w finalnej wersji pobierać z ENV
	private static final String KEY = "Test";
	private static final String SALT = "sdas";
	
	private EncryptionData() {}
	
    public static String encrypt(String stringToEncrypt) {
		log.info("Rozpoczęcie szyfrowania.");
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);
	         
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(KEY.toCharArray(), SALT.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			log.error("Błąd w trakcie szyfrowania. " + e.getMessage());
			e.printStackTrace();			
		}
		return null;
	}    
    
	public static String decrypt(String encryptedString) {
		log.info("Rozpoczęcie deszyfrowania.");
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);
	         
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(KEY.toCharArray(), SALT.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
	        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedString)));

		} catch (Exception e) {
			log.error("Błąd w trakcie deszyfrowania. " + e.getMessage());
			e.printStackTrace();			
		}
		return null;
	}
}

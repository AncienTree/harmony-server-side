package pl.entpoint.harmony.util;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author Mateusz Dąbek
 * @created 27 kwi 2020
 * 
 */

public class BlowfishEncryption {
	
	//TODO Zmienić i ukryć w finalnej wersji
	private static final String KEY = "Test";
    private static Base64 base64 = new Base64(true);
    
    public static String encrypt(String data) throws Exception {
    	SecretKeySpec key = new SecretKeySpec(KEY.getBytes("UTF8"), "Blowfish");
    	Cipher cipher = Cipher.getInstance("Blowfish");
    	cipher.init(Cipher.ENCRYPT_MODE, key);
    	    	
    	return base64.encodeToString(cipher.doFinal(data.getBytes("UTF8")));
    }
    
    public static String decrypt(String encryptedData)  throws Exception {
    	byte[] encrypted = base64.decodeBase64(encryptedData);
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes("UTF8"), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encrypted);
        
    	return new String(decrypted);
    }
}

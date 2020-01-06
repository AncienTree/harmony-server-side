package pl.entpoint.harmony.util;

public class LoginConverter {
	
	public static String createLogin(String firstName, String lastName) {
		String fn = replacePolishChar(firstName).toLowerCase().trim();
		String ls = replacePolishChar(lastName).toLowerCase().trim();
		ls = twoPartLastNameChceck(ls);
		
		String login = ls + "_" + fn.substring(0, 1);
		
		return login;
	}
	
	private static String replacePolishChar(String text) {
		return text.replace("ą", "a").replace("Ą", "A")
				.replace("ć", "c").replace("Ć", "C")
				.replace("ę", "e").replace("Ę", "E")
				.replace("ł", "l").replace("Ł", "L")
				.replace("ń", "n").replace("Ń", "N")
				.replace("ó", "o").replace("Ó", "O")
				.replace("ś", "s").replace("Ś", "S")
				.replace("ż", "z").replace("Ż", "Z")
				.replace("ź", "z").replace("Ź", "Z")
				.replace(" ", "");
	}

	private static String twoPartLastNameChceck(String lastName) {
		if (lastName.contains("-")) {
			String temp = "";
			for(int i = 0; i < lastName.length(); i++) {
				if(lastName.charAt(i) == '-') {
					break;
				}
				temp += lastName.substring(i, i + 1);
			}
			
			return temp;
		}
		return lastName;
	}
}

package pl.entpoint.harmony.util;

/**
 * @author Mateusz Dąbek
 * @created 15/01/2020
 */

public class LoginConverter {
	
	private LoginConverter() {}

    public static String createLogin(String firstName, String lastName) {
        String fn = replacePolishChar(firstName).toLowerCase().trim();
        String ls = replacePolishChar(lastName).toLowerCase().trim();
        ls = twoPartLastNameCheck(ls);

        return ls + "_" + fn.substring(0, 1);
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

    private static String twoPartLastNameCheck(String lastName) {
        if (lastName.contains("-")) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < lastName.length(); i++) {
                if (lastName.charAt(i) == '-') {
                    break;
                }
                temp.append(lastName, i, i + 1);
            }

            return temp.toString();
        }
        return lastName;
    }
}

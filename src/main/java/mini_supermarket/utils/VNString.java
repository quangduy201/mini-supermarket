package mini_supermarket.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class VNString {
    public static final String NULL = " \u0000 ";

    public static char removeAccent(char c) {
        String s = String.valueOf(c);
        s = removeAccent(s);
        return s.charAt(0);
    }

    public static String removeAccent(String str) {
        str = removeUpperCaseAccent(str);
        str = removeLowerCaseAccent(str);
        return str;
    }

    public static String removeUpperCaseAccent(String str) {
        str = str.replaceAll("[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]", "A");
        str = str.replaceAll("[ÈÉẸẺẼÊỀẾỆỂỄ]", "E");
        str = str.replaceAll("[ÌÍỊỈĨ]", "I");
        str = str.replaceAll("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]", "O");
        str = str.replaceAll("[ÙÚỤỦŨƯỪỨỰỬỮ]", "U");
        str = str.replaceAll("[ỲÝỴỶỸ]", "Y");
        str = str.replaceAll("[Đ]", "D");
        return str;
    }

    public static String removeLowerCaseAccent(String str) {
        str = str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
        str = str.replaceAll("[èéẹẻẽêềếệểễ]", "e");
        str = str.replaceAll("[ìíịỉĩ]", "i");
        str = str.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
        str = str.replaceAll("[ùúụủũưừứựửữ]", "u");
        str = str.replaceAll("[ỳýỵỷỹ]", "y");
        str = str.replaceAll("[đ]", "d");
        return str;
    }

    public static String currency(double money) {
        Locale locale = new Locale.Builder().setLanguage("vi").setRegion("VN").build();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(money);
    }

    public static String snakeCaseToCamelCase(String snakeCase) {
        StringBuilder camelCase = new StringBuilder();
        String[] parts = snakeCase.split("_");
        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                camelCase.append(parts[i]);
            } else {
                camelCase.append(Character.toUpperCase(parts[i].charAt(0)));
                camelCase.append(parts[i].substring(1));
            }
        }
        return camelCase.toString();
    }

    public static String camelCaseToSnakeCase(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char currentChar = camelCase.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                snakeCase.append('_').append(Character.toLowerCase(currentChar));
            } else {
                snakeCase.append(currentChar);
            }
        }
        return snakeCase.toString();
    }

    public static boolean containsUnicode(String str) {
        return str.chars().anyMatch(c -> c >= 128);
    }

    public static boolean containsUpperCase(String str) {
        return str.chars().anyMatch(Character::isUpperCase);
    }

    public static boolean containsLowerCase(String str) {
        return str.chars().anyMatch(Character::isLowerCase);
    }

    public static boolean containsAlphabet(String str) {
        return str.chars().anyMatch(Character::isAlphabetic);
    }

    public static boolean containsNumber(String str) {
        return str.chars().anyMatch(Character::isDigit);
    }

    public static boolean containsAlphaNumber(String str) {
        return str.matches(".*[a-zA-Z].*") && str.matches(".*\\d.*");
    }

    public static boolean containsSpecial(String str) {
        return str.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
    }

    public static boolean checkFormatPhone(String str) {
        return str.matches("^(\\+?84|0)[35789]\\d{8}$");
    }

    public static boolean checkFormatOfEmail(String str) {
        return str.matches("^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+");
    }

    public static boolean checkFormatDate(String str) {
        if (str.matches("^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])$"))
            return true;
        return str.matches("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}$");
    }

    public static boolean checkFormatDateTime(String str) {
        if (str.matches("^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) (\\d{2}:\\d{2}(?::\\d{2}(\\.\\d{1,6})?)?)?$"))
            return true;
        return str.matches("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4} (\\d{2}:\\d{2}(?::\\d{2}(\\.\\d{1,6})?)?)?$");
    }

    public static boolean checkRangeOfPercent(String str) {
        try {
            return Double.parseDouble(str) > 0 && Double.parseDouble(str) < 100;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean checkUnsignedNumber(String str){
        try {
            return Double.parseDouble(str) > 0;
        } catch (Exception e) {
            return true;
        }
    }


}

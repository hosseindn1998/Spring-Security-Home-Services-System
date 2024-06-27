package ir.hosseindn.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class CustomValidations {
    public static boolean isNotValidIranianNationalCode(String input) {
        if (!input.matches("^\\d{10}$")) {
            return true;
        } else {
            int check = Integer.parseInt(input.substring(9, 10));
            int sum = IntStream.range(0, 9).map((x) -> {
                return Integer.parseInt(input.substring(x, x + 1)) * (10 - x);
            }).sum() % 11;
            return sum < 2 ? check != sum : check + sum != 11;
        }
    }
    public static boolean isValidPathFile(String filaAddress) {

        String regex = "^[^\\s]+:\\\\+[^\\s]+\\.(jpg|jpeg)$";

        Pattern pattern = Pattern.compile(regex);
        if (filaAddress == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(filaAddress);

        return matcher.matches();
    }
}

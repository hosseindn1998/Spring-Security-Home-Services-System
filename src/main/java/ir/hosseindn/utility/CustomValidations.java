package ir.hosseindn.utility;

import java.util.stream.IntStream;

public class CustomValidations {
    public static boolean isValidIranianNationalCode(String input) {
        if (!input.matches("^\\d{10}$")) {
            return false;
        } else {
            int check = Integer.parseInt(input.substring(9, 10));
            int sum = IntStream.range(0, 9).map((x) -> {
                return Integer.parseInt(input.substring(x, x + 1)) * (10 - x);
            }).sum() % 11;
            return sum < 2 ? check == sum : check + sum == 11;
        }
    }
}

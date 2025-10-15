package calculator;

public class InputValidator {

    public static boolean isBlank(String input) {
        if (input.length() == 0){
            return true;
        }
        else {
            return false;
        }
    }
}

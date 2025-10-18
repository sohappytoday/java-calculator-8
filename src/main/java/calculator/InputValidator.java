package calculator;

import java.util.ArrayList;
import java.util.List;

public class InputValidator {

    public static void validateInput(String str) {
        // 빈칸이거나 null이면 IllegalArgumentException을 발생
        if (InputValidator.isBlankorNull(str)) {
            throw new IllegalArgumentException();
        }

        if (isNumeric(str)) {
            return;
        }

        // 기본연산자와 커스텀연산자를 가지고 있지 않으면 IllegalArgumentException을 발생
        if (!(InputValidator.existDefault(str) || InputValidator.existCustom(str))){
            throw new IllegalArgumentException();
        }

    }



    //입력 값이 빈 칸이 아닌지 확인하는 메서드
    private static boolean isBlankorNull(String input) {
        if (input == null || input.equals(""))
            return true;
        else
            return false;
    }

    //기본 연산자가 존재하는지 확인하는 메서드
    private static boolean existDefault(String input) {
        for (char c : input.toCharArray()) {
            if (c == ':' || c == ',') {
                return true;
            }
        }
        return false;
    }

    //커스텀 연산자가 존재하는지 확인하는 메서드
    public static boolean existCustom(String input){
        while (input.length()>4){
            if (input.startsWith("//") && input.contains("\\n"))
                return true;
            input = input.substring(1);
        }
        return false;
    }

    //숫자만 있는지 확인하는 메서드
    private static boolean isNumeric(String str) {
        List<String> numberList = List.of("0","1","2","3","4","5","6","7","8","9");

        for (int i = 0; i < str.length(); i++) {
            String ch = String.valueOf(str.charAt(i));
            if (!numberList.contains(ch)) {
                return false;
            }
        }
        return true;
    }
}

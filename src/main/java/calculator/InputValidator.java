package calculator;

public class InputValidator {

    //입력 값이 빈 칸이 아닌지 확인하는 메서드
    public static boolean isBlank(String input) {
        return input.isEmpty();
    }

    //기본 연산자가 존재하는지 확인하는 메서드
    public static boolean existDefault(String input) {
        for (char c : input.toCharArray()) {
            if (c == ':' || c == ',') {
                return true;
            }
        }
        return false;
    }

    //커스텀 연산자가 존재하는지 확인하는 메서드
    public static boolean existCustom(String input){
        return input.startsWith("//") && input.contains("\n");
    }
}

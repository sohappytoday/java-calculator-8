package calculator;

public class StringCalculator {

    public static int calculate(String str) {

        /**
         *  String이 존재하지 않으면 IllegalArgumentException을 반환합니다.
         */
        if (InputValidator.isBlank(str)) {
            throw new IllegalArgumentException();
        }

        return 0;
    }
}

package calculator;

public class StringCalculator {

    public static int calculate(String str) {

        /**
         *  예외 처리
         */

        // 빈칸이거나 null이면 IllegalArgumentException을 발생
        if (InputValidator.isBlank(str)) {
            throw new IllegalArgumentException();
        }

        // 기본연산자와 커스텀연산자를 가지고 있지 않으면 IllegalArgumentException을 발생
        if (!(InputValidator.existDefault(str) || InputValidator.existCustom(str))){
            throw new IllegalArgumentException();
        }

        return 0;
    }
}

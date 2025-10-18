package calculator;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        /**
         * 문자열 받기
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String str = sc.nextLine();

        int result = StringCalculator.calculate(str);
        System.out.println("결과 : "+result);
    }
}

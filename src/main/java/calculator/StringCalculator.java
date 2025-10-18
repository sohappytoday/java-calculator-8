package calculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static int calculate(String str) {

        int sum = 0;

        /**
         *  예외 처리
         */
        InputValidator.validateInput(str);

        /**
         *  연산자 추출 리스트 생성
         */
        List<String> delimiterList = new ArrayList<>(List.of(":", ","));
        List<String> customDelimiterList = InputParsing.extractCustom(str);
        delimiterList.addAll(customDelimiterList);
        /**
         * 예외 처리: custom 연산자가 default 연산자를 포함하는 것을 방지
         */
        delimiterList.sort((a, b) -> b.length() - a.length());

        /**
         *  숫자 추출
         */
        List<Integer> numberList = InputParsing.extractNumbers(str,delimiterList);

        //연산자가 잘 들어가는지 테스트용 코드
        System.out.println(delimiterList);

        for (int num : numberList){
            sum += num;
        }

        return sum;
    }
}

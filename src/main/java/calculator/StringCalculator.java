package calculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static int calculate(String str) {

        /**
         *  예외 처리
         */
        InputValidator.validateInput(str);

        /**
         *  연산자 추출 리스트 생성
         */
        List<String> delimiterList = new ArrayList<>();
        //커스텀 연산자 추출
        List<String> customDelimiterList = InputParsing.extractCustom(str);
        delimiterList.add(":");
        delimiterList.add(",");
        delimiterList.addAll(customDelimiterList);


        return 0;
    }
}

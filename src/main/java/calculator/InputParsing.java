package calculator;

import java.util.ArrayList;
import java.util.List;

public class InputParsing {

    public static List<String> extractCustom(String input){
        if(!InputValidator.existCustom(input)){
            return new ArrayList<>();
        }

        int index = 0;

        List<String> customDelimiterList = new ArrayList<>();

        while(index < input.length()) {
            // "//"로 시작하는 위치 찾기 + 2
            int start = input.indexOf("//",index) + 2;

            //더 이상 "//"가 존재하지 않음
            if (start - 2 == -1){
                break;
            }

            // "//"이후 "\n"으로 끝나는 위치 찾기
            int end = input.indexOf("\\n", start);

            //"//"는 존재하지만 "\n"이 존재하지 않으면 예외처리
            if(end == -1){
                //커스텀 연산자가 "//"임을 막기 위해
                if(!customDelimiterList.contains("//")) {
                    throw new IllegalArgumentException();
                }
                else{
                    break;
                }
            }

            String customDelimiter = input.substring(start, end);
            // "//"뒤에 "\n"이 바로 붙으면 예외처리
            if (customDelimiter.equals("")) {
                throw new IllegalArgumentException();
            }
            customDelimiterList.add(customDelimiter);

            // "\n" 이후부터 다시 시작
            index = end + 2;
        }
        return customDelimiterList;
    }
}

package calculator;

import java.util.ArrayList;
import java.util.List;

public class InputParsing {

    public static List<String> extractCustom(String input){
        int length = input.length();

        if(!InputValidator.existCustom(input)){
            return new ArrayList<>();
        }

        int index = 0;

        List<String> customDelimiterList = new ArrayList<>();

        while(index < length) {
            // "//"로 시작하는 위치 찾기 + 2
            int start = input.indexOf("//",index) + 2;

            //더 이상 "//"가 존재하지 않음
            if (start - 2 == -1){
                // "\n"이 존재하면 예외 처리
                if(input.indexOf("\n",index) != -1) {
                    throw new IllegalArgumentException();
                }
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

    public static List<Integer> extractNumbers(String input, List<String> delimiterList) {
        int index = 0;
        int length = input.length();

        List<Integer> numberList = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        //선언부인지 확인하는 boolean type
        boolean inDeclaration = false;

        while (index < length) {

            // "//" 발견 시 → 선언부인지 delimiter인지 구분
            if (index + 1 < length && input.startsWith("//", index)) {
                int endPos = input.indexOf("\\n", index + 2);

                // \n이 있다면 선언부
                if (endPos != -1 && endPos > index + 2) {
                    inDeclaration = true;
                    index += 2;
                    continue;
                }

                // //은 있지만 \n이 없다면 custom 연산자인지 검증
                else {
                    boolean isCustom = false;
                    for (String delimiter : delimiterList) {
                        if (input.startsWith(delimiter, index)) {
                            if (current.length() > 0) {
                                numberList.add(Integer.parseInt(current.toString()));
                                current.setLength(0);
                            }
                            index += delimiter.length();
                            isCustom = true;
                            break;
                        }
                    }
                    if (!isCustom){
                        throw new IllegalArgumentException();
                    }
                    continue;
                }
            }

            // 선언부 내부에서 \n 발견 → 선언부 종료
            if (inDeclaration &&
                    index + 1 < length &&
                    input.charAt(index) == '\\' &&
                    input.charAt(index + 1) == 'n') {
                inDeclaration = false;
                index += 2;
                continue;
            }

            // 숫자면 누적해서 StringBuilder에 추가
            char c = input.charAt(index);
            if (Character.isDigit(c)) {
                current.append(c);
                index++;
                continue;
            }

            // 숫자 이외의 문자가 왔을 시, 연산자인지 검증
            if (!inDeclaration) {
                //StringBuilder에 값이 들어있으면 numberList에 저장
                if (current.length() > 0) {
                    numberList.add(Integer.parseInt(current.toString()));
                    current.setLength(0);
                }

                boolean isValidDelimiter = false;
                for (String delimiter : delimiterList) {
                    if (input.startsWith(delimiter, index)) {
                        isValidDelimiter = true;
                        index += delimiter.length();
                        break;
                    }
                }

                if (!isValidDelimiter) {
                    throw new IllegalArgumentException();
                }

                continue;
            }

            index++;
        }

        // 마지막 숫자 처리
        if (current.length() > 0) {
            numberList.add(Integer.parseInt(current.toString()));
        }

        return numberList;
    }

}

package calculator;

import java.util.ArrayList;
import java.util.List;

public class InputParsing {

    public static List<String> extractCustom(String input){
        // (//)와 (\n)이 존재하지 않으면 빈 리스트 리턴
        if(!InputValidator.existCustom(input)){
            return new ArrayList<>();;
        }
        return;
    }
}

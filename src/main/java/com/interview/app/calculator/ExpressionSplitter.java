package com.interview.app.calculator;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
class ExpressionSplitter {

    public List<String> split(String input) {
        input = removeWhiteSpaces(input);

        List<String> list = new LinkedList<>();
        String temp = "";
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                temp += c;
                if (i + 1 == input.length() || (i + 1 < input.length() && !Character.isDigit(input.charAt(i + 1)))) {
                    list.add(temp);
                    temp = "";
                }
            } else {
                list.add(String.valueOf(c));
            }
            i++;
        }
        return list;
    }

    private String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }

}

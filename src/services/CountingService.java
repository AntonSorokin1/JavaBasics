package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountingService {
    public String findNumberFromEnd(String s) {
        Matcher matcher = Pattern.compile("\\d+$").matcher(s);
        return (matcher.find()) ? s.substring(matcher.start()) : "";
    }

    public String findNumberFromBegin(String s) {
        Matcher matcher = Pattern.compile("^\\d+").matcher(s);
        return (matcher.find()) ? s.substring(0, matcher.end()) : "";
    }

    public String solveSimpleEquation(String equation) {
        // Remove whitespace and init String Builder
        StringBuilder resultString = new StringBuilder();
        resultString.append(equation.replaceAll("\\s", ""));

        // Find all * and /
        Pattern highPattern = Pattern.compile("[*/]");
        while (resultString.toString().matches(".*[*/].*")) {
            Matcher matcher = highPattern.matcher(resultString);
            if (matcher.find()) {
                int index = matcher.start();
                int a = Integer.parseInt(findNumberFromEnd(resultString.substring(0, index)));
                int b = Integer.parseInt(findNumberFromBegin(resultString.substring(index + 1)));
                int result = (resultString.charAt(index) == '*') ? a * b : a / b;
                int sizeA = String.valueOf(a).length();
                int sizeB = String.valueOf(b).length();
                resultString.replace(index - sizeA, index + sizeB + 1, String.valueOf(result));
            }
        }

        // Find all + and -
        Pattern lowPattern = Pattern.compile("[-+]");
        while (resultString.toString().matches(".*[-+].*")) {
            Matcher matcher = lowPattern.matcher(resultString);
            if (matcher.find()) {
                int index = matcher.start();
                int a = Integer.parseInt(findNumberFromEnd(resultString.substring(0, index)));
                int b = Integer.parseInt(findNumberFromBegin(resultString.substring(index + 1)));
                int result = (resultString.charAt(index) == '+') ? a + b : a - b;
                int sizeA = String.valueOf(a).length();
                int sizeB = String.valueOf(b).length();
                resultString.replace(index - sizeA, index + sizeB + 1, String.valueOf(result));
            }
        }

        return resultString.toString();
    }

    public String solveEquation(String equation) {
        // Remove whitespace and init String Builder
        StringBuilder resultString = new StringBuilder();
        resultString.append(equation.replaceAll("\\s", ""));

        // Find all ( and )
        int openIndex;
        while ((openIndex = resultString.toString().lastIndexOf('(')) != -1) {
            int closeIndex = resultString.toString().substring(openIndex).indexOf(')') + openIndex;
            String result = solveSimpleEquation(resultString.substring(openIndex + 1, closeIndex));
            resultString.replace(openIndex, closeIndex + 1, result);
        }

        return solveSimpleEquation(resultString.toString());
    }
}
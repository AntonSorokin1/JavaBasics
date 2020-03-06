package services;

import exceptions.*;

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

    public String solveSimpleEquation(String equation) throws Exception {
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
                char action = resultString.charAt(index);
                if (action == '/' && b == 0)
                    throw new DivisionByZeroException();
                int result = (action == '*') ? a * b : a / b;
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

    public String solveEquation(String equation) throws Exception {
        // Remove whitespace and init String Builder
        StringBuilder resultString = new StringBuilder();
        resultString.append(equation.replaceAll("\\s", ""));

        if (resultString.toString().matches(".*[@#$%^&{};,.?!].*"))
            throw new ForbiddenSymbolsException();
        if (resultString.toString().matches(".*[-+*/][-+*/]+.*"))
            throw new MultipleActionException();

        // Find all ( and )
        int openIndex;
        while ((openIndex = resultString.toString().lastIndexOf('(')) != -1) {
            int closeIndex = resultString.toString().substring(openIndex).indexOf(')');
            if (closeIndex == -1)
                throw new CannotFindCloseBracketException();
            String result = solveSimpleEquation(resultString.substring(openIndex + 1, closeIndex + openIndex));
            resultString.replace(openIndex, closeIndex + openIndex + 1, result);
        }

        if (resultString.toString().indexOf(')') != -1)
            throw new CannotFindOpenBracketException();

        return solveSimpleEquation(resultString.toString());
    }
}
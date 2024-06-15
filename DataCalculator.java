package Calculator;

import java.util.List;

public class DataCalculator {

    public static String calculate(String input) throws Exception {
        String operation = getOperationSign(input);

        StringBuilder regex = new StringBuilder(" \\");
        regex.append(operation).append(" ");

        String[] split = input.split(regex.toString());
        if (split.length != 2)
            throw new Exception("Неверное выражение");

        String first = split[0];
        String second = split[1];

        String result = "";
        switch (operation) {

            case "+":
                if (!isString(first) || !isString(second))
                    throw new IllegalArgumentException("аргумент не строка");
                result = getStringWithoutQuoteMark(first).concat(getStringWithoutQuoteMark(second));
                break;

            case "-":
                if (!isString(first) || !isString(second))
                    throw new IllegalArgumentException("аргумент не строка");
                first = getStringWithoutQuoteMark(first);
                second = getStringWithoutQuoteMark(second);
                if (!first.contains(second)) {
                    result = first;
                } else
                    result = first.replace(second, "");
                break;

            case "*":
                if (!isString(first) || !isInteger(second))
                    throw new Exception(" Начинается не со строки либо число больше 10 ");
                first = getStringWithoutQuoteMark(first);
                for (int i = 0; i < parseInt(second); i++) {
                    result = result.concat(first);
                }
                break;

            case "/":
                if (!isString(first) || !isInteger(second))
                    throw new Exception(" Начинается не со строки либо число больше 10 ");
                first = getStringWithoutQuoteMark(first);
                result = first.substring(0, (first.length() / parseInt(second)));
                break;

            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        if (result.length() > 40)
            result = result.substring(0, 40).concat("...");

        return "\"" + result + "\"";
    }

    static String getOperationSign(String input) {
        List<String> operations = List.of(" - ", " + ", " * ", " / ");
        for (String op : operations) {
            if (input.contains(op)) {
                return op.trim();
            }
        }
        return "";
    }

    static String getStringWithoutQuoteMark(String str) {
        if (isString(str)) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    static boolean isString(String str) {
        return !str.isEmpty() && str.length() <= 12 && (str.charAt(0) == '\"' &&
                str.charAt(str.length() - 1) == '\"');
    }

    static boolean isInteger(String str) throws Exception {
        try {
            int num = Integer.parseInt(str);
            return num >= 0 && num <= 10;
        } catch (NumberFormatException e) {
            throw new Exception(" Не является целым числом либо число больше 10");
        }
    }

    static int parseInt(String str) {
        return Integer.parseInt(str);
    }
}






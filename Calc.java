package Generic;

import pac3Collection.Car;

import java.util.*;

public class Calc {
    static String[] rim = new String[]{"0s", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
            "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
            "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
            "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
            "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
            "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    //метод проверяющий введено ли число в формате римских чисел
    static int chekRim(String n) {
        int result = 0;
        for (int i = 0; i < rim.length; i++) {
            if (n.equals(rim[i])) {
                result++;
            }
        }
        return result;

    }

    //    Метод конвертирования строку в арабское число типа int
    static int convertToArabian(String s1) {
        int num = 0;

        for (int i = 0; i < rim.length; i++) {
            if (s1.equals(rim[i]) || s1.equals(String.valueOf(i))) {
                num = i;
            }

        }
        return num;
    }

    public static String calc(String input) throws Exception {
        //разделяем введенное выражение на операнды с которыми необходимо произвести действие
        String[] startArray = input.trim().split("[-+*/ ]");

        /*
        убираем пустые строки из массива на случай если
        выражение было указано с пробелами
         */
        List<String> nonEmptyarray = new ArrayList<>();
        for (String s : startArray
        ) {
            if (!s.isEmpty()) {
                nonEmptyarray.add(s);
            }
        }
        String[] s = nonEmptyarray.toArray(new String[0]);
        if (nonEmptyarray.size() > 2) {
            throw new RuntimeException("можно использовать только два операнда");
        }
        //получаем из массива значения выражения с которыми будем производить операцию

        String o1 = s[0];
        String o2 = s[1];

        int operand1 = convertToArabian(o1);
        int operand2 = convertToArabian(o2);

        //проверяем, что введенное значение не больше 10, иначе выбрасываем исключение
        if ((operand1 > 10 || operand1 < 0) || (operand2 < 0 || operand2 > 10)) {
            throw new RuntimeException("вы ввели неверное  число");
        }


        //определеяем введенную нужную операцию и записываем в переменную результат
        String result = "";
        if (input.contains("+")) {
            result = String.valueOf(operand1 + operand2);
        } else if (input.contains("-")) {
            result = String.valueOf(operand1 - operand2);
        } else if (input.contains("*")) {
            result = String.valueOf(operand1 * operand2);
        } else if (input.contains("/")) {
            result = String.valueOf(operand1 / operand2);
        } else throw new RuntimeException("вы ввели неверную операцию"); //если ввели не +-/* - бросаем исключение

        //Проверка является ли введенное число римским и является ли результат вычисления положительным числом
        if ((chekRim(o1) > 0) & (chekRim(o2) > 0)) {
            if (Integer.parseInt(result) > 0) {
                result = rim[Integer.parseInt(result)];
            } else throw new RuntimeException("результат операции с римским числом не может быть отрицательным");
            //проверка являются ли введенные числа одного формата
        } else if ((chekRim(o1) != 0 & chekRim(o2) == 0) | chekRim(o1) == 0 & chekRim(o2) != 0) {
            throw new RuntimeException("Используйте только один формат чисел");
            //проверка не выполняется ли операция с римским числом и 0
        }
        return result; //возвращаем результат

    }


    public static void main(String[] args) throws Exception {
        System.out.println("Введите пример. Используйте числа от 0 до 10 и операции - + * / без пробелов.");
        System.out.println(calc(new Scanner(System.in).nextLine()));
    }
}

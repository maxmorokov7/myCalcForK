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

    //����� ����������� ������� �� ����� � ������� ������� �����
    static int chekRim(String n) {
        int result = 0;
        for (int i = 0; i < rim.length; i++) {
            if (n.equals(rim[i])) {
                result++;
            }
        }
        return result;

    }

    //    ����� ��������������� ������ � �������� ����� ���� int
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
        //��������� ��������� ��������� �� �������� � �������� ���������� ���������� ��������
        String[] startArray = input.trim().split("[-+*/ ]");

        /*
        ������� ������ ������ �� ������� �� ������ ����
        ��������� ���� ������� � ���������
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
            throw new RuntimeException("����� ������������ ������ ��� ��������");
        }
        //�������� �� ������� �������� ��������� � �������� ����� ����������� ��������

        String o1 = s[0];
        String o2 = s[1];

        int operand1 = convertToArabian(o1);
        int operand2 = convertToArabian(o2);

        //���������, ��� ��������� �������� �� ������ 10, ����� ����������� ����������
        if ((operand1 > 10 || operand1 < 0) || (operand2 < 0 || operand2 > 10)) {
            throw new RuntimeException("�� ����� ��������  �����");
        }


        //����������� ��������� ������ �������� � ���������� � ���������� ���������
        String result = "";
        if (input.contains("+")) {
            result = String.valueOf(operand1 + operand2);
        } else if (input.contains("-")) {
            result = String.valueOf(operand1 - operand2);
        } else if (input.contains("*")) {
            result = String.valueOf(operand1 * operand2);
        } else if (input.contains("/")) {
            result = String.valueOf(operand1 / operand2);
        } else throw new RuntimeException("�� ����� �������� ��������"); //���� ����� �� +-/* - ������� ����������

        //�������� �������� �� ��������� ����� ������� � �������� �� ��������� ���������� ������������� ������
        if ((chekRim(o1) > 0) & (chekRim(o2) > 0)) {
            if (Integer.parseInt(result) > 0) {
                result = rim[Integer.parseInt(result)];
            } else throw new RuntimeException("��������� �������� � ������� ������ �� ����� ���� �������������");
            //�������� �������� �� ��������� ����� ������ �������
        } else if ((chekRim(o1) != 0 & chekRim(o2) == 0) | chekRim(o1) == 0 & chekRim(o2) != 0) {
            throw new RuntimeException("����������� ������ ���� ������ �����");
            //�������� �� ����������� �� �������� � ������� ������ � 0
        }
        return result; //���������� ���������

    }


    public static void main(String[] args) throws Exception {
        System.out.println("������� ������. ����������� ����� �� 0 �� 10 � �������� - + * / ��� ��������.");
        System.out.println(calc(new Scanner(System.in).nextLine()));
    }
}

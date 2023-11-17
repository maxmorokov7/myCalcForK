package Generic;

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

    //ìåòîä ïðîâåðÿþùèé ââåäåíî ëè ÷èñëî â ôîðìàòå ðèìñêèõ ÷èñåë
    static int chekRim(String n) {
        int result = 0;
        for (int i = 0; i < rim.length; i++) {
            if (n.equals(rim[i])) {
                result++;
            }
        }
        return result;

    }

    //    Ìåòîä êîíâåðòèðîâàíèÿ ñòðîêó â àðàáñêîå ÷èñëî òèïà int
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
        //ðàçäåëÿåì ââåäåííîå âûðàæåíèå íà îïåðàíäû ñ êîòîðûìè íåîáõîäèìî ïðîèçâåñòè äåéñòâèå
        String[] startArray = input.trim().split("[-+*/ ]");

        /*
        óáèðàåì ïóñòûå ñòðîêè èç ìàññèâà íà ñëó÷àé åñëè
        âûðàæåíèå áûëî óêàçàíî ñ ïðîáåëàìè
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
            throw new RuntimeException("ìîæíî èñïîëüçîâàòü òîëüêî äâà îïåðàíäà");
        }
        //ïîëó÷àåì èç ìàññèâà çíà÷åíèÿ âûðàæåíèÿ ñ êîòîðûìè áóäåì ïðîèçâîäèòü îïåðàöèþ

        String o1 = s[0];
        String o2 = s[1];

        int operand1 = convertToArabian(o1);
        int operand2 = convertToArabian(o2);

        //ïðîâåðÿåì, ÷òî ââåäåííîå çíà÷åíèå íå áîëüøå 10, èíà÷å âûáðàñûâàåì èñêëþ÷åíèå
        if ((operand1 > 10 || operand1 < 0) || (operand2 < 0 || operand2 > 10)) {
            throw new RuntimeException("âû ââåëè íåâåðíîå  ÷èñëî");
        }


        //îïðåäåëåÿåì ââåäåííóþ íóæíóþ îïåðàöèþ è çàïèñûâàåì â ïåðåìåííóþ ðåçóëüòàò
        String result = "";
        if (input.contains("+")) {
            result = String.valueOf(operand1 + operand2);
        } else if (input.contains("-")) {
            result = String.valueOf(operand1 - operand2);
        } else if (input.contains("*")) {
            result = String.valueOf(operand1 * operand2);
        } else if (input.contains("/")) {
            result = String.valueOf(operand1 / operand2);
        } else throw new RuntimeException("âû ââåëè íåâåðíóþ îïåðàöèþ"); //åñëè ââåëè íå +-/* - áðîñàåì èñêëþ÷åíèå

        //Ïðîâåðêà ÿâëÿåòñÿ ëè ââåäåííîå ÷èñëî ðèìñêèì è ÿâëÿåòñÿ ëè ðåçóëüòàò âû÷èñëåíèÿ ïîëîæèòåëüíûì ÷èñëîì
        if ((chekRim(o1) > 0) & (chekRim(o2) > 0)) {
            if (Integer.parseInt(result) > 0) {
                result = rim[Integer.parseInt(result)];
            } else throw new RuntimeException("ðåçóëüòàò îïåðàöèè ñ ðèìñêèì ÷èñëîì íå ìîæåò áûòü îòðèöàòåëüíûì");
            //ïðîâåðêà ÿâëÿþòñÿ ëè ââåäåííûå ÷èñëà îäíîãî ôîðìàòà
        } else if ((chekRim(o1) != 0 & chekRim(o2) == 0) | chekRim(o1) == 0 & chekRim(o2) != 0) {
            throw new RuntimeException("Èñïîëüçóéòå òîëüêî îäèí ôîðìàò ÷èñåë");
            //ïðîâåðêà íå âûïîëíÿåòñÿ ëè îïåðàöèÿ ñ ðèìñêèì ÷èñëîì è 0
        }
        return result; //âîçâðàùàåì ðåçóëüòàò

    }


    public static void main(String[] args) throws Exception {
        System.out.println("Ââåäèòå ïðèìåð. Èñïîëüçóéòå ÷èñëà îò 0 äî 10 è îïåðàöèè - + * / áåç ïðîáåëîâ.");
        System.out.println(calc(new Scanner(System.in).nextLine()));
    }
}

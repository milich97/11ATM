package edu.spbu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Миша on 05.09.2017.
 */
public class Main {
    static int numberOfVariants = 0;
    static ArrayList<Integer> denominations;
    static int[] usingOfDenominations;

    public static void main(String[] args) throws IOException {
        denominations = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input available denominations");
        String s = bufferedReader.readLine();
        if (isNumber(s)) {
            boolean adding;
            for (String val : s.split(" ")) {
                adding = true;
                int denomination = Integer.parseInt(val);              //значение номинала
                int counter = 0;
                while (counter < denominations.size()) {
                    if (denomination > denominations.get(counter))     //сортировка
                        counter++;
                    else {
                        if (denomination < denominations.get(counter)) break;
                        else {
                            adding = false;
                            break;
                        }                     //если равны, то не добавлять
                    }
                }
                if (adding) denominations.add(counter, denomination);
            }
            usingOfDenominations = new int[denominations.size()];
            System.out.println("Input note");
            int note = Integer.parseInt(bufferedReader.readLine());
            int lastEl = denominations.size() - 1;
            exchange(note, lastEl);
            if (numberOfVariants == 0) System.out.println("Impossible to exchange!");
            ;
        } else System.out.println("You need to enter the Integer positive numbers");
    }


    private static void exchange(int note, int lastEl) {
        if (lastEl == 0) {
            if (note % denominations.get(0) == 0) {
                numberOfVariants++;
                usingOfDenominations[0] = note / denominations.get(0);
                printf(usingOfDenominations);

            }
        } else {
            int maxNumberOfTheMostExpensiveNote = 1;
            while (maxNumberOfTheMostExpensiveNote * denominations.get(lastEl) <= note)
                maxNumberOfTheMostExpensiveNote++;
            maxNumberOfTheMostExpensiveNote--;

            for (int i = maxNumberOfTheMostExpensiveNote; i >= 0; i--) {
                usingOfDenominations[lastEl] = i;
                exchange(note - i * denominations.get(lastEl), lastEl - 1);
            }
        }

    }

    private static void printf(int[] usingOfDenominations) {
//        for (int i = 0; i < usingOfDenominations.length; i++) {
//            System.out.print(usingOfDenominations[i] + " ");
//        }
//        System.out.println();
//
        for (int i = 0; i < usingOfDenominations.length; i++) {
            for (int j = 0; j < usingOfDenominations[i]; j++) {
                if ((i == (usingOfDenominations.length - 1)) && (j == (usingOfDenominations[i] - 1)))
                    System.out.print(denominations.get(i));
                else System.out.print(denominations.get(i) + " ");
            }
        }
        System.out.println();
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != 32)) return false;
        }
        return true;
    }


}

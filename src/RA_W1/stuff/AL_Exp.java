package RA_W1.stuff;

import java.util.ArrayList;

public class AL_Exp {
    public static void main(String[] args) {
        ArrayList list;
        list = new ArrayList<Integer>();

        list.add(7);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Here is your " + i + " element of array: " + list.get(i));
        }


        list.add(8);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Here is your " + i + " element of array: " + list.get(i));
        }

        list.add(9);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Here is your " + i + " element of array: " + list.get(i));
        }



    }
}

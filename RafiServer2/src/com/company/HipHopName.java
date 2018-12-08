package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class HipHopName extends Name {

    //private String firstName;
    //private String lastName;


    String[] HipName1 = {"Lil", "Big", "Killa", "Shoog", "BangBang", "Stoner", "Diz", "Nuke", "Money", "CrackMaster"};
    String[] HipName2 = {"Bastard", "Dog", "Daddy", "Fresh", "Sleezer", "Boss", "Sniff", "Diesel", "God", "Crib"};

    public HipHopName(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public HipHopName(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public Name makeNewName() {
        char arr[]  = getFirstName().toCharArray();
        int[] intArray1 = new int[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            intArray1[i] = (int) arr[i];
            //System.out.println("names asci is"+ascii[i]);
        }

        int sum = 0;

        for (int i = 0; i < intArray1.length; i++) {
            sum += intArray1[i];


        }
        sum = sum % 10;


        int sum2 = 0;

        char arr2[] = getLastName().toCharArray();

        for (int i = 0; i < arr2.length; i++) {
            sum2 += arr2[i];

        }
        sum2 = sum2 % 10;


        //System.out.println("your HipHop Name is " + HipName1[sum] + " " + HipName2[sum2]);
        return new HipHopName(HipName1[sum], HipName2[sum2]);


    }

/*    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }*/
}

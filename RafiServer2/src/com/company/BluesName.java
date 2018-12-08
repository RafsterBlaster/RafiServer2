package com.company;

import java.io.IOException;
import java.io.InputStream;

public class BluesName extends Name{



    String[] BluesName1 = {"Fat", "Buddy", "Texas", "Pretty", "Jailhouse", "Skinny", "Blind", "Screaming", "Hollering", "Sticky"};
    String[] BluesName2 = {"Bones", "Money", "Hips", "Lips", "Mama", "Sugar", "Willy", "Smoke", "Dog", "Duke"};

    public BluesName(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public BluesName(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public Name makeNewName() {
        char arr[] = getFirstName().toCharArray();
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


        //System.out.println("your Blues Name is " + BluesName1[sum] + " " + BluesName2[sum2]);
        return new BluesName(BluesName1[sum], BluesName2[sum2]);


    }
/*
    @Override
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

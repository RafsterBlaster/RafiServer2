package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Name implements Writable{
    private String firstName;
    private String lastName;
    /*public Map<String, Integer> namez= new HashMap<>();*/



    /*String[] HipName1 = {"Lil", "Big", "Killa", "Shoog", "BangBang", "Stoner", "Diz", "Nuke", "Money", "CrackMaster"};
    String[] HipName2 = {"Bastard", "Dog", "Daddy", "Fresh", "Sleezer", "Boss", "Sniff", "Diesel", "God", "Crib"};

    String[] BluesName1 = {"Lil", "Big", "Killa", "Shoog", "BangBang", "Stoner", "Diz", "Nuke", "Money", "CrackMaster"};
    String[] BluesName2 = {"Bastard", "Dog", "Daddy", "Fresh", "Sleezer", "Boss", "Sniff", "Diesel", "God", "Crib"};
*/

    @Override
    public void write(OutputStream outputStream) throws IOException {
        byte[] contentBytes = firstName.getBytes();
        outputStream.write(contentBytes.length);
        outputStream.write(contentBytes);

        byte[] contentBytes1 = lastName.getBytes();
        outputStream.write(contentBytes1.length);
        outputStream.write(contentBytes1);



    }

    @Override
    public void read(InputStream inputStream) throws IOException {
        int actuallyRead;
        int stringLength = inputStream.read();
        if(stringLength == -1)
            throw new IOException("no message");
        byte[] contentBytes = new byte[stringLength];
        actuallyRead = inputStream.read(contentBytes);
        if(actuallyRead != stringLength)
            throw new IOException("message length error");
        firstName=new String(contentBytes);
        System.out.println(firstName);

        int actuallyRead1;
        int stringLength1 = inputStream.read();
        if(stringLength1 == -1)
            throw new IOException("no message");
        byte[] contentBytes1 = new byte[stringLength1];
        actuallyRead1 = inputStream.read(contentBytes1);
        if(actuallyRead1 != stringLength1)
            throw new IOException("not the expected number of bytes");
        lastName=new String(contentBytes1);
        System.out.println(lastName);


    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(InputStream inputStream) throws IOException {
        read(inputStream);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
       /* return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';*/
       return firstName+" "+lastName;
    }




}

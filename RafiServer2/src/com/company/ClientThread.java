package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.*;


public class ClientThread extends Thread{

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private static String content;
    int choice;
    boolean go=true;
    Map<String, Integer> namesMap= new HashMap<>();
    String listString= "Names user got so far this session are-\n";


    List<Name> namesList = new ArrayList<>();


    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try{




                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

            while (go) {


                choice = inputStream.read();

                System.out.println(choice);



                //sending chosen option to user
                switch (choice) {
                    case -1:
                        go=false;
                        return;
                    case 1:
                        case1();


                        break;
                    case 2:
                        case2();
                        break;

                    case 3:
                        content = "you chose to quit";
                        System.out.println("user chose" + content);
                        break;
                }

                //קבלת אובייקט מהקליינט

                switch (choice) {
                    case 1:

                        HipHopName name = new HipHopName(inputStream);
                        System.out.println("user sent object " + name.getFirstName());
                        //המרת האובייקט לשם חדש


                        HipHopName newName = (HipHopName) name.makeNewName();
                        System.out.println(newName);

                        //שליחת השם החדש לקליינט

                        sendMessage(outputStream, "your HipHop Name is " + newName.getFirstName() + " " + newName.getLastName());

                        //רישום השם בהאשמאפ

                        addNameToHashmap(newName, namesMap);

                        //הוספתהשםהחדש לרשימה
                        namesList.add(newName);

                    //System.out.println("name list is "+namesList);


                        //המשך פעילות מול הקליינט


                        choice = inputStream.read();
                        System.out.println("user chose option no. " + choice);

                        switch (choice) {
                            case 1:
                                /*ClientThread clientThread;*/
                                sendMessage(outputStream, "start over");
                                /*new ClientThread(socket).start();*/
                                break;

                            case 2:
                                Integer i = namesMap.get(String.valueOf(newName));

                                sendMessage(outputStream, (i - 1) + " users except you got this name this session");

                                break;

                            case 3:
                                String namesMapDisplay = namesMap.toString();
                                sendMessage(outputStream, namesMapDisplay);

                                break;

                            case 4:





                                for (Name s : namesList)
                                {
                                    listString += s + "\n";
                                }

                                /*String namesArrayListDisplay = namesList.toString();*/
                                sendMessage(outputStream, listString);




                                break;


                        }


                        break;
                    case 2:
                        BluesName name1 = new BluesName(inputStream);
                        System.out.println("user sent object " + name1);
                        //המרת האובייקט לשם חדש

                        BluesName newName1 = (BluesName) name1.makeNewName();
                        System.out.println(newName1);

                        //שליחת השם החדש לקליינט

                        sendMessage(outputStream, "Your Blues name is " + newName1.getFirstName() + " " + newName1.getLastName());


                        //רישום השם בהאשמאפ

                        addNameToHashmap(newName1, namesMap);
                        /*addNameToHashmap(newName1, newName1.namez);
                        System.out.println("namez is"+newName1.namez);*/

                        //הוספתהשםהחדש לרשימה
                        namesList.add(newName1);

                        //System.out.println("name list is "+namesList);


                        //המשך פעילות מול הקליינט


                        choice = inputStream.read();
                        System.out.println("user chose option no. " + choice);

                        switch (choice) {
                            case 1:


                                sendMessage(outputStream, "start over");

                                break;

                            case 2:
                                Integer i = namesMap.get(String.valueOf(newName1));

                                sendMessage(outputStream, (i - 1) + " users except you got this name this session");

                                break;




                            case 3:


                                String namesMapDisplay = namesMap.toString();
                                sendMessage(outputStream, namesMapDisplay);




                                break;

                            case 4:







                                for (Name s : namesList)
                                {
                                    listString += s + "\n";
                                }





                                /*String namesArrayListDisplay = namesList.toString();*/
                                sendMessage(outputStream, listString);




                                break;


                        }





                }

            }




        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    public static void addListToHashmap(List<Name> namesList, Map<String, Integer> namesMap){

        for (Name n:namesList
        ) {if(namesMap.containsKey(String.valueOf(n))){
            Integer count = namesMap.get(String.valueOf(n));
            namesMap.put(String.valueOf(n), count + 1);
        }else{
            namesMap.put(String.valueOf(n), 1);
        }

        }


    }

    public static void addNameToHashmap(Name n, Map<String, Integer> namesMap){



        synchronized (namesMap) {


            if (namesMap.containsKey(String.valueOf(n))) {
                Integer count = namesMap.get(String.valueOf(n));
                namesMap.put(String.valueOf(n), count + 1);
            } else {
                namesMap.put(String.valueOf(n), 1);
            }

        }

        /*Name xName= new Name(n.getFirstName(),n.getLastName());
        xName.namez.put(n.toString(),1);
        System.out.println("sffsf"+xName.namez);*/




        /*synchronized (namesMap) {


            if (namesMap.containsKey(String.valueOf(n))) {
                Integer count = namesMap.get(String.valueOf(n));
                namesMap.put(String.valueOf(n), count + 1);
            } else {
                namesMap.put(String.valueOf(n), 1);
            }

        }*/








    }



    public void sendInt(OutputStream outputStream,int num) throws IOException {

        byte[] buffer = new byte[4];
        ByteBuffer.wrap(buffer).putInt(num);
        outputStream.write(buffer);


    }

    public void readInt(InputStream inputStream) throws IOException {
        byte[] intBytes = new byte[4];
        int actuallyRead = inputStream.read(intBytes);
        if(actuallyRead != 4)
            throw new IOException("expected four bytes but received " + actuallyRead + " bytes..  :-(");
        int number = ByteBuffer.wrap(intBytes).getInt();
        System.out.println("you recevied the num"+number);
    }

    public static void sendMessage(OutputStream outputStream,String message) throws IOException {
        byte[] contentBytes = message.getBytes();
        outputStream.write(contentBytes.length);
        outputStream.write(contentBytes);
    }

    public static void readMessgae(InputStream inputStream) throws IOException{


        int actuallyRead;
        int stringLength = inputStream.read();
        if(stringLength == -1)
            throw new IOException("no message");
        byte[] contentBytes = new byte[stringLength];
        actuallyRead = inputStream.read(contentBytes);
        if(actuallyRead != stringLength)
            throw new IOException("sdfgdfg");
        content=new String(contentBytes);
        System.out.println(content);
    }


    private void case1() throws IOException {


        content="you chose hiphopname generator,now follow instructions";
        sendMessage(outputStream,content);
        outputStream.write(choice);





    }

    private void case2() throws IOException {


        content="you chose Bluesname generator,now follow instructions";
        sendMessage(outputStream,content);
        outputStream.write(choice);





    }

    /*public void arraylist2String(List<Name> namesList,String xxx){

        String[] stringArray = namesList.toArray(new String[namesList.size()]);
                                Arrays.sort(stringArray);
        for (int i = 0; i < stringArray.length-1; i++) {
            xxx+=" "+i;
            System.out.println("ffjfjffj is"+ xxx);

        }
                               // sendMessage(outputStream,stringArray.toString());






    }
*/








}

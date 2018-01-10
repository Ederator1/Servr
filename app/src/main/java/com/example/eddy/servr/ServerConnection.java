package com.example.eddy.servr;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerConnection {

    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static String temp;

    private static String output;
    private static ArrayList<ArrayList<String>> printedOutput;

    public static ArrayList<String> user;
    public static ArrayList<ArrayList<String>> userServices;
    public static ArrayList<ArrayList<String>> streamServices;

    public ServerConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        searchServices("Tester");
    }

    public void register(String user){
        listenSocket();
        try {
            out.println("register");
            out.println(user);
            System.out.println(in.readLine());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void login(String credentials){
        listenSocket();
        try {
            out.println("login");
            out.println(credentials);
            temp = in.readLine();
            if (temp.equals("null")){
                temp = null;
            } else {
                temp = temp.replace("[", "");
                temp = temp.replace(" ", "");
                temp = temp.replace("]", "");
                user = new ArrayList<>(Arrays.asList(temp.split(",")));
                getUserServices(Integer.parseInt(user.get(0)));
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void getUserServices(int userID){
        listenSocket();
        try {
            out.println("personal_services");
            out.println(userID);
            userServices = getNestedArray(in.readLine());
            System.out.println(userServices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchServices(String searchQuery){
        listenSocket();
        try{
            out.println("search_services");
            out.println(searchQuery);
            streamServices = getNestedArray(in.readLine());
            System.out.println(streamServices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listenSocket(){
        try{
            //@school: 10.178.155.72
            //@home: 192.168.2.13
            socket = new Socket("10.178.155.72", 8001);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println(Arrays.asList(e.getStackTrace()));
        }
    }

    private ArrayList<ArrayList<String>> getNestedArray(String inputArray){
        printedOutput = new ArrayList<>();
        output = inputArray.substring(1, inputArray.length() - 1);

        while (output.contains("]")){
            printedOutput.add(new ArrayList<>(Arrays.asList(output.substring(1, output.indexOf("]")).split(","))));
            if (output.indexOf("]") < output.length() - 1){
                output = output.substring(output.indexOf("]") + 2);
            } else {
                break;
            }
        }

        return printedOutput;
    }
}

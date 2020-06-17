package com.bridgelabz.addressbook.utils;
import java.util.Scanner;

/**
 * @Authpr:Amrut
 * this is the utility class where we use scanner object and use scanner object every class for input
 */
public class AddressBookUtil {
    private final static Scanner scanner = new Scanner(System.in);

    /*define default constructor and make it as private so that object cannot be created outside the class*/
    private AddressBookUtil(){

    }
    /*function to get number input from user*/
    public static int getUserNumber(){
        return scanner.nextInt();
    }
    /*function to get string input from user*/
    public static String getUserString(){
        return scanner.nextLine();
    }
}

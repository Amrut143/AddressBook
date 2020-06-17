package com.bridgelabz.addressbook.main;
import com.bridgelabz.addressbook.utils.AddressBookRepo;
import com.bridgelabz.addressbook.utils.AddressBookUtil;
import com.bridgelabz.addressbook.services.IAddressBookService;
import com.bridgelabz.addressbook.services.AddressBookServiceImpl;

/**
 *This is the main class contains main method and handles everyother packages and classes.
 *@author:Amrut
 */
public class AddressBookMain {
    public static void main(String[] args) {

        /*creating the implementation class object*/
        final IAddressBookService iAddressBookService = new AddressBookServiceImpl(new AddressBookRepo());
        System.out.println("Welcome to addressbook problem");

        iAddressBookService.addPerson();
        System.out.println("Contact Added::");
    }
}

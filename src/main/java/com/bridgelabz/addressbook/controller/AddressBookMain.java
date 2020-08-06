package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.strategy.CSVReadWriteStrategy;
import com.bridgelabz.addressbook.strategy.JSONReadWriteUsingGsonStrategy;
import com.bridgelabz.addressbook.strategy.SimpleJSONReadWriteStrategy;
import com.bridgelabz.addressbook.utils.AddressBookUtil;
import com.bridgelabz.addressbook.services.IAddressBookService;
import com.bridgelabz.addressbook.services.AddressBookServiceImpl;
import java.io.IOException;

/**
 * This is the main class contains main method and handles everyother packages and classes.
 *
 * @author:Amrut
 */
public class AddressBookMain {
    public static void main(String[] args) throws IOException {

        final IAddressBookService iAddressBookService = new AddressBookServiceImpl();
        System.out.println("Welcome to addressbook problem");

        System.out.println("\nEnter the file operation you want to perform: " +
                "\n1.SimpleJSONFile \n2.CSVFile \n3.JSONFileUsingGson \n4.DataBaseOperation \nEnter your option:: ");
        int choice = AddressBookUtil.getUserNumber();
        switch (choice) {
            case 1:
                iAddressBookService.loadDataFromSimpleJSON();
                break;
            case 2:
                iAddressBookService.loadDataFromCSVFile();
                break;
            case 3:
                iAddressBookService.loadDataFromGsonJSON();
                break;
            case 4:
                iAddressBookService.dbCRUDOperation();
                break;
            default:
                System.out.println("Invalid input, please enter valid input");
                break;
        }

        while (true) {
            System.out.println("View options and choose\n1.Add contact \n2.View contact \n3.Edit contact " +
                    "\n4.Delete contact \n5.Sort contact \n6.View by city and state " +
                    "\n7.Search by city or state \n8. Save Details & Quit " +
                    "\n9. Quit without saving \n Enter your option:: ");

            final int option = AddressBookUtil.getUserNumber();
            switch (option) {
                case 1:
                    iAddressBookService.addPerson();
                    System.out.println("Contact Added::");
                    break;
                case 2:
                    System.out.println("Displaying Contact::");
                    iAddressBookService.display();
                    break;
                case 3:
                    iAddressBookService.editPerson();
                    System.out.println("Contact Edited::");
                    break;
                case 4:
                    System.out.println("Delete Contact::");
                    iAddressBookService.deletePerson();
                    break;
                case 5:
                    System.out.println("Sort Contact::");
                    iAddressBookService.sort();
                    break;
                case 6:
                    System.out.println("View by city and state::");
                    iAddressBookService.viewByCityAndState();
                    break;
                case 7:
                    System.out.println("Search by city or state::");
                    iAddressBookService.findByCityOrState();
                    break;
                case 8:
                    iAddressBookService.saveDetails();
                    System.out.println("Quit.........");
                    System.exit(0);
                case 9:
                    System.out.println("Quiting......");
                    System.exit(0);
                default:
                    System.out.println("Invalid Entry");
            }
        }
    }
}

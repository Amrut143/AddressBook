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

        while(true) {
            System.out.println("View options and choose\n1.Add contact \n2.View contact \n3.Edit contact \n4.Delete contact \5.Sort contact");

            final int choice = AddressBookUtil.getUserNumber();
            switch (choice) {
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
                default:
                    System.out.println("Invalid Entry");
            }
        }
    }
}

package com.bridgelabz.addressbook.utils;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.services.AddressBookServiceImpl;
import java.util.List;
import java.util.ArrayList;

/**
 *this class add the data into Arraylist and display the data.
 *@author:Amrut
 */
public class AddressBookRepo {

    /*creating arraylist object to store the data*/
    public final List<Person> addressBook = new ArrayList<>();

    /*function to add the data into addressbook*/
    public void addToAddressBook(final Person person){
        addressBook.add(person);
        System.out.println("contact added into file");
    }

    /*function to display the contact details*/
    public void displayContact(){
        for(Person obj : addressBook){
            System.out.println("*****************");
            System.out.println("FirstName: "+obj.getFirstName());
            System.out.println("LastName: "+obj.getLastName());
            System.out.println("Address: "+obj.getAddress());
            System.out.println("City: "+obj.getCity());
            System.out.println("State: "+obj.getState());
            System.out.println("ZipCode: "+obj.getZipCode());
            System.out.println("Phone: "+obj.getPhone());
        }
    }
}

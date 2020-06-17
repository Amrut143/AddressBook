package com.bridgelabz.addressbook.services;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utils.AddressBookRepo;
import com.bridgelabz.addressbook.utils.AddressBookUtil;

/**
 *Creating implementation class for AddressBookService interface for method implementation
 *@author:Amrut
 */
public class AddressBookServiceImpl implements IAddressBookService {

    /*define addressbookrepo class as a variable*/
    final AddressBookRepo repo;

    public AddressBookServiceImpl(AddressBookRepo repo) {
        this.repo = repo;
    }

    @Override
    public void addPerson() {
        AddressBookUtil.getUserString();
        System.out.println("Enter First name: ");
        final String firstName = AddressBookUtil.getUserString();

        System.out.println("Enter Last name: ");
        final String lastName = AddressBookUtil.getUserString();

        System.out.println("Enter Address: ");
        final String address = AddressBookUtil.getUserString();

        System.out.println("Enter City: ");
        final String city =  AddressBookUtil.getUserString();

        System.out.println("Enter State: ");
        final String state = AddressBookUtil.getUserString();

        System.out.println("Enter Phone number: ");
        final String phone = AddressBookUtil.getUserString();

        System.out.println("Enter Zip code: ");
        final String zipCode = AddressBookUtil.getUserString();

        final Person person = new Person(firstName,lastName, address, city, state, phone, zipCode);
        repo.addToAddressBook(person);
    }

}

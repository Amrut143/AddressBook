package com.bridgelabz.addressbook.services;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utils.AddressBookRepo;
import com.bridgelabz.addressbook.utils.AddressBookUtil;

import java.util.List;
import java.util.stream.Collectors;

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
        removeDuplicate();

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

    @Override
    public void editPerson() {
        Person p = getPersonName();
        if(p != null ){
            System.out.print("Enter Address: ");
            final String address = AddressBookUtil.getUserString();
            p.setAddress(address);

            System.out.print("Enter City: ");
            final String city =  AddressBookUtil.getUserString();
            p.setCity(city);

            System.out.print("Enter State: ");
            final String state = AddressBookUtil.getUserString();
            p.setState(state);

            System.out.print("Enter Zip code: ");
            final String zipCode = AddressBookUtil.getUserString();
            p.setZipCode(zipCode);

            System.out.print("Enter Phone number: ");
            final String phone = AddressBookUtil.getUserString();
            p.setPhone(phone);
            saveChange();

        }else{
            System.out.println("data not found.");
        }
    }
    /*override delete method to delete the person details*/
    @Override
    public void deletePerson() {
        Person p = getPersonName();
        if(p != null ){
            repo.addressBook.remove(p);
            System.out.println("contact deleted");
            saveChange();
        }else{
            System.out.println("contact not found.");
        }

    }
    @Override
    public void display(){
        repo.displayContact();
    }
    /*override method to save the changes made*/
    @Override
    public void saveChange() {
        List<Person> addressBook = repo.addressBook;
    }
    public void removeDuplicate() {
        for(Person p : repo.addressBook) {
                /*using stream api*/
                List<Person> addressBook = repo.addressBook.stream().distinct().collect(Collectors.toList());
        }
    }

    private Person getPersonName(){
        AddressBookUtil.getUserString();
        display();
        System.out.print("Enter first name of person you want to edit or delete:: ");
        String firstName = AddressBookUtil.getUserString();
        Person p = findPerson(firstName);
        return p;
    }

    @Override
    public Person findPerson(String firstName){
        Person returnPerson = null;
        for(Person p : repo.addressBook){
            if(firstName.equals(p.getFirstName())){
                returnPerson = p;
                break;
            }
        }
        return returnPerson;
    }
}

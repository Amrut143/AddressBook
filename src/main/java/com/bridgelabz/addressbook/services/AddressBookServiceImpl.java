package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utils.AddressBookUtil;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creating implementation class for AddressBookService interface for method implementation
 *
 * @author:Amrut
 */
public class AddressBookServiceImpl implements IAddressBookService {

    /*creating arraylist object to store the data*/
    public List<Person> addressBook = new ArrayList<>();

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
        final String city = AddressBookUtil.getUserString();

        System.out.println("Enter State: ");
        final String state = AddressBookUtil.getUserString();

        System.out.println("Enter Phone number: ");
        final String phone = AddressBookUtil.getUserString();

        System.out.println("Enter Zip code: ");
        final String zipCode = AddressBookUtil.getUserString();

        final Person personDetails = new Person(firstName, lastName, address, city, state, phone, zipCode);
        addressBook.add(personDetails);
        addressBook = addressBook.stream()
                                 .distinct()
                                 .collect(Collectors.toList());

    }

    @Override
    public void editPerson() {
        Person person = getPersonName();
        if (person != null) {
            System.out.print("Enter Address: ");
            final String address = AddressBookUtil.getUserString();
            person.setAddress(address);

            System.out.print("Enter City: ");
            final String city = AddressBookUtil.getUserString();
            person.setCity(city);

            System.out.print("Enter State: ");
            final String state = AddressBookUtil.getUserString();
            person.setState(state);

            System.out.print("Enter Zip code: ");
            final String zipCode = AddressBookUtil.getUserString();
            person.setZipCode(zipCode);

            System.out.print("Enter Phone number: ");
            final String phone = AddressBookUtil.getUserString();
            person.setPhone(phone);
            saveChange();
        } else {
            System.out.println("data not found.");
        }
    }

    /*override delete method to delete the person details*/
    @Override
    public void deletePerson() {
        Person person = getPersonName();
        if (person != null) {
            addressBook.remove(person);
            System.out.println("contact deleted");
            saveChange();
        } else {
            System.out.println("contact not found.");
        }
    }

    @Override
    public void sort() {
        System.out.println("1. Sort by name");
        System.out.println("2. Sort by city");
        System.out.println("3. Sort by state");
        System.out.println("4. Sort by zip");
        System.out.print("Enter your choice: ");
        int choice = AddressBookUtil.getUserNumber();
        switch (choice) {
            case 1:
                addressBook.sort(Person.sortByName);
                display();
                break;
            case 2:
                addressBook.sort(Person.sortByCity);
                display();
                break;
            case 3:
                addressBook.sort(Person.sortByState);
                display();
                break;
            case 4:
                addressBook.sort(Person.sortByZip);
                display();
                break;
            default:
                System.out.println("Invalid input.");
        }
    }

    @Override
    public void display() {
        for (Person person : addressBook) {
            System.out.println("*****************");
            System.out.println("FirstName: " + person.getFirstName());
            System.out.println("LastName: " + person.getLastName());
            System.out.println("Address: " + person.getAddress());
            System.out.println("City: " + person.getCity());
            System.out.println("State: " + person.getState());
            System.out.println("ZipCode: " + person.getZipCode());
            System.out.println("Phone: " + person.getPhone());
        }
    }

    /*override method to save the changes made*/
    @Override
    public void saveChange() {

        List<Person> personList = addressBook;
    }

    /*override method viewByCityAndState*/
    @Override
    public void viewByCityAndState() {
        /*local variable to asking user for city and state*/
        AddressBookUtil.getUserString();
        System.out.println("Enter the city you want::");
        String city = AddressBookUtil.getUserString();
        System.out.println("Enter the state you want::");
        String state = AddressBookUtil.getUserString();
        List<Person> personList = new ArrayList<>();
        for (Person person : addressBook) {
            addressBook.stream()
                       .filter(persons ->
                               city.equalsIgnoreCase(person.getCity()) &&
                                       state.equalsIgnoreCase(person.getState()))
                       .forEach(personList::add);
                System.out.println(personList);
        }
    }

    @Override
    public void findByCityOrState() {
        List<Person> personList = new ArrayList<>();
        System.out.println("1. Search by city");
        System.out.println("2. Search by state");
        System.out.println("Enter your choice::");
        int choice = AddressBookUtil.getUserNumber();
        switch (choice) {
            case 1:
                AddressBookUtil.getUserString();
                System.out.println("Enter the city you want to search::");
                String city = AddressBookUtil.getUserString();
                for (Person person : addressBook) {
                    addressBook.stream()
                               .filter(persons -> person.getCity().equalsIgnoreCase(city))
                               .forEach(personList::add);
                    System.out.println(personList);
                }
                break;
            case 2:
                AddressBookUtil.getUserString();
                System.out.println("Enter the state you want to search::");
                String state = AddressBookUtil.getUserString();
                for (Person person : addressBook) {
                    addressBook.stream()
                               .filter(persons -> person.getState().equalsIgnoreCase(state))
                               .forEach(personList::add);
                    System.out.println(personList);
                }
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    private Person getPersonName() {
        AddressBookUtil.getUserString();
        display();
        System.out.print("Enter first name of person you want to edit or delete:: ");
        String firstName = AddressBookUtil.getUserString();
        Person person = findPerson(firstName);
        return person;
    }

    @Override
    public Person findPerson(String firstName) {
        Person returnPerson = addressBook.stream()
                                         .filter(person -> firstName.equals(person.getFirstName()))
                                         .findFirst()
                                         .orElse(null);
        return returnPerson;
    }
}

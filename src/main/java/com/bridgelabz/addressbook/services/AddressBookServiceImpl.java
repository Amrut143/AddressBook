package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utils.AddressBookUtil;
import org.json.simple.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creating implementation class for AddressBookService interface for method implementation
 *
 * @author:Amrut
 */
public class AddressBookServiceImpl implements IAddressBookService {

    public List<Person> addressBook = new ArrayList<>();
    JSONObject personDetails = new JSONObject();

    @Override
    public void addPerson() {
        AddressBookUtil.getUserString();
        System.out.println("Enter First name: ");
        final String firstName = AddressBookUtil.getUserString();
        personDetails.put("FirstName: ", firstName);

        System.out.println("Enter Last name: ");
        final String lastName = AddressBookUtil.getUserString();
        personDetails.put("LastName: ", lastName);

        System.out.println("Enter Address: ");
        final String address = AddressBookUtil.getUserString();
        personDetails.put("Address: ", address);

        System.out.println("Enter City: ");
        final String city = AddressBookUtil.getUserString();
        personDetails.put("City: ", city);

        System.out.println("Enter State: ");
        final String state = AddressBookUtil.getUserString();
        personDetails.put("State: ", state);

        System.out.println("Enter Phone number: ");
        final String phone = AddressBookUtil.getUserString();
        personDetails.put("PhoneNumber: ", phone);

        System.out.println("Enter Zip code: ");
        final String zipCode = AddressBookUtil.getUserString();
        personDetails.put("ZipCode: ", zipCode);

        final Person person = new Person(firstName, lastName, address, city, state, phone, zipCode);
        addressBook.add(person);
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
            saveDetails();
        } else {
            System.out.println("data not found.");
        }
    }

    @Override
    public void deletePerson() {
        Person person = getPersonName();
        if (person != null) {
            addressBook.remove(person);
            System.out.println("contact deleted");
            saveDetails();
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

            this.readPersonDetailsFromJson();
        }
    }

    @Override
    public void saveDetails() {
        this.writeIntoJson();
    }

    @Override
    public void viewByCityAndState() {
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

    private void writeIntoJson() {
        JSONObject personObject = new JSONObject();
        personObject.put("personDetails", personDetails);
        JSONArray personList = new JSONArray();
        personList.put(personObject);
        try (FileWriter file = new FileWriter("D:/FellowshipProgram/AddressBook/src/test/resources" +
                "/PersonAddressBook.json")) {
            file.write(personList.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parsePersonObject(JSONObject person) {
        JSONObject personObject = (JSONObject) person.get("personDetail");
        String firstName = (String) personObject.get("firstName");
        System.out.println(firstName);
        String lastName = (String) personObject.get("lastName");
        System.out.println(lastName);
        String address = (String) personObject.get("address");
        System.out.println(address);
        String city = (String) personObject.get("city");
        System.out.println(city);
        String phone = (String) personObject.get("phone");
        System.out.println(phone);
        String state = (String) personObject.get("state");
        System.out.println(state);
        String zip = (String) personObject.get("zip");
        System.out.println(zip);
    }

    private void readPersonDetailsFromJson() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("D:/FellowshipProgram/AddressBook/src/test/resources" +
                "/PersonAddressBook.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray personList = (JSONArray) object;
            System.out.println(personList);
            personList.forEach(person -> this.parsePersonObject((JSONObject) person));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

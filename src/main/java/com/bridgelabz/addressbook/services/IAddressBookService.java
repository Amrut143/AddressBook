package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;

import java.io.IOException;

/**
 *@author Amrut
 *creating an interface to define methods which are going to implement in the corresponding implementation class.
 */
public interface IAddressBookService {
    void addPerson();
    void editPerson();
    void deletePerson();
    void display();
    void saveDetails();
    void viewByCityAndState();
    Person findPerson(String firstName);
    void sort();
    void findByCityOrState();
    void loadDataFromSimpleJSON() throws IOException;
    void loadDataFromCSVFile() throws IOException;
    void loadDataFromGsonJSON() throws IOException;
}

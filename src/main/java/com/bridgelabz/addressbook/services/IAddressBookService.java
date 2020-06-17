package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;

/**
 *@author Amrut
 *creating an interface to define methods which are going to implement in the corresponding implementation class.
 */
public interface IAddressBookService {
    void addPerson();
    void editPerson();
    void deletePerson();
    void display();
    void saveChange();
    /*override method viewByCityAndState*/
    void viewByCityAndState();
    Person findPerson(String firstName);
    void sort();
    void findByCityOrState();
}

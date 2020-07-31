package com.bridgelabz.addressbook.models;

import java.util.Comparator;
import java.util.Objects;

/**
 *this is the person pojo class to define the required fields and setter getter methods
 *@author:Amrut
 */
public class Person {

    public String firstName;
    public String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;

    public Person(final String firstName, final String lastName, final String address, final String city, final String state, final String zipCode, final String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public Person() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    /*Sort element by name using comparator*/
    public static Comparator<Person> sortByName = new Comparator<Person>() {
        public int compare(Person firstPerson, Person secondPerson) {
            String name1 = firstPerson.getFirstName();
            String name2 = secondPerson.getFirstName();

            return name1.compareTo(name2);
        }
    };

    /*Sort element by city*/
    public static Comparator<Person> sortByCity = new Comparator<Person>(){
        public int compare(Person firstPerson, Person secondPerson){
            String city1 = firstPerson.getCity();
            String city2 = secondPerson.getCity();

            return city1.compareTo(city2);
        }
    };

    /*Sort element by State*/
    public static Comparator<Person> sortByState = new Comparator<Person>(){
        public int compare(Person firstPerson, Person secondPerson){
            String state1 = firstPerson.getState();
            String state2 = secondPerson.getState();

            return state1.compareTo(state2);
        }
    };

    /*Sort element by zipcode*/
    public static Comparator<Person> sortByZip = new Comparator<Person>(){
        public int compare(Person firstPerson, Person secondPerson){
            String zip1 = firstPerson.getZipCode();
            String zip2 = secondPerson.getZipCode();

            return zip1.compareTo(zip2);
        }
    };
}
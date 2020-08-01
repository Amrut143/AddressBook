package com.bridgelabz.addressbook.models;

import com.opencsv.bean.CsvBindByName;

import java.util.Comparator;
import java.util.Objects;

/**
 *this is the person pojo class to define the required fields and setter getter methods
 *@author:Amrut
 */
public class Person {

    @CsvBindByName(required = true, column = "FIRST NAME")
    private final String firstName;

    @CsvBindByName(required = true, column = "LAST NAME")
    private final String lastName;

    @CsvBindByName(required = true, column = "ADDRESS")
    private String address;

    @CsvBindByName(required = true, column = "CITY")
    private String city;

    @CsvBindByName(required = true, column = "STATE")
    private String state;

    @CsvBindByName(required = true, column = "MOBILE NO.")
    private String phone;

    @CsvBindByName(required = true, column = "ZIP")
    private String zipCode;

    public Person(String firstName, String lastName, String address, String city,
                  String state, String phone, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.zipCode = zipCode;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", zipCode='" + zipCode + '\'' +
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
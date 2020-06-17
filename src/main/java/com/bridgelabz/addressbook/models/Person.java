package com.bridgelabz.addressbook.models;

/**
 *this is the person pojo class to define the required fields and setter getter methods
 *@author:Amrut
 */
public class Person {
    /*data of a person*/
    private final String firstName;
    private final String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;

    /*constructor to initialize the data of persons*/
    public Person(final String firstName, final String lastName, final String address, final String city, final String state, final String zipCode, final String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
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
}

package com.bridgelabz.addressbook.strategy;

import com.bridgelabz.addressbook.models.Person;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONReadWriteUsingGsonStrategy extends Thread implements IFileReadWriteStrategy {

    @Override
    public synchronized void writeDataToFile(List<Person> addressBook, String filePath) {
        System.out.println("Write into json file using gson");
        String personDetail = new Gson().toJson(addressBook);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(personDetail);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized List<Person> readDataToList(String filePath) {
        System.out.println("Read from json file using gson");
        List<Person> addressBook = null;
        try {
            Person[] personDetails = new Gson().fromJson(new FileReader(filePath), Person[].class);
            addressBook = new ArrayList<>(Arrays.asList(personDetails));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    @Override
    public void run() {
        this.readDataToList("D:/FellowshipProgram/AddressBook/src/test/resources/GsonAddressBook.json");
        this.writeDataToFile(new ArrayList<>(), "D:/FellowshipProgram/AddressBook/src/test/resources/GsonAddressBook.json");
    }
}

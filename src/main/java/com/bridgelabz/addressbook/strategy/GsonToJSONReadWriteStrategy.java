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

public class GsonToJSONReadWriteStrategy implements IFileReadWriteStrategy {

    @Override
    public void writeDataToFile(List<Person> addressBook, String filePath) {
        String personDetail = new Gson().toJson(addressBook);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(personDetail);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> readDataToList(String filePath) {
        List<Person> addressBook = null;
        try {
            Person[] personDetails = new Gson().fromJson(new FileReader(filePath), Person[].class);
            addressBook = new ArrayList<>(Arrays.asList(personDetails));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return addressBook;
    }
}

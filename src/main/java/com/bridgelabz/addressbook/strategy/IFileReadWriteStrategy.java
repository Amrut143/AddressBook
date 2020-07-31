package com.bridgelabz.addressbook.strategy;

import com.bridgelabz.addressbook.models.Person;

import java.io.IOException;
import java.util.List;

public interface IFileReadWriteStrategy {

    List<Person> readDataToList(String filePath) throws IOException;

    void writeDataToFile(List<Person> addressBook, String filePath);
}

package com.bridgelabz.addressbook.strategy;

import com.bridgelabz.addressbook.models.Person;

import java.util.List;

public interface IFileReadWriteStrategy {

    List<Person> readDataToList(String filePath);

    void writeDataToFile(List<Person> addressBook, String filePath);
}

package com.bridgelabz.addressbook.strategy;

import com.bridgelabz.addressbook.models.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReadWriteStrategy extends Thread implements IFileReadWriteStrategy {

    @Override
    public synchronized List<Person> readDataToList(String filePath) throws IOException {
        System.out.println("Read from csv file");
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<Person> addressBook = new ArrayList<>();
            csvReader.readNext();
            String[] personData;
            while ((personData = csvReader.readNext()) != null) {
                addressBook.add(new Person(personData[2],
                        personData[3],
                        personData[0],
                        personData[1],
                        personData[5],
                        personData[6],
                        personData[4]));
            }
            return addressBook;
        }
    }

    @Override
    public synchronized void writeDataToFile(List<Person> addressBook, String filePath) {
        System.out.println("Write into csv file");
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(addressBook);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            this.readDataToList("D:/FellowshipProgram/AddressBook/src/test/resources/AddressBook.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.writeDataToFile(new ArrayList<>(), "D:/FellowshipProgram/AddressBook/src/test/resources/AddressBook.csv");
    }
}

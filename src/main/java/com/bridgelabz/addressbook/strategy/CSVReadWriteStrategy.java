package com.bridgelabz.addressbook.strategy;

import com.bridgelabz.addressbook.models.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReadWriteStrategy implements IFileReadWriteStrategy {

    @Override
    public List<Person> readDataToList(String filePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVReader csvReader = new CSVReader(reader)
        ) {
            List<Person> addressBook = new ArrayList<>();
            csvReader.readNext();
            String[] nextPerson;
            while ((nextPerson = csvReader.readNext()) != null) {
                addressBook.add(new Person(nextPerson[2],
                        nextPerson[3],
                        nextPerson[0],
                        nextPerson[1],
                        nextPerson[5],
                        nextPerson[6],
                        nextPerson[4]));
            }
            return addressBook;
        }
    }

    @Override
    public void writeDataToFile(List<Person> addressBook, String filePath) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(addressBook);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}

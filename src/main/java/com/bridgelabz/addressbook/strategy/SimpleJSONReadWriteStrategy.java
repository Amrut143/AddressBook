package com.bridgelabz.addressbook.strategy;

import com.bridgelabz.addressbook.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleJSONReadWriteStrategy extends Thread implements IFileReadWriteStrategy {

    @Override
    public synchronized void writeDataToFile(List<Person> addressBook, String jsonFilePath) {
        System.out.println("Write into simple json");
        JSONArray personList = new JSONArray();
        addressBook.forEach(person -> {
            JSONObject personDetails = new JSONObject();
            personDetails.put("First Name", person.getFirstName());
            personDetails.put("Last Name", person.getLastName());
            personDetails.put("Address", person.getAddress());
            personDetails.put("City", person.getCity());
            personDetails.put("State", person.getState());
            personDetails.put("Phone Number", person.getPhone());
            personDetails.put("Zipcode", person.getZipCode());

            JSONObject personObject = new JSONObject();
            personObject.put("person", personDetails);
            personList.add(personObject);
        });
        try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
            fileWriter.append(personList.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized List<Person> readDataToList(String jsonFilePath) {
        System.out.println("Read from simple json file");
        JSONParser jsonParser = new JSONParser();
        List<Person> addressBook = new ArrayList<>();
        try (FileReader fileReader = new FileReader(jsonFilePath)) {
            Object object = jsonParser.parse(fileReader);
            JSONArray personList = (JSONArray) object;
            personList.forEach(person -> addressBook.add(parsePersonObject((JSONObject) person)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    private Person parsePersonObject(JSONObject jsonObject) {
        JSONObject personObject = (JSONObject) jsonObject.get("person");
        return new Person((String) personObject.get("First Name"),
                (String) personObject.get("Last Name"),
                (String) personObject.get("Address"),
                (String) personObject.get("City"),
                (String) personObject.get("State"),
                (String) personObject.get("Phone Number"),
                (String) personObject.get("Zipcode"));
    }

    @Override
    public void run() {
        this.readDataToList("D:/FellowshipProgram/AddressBook/src/test/resources/PersonAddressBook.json");
        this.writeDataToFile(new ArrayList<>(), "D:/FellowshipProgram/AddressBook/src/test/resources/PersonAddressBook.json");
    }
}

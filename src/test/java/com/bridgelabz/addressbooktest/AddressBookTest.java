package com.bridgelabz.addressbooktest;

import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.services.AddressBookServiceImpl;
import com.bridgelabz.addressbook.services.IAddressBookService;
import com.bridgelabz.addressbook.strategy.SimpleJSONReadWriteStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class AddressBookTest {

    @Test
    public void givenFilePath_WhenReadFromFile_ShouldReturnPersonList() {
        List<Person> testPersonList = new ArrayList<>();
        Person testPerson = new Person("Amrut", "Panda", "silkboard", "blr", "ka", "6785904656", "457869");
        testPersonList.add(testPerson);
        SimpleJSONReadWriteStrategy mockService = Mockito.mock(SimpleJSONReadWriteStrategy.class);
        when(mockService.readDataToList("testFileName")).thenReturn(testPersonList);
        Assert.assertEquals(mockService.readDataToList("testFileName").size(), 1);
    }
}



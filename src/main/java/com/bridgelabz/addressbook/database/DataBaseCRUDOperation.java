package com.bridgelabz.addressbook.database;

import com.bridgelabz.addressbook.utils.AddressBookUtil;

import java.sql.*;

public class DataBaseCRUDOperation {

    private static final String INSERT_QUERY = "INSERT INTO PERSON(FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, " +
            "PHONENUMBER, ZIPCODE) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM PERSON";
    private static final String DELETE_QUERY = "DELETE FROM PERSON WHERE FIRSTNAME = ? AND LASTNAME = ?";
    private final Connection connection;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public DataBaseCRUDOperation() {
        connection = DBConnection.getConnection();
    }

    public void addPersonDetailsToDataBase() {
        try {
            AddressBookUtil.getUserString();
            System.out.println("Enter First name: ");
            final String firstName = AddressBookUtil.getUserString();

            System.out.println("Enter Last name: ");
            final String lastName = AddressBookUtil.getUserString();

            System.out.println("Enter Address: ");
            final String address = AddressBookUtil.getUserString();

            System.out.println("Enter City: ");
            final String city = AddressBookUtil.getUserString();

            System.out.println("Enter State: ");
            final String state = AddressBookUtil.getUserString();

            System.out.println("Enter Phone number: ");
            final String phone = AddressBookUtil.getUserString();

            System.out.println("Enter Zip code: ");
            final String zipCode = AddressBookUtil.getUserString();

            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, zipCode);

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                System.out.println("record not inserted");
            } else {
                System.out.println("record inserted");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPersonDetailsFromDataBase() {
        try {
            preparedStatement = connection.prepareStatement(SELECT_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String phone = resultSet.getString("phoneNumber");
                String zipCode = resultSet.getString("zipCode");

                System.out.println("FIRSTNAME: " + firstName + "  " + "LASTNAME: " + lastName + " " + "ADDRESS: " + address + "  "
                        + "CITY: " + city + "  " + "STATE: " + state + "  " + "ZIPCODE: " + zipCode + "  "
                        + "PHONENUMBER: " + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void editPersonDetails() {
        AddressBookUtil.getUserString();
        System.out.println("Enter first name of person you want to edit: ");
        String firstName = AddressBookUtil.getUserString();
        System.out.println("Enter last name of person you want to edit: ");
        String lastName = AddressBookUtil.getUserString();
        System.out.println("1.Edit address \n2.Edit city \n3.Edit state \n4.Edit phone number " +
                "\n5.Edit zip code");
        System.out.println("Enter your choice for edit person details::");


        int option = AddressBookUtil.getUserNumber();
        switch (option) {
            case 1:
                AddressBookUtil.getUserString();
                System.out.println("Enter Address: ");
                final String address = AddressBookUtil.getUserString();
                this.updatePersonDetailsByField(firstName, lastName, "address", address);
                break;
            case 2:
                AddressBookUtil.getUserString();
                System.out.println("Enter City: ");
                final String city = AddressBookUtil.getUserString();
                this.updatePersonDetailsByField(firstName, lastName, "city", city);

                break;
            case 3:
                AddressBookUtil.getUserString();
                System.out.println("Enter State: ");
                final String state = AddressBookUtil.getUserString();
                this.updatePersonDetailsByField(firstName, lastName, "state", state);

                break;
            case 4:
                AddressBookUtil.getUserString();
                System.out.println("Enter Phone number: ");
                final String phone = AddressBookUtil.getUserString();
                this.updatePersonDetailsByField(firstName, lastName, "phoneNumber", phone);

                break;
            case 5:
                AddressBookUtil.getUserString();
                System.out.println("Enter Zip code: ");
                final String zipCode = AddressBookUtil.getUserString();
                this.updatePersonDetailsByField(firstName, lastName, "zipCode", zipCode);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    private void updatePersonDetailsByField(String firstName, String lastName, String fieldName, String newValue) {
        String updateQuery = "UPDATE PERSON SET " + fieldName + " = '" + newValue + "' WHERE FIRSTNAME = '"
                + firstName + "' AND LASTNAME = '" + lastName + "'";
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(updateQuery);

            if (result == 0) {
                System.out.println("record not updated");
            } else {

                System.out.println("record updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePersonDetails() {
        try {
            AddressBookUtil.getUserString();
            System.out.println("Enter first name of person you want to delete: ");
            String firstName = AddressBookUtil.getUserString();
            System.out.println("Enter last name of person you want to delete: ");
            String lastName = AddressBookUtil.getUserString();

            preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                System.out.println("record not deleted");
            } else {

                System.out.println("record deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

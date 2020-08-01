package com.bridgelabz.addressbooktest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;


public class RESTAssuredAddressBookTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4000;
    }

    public Response getPersonList() {
        Response response = RestAssured.get("/person/list");
        return response;
    }

    @Test
    public void onCallingList_ReturnPersonList() {
        Response response = getPersonList();
        System.out.println("AT FIRST: " + response.asString());
        response.then().body("id", Matchers.hasItems(1, 2));
        response.then().body("firstName", Matchers.hasItems("Amrutnandan"));
    }

    @Test
    public void givenPerson_OnPost_ShouldReturnAddedPerson() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"firstName\": \"Deepak\", \"lastName\": \"Josi\", \"address\": \"patia\", " +
                        "\"city\": \"bbsr\", \"state\": \"odisha\", \"zipCode\": \"456737\"," +
                        "\"phone\": \"8967654398\"}")
                .when()
                .post("/person/create");
        String resAsStr = response.asString();
        JsonObject jsonObject = new Gson().fromJson(resAsStr, JsonObject.class);
        int id = jsonObject.get("id").getAsInt();
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("firstName", Matchers.is("Deepak"));
    }


    @Test
    public void givenPerson_OnUpdate_ShouldReturnUpdatedPerson() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"firstName\": \"Amrutnandan\", \"lastName\": \"Panda\", \"address\": \"silkboard\", " +
                        "\"city\": \"blr\", \"state\": \"ka\", \"zipCode\": \"457885\"," +
                        "\"phone\": \"7657447587\"}")
                .when()
                .put("/person/update/1");
        String resAsStr = response.asString();
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("firstName", Matchers.is("Amrutnandan"));
    }

    @Test
    public void givenPersonId_OnDelete_ShouldReturnSuccessStatus() {
        Response response = RestAssured.delete("/person/delete/5");
        String resAsStr = response.asString();
        int statusCode = response.getStatusCode();
        MatcherAssert.assertThat(statusCode, CoreMatchers.is(200));
        response = getPersonList();
        System.out.println("AT END: " + response.asString());
        response.then().body("id", Matchers.not(Integer.class));
    }
}

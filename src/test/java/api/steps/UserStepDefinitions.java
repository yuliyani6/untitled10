package api.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserStepDefinitions {

    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String APP_ID = "63a804408eb0cb069b57e43a"; // ganti dengan APP ID kamu jika berbeda

    private Response response;
    private static String userID;

    @Given("I have user details with first name {string}, last name {string}, and a unique email")
    public void iHaveUserDetails(String firstName, String lastName) {
        String uniqueEmail = firstName.toLowerCase() + "." + lastName.toLowerCase() + System.currentTimeMillis() + "@example.com";


        String requestBody = "{\"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName + "\", \"email\": \"" + uniqueEmail + "\" }";
        System.out.println("[DEBUG] Request Body: " + requestBody);

        response = RestAssured.given()
                .header("app-id", APP_ID)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/user/create");


        System.out.println("[DEBUG] Response: " + response.asString());
        System.out.println("[DEBUG] Status Code: " + response.getStatusCode());


        if (response.getStatusCode() != 200){
            throw new RuntimeException("User creation failed! Response: " + response.asString());
        }


        userID = response.jsonPath().getString("id");
        System.out.println("[CREATE] User ID Created: " + userID);
        Assert.assertNotNull("User ID should not be null after creation",userID);

    }

    @When("I send a request to create the user")
    public void iSendARequestToCreateTheUser() {
        String requestBody = "{\"firstName\": \"Bintang\", \"lastName\": \"Bersinar\", \"email\": \"bintang." + System.currentTimeMillis() + "@example.com\"}";

        response = RestAssured.given()
                .header("app-id", APP_ID)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/user/create");

        System.out.println("[DEBUG] Create User Response: " + response.asString());


        userID = response.jsonPath().getString("id");
    }


    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        response.then().statusCode(200);
        Assert.assertNotNull("user ID should not be null", userID);
        System.out.println("[SUCCESS] User Created with ID: " + userID);
    }

    @When("I send a POST request to {string}")
    public void iSendAPostRequestTo(String endpoint) {
        response = given()
                .header("app-id", APP_ID)
                .contentType("application/json")
                .when()
                .post(BASE_URL + endpoint);

        System.out.println("[POST] Request to: " + BASE_URL + endpoint);
        System.out.println("[DEBUG] Response: " + response.asString());
    }


    @When("I send a PUT request to {string}")
    public void iSendAPUTRequestTo(String endpoint) {
        String finalEndpoint = BASE_URL + endpoint.replace("{id}", userID);
        System.out.println("[PUT] Request to: " + finalEndpoint);

        response = given()
                .header("app-id", APP_ID)
                .contentType("application/json")
                .body("{ \"firstName\": \"Updated Name\", \"email\": \"updated_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com\" }")
                .when()
                .put(finalEndpoint);

        System.out.println("[DEBUG] Response: " + response.asString());
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the user ID")
    public void theResponseShouldContainTheUserId() {
        userID = response.jsonPath().getString("id");
        Assert.assertNotNull("User ID should be present in response", userID);
        System.out.println("[DEBUG] User ID: " + userID);
    }

    @Then("I save the user ID for later use")
    public void iSaveTheUserIdForLaterUse() {
        Assert.assertNotNull("User ID must be saved", userID);
    }

    //Get User
    @Given("I have the saved user ID")
    public void iHaveTheSavedUserID() {
        System.out.println("[CHECK] Current User ID: " + userID);
        Assert.assertNotNull("User ID must be saved before this step", userID);
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        String finalEndpoint = BASE_URL + endpoint.replace("{id}", userID);
        System.out.println("[GET] Request to: " + finalEndpoint);

        response = given()
                .header("app-id", APP_ID)
                .when()
                .get(finalEndpoint);
    }

    @And("the response should contain the user details")
    public void theResponseShouldContainTheUserDetails() {
        response.then().statusCode(200)
                .body("id", equalTo(userID))
                .body("firstName", notNullValue())
                .body("email", notNullValue());
    }
    @Given("I have a valid user ID")
    public void i_have_valid_user_id() {
        assert userID != null : "User ID not initialized";
    }

    @When("I send a PUT request to update the user with:")
    public void i_send_put_request(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMap(String.class, String.class);
        JSONObject updatedData = new JSONObject(data);

        response = given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(updatedData.toString())
                .when()
                .put(BASE_URL + "/user/" + userID);
    }



    @Then("the response status code should be 200")
    public void verify_put_status_code() {
        response.then().statusCode(200);
    }
    @And("the response should contain the updated user details")
    public void verify_updated_user_details() {
        response.then()
                .body("id", equalTo(userID))
                .body("firstName", notNullValue())
                .body("lastName", notNullValue())
                .body("email", notNullValue());
    }


    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        String finalEndpoint = BASE_URL + endpoint.replace("{id}", userID);
        System.out.println("[DELETE] Request to: " + finalEndpoint);

        response = given()
                .header("app-id", APP_ID)
                .when()
                .delete(finalEndpoint);

        response.then().statusCode(200);
        System.out.println("[DEBUG] Delete Response: " + response.asString());
    }

    @Then("the user should no longer exist")
    public void theUserShouldNoLongerExist() throws InterruptedException {
        //tunggu sebelum get untuk memastikan user sudah terhapus
        Thread.sleep(2000);

        given()
                .header("app-id", APP_ID)
                .when()
                .get(BASE_URL + "/user/" + userID)
                .then()
                .statusCode(404);

        System.out.println("[CHECK] User successfully deleted.");
    }
}

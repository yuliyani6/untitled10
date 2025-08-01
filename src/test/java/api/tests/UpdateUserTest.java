package api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseTest {

    @Test
    public void updateUser (){
        String userId = "6888bbdae181cd857f467528"; // Ganti dengan ID user yang valid

        //Set Base URI secara explicit agar import RestAssured digunakan
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        //Membuat JSON Payload untuk update user
        JSONObject updateData = new JSONObject();
        updateData.put("firstName", "Michael");
        updateData.put("lastName", "Douglas");

        //kirim request PUT untuk memperbarui user
        Response response = given()
                .header("app-id", "63a804408eb0cb069b57e43a") // Tambahkan app-id untuk autentikasi
                .contentType("application/json")
                .body(updateData.toString())
                .when()
                .put("/user/" + userId)
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Michael"))
                .body("lastName", equalTo("Douglas"))

                .extract().response();

        System.out.println("user updated Response: " + response.prettyPrint());


    }
}
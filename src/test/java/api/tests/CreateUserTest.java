package api.tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserTest {

    @Test
    public void testCreateUser() {
        // Data user
        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", "Yuliyani");
        userData.put("lastName", "Aprilyani");
        userData.put("email", "yuli.yani1753777394552@example.com"); // Email harus unik

        // Kirim POST request
        Response response = RestAssured.given()
                .baseUri("https://dummyapi.io")
                .basePath("/data/v1/user/create")
                .header("app-id", "63a804408eb0cb069b57e43a") // GANTI dengan App ID kamu dari dummyapi.io
                .contentType(ContentType.JSON)
                .body(userData)
                .post();

        // Validasi status code
        assertEquals(200, response.getStatusCode());

        // Validasi nama depan di response
        String firstName = response.jsonPath().getString("firstName");
        assertEquals("Yuliyani", firstName);

        // Cetak ID user (opsional)
        System.out.println("User ID: " + response.jsonPath().getString("id"));
    }
}

package api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {

    @Test
    public void deleteUser(){
        //ganti dengan id user yang valid dari hasil create user
        String userID = "60d0fe4f5311236168a109ca";

        //set base URI RestAssured
        RestAssured.baseURI = "https://dummyapi.io/data/v1";


        Response checkUser = given()
                .header("app-id", "63a804408eb0cb069b57e43a") //App ID valid
                .when()
                .get("/user/" +  userID)
                .then()
                .extract().response();

        System.out.println("Check User Response: " + checkUser.getBody().asPrettyString());

        //jika user tidak ditemukan (404), tidak perlu lanjut delete
        if (checkUser.statusCode() == 400) {
            System.out.println("user tidak ditemukan atau sudah dihapus sebelumnya.");
            // stop eksekusikarena user sudah tidak ada

        }



    }
}

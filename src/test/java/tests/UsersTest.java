package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UsersTest {

    @Test
    public void getUsersTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",10);

        Response response = given()
                .baseUri("https://dummyapi.io/data")
                .basePath("/v1/")
                .header("app-id", "675338db00236f7d1c311b6f")
                .queryParams(map)
                .log().all()
                .when().get(Constants.GET_ALL_USERS);

        Assert.assertEquals(response.getStatusCode(),200, "Expected 200 but got: "+response.getStatusCode());
        String actualFirstName = response.jsonPath().get("data[0].firstName");
        System.out.println(actualFirstName);
        Assert.assertEquals(actualFirstName,"Edita");

    }

    @Test
    public void getUsersUsingJsonPathTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",10);

        JsonPath jsonPath  = given()
                .baseUri("https://dummyapi.io/data")
                .basePath("/v1/")
                .header("app-id", "675338db00236f7d1c311b6f")
                .queryParams(map)
                .log().all()
                .when().get(Constants.GET_ALL_USERS).jsonPath();


        String actualFirstName = jsonPath.getString("data[0].firstName");
        boolean result = actualFirstName.equals("Edita");
        Assert.assertTrue(result,"Expected first name is not correct!");

    }

    @Test
    public void getUsersByIdTest() {
        String userId = "60d0fe4f5311236168a109ca";
        Response response = given()
                .baseUri("https://dummyapi.io/data")
                .basePath("/v1/")
                .header("app-id", "675338db00236f7d1c311b6f")
                .pathParam("id",userId)
                .log().all()
                .when().get(Constants.GET_ALL_USERS_BY_ID);

        Assert.assertEquals(response.getStatusCode(),200, "Expected 200 but got: "+response.getStatusCode());
        String actualFirstName = response.jsonPath().get("data[0].firstName");
        System.out.println(actualFirstName);
        Assert.assertEquals(actualFirstName,"Edita");

    }
}

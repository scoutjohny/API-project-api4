package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
                .when().get("user");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200, "Expected 200 but got: "+response.getStatusCode());
        softAssert.assertAll();
        Assert.assertEquals(response.getStatusCode(),200, "Expected 200 but got: "+response.getStatusCode());
        String actualFirstName = response.jsonPath().get("data[0].firstName");
        System.out.println(actualFirstName);
        Assert.assertEquals(actualFirstName,"Edita");

    }
}

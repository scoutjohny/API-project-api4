package tests;

import config.Config;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import listeners.TestListener;
import models.userModel.UserRequest;
import models.userModel.UserResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.Constants.CREATE_USER;
import static utils.Utils.createJsonFile;
@Listeners (TestListener.class)

public class UsersTest extends Config {

    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        softAssert = new SoftAssert();
    }
    @Test
    public void getUsersTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",10);

        Response response = given()
                .queryParams(map)
                .when().get(Constants.GET_ALL_USERS);

        Assert.assertEquals(response.getStatusCode(),200, "Expected 200 but got: "+response.getStatusCode());
        String actualFirstName = response.jsonPath().get("data[0].firstName");
        System.out.println(actualFirstName);
        Assert.assertEquals(actualFirstName,"Roberto");

    }

    @Test
    public void getUsersUsingJsonPathTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",10);

        JsonPath jsonPath  = given()
                .queryParams(map)
                .when().get(Constants.GET_ALL_USERS).jsonPath();


        String actualFirstName = jsonPath.getString("data[0].firstName");
        boolean result = actualFirstName.equals("Roberto");
        Assert.assertTrue(result,"Expected first name is not correct!");

    }

    @Test
    public void getUsersByIdTest() {
        String userId = "60d0fe4f5311236168a109cd";
        Response response = given()
                .pathParam("id",userId)
                .when().get(Constants.GET_USER_BY_ID);

        Assert.assertEquals(response.getStatusCode(),200, "Expected 200 but got: "+response.getStatusCode());
        String actualFirstName = response.jsonPath().get("firstName");
        System.out.println(actualFirstName);
        Assert.assertEquals(actualFirstName,"Roberto");

    }

    @Test
    public void deleteUsersByIdTest() {
        String userId = "60d0fe4f5311236168a109cc";
        Response response = given()
                .pathParam("id",userId)
                .when().delete(Constants.DELETE_USER_BY_ID);

        Assert.assertEquals(response.getStatusCode(),200, "Expected 200 but got: "+response.getStatusCode());
        String id = response.jsonPath().get("id");
        System.out.println(id);

        Assert.assertEquals(id,userId);

        given()
                .pathParam("id",userId)
                .when().delete(Constants.GET_USER_BY_ID);
        Assert.assertEquals(response.getStatusCode(),404, "Expected 404 but got: " +response.getStatusCode());

    }

    @Test
    public void createUserTest(){

//        Response response = given()
//                .body("{\n" +
//                        "    \"firstName\": \"Sarita\",\n" +
//                        "    \"lastName\": \"Anderson\",\n" +
//                        "    \"picture\": \"https://randomuser.me/api/portraits/women/58.jpg\",\n" +
//                        "    \"gender\": \"female\",\n" +
//                        "    \"email\": \"sarita.anderson9763@example.com\",\n" +
//                        "    \"dateOfBirth\": \"1996-04-30T19:26:49.610Z\",\n" +
//                        "    \"phone\": \"92694011\",\n" +
//                        "    \"location\": {\n" +
//                        "        \"street\": \"9614, SÃ¸ndermarksvej\",\n" +
//                        "        \"city\": \"Kongsvinger\",\n" +
//                        "        \"state\": \"Nordjylland\",\n" +
//                        "        \"country\": \"Denmark\",\n" +
//                        "        \"timezone\": \"-9:00\"\n" +
//                        "    }\n" +
//                        "}")
//                .when().post(CREATE_USER);
//
//        Assert.assertEquals(response.getStatusCode(),200);

        UserRequest user = UserRequest.createUser();

        UserResponse userResponse = given()
                .body(user)
                .when().post(CREATE_USER)
                .getBody().as(UserResponse.class);

        String userId = userResponse.getId();

        softAssert.assertEquals(userResponse.getFirstName(),user.getFirstName());
        softAssert.assertEquals(userResponse.getLastName(),user.getLastName());
        softAssert.assertEquals(userResponse.getPicture(),user.getPicture());
        softAssert.assertAll();
    }

    @Test
    public void createUserUsingJavaObjectTest(){
        UserRequest user = UserRequest.createUser();

        createJsonFile("userRequest",user);

        UserResponse userResponse = given()
                .body(user)
                .when().post(CREATE_USER)
                .getBody().as(UserResponse.class);

        String userId = userResponse.getId();

        softAssert.assertEquals(userResponse.getFirstName(),user.getFirstName());
        softAssert.assertEquals(userResponse.getLastName(),user.getLastName());
        softAssert.assertEquals(userResponse.getPicture(),user.getPicture());
        softAssert.assertAll();
    }
}
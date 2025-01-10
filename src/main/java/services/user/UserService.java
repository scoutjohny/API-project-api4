package services.user;

import io.restassured.response.Response;
import models.userModel.UserRequest;

import static io.restassured.RestAssured.given;
import static utils.Constants.CREATE_USER;

public class UserService {

    public static final String GET_ALL_USERS = "user";

    public Response get(){
        UserRequest user = UserRequest.createUser();
        return given()
                .body(user)
                .when().post(CREATE_USER);
    }
}

package services.user;

import api.ApiClient;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.userModel.UserLocation;
import models.userModel.UserRequest;
import models.userModel.UserResponse;

import java.time.LocalDateTime;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static utils.Constants.CREATE_USER;

public class UserService {

    public static final String GET_ALL_USERS = "user";

    private ApiClient apiClient = new ApiClient();

    public static UserRequest createUserTemplate(){
        Faker faker = new Faker(new Locale("en-US"));

        UserLocation location = UserLocation.builder()
                .street(faker.address().streetAddress())
                .city(faker.address().cityName())
                .state(faker.address().state())
                .country(faker.address().country())
                .build();

        return UserRequest.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .picture(faker.internet().image(faker.number().randomDigitNotZero(),faker.number().randomDigitNotZero(),faker.random().nextBoolean(),faker.number().randomDigitNotZero()+".jpg"))
                .gender(faker.demographic().sex().toLowerCase())
                .email(faker.internet().emailAddress())
                .dateOfBirth(String.valueOf(LocalDateTime.of(faker.number().numberBetween(1945,2000),faker.number().numberBetween(1,12),faker.number().numberBetween(1,28),faker.number().numberBetween(0,23),faker.number().numberBetween(0,59),faker.number().numberBetween(0,59))))
                .phone(faker.phoneNumber().cellPhone())
                .location(location)
                .build();
    }

    public UserResponse createUser(UserRequest user){
        return apiClient.post(CREATE_USER, user).getBody().as(UserResponse.class);
    }
//    public Response get(){
//        UserRequest user = UserRequest.createUser();
//        return given()
//                .body(user)
//                .when().post(CREATE_USER);
//    }
}

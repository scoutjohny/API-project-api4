package models.userModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With

public class UserRequest {

    private String firstName;
    private String lastName;
    private String picture;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String phone;
    private UserLocation location;

//    public UserRequest(String firstName, String lastName, String picture, String gender, String email, String dateOfBirth, String phone, UserLocation location){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.picture = picture;
//        this.gender = gender;
//        this.email = email;
//        this.dateOfBirth = dateOfBirth;
//        this.phone = phone;
//        this.location = location;
//    }

//    public  UserRequest(){
//
//    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPicture() {
//        return picture;
//    }
//
//    public void setPicture(String picture) {
//        this.picture = picture;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(String dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public UserLocation getLocation() {
//        return location;
//    }
//
//    public void setLocation(UserLocation location) {
//        this.location = location;
//    }

    public static UserRequest createUser(){
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
}

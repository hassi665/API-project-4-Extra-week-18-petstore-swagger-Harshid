package io.petstore.swagger.petanduserinfo;

import io.petstore.swagger.constants.EndPoints;
import io.petstore.swagger.model.UserBodyDataPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class UserSteps {

    @Step("Creating user with id : {0}, username {1}, firstname : {2}, lastName: {3}, email: {4}, password: {5} and phone: {6}")
    public ValidatableResponse createUser(int id, String username, String firstname, String lastname, String email,
                                             String password, String phone,  int userStatus) {

        UserBodyDataPojo userBodyDataPojo = new UserBodyDataPojo();
        userBodyDataPojo.setId(id);
        userBodyDataPojo.setUsername(username);
        userBodyDataPojo.setFirstname(firstname);
        userBodyDataPojo.setLastname(lastname);
        userBodyDataPojo.setEmail(email);
        userBodyDataPojo.setPassword(password);
        userBodyDataPojo.setPhone(phone);
        userBodyDataPojo.setUserStatus(userStatus);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(userBodyDataPojo)
                .when()
                .post("/user")
                .then().log().all().statusCode(200);
    }

    @Step("Getting the user information with username: {0}")
    public HashMap<String, Object> getUserInfoByUserName(String username) {
        String p1 = "findAll{it.username == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_USERNAME)
                .then()
                .extract()
                .path(p1 + username + p2);
    }

    @Step("Updating user information with Username where user id : {0}, username {1}, firstname : {2}, lastName: {3}, email: {4}, password: {5} and phone: {6}")
    public ValidatableResponse updateUser(int id, String username, String firstname, String lastname, String email,
                                             String password, String phone, int userStatus) {

        UserBodyDataPojo userBodyDataPojo = new UserBodyDataPojo();
        userBodyDataPojo.setId(id);
        userBodyDataPojo.setUsername(username);
        userBodyDataPojo.setFirstname(firstname);
        userBodyDataPojo.setLastname(lastname);
        userBodyDataPojo.setEmail(email);
        userBodyDataPojo.setPassword(password);
        userBodyDataPojo.setPhone(phone);
        userBodyDataPojo.setUserStatus(userStatus);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("username", username)
                .body(userBodyDataPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_USERNAME)
                .then();
    }

    @Step("Deleting user information with userId: {0}")
    public ValidatableResponse deleteUser(String username) {
        return SerenityRest.given().log().all()
                .pathParam("username", username )
                .when()
                .delete(EndPoints.DELETE_USER_BY_USERNAME)
                .then();
    }

    @Step("Getting user information with username: {0}")
    public ValidatableResponse getUserByUsername(String username){
        return SerenityRest.given().log().all()

                .pathParam("username", username)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_USERNAME)
                .then().log().all();
    }
}

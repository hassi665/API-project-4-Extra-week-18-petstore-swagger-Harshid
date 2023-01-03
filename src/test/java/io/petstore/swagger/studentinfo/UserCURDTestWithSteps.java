package io.petstore.swagger.studentinfo;

import io.petstore.swagger.petanduserinfo.UserSteps;
import io.petstore.swagger.testbase.TestBase;
import io.petstore.swagger.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.log;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCURDTestWithSteps extends TestBase {

    static int id = getRandomDigits();
    static String username = "SRK" + getRandomValue();
    static String firstName = "Harshid" + getRandomValue();
    static String lastName = "Prime" + getRandomValue();
    static String email = "SRK.king" + getRandomValue() + "@gmail.com";
    static String password = "SRK@Prime";
    static String phone = "07896541234";
    static int userStatus = 1;

    @Steps
    UserSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {

        userSteps.createUser(id, username, firstName, lastName, email, password, phone, userStatus);
    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response = userSteps.getUserByUsername(username).statusCode(200);
        HashMap<String, ?> username1 = response.extract().path("");
        Assert.assertTrue(username1.containsValue(username));
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        username = "updated";
        ValidatableResponse response = userSteps.updateUser(id, username, firstName, lastName, email, password, phone, userStatus);
        HashMap<String, ?> update = response.extract().path("");
        Integer newId = new Integer(id);
        String id1 = newId.toString();
        Assert.assertTrue(update.containsValue(id1));
    }

    @Title("Delete the user")
    @Test
    public void test004() {
        userSteps.deleteUser(username);
    }

    @Title(" Check deleted user still exited")
    @Test
    public void test005() {
        userSteps.getUserByUsername(username).statusCode(404);
    }
}

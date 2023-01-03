package io.petstore.swagger.studentinfo;

import io.petstore.swagger.model.PetBodyDataPojo;
import io.petstore.swagger.petanduserinfo.PetSteps;
import io.petstore.swagger.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class PetCRUDTestWithSteps extends TestBase {

    static int id = getRandomDigits();
    static String Name = "Siberian Husky";
    static String status = "Available";

    @Steps
    PetSteps petSteps;

    @Title("This method will create new record of Pet")
    @Test
    public void test001() {
        PetBodyDataPojo.CategoryData categoryData = new PetBodyDataPojo.CategoryData(getRandomDigits(), "Dog");
        ArrayList<String> photoList = new ArrayList<>();
        photoList.add("https://dogtime.com/dog-breeds/siberian-husky#/slide/1");
        ArrayList<PetBodyDataPojo.TagData> tagDataList = new ArrayList<>();
        PetBodyDataPojo.TagData tagData = new PetBodyDataPojo.TagData(getRandomDigits(), "Black and White spots");
        tagDataList.add(tagData);
        petSteps.createPet(id, categoryData, Name, photoList, tagDataList, status);
    }

    @Title("This method will fetch existing record of Pet")
    @Test
    public void test002() {
        ValidatableResponse response = petSteps.getUserByID(id).statusCode(200);
        String name = response.extract().path("name");
        Assert.assertTrue(name.matches(Name));
    }

    @Title("This method will update an existing record of Pet")
    @Test
    public void test003() {
        status = "not available";
        PetBodyDataPojo.CategoryData categoryData = new PetBodyDataPojo.CategoryData(getRandomDigits(), "Dog");
        ArrayList<String> photoList = new ArrayList<>();
        photoList.add("https://dogtime.com/dog-breeds/siberian-husky#/slide/1");
        ArrayList<PetBodyDataPojo.TagData> tagDataList = new ArrayList<>();
        PetBodyDataPojo.TagData tagData = new PetBodyDataPojo.TagData(getRandomDigits(), "Black and White spots");
        tagDataList.add(tagData);
        ValidatableResponse response = petSteps.updatePetRecord(id, categoryData, Name, photoList, tagDataList, status);
        HashMap<String, ?> update = response.extract().path("");
        Assert.assertThat(update, hasValue(status));
    }

    @Title("This method will delete an existing record of Pet")
    @Test
    public void test004() {
        petSteps.deleteUser(id);
        petSteps.getUserByID(id).statusCode(404);
    }

}

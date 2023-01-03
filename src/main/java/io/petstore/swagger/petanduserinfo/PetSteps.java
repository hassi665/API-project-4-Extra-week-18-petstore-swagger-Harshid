package io.petstore.swagger.petanduserinfo;

import io.petstore.swagger.constants.EndPoints;
import io.petstore.swagger.model.PetBodyDataPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PetSteps{ @Step("Creating Pet record with Id: {0}, categoryData: {1}, Name: {2}, photoList: {3}, tagDataList: {4}and status: {5}")
public ValidatableResponse createPet(int Id, PetBodyDataPojo.CategoryData categoryData, String Name,
                                     ArrayList<String> photoList, ArrayList<PetBodyDataPojo.TagData> tagDataList,
                                     String status) {

    PetBodyDataPojo petBodyDataPojo = new PetBodyDataPojo();
    petBodyDataPojo.setId(Id);
    petBodyDataPojo.setCategory(categoryData);
    petBodyDataPojo.setName(Name);
    petBodyDataPojo.setPhotoUrls(photoList);
    petBodyDataPojo.setTags(tagDataList);
    petBodyDataPojo.setStatus(status);

    return SerenityRest.given().log().all()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .when()
            .body(petBodyDataPojo)
            .post(EndPoints.POST_PET)
            .then().log().all().statusCode(200);
}

    @Step("Getting existing Pet record with Id: {0}")
    public ValidatableResponse getUserByID(int id) {
        return SerenityRest.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_PET_BY_ID)
                .then();
    }

    @Step("Updating Pet record with Id: {0}, categoryData: {1}, Name: {2}, photoList: {3}, tagDataList: {4}and status: {5}")
    public ValidatableResponse updatePetRecord(int Id, PetBodyDataPojo.CategoryData categoryData,
                                               String Name, ArrayList<String> photoList,
                                               ArrayList<PetBodyDataPojo.TagData> tagDataList, String status) {
        PetBodyDataPojo petBodyDataPojo = new PetBodyDataPojo();
        petBodyDataPojo.setId(Id);
        petBodyDataPojo.setCategory(categoryData);
        petBodyDataPojo.setName(Name);
        petBodyDataPojo.setPhotoUrls(photoList);
        petBodyDataPojo.setTags(tagDataList);
        petBodyDataPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(petBodyDataPojo)
                .put(EndPoints.UPDATE_PET_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Step("Delete Pet record with Id: {0}")
    public void deleteUser(int Id) {
        SerenityRest.given().log().all()
                .when()
                .pathParam("id", Id)
                .delete(EndPoints.DELETE_PET_BY_ID)
                .then().log().all().statusCode(200);

    }

}

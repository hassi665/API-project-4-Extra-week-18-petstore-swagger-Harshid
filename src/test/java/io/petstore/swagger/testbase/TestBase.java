package io.petstore.swagger.testbase;

import io.petstore.swagger.constants.Path;
import io.petstore.swagger.utils.PropertyReader;
import io.petstore.swagger.utils.TestUtils;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Jay
 */
public class TestBase extends TestUtils {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.basePath = Path.SOURCE;
    }

}

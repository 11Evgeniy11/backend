package Lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleTest extends AbstractTest {

    @Test
    void getRecipePositiveTest() {
        given().spec(requestSpecification)
                .pathParam("id", 716429)
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .spec(responseSpecification);
    }


    @Test
    void getAccountInfoWithExternalEndpointTest(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","Burger")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("American"));
    }

    @Test
    void getSpecifyingRequestDataTest() {
        given().spec(requestSpecification)
                .queryParam("includeNutrition", "false")
                .pathParam("id", 716429)
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineBritish() {
        given().spec(requestSpecification)
                .queryParam("cuisine", "British")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineItalian() {
        given().spec(requestSpecification)
                .queryParam("cuisine", "Italian")
                .queryParam("intolerances", "gluten")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineGreek() {
        given().spec(requestSpecification)
                .queryParam("cuisine", "Greek")
                .queryParam("equipment", "blender")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineIndian() {
        given().spec(requestSpecification)
                .queryParam("cuisine", "Indian")
                .queryParam("excludeIngredients", "eggs")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getPork() {
        given().spec(requestSpecification)
                .formParam("title", "Pork roast with green beans")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getPotatoPancakes() {
        given().spec(requestSpecification)
                .formParam("title", "Potato pancakes")
                .formParam("ingredientList", "1 onion")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getMeatDumplings() {
        given().spec(requestSpecification)
                .formParam("title", "Meat dumplings")
                .formParam("ingredientList", "5 eggs")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getPizza() {
        given().spec(requestSpecification)
                .formParam("title", "Pizza")
                .formParam("ingredientList", "1 package active dry yeast")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getChicken() {
        given().spec(requestSpecification)
                .formParam("title", "chicken")
                .formParam("ingredientList", "1 chicken")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }


    @Test
    void addMealTest() {
        given().spec(requestSpecification)
                .body("{\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 1,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 eggs\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
    }

}
package herokuapp.apichallenges.tests;

import herokuapp.apichallenges.models.lombok.*;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static herokuapp.apichallenges.auth.Authorization.*;
import static herokuapp.apichallenges.data.TestData.newTodoDescription;
import static herokuapp.apichallenges.data.TestData.newTodoTitle;
import static herokuapp.apichallenges.specs.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Create Todos Tests")
public class CreateTodosTests extends TestBase {

    @Test
    @DisplayName("Create Todo Positive Test")
    @Owner("m.malakhova")
    public void createTodoPositiveTest() {
        CreationTodoRequestModel creationTodoRequestBody = new CreationTodoRequestModel();
        creationTodoRequestBody.setTitle(newTodoTitle);
        creationTodoRequestBody.setDoneStatus(false);
        creationTodoRequestBody.setDescription(newTodoDescription);

        CreationTodoResponseModel response = step("Create Todo", () ->
                given()
                        .spec(requestSpec)
                        .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                        .body(creationTodoRequestBody)
                        .when()
                        .post("/todos")
                        .then()
                        .spec(response201Spec)
                        .extract().as(CreationTodoResponseModel.class));
        step("Check Todo in response", () ->
        {
            assertEquals(newTodoTitle, response.getTitle());
            assertEquals(false, response.getDoneStatus());
            assertEquals(newTodoDescription, response.getDescription());
        });
    }

    @Test
    @DisplayName("Create Todo Negative Test")
    @Owner("m.malakhova")
    public void createTodoNegativeTest() {
        CreationTodoRequestModel creationTodoRequestBody = new CreationTodoRequestModel();
        creationTodoRequestBody.setTitle(newTodoTitle);
        creationTodoRequestBody.setDoneStatus(null);
        creationTodoRequestBody.setDescription(newTodoDescription);

        ErrorMessageResponseModel response = step("Create Todo", () ->
                given()
                        .spec(requestSpec)
                        .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                        .body(creationTodoRequestBody)
                        .when()
                        .post("/todos")
                        .then()
                        .spec(response400Spec)
                        .extract().as(ErrorMessageResponseModel.class));

        step("Check error message in response", () ->
                assertEquals("Failed Validation: doneStatus should be BOOLEAN", response.getErrorMessages()[0]));
    }

}

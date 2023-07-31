package herokuapp.apichallenges.tests;

import herokuapp.apichallenges.models.lombok.*;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static herokuapp.apichallenges.auth.Authorization.*;
import static herokuapp.apichallenges.data.TestData.changedTodoTitle;
import static herokuapp.apichallenges.helpers.GettingTodos.getFirstTodos;
import static herokuapp.apichallenges.helpers.GettingTodos.getListOfTodos;
import static herokuapp.apichallenges.specs.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Change Todos Tests")
public class ChangesTodoTests extends TestBase {

    @Test
    @DisplayName("Change Todo Positive Test")
    @Owner("m.malakhova")
    public void changeTodoTitlePositiveTest() {

        ChangingTodoRequestModel updatingTodoRequestModel = new ChangingTodoRequestModel();
        updatingTodoRequestModel.setTitle(changedTodoTitle);
        updatingTodoRequestModel.setDoneStatus(false);

        CreationTodoResponseModel response = step("Change Todo's title", () ->
                given()
                        .spec(requestSpec)
                        .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                        .body(updatingTodoRequestModel)
                        .when()
                        .post("/todos/" + getFirstTodos(getListOfTodos()).getKey())
                        .then()
                        .spec(response200Spec)
                        .extract().as(CreationTodoResponseModel.class));

        step("Check Todos's title in response", () ->
                assertEquals(changedTodoTitle, response.getTitle()));
    }

    @Test
    @DisplayName("Update Todo Status Positive Test")
    @Owner("m.malakhova")
    public void changeTodoStatusPositiveTest() {

        ChangingTodoRequestModel updatingTodoRequestModel = new ChangingTodoRequestModel();
        updatingTodoRequestModel.setDoneStatus(true);

        CreationTodoResponseModel response = step("Change Todo's DoneStatus", () ->
                given()
                        .spec(requestSpec)
                        .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                        .body(updatingTodoRequestModel)
                        .when()
                        .post("/todos/" + getFirstTodos(getListOfTodos()).getKey())
                        .then()
                        .spec(response200Spec)
                        .extract().as(CreationTodoResponseModel.class));

        step("Check Todos's DoneStatus in response", () ->
                assertEquals(true, response.getDoneStatus()));
    }

    @Test
    @DisplayName("Change Todo Status Negative Test")
    @Owner("m.malakhova")
    public void changeTodoInvalidStatusPositiveTest() {

        ChangingTodoRequestModel updatingTodoRequestModel = new ChangingTodoRequestModel();
        updatingTodoRequestModel.setDoneStatus(null);

        ErrorMessageResponseModel response = step("Change Todo's DoneStatus", () ->
                given()
                        .spec(requestSpec)
                        .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                        .body(updatingTodoRequestModel)
                        .when()
                        .post("/todos/" + getFirstTodos(getListOfTodos()).getKey())
                        .then()
                        .spec(response400Spec)
                        .extract().as(ErrorMessageResponseModel.class));

        step("Check error message in response", () ->
                assertEquals("Failed Validation: doneStatus should be BOOLEAN", response.getErrorMessages()[0]));
    }
}

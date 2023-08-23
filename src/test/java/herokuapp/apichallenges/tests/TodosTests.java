package herokuapp.apichallenges.tests;

import herokuapp.apichallenges.helpers.CreationTodo;
import herokuapp.apichallenges.models.*;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static herokuapp.apichallenges.specs.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Todos Tests")
public class TodosTests extends TestBase {
    public static String changedTodoTitle = "changed title";
    public static String newTodoTitle = "create new todo";
    public static String newTodoDescription = "create it now";
    CreationTodo creationTodo = new CreationTodo();

    @Test
    @DisplayName("Create Todo Positive Test")
    @Owner("m.malakhova")
    public void createTodoPositiveTest() {
        CreationTodoRequestModel creationTodoRequestBody = new CreationTodoRequestModel();
        creationTodoRequestBody.setTitle(newTodoTitle);
        creationTodoRequestBody.setDoneStatus(false);
        creationTodoRequestBody.setDescription(newTodoDescription);
        Integer id;

        CreationTodoResponseModel postResponse = step("Create Todo", () ->
                given()
                        .spec(requestSpec)
                        .body(creationTodoRequestBody)
                        .when()
                        .post("/todos")
                        .then()
                        .spec(response201Spec)
                        .extract().as(CreationTodoResponseModel.class));
        step("Check Todo in response", () ->
        {
            assertEquals(newTodoTitle, postResponse.getTitle());
            assertEquals(false, postResponse.getDoneStatus());
            assertEquals(newTodoDescription, postResponse.getDescription());
        });

        id = postResponse.getId();

        CheckingTodosResponse getResponse = step("Get Todo by id", () ->
                given()
                        .spec(requestSpec)
                        .formParam("doneStatus", false)
                        .when()
                        .get("/todos/" + id)
                        .then()
                        .spec(response200Spec)
                        .extract().as(CheckingTodosResponse.class));
        step("Check Todo in response", () ->
        {
            assertEquals(newTodoTitle, getResponse.getTodos().getFirst().getTitle());
            assertEquals(false, getResponse.getTodos().getFirst().getDoneStatus());
            assertEquals(newTodoDescription, getResponse.getTodos().getFirst().getDescription());
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
                        .body(creationTodoRequestBody)
                        .when()
                        .post("/todos")
                        .then()
                        .spec(response400Spec)
                        .extract().as(ErrorMessageResponseModel.class));

        step("Check error message in response", () ->
                assertEquals("Failed Validation: doneStatus should be BOOLEAN", response.getErrorMessages()[0]));
    }

    @Test
    @DisplayName("Change Todo Positive Test")
    @Owner("m.malakhova")
    public void changeTodoTitlePositiveTest() {
        Integer id = creationTodo.createTodoForTest();
        ChangingTodoRequestModel updatingTodoRequestModel = new ChangingTodoRequestModel();
        updatingTodoRequestModel.setTitle(changedTodoTitle);
        updatingTodoRequestModel.setDoneStatus(false);

        CreationTodoResponseModel response = step("Change Todo's title", () ->
                given()
                        .spec(requestSpec)
                        .body(updatingTodoRequestModel)
                        .when()
                        .post("/todos/" + id)
                        .then()
                        .spec(response200Spec)
                        .extract().as(CreationTodoResponseModel.class));

        step("Check Todos's title in response", () ->
                assertEquals(changedTodoTitle, response.getTitle()));

        CheckingTodosResponse getResponse = step("Get Todo by id", () ->
                given()
                        .spec(requestSpec)
                        .formParam("doneStatus", false)
                        .when()
                        .get("/todos/" + id)
                        .then()
                        .spec(response200Spec)
                        .extract().as(CheckingTodosResponse.class));
        step("Check Todo's title in system", () ->
                    assertEquals(changedTodoTitle, getResponse.getTodos().getFirst().getTitle()));
    }

    @Test
    @DisplayName("Update Todo Status Positive Test")
    @Owner("m.malakhova")
    public void changeTodoStatusPositiveTest() {
        Integer id = creationTodo.createTodoForTest();
        ChangingTodoRequestModel updatingTodoRequestModel = new ChangingTodoRequestModel();
        updatingTodoRequestModel.setDoneStatus(true);

        CreationTodoResponseModel response = step("Change Todo's DoneStatus", () ->
                given()
                        .spec(requestSpec)
                        .body(updatingTodoRequestModel)
                        .when()
                        .post("/todos/" + id)
                        .then()
                        .spec(response200Spec)
                        .extract().as(CreationTodoResponseModel.class));

        step("Check Todos's DoneStatus in response", () ->
                assertEquals(true, response.getDoneStatus()));

        CheckingTodosResponse getResponse = step("Get Todo by id", () ->
                given()
                        .spec(requestSpec)
                        .formParam("doneStatus", true)
                        .when()
                        .get("/todos/" + id)
                        .then()
                        .spec(response200Spec)
                        .extract().as(CheckingTodosResponse.class));
        step("Check Todos's DoneStatus in system", () ->
                assertEquals(true, getResponse.getTodos().getFirst().getDoneStatus()));
    }

    @Test
    @DisplayName("Change Todo Status Negative Test")
    @Owner("m.malakhova")
    public void changeTodoInvalidStatusTest() {
        Integer id = creationTodo.createTodoForTest();
        ChangingTodoRequestModel updatingTodoRequestModel = new ChangingTodoRequestModel();
        updatingTodoRequestModel.setDoneStatus(null);

        ErrorMessageResponseModel response = step("Change Todo's DoneStatus", () ->
                given()
                        .spec(requestSpec)
                        .body(updatingTodoRequestModel)
                        .when()
                        .post("/todos/" + id)
                        .then()
                        .spec(response400Spec)
                        .extract().as(ErrorMessageResponseModel.class));

        step("Check error message in response", () ->
                assertEquals("Failed Validation: doneStatus should be BOOLEAN", response.getErrorMessages()[0]));
    }

    @Test
    @DisplayName("Delete Todo Positive Test")
    @Owner("m.malakhova")
    public void deleteTodoPositiveTest() {
        Integer id = creationTodo.createTodoForTest();
        step("Delete Todo", () ->
                given()
                        .spec(requestSpec)
                        .when()
                        .delete("/todos/" + id)
                        .then()
                        .spec(response200Spec));

        step("Check deleted Todo in system", () ->
                given()
                        .spec(requestSpec)
                        .when()
                        .get("/todos/" + id)
                        .then()
                        .spec(response404Spec));
    }
}

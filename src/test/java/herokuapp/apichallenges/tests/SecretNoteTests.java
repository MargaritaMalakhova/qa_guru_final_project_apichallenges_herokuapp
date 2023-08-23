package herokuapp.apichallenges.tests;

import herokuapp.apichallenges.models.SecretNoteRequestModel;
import herokuapp.apichallenges.models.SecretNoteResponseModel;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static herokuapp.apichallenges.auth.Authorization.*;
import static herokuapp.apichallenges.specs.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Secret Note Tests")
public class SecretNoteTests extends TestBase {
    public static String changedNote = "secret note is changed";
    public static String newSecretNote = "create new secret note";
    public static String InvalidXAuthTokenHeader = "asdtr";
    @Test
    @DisplayName("Create Secret Note Positive Test")
    @Owner("m.malakhova")
    public void createSecretNotePositiveTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(newSecretNote);

        SecretNoteResponseModel response = step("Create Secret Note", () ->
                given()
                        .spec(requestSpecSecret)
                        .body(creationSecretTodoRequestBody)
                        .when()
                        .post("/secret/note")
                        .then()
                        .spec(response200Spec)
                        .extract().as(SecretNoteResponseModel.class));

        step("Check changed note in response", () ->
                assertEquals(newSecretNote, response.getNote()));

        SecretNoteResponseModel getResponse = step("Get Secret Note", () ->
                given()
                        .spec(requestSpecSecret)
                        .when()
                        .get("/secret/note")
                        .then()
                        .spec(response200Spec)
                        .extract().as(SecretNoteResponseModel.class));
        step("Check Secret Note in system", () ->
            assertEquals(newSecretNote, getResponse.getNote()));
    }

    @Test
    @DisplayName("Create Secret Note Without AuthToken Negative Test")
    @Owner("m.malakhova")
    public void createSecretNoteWithoutTokenTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(newSecretNote);

        step("Create Secret Note without AuthToken and check StatusCode (401) in response", () ->
                given()
                        .spec(requestSpec)
                        .body(creationSecretTodoRequestBody)
                        .when()
                        .post("/secret/note")
                        .then()
                        .spec(response401Spec));
    }

    @Test
    @DisplayName("Create Secret Note With Invalid AuthToken Negative Test")
    @Owner("m.malakhova")
    public void createSecretNoteWithInvalidTokenTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(newSecretNote);

        step("Create Secret Note with invalid AuthToken and check StatusCode (403) in response", () ->
                given()
                        .spec(requestSpec)
                        .headers(XAuthTokenHeader, InvalidXAuthTokenHeader)
                        .body(creationSecretTodoRequestBody)
                        .when()
                        .post("/secret/note")
                        .then()
                        .spec(response403Spec));
    }
    @Test
    @DisplayName("Change Secret Note Positive Test")
    @Owner("m.malakhova")
    public void updateSecretNotePositiveTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(changedNote);

        SecretNoteResponseModel response = step("Change Secret Note", () ->
                given()
                        .spec(requestSpecSecret)
                        .body(creationSecretTodoRequestBody)
                        .when()
                        .post("/secret/note")
                        .then()
                        .spec(response200Spec)
                        .extract().as(SecretNoteResponseModel.class));

        step("Check changed note in response", () ->
                assertEquals(changedNote, response.getNote()));

        SecretNoteResponseModel getResponse = step("Get Secret Note", () ->
                given()
                        .spec(requestSpecSecret)
                        .when()
                        .get("/secret/note")
                        .then()
                        .spec(response200Spec)
                        .extract().as(SecretNoteResponseModel.class));
        step("Check Secret Note in system", () ->
                assertEquals(changedNote, getResponse.getNote()));
    }

    @Test
    @DisplayName("Change Secret Note Negative Test")
    @Owner("m.malakhova")
    public void updateSecretNoteNegaiveTest() {

        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(changedNote);

        step("Change Secret Note without AuthToken and check StatusCode (401) in response", () -> given()
                .spec(requestSpec)
                .body(creationSecretTodoRequestBody)
                .when()
                .post("/secret/note")
                .then()
                .spec(response401Spec));
    }
}

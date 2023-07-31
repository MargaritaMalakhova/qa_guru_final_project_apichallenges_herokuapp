package herokuapp.apichallenges.tests;

import herokuapp.apichallenges.models.lombok.SecretNoteRequestModel;
import herokuapp.apichallenges.models.lombok.SecretNoteResponseModel;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static herokuapp.apichallenges.auth.Authorization.*;
import static herokuapp.apichallenges.data.TestData.InvalidXAuthTokenHeader;
import static herokuapp.apichallenges.data.TestData.newSecretNote;
import static herokuapp.apichallenges.specs.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Create Secret Note Tests")
public class CreateSecretNoteTests extends TestBase {

    @Test
    @DisplayName("Create Secret Note Positive Test")
    @Owner("m.malakhova")
    public void createSecretNotePositiveTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(newSecretNote);

        SecretNoteResponseModel response = step("Create Secret Note", () ->
                given()
                        .spec(requestSpec)
                        .headers(tokenXChallengerHeader, tokenXChallengerHeaderValue, XAuthTokenHeader, XAuthTokenHeaderValue)
                        .body(creationSecretTodoRequestBody)
                        .when()
                        .post("/secret/note")
                        .then()
                        .spec(response200Spec)
                        .extract().as(SecretNoteResponseModel.class));

        step("Check changed note in response", () ->
                assertEquals(newSecretNote, response.getNote()));
    }

    @Test
    @DisplayName("Create Secret Note Without AuthToken Negative Test")
    @Owner("m.malakhova")
    public void createSecretNoteWithoutTokenNegativeTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(newSecretNote);

        step("Create Secret Note without AuthToken and check StatusCode (401) in response", () ->
                given()
                        .spec(requestSpec)
                        .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                        .body(creationSecretTodoRequestBody)
                        .when()
                        .post("/secret/note")
                        .then()
                        .spec(response401Spec));
    }

    @Test
    @DisplayName("Create Secret Note With Invalid AuthToken Negative Test")
    @Owner("m.malakhova")
    public void createSecretNoteWithInvalidTokenNegativeTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(newSecretNote);

        step("Create Secret Note with invalid AuthToken and check StatusCode (403) in response", () ->
                given()
                        .spec(requestSpec)
                        .headers(tokenXChallengerHeader, tokenXChallengerHeaderValue,
                                XAuthTokenHeader, InvalidXAuthTokenHeader)
                        .body(creationSecretTodoRequestBody)
                        .when()
                        .post("/secret/note")
                        .then()
                        .spec(response403Spec));
    }
}

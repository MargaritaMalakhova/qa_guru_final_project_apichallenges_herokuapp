package herokuapp.apichallenges.tests;

import herokuapp.apichallenges.models.lombok.SecretNoteRequestModel;
import herokuapp.apichallenges.models.lombok.SecretNoteResponseModel;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static herokuapp.apichallenges.data.TestData.changedNote;
import static io.qameta.allure.Allure.step;

import static herokuapp.apichallenges.auth.Authorization.*;
import static herokuapp.apichallenges.specs.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Change Secret Note Tests")
public class ChangesSecretNoteTests extends TestBase {

    @Test
    @DisplayName("Change Secret Note Positive Test")
    @Owner("m.malakhova")
    public void updateSecretNotePositiveTest() {
        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(changedNote);

        SecretNoteResponseModel response = step("Change Secret Note", () ->
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
                assertEquals(changedNote, response.getNote()));
    }

    @Test
    @DisplayName("Change Secret Note Negative Test")
    @Owner("m.malakhova")
    public void updateSecretNoteNegaiveTest() {

        SecretNoteRequestModel creationSecretTodoRequestBody = new SecretNoteRequestModel();
        creationSecretTodoRequestBody.setNote(changedNote);

        step("Change Secret Note without AuthToken and check StatusCode (401) in response", () -> given()
                .spec(requestSpec)
                .headers(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                .body(creationSecretTodoRequestBody)
                .when()
                .post("/secret/note")
                .then()
                .spec(response401Spec));
    }
}

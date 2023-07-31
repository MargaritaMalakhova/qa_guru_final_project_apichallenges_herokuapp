package herokuapp.apichallenges.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static herokuapp.apichallenges.auth.Authorization.tokenXChallengerHeader;
import static herokuapp.apichallenges.auth.Authorization.tokenXChallengerHeaderValue;
import static herokuapp.apichallenges.data.TestData.idForDeleting;
import static herokuapp.apichallenges.helpers.GettingTodos.getListOfTodos;
import static herokuapp.apichallenges.specs.Specifications.requestSpec;
import static herokuapp.apichallenges.specs.Specifications.response200Spec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.not;

@DisplayName("Delete Tests")
public class DeleteTodoTests extends TestBase {

    @Test
    @DisplayName("Delete Todo Positive Test")
    @Owner("m.malakhova")
    public void deleteTodoPositiveTest() {

        step("Delete Todo", () ->
                given()
                        .spec(requestSpec)
                        .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                        .when()
                        .delete("/todos/" + idForDeleting)
                        .then()
                        .spec(response200Spec));

        step("Check Deleted Todo in List of Todos", () ->
                assertThat(not(getListOfTodos().containsKey(idForDeleting))));
    }
}


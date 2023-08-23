package herokuapp.apichallenges.helpers;

import herokuapp.apichallenges.models.CreationTodoRequestModel;
import herokuapp.apichallenges.models.CreationTodoResponseModel;
import io.restassured.response.Response;

import static herokuapp.apichallenges.specs.Specifications.requestSpec;
import static herokuapp.apichallenges.specs.Specifications.response201Spec;
import static herokuapp.apichallenges.tests.TodosTests.newTodoDescription;
import static herokuapp.apichallenges.tests.TodosTests.newTodoTitle;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreationTodo {
    Integer id;
    public Integer createTodoForTest() {
        CreationTodoRequestModel creationTodoRequestBody = new CreationTodoRequestModel();
        creationTodoRequestBody.setTitle(newTodoTitle);
        creationTodoRequestBody.setDoneStatus(false);
        creationTodoRequestBody.setDescription(newTodoDescription);

        CreationTodoResponseModel postResponse = step("Create Todo", () ->
                given()
                        .spec(requestSpec)
                        .body(creationTodoRequestBody)
                        .when()
                        .post("/todos")
                        .then()
                        .spec(response201Spec)
                        .extract().as(CreationTodoResponseModel.class));

        id = postResponse.getId();
        return id;
    }
}

package herokuapp.apichallenges.helpers;

import herokuapp.apichallenges.models.lombok.TodoModel;
import herokuapp.apichallenges.models.lombok.CheckingTodosResponse;

import java.util.*;

import static herokuapp.apichallenges.auth.Authorization.tokenXChallengerHeader;
import static herokuapp.apichallenges.auth.Authorization.tokenXChallengerHeaderValue;
import static io.restassured.RestAssured.given;

public class GettingTodos {
    public static HashMap<Integer, String> map = new HashMap<>();
    public static HashMap<Integer, String> getListOfTodos() {

        CheckingTodosResponse response = given()
                .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
                .formParam("doneStatus", false)
                .when()
                .get("/todos")
                .then()
                .statusCode(200)
                .extract().as(CheckingTodosResponse.class);


        LinkedList<TodoModel> todosFromResponse = response.getTodos();
        for (TodoModel notSecretTodo : todosFromResponse) {
            map.put(notSecretTodo.getId(), notSecretTodo.getTitle());
        }
        return map;
    }

    public static Map.Entry<Integer, String> getFirstTodos(HashMap<Integer, String> map) {

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();

        Map.Entry<Integer, String> firstValue = iterator.next();
        return firstValue;
    }
}

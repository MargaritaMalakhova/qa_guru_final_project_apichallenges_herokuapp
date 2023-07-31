package herokuapp.apichallenges.models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckingTodosResponse {

    LinkedList<TodoModel> todos;
}

package herokuapp.apichallenges.models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreationTodoRequestModel {
    String title;
    Boolean doneStatus;
    String description;
}

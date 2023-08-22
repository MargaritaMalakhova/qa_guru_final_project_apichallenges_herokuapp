package herokuapp.apichallenges.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoModel {
    Integer id;
    String title;
    Boolean doneStatus;
    String description;
}

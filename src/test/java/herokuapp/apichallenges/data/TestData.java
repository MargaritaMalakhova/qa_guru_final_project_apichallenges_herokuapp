package herokuapp.apichallenges.data;

import static herokuapp.apichallenges.helpers.GettingTodos.getFirstTodos;
import static herokuapp.apichallenges.helpers.GettingTodos.getListOfTodos;

public class TestData {
    public static String changedNote = "secret note is changed";
    public static String changedTodoTitle = getFirstTodos(getListOfTodos()).getValue() + " changed";
    public static String newSecretNote = "create new secret todo";
    public static String InvalidXAuthTokenHeader = "asdtr";
    public static String newTodoTitle = "create new todo";
    public static String newTodoDescription = "create it now";
    public static Integer idForDeleting = getFirstTodos(getListOfTodos()).getKey();
}

package herokuapp.apichallenges.tests;

import herokuapp.apichallenges.auth.Authorization;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://apichallenges.herokuapp.com";
        Authorization.getXChal();
        Authorization.getXCAuth();
    }
}

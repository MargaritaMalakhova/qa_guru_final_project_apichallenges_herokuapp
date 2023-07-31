package herokuapp.apichallenges.tests;

import com.codeborne.selenide.Configuration;
import herokuapp.apichallenges.auth.Authorization;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://apichallenges.herokuapp.com";
        RestAssured.baseURI = "https://apichallenges.herokuapp.com";
        Authorization.getXChal();
        Authorization.getXCAuth();
    }
}

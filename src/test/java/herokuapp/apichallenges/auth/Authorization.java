package herokuapp.apichallenges.auth;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authorization {
    public static String tokenXChallengerHeader = "X-Challenger";
    public static String XAuthTokenHeader = "X-Auth-Token";
    public static String tokenXChallengerHeaderValue;
    public static String XAuthTokenHeaderValue;

    public static String getXChal() {

        Response response = given()
                .header("ContentType","application/json")
                .when()
                .post("/challenger")
                .then()
                .statusCode(201)
                .extract()
                .response();

        tokenXChallengerHeaderValue = response.getHeader(tokenXChallengerHeader);
        return tokenXChallengerHeaderValue;
    }

    public static String getXCAuth() {
        Response response = given()

                .headers(tokenXChallengerHeader,tokenXChallengerHeaderValue,
                        "Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .when()
                .post("/secret/token")
                .then()
                .statusCode(201)
                .extract()
                .response();

        XAuthTokenHeaderValue = response.getHeader(XAuthTokenHeader);
        return XAuthTokenHeaderValue;
    }
}

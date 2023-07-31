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
                .log().uri()
                .log().headers()
                .header("ContentType","application/json")
                .when()
                .post("/challenger")
                .then()
                .log().status()
                .log().headers()
                .statusCode(201)
                .extract()
                .response();

        tokenXChallengerHeaderValue = response.getHeader(tokenXChallengerHeader);
        return tokenXChallengerHeaderValue;
    }

    public static String getXCAuth() {
        Response response = given()

                .log().uri()
                .log().headers()
                .headers(tokenXChallengerHeader,tokenXChallengerHeaderValue,
                        "Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .when()
                .post("/secret/token")
                .then()
                .log().status()
                .log().headers()
                .statusCode(201)
                .extract()
                .response();

        XAuthTokenHeaderValue = response.getHeader(XAuthTokenHeader);
        return XAuthTokenHeaderValue;
    }
}

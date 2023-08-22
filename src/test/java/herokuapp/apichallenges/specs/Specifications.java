package herokuapp.apichallenges.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static herokuapp.apichallenges.auth.Authorization.*;
import static herokuapp.apichallenges.auth.Authorization.XAuthTokenHeaderValue;
import static herokuapp.apichallenges.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class Specifications {
    public static RequestSpecification requestSpec = with()
            .header(tokenXChallengerHeader, tokenXChallengerHeaderValue)
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON);

    public static RequestSpecification requestSpecSecret = with()
            .headers(tokenXChallengerHeader, tokenXChallengerHeaderValue, XAuthTokenHeader, XAuthTokenHeaderValue)
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON);

    public static ResponseSpecification response200Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification response201Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .expectBody("id", notNullValue())
            .expectBody("title", notNullValue())
            .expectBody("doneStatus", notNullValue())
            .expectBody("description", notNullValue())
            .build();
    public static ResponseSpecification response400Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400)
            .expectBody("errorMessages", notNullValue())
            .build();

    public static ResponseSpecification response401Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(401)
            .build();

    public static ResponseSpecification response403Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(403)
            .build();

    public static ResponseSpecification response404Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404)
            .build();
}

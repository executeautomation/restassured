package steps;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BDDStyledMethod {


    public static void SimpleGETPost(String postNumber){
        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
                then().body("author", is("Karthik KK"));
    }

    public static void PerformContainsCollection() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/posts/")
        .then()
                .body("author", containsInAnyOrder("Karthik KK", "Karthik KK", null)).statusCode(204);
    }

    public static void PerformPathParameter() {

        given()
                .contentType(ContentType.JSON).
        with()
                .pathParams("post", 1).
        when()
                .get("http://localhost:3000/posts/{post}").
        then()
                .body("author", containsString("Karthik KK"));
    }

    public static void PerformQueryParameter(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("id", 1).
        when()
                .get("http://localhost:3000/posts/").
        then()
                .body("author", hasItem("Karthik KK"));
    }

    public static void PerformPOSTWithBodyParameter() {
        HashMap<String,String> postContent = new HashMap<>();
        postContent.put("id", "5");
        postContent.put("title", "Robotium course");
        postContent.put("author", "ExecuteAutomation");

        given()
                .contentType(ContentType.JSON).
        with()
                .body(postContent).
        when()
                .post("http://localhost:3000/posts").
        then()
                .body("author", Is.is("ExecuteAutomation"));
    }





}

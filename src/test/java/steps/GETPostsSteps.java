package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.Address;
import model.Location;
import model.LoginBody;
import model.Posts;
import utilities.APIConstant;
import utilities.EARestAssuredV2;
import utilities.RestAssuredExtension;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class GETPostsSteps {


    public static ResponseOptions<Response> response;
    public static String token;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOpsWithToken(url, token);
    }

    @And("^I perform GET operation with path parameter for address \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterForAddress(String url, DataTable table) throws Throwable {
        var data = table.raw();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", data.get(1).get(0));

        //response = RestAssuredExtension.GetWithQueryParamsWithToken(url, pathParams, response.getBody().jsonPath().get("access_token"));

        EARestAssuredV2 eaRestAssuredV2 = new EARestAssuredV2(url,APIConstant.ApiMethods.GET,token);
        response = eaRestAssuredV2.ExecuteWithQueryParams(queryParams);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {

        var posts = new Posts.Builder().build();

        var post = response.getBody().as(posts.getClass());

        assertThat(post.getAuthor(), equalTo("Karthik KK"));

        //assertThat(posts[0].getAuthor(), equalTo("Karthik KK"));
    }

    //Deserialize

    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String authorName) throws Throwable {

        var a = response.getBody().asString();

        assertThat(a, matchesJsonSchemaInClasspath("post.json"));



//        assertThat(response.getBody().jsonPath().get("author"), hasItem("Karthik KK"));
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() throws Throwable {
        BDDStyledMethod.PerformContainsCollection();
    }

    @Then("^I should see verify GET Parameter$")
    public void iShouldSeeVerifyGETParameter() throws Throwable {
        BDDStyledMethod.PerformPathParameter();
    }


    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String url, DataTable table) throws Throwable {

        var data = table.raw();

//        HashMap<String, String> body = new HashMap<>();
//        body.put("email", data.get(1).get(0));
//        body.put("password", data.get(1).get(1));

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));

        EARestAssuredV2 eaRestAssuredV2 = new EARestAssuredV2(url, APIConstant.ApiMethods.POST, token);

        //token = eaRestAssuredV2.Authenticate(body);

        token = eaRestAssuredV2.Authenticate(loginBody);
    }

    @Then("^I should see the street name as \"([^\"]*)\"$")
    public void iShouldSeeTheStreetNameAs(String streetName) throws Throwable {

        var a = response.getBody().as(Location[].class);

        Address address = a[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase("primary")).findFirst().orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));


//        assertThat(a[0].getAddress().getStreet(), equalTo(streetName));


    }
}

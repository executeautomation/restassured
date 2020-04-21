package steps;

import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;
import utilities.RestAssuredExtension;

public class TestInitialize {

    @Before
    public void TestSetup(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}

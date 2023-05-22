package Helpers;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class TestDataHelper {

    private static final String HOST = "localhost";

    private static final int PORT = 8080;

    private static final String URL = String.format("http://%s:%d/%s", HOST, PORT, "%s");

    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(PORT);

    @BeforeMethod
    public void setUpWireMockServer() {
        System.out.println("Start server");
        WIRE_MOCK_SERVER.start();
        WireMock.configureFor(HOST, PORT);

    }

    @AfterMethod(alwaysRun = true)
    public void stopWireMockServer() {
        if (WIRE_MOCK_SERVER.isRunning()) {
            System.out.println("Shot Down");
            WIRE_MOCK_SERVER.stop();
        }
    }
    @Test
    public void getGoods() {

        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor(HOST, PORT);
        String responseGetMethodExpected = "{\n  \"users\": [\n    {\n      \"userName\": \"Andry\",\n      \"userId\": 23\n    },\n    {\n      \"userName\": \"Eduard\",\n      \"userId\": 12\n    }\n  ]\n}";
        String apiURL = String.format(URL, "api/goods");

        //Get response and check: status code, header and response for all users
        String responseGetMethodActual = RestAssured.given().log().all()
                .get(apiURL)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .header("Content-Type", ("application/json"))
                .extract()
                .body()
                .asString();

        // Check the responses after get method and compare the responses
        Assert.assertEquals(responseGetMethodActual, responseGetMethodExpected);
        wireMockServer.stop();
    }

    //@Test
    public void sendGetRequestGetAllUsers() {

        //WireMockServer wireMockServer = new WireMockServer();
        //wireMockServer.start();
        //configureFor(HOST, PORT);
        String responseGetMethodExpected = "{\n  \"users\": [\n    {\n      \"userName\": \"Andry\",\n      \"userId\": 23\n    },\n    {\n      \"userName\": \"Eduard\",\n      \"userId\": 12\n    }\n  ]\n}";

        stubFor(get(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseGetMethodExpected)));

        String apiURL = String.format(URL, "api/users");

        //Get response and check: status code, header and response for all users
        String responseGetMethodActual = RestAssured.given().log().all()
                .get(apiURL)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .header("Content-Type", ("application/json"))
                .extract()
                .body()
                .asString();

        // Check the responses after get method and compare the responses
        Assert.assertEquals(responseGetMethodActual, responseGetMethodExpected);
        //wireMockServer.stop();
    }

}

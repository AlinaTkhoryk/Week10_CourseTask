package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.Elmir;


import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import  io.restassured.response.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.net.URI;

public class ElmirTest extends BaseTest {

    @Test
    public void testCaseOne() {
         String goodsResponse = RestAssured.given()
                //.contentType(ContentType.JSON)
                .get("/api/goods")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .header("Content-Type", ("application/json"))

                .extract()
                .response()
                .asString();



        driver.get("https://elmir.ua/");
        Elmir elmirPage = PageFactory.initElements(driver, Elmir.class);
        //System.out.println("Hello World! Start");
        /*LOG.debug("Debug Message Logged !!!");
        LOG.info("Info Message Logged !!!");
        LOG.error("Error Message Logged !!!", new NullPointerException("NullError"));*/
        //System.out.println("Hello World! End");

        elmirPage.contactsBtnClick();
        elmirPage.dropDownContactClick();
        switchToWindow(2);
        elmirPage.headerCallBackClick();

/*        mouseClick(By.xpath("//a[text()='Контакты'][1]"));
        click(By.xpath("//a[@href='//service.elmir.ua/'][1]"));
        switchToWindow(2);
        mouseClick(By.xpath("//a[@class='cbtel'][1]"));*/

/*        try {

        } catch (NoSuchElementException e){
            e.printStackTrace();
        }*/

    }
}

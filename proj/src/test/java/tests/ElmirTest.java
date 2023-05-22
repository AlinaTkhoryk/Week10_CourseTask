package tests;

import Datas.ItemData;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
import java.util.ArrayList;
import java.util.List;

public class ElmirTest extends BaseTest {

    @Test
    public void testCaseOne() {

        List<ItemData> expectedData = new ArrayList<>();

        try {
            expectedData = getGoods();
        }
        catch (ParseException e){
            Assert.fail(e.getMessage());
        }

        Assert.assertNotEquals(expectedData.size(), 0);

        driver.get("https://elmir.ua/");
        Elmir elmirPage = PageFactory.initElements(driver, Elmir.class);

        wait.until(ExpectedConditions.visibilityOf(elmirPage.getSerach()));

        for (int i=0; i<expectedData.size();i++) {

            try {
                ItemData requiredItem = expectedData.get(i);
                elmirPage.MakeSearch(requiredItem.name);

                wait.until(ExpectedConditions.visibilityOf(elmirPage.getSerachResults()));

                //elmirPage.MakeSearch("rtx 4070"); //this is exist one
                List<ItemData> searchResults = elmirPage.GetSearchResult();

                Assert.assertNotEquals(searchResults.size(), 0);

                boolean hasItem = false;

                for (int j = 0; j < searchResults.size(); j++) {

                    ItemData searchResult = searchResults.get(i);

                    if (searchResult.IsSame(requiredItem)) {
                        hasItem = true;
                        break;
                    }
                }

                Assert.assertEquals(hasItem, true);
            }
            catch (TimeoutException exception){
                Assert.fail(exception.getMessage());
            }
        }
    }
}

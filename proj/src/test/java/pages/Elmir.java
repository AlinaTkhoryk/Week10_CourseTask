package pages;


import Datas.ItemData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Elmir {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy(name = "q")
    WebElement search;

    @FindBy(id = "vitrina-tovars")
    WebElement searchResult;

    String menuTemplate = "//a[@class='ml-a' and text()='%s']";

    public Elmir(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
        BaseTest.LOG.info("Init page");
    }

    public void MakeSearch(String text){
        search.clear();
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
    }

    public List<ItemData> GetSearchResult(){

        List<ItemData> results = new ArrayList<>();

        List<WebElement> elements =   searchResult.findElements(By.className("vit-item"));

        for (int i=0;i< elements.size();i++){
            WebElement element = elements.get(i);
            String elementText= element.getText();
            String[] elementDatas = elementText.split("\n");

            ItemData itemData = new ItemData();

            itemData.name = elementDatas[0];
            if(elementDatas.length>=4) {
                itemData.price = elementDatas[3];
            }

            if(elementDatas.length>=5) {
                itemData.code = elementDatas[4];
            }

            if(elementDatas.length>=6) {
                itemData.availability = elementDatas[5];
            }
            results.add(itemData);
        }
        return results;
    }

    public WebElement getSerach() {
        return search;
    }

    public WebElement getSerachResults() {
        return  searchResult;
    }
}

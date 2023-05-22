package tests;

import Datas.ItemData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pages.Elmir;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    //protected ChromeOptions options;
    protected WebDriverWait wait;
    protected Actions action;
    public static final Logger LOG = LogManager.getLogger(BaseTest.class.getName());

    @BeforeTest
    public void setDriver(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
        action = new Actions(driver);
    }

    @AfterTest(alwaysRun = true)
    public void closeUp() {
        if (driver != null)
            driver.quit();
    }

    protected List<ItemData> getGoods() throws ParseException {
        String json = "{\"goods\":[\n    {\n      \"name\":\"Видеокарта Inno3D NVIDIA CMP 90-HX MINING CARD (ACMP-90HX-3FS, 288-9N612-101KT)\",\n      \"price\":\"79 999 грн\",\n      \"availability\":\"Под заказ\",\n      \"code\": \"Код:63918\"\n    },\n    {\n      \"name\":\"МФУ Kyocera Ecosys M2040dn (1102S33NL0)\",\n      \"price\":\"14 060 грн\",\n      \"availability\":\"Доступен\",\n      \"code\": \"Код: 1122990\"\n    },\n    {\n      \"name\":\"Bluetooth-адаптер Baseus Bluetooth Qiyin AUX Black (WXQY-01)\",\n      \"price\":\"277 грн\",\n      \"availability\":\"В наличии\",\n      \"code\": \"Код: 1205257\"\n    },\n    {\n      \"name\":\"Штатив VELBON EX-323 Mini\",\n      \"price\":\"1 004 грн\",\n      \"availability\":\"Доступен\",\n      \"code\": \"Код: 24567\"\n    }\n  ]}";

        JSONParser parser = new JSONParser();
        Object jsonObj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) jsonObj;
        JSONArray goodsJsonArray = (JSONArray) jsonObject.get("goods");

        List<ItemData> itemDatas = new ArrayList<ItemData>();

        for (int i = 0; i < goodsJsonArray.size(); i++) {
            JSONObject jsonItemData = (JSONObject) goodsJsonArray.get(i);
            ItemData itemData = new ItemData();
            itemData.availability = jsonItemData.get("availability").toString();
            itemData.name = jsonItemData.get("name").toString();
            itemData.code = jsonItemData.get("code").toString();
            itemData.price = jsonItemData.get("price").toString();

            itemDatas.add(itemData);
        }
        return itemDatas;
    }
}
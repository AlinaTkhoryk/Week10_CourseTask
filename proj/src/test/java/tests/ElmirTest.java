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

public class ElmirTest extends BaseTest {

    @Test
    public void testCaseOne() {
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

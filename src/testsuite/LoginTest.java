package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    static String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //enter username
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");
        //enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        //click on login button
        WebElement clickLogin = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        clickLogin.click();
        //verify secure area
        //expected result
        String expectedText = "You logged into a secure area!";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@class='flash success']"));
        String actualText = actualTextElement.getText().substring(0, 30);
        // verify expected and actual
        Assert.assertEquals(expectedText, actualText);
        //System.out.println(actualText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //enter invalid username
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith1");
        //enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        //click on login button
        WebElement clickLogin = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        clickLogin.click();
        String expectedText = "Your username is invalid!";

        WebElement actualTextElement = driver.findElement(By.xpath("//div[contains(text(),'invalid!')]"));
        String actualText = actualTextElement.getText().substring(0, 25);
        // verify expected and actual
        Assert.assertEquals(expectedText, actualText);
        //System.out.println(actualText);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //enter user name
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");
        //enter in-valid password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword");
        //click on login button
        WebElement clickLogin = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        clickLogin.click();    // Verify the "Your password is invalid!"
        // expected result
        String expectedText = "Your password is invalid!";

        WebElement actualTextElement = driver.findElement(By.xpath("//div[contains(text(),'invalid!')]"));
        String actualText = actualTextElement.getText().substring(0, 25);
        // verify expected and actual
        Assert.assertEquals(expectedText, actualText);


    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

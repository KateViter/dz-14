import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WebTest extends BaseTest{

    @Test
    public void firstTest() {
        driver.get("https://demoqa.com/elements");
        WebElement buttons = driver.findElement(By.xpath("//*[contains(text(),\"Buttons\")]"));
        buttons.click();
        WebElement clickMe = driver.findElement( By.xpath("(//*[@class=\"btn btn-primary\"])[3]"));
        clickMe.click();
        WebElement textMessage = driver.findElement(By.id("dynamicClickMessage"));
        Assert.assertEquals(textMessage.getText(), "You have done a dynamic click");
    }

    @Test
    public void secondTest() {
        driver.get("https://demoqa.com/webtables");
        WebElement addBtn = driver.findElement(By.xpath("//*[@id=\"addNewRecordButton\"]"));
        addBtn.click();

        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"userEmail\"]"));
        WebElement ageInput = driver.findElement(By.xpath("//*[@id=\"age\"]"));
        WebElement salaryInput = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
        WebElement departmentInput = driver.findElement(By.xpath("//*[@id=\"department\"]"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        firstNameInput.sendKeys("John");
        lastNameInput.sendKeys("Doe");
        emailInput.sendKeys("johndoe@example.com");
        ageInput.sendKeys("30");
        salaryInput.sendKeys("500");
        departmentInput.sendKeys("IT");
        submitButton.click();

        List<String> records = new ArrayList<>();
        for (WebElement data : driver.findElements(By.xpath("(//*[@class=\"rt-tr-group\"])[4]//*[@class=\"rt-td\"]"))) {
            String text = data.getText();
            records.add(text);
        }

        Assert.assertEquals(records.get(0), "John");
        Assert.assertEquals(records.get(1), "Doe");
        Assert.assertEquals(records.get(2), "30");
        Assert.assertEquals(records.get(3), "johndoe@example.com");
        Assert.assertEquals(records.get(4), "500");
        Assert.assertEquals(records.get(5), "IT");

        WebElement editButton = driver.findElement(By.xpath("//*[@id=\"edit-record-4\"]//*//*"));
        Actions actions = new Actions(driver);
        actions.moveToElement(editButton);
        actions.perform();
        editButton.click();
        WebElement firstNameEdit = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstNameEdit.sendKeys("Tomas");
        WebElement submitBtn = driver.findElement(By.id("submit"));
        submitBtn.click();
    }
}

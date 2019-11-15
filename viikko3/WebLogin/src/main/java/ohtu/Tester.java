package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");

        // onnistunut kirjautuminen
        
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element = driver.findElement(By.name("login"));
//        element.submit();
//        System.out.println(driver.getPageSource());

        // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("salasana");
//        element = driver.findElement(By.name("login"));
//        element.submit();
//        System.out.println(driver.getPageSource());

        // uuden käyttäjätunnuksen luominen
        
//        WebElement element = driver.findElement(By.linkText("register new user"));
//        element.click();
//        Random r = new Random();
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("arto" + r.nextInt(100000));
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("salasana");
//        element = driver.findElement(By.name("passwordConfirmation"));
//        element.sendKeys("salasana");
//        element = driver.findElement(By.name("signup"));
//        element.submit();
//        System.out.println(driver.getPageSource());
        
        // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("arto" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("signup"));
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
        System.out.println(driver.getPageSource());

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}

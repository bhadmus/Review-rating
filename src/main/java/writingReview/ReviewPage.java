package writingReview;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ReviewPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
    private Dotenv dotenv = Dotenv.load();
    ElementMapper ele;

    public void startSession() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(ele.homePage);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        action = new Actions(driver);
        String userName = dotenv.get("USERNAME");
        String password = dotenv.get("ACCESS");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.loginTab)));
        driver.findElement(By.cssSelector(ele.loginTab)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.emailField)));
        this.waitAndType(ele.emailField, userName);
        this.waitAndType(ele.passwordField, password);
        driver.findElement(By.cssSelector(ele.loginButton)).click();

    }

    private void scrollElement(WebElement element){
        JavascriptExecutor jav = (JavascriptExecutor) driver;
        jav.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void waitAndType(String field, String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(field)));
        driver.findElement(By.cssSelector(field)).sendKeys(text);

    }

    private void hoverOverElement(String element){
        WebElement locatedElement = driver.findElement(By.cssSelector(element));
        action.moveToElement(locatedElement).perform();

    }

    private void forceAClick(String element){
        WebElement locatedElement = driver.findElement(By.cssSelector(element));
        action.moveToElement(locatedElement).click().perform();

    }

    private void waitAndClick(String element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
        driver.findElement(By.cssSelector(element)).click();

    }

    public void writeReview() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.companyLists)));
        WebElement tab = driver.findElement(By.cssSelector(ele.companyLists));
        action.moveToElement(tab).perform();
        this.waitAndClick(ele.financeAdvisor);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.mattTobben)));
        WebElement card = driver.findElement(By.cssSelector(ele.gregoryAlan));
        this.scrollElement(card);
        Assert.assertEquals(card.getText(), ele.getGregoryAlan);
        this.hoverOverElement(ele.fourStar);
        this.hoverOverElement(ele.fiveStar);
        this.waitAndClick(ele.writeAReview);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.setFiveStar)));
        this.forceAClick(ele.setFiveStar);
        this.waitAndType(ele.reviewComment, ele.getReviewComment);
        String wordCount = driver.findElement(By.cssSelector(ele.reviewCount)).getText();
        boolean counter = Integer.parseInt(wordCount) > 200;
        Assert.assertTrue(counter);
        driver.findElement(By.cssSelector(ele.submitReviewButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.editReviewButton)));
        WebElement editButton = driver.findElement(By.cssSelector(ele.editReviewButton));
        Assert.assertEquals(editButton.getText(), ele.getEditReviewButtonText);
    }

    public void terminateSession(){

        driver.quit();
    }

    public static void main(String[] args) {
        ReviewPage test = new ReviewPage();

        test.startSession();
        test.writeReview();
    }
}

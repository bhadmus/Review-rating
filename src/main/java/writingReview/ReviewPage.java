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
        driver.findElement(By.cssSelector(ele.emailField)).sendKeys(userName);
        driver.findElement(By.cssSelector(ele.passwordField)).sendKeys(password);
        driver.findElement(By.cssSelector(ele.loginButton)).click();

    }

    public void scrollElement(WebElement element){
        JavascriptExecutor jav = (JavascriptExecutor) driver;
        jav.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void writeReview() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.companyLists)));
        WebElement tab = driver.findElement(By.cssSelector(ele.companyLists));
        action.moveToElement(tab).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.financeAdvisor)));
        driver.findElement(By.cssSelector(ele.financeAdvisor)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.mattTobben)));
        WebElement card = driver.findElement(By.cssSelector(ele.gregoryAlan));
        this.scrollElement(card);
        Assert.assertEquals(card.getText(), ele.getGregoryAlan);
        WebElement fourStar = driver.findElement(By.cssSelector(ele.fourStar));
        WebElement fiveStar = driver.findElement(By.cssSelector(ele.fiveStar));
        action.moveToElement(fourStar).perform();
        action.moveToElement(fiveStar).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.writeAReview)));
        driver.findElement(By.cssSelector(ele.writeAReview)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.setFiveStar)));
        WebElement rating = driver.findElement(By.cssSelector(ele.setFiveStar));
        action.moveToElement(rating).click().perform();
        driver.findElement(By.cssSelector(ele.reviewComment)).sendKeys(ele.getReviewComment);
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

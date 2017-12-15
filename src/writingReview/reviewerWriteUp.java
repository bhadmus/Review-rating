package writingReview;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;


public class reviewerWriteUp {

public static void main (String args [])throws InterruptedException{
	System.setProperty("webdriver.gecko.driver",
            "C:\\Users\\Ademola Bhadmus\\Documents\\HTC\\Learning Folder\\Hover and Rating\\geckodriver-v0.19.1-win64\\geckodriver.exe");
	WebDriver driver1 = new FirefoxDriver();
    
    // Since the actual task is to get to write a review, login was written as a function in order to make the code 
	// easier to read through and less long 
    LoginProcedure attemptLogin = new LoginProcedure(driver1);
    attemptLogin.login();
    

    
   driver1.get("https://wallethub.com/profile/test_insurance_company/");
   
   
   	

   // This line hovers on the stars in order to see the ratings to hover or click
   
   WebElement hoverElement = driver1.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/span"));
   Actions builder = new Actions(driver1);

   builder.moveToElement(hoverElement).perform();
   Thread.sleep(3000);
   builder.moveToElement(driver1.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[1]/div/a[4]"))).perform();//this hovers to the fourth star
   Thread.sleep(3000);
   builder.moveToElement(driver1.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[1]/div/a[5]"))).perform();//This hovers to the fifth star

   WebDriverWait wait = new WebDriverWait(driver1,5);
   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[1]/div/a[5]"))).click();//The performs click action on the fifth star

   //This line gets the redirected url as a result of clicking the fifth star
   String redirectedUrl = driver1.getCurrentUrl();
   driver1.get(redirectedUrl);

   
   WebElement dropDown = driver1.findElement(By.cssSelector(".bf-icon-down-open"));
   dropDown.click();//To open the drop-down and select Health option

   WebElement optionHealth = driver1.findElement(By.xpath("/html/body/div[2]/main/div/div/div[2]/form/div[1]/div/ul/li[2]/a"));
   optionHealth.click();//To select  Health option
   Thread.sleep(5000);

   Actions rate = new Actions(driver1);
   rate.moveToElement(driver1.findElement(By.xpath("/html/body/div[2]/main/div/div/div[2]/form/div[2]/div/span[1]/a[5]"))).click().perform();
   Thread.sleep(5000);//To select the fifth star rating

   WebElement writeReview = driver1.findElement(By.xpath("//*[@id=\"review-content\"]"));
   writeReview.clear();
   writeReview.sendKeys("All rights reserved. All rights reserved. All rights reserved. All rights reserved. All rights reserved. All rights reserved. All rights reserved. All rights reserved. All rights reserved. All rights reserved. All rights reserved. ");
   //This simulates typing the text into the review test box
   

   WebElement submitButton = driver1.findElement(By.xpath("/html/body/div[2]/main/div/div/div[2]/form/div[3]/div[2]/input"));
   WebDriverWait pause = new WebDriverWait(driver1, 5);
   pause.until(ExpectedConditions.elementToBeClickable(submitButton)).click();//This helps to submit the review

   String newUrl = driver1.getCurrentUrl();
   driver1.get(newUrl);//To continue automation of the site, the redirected url is needed and this is how we achieved it.

   WebElement goToProfile = driver1.findElement(By.xpath("/html/body/div[2]/header/div/nav[3]/a[3]"));
   Actions profileLauncher = new Actions(driver1);
   
   
   // The following code snippet helps to select and click profile after hovering over the name of user
   profileLauncher.moveToElement(goToProfile).perform();
   Thread.sleep(3000);
   profileLauncher.moveToElement(driver1.findElement(By.xpath("/html/body/div[2]/header/div/nav[3]/nav/ul/li[4]/a"))).perform();//this hovers to the fourth star
   Thread.sleep(3000);

   WebDriverWait time_out = new WebDriverWait(driver1,5);
   time_out.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/div/nav[3]/nav/ul/li[4]/a"))).click();//The performs click action on the fifth star

   WebElement reviewTab = driver1.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[1]/div[2]/ul/li[3]/a"));
   reviewTab.click();
   //Snippet ends 
   
   String reviewUrl = driver1.getCurrentUrl();
   driver1.get(reviewUrl);//to get the review url

   try {
       assertTrue(driver1.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[1]/div[3]/div[3]/div/div/p")).getText().matches("^[\\s\\S]*All rights reserved.[\\s\\S]*$"));
       System.out.println("Text Found");
   } catch (Error e) {
       System.out.println("Text not found");
   }
//To check if the posted message is present on this page 
   
   Thread.sleep(3000);

   driver1.close();
}

}
package writingReview;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//A simple class to login to the site
public class LoginProcedure {
    WebDriver driver ;


    public LoginProcedure(WebDriver driver1) {
		this.driver = driver1;
	}


	public  void login ( ) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver",
                "C:\\Users\\Ademola Bhadmus\\Documents\\HTC\\Learning Folder\\Hover and Rating\\geckodriver-v0.19.1-win64\\geckodriver.exe");
        driver.get("https://wallethub.com/join/light");
        driver.manage().window().maximize();
    //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
         System.out.println(driver.getCurrentUrl());

    WebElement login_tab = driver.findElement(By.xpath("/html/body/main/div/form/div/nav/ul/li[2]/a"));
    login_tab.click();

    WebElement email = driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/input"));
    WebElement password = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/input"));
    email.clear();
    email.sendKeys("a********s@hotmail.com");



    password.clear();
    password.sendKeys("*********");

    WebElement login_button = driver.findElement(By.xpath("/html/body/main/div/form/div/div[3]/button"));
    login_button.click();

    Thread.sleep(20000);



    }


}

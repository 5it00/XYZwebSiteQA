package XYZpac;

import java.time.Duration;
import java.util.List;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTest {
 public WebDriver driver;
 SoftAssert softassert = new SoftAssert();
 
 @BeforeTest
 public void beforeTest() {
WebDriverManager.edgedriver().setup();
driver = new EdgeDriver();
driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
 }
 @Test()
 public void CustomerLogin() throws InterruptedException {
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
String bigAmount = "3000";
String smolAmount="100";
//covert to int to compare
int big = Integer.parseInt(bigAmount);
int smol=Integer.parseInt(smolAmount);
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button")).click();
driver.findElement(By.xpath("//*[@id=\"userSelect\"]")).sendKeys("r"+Keys.ENTER);
//login
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/button")).click();
//Deposit
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")).click();
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(bigAmount);
Thread.sleep(2500);
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")).click();
Thread.sleep(3000);
//withdrawl
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[3]")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(smolAmount);
Thread.sleep(2500);
driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")).click();
Thread.sleep(3000);
String balance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();
System.out.println("****BALANCE*****"+balance);
int newBa = Integer.parseInt(balance);
softassert.assertEquals(newBa, big-smol);
//back to home
driver.findElement(By.xpath("/html/body/div/div/div[1]/button[1]")).click();
softassert.assertAll();
 }
// @Test()
// public void BankManagerLogin() throws InterruptedException {
//	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
// driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click();
// }
 @Test()
 public void ddCustomer() throws InterruptedException {
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click();
	 //add customer randomly
	 int userId = (int) (Math.random()*100);
	 StringBuilder  userName = new StringBuilder();
	 userName.append(userId);
	String userIdString =userName.toString();
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click();
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]")).click();
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input")).sendKeys("Abed");
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input")).sendKeys("user"+userIdString);
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input")).sendKeys(userIdString);
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")).click();
	 String alretText= driver.switchTo().alert().getText();
	 System.out.println(alretText);
	boolean myCheck= alretText.contains("successfully");
	 softassert.assertEquals(myCheck, true,"yess contains successfully");
	 driver.switchTo().alert().accept();
	 Thread.sleep(2500);
	//openCart
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]")).click();
		WebElement  customer= driver.findElement(By.xpath("//*[@id=\"userSelect\"]"));
		//use select object to get methods as u need easier.
		Select custselect = new Select(customer);
		//custselect.selectByIndex(2);
		custselect.selectByVisibleText("Abed"+" "+"user"+userIdString);
		WebElement  currency=driver.findElement(By.xpath("//*[@id=\"currency\"]"));
		Select curelect = new Select(currency);
		curelect.selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
	 driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list");
	 //search user you have entered.
	 driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div/div/input")).sendKeys("user"+userIdString);
	List<WebElement>userSize= driver.findElements(By.xpath("//tbody/tr"));//byselectorhub 
	int totalUsers = userSize.size();
	 System.out.println("**********"+totalUsers+"**************");
	 softassert.assertEquals(totalUsers, 1);
	 softassert.assertAll();
 }
}


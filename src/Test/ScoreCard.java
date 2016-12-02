package Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class ScoreCard {

	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anamika\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.espncricinfo.com");
		driver.manage().window().maximize();		
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        WebElement LiveScore = driver.findElement(By.xpath("//li[@class='nav_grpitm nav_live_scores']//a[text()='Live Scores']"));
        WebElement LiveScoreHome = driver.findElement(By.xpath("//li[@class='sub_nav_item']//a[text()='Live Scores Home']"));
        
        
        Actions action = new Actions(driver);
        action.moveToElement(LiveScore).build().perform();
        action.click(LiveScoreHome).perform();        
       
        driver.findElement(By.xpath("//a[@href='/ci/engine/match/1065344.html'][text()='Scorecard']")).click();
		WebElement htmltable = driver.findElement(By.xpath("//table[@class='batting-table innings'][1]"));
		List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
		
		for(int i=1; i<rows.size()-1; i++)
		{
			List<WebElement> coloumn = rows.get(i).findElements(By.tagName("td"));
		
			for(int j=3;j<coloumn.size();j++)
			{
					System.out.println(coloumn.get(j).getText());
					if(coloumn.get(j).getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) 
					{
						System.out.println("Is a number");
					}else 
					{
						System.out.println("Is not a number");
					}
			}
		}
	}
}

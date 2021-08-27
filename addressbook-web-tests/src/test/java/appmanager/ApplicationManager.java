package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {

  WebDriver wd;

  private SessionHelper sessionHelper;
  private GroupHelper groupHelper;
  private NavigationHelper navigationHelper;

  public void init() {
    wd = new FirefoxDriver();
    wd.get("http://localhost/addressbook/");
    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void goToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void saveContact() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
  }

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void stop() {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }
}

package tests;

import appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

  protected static ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  /*
  System.getProperty даёт возможность указывать в конфигурации запуска класса тип запускаемого браузера
  VM options = -Dbrowser=firefox
  если в конфигурации ничего не указано, запускается дефолтный - CHROME
   */

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}

package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("mantis-tests/src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}

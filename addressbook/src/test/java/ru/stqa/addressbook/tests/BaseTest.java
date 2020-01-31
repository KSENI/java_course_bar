package ru.stqa.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.stqa.addressbook.applicationmanager.ApplicationManager;

import java.io.IOException;

@Listeners(MyTestListener.class)
public class BaseTest {

    protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestContext context) throws IOException {
        app.init();
        context.setAttribute("app","app");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}

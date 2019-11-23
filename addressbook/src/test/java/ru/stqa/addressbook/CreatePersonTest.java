package ru.stqa.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreatePersonTest {
    private WebDriver wd;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/");
        login("admin", "secret");
    }

    private void login(String userName, String password) {
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(userName);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testCreatePerson() {
        wd.get("http://localhost/");
        goToCreatePerson();
        fillPersonalData(new PersonData("FirstName", "MiddleName", "LastName", "NickName",
                "Title", "Company", "Address", "Home", "Mobile", "Work",
                "Fax", "emal", "email", "email3", "homepage", "1990",
                "1999", "address", "home", "notes"));
        submitAddedPerson();
        goToHomePage();
    }

    private void submitAddedPerson() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    private void goToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    private void fillPersonalData(PersonData personData) {
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(personData.getFirstName());
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(personData.getMiddleName());
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(personData.getLastName());
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(personData.getNickName());
        wd.findElement(By.name("title")).clear();
        wd.findElement(By.name("title")).sendKeys(personData.getTitle());
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(personData.getCompany());
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(personData.getAddress());
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(personData.getHomePhone());
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(personData.getMobilePhone());
        wd.findElement(By.name("work")).clear();
        wd.findElement(By.name("work")).sendKeys(personData.getWorkPhone());
        wd.findElement(By.name("fax")).clear();
        wd.findElement(By.name("fax")).sendKeys(personData.getFax());
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(personData.getEmail1());
        wd.findElement(By.name("email2")).clear();
        wd.findElement(By.name("email2")).sendKeys(personData.getEmail2());
        wd.findElement(By.name("email3")).clear();
        wd.findElement(By.name("email3")).sendKeys(personData.getEmail3());
        wd.findElement(By.name("homepage")).clear();
        wd.findElement(By.name("homepage")).sendKeys(personData.getHomepage());
        wd.findElement(By.name("bday")).click();
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText("1");
        wd.findElement(By.xpath("//option[@value='1']")).click();
        wd.findElement(By.name("bmonth")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("January");
        wd.findElement(By.xpath("//option[@value='January']")).click();
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(personData.getBirthday());
        wd.findElement(By.name("aday")).click();
        new Select(wd.findElement(By.name("aday"))).selectByVisibleText("2");
        wd.findElement(By.cssSelector("select[name=\"aday\"] > option[value=\"2\"]")).click();
        wd.findElement(By.name("amonth")).click();
        new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("February");
        wd.findElement(By.xpath("(//option[@value='February'])[2]")).click();
        wd.findElement(By.name("ayear")).clear();
        wd.findElement(By.name("ayear")).sendKeys(personData.getAnniversary());
        wd.findElement(By.name("new_group")).click();
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
        wd.findElement(By.xpath("(//option[@value='1'])[3]")).click();
        wd.findElement(By.name("address2")).clear();
        wd.findElement(By.name("address2")).sendKeys(personData.getAddress2());
        wd.findElement(By.name("phone2")).clear();
        wd.findElement(By.name("phone2")).sendKeys(personData.getHomePhone2());
        wd.findElement(By.name("notes")).clear();
        wd.findElement(By.name("notes")).sendKeys(personData.getNotes());
    }

    private void goToCreatePerson() {
        wd.findElement(By.linkText("add new")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        wd.quit();
    }
}

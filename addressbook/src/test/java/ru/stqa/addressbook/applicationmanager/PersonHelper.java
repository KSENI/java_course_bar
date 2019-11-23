package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.PersonData;

public class PersonHelper extends BaseHelper {
    public PersonHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitAddedPerson() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillPersonalData(PersonData personData) {
        type(By.name("firstname"), personData.getFirstName());
        type(By.name("middlename"), personData.getMiddleName());
        type(By.name("lastname"), personData.getLastName());
        type(By.name("nickname"), personData.getNickName());
        type(By.name("title"), personData.getTitle());
        type(By.name("company"), personData.getCompany());
        type(By.name("address"), personData.getAddress());
        type(By.name("home"), personData.getHomePhone());
        type(By.name("mobile"), personData.getMobilePhone());
        type(By.name("work"), personData.getWorkPhone());
        type(By.name("fax"), personData.getFax());
        type(By.name("email"), personData.getEmail1());
        type(By.name("email2"), personData.getEmail2());
        type(By.name("email3"), personData.getEmail3());
        type(By.name("homepage"), personData.getHomepage());
        select(By.name("bday"), "1", By.xpath("//option[@value='1']"));
        select(By.name("bmonth"), "January", By.xpath("//option[@value='January']"));
        type(By.name("byear"), personData.getBirthday());
        select(By.name("aday"), "2", By.cssSelector("select[name=\"aday\"] > option[value=\"2\"]"));
        select(By.name("amonth"), "February", By.xpath("(//option[@value='February'])[2]"));
        type(By.name("ayear"), personData.getAnniversary());
        type(By.name("address2"), personData.getAddress2());
        type(By.name("phone2"), personData.getHomePhone2());
        type(By.name("notes"), personData.getNotes());
    }
}

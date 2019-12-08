package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.PersonData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonHelper extends BaseHelper {
    PersonHelper(WebDriver wd) {
        super(wd);
    }

    private void submitAddedPerson() {
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

    public void initModificationPerson() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitModifiedPerson() {
        click(By.name("update"));
    }

    public void selectFirstPerson() {
        click(By.xpath("//td/input"));
    }

    public void deleteSelectedPerson() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void createPerson(PersonData person) {
        fillPersonalData(person);
        submitAddedPerson();
    }

    public boolean isHavePerson() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<PersonData> getPersons() {
        List<PersonData> persons = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            persons.add(new PersonData(firstName, null, lastName, null, null, null,
                    null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null));
        }
        return persons;
    }

    public void sortBeforeAndAfterPerson(List<PersonData> beforePersons, List<PersonData> afterPersons) {
        Comparator<? super PersonData> byLastName = Comparator.comparing(PersonData::getLastName);
        beforePersons.sort(byLastName);
        afterPersons.sort(byLastName);
    }
}

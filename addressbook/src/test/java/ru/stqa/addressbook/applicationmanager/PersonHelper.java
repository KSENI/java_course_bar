package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;

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
        type(By.name("address"), personData.getAddress());
        type(By.name("home"), personData.getHomePhone());
        type(By.name("mobile"), personData.getMobilePhone());
        type(By.name("work"), personData.getWorkPhone());
        type(By.name("email"), personData.getEmail1());
        type(By.name("email2"), personData.getEmail2());
        type(By.name("email3"), personData.getEmail3());
        type(By.name("homepage"), personData.getHomepage());
    }

    public void initModificationPerson() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitModifiedPerson() {
        click(By.name("update"));
    }

    public void deleteSelectedPerson(PersonData deletedPerson) {
        click(By.cssSelector("td input[id='" + deletedPerson.getId() + "']"));
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

    public Persons getPersons() {
        Persons persons = new Persons();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center input")).getAttribute("value"));
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            persons.add(new PersonData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return persons;
    }

    public String getPhoneNumbersInTableForFirstPerson() {
        return getText(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[6]"));
    }

    public String getPhoneNumbersInPersonPageForFirstPerson() {
        String homePhone = getValue(By.cssSelector("input[name=home]"));
        String mobilePhone = getValue(By.cssSelector("input[name=mobile]"));
        String workPhone = getValue(By.cssSelector("input[name='work']"));
        return homePhone + mobilePhone + workPhone;
    }

    public String getAddressInTableForFirstPerson() {
        return getText(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[4]"));
    }

    public String getAddressInPersonPageForFirstPerson() {
        return getValue(By.cssSelector("textarea[name=address]"));
    }

    public String getEmailsInTableForFirstPerson() {
        return getText(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[5]"));
    }

    public String getEmailsInPersonPageForFirstPerson() {
        String email1 = getValue(By.name("email"));
        String email2 = getValue(By.name("email2"));
        String email3 = getValue(By.name("email3"));
        return email1 + email2 + email3;
    }
}

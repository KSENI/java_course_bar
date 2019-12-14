package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;

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

    public int selectFirstPersonAndReturnId() {
        By locator = By.xpath("//td/input");
        int id = Integer.parseInt(wd.findElement(locator).getAttribute("id"));
        click(locator);
        return id;
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

    public Persons getPersons() {
        Persons persons = new Persons();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            persons.add(new PersonData().withFirstName(firstName).withLastName(lastName));
        }
        return persons;
    }

    public void sortBeforeAndAfterPerson(List<PersonData> beforePersons, List<PersonData> afterPersons) {
        Comparator<? super PersonData> byLastName = Comparator.comparing(PersonData::getLastName);
        beforePersons.sort(byLastName);
        afterPersons.sort(byLastName);
    }
}

package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;

import java.util.*;

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

    public void initModificationPerson(PersonData contact) {
        click(By.cssSelector("td input[id='" + contact.getId() + "']"));
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

    public PersonData infoFromEditForm(PersonData contact) {
        initModificationPerson(contact);

        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new PersonData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
    }

    public Set<PersonData> all() {
        Set<PersonData> contacts = new HashSet<PersonData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contacts.add(new PersonData().withId(id).withLastName(lastName).withFirstName(firstName)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
        }
        return contacts;
    }

    public int getMaxId() {
        List<WebElement> persons = wd.findElements(By.cssSelector("tr input[type='checkbox']"));
        List<Integer> allIds = new ArrayList<>();
        for (WebElement person : persons) {
            allIds.add(Integer.parseInt(person.getAttribute("value")));
        }
        return Collections.max(allIds);
    }

    public void addGroupToPerson(PersonData person) {
        click(By.cssSelector("td input[id='" + person.getId() + "']"));
        click(By.cssSelector(".right input[type='submit']"));
    }

    public void deleteGroup(PersonData person) {
        Groups allGroups = person.getGroups();

        GroupData groups = (GroupData) allGroups.toArray()[0];
        String groupName = groups.getGroupName();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
        click(By.cssSelector("td input[id='" + person.getId() + "']"));
        click(By.cssSelector(".left input[name='remove']"));

    }

    public void selectElementById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void selectSituatedGroupFromList(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getGroupId()));
        click(By.xpath("//div[@id='content']/form[2]/div[4]/input"));
    }

    public void contactsFilterByGroup(int id) {
        wd.findElement(By.xpath("//select[@name='group']")).click();
        wd.findElement(By.xpath("//option[@value='" + id + "']")).click();
    }

    public void deleteContactFromGroupButton() {
        click(By.name("remove"));
    }
}

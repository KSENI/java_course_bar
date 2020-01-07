package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DeleteGroupInPerson extends BaseTest {
    GroupData groupToAdded;

    @BeforeMethod
    public void ensureGroupPreconditions() {
        if (checkContacts().size() == 0) { //контактов нет
            createContactAndReturnCreatedContact();
        }
        if (checkGroups().size() == 0) { //групп нет
            groupToAdded = createGroupAndReturnCreatedGroup();
        }
    }

    @Test
    public void personDeleteInGroupsTest() {
        if (app.getDbHelper().situatedGroupForRemoveContact() == null) { //нет группы с контактами
            app.goTo().goToHomePage();
            app.getPersonHelper().createPerson(new PersonData()
                    .withFirstName("name").withLastName("last name").withHomePhone("123")
                    .withEmail1("fsdf.sdfsdf@dffd.com").withAddress("address")
                    .inGroup(app.getDbHelper().groups().iterator().next()));
        }

        Persons beforeContacts = app.getDbHelper().situatedGroupForRemoveContact().getPersons();
        int situatedGroupIDForRemoveContact = app.getDbHelper().situatedGroupForRemoveContact().getGroupId();

        app.goTo().goToHomePage();
        app.getPersonHelper().contactsFilterByGroup(situatedGroupIDForRemoveContact);
        PersonData selectedContact = beforeContacts.iterator().next();
        app.getPersonHelper().selectElementById(selectedContact.getId());
        app.getPersonHelper().deleteContactFromGroupButton();
        app.goTo().goToHomePage();

        Persons afterContacts = app.getDbHelper().getGroupFromDb(situatedGroupIDForRemoveContact).getPersons();

        assertThat(afterContacts, equalTo(beforeContacts.without(selectedContact)));
    }

    public Persons checkContacts() {
        return app.getDbHelper().persons();
    }

    public void createContactAndReturnCreatedContact() {
        app.goTo().goToHomePage();
        PersonData personToAddGroup = new PersonData().withFirstName("New First Name").withLastName("New Last Name")
                .withHomePhone("+7-555-555-new").withMobilePhone("66 666 66-new").withWorkPhone("+7(909)-66-new")
                .withAddress("Address").withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com")
                .withEmail3("df@df.co").withHomepage("new homepage sdfsdfsdf.com").withMiddleName("New middle Name");
        app.goTo().goToCreatePerson();
        app.getPersonHelper().createPerson(personToAddGroup);
        app.goTo().goToHomePage();
        int maxId = app.getPersonHelper().getMaxId();
        personToAddGroup.withId(maxId);
    }

    public Groups checkGroups() {
        return app.getDbHelper().groups();
    }

    public GroupData createGroupAndReturnCreatedGroup() {
        app.goTo().goToGroupPage();
        groupToAdded = new GroupData().withGroupFooter("footer").withGroupHeader("header").withGroupName("name");
        int idCreatedGroup = app.getGroupHelper().createGroupAndReturnId(groupToAdded);
        groupToAdded.withGroupId(idCreatedGroup);
        app.goTo().goToGroupPage();
        return groupToAdded;
    }

}

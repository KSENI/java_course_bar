package ru.stqa.addressbook.tests.personTests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePersonTest extends BaseTest {
    @DataProvider
    public Iterator<Object[]> validPerson() throws IOException {
        String pathToFile = "src/test/java/ru/stqa/addressbook/resources/persons.json";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<PersonData> groups = gson.fromJson(json.toString(), new TypeToken<List<PersonData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validPerson")
    public void testCreatePerson(PersonData addedPerson) {
        app.goTo().goToHomePage();
        int maxId = app.getPersonHelper().getMaxId();
        Persons beforePersons = app.getDbHelper().persons();
        app.goTo().goToCreatePerson();

        app.getPersonHelper().createPerson(addedPerson.withId(maxId + 1));
        app.goTo().goToHomePage();

        Persons afterPersons = app.getDbHelper().persons();
        assertThat(afterPersons, equalTo(beforePersons.withAdded(addedPerson)));
    }
}

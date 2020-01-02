package ru.stqa.addressbook.tests.groupTests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
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

public class CreationGroupTests extends BaseTest {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        String pathToFile = "src/test/java/ru/stqa/addressbook/resources/group.json";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData addedGroup) {
        app.goTo().goToGroupPage();
        Groups beforeGroup = app.getDbHelper().groups();

        int idCreatedGroup = app.getGroupHelper().createGroupAndReturnId(addedGroup);
        addedGroup.withGroupId(idCreatedGroup);
        app.goTo().goToGroupPage();

        Groups afterGroup = app.getDbHelper().groups();
        assertThat(afterGroup, equalTo(beforeGroup.withAdded(addedGroup)));
    }
}

package ru.stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.addressbook.model.PersonData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PersonDataGenerator {
    @Parameter(names = "-c", description = "groups count")
    public int count;
    @Parameter(names = "-f", description = "file address")
    public String file;

    public static void main(String[] args) throws IOException {
        PersonDataGenerator generator = new PersonDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (Exception e) {
            jCommander.usage();
            return;
        }
        new JCommander(generator, args);
        generator.run();
    }

    private void run() throws IOException {
        List<PersonData> persons = generatePerson(count);
        save(persons, new File(file));
    }

    private void save(List<PersonData> persons, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(persons);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private List<PersonData> generatePerson(int count) {
        List<PersonData> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            persons.add(new PersonData().
                    withFirstName("First Name " + i).withLastName("Last Name " + i)
                    .withHomePhone("+7 (909)" + i).withMobilePhone("8-908-" + i).withWorkPhone("BD-" + i)
                    .withEmail1(i + "1dfsdf@dsfsdf.com").withEmail2(i + "2dfsdf@dsfsdf.com").withEmail3(i + "3dfsdf@dsfsdf.com")
                    .withAddress("address " + i).withHomepage("home page" + i).withMiddleName("middle name" + i));
        }
        return persons;
    }
}

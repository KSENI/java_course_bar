package ru.stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
    @Parameter(names = "-c", description = "groups count")
    public int count;
    @Parameter(names = "-f", description = "file address")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        List<GroupData> groups = generateGroups(count);

        if (format.equals("xml")) {
            saveAsXml(groups, new File(file));
        } else if (format.equals("json")) {
            saveJson(groups, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withGroupName("name " + i).withGroupHeader("header " + i).withGroupFooter("footer " + i));
        }
        return groups;
    }
}

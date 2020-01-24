package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TestBase {
    private final Properties properties;

    public TestBase() {
        properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void initProperties() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("rest-sample/src/test/resources/%s.properties", target))));
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String status = issueStatus(issueId);
        if (!status.equals("Closed")) {
            return true;
        }
        return false;
    }

    protected Executor getExecutor() {
        return Executor.newInstance().auth(getProperty("loginUsername"), getProperty("passwordUsername"));
    }

    private String issueStatus(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(getProperty("baseUrlIssueStatus") + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
        String issueStatus = issue.iterator().next().getState_name();
        return issueStatus;
    }
}


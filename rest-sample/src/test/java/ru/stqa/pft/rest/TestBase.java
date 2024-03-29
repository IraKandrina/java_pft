package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.util.Set;

public class TestBase {

    @BeforeSuite
    public void setUp() throws Exception{
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public boolean isIssueOpen(int issueId) {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues/"
                + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issuesAsJson = parsed.getAsJsonObject()
                .get("issues");

        Set<Issue> issues = new Gson().fromJson(issuesAsJson,
                new TypeToken<Set<Issue>>() {}.getType());
        Issue issue = issues.iterator().next();

        if (issue.getStateName().equals("Resolved") || issue.getStateName().equals("Closed")) {
            return false;
        }
        return true;
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
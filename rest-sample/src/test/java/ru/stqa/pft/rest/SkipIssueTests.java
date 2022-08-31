package ru.stqa.pft.rest;

import org.testng.annotations.Test;

public class SkipIssueTests extends TestBase {

    @Test
    public void testSkip() {
        int skippedIssueId = 2167;
        skipIfNotFixed(skippedIssueId);
    }
}

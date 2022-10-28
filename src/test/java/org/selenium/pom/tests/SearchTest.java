package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {
        String searchFor = "Blue";

        StorePage storePage = new StorePage(getDriver()).load();
        storePage.search(searchFor);
        storePage.waitSearchPageLoaded();
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
    }
}

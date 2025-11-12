package com.examples.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.title;

public class CMoviePage {
    private static  ElementsCollection topCastList;
    private static String sThirdTopCastName;
    private static final String TOP_CAST_SECTION_SELECTOR  = "[data-testid='title-cast']";
    private static final String TOP_CAST_MEMBERS_SELECTOR  = "a[data-testid='title-cast-item__actor']";

    private static final Logger LOGGER = LoggerFactory.getLogger(CMoviePage.class);

    @Step("Scroll to the Top Cast section")
    public void scrollToTheTopCastSection() {
        $(TOP_CAST_SECTION_SELECTOR).scrollTo().shouldBe(visible);
    }

    @Step("Verify that there are more than 3 members in the Top Cast section")
    public void verifyTopCastSection() {
        topCastList = getListOfTopCastSectionMembers();
        Assert.assertTrue(topCastList.size() > 3,
                "Top Cast members' number is smaller than 3, actual: " + topCastList.size());
    }

    @Step("Click on the 3rd cast member")
    public void clickOnTheThirdCastMember() {
        sThirdTopCastName = topCastList.get(2).getText();
        LOGGER.info("Third cast name is: {}", sThirdTopCastName);
        topCastList.get(2).shouldBe(Condition.visible).click();
    }

    @Step("Verify that the correct profile page has opened")
    public void verifyTheCorrectProfilePageOpened() {
        String sProfilePageTitle = title();
        LOGGER.info("Profile page title is: {}", sProfilePageTitle);
        Assert.assertNotNull(sProfilePageTitle, "Profile page title is null");
        Assert.assertTrue(sProfilePageTitle.contains(sThirdTopCastName),
                "Profile page title does not match, expected: " + sThirdTopCastName + ", actual: " + sProfilePageTitle);
    }

    private ElementsCollection getListOfTopCastSectionMembers() {
        return $$(TOP_CAST_MEMBERS_SELECTOR);
    }
}

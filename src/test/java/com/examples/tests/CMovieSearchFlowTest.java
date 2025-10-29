package com.examples.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.examples.pages.CImdbMainPage;
import com.examples.pages.CMoviePage;


import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.title;

@Listeners({AllureTestNg.class})
public class CMovieSearchFlowTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CMovieSearchFlowTest.class);

    @BeforeClass
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @AfterClass
    public void afterHook() {
        closeWebDriver();
    }

    @Test
    @Description("Verify Imdb movie search flow")
    public void movieSearchVerified() {

        CImdbMainPage imdbMainPage = new CImdbMainPage();
        imdbMainPage.openImdbMainPage();

        imdbMainPage.clickOnTheSearchBar();
        imdbMainPage.searchMovieByText("QA");

        ElementsCollection movieList = imdbMainPage.getListOfMovies();
        String sFirstMovieName = movieList.first().getText();
        LOGGER.info("First movie name is: {}", sFirstMovieName);
        movieList.first().shouldBe(Condition.visible).click();

        String sMoviePageTitle = title();
        LOGGER.info("Movie page title is: {}", sMoviePageTitle);
        Assert.assertNotNull(sMoviePageTitle, "Movie page title is null");
        Assert.assertTrue(sMoviePageTitle.contains(sFirstMovieName),
                "Movie Title does not match, expected: " + sFirstMovieName + ", actual: " + sMoviePageTitle);

        CMoviePage moviePage = new CMoviePage();
        moviePage.scrollToTheTopCastSection();
        ElementsCollection topCastList = moviePage.getListOfTopCastSectionMembers();
        Assert.assertTrue(topCastList.size() > 3,
                "Top Cast members' number is smaller than 3, actual: " + topCastList.size());

        String sThirdTopCastName = topCastList.get(2).getText();
        LOGGER.info("Third cast name is: {}", sThirdTopCastName);
        topCastList.get(2).shouldBe(Condition.visible).click();
        String sProfilePageTitle = title();
        LOGGER.info("Profile page title is: {}", sProfilePageTitle);
        Assert.assertNotNull(sProfilePageTitle, "Profile page title is null");
        Assert.assertTrue(sProfilePageTitle.contains(sThirdTopCastName),
                "Profile page title does not match, expected: " + sThirdTopCastName + ", actual: " + sProfilePageTitle);
    }
}
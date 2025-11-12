package com.examples.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.examples.pages.CImdbMainPage;
import com.examples.pages.CMoviePage;


import static com.codeborne.selenide.Selenide.closeWebDriver;

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
        LOGGER.info("IMDb movie search flow test verification started.");

        CImdbMainPage imdbMainPage = new CImdbMainPage();
        imdbMainPage.openImdbMainPage();
        imdbMainPage.clickOnTheSearchBar();
        imdbMainPage.searchMovieByText("QA");
        imdbMainPage.saveTheFirstMovieNameFromDropdown();
        imdbMainPage.clickOnTheFirstMovie();
        imdbMainPage.verifyTheFirstMovieTitle();

        CMoviePage moviePage = new CMoviePage();
        moviePage.scrollToTheTopCastSection();
        moviePage.verifyTopCastSection();
        moviePage.clickOnTheThirdCastMember();
        moviePage.verifyTheCorrectProfilePageOpened();

        LOGGER.info("IMDb movie search flow test verification ended.");
    }
}
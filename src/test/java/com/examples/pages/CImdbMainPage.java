package com.examples.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class CImdbMainPage {
    private static String sFirstMovieName;
    private static final String IMDB_MAIN_PAGE_URL = "https://www.imdb.com";
    private static final String SEARCH_BAR_ID = "#suggestion-search";
    private static final String MOVIE_LIST_SELECTOR = "li[role='option'] a[href*='/title/'] .searchResult__constTitle";

    private static final Logger LOGGER = LoggerFactory.getLogger(CImdbMainPage.class);

    @Step("Open IMDb main page")
    public void openImdbMainPage() {
        open(IMDB_MAIN_PAGE_URL);
    }

    @Step("Click on search bar")
    public void clickOnTheSearchBar() {
        $(SEARCH_BAR_ID).click();
    }

    @Step("Search for movie by text: {text}")
    public void searchMovieByText(String sText) {
        $(SEARCH_BAR_ID).type(sText);
    }

    @Step("Save the first movie name from dropdown")
    public void saveTheFirstMovieNameFromDropdown() {
        sFirstMovieName = getListOfMovies().first().getText();
        LOGGER.info("First movie name is: {}", sFirstMovieName);
    }

    @Step("Click on the first movie title")
    public void clickOnTheFirstMovie() {
        getListOfMovies().first().shouldBe(Condition.visible).click();
    }

    @Step("Verify that the movie page title matches the saved movie name")
    public void verifyTheFirstMovieTitle() {
        String sMoviePageTitle = title();
        LOGGER.info("Movie page title is: {}", sMoviePageTitle);
        Assert.assertNotNull(sMoviePageTitle, "Movie page title is null");
        Assert.assertTrue(sMoviePageTitle.contains(sFirstMovieName),
                "Movie Title does not match, expected: " + sFirstMovieName + ", actual: " + sMoviePageTitle);
    }

    private ElementsCollection getListOfMovies() {
        return $$(MOVIE_LIST_SELECTOR);
    }
}
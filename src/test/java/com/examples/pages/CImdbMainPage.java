package com.examples.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CImdbMainPage {
    private static final String IMDB_MAIN_PAGE_URL = "https://www.imdb.com";
    private static final String SEARCH_BAR_ID = "#suggestion-search";
    private static final String MOVIE_LIST_SELECTOR = "li[role='option'] a[href*='/title/'] .searchResult__constTitle";

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

    @Step("Get search results")
    public ElementsCollection getListOfMovies() {
        return $$(MOVIE_LIST_SELECTOR);
    }
}
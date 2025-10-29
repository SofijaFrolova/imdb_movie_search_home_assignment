package com.examples.pages;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CMoviePage {
    private static final String TOP_CAST_SECTION_SELECTOR  = "[data-testid='title-cast']";
    private static final String TOP_CAST_MEMBERS_SELECTOR  = "a[data-testid='title-cast-item__actor']";

    @Step("Scroll to the Top Cast section")
    public void scrollToTheTopCastSection() {
        $(TOP_CAST_SECTION_SELECTOR).scrollTo().shouldBe(visible);
    }

    @Step("Get list of Top Cast members")
    public ElementsCollection getListOfTopCastSectionMembers() {
        return $$(TOP_CAST_MEMBERS_SELECTOR);
    }
}

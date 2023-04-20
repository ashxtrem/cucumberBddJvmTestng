package com.cucumberTestng.pageObjects;

import com.cucumberTestng.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListingPage extends BasePage {

    public ListingPage(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
    }

    @FindBy(id = "search")
    private WebElement searchDiv;

    public boolean validatePageLoad() {
        return searchDiv.isDisplayed();
    }
}

package com.cucumberTestng.pageObjects;

import com.cucumberTestng.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
    }
    
    @FindBy(xpath = "//textarea[@title='Search']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@value='Google Search']")
    private WebElement searchBtn;

    public void visitUrl(String url) {
        navigate(url);
    }

    public void searchText(String searchText) {
        waitForElement(searchBox, 5);
        setElementText(searchBox, searchText);
        clickButton(searchBtn);
    }
}

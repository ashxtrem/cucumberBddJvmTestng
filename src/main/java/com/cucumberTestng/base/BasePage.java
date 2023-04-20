package com.cucumberTestng.base;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	public JavascriptExecutor jse;

	// create constructor
	public BasePage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	protected void navigate(String url) {
		driver.navigate().to(url);
	}

	protected static void clickButton(WebElement button)
	{
		button.click();
	}
	protected static String getElementText(WebElement element)
	{
		return element.getText();
	}
	protected static void setElementText(WebElement elementToSet , String value )
	{
		elementToSet.sendKeys(value);
	}

	protected void clickByFindElement(By xpath) {
		driver.findElement(xpath).click();
	}

	public void waitForElement(WebElement ele, int time) {
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void moveToElement(String element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = String.format("document.querySelector('%s').scrollBy(0,100)",element);
		js.executeScript(script);
	}

}



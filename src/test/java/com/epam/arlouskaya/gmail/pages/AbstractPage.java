package com.epam.arlouskaya.gmail.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

	protected WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public Boolean isElementPresent(By locator) {
		return this.driver.findElements(locator).size() > 0;
	}

	private ExpectedCondition<WebElement> visibilityOfElement(final By locator) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver dr) {
				WebElement toReturn = dr.findElement(locator);
				if (toReturn.isDisplayed()){
					return toReturn;
				}
				return null;
			}
		};
	}
	
	public void waitForElementIsDisplayed(By locator){
		Wait wait = new WebDriverWait(driver, 20);
		wait.until(visibilityOfElement(locator));
	}
	

	
}

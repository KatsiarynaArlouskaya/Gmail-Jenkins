package com.epam.arlouskaya.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPromtPage extends AbstractPage{
	
	@FindBy(id = "cancel")
	private WebElement btnCancel;

	public AccountPromtPage(WebDriver driver) {
		super(driver);
	}

	public boolean cancel() {
		btnCancel.click();
		return true;
	}

}

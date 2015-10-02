package com.epam.arlouskaya.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertWindow extends AbstractPage{
	
	
	By alertDialog = By.xpath("//div[@role='alertdialog']");
	
	@FindBy(xpath="//span[@class='Kj-JD-K7-K0']")
	WebElement alertMsg;
	public AlertWindow(WebDriver driver) {
		super(driver);
	}
	
	public boolean isAlertWithMsgPresent(String msg) {
		if (isElementPresent(alertDialog)){
			if (alertMsg.getText().contains(msg)){
				return true;
			}
		}
		return false;
	}

	
}

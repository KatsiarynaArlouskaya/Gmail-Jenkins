package com.epam.arlouskaya.gmail.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.arlouskaya.gmail.util.Utils;

public class ThemePage extends AbstractPage{
	private static final Logger logger = LogManager.getLogger(ThemePage.class.getName());

	@FindBy(xpath="//div[text()='My Photos']")
	WebElement btnMyPhotos;
	
	@FindBy(xpath="//div[text()='Upload a photo']")
	WebElement tabUploadPhoto;
	
	@FindBy(className="Xf-dn-wn")
	WebElement btnSelectPhoto;
	
	@FindBy(xpath="//span[@aria-label='Close']")
	WebElement btnClose;
	
	@FindBy(xpath="//div[@class='a4t']")
	WebElement backgroundPict;

	
	public ThemePage(WebDriver driver) {
		super(driver);
	}

	public void clickToMyPhotos() {
		btnMyPhotos.click();
		waitForElementIsDisplayed(By.xpath("//iframe[@class='KA-JQ']"));
		logger.info("iframe 'Select your background image' is upload");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='KA-JQ']")));
		waitForElementIsDisplayed(By.xpath("//div[@class='Xf-cg-dg Xf-Zf-Xb-dg']"));
		logger.info("swich to iframe 'Select your background image'");
		
	}
	

	public void gotoUploadPhoto() {
		tabUploadPhoto.click();		
	}

	public void clickToSelectPhoto() {
		btnSelectPhoto.click();
		
	}

	public void uploadPhoto(String pathToNotPhoto) {
		Utils.uploadFile(pathToNotPhoto);
		
	}

	public void chooseTheme(String urlPict) {	
		waitForElementIsDisplayed(By.xpath("//img[contains(@src,'"+urlPict+"')]"));
		WebElement themePict = driver.findElement(By.xpath("//img[contains(@src,'"+urlPict+"')]"));
		logger.info("url pict is "+urlPict);
		themePict.click();	
		btnClose.click();
		waitForElementIsDisplayed(By.xpath("//div[contains(@style,'"+urlPict+"')]"));	
	}
	
	public boolean checkTheme(String urlPict) {	
		return isElementPresent(By.xpath("//div[contains(@style,'"+urlPict+"')]"));
	}
}

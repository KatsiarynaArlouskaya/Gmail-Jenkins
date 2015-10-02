package com.epam.arlouskaya.gmail.pages;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.arlouskaya.gmail.util.Utils;

public class InboxPage extends AbstractPage{
	private static final Logger logger = LogManager.getLogger(InboxPage.class.getName());
	
	
	@FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
	WebElement btnCompose;
	
	@FindBy(xpath = "//textarea[@name='to']")
	WebElement inputTo;
	
	@FindBy(xpath = "//input[@placeholder='Subject']")
	WebElement inputSubject;
	
	@FindBy(xpath = "//div[@role='textbox']")
	WebElement inputMsg;
	
	@FindBy(xpath = "//div[@aria-label='Attach files']")
	WebElement btnAttach;
	
	@FindBy(xpath = "//div[contains(@aria-label,'Insert emoticon')]")
	WebElement btnEmotIcons;
	
	@FindBy(xpath="//div[@class='a8E']/button[2]")
	WebElement btnShowFacesEmotIcons;
	
	@FindBy(className="a8o")
	WebElement btnCloseEmot;
	
	@FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
	WebElement btnSend;
	
	@FindBy(xpath = "//a[contains(@title,'Google Account')]")
	WebElement btnAccount;
	
	@FindBy(linkText = "Sign out")
	WebElement btnSignOut;
	
	@FindBy(xpath = "//span[text()='More']")
	WebElement btnMoreInLeftMenu;
	
	@FindBy(linkText = "Spam")
	WebElement btnSpam;
	
	@FindBy(linkText = "Trash")
	WebElement btnTrash;
	
	@FindBy(xpath = "//a[contains(@title,'Inbox')]")
	WebElement btnInbox;
	
	@FindBy(xpath = "//div[@class='T-I J-J5-Ji ash T-I-ax7 L3']")
	WebElement btnSettings;
	
	@FindBy(id="ms")
	WebElement btnSettingsInSettings;
	
	@FindBy(id="pbwc")
	WebElement btnThemesInSettings;
	

	

	public InboxPage(WebDriver driver) {
		super(driver);
	}
	
	//Create different messages
	public void createNewMsg(String receiver, String msg){
		btnCompose.click();
		waitForElementIsDisplayed(By.xpath("//textarea[@name='to']"));		
		inputTo.sendKeys(receiver);
		inputSubject.sendKeys(msg);
		inputMsg.sendKeys(msg);
	}
	
	public void addAttach(String pathToAtt) {
		btnAttach.click();
		Utils.uploadFile(pathToAtt);		
	}
	
	public void addEmotIcons(int numberOfIcons) {
		btnEmotIcons.click();
		btnShowFacesEmotIcons.click();
		List<WebElement> btnEmot= driver.findElements(By.className("a8m"));
		for (int i = 0; i < numberOfIcons; i++) {
			btnEmot.get(i).click();
		}
		btnCloseEmot.click();		
	}
	public Boolean checkNumberIcon(int numberOfIcons) {
		List<WebElement> btnEmot= inputMsg.findElements(By.xpath("//img[contains(@src, 'emoji')]"));
		logger.info("Number of icons = "+btnEmot.size());
		return numberOfIcons==btnEmot.size();
	}
	
	public void sendMsg(){
		btnSend.click();	
		waitForElementIsDisplayed(By.xpath("//div[contains(text(),'Your message has been sent.')]"));
	}
	
	//go to letter

	public void goToLetter(String user) {
		if (driver.findElement(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']")).isDisplayed()){
			driver.findElement(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']//div[@class='yW']/span[@email='"+user+"']")).click();
			logger.info("letter in inbox");
		}
		else
			if (driver.findElement(By.xpath("//div[@class='BltHke nH oy8Mbf']")).isDisplayed()){
				driver.findElement(By.xpath("//div[@class='BltHke nH oy8Mbf']//div[@class='yW']/span[@email='"+user+"']")).click();
				logger.info("letter in trash");
			}
		waitForElementIsDisplayed(By.xpath("//img[@data-tooltip='Show details']"));
	}
	
	//switch between different folders in left side
	
	public void goToSpamFolder() {
		btnMoreInLeftMenu.click();
		btnSpam.click();	
		waitForElementIsDisplayed(By.xpath("//div[@class='BltHke nH oy8Mbf']"));
	}
	
	public void goToInboxFolder() {
		btnInbox.click();
		waitForElementIsDisplayed(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']"));
	}
	
	public void goToTrashFolder() {
		btnMoreInLeftMenu.click();
		btnTrash.click();
		waitForElementIsDisplayed(By.xpath("//div[@class='BltHke nH oy8Mbf']"));
	}
	
	// check, is e-mail present in a chosen folder 
	
	public boolean isEmailPresent(String user) {
		return isElementPresent(By.xpath("//span[@email='"+user+"']"));
	}

	//go to different Settings
	
	public void clickBtnSettings() {
		btnSettings.click();		
	}
	
	public void chooseInSettingsItemSettings() {
		btnSettingsInSettings.click();		
	}

	public void chooseInSettingsItemThemes() {
		btnThemesInSettings.click();
		waitForElementIsDisplayed(By.xpath("//div[text()='My Photos']"));
		logger.info("Page Theme Settings is upload");
	}













}

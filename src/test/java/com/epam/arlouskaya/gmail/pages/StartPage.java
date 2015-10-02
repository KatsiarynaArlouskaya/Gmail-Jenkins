package com.epam.arlouskaya.gmail.pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.epam.arlouskaya.gmail.steps.Steps;

public class StartPage extends AbstractPage{
	
	private static final Logger logger = LogManager.getLogger(StartPage.class.getName());
	public static final String BASE_URL = "https://gmail.com/";
	private final String ACCOUNT_RECOVERY_PROMT = "AccountRecoveryOptionsPrompt";
	private final String CHECKED = "checked";

	@FindBy(id = "Email")
	private WebElement inputEmail;
	
	@FindBy(id = "Passwd")
	private WebElement inputPasswd;
	
	@FindBy(id = "next")
	private WebElement btnNext;
	
	@FindBy(id = "signIn")
	private WebElement btnSignIn;

	@FindBy(id = "PersistentCookie")
	private WebElement chbxCookies;
	
	public StartPage(WebDriver driver) {
		super(driver);
	}

	public void openPage(){
		driver.navigate().to(BASE_URL);
	}

	public void signIn(String username, String password) {	
		if (driver.getCurrentUrl().contains("about")){
			driver.findElement(By.id("gmail-sign-in")).click();
			logger.info("by check url, go to sign in");
		}
		inputEmail.sendKeys(username);
		btnNext.click();
		if (chbxCookies.getAttribute(CHECKED)!=null){
			chbxCookies.click();
		}
		inputPasswd.sendKeys(password);
		btnSignIn.click();
		if (driver.getCurrentUrl().contains(ACCOUNT_RECOVERY_PROMT)){
			logger.info("ACCOUNT_RECOVERY_PROMT");
			AccountPromtPage accountPromtPage = new AccountPromtPage(driver);
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (isElementPresent(By.xpath("//a[text()='"+username+"']"))){
			logger.info("Sign in "+username+" is OK");
		}
		else {
			logger.info("Open another page "+driver.getTitle()+" url:"+driver.getCurrentUrl());
		}
	
	}
	
	public void signOut(){	
		try {
			driver.manage().deleteAllCookies();
			logger.info("Cookies have been removed");
		} catch (org.openqa.selenium.UnhandledAlertException e) {
			logger.info("Allert was accept. Allert:" + driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		}
		if (ExpectedConditions.alertIsPresent().apply(driver) != null){
			logger.info("Allert was accept. Allert:" + driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		}
	}
	

	
	
	
}

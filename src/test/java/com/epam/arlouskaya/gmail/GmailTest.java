package com.epam.arlouskaya.gmail;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.epam.arlouskaya.gmail.steps.Steps;
import com.epam.arlouskaya.gmail.util.Utils;
import com.gargoylesoftware.htmlunit.WebConsole.Logger;

public class GmailTest {

	private Steps steps;
	private static final String USER2 = "orlkat201501@gmail.com";
	private static final String USER1 = "orlkat201502@gmail.com";
	private static final String USER3 = "orovsk201501@gmail.com";
	private static final String PASSWORD = "epam2015";
	private static final int LENGHT_MSG = 10;
	private static final String NAME_MEETING = "this is meeting";
	private static final String LOCATION_MEETING = "Minsk, K1/2, 104";
	private static final String DESCRIPTION_MEETING = "this is description";

	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.initBrowser();

	}

	@Test(enabled = false)
	public void Spam() {
		steps.signIn(USER1, PASSWORD);
		steps.sendMsg(USER2, Utils.getRandomString(LENGHT_MSG));
		steps.signOut();
		steps.signIn(USER2, PASSWORD);
		steps.markLetterAsSpam(USER1);
		steps.signOut();
		steps.signIn(USER1, PASSWORD);
		steps.sendMsg(USER2, Utils.getRandomString(LENGHT_MSG));
		steps.signOut();
		steps.signIn(USER2, PASSWORD);
		Assert.assertTrue(steps.checkSpamFrom(USER1));
	}

	@Test(enabled = false)
	public void Forward() {
		long sizeFile=10L;
		steps.signIn(USER2, PASSWORD);
		steps.clickBtnSettings();
		steps.chooseSettingsInSettings();
		steps.chooseForwardTab();
		steps.setForwardTo(USER3);
		steps.signOut();
		steps.signIn(USER3, PASSWORD);
		steps.acceptForwardAndSignOut(USER2, PASSWORD);
		steps.signIn(USER2, PASSWORD);
		steps.clickBtnSettings();
		steps.chooseSettingsInSettings();
		steps.chooseForwardTab();
		steps.choosePropertyForwardCopy();
		steps.chooseFiltersTab();
		steps.createFilter(USER1);
		steps.signOut();
		steps.signIn(USER1, PASSWORD);
		steps.sendMsgWithAttach(USER2, Utils.getRandomString(LENGHT_MSG),
				Utils.getPathToTempFileWithLength(sizeFile));
		steps.sendMsg(USER2, Utils.getRandomString(LENGHT_MSG));
		steps.signOut();
		steps.signIn(USER2, PASSWORD);
		Assert.assertTrue(steps.checkTrashFrom(USER1));
		steps.goToLetter(USER1);
		Assert.assertTrue(steps.checkLetterIsImportant(USER1));
		Assert.assertTrue(steps.checkLetterHasAtt(USER1));
		Assert.assertTrue(steps.checkInboxFrom(USER1));
		steps.goToLetter(USER1);
		Assert.assertFalse(steps.checkLetterHasAtt(USER1));
		Assert.assertTrue(steps.checkLetterIsImportant(USER1));// must be FALSE
		steps.signOut();
		steps.signIn(USER3, PASSWORD);
		Assert.assertTrue(steps.checkInboxFrom(USER1));
		Assert.assertFalse(steps.checkLetterHasAtt(USER1));
	}

	@Test(enabled = false)
	public void MainMailBox() {
		long sizeFile=30*1024L*1024L;
		String warnMsgBigFile="The file you are trying to send exceeds the 25MB";
		steps.signIn(USER1, PASSWORD);
		steps.sendMsgWithAttach(USER2, Utils.getRandomString(LENGHT_MSG),
				Utils.getPathToTempFileWithLength(sizeFile));
		Assert.assertTrue(steps.isAlertMsgPresent(warnMsgBigFile));
	}

	@Test(enabled = false)
	public void ThemesUploadFile() {
		String titleWindow = "Pick your theme";
		long sizeFile=1024L;
		String messageUploadNotSupported="is not supported for upload";
		steps.signIn(USER1, PASSWORD);
		steps.clickBtnSettings();
		steps.chooseThemesInSettings();
		Assert.assertTrue(steps.isMsgPresent(titleWindow));
		steps.selectMyPhoto(Utils.getPathToTempFileWithLength(sizeFile));
		Assert.assertTrue(steps.isMsgPresent(messageUploadNotSupported));

	}

	@Test(enabled = true)
	public void EmotIcons() {
		int NUMBER_ICONS = 3;
		steps.signIn(USER1, PASSWORD);
		Assert.assertTrue(steps.sendMsgWithEmotIcons(USER2,
				Utils.getRandomString(LENGHT_MSG), NUMBER_ICONS));
		steps.signOut();
		steps.signIn(USER2, PASSWORD);
		steps.goToLetter(USER1);
		Assert.assertTrue(steps.checkLetterTextEmotIcon(NUMBER_ICONS));
	}

	@Test(enabled = false)
	public void ThemesSelect() {
		String URL_PICT_THEME = "https://lh5.ggpht.com/r0SGNlUNQocspUn5Vq3meD_B4XCMHNzmsYX7GYs40h_cddB-3omCWopHqNGVsUqgzL5mdXFFxQ";
		steps.signIn(USER1, PASSWORD);
		steps.clickBtnSettings();
		steps.chooseThemesInSettings();
		steps.chooseTheme(URL_PICT_THEME);
		Assert.assertTrue(steps.checkTheme(URL_PICT_THEME));
	}

	@Test(enabled = false)
	public void AttMeeting() {
		steps.signIn(USER1, PASSWORD);
		steps.sendMsgWithMeeting(USER2, Utils.getRandomString(LENGHT_MSG),
				NAME_MEETING, LOCATION_MEETING, DESCRIPTION_MEETING);
		// Test is not completed
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		 steps.closeDriver();
	}

}

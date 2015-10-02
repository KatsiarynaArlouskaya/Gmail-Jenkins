package com.epam.arlouskaya.gmail.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Utils {
	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random rnd = new Random();

	public static String getRandomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	public static void uploadFile(String pathToAtt) {
		// put path to your image in a clipboard
		StringSelection ss = new StringSelection(pathToAtt);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		// imitate mouse events like ENTER, CTRL+C, CTRL+V
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(3000);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getPathToTempFileWithLength(Long lenghFile) {
		String pathToFile = "";
		try {
			File tempfile = File.createTempFile("tempForTest", ".tmp");
			tempfile.deleteOnExit();
			RandomAccessFile raf = new RandomAccessFile(tempfile, "rw");  
			try {  
				raf.setLength(lenghFile);  
			}  
			finally {  
				raf.close();  
			}  
			pathToFile=tempfile.getAbsolutePath();
		} catch (IOException e) {
		}
		return pathToFile;
	}
}

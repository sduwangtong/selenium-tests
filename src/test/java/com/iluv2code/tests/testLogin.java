package com.iluv2code.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class testLogin extends testBaseClass {

    @Test
    public void testCases() {
        System.out.println("Testing logging in");
        login(1,1,0,1,"","");
        logoff();
        //TODO: Negative tests
    }
    public static void login(int startRecord, int endRecord, int contentSheet, int xpathSheet, String userName, String passWord){
        sheet = workbook.getSheet(contentSheet);
        xpathsheet = workbook.getSheet(xpathSheet);


        for (int i = startRecord; i <= endRecord; i++) {
            if (userName == null || userName.length() == 0 || passWord == null || passWord.length() == 0) {

                userName = sheet.getCell(0, i).getContents();
                passWord = sheet.getCell(1, i).getContents();
            }
            String xpath_loginButton = xpathsheet.getCell(0,1).getContents();
            String xpath_navBar = xpathsheet.getCell(1,1).getContents();

            driver.navigate().to(baseUrl);

            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys(userName);

            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(passWord);

            driver.findElement(By.xpath(xpath_loginButton)).click();

            // the login name shown at navigation bar should be equal to our account
            assertEquals(userName,driver.findElement(By.xpath(xpath_navBar)).getText());
        }

    }

    public static void logoff() {
        xpathsheet = workbook.getSheet(1);

        String xpath_navBar = xpathsheet.getCell(1,1).getContents();
        String xpath_logOff = xpathsheet.getCell(2,1).getContents();

        driver.findElement(By.xpath(xpath_navBar)).click();
        driver.findElement(By.xpath(xpath_logOff)).click();
    }



}
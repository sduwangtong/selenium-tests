package com.iluv2code.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by Tony on 12/3/14.
 */
public class testRegister extends testBaseClass {
    @Test
    public void testCases() {
        System.out.println("Testing registration of two users");
        register(1, 2, 4, 5);

        //TODO:negative test
    }

    private void register (int startRecord, int endRecord, int contentSheet, int xpathSheet) {
        sheet = workbook.getSheet(contentSheet);
        xpathsheet = workbook.getSheet(xpathSheet);
        String xpath_join_button = xpathsheet.getCell(0, 1).getContents();
        String xpath_getStart = xpathsheet.getCell(1, 1).getContents();
        String xpath_firstName = xpathsheet.getCell(2, 1).getContents();
        String xpath_lastName= xpathsheet.getCell(3, 1).getContents();
        String xpath_email = xpathsheet.getCell(4, 1).getContents();
        String xpath_password = xpathsheet.getCell(5, 1).getContents();
        String xpath_stocksLabel = xpathsheet.getCell(6, 1).getContents();
        String xpath_optionsLabel = xpathsheet.getCell(7, 1).getContents();
        String xpath_fundsLabel = xpathsheet.getCell(8, 1).getContents();
        String xpath_otherLabel= xpathsheet.getCell(9, 1).getContents();
        String xpath_biography = xpathsheet.getCell(10, 1).getContents();
        String xpath_websiteUrl = xpathsheet.getCell(11, 1).getContents();
        String xpath_acceptedTos = xpathsheet.getCell(12, 1).getContents();
        String xpath_submitForm = xpathsheet.getCell(13, 1).getContents();


        for (int i = startRecord; i <= endRecord; i++) {
            sheet = workbook.getSheet(contentSheet);
            xpathsheet = workbook.getSheet(xpathSheet);

            driver.navigate().to(baseUrl);

            String firstName = sheet.getCell(0, i).getContents();
            String lastName = sheet.getCell(1, i).getContents();
            String email = sheet.getCell(2, i).getContents();
            String password = sheet.getCell(3, i).getContents();
            String stocksLabel = sheet.getCell(4, i).getContents();
            String optionsLabel = sheet.getCell(5, i).getContents();
            String fundsLabel = sheet.getCell(6, i).getContents();
            String otherLabel = sheet.getCell(7, i).getContents();
            String biography = sheet.getCell(8, i).getContents();
            String websiteUrl = sheet.getCell(9, i).getContents();
            String acceptedTos = sheet.getCell(10, i).getContents();

            driver.findElement(By.xpath(xpath_join_button)).click();
            driver.findElement(By.xpath(xpath_getStart)).click();

            fillForm(xpath_firstName,firstName);
            fillForm(xpath_lastName,lastName);
            fillForm(xpath_email,email);
            fillForm(xpath_password, password);

            selectLabel(xpath_stocksLabel, stocksLabel);
            selectLabel(xpath_optionsLabel,optionsLabel);
            selectLabel(xpath_fundsLabel,fundsLabel);
            selectLabel(xpath_otherLabel,otherLabel);

            fillForm(xpath_biography,biography);
            fillForm(xpath_websiteUrl, websiteUrl);

            selectLabel(xpath_acceptedTos,acceptedTos);
            driver.findElement(By.xpath(xpath_submitForm)).click();

            uploadImage(i, contentSheet, xpathSheet);

            testLogin.login(1, 1, 0, 1, email,password);
            testLogin.logoff();
        }
    }

    private void uploadImage(int i, int contentSheet, int xpathSheet) {
        sheet = workbook.getSheet(contentSheet);
        xpathsheet = workbook.getSheet(xpathSheet);
        String xpath_uploadPhoto = xpathsheet.getCell(14, 1).getContents();
        String xpath_photoInput = xpathsheet.getCell(15, 1).getContents();
        String xpath_savePhoto = xpathsheet.getCell(16, 1).getContents();
        String xpath_next = xpathsheet.getCell(17, 1).getContents();

        String photoInput = sheet.getCell(11, i).getContents();

        driver.findElement(By.xpath(xpath_uploadPhoto)).click();
        driver.findElement(By.xpath(xpath_photoInput)).sendKeys(photoInput);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath_savePhoto)));
        driver.findElement(By.xpath(xpath_savePhoto)).click();

        driver.findElement(By.xpath(xpath_next)).click();
    }

}

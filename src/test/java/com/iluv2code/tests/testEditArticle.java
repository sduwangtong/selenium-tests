package com.iluv2code.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Tony on 12/1/14.
 */
public class testEditArticle extends testBaseClass {
    @Test
    public void testCases() {
        System.out.println("Testing creating two articles");
        testLogin.login(1, 1, 0, 1, "", "");
        createArticle(1, 2, 2, 3);
        testLogin.logoff();

        //TODO:negative test
    }

    /**
     * Creating an article according to excel sheet, then checking the tittle, headline according to input information
     *
     * @param startRecord       The first record/row you want to start with
     * @param endRecord         The last record/row you want to hit
     * @param contentSheet      The excel sheet number of your content
     * @param xpathSheet        The excel sheet number of your xpath
     */
    private void createArticle(int startRecord, int endRecord, int contentSheet, int xpathSheet) {
        try {
                sheet = workbook.getSheet(contentSheet);
                xpathsheet = workbook.getSheet(xpathSheet);
                String xpath_author = xpathsheet.getCell(0, 1).getContents();
                String xpath_webSite = xpathsheet.getCell(1, 1).getContents();
                String xpath_topic = xpathsheet.getCell(2, 1).getContents();
                String xpath_headline = xpathsheet.getCell(3, 1).getContents();
                String xpath_callout = xpathsheet.getCell(4, 1).getContents();
                String xpath_body_iframe = xpathsheet.getCell(5, 1).getContents();
                String xpath_body = xpathsheet.getCell(6, 1).getContents();
                String xpath_image = xpathsheet.getCell(7, 1).getContents();
                String xpath_upload = xpathsheet.getCell(8, 1).getContents();
                String xpath_createArticle = xpathsheet.getCell(9, 1).getContents();
                String xpath_hiddenInput = xpathsheet.getCell(10, 1).getContents();
                String xpath_saveAndContinue = xpathsheet.getCell(11, 1).getContents();


                for (int i = startRecord; i <= endRecord; i++) {
                    String author = sheet.getCell(0, i).getContents();
                    String webSite = sheet.getCell(1, i).getContents();
                    String topic = sheet.getCell(2, i).getContents();
                    String headline = sheet.getCell(3, i).getContents();
                    String callout = sheet.getCell(4, i).getContents();
                    String body = sheet.getCell(5, i).getContents();
                    String image = sheet.getCell(6, i).getContents();

                    driver.findElement(By.xpath(xpath_createArticle)).click();

        //            TODO: adding writer, changing site and topic


                    driver.findElement(By.xpath(xpath_headline)).clear();
                    driver.findElement(By.xpath(xpath_headline)).sendKeys(headline);

                    driver.findElement(By.xpath(xpath_headline)).clear();
                    driver.findElement(By.xpath(xpath_headline)).sendKeys(headline);

                    driver.findElement(By.xpath(xpath_callout)).clear();
                    driver.findElement(By.xpath(xpath_callout)).sendKeys(callout);

                    driver.switchTo().frame(xpath_body_iframe);
                    driver.findElement(By.xpath(xpath_body)).clear();
                    driver.findElement(By.xpath(xpath_body)).sendKeys(body);
                    driver.switchTo().defaultContent();

                    driver.findElement(By.xpath(xpath_image)).click();

                    // wait page to load
        //            javaScriptHandleHiddenInput(xpath_hiddenInput, image);

                    driver.findElement(By.xpath(xpath_upload)).click();
                    driver.findElement(By.xpath(xpath_saveAndContinue)).click();
                    articleSubmission(i, contentSheet, xpathSheet);
                    checkArticle( author, webSite, headline);
                }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("The excel don't have that cell, check your excel file");
            e.printStackTrace();
        }
    }

    private void articleSubmission(int record, int contentSheet, int xpathSheet) {
        sheet = workbook.getSheet(contentSheet);
        xpathsheet = workbook.getSheet(xpathSheet);

        String relateArticleBy = sheet.getCell(7, record).getContents();
        String Keywords = sheet.getCell(8, record).getContents();
        String seo_title = sheet.getCell(9, record).getContents();
        String seo_description = sheet.getCell(10, record).getContents();
        String seo_keywords = sheet.getCell(11, record).getContents();

        String xpath_relateArticleBy = xpathsheet.getCell(12, 1).getContents();
        String xpath_Keywords = xpathsheet.getCell(13, 1).getContents();
        String xpath_seo_title = xpathsheet.getCell(14, 1).getContents();
        String xpath_seo_description = xpathsheet.getCell(15, 1).getContents();
        String xpath_seo_keywords = xpathsheet.getCell(16, 1).getContents();
        String xpath_saveAndContinue = xpathsheet.getCell(11, 1).getContents();
        String editorialReview = xpathsheet.getCell(17, 1).getContents();

        selectorSelectByText(xpath_relateArticleBy, relateArticleBy);

        //TODO: Keywords selection

        driver.findElement(By.xpath(xpath_seo_title)).clear();
        driver.findElement(By.xpath(xpath_seo_title)).sendKeys(seo_title);

        driver.findElement(By.xpath(xpath_seo_description)).clear();
        driver.findElement(By.xpath(xpath_seo_description)).sendKeys(seo_description);

        driver.findElement(By.xpath(xpath_seo_keywords)).clear();
        driver.findElement(By.xpath(xpath_seo_keywords)).sendKeys(seo_keywords);

        driver.findElement(By.xpath(xpath_saveAndContinue)).click();
        driver.findElement(By.xpath(editorialReview)).click();
    }

    private void checkArticle( String author, String webSite, String headline) {

        String xpath_inReviewTab = xpathsheet.getCell(18, 1).getContents();
        String xpath_review_headline = xpathsheet.getCell(19, 1).getContents();
        String xpath_review_site = xpathsheet.getCell(20, 1).getContents();
        String xpath_review_writer = xpathsheet.getCell(21, 1).getContents();

        driver.findElement(By.xpath(xpath_inReviewTab)).click();

        assertEquals(author, driver.findElement(By.xpath(xpath_review_writer )).getText());
        assertEquals(webSite, driver.findElement(By.xpath(xpath_review_site)).getText());
        assertEquals(headline, driver.findElement(By.xpath(xpath_review_headline)).getText());
    }

}


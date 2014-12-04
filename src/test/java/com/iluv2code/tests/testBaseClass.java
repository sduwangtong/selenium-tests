package com.iluv2code.tests;

import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tony on 11/28/14.
 */
public class testBaseClass {
    static WebDriver driver;
    static String baseUrl;
    static Workbook workbook;
    static Sheet sheet;
    static Sheet xpathsheet;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        baseUrl = "https://stagecontributor.thestreet.com";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            workbook = Workbook.getWorkbook(new File("file/test_data.xls"));
        } catch (Exception e) {
            System.out.println("Can't initial the jxl file, please set up your excel file");
            e.printStackTrace();
        }
        wait = new WebDriverWait(driver, 5);
    }

    @AfterTest
    public void closeSelenium() {
        //Shutdown the browser
        driver.quit();
        workbook.close();
    }

    /**
     * Uploading a picture for user by interacting with hidden angularjs input
     *
     * @param className     The class name of hidden input field
     * @param image         The image's xpath
     */
    public void javaScriptHandleHiddenInput(String className, String image) {
        ((JavascriptExecutor) driver).executeScript("var elems = document.getElementsByClassName('"+className+"');\n" +
                "for(var i = 0; i < elems.length; i++) {\n" +
                "    elems[i].style.visibility = 'visible';\n" +
                "    elems[i].style.height = '200px';\n" +
                "    elems[i].style.width = '200px';\n" +
                "}");
        driver.findElement(By.className(className)).sendKeys(image);
    }

    /**
     * Selecting a selector by the input text
     *
     * @param xpath     The xpath of selector
     * @param text      The selector you want to select
     */
    public void selectorSelectByText (String xpath, String text) {
        Select select = new Select(driver.findElement(By.xpath(xpath)));
        select.selectByVisibleText(text);
    }

    /**
     * Filling a form field
     * @param xpath     The xpath of an empty/ filled form filed
     * @param content   The content you want to filed with
     */
    public void fillForm(String xpath, String content) {
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(content);
    }

    /**
     * Selecting a checkbox- if the input is yes, we select it, otherwise we ignore it
     * @param xpath     The xpath of checkbox
     * @param content   "Yes"/ "No"
     */
    public void selectLabel(String xpath, String content) {
        if (content.equalsIgnoreCase("Yes")  && !driver.findElement(By.xpath(xpath)).isSelected()) {
            driver.findElement(By.xpath(xpath)).click();
        }
    }
}

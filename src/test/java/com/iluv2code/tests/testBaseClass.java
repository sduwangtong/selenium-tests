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
    static StringBuffer verificationErrors = new StringBuffer();
    static Workbook workbook;
    static Sheet sheet;
    static Sheet xpathsheet;
    static int totalrow;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://stagecontributor.thestreet.com";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        workbook = Workbook.getWorkbook(new File("file/test_data.xls"));
        wait = new WebDriverWait(driver, 5);
    }

    @AfterTest
    public void closeSelenium() {
        //Shutdown the browser
        driver.quit();
        workbook.close();
    }

    public void javaScriptHandleHiddenInput(String className) {
        ((JavascriptExecutor) driver).executeScript("var elems = document.getElementsByClassName('"+className+"');\n" +
                "for(var i = 0; i < elems.length; i++) {\n" +
                "    elems[i].style.visibility = 'visible';\n" +
                "    elems[i].style.height = '200px';\n" +
                "    elems[i].style.width = '200px';\n" +
                "}");
    }

    public void selectorSelectByText (String xpath, String text) {
        Select select = new Select(driver.findElement(By.xpath(xpath)));
        select.selectByVisibleText(text);
    }

    public void fillForm(String xpath, String content) {
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(content);
    }

    public void selectLabel(String xpath, String content) {
        if (content.equalsIgnoreCase("Yes")  && !driver.findElement(By.xpath(xpath)).isSelected()) {
            driver.findElement(By.xpath(xpath)).click();
        }
    }
}

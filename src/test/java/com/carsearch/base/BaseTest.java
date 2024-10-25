package com.carsearch.base;

import com.carsearch.Utils.ReadTextFile;
import com.carsearch.factory.PlaywrightFactory;
import com.carsearch.models.CarDetails;
import com.carsearch.pages.HomePage;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    protected PlaywrightFactory playwrightFactory;
    Page page;
    Properties prop;

    protected HomePage homePage;
    protected List<String> vehicleRegFromFile;
    protected List<CarDetails> expectedCarDetails;

    @BeforeTest
    public void SetUp(){
        playwrightFactory = new PlaywrightFactory();
        prop = playwrightFactory.initiateProperties();
        page = playwrightFactory.SetUpBrowser(prop);
        homePage = new HomePage(page);

        ReadTextFile readTextFile = new ReadTextFile();
        try {
            vehicleRegFromFile = readTextFile.getReg();
            expectedCarDetails = readTextFile.getCarDetails();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }
}

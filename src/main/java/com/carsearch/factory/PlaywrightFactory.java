package com.carsearch.factory;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    Properties prop;
    public Page SetUpBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser "+browserName+" initialised");
        playwright = Playwright.create();

        switch (browserName.toLowerCase()){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                System.out.println();
                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(prop.getProperty("url"));
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);

        return page;
    }

    public void navigateToUrl(){
        page.navigate(prop.getProperty("url"));
    }

    public Properties initiateProperties(){
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/main/resources/config/config.properties");
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}

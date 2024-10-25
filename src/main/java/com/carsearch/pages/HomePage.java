package com.carsearch.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;

    private String vehicleReg = "#vehicleReg";
    private String mileage = "#Mileage";
    private String getValuation = "#btn-go";
    private String acceptCookies = "#onetrust-accept-btn-handler";

    public HomePage(Page page){
        this.page = page;
    }

    private boolean cookiesVisible(){return page.isVisible(acceptCookies);}

    public void acceptCookies(){
        if(cookiesVisible()){
            page.click(acceptCookies);
        }
    }

    public void searchCarReg(String value, int carMileage){

      page.waitForSelector(vehicleReg);
      page.fill(vehicleReg, value);
      page.fill(this.mileage, String.valueOf(carMileage));
    }

    public CarDetailsPage getValuation(){
        page.waitForSelector(getValuation);
        page.click(getValuation);
        return new CarDetailsPage(page);
    }
}

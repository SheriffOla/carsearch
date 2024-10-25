package com.carsearch.pages;

import com.carsearch.models.CarDetails;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;

public class CarDetailsPage {

    private Page page;

    private String regNumber = "div[class$='ng-star-inserted'] div[class='details-vrm ng-star-inserted']";
    private String make = "div[class^='position-relative'] div[class='d-table'] div:nth-child(1) div:nth-child(2)";
    private String model = "div[class^='position-relative'] div[class='d-table'] div:nth-child(2) div:nth-child(2)";
    private String year = "div[class^='position-relative'] div[class='d-table'] div:nth-child(3) div:nth-child(2)";

    public CarDetailsPage(Page page){
        this.page = page;
    }


    public CarDetails getDisplayedCarDetails(){
        CarDetails  displayedCarDetails = new CarDetails();
        try{
            page.waitForSelector(regNumber);
            String displayedRegNumber = page.textContent(regNumber).trim();
            String displayedModel = page.textContent(model).trim();
            String displayedMake = page.textContent(make).trim();
            String displayedYear = page.textContent(year).trim();

            displayedCarDetails.setVariant_Reg(displayedRegNumber);
            displayedCarDetails.setModel(displayedModel);
            displayedCarDetails.setMake(displayedMake);
            displayedCarDetails.setYear(displayedYear);
        }catch (TimeoutError n){

            /*
            Return null when no details found
             */
           return null;
        }

     return displayedCarDetails;
    }
}

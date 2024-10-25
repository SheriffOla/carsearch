package com.carsearch.tests;

import com.carsearch.Utils.RandomMiles;
import com.carsearch.base.BaseTest;
import com.carsearch.models.CarDetails;
import com.carsearch.pages.CarDetailsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PageTests extends BaseTest {
    private int index;

    @Test(dataProvider = "data-provider")
    public void SearchTest(String  vrn){

        homePage.acceptCookies();

        String reg = vrn.trim();
        reg = reg.replaceAll("\\s+","");

            homePage.searchCarReg(reg, RandomMiles.GenerateMiles());
            CarDetailsPage carDetailsPage = homePage.getValuation();
            CarDetails actualCarDetails = carDetailsPage.getDisplayedCarDetails();

                if(actualCarDetails != null){
                    String regToCompare = actualCarDetails.getVariant_Reg();
                    CarDetails expectedCarDetail = expectedCarDetails.stream()
                            .filter(details -> regToCompare.equals(details.getVariant_Reg())).findAny().orElse(null);

                    Assert.assertEquals(expectedCarDetail.getVariant_Reg(), actualCarDetails.getVariant_Reg());
                    Assert.assertEquals(expectedCarDetail.getMake(), actualCarDetails.getMake());
                    Assert.assertEquals(expectedCarDetail.getModel(), actualCarDetails.getModel());
                    Assert.assertEquals(expectedCarDetail.getYear(), actualCarDetails.getYear());

                    returnToInputPage();
                }else{
                    returnToInputPage();
                    Assert.fail("Car with reg number "+reg+ " was not returned");
                }
    }

    @DataProvider(name = "data-provider")
    public Object[][] vehicleRegs(){
        Object [][] array = new Object[vehicleRegFromFile.size()][];
        for (int i=0;i<vehicleRegFromFile.size();i++){
            array[i] = new Object[1];
            array[i][0] = vehicleRegFromFile.get(i);
        }
        return array;
   }

    private void returnToInputPage(){
        index++;
        playwrightFactory.navigateToUrl();
    }
}

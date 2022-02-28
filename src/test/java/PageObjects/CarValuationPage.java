package PageObjects;

import Helpers.TextFileHelper;
import Helpers.Vehicle;
import Utils.TestContext;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Properties;

public class CarValuationPage extends BasePage {
    TextFileHelper textFileHelper = new TextFileHelper();
    List<String> inputVehicles;
    List<Vehicle> outputVehicles;
    private static final String btn_acceptCookies_xpath = "//button[contains(text(),'Accept All')]";
    private static final String textbox_vrm_id = "vrm";
    private static final String btn_startValuation_xpath = "//button[@type='submit']";
    private static final String txt_regNumber_xpath = "//div[@class='shared-styles__SummaryContainer-sc-1uxdbbu-0 eGPcPw']/p[1]";
    private static final String txt_vehicleDetails_xpath = "//div[@class='shared-styles__SummaryContainer-sc-1uxdbbu-0 eGPcPw']/p[2]";
    private static final String txt_errorMessage_xpath = "//span[@class='sc-1ld3ie8-1 OgVku']";

    public Properties getProperties() {
        return TestContext.getInstance().getEnvironmentProperties();
    }

    public void navigateToCazooURL(){
        getURL(getProperties().getProperty("appURL"));
    }

    public void verifyActualAndExpectedVehicleDetails() {
              int counter =0;
            for (String vehicleRegistrationNumber : inputVehicles) {

                navigateToCazooURL();
                if(counter==0){
                    clickOnAcceptCookies();
                }
                enterRegistrationNumber(vehicleRegistrationNumber);
                clickOnStartValuation();

                try {
                    String regNumber = getRegNumber();
                    String vehicleDetails = getVehicleDetails();

                    Vehicle vehicle = new Vehicle();
                    String[] regNumberParts = regNumber.split(":");

                    if (regNumberParts.length > 1) {
                        vehicle.RegistrationNumber = regNumberParts[1].trim();
                    }

                    String[] makeAndModelParts = vehicleDetails.split(":");

                    if (makeAndModelParts.length > 1) {
                        String[] makeParts = makeAndModelParts[1].trim().split(" ");

                        if (makeParts.length > 0) {
                            vehicle.Make = makeParts[0].trim();
                        }

                        vehicle.Model = makeAndModelParts[1].replace(vehicle.Make, "").trim();
                    }

                    Vehicle matchingVehicle = outputVehicles.stream().filter(v -> v.RegistrationNumber.equals(vehicle.RegistrationNumber)).findAny().orElse(null);

                    Assert.assertNotNull(matchingVehicle);
                    Assert.assertEquals(vehicle.RegistrationNumber, matchingVehicle.RegistrationNumber);
                    Assert.assertEquals(vehicle.Make, matchingVehicle.Make);
                    Assert.assertEquals(vehicle.Model, matchingVehicle.Model);

                }
                catch (Exception exception){
                    String errorMessage = getErrorMessageWhenVehicleNotFound();
                    System.out.println("An error occurred while finding the vehicle :" + errorMessage);
                }

                refreshPage();
                counter++;
            }
    }

    public void readOutputFileAndExtractVehicles(){
        String outputFilePath = System.getProperty("user.dir") + "\\src\\test\\TestDataFiles\\car_output_v1.txt";

        outputVehicles = textFileHelper.readOutputFile(outputFilePath);
    }

    public void readInputFileAndExtractVehicleRegNumber(){
        String inputFilePath = System.getProperty("user.dir") + "\\src\\test\\TestDataFiles\\car_input_v1.txt";

        inputVehicles = textFileHelper.readInputFile(inputFilePath);
    }

    public void clickOnAcceptCookies() {
            clickElement(By.xpath(btn_acceptCookies_xpath));

    }

    public void enterRegistrationNumber(String regNumber){
        enterTextByElement(By.id(textbox_vrm_id), regNumber);
    }

    public void clickOnStartValuation(){
        clickElement(By.xpath(btn_startValuation_xpath));
    }

    public String getRegNumber(){
        return getElementText(By.xpath(txt_regNumber_xpath));
    }

    public String getVehicleDetails(){
        return getElementText(By.xpath(txt_vehicleDetails_xpath));
    }

    public String getErrorMessageWhenVehicleNotFound(){
        return getElementText(By.xpath(txt_errorMessage_xpath));
    }

}

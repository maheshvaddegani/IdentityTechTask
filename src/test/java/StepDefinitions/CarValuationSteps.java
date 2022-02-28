package StepDefinitions;

import PageObjects.CarValuationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarValuationSteps {
    CarValuationPage carValuationPage = new CarValuationPage();

            @Given("I read the input file and extract vehicle registration numbers")
            public void I_read_the_input_file_and_extract_vehicle_registration_numbers() {
                    carValuationPage.readInputFileAndExtractVehicleRegNumber();
            }

            @When("I read the output file and get the vehicle details")
            public void I_read_the_output_file_and_get_the_vehicle_details() {
                    carValuationPage.readOutputFileAndExtractVehicles();
            }

            @Then("I navigate to cazoo car valuation page and get vehicle data and validate vehicle expected and actual details")
            public void I_validate_vehicle_expected_and_actual_details(){
                    carValuationPage.verifyActualAndExpectedVehicleDetails();
            }
}

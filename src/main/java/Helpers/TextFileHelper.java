package Helpers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileHelper {
    public List<String> readInputFile(String filePath){
        List<String> vehicleRegNumbers = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            List<Pattern> patterns = new ArrayList<Pattern>();
            Pattern pattern1 = Pattern.compile("[A-Z]{2}[0-9]{2} [A-Z]{3}");
            Pattern pattern2 = Pattern.compile("[A-Z]{2}[0-9]{2}[A-Z]{3}");
            patterns.add(pattern1);
            patterns.add(pattern2);

            String line;
            while ((line = br.readLine()) != null) {

                for (Pattern pattern : patterns) {
                    Matcher matcher = pattern.matcher((line));

                    if (matcher.find()) {
                        String vehicleRegNumber = matcher.group();
                        vehicleRegNumbers.add(vehicleRegNumber);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicleRegNumbers;
    }

    public List<Vehicle> readOutputFile(String filePath)
    {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0)
                {
                    lineNumber++;
                    continue;
                }

                if (line != null && !line.trim().isEmpty()) {
                    String[] vehicleDetails = line.split(",");

                    Vehicle vehicle = new Vehicle();

                    if (vehicleDetails.length > 1) {
                        vehicle.RegistrationNumber = vehicleDetails[0].trim();
                        vehicle.Make = vehicleDetails[1].trim();
                        vehicle.Model = line.replace(vehicleDetails[0] + "," + vehicleDetails[1] + ",", "").trim();

                        vehicles.add(vehicle);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}

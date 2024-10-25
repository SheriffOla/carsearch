package com.carsearch.Utils;

import com.carsearch.models.CarDetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadTextFile {

    private String readInputFile() throws IOException {

        File file = new File("./src/main/resources/car_input.txt");

        return new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
    }

    public List<String> getReg() throws IOException, URISyntaxException {
        List<String> foundReg = new ArrayList<>();
        String text = readInputFile();
        Pattern pattern = Pattern.compile("(?:B ?[0-9]{2,3}|[A-Z]{2} ?[0-9]{2}) ?[A-HJ-NP-Z][A-Z][A-HJ-NP-Z]");

        Matcher isReg = pattern.matcher(text);
        while (isReg.find()){
            foundReg.add(isReg.group());
        }

       return foundReg;
    }

    public List<CarDetails> getCarDetails(){
        int header = 0;
        List<CarDetails> carDetails = new ArrayList<>();

        Path filePath = Paths.get("./src/main/resources/car_output.txt");
        try(BufferedReader reader = Files.newBufferedReader(filePath)){
            String details = reader.readLine();

            while (details != null ){
                if(header != 0){
                    String [] detail = details.split(",");
                    CarDetails aCarDetail = getACarDetails(detail);
                    carDetails.add(aCarDetail);
                }
                details = reader.readLine();
                header++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carDetails;
    }

    private CarDetails getACarDetails(String[] details){
        CarDetails carDetails = new CarDetails();
        carDetails.setVariant_Reg(details[0].trim());
        carDetails.setMake(details[1].trim());
        carDetails.setModel(details[2].trim());
        carDetails.setYear(details[3].trim());

        return carDetails;
    }
}

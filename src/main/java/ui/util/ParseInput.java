package ui.util;

import ui.model.DataSet;
import ui.model.Inputs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ParseInput {

    public static DataSet parseInput(String path) throws IOException {
        //file input
        List<String> lines = Files.readAllLines( Paths.get(path));

        //Ouput
        List<Inputs> featureInputs = new LinkedList<>();

        //weather,temperature,humidity,wind,play
        //Attributes for model
        List<String> attributes = Arrays.asList(lines.get(0).split(","));

        //remove first line header
        lines.remove(0);

        for(int i = 0; i<lines.size(); i++){
            Inputs featureInput = new Inputs();
            List<String> values = Arrays.asList(lines.get(i).split(","));

            for(int j = 0; j<values.size(); j++){

                //Last index is for class label
                if(j == values.size()-1){
                    featureInput.setClassLabel(values.get(j));
                    continue;
                }

                featureInput.addValue(attributes.get(j), values.get(j));
            }

            featureInputs.add(featureInput);
        }
        return new DataSet(featureInputs);
    }
}

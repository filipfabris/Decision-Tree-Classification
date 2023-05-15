package ui.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Inputs {

    private final Map<String, String> values;

    public String classLabel;

    public Inputs() {
        this.values = new HashMap<>();
        this.classLabel = null;
    }

    //Add value to map
    public void addValue(String key, String value){
        values.put(key, value);
    }

    //Set class label
    public void setClassLabel(String classLabel){
        this.classLabel = classLabel;
    }

    public Set<String> getFeatures() {
        return new HashSet<>( values.keySet() );
    }

    public String getFeatureValue(String feature) {
        return values.get(feature);
    }

    public String getClassLabel() {
        return classLabel;
    }
}

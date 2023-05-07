package ui.model;

import java.util.*;

public class DataInput {

    public List<Inputs> inputs;

    public Set<String> features;

    public double classEntropy;
    private Map<String, Integer> labelsOccurances;

    public String mostCommonClassOccurrence;

    public DataInput(List<Inputs> featureInputs) {
        this.inputs = featureInputs;
        this.labelsOccurances = new HashMap<>();
        this.setLabels();
        this.calculateLabelOccurrences();
        this.calculateClassEntropy();
        this.calculateMostCommonClassOccurrence();
    }

    public void setLabels(){
        this.features = inputs.get( 0 ).getFeatures();
    }

    public void calculateLabelOccurrences(){
        for (Inputs input : inputs) {
            String label = input.classLabel; //yes ili no ili nesto drugo
            if(labelsOccurances.containsKey( label )){
                labelsOccurances.put( label, labelsOccurances.get( label ) + 1 );
            }else{
                labelsOccurances.put( label, 1 );
            }
        }
    }

    public void calculateClassEntropy(){
        for(String label: labelsOccurances.keySet()){
            double labelOccurrence = labelsOccurances.get( label );
            double total = inputs.size();
            double labelEntropy = (labelOccurrence/total) * Math.log( labelOccurrence/total ) / Math.log( 2 );
            this.classEntropy += -labelEntropy;
        }
    }

    public void calculateMostCommonClassOccurrence(){
        int max = 0;

        List<String> sortedLabels = new ArrayList<>(labelsOccurances.keySet());
        Collections.sort(sortedLabels);

        for(String label: sortedLabels){
            if(labelsOccurances.get( label ) > max){
                max = labelsOccurances.get( label );
                this.mostCommonClassOccurrence = label;
            }
        }
    }

    public double calculateGain(String feature) {
        double gain = this.classEntropy;
        //Pronađi sve vrijednosti za bestFeature, distinct
        List<String> featureValues = new ArrayList<>();
        for (Inputs input : inputs) {
            String featureValue = input.getFeatureValue( feature ); ////////////////////////////////////////
            if(featureValues.contains( featureValue ) == false){
                featureValues.add( featureValue );
            }
        }
            for (String featureValue : featureValues) {
                Map<String, Integer> featureValueLabelsOccurances = new HashMap<>();
                double featureValueOccurrence = 0;

                for (Inputs input : inputs) {
                    if(input.getFeatureValue( feature ).equals( featureValue )){
                        String label = input.classLabel; //yes ili no ili nesto drugo
                        featureValueOccurrence++;
                        if(featureValueLabelsOccurances.containsKey( label )){
                            featureValueLabelsOccurances.put( label, featureValueLabelsOccurances.get( label ) + 1 );
                        }else{
                            featureValueLabelsOccurances.put( label, 1 );
                        }
                    }
                }

                double featureValueEntropy = calculateFeatureValueEntropy(featureValueLabelsOccurances);
                gain -= (featureValueOccurrence/inputs.size()) * featureValueEntropy;
            }
        return gain;
    }

    private double calculateFeatureValueEntropy(Map<String, Integer> featureValueLabelsOccurances) {
        double featureValueEntropy = 0;
        double total = 0;
        for(String label: featureValueLabelsOccurances.keySet()){
            total += featureValueLabelsOccurances.get( label );
        }

        for(String label: featureValueLabelsOccurances.keySet()){
            double labelOccurrence = featureValueLabelsOccurances.get( label );
            double labelEntropy = (labelOccurrence/total) * Math.log( labelOccurrence/total ) / Math.log( 2 );
            featureValueEntropy += -labelEntropy;
        }
        return featureValueEntropy;
    }


    public Map<String, DataInput> splitByFeature(String bestFeature) {
        Map<String, DataInput> splitedDataInput = new HashMap<>();

        //Pronađi sve vrijednosti za bestFeature, distinct
        List<String> featureValues = new ArrayList<>();
        for (Inputs input : inputs) {
            String featureValue = input.getFeatureValue( bestFeature );
            if(featureValues.contains( featureValue ) == false){
                featureValues.add( featureValue );
            }
        }

        //Iteriraj po svakoj vrijednosti i napravi novi DataInput za svaku vrijednost featurea
        for (String featureValue : featureValues) {
            List<Inputs> featureValueInputs = new ArrayList<>();
            for (Inputs input : inputs) {
                //Map vrijeme - sunčano
                //map.get(vrijeme) -> sunčano
                //Ako je sunčano jednaka featureValue, dodaj u listu
                //I tako za sve vrijednosti featurea
                if(input.getFeatureValue( bestFeature ).equals( featureValue )){
                    featureValueInputs.add( input );
                }
            }
            splitedDataInput.put( featureValue, new DataInput( featureValueInputs ) );
        }
        return splitedDataInput;
    }

    public List<String> getLabels(){
        return labelsOccurances.keySet().stream().toList();
    }
}

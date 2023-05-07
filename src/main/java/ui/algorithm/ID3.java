package ui.algorithm;

import ui.model.DataInput;
import ui.model.Inputs;
import ui.model.Tree.Leaf;
import ui.model.Tree.Node;
import ui.model.Tree.NodeModel;
import ui.util.Output;

import java.util.*;

public class ID3 implements Algorithm{

    private NodeModel root;

    private int maxDepth;

    public ID3(int depth) {
        this.maxDepth = depth;
    }

    public ID3() {
        this.maxDepth = Integer.MAX_VALUE;
    }

    @Override
    public void fit(DataInput training) {
        this.root = generateTree(training, training, training.features, 0);

        Output.printTree(this.root);

    }

    @Override
    public void predict(DataInput testing) {

        List<String> result = new LinkedList<>();

        for(Inputs input: testing.inputs){
            String element = this.predictSingle(input, this.root);
            result.add(element);
        }

        Output.printPrediction(result);
        Output.printAccuracy(this.calculateAccuracy(testing, result));
        Output.printConfusionMatrix(this.calculateConfusionMatrix(testing, result), testing.getLabels());

    }

    private NodeModel generateTree(DataInput set, DataInput parentSet, Set<String> features, int depth){
        if(set.classEntropy == 0){
            return new Leaf( depth, set.mostCommonClassOccurrence );
        }
        if(features.isEmpty()){
            return new Leaf( depth, set.mostCommonClassOccurrence );
        }

        if(depth >= this.maxDepth){
            return new Leaf( depth, set.mostCommonClassOccurrence );
        }

        String bestFeature = this.findBestFeature(set, features);
        Map<String, DataInput> featureSets = set.splitByFeature(bestFeature);
        Map<String, NodeModel> children = new HashMap<>();

        for(Map.Entry<String, DataInput> entry : featureSets.entrySet()){
            DataInput newSet = entry.getValue();
            Set<String> newFeatures = new HashSet<>(features);
            newFeatures.remove(bestFeature);
            children.put(entry.getKey(), generateTree(newSet, set, newFeatures, depth+1));
        }
        return new Node(depth, bestFeature, set.mostCommonClassOccurrence, children);
    }

    private String findBestFeature(DataInput set, Set<String> features) {
        double maxGain = 0;
        String bestFeature = "";
        StringBuilder sb = new StringBuilder();
        for (String feature : features) {
            double gain = set.calculateGain(feature);
            //IG(weather)=0.2467IG(humidity)=0.1518
            sb.append( "IG(" + feature + ")=" + String.format( "%.4f", gain ) + " ");
            if(gain > maxGain){
                maxGain = gain;
                bestFeature = feature;
            }
        }
        System.out.println(sb.toString());
        return bestFeature;
    }

    private String predictSingle(Inputs input, NodeModel currentNode){
        //Je li list ili čvor
        if(currentNode instanceof Leaf){
            return ((Leaf) currentNode).getClassLabel();
        }
        //Čvor mora biti
        Node node = (Node) currentNode;
        String nodeFearure = node.getFeature(); //weather
        String inputFeatureValue = input.getFeatureValue( nodeFearure ); //sunny

        NodeModel nextNode = node.getChildren().get(inputFeatureValue); //humidity

        //Ako je null onda nema takvog featurea
        if(nextNode == null){
            return node.getMostCommonClassOccurrence();
        }else {
            return predictSingle(input, nextNode);
        }
    }

    private double calculateAccuracy(DataInput testingData, List<String> result){
        int correct = 0;
        for(int i = 0; i < testingData.inputs.size(); i++){
            String actual = testingData.inputs.get(i).getClassLabel(); //Result from testing data
            String predicted = result.get(i); //Result from prediction based on testing data

            if(actual.equals(predicted)){
                correct++;
            }
        }
        return (double) correct / testingData.inputs.size();
    }

    private int[][] calculateConfusionMatrix(DataInput testing, List<String> result) {

        List<String> labels = new ArrayList<>(testing.getLabels());
        Collections.sort(labels);

        int[][] confusionMatrix = new int[labels.size()][labels.size()];

        for (int i = 0; i < testing.inputs.size(); i++) {
            String actual = testing.inputs.get(i).getClassLabel(); //Result from testing data
            String predicted = result.get(i); //Result from prediction based on testing data
            int actualIndex = labels.indexOf(actual); //x
            int predictedIndex = labels.indexOf(predicted); //y
            confusionMatrix[actualIndex][predictedIndex]++;
        }

        return confusionMatrix;

    }


}

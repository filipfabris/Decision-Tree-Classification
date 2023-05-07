package ui.util;

import ui.model.Tree.Leaf;
import ui.model.Tree.Node;
import ui.model.Tree.NodeModel;

import java.util.List;
import java.util.Map;

public class Output {

    public static void printTree(NodeModel root){
        StringBuilder sb = new StringBuilder();
        System.out.println("[BRANCHES]:");
        System.out.print(utilPrintTree(root, sb, ""));
    }

    private static String utilPrintTree(NodeModel currentNode, StringBuilder sb, String prefix){
        if(currentNode instanceof Leaf){
            sb.append( prefix + currentNode.toString() + "\n");
            return sb.toString();
        }

        Node node = (Node) currentNode;
        Map<String, NodeModel> children = node.getChildren();

        String oldPrefix = prefix;

        for(Map.Entry<String, NodeModel> entry : children.entrySet()){
            prefix = oldPrefix + ( currentNode.toString() + "=" + entry.getKey() + " ");
            utilPrintTree(entry.getValue(), sb, prefix);
        }

        return sb.toString();
    }

    public static void printPrediction(List<String> result){
        System.out.print("[PREDICTIONS]: ");
        StringBuilder sb = new StringBuilder();
        for(String element : result){
            sb.append(element + " ");
        }
        sb.append( "\n");
        System.out.print(sb.toString());
    }

    public static void printAccuracy(double accuracy){
        System.out.print("[ACCURACY]: ");
        System.out.println(String.format("%.5f", accuracy));
    }

    public static void printConfusionMatrix(int[][] confusionMatrix, List<String> classes){
        StringBuilder sb = new StringBuilder();
        sb.append( "[CONFUSION_MATRIX]:\n");
        for(int i = 0; i < confusionMatrix.length; i++){
            for(int j = 0; j < confusionMatrix[i].length; j++){
                sb.append(confusionMatrix[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}


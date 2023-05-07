package ui.model.Tree;

import java.util.Map;
import java.util.Objects;

public class Node extends NodeModel{

    private String feature;

    private String mostCommonClassOccurrence;

    private Map<String, NodeModel> children;

    public Node(int depth, String feature, String mostCommonClassOccurrence, Map<String, NodeModel> children) {
        super(depth);
        this.feature = feature;
        this.mostCommonClassOccurrence = mostCommonClassOccurrence;
        this.children = children;
    }

    public String getFeature() {
        return feature;
    }

    public Map<String, NodeModel> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return Objects.equals( feature, node.feature ) && Objects.equals( mostCommonClassOccurrence, node.mostCommonClassOccurrence ) && Objects.equals( children, node.children );
    }

    @Override
    public int hashCode() {
        return Objects.hash( feature, mostCommonClassOccurrence, children );
    }

    @Override
    public String toString() {
        return (this.getDepth() + 1) + ":" + feature;
    }

    public String getMostCommonClassOccurrence() {
        return mostCommonClassOccurrence;
    }
}

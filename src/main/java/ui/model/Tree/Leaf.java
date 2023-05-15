package ui.model.Tree;

import java.util.Objects;

public class Leaf extends NodeModel{

    private final String classLabel;

    public Leaf(int depth, String classLabel) {
        super(depth);
        this.classLabel = classLabel;
    }

    public String getClassLabel() {
        return classLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leaf leaf)) return false;
        return Objects.equals( classLabel, leaf.classLabel );
    }

    @Override
    public int hashCode() {
        return Objects.hash( classLabel );
    }

    @Override
    public String toString() {
        return classLabel;
    }
}

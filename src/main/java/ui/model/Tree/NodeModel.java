package ui.model.Tree;

import java.util.Objects;

public class NodeModel {

    private final int depth;

    public NodeModel(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "NodeModel{" + "depth=" + depth + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodeModel nodeModel)) return false;
        return depth == nodeModel.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash( depth );
    }
}

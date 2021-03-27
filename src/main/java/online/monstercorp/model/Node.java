package online.monstercorp.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Node<T> {

    private T data;

    private final List<Node<T>> children = new ArrayList<>();

    private transient Node<T> parent = null;

    @NonNull
    private final SIDE side;

    public Node(@NonNull SIDE side) {
        this.side = side;
    }

    public Node(T data, @NonNull SIDE side) {
        this.data = data;
        this.side = side;
    }

    public Node(T data) {
        this.data = data;
        this.side = SIDE.NONE;
    }

    public Node<T> addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }


    public Node<T> getRoot() {
        if(parent == null){
            return this;
        }
        return parent.getRoot();
    }

    public static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData() + node.side.name());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }

    public void setParents(){
        for (Node<T> child : this.getChildren()) {
            child.setParent(this);
            child.setParents();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", side=" + side +
                '}';
    }
}

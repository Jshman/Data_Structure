package Trees;

class Node {
    protected int data;
    protected Node left;
    protected Node right;
    protected Node parent;

    Node(int data) {
        this.data = data;
    }

    Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node left) {this.left = left;}

    public void setRight(Node right) {this.right = right;}

    public void setData(int data) {this.data = data;}

    public void setParent(Node parent) {this.parent = parent;}

    public int getData() {return data;}

    public Node getLeft() {return left;}

    public Node getRight() {return right;}

    public Node getParent() {return parent;}
}
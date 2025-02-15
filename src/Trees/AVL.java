package Trees;


class AVL extends BST {
    private AVLNode root;

    public void setRoot(AVLNode root) { this.root = root; }
    public AVLNode getRoot() { return this.root; }

    // LL 회전
    private void rightRotate(AVLNode y) {
        AVLNode z = (AVLNode) y.getParent();
        AVLNode x = (AVLNode) y.getRight();

        
    }

    // RR 회전
    private void leftRotate(AVLNode y) {
        AVLNode z = (AVLNode) y.getParent();
        AVLNode x = (AVLNode) y.getRight();

        z.setRight(y);
        y.setParent(z);
        
        y.setLeft(x);
        x.setParent(y);
    }

    public void insert(int data) {insert(new AVLNode(data));}
    public void insert(AVLNode node) {
        super.insert(node);

    }
}
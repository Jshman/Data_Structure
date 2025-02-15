package Trees;


class AVL extends BST {
    private AVLNode root;

    public void setRoot(AVLNode root) { this.root = root; }
    public AVLNode getRoot() { return this.root; }

    // LL 회전
    private void rightRotate(AVLNode z) {
        AVLNode y = (AVLNode) z.getLeft();
        
        if (z.getParent() != null) {    if (z.getParent().getLeft() == z) {z.getParent().setLeft(y);}
                                        else {z.getParent().setRight(y);}}
        else {setRoot(y);}
        
        y.setParent(z.getParent());
        z.setParent(y);
        
        if (y.getRight() != null) {y.getRight().setParent(z);}
        z.setLeft(y.getRight());
        y.setRight(z);
    }

    // RR 회전
    private void leftRotate(AVLNode z) {
        AVLNode y = (AVLNode) z.getRight();

        if (z.getParent() != null) {    if (z.getParent().getLeft() == z) {z.getParent().setLeft(y);}
                                        else {z.getParent().setRight(y);}}
        else {setRoot(y);} // z가 root일 때 회전을 하는 것이므로, y가 root가 된다.

        y.setParent((AVLNode) z.getParent());
        z.setParent(y);
        if (y.getLeft() != null) {y.getLeft().setParent(z);}
        z.setRight(y.getLeft());
        y.setLeft(z);
    }

    public void insert(int data) {insert(new AVLNode(data));}
    public void insert(AVLNode node) {
        super.insert(node);

    }
}
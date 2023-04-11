package Tree;

public class MainJava {
    public static void main(String args[]) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] arr = {5, 4, 1, 2, 7, 9, 10, 3, 0, 6, 8};

        for (int n : arr) {
            bst.insert(n);
        }
        bst.preOrder();
        bst.inOrder();
        bst.postOrder();
//        bst.inOrder();
//        bst.delete(4);
        bst.delete(1);
        bst.inOrder();
    }
}

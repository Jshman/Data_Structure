package Trees;

public class MainJava {
    public static void main(String args[]) {
        AVL avl = new AVL();
        int[] arr = {5, 4, 1, 2, 7, 9, 10, 3, 0, 6, 8};
        
        for (int n : arr) {
              avl.insert(n);
        }

        avl.printTree();
    }
}

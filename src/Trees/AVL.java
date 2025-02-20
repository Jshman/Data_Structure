package Trees;

import java.util.ArrayList;
import java.util.List;

class AVL extends BST {
    private AVLNode root;

    public void setRoot(AVLNode node) {
        this.root = node; 
        /**부모 노드를 null로 분명히 설정 
         * 회전을 하다보면 root node가 변경될 수 있음.
         * 이때 root의 부모노드를 제대로 설정하지 않으면 무한 loop 에 빠질 가능성이 있음.**/
        node.setParent(null);
    }
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

        z.updateHeight();
        y.updateHeight();
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

        z.updateHeight();
        y.updateHeight();
    }

    // 삽입
    @Override
    public AVLNode insert(int data) {return insert(new AVLNode(data));}
    public AVLNode insert(AVLNode node) {
        super.insert(node);
        if (this.root == null) {
            setRoot(node);
            super.setRoot(node); // 상속한 부모 클래스(BST)에서도 setRoot를 해줘야 제대로 된 insert가 가능하다. 그 이유는 삽입 자체를 부모 클래스의 insert를 사용하고 있기 때문.
        }
        // 새로 입력한 node의 위치에서 BF를 계산한다.
        node.updateHeight();
        AVLNode cur = (AVLNode) node.getParent();

        while (cur != null) {
            cur.updateHeight();
            int curBF = cur.getBalanceFactor();

             if (Math.abs(curBF) <= 1) {
                cur = (AVLNode) cur.getParent();
                continue;
             }
            
             if (0 < curBF) {
                // LL 문제
                if (cur.getLeft() != null && 0 < ((AVLNode) cur.getLeft()).getBalanceFactor()) {
                    System.out.println(cur.getData() + " 에서 불균형이 발생해 " + "LL 회전했다.");
                    rightRotate(cur);
                } else {
                // LR 문제
                    System.out.println(cur.getData() + " 에서 불균형이 발생해 " + "LR 회전했다.");
                    leftRotate((AVLNode) cur.getLeft());
                    rightRotate(cur);
                }
             }
             else {
                // RL 문제
                if (cur.getRight() != null && 0 < ((AVLNode) cur.getRight()).getBalanceFactor()) {
                    System.out.println(cur.getData() + " 에서 불균형이 발생해 " + "RL 회전했다.");
                    rightRotate((AVLNode) cur.getRight());
                    leftRotate(cur);
                }
                // RR 문제
                else {
                    System.out.println(cur.getData() + " 에서 불균형이 발생해 " + "RR 회전했다.");
                    leftRotate(cur);
                }
             }
            //  cur = node;
            cur = (AVLNode) cur.getParent();

            printTree();
        }
        // 부모 클래스에 새로 insert 된 정보와, 회전한 정보를 전달 할 필요가 있다.
        // 정보를 전달하는 방법은 AVL 트리의 root를 계속 BST의 root로 설정함으로써 BST의 insert가 동작할 때 서로같은 트리를 참조하도록 한다.
        super.setRoot(getRoot());
        return node;
    }


    public void inOrder() {
        /* 중위 순회 : 왼쪽자식 - 노드 - 오른쪽자식 순으로 순회한다. */
        System.out.println("***** 중위 순회 *****");
        inOrder(this.root);
        System.out.println("\n= = = = = = = = = = = = = = = = = =");
    }

    private void inOrder(AVLNode node) {
        if (node != null) {
            inOrder((AVLNode) node.getLeft());
            System.out.printf("%d ", node.getData());
            inOrder((AVLNode) node.getRight());
        }
    }






    // 트리를 가로로 출력하는 메서드 by ChatGPT
    public void printTree() {
        List<List<String>> lines = new ArrayList<>();
        int[] widths = new int[1];

        // 트리의 각 노드를 리스트에 추가
        collectTree(root, 0, 0, lines, widths);

        // 트리 출력
        for (List<String> line : lines) {
            for (String part : line) {
                System.out.print(part);
            }
            System.out.println();
        }
    }

    public static String repeat(int times) {
        if (times <= 0) {
            return "";
        }
        char[] chars = new char[times];
        for (int i = 0; i < times; i++) {
            chars[i] = ' ';
        }
        return new String(chars);
    }

    private int collectTree(AVLNode node, int depth, int pos, List<List<String>> lines, int[] widths) {
        if (node == null) return 0;
    
        // Ensure that the lines are large enough
        while (lines.size() <= depth) {
            lines.add(new ArrayList<>());
        }
    
        // Calculate the width of the left subtree
        int leftWidth = collectTree((AVLNode) node.getLeft(), depth + 1, pos, lines, widths);
    
        // Calculate the width of the current node
        String value = Integer.toString(node.getData());
        String padding = " " + repeat(pos + leftWidth - lines.get(depth).size()); // Add correct padding
        lines.get(depth).add(padding + value);
    
        // Calculate the width of the right subtree
        int rightWidth = collectTree((AVLNode) node.getRight(), depth + 1, pos + leftWidth + value.length(), lines, widths);
    
        return leftWidth + value.length() + rightWidth;
    }

}
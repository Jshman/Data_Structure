package Tree;

public class BinarySearchTree {
    private class Node {
        private int data;
        private Node left;
        private Node right;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    private Node root = null;
    private int size = 0;

    public void insert(int data) {
        insert(new Node(data));
    }

    public void insert(Node node) {
        /* root보다 작으면 왼쪽, 크면 오른쪽으로 정렬되게. */

        // root가 null이면 node가 root.
        if (root == null) {
            root = node;
            size++;
            return;
        }
        Node cur = root;
        // 다음 차례에 null과 비교하려고 하면 stop.
        // 같은 데이터는 들어오지 않으므로 cur과 node의 data가 같은 경우는 없다.
        // root보다 크면 오른쪽, 작으면 왼쪽
        while (true) {
            // cur보다 작다면?
            if (node.getData() < cur.getData()) {
                // cur의 왼쪽 자식과 비교해주어야 한다.
                // 그런데 cur.getData()에서 nullPointerException이 일어나면 그 자리에 node가 삽입되어야 한다.
                // 그러니 cur의 left가 null인지 따져주는 if문을 작성하자.
                if (cur.getLeft() == null) {
                    cur.setLeft(node);
                    break;
                }
                cur = cur.getLeft();
            }
            // cur보다 크다면?
            else {
                // cur의 오른쪽 자식과 비교해주어야 한다.
                // 위의 논리대로 코드를 작성한다.
                if (cur.getRight() == null) {
                    cur.setRight(node);
                    break;
                }
                cur = cur.getRight();
            }
        }
        // insert에 성공하면 size는 +1
        size++;
    }

    private int checkChild(Node node) {
        int kid = 0;
        if (node.getLeft() != null) {
            kid++;
        }
        if (node.getRight() != null) {
            kid++;
        }
        return kid;
    }

    public void delete(int data) {
        delete(new Node(data));
    }


    public void delete(Node target) {
        Node cur = root;
        System.out.printf("\n[from delete] Attempted to delete the target(%d).\n\n", target.getData());
        if (!search(target)) {
            System.out.println("[from delete] Couldn't find the target node.");
            return;
        }

        // 현재 노드의 자식 노드 중 target이 있는지 확인한다.
        // target은 자식 노드를 가지는지 확인한다.
        while (cur != null) {
            if (target.getData() < cur.getData()) {
                cur = cur.getLeft();
            } else if (target.getData() > cur.getData()) {
                cur = cur.getRight();
            } else {
                // 트리의 root 노드를 지우는 경우이다.
                // 오른쪽 서브트리에서 가장 작은 값을 찾아서 root node와 자리를 바꾼다.
                // 그 노드의 오른쪽 자식과 부모노드를 잇는다.
                remove(cur.getRight());
                return;
            }
        }

    }

    /* subRoot: 서브트리의 root node */
    private void remove(Node subRoot) {
        if (subRoot == null) return;

        Node parent = subRoot; // 자식 노드의 부모 노드
        Node child = subRoot.getLeft(); // (지워질)자식 노드

        // 서브트리가 없음
        if (child == null) {
            child = parent;
        }

        while (child.getLeft() != null) {
            child = child.getLeft();
            parent = parent.getLeft();
        }

        subRoot.setData(child.getData());
        if (child.getRight() == null) {
            // child의 오른쪽 자식이 없음.
            parent.setLeft(null);
        } else {
            // child의 오른쪽 자식이 있음.
            parent.setLeft(child.getRight());
        }
    }

    private void connect(Node parent, Node child) {
        if (child == null) return;

        if (parent.getData() < child.getData()) {
            parent.setRight(child);
        } else {
            parent.setLeft(child);
        }
    }

    private void connect(Node parent, Node child1, Node child2) {

    }

    public boolean search(int data) {
        return search(new Node(data));
    }

    public boolean search(Node target) {
        /* 노드가 있다면 true, 없다면 false를 반환 */
        // cur 노드와 target 노드의 데이터를 비교해나가면서 target을 탐색한다.
        Node cur = root;
        while (cur != null) {
            // target이 존재하는 경우!
            if (cur.getData() == target.getData()) {
                return true;
            }

            // cur보다 작은 경우  : cur의 왼쪽 자식과 비교해야 한다.
            else if (target.getData() < cur.getData()) {
                // 아니면 왼쪽 노드로.
                cur = cur.getLeft();
            }
            // cur보다 큰 경우
            else {
                cur = cur.getRight();
            }
        }

        return false;
    }

    public void preOrder() {
        /* 전위 순회 : 노드 - 왼쪽자식 - 오른쪽자식 순으로 순회한다. */
        System.out.println("***** 전위 순회 *****");
        preOrder(root);
        System.out.println("\n= = = = = = = = = = = = = = = = = =");
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.printf("%d ", node.getData());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void inOrder() {
        /* 중위 순회 : 왼쪽자식 - 노드 - 오른쪽자식 순으로 순회한다. */
        System.out.println("***** 중위 순회 *****");
        inOrder(root);
        System.out.println("\n= = = = = = = = = = = = = = = = = =");
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.printf("%d ", node.getData());
            inOrder(node.getRight());
        }
    }

    public void postOrder() {
        /* 후위 순회 : 왼쪽자식 - 오른쪽자식 - 노드 순으로 순회한다. */
        System.out.println("***** 후위 순회 *****");
        postOrder(root);
        System.out.println("\n= = = = = = = = = = = = = = = = = =");
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.printf("%d ", node.getData());
        }
    }

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }
}

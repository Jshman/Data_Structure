package Trees;

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
        Node current = root;
        System.out.printf("\n[from delete] Attempted to delete the target(%d).\n\n", target.getData());
        if (!search(target)) {
            System.out.println("[from delete] Couldn't find the target node.");
            return;
        }

        // case 1: target이 root node일때
        if (current.getData() == target.getData()) {
            // case 1-1: root node가 leaf 노드일때
            if (current.getLeft() == null && current.getRight() == null) {
                root = null;
            }
            // case 1-2: root node의 자식이 둘 일때
            else if (current.getRight() != null && current.getLeft() != null) {
                // 오른쪽 서브트리에서 min 값을 찾아 root노드와 자리를 바꾼다.
                // 이때 min값의 parent도 알아야 한다.
                Node min = current;
                Node minParent = current;

                while (min.getLeft() != null) {
                    minParent = min;
                    min = min.getLeft();
                }

                minParent.setLeft(min.getRight());
                min.setLeft(current.getLeft());
                min.setRight(current.getRight());

                root = min;
            }
            // case 1-3: root node의 자식이 하나 일때
            else if (current.getRight() != null) {
                //왼쪽 자식
                Node max = current;
                Node maxParent = current;

                while (max.getRight() != null) {
                    maxParent = max;
                    max = max.getRight();
                }

                maxParent.setRight(max.getLeft());
                max.setLeft(current.getLeft());
                max.setRight(current.getRight());

                root = max;
            }
            else if (current.getLeft() != null) {
                //오른쪽 자식
                Node min = current;
                Node minParent = current;

                while (min.getLeft() != null) {
                    minParent = min;
                    min = min.getLeft();
                }

                minParent.setRight(min.getRight());
                min.setLeft(current.getLeft());
                min.setRight(current.getRight());

                root = min;
            }
            return;
        }

        Node currentParent = current;
        while (current.getData() != target.getData()) {
            currentParent = current;
            if (target.getData() < current.getData()) {
                current = current.getLeft();
            } else if (target.getData() > current.getData()) {
                current = current.getRight();
            }
        }

        // case 2: target이 leaf node일때
        if (current.getRight() == null && current.getLeft() == null) {
            //case 2-1: target이 parent의 오른쪽 자식인 경우
            if (current == currentParent.getRight()) {
                currentParent.setRight(null);
            }
            //case 2-2: target이 parent의 왼쪽 자식인 경우
            else if (current == currentParent.getLeft()) {
                currentParent.setLeft(null);
            }
            return;
        }

        // case 3: target이 subtree의 root node일때
        // case1과 같이 만든다.
        if (current.getLeft() == null && current.getRight() == null) {
            current = null;
        }
        // case 3-2: target node의 자식이 둘 일때
        else if (current.getRight() != null && current.getLeft() != null) {
            // 오른쪽 서브트리에서 min 값을 찾아 root노드와 자리를 바꾼다.
            // 이때 min값의 parent도 알아야 한다.
            Node min = current.getRight();
            Node minParent = current;

            while (min.getLeft() != null) {
                minParent = min;
                min = min.getLeft();
            }

            minParent.setLeft(min.getRight());
            min.setLeft(current.getLeft());
            min.setRight(current.getRight());

            currentParent.setRight(min);
        }
        // case 3-3: target node의 자식이 하나 일때
        else if (current.getRight() != null) {
            //왼쪽 자식
            Node max = current.getLeft();
            Node maxParent = current;

            while (max.getRight() != null) {
                maxParent = max;
                max = max.getRight();
            }

            maxParent.setRight(max.getLeft());
            max.setLeft(current.getLeft());
            max.setRight(current.getRight());

            currentParent.setLeft(max);
        }
        else if (current.getLeft() != null) {
            //오른쪽 자식
            Node min = current.getRight();
            Node minParent = current;

            while (min.getLeft() != null) {
                minParent = min;
                min = min.getLeft();
            }

            minParent.setRight(min.getRight());
            min.setLeft(current.getLeft());
            min.setRight(current.getRight());

            currentParent.setLeft(min);
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
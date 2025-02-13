package Trees;

public class BST {
    private Node root;

    private void setRoot(Node node) {this.root = node;}
    public Node getRoot() {return this.root;}
    
    // 삽입
    public void insert(int data) {
        insert(new Node(data));
    }

    public void insert(Node node) {
        if (this.root == null) {
            this.root = node;
            return;
        }
        
        Node cur = this.root;
        while (cur != null) {
            if (node.getData() <= cur.getData()) {
                if (cur.getLeft() == null) {
                    cur.setLeft(node);
                    node.setParent(cur);
                    return;
                }
                cur = cur.getLeft();
            } else if (cur.getData() < node.getData()) {
                if (cur.getRight() == null) {
                    cur.setRight(node);
                    node.setParent(cur);
                    return;
                }
                cur = cur.getRight();
            }   
        }
    }

    // 삭제
    public Node delete(int data) {
        return delete(new Node(data));
    }

    // 자식 노드가 0개인 경우
    private Node case1(Node targetNode){
        if (targetNode.getData() == this.root.getData()) {setRoot(null);;}

        Node parent = targetNode.getParent();

        if (parent.getLeft().getData() == targetNode.getData()) {parent.setLeft(null);}
        else {parent.setRight(null);}

        targetNode.setParent(null);

        return targetNode;
    }
    
    // 자식 노드가 1개인 경우
    private Node case2(Node targetNode){
        Node parent = targetNode.getParent();

        Node targetNodeChild = null;
        if (targetNode.getLeft() != null) {targetNodeChild = targetNode.getLeft();}
        else {targetNodeChild = targetNode.getRight();}

        // targetNode가 root인 경우
        if (parent == null) {
            targetNodeChild.setParent(null);
            setRoot(targetNodeChild);

            targetNode.setLeft(null);
            targetNode.setRight(null);
            return targetNode;
        }

        // 부모노드의 왼쪽 자식이면
        if (parent.getLeft().getData() == targetNode.getData()) {parent.setLeft(targetNodeChild);}
        // 오른쪽 자식이면
        else {parent.setRight(targetNodeChild);}

        targetNodeChild.setParent(parent);

        targetNode.setParent(null);
        targetNode.setLeft(null);
        targetNode.setRight(null);

        return targetNode;
    }

    //자식 노드가 2개인데, 제일 작은 노드가 자식 노드가 아닐 때
    private Node case31(Node targetNode){
        Node parent = targetNode.getParent();

        Node rightSubTreeNodeParent = targetNode.getRight();
        Node rightSubTreeNode = targetNode.getRight().getLeft();
        while (rightSubTreeNode.getLeft() != null) {
            rightSubTreeNodeParent = rightSubTreeNode;
            rightSubTreeNode = rightSubTreeNode.getLeft();
        }
        // 노드와 부모 노드의 연결을 끊는다.
        rightSubTreeNodeParent.setLeft(rightSubTreeNode.getRight());
        if (rightSubTreeNode.getRight() != null) {rightSubTreeNode.setParent(rightSubTreeNodeParent);}
        
        // targetNode의 자식노드들의 부모노드를 rightSubTreeNode로 설정한다.
        targetNode.getRight().setParent(rightSubTreeNode);
        targetNode.getLeft().setParent(rightSubTreeNode);

        //rightSubTreeNode의 왼쪽 자식을 targetNode의 왼쪽 자식으로, 오른쪽 자식은 오른쪽 자식으로 설정한다.
        rightSubTreeNode.setLeft(targetNode.getLeft());
        rightSubTreeNode.setRight(targetNode.getRight());

        //targetNode의 양쪽자식을 null로 설정한다.
        targetNode.setLeft(null);
        targetNode.setRight(null);

        // 지우려는 노드가 root 노드라면
        if (parent == null) {setRoot(rightSubTreeNode);}
        else {
            // 아니라면 부모노드와 이어줘야 됨.
            rightSubTreeNode.setParent(parent);

            //parent의 왼쪽 자식이면
            if (parent.getLeft().getData() == targetNode.getData()) {parent.setLeft(rightSubTreeNode);}
            else {parent.setRight(rightSubTreeNode);}
        }

        return targetNode;
    }
   
    //자식 노드가 2개인데, 제일 작은 노드가 자식 노드일 때
    private Node case32(Node targetNode){
        Node parent = targetNode.getParent();
        Node rightNode = targetNode.getRight();
        
        // targetNode가 root가 아니면
        if (parent != null) {
            if (parent.getLeft().getData() == targetNode.getData()) {parent.setLeft(rightNode);}
            else {parent.setRight(rightNode);}

            rightNode.setParent(parent);
        }
        // root 가 맞다면
        else {
            setRoot(rightNode);
            rightNode.setParent(null);
        }

        rightNode.setLeft(targetNode.getLeft());
        targetNode.getLeft().setParent(rightNode);
        
        targetNode.setParent(null);
        targetNode.setRight(null);
        targetNode.setLeft(null);
    

        return targetNode;
    }


    public Node delete(Node target) {
        if (this.root == null) {return null;}

        Node curNode = this.root;
        // 목표노드를 찾는다.
        while (curNode != null) { 
            if (target.getData() == curNode.getData()) {break;}
            else if (target.getData() <= curNode.getData()) {curNode = curNode.getLeft();}
            else if (target.getData() > curNode.getData()) {curNode = curNode.getRight();}
        }

        //Not Found
        if (curNode == null) {return null;}

        // 목표노드가 자식노드를 가지고 있는지 파악한다.
        if (curNode.getLeft() == null && curNode.getRight() == null){
            return case1(curNode);
        } else if (curNode.getLeft() != null && curNode.getRight() != null) {
            if (curNode.getRight().getLeft() != null) {return case31(curNode);}
            return case32(curNode);
        } else if (curNode.getLeft() == null || curNode.getRight() == null) {
            return case2(curNode);
        }

        return null;
    }

    // 탐색
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
}
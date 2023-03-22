package LinkedList;

public class DoublyLinkedList {
    class Node{
        int data;
        Node previous, next;
        Node(int data){
            this.data = data;
        }
    }

    Node head, tail;
    int size;
    DoublyLinkedList() {
        // 생성자는 딱히 필요없음.
    }

    public void insert(int data) {
        Node node = new Node(data);
        if (size == 0){
            head = node;
            tail = new Node(data);
            size++;
            return;
        }

        size++;

        Node current = head;
        while(current != null) {
            if (current.data < node.data) {

                // 현재 노드가 tail인 경우 (현재 노드가 가장 큰 경우)
                if (current.next == null) {
                    tail = node;
                    node.previous = current;
                    current.next = node;
                    return;
                }

                // 그게 아니면 다음 노드를 탐색한다.
                current = current.next;

            }
            else if(current.data >= node.data) {
                node.next = current;
                node.previous = current.previous; // current == head일 땐 어차피 head.previous == null이라 상관없는 코드.
                if (current.previous == null) {
                    current.previous = node;
                    head = node;
                    return;
                }
                current.previous.next = node;
                current.previous = node;
                return;
            }
        }

    }

    public Node delete(int index) {
        if (index < 0 || size-1 < index) {
            System.out.println("Invalid index entered.");
            return null;
        }
        Node target = getNode(index); // target이 head 또는 tail인 경우가 존재

        // head인 경우
        if (target.previous == null) {
            head = target.next;
            target.next.previous = null;
        }
        // tail인 경우
        else if (target.next == null) {
            tail = target.previous;
            target.previous.next = null;
        }
        // 나머지의 경우
        else {
            target.previous.next = target.next;
            target.next.previous = target.previous;
            System.out.println("delete completion");
        }
        return target;
    }

    public Node getHead() {return head;}
    public Node getTail() {return tail;}
    public Node getNode(int index) {
        if (index < 0 || size-1 < index){
            return null;
        }
        Node node = null;
        if (index < size/2) {
            node = searchForward(index);
        } else {
            node = searchBackward(index);
        }
        return node;
    }

    private Node searchForward(int index) {
        Node current = head;
        int idx = 0;
        while (idx++ < index){
            current = current.next;
        }
        return current;
    }
    private Node searchBackward(int index) {
        Node current = tail;
        int idx = size-1;
        while (idx-- > index) {
            current = current.previous;
        }
        return current;
    }

    public void printForward() {
        Node node = head;
        int count = 1;
        System.out.println("[print Forward 실행]");
        while (node != null) {
            System.out.printf("%d번째 노드 | data : %d\n", count++, node.data);
            node = node.next;
        }
//        System.out.println("= = = = = = = = = = = = = = = = = = = = = = =");
    }
    public void printBackward() {
        Node node = tail;
        int count = 1;
        System.out.println("[print Backward 실행]");
        while (node != null) {
            System.out.printf("%d번째 노드 | data : %d\n", count++, node.data);
            node = node.previous;
        }
    }
}

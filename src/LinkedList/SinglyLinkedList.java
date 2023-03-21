package LinkedList;

public class SinglyLinkedList {
    class Node {
        int data;
        Node next;

        Node(int data) {this.data = data;}

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    int size = 0;// 연결 리스트에 저장된 원소의 개수
    Node start = null; //연결 리스트를 가리키는 레퍼런스 , start == head

    // x를 list에 내림차순으로 삽입하는 메서드
    void insert(int x){
        size++;
        Node newNode = new Node(x);
        if (start == null) {
            start = newNode;
            return;
        }


        Node existing = start;
        while (existing != null) {
            if (existing.data > newNode.data) {
                if (existing.next == null) {
                    existing.next = newNode;
                    return;
                }
                if (existing.next.data <= newNode.data) {
                    newNode.next = existing.next;
                    existing.next = newNode;
                    return;
                }
                else {
                    existing = existing.next;
                }
                // 위에서 처리해주었기 때문에 이 조건문은 start와 newNode가 같은 경우를 따지는 조건문이다.
            }
            else if (existing.data <= newNode.data) {
                newNode.next = start;
                start = newNode;
                return;
            }
        }

        System.out.println("[insert] 제대로 연결되지 않았습니다.");
        return;
    }
    void setLink(Node head, Node tail) {
        tail.next = head.next;
        head.next = tail;
    }

    // 연결 리스트의 값을 차례대로 출력하는 메서드
    void print() {
        Node node = start;
        while (node != null) {
            System.out.printf(node.data+" ");
            node = node.next;
        }
        System.out.println();
    }
}

package LinkedList;

public class CircularLinkedList {
    class Node{
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
        void setNext(Node next) {
            this.next = next;
        }
    }
    CircularLinkedList() {
        // 필요없음
    }
    int size = 0;
    Node tail;
//    Node head; // -> tail.next와 같음. 그래서 굳이 필요없음

    //삽입
    // 오름차순으로 정렬되도록 삽입을 구현할 예정임. (tail이 가장 큰 수가 되도록)
    public void insert(int data) {
        insert(new Node(data));
    }
    public void insert(Node node) {
        if (size == 0) {
            // 리스트 안에 원소(노드)가 하나도 없는 경우
            tail = node;
            size++;
            return;
        }


        else if (size == 1){ //tail == head
            tail.next = node;
            node.next = tail;
            if (tail.data < node.data) {
                tail = node;
            }
            size++;
            return;
        }

        Node current = tail.next;
        for (int i=0; i<size; i++) {
            if (current.data < node.data) {
                // 다음 노드랑 new node랑 비교해야지
                // 그런데 다음 노드가 null, 즉 current가 tail인 경우는 어떡해?
                if (i == size-1) {
                    // current가 tail인 경우.
                    tail = node;
                    node.next = current.next;
                    current.next = node;
                    size++;
                    return;
                } else if (current.next.data >= node.data) {
                    node.next = current.next;
                    current.next = node;
                    size++;
                    return;
                }
                // 그게 아니라면
                current = current.next;
            } else if (current.data >= node.data) {
                //위에서 작거나 같은 경우를 따져주었으므로 이곳에 들어오는 경우는 node가 head가 되는 경우이다.
                node.next = current;
                tail.next = node;
                size++;
                return;
            }
        }
    }

    //삭제
    // 해당 data를 가진 노드를 삭제함.
    public void delete(int data) {
        Node node = new Node(data);
        delete(node);
    }
    // 노드의 data를 기준으로 노드를 삭제함.
    public Node delete(Node target) {
        //case0 빈 리스트
        if (size == 0) {
            System.out.println("[This list is already empty]");
            return null;
        }
        //case1 원소가 하나만 존재
        else if (size == 1 && target.data == tail.data) {
            size--;
            tail = null;
            return target;
        }

        //case2 원소가 둘 이상 존재
        else {
            Node current = tail.next;
            //case2-1 삭제하려는 노드가 head인 경우
            if (target.data == tail.next.data) {
                tail.next = current.next;
                target.next = null;
                size--;
                return target;
            }
            // case2-2,3 삭제하려는 노드가 중간 또는 끝에 있는 경우
            // 탐색을 통해서 target을 찾는다.
            for (int i=0; i<size; i++) {
                if (current.next.data == target.data) {
                    current.next = current.next.next;
                    target.next = null;
                    size--;
                    return target;
                }
                current = current.next;
            }
        }
        System.out.println("해당 노드를 찾을 수 없습니다.");
        return null;
    }

    //탐색
    public Node search(int index){
        Node node = tail.next; //==head
        int current = 0;
        while (current++ < index) {
            node = node.next;
        }
        return node;
    }

    //출력
    public void print() {
        Node node = tail.next;
//        System.out.println(tail.data+" 이거 맞냐");
        if (node == null) {
            return;
        }
        for (int i=0; i<size; i++) {
            System.out.printf("%d번째 노드 | %d\n", i+1, node.data);
            node = node.next;
        }
    }
}

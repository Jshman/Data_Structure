package UnivHyojeong;

public class MergeList {
    private class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
        public int getValue(){
            count++;
            return value;
        }
    }
    protected Node firsthead;
    protected Node secondhead;
    private int size1, size2, count;

    public MergeList() {
        firsthead = null;
        secondhead = null;
        size1 = 0;
        size2 = 0;
        count = 0;
    }

    public void inserLast(int newItem, int idx) {
        /* 리스트의 마지막 요소에 삽입하는 메소드를 구현 */
        Node node = null;
        if (idx == 1) {
            node = firsthead;
            size1++;
            if (firsthead == null) {
                firsthead = new Node(newItem);
                return;
            }
        } else if (idx == 2) {
            node = secondhead;
            size2++;
            if (secondhead == null) {
                secondhead = new Node(newItem);
                return;
            }
        } else {
            System.out.println("idx를 잘못입력함");
            return;
        }

        while (node != null) {
            if (node.next == null) {
                node.next = new Node(newItem);
                break;
            }
            node = node.next;
        }
        // idx=1는 firsthead의 마지막 요소에 삽입
        // idx=2는 secondhead의 마지막 요소에 삽입
    }

    public void merge() {
        Node cur_first = firsthead;
        Node cur_second = secondhead;
        Node Mergehead = null;

        if (cur_first.value <= cur_second.value) {
            Mergehead = cur_first;
            if (cur_first.value == cur_second.value) {
                cur_second = cur_second.next;
                count++;
            }
            cur_first = cur_first.next;
            count++;
        } else if (cur_first.value > cur_second.value) {
            Mergehead = cur_second;
            cur_second = cur_second.next;
            count++;
        }

        firsthead = Mergehead;
        secondhead = null;
        try {
            /* MergeSort의 Merge 메소드를 활용하여 구현 */
            for (;;) {
                if (cur_first.value <= cur_second.value) {
                    if (cur_first.value == cur_second.value) {
                        cur_second = cur_second.next;       // 다음 노드로 이동
                        count++;
                    }
                    if (Mergehead.value == cur_first.value) {
                        cur_first = cur_first.next;     // 다음 노드로 이동
                        count++;
                        continue;
                    }
                    Mergehead.next = cur_first;
                    cur_first = cur_first.next;     // 다음 노드로 이동
                    count++;
                } else if (cur_first.value > cur_second.value) {
                    if (Mergehead.value == cur_second.value) {
                        cur_second = cur_second.next;       // 다음 노드로 이동
                        count++;
                        continue;
                    }
                    Mergehead.next = cur_second;
                    cur_second = cur_second.next; // 다음 노드로 이동
                    count++;
                }
                Mergehead = Mergehead.next;
            }
        } catch (NullPointerException e) {
            count++;
            if (cur_first == null) {
                Mergehead.next = cur_second;
                count++;
            } else if (cur_second == null) {
                Mergehead.next = cur_first;
                count++;
            }
        }
   }

    public void printall() {
        // 두 리스트의 요소들을 출력.
        System.out.printf("[1]");
        Node node = firsthead;
        while (node != null) {
            System.out.printf(" %d ", node.value);
            node = node.next;
        }

        node = secondhead;
        System.out.printf("\n[2]");
        while (node != null) {
            System.out.printf(" %d ", node.value);
            node = node.next;
        }

        System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = =");
    }

    private void setSize(int idx) {
        Node node = null;
        if (idx == 1) {
            node = firsthead;
        } else if (idx == 2){
            node = secondhead;
        }

        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }

        if (idx == 1) {
            size1 = size;
        } else if (idx == 2){
            size2 = size;
        }
    }
    public int getSize1(){
        setSize(1);
        return size1;
    }
    public int getSize2(){
        setSize(2);
        return size2;
    }
    public int getCount(){return count;}

}

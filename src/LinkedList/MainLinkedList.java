package LinkedList;

import java.io.*;

public class MainLinkedList {
    public static void main(String[] args) throws IOException {
        SinglyLinkedList ll = null;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\testProject\\src\\LinkedList\\input.txt"));

        String str;
        String[] stringlist;
        while((str = br.readLine()) != null){
            ll = new SinglyLinkedList();

            stringlist = str.split(" ");
            for (String tmp : stringlist) {
                ll.insert(Integer.parseInt(tmp));
            }
            ll.print();
        }
    }
}

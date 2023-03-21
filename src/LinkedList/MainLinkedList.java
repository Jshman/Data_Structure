package LinkedList;

import java.io.*;

public class MainLinkedList {
    public static void main(String[] args) throws IOException {
        SinglyLinkedList ll = null;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\testProject\\src\\LinkedList\\input.txt"));

        String str;
        String[] stringlist;
        int count = 0;
        while((str = br.readLine()) != null){
            ll = new SinglyLinkedList();

            System.out.printf("[%d번 째 줄 리스트]\n", ++count);
            stringlist = str.split(" ");
            for (String tmp : stringlist) {
                ll.insert(Integer.parseInt(tmp));
                ll.print();
            }
            System.out.println("= = = = = = = = = = = = = =");
        }
    }
}

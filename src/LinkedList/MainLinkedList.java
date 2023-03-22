package LinkedList;

import java.io.*;

public class MainLinkedList {
    public static void main(String[] args) throws IOException {
        SinglyLinkedList ll = null;
        DoublyLinkedList dl = null;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\testProject\\src\\LinkedList\\input.txt"));

        String str;
        String[] stringlist;
        int count = 0;
        while ((str = br.readLine()) != null) {
            dl = new DoublyLinkedList();

            System.out.printf("[%d번 째 줄 리스트]\n", ++count);
            stringlist = str.split(" ");
            for (String tmp : stringlist) {
                dl.insert(Integer.parseInt(tmp));
            }
            dl.printForward();
            System.out.println("= = = = = = = = = = = = = =");
            dl.delete(2);
            dl.printForward();
            System.out.println("= = = = = = = = = = = = = =");
        }
    }
}

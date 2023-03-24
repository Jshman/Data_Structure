package LinkedList;

import java.io.*;

public class MainLinkedList {
    public static void main(String[] args) throws IOException {
        CircularLinkedList cl;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\testProject\\src\\LinkedList\\input.txt"));

        String str;
        String[] stringlist;
        int count = 0;
        while ((str = br.readLine()) != null) {
            stringlist = str.split(" ");
            cl = new CircularLinkedList();
            for (String s : stringlist) {
                cl.insert(Integer.parseInt(s));
//                System.out.println("= = = = = = = = = = = = = = = =");
            }
            cl.print();
            cl.delete(1);
            cl.delete(5);
            cl.delete(10);
            cl.delete(100);
            System.out.println("= = = = = = = = = = = = = = = =");
            cl.print();
        }
    }
}

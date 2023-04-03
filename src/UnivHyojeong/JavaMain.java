package UnivHyojeong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JavaMain {
    public static void main(String[] args) throws IOException {
        /* Load */
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\testProject\\src\\UnivHyojeong\\input.txt"));
        String[] stringlist;
        String[] stringlist2;

        MergeList ml = new MergeList();

        // txt파일에서 첫번째 줄을 읽어오고 첫번째 리스트에 삽입
        stringlist = br.readLine().split(" ");
        for (String elem : stringlist) {
            ml.inserLast(Integer.parseInt(elem), 1);
        }

        // txt파일에서 두번째 줄을 읽어오고 두번째 리스트에 삽입
        stringlist2 = br.readLine().split(" ");
        for (String elem : stringlist2) {
            ml.inserLast(Integer.parseInt(elem), 2);
        }

        /* Merge */
        //모든 리스트 출력
        ml.printall();
        //합병
        ml.merge();
        //모든 리스트 출력
        ml.printall();
        System.out.println(ml.getSize1());
        System.out.println(ml.getSize2());
        System.out.println(ml.getCount());
    }
}

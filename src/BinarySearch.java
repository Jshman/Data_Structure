import java.util.*;
import java.util.stream.IntStream;

public class BinarySearch {
    public static void main(String[] args) {
        int tmp;
        int target;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random(0);
        // 2의 거듭제곱 반복
        for (int n = 3; n<=10; n++) {
            tmp = (int) Math.pow(2, n);


            int[] bin_arr = IntStream.rangeClosed(0, tmp-1).toArray();
            int[] seq_arr = new int[tmp];

            // 선형 배열에 난수 추가하기
            for (int i=0; i<tmp; i++){
                seq_arr[i] = rand.nextInt(tmp);
            }

            // 예제의 기본 입출력
            System.out.printf("n = %d\n", tmp);
            target = rand.nextInt(tmp);
            System.out.printf("목표 값: %d\n", target);

            seq_search(seq_arr, target);
            bin_search(bin_arr, target);
            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = =");
        }


    }

    /*
        [선형탐색] target을 array에서 발견하면, target의 index를 반환합니다.
     */
    public static int seq_search(int[] array, int target){
        for (int i=0; i<array.length; i++){
            if (array[i] == target) {
                System.out.printf("순차탐색(count, index): (%d, %d)\n", i+1, i);
//                System.out.println(Arrays.toString(array));
                return i;
            }
        }
        System.out.printf("순차탐색(count, index): (%d, %d)\n", array.length, -array.length);
        return -array.length;
    }


    /*
        [이진탐색] target을 array에서 발견하면, target의 index를 반환합니다.
     */
    public static int bin_search(int[] array, int target){
        int left, right, mid; // 탐색하려는 index
        left = 0;
        right = array.length-1;
        mid = (left + right) / 2;
        int count = 1;
        while (left <= right) {
//            System.out.println(left+" "+mid+" "+right+" "+target);
            if (array[mid] == target) {
                System.out.printf("이진탐색(count, index): (%d, %d)\n", count, mid);
                return mid;
            }
            else if (array[mid] < target) {left = mid+1;}
            else {right = mid-1;}
            mid = (left + right) / 2;
            count++;
        }
        return -array.length;
    }

}

import java.util.*;

public class Mainjava {
    public static void main(String[] args) {

        Random rand = new Random(0);

        // 2의 거듭제곱 반복
        for (int sq=3;sq<=10;sq++){
            int n=(int)(Math.pow(2,sq));

            int[] bin_ary = new int[n];
            for (int i=0; i<n; i++) {
                bin_ary[i]=i;
            }// 0부터 n-1까지의 정수를 순서대로 저장한 배열


            int[] seq_ary = new int[n]; // 0에서 n-1사이의 난수를 저장한 배열
            for (int i=0; i<n; i++) {
                seq_ary[i]=rand.nextInt(n);
            }
            int target = rand.nextInt(n); // 0에서 n-1 사이의 랜덤한 정수 생성하여 저장
// 순차탐색을 호출하여 타겟 인덱스와 연산 회수 프린트
            System.out.println("n= "+ n);
            System.out.println("목표 값: "+target);
            seq_search(seq_ary,target);
            bin_search(bin_ary,target);
            System.out.println("= = = = = = = = = = = = = = = = = = = = = = =");
// 이진탐색을 호출하여 타겟 인덱스와 연산 회수 프린트
        }
    }
    public static int seq_search(int[] array, int target)
    {
        int count=0;
        int index=0;
        for(int i=0; i<array.length; i++){
            count++;
            if(array[i]==target){
                index = i;
                System.out.println("순차탐색(count, index): ("+count+", "+index+")");
                return 0;
            }
        }
        index = -array.length;
        System.out.println("순차탐색(count, index): ("+count+", "+index+")");
        return 0;

// TODO 순차탐색 구현
    }
    public static int bin_search(int[] array, int target)
    {
        int count=0;
        int p=0;
        int q=array.length-1;
        while(p<=q){
            count++;
            int mid=(p+q)/2;
            if(array[mid]==target){
                System.out.println("이진탐색(count, index): ("+count+", "+mid+")");
                return 0;
            }else if (array[mid]<target){
                p = mid+1;
            }else{
                q = mid-1;
            }
        }
        System.out.println("이진탐색(count, index): ("+count+", "+(-array.length)+")");
        return 0;
// TODO 이진탐색 구현
    }
}
package array.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class rk24 {

    public static int M;  // 目标值

    public static int n; // 源数组长度
    public static int[] A;

    public static int[] remain_sum; // 剩余元素之和数组 

    public void printArray(List<Integer> p) {
        System.out.println(Arrays.toString(p.toArray()));
    }

    void backtrack(int index, int current_sum, ArrayList<Integer> p, int pLen) {
        if (current_sum == M) {
            printArray(p);
            return;
        }

        if (index >= n) {
            return;
        }

        if (current_sum + remain_sum[index] < M){
            return;
        }

        if (current_sum + A[index] <= M) {
            p.add(A[index]);
            backtrack(index + 1, current_sum + A[index], p, pLen + 1);
            p.removeLast();
        }

        if (current_sum + remain_sum[index + 1] > M) {
            return;
        }

    }

    public static void main(String[] args) {
        rk24 test = new rk24();
        M = 5;

        n = 5;
        A = new int[]{1,2,3,4,5};

        remain_sum = new int[n + 1];
        remain_sum[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            remain_sum[i] = remain_sum[i + 1] + A[i];
        }

        ArrayList<Integer> res = new ArrayList<>();
        test.backtrack(0, 0, res, 0);

    }

}

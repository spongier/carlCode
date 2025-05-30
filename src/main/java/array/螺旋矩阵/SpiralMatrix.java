package array.螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        // 借助四个变量，控制i、j的范围
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int count = 1, tar = n * n;
        while (count <= tar) {
            // 从左到右处理最上一行，结束最上层遍历
            for (int i = l; i <= r; i++)
                res[t][i] = count++;
            t++;
            // 从上到下处理最右一列，结束最右遍历
            for (int i = t; i <= b; i++)
                res[i][r] = count++;
            r--;
            // 从右到左处理最下一行，结束最下层遍历
            for (int i = r; i >= l; i--)
                res[b][i] = count++;
            b--;
            // 从下到上处理最左一列，结束最左遍历
            for (int i = b; i >= t; i--)
                res[i][l] = count++;
            l++;
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        // 借助四个变量，控制i、j的范围
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        while (true) {
            // 从左到右处理最上一行，结束最上层遍历
            for (int i = l; i <= r; i++)
                res.add(matrix[t][i]);
            if (++t > b)
                break;

            // 从上到下处理最右一列，结束最右遍历
            for (int i = t; i <= b; i++)
                res.add(matrix[i][r]);
            if (--r < l)
                break;

            // 从右到左处理最下一行，结束最下层遍历
            for (int i = r; i >= l; i--)
                res.add(matrix[b][i]);
            if (--b < t)
                break;

            // 从下到上处理最左一列，结束最左遍历
            for (int i = b; i >= t; i--)
                res.add(matrix[i][l]);
            if (++l > r)
                break;
        }
        return res;
    }

    public int[] spiralArray(int[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return new int[0];

        int n = matrix[0].length;
        int[] res = new int[m * n];
        int index = 0;

        int l = 0, r = n - 1, t = 0, b = m - 1;
        while (true) {
            // 从左到右处理最上一行，结束最上层遍历
            for (int i = l; i <= r; i++)
                res[index++] = matrix[t][i];
            if (++t > b)
                break;

            // 从上到下处理最右一列，结束最右遍历
            for (int i = t; i <= b; i++)
                res[index++] = matrix[i][r];
            if (--r < l)
                break;

            // 从右到左处理最下一行，结束最下层遍历
            for (int i = r; i >= l; i--)
                res[index++] = matrix[b][i];
            if (--b < t)
                break;

            // 从下到上处理最左一列，结束最左遍历
            for (int i = b; i >= t; i--)
                res[index++] = matrix[i][l];
            if (++l > r)
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        SpiralMatrix test = new SpiralMatrix();
        int[][] matrix1 = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };

        test.spiralOrder(matrix1);
    }

}

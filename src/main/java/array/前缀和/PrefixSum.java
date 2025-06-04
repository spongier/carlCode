package array.前缀和;

import java.util.TreeSet;

public class PrefixSum {
    // 预处理数组，存储前 i 项的和，快速计算任意子数组和

    // lc 560
    // prefix[i] 是累加到i-1的前缀和，prefix[j + 1] 是累加到j位置的
    public int subarraySum(int[] nums, int k) {
        // 前缀和准备 i + 1累加和 包含i
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // 没法子滑动窗口，有负数
        int count = 0;
        for (int j = 0; j < nums.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (preSum[j + 1] - preSum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // lc 303 一维区间
    class NumArray {

        private int[] preSum;
        public NumArray(int[] nums) {
            int len = nums.length;
            if (nums == null || len <= 0)   return;
            preSum = new int[len + 1];

            for (int i = 0; i < len; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right] - preSum[left];
        }
    }

    // lc 304 二维区域和
    class NumMatrix {

        private int[][] prefixSum;

        public NumMatrix(int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            if (matrix == null || rows == 0 || cols == 0) return;
            prefixSum = new int[rows + 1][cols + 1];

            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    prefixSum[i][j] = matrix[i - 1][j - 1]
                            + prefixSum[i - 1][j]
                            + prefixSum[i][j - 1]
                            - prefixSum[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefixSum[row2 + 1][col2 + 1]
                    - prefixSum[row2 + 1][col1]
                    - prefixSum[row1][col2 + 1]
                    + prefixSum[row1][col1];
        }
    }

    // lc 363
    // 核心思想：约定行的上下限，将二维数组拍扁（计算每一列之和）
    // 问题转变为 在一维数组中，求解和不超过 K 的最大连续子数组之和
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        int maxRes = Integer.MIN_VALUE;

        for (int r1 = 0; r1 < rows; r1++) {
            int[] colSum = new int[cols];

            // 内层循环，枚举下边界
            for (int r2 = r1; r2 < rows; r2++) {

                // 更新列累加和
                for (int i = 0; i <= cols; i++) {
                    colSum[i] += matrix[r2][i];
                }

                // 问题转为 一维数组colSum 找最大子数组和 <= k
                TreeSet<Integer> prefixSet = new TreeSet<>();
                prefixSet.add(0);
                int curPrefix = 0;

                for (int num : colSum) {
                    curPrefix += num;
                    // curSum - preSum <= k ---> preSum >= curSum - k
                    Integer tar = prefixSet.ceiling(curPrefix - k);
                    if (tar != null) {
                        maxRes = Math.max(maxRes, curPrefix - tar);
                    }
                    prefixSet.add(curPrefix);
                }

            }
        }
        return maxRes;
    }

}

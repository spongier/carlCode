package array.前缀和;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class PrefixSum {
    // 预处理数组，存储前 i 项的和，快速计算任意子数组和

    // lc 303 一维区间
    class NumArray {

        private int[] preSum;

        public NumArray(int[] nums) {
            int len = nums.length;
            if (nums == null || len <= 0)
                return;
            preSum = new int[len + 1];

            for (int i = 0; i < len; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    // lc 304 二维区域和
    class NumMatrix {

        private int[][] prefixSum;

        public NumMatrix(int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            if (matrix == null || rows == 0 || cols == 0)
                return;
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


    // lc 560 
    public int subarraySum(int[] nums, int k) {
        // 一维数组找 子数组和为k
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            // nums[j] - nums[i] 才是子数组
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // lc 1074 与560 同属一类
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int res = 0;

        for (int r1 = 0; r1 < rows; r1++) {
            int[] colSum = new int[cols]; // 存储当前 r1 到 r2 的列累加和

            // 枚举下边界
            for (int r2 = r1; r2 < rows; r2++) {
                // 更新列累加和
                for (int i = 0; i < cols; i++) {
                    colSum[i] += matrix[r2][i];
                }

                // 转变为：一维数组统计 和为target的子数组 数量
                // 理解难点：一维数组找 子数组，怎么找？比如找到一个子数组和为target，那么它肯定是nums[j] - nums[i]
                // j > i 理解为长数组-短数组，（在cols数组就理解为 长区间 - 短区间
                Map<Integer, Integer> prefixMap = new HashMap<>();
                prefixMap.put(0, 1);
                int curPrefix = 0;
                for (int num : colSum) {
                    curPrefix += num;
                    res += prefixMap.getOrDefault(curPrefix - target, 0);
                    prefixMap.put(curPrefix, prefixMap.getOrDefault(curPrefix, 0) + 1);
                }
            }
        }
        return res;
    }

    // lc 363
    // 核心思想：约定行的上下限，将二维数组拍扁（计算每一列之和）
    // 问题转变为 在一维数组中，统计子数组和不超过K的 最大和值
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        int maxRes = Integer.MIN_VALUE;

        for (int r1 = 0; r1 < rows; r1++) {
            int[] colSum = new int[cols];
            for (int r2 = r1; r2 < rows; r2++) {
                for (int i = 0; i <= cols; i++) {
                    colSum[i] += matrix[r2][i];
                }

                // 转变为：一维数组colSum 找最大子数组和 <= k
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

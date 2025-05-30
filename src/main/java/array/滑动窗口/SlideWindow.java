package array.滑动窗口;

import java.util.HashSet;

class SlideWindow {

    // 滑动窗口，是双指针的难点，控制窗口!

    // lc 209
    // 思路：1、排序(x，子数组，别想排序了！)
    public int minSubArrayLen(int target, int[] nums) {
        // 窗口：连续子数组，其和>= target
        // 窗口起始位置何时移动：>= target （只控制左指针，不符合>= 进入r的下一轮
        // 窗户结束位置何时移动：循环结束自动r++

        int res = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = 0;
        while (r < nums.length) {
            sum += nums[r];

            // 循环操作左指针
            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // lc 904
    // 窗口：窗口内最多出现两种水果
    // 右指针：正常遍历
    // 左指针：维持窗口水果种类，但是1 2 1 3 这种就需要将种类1全部移除掉，才能将种类数-1，就需要有个种类计数器

    public int totalFruit(int[] fruits) {
        int res = Integer.MIN_VALUE;

        int[] countTypeNum = new int[fruits.length + 1];
        int totalType = 0;
        for (int i = 0, j = 0; i < fruits.length; i++) {
            countTypeNum[fruits[i]]++;
            if (countTypeNum[fruits[i]] == 1) {
                // 第一次出现
                totalType++;
            }

            while (totalType > 2) {
                // 窗口内水果种类变多，去除掉
                countTypeNum[fruits[j]]--;
                if (countTypeNum[fruits[j]] == 0)
                    totalType--;
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // lc 76
    // 窗口就是包含t字符串里面的全部元素，左窗口移动条件：移动到新位置，该位置对应的字符串不再满足窗口条件
    public String minWindow(String s, String t) {
        int[] hash = new int[128];
        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i)]--; // 负值代表有 相关字符的空缺
        }

        char[] sArr = s.toCharArray();
        int demand = 0;
        int res = Integer.MAX_VALUE;
        int L = -1;
        for (int i = 0, j = 0; j < sArr.length; j++) {
            if (++hash[sArr[j]] <= 0)
                demand++; // 负值表示存在空缺未填满

            // 缩小窗口
            while (i < j && hash[sArr[i]] > 0) {
                hash[sArr[i]]--;
                i++;
            }

            // 记录最小窗口
            if (demand == t.length() && res > j - i + 1) {
                res = j - i + 1;
                L = i;
            }
        }
        return res == Integer.MAX_VALUE ? "" : s.substring(L, L + res);
    }

    // lc 3 按照滑动窗口标准模板来
    public int lengthOfLongestSubstring(String s) {
        char[] st = s.toCharArray();
        int[] hash = new int[128];
        int res = 0;
        for (int i = 0, j = 0; j < st.length; j++) {
            // j元素历史出现过，左窗口一直缩小到历史出现过j值的位置
            while (i < j && hash[st[j]] != 0)
                hash[st[i++]]--;
            hash[st[j]]++;
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        SlideWindow test = new SlideWindow();
        // int[] testArr = new int[] {
        // 3,3,3,1,2,1,1,2,3,3,4
        // };
        // test.totalFruit(testArr);

        String s = "ADOBECODEBANC";
        String t = "ABC";
        String m = "tmmzuxt";
        test.lengthOfLongestSubstring(m);
    }
}
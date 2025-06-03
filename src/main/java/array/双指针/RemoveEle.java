package array.双指针;

class RemoveEle {

  private void swap(int[] nums, int l, int r) {
    int tmp = nums[l];
    nums[l] = nums[r];
    nums[r] = tmp;
  }

  // lc 26 l = r就移动r，不等就swap
  public int removeDuplicates(int[] nums) {
    int slow = 1;
    for (int fast = 1; fast < nums.length; fast++) {
      if (nums[fast] != nums[slow - 1]) {
        nums[slow] = nums[fast];
        slow++;
      }
    }
    return slow;
  }

  // lc 80
  public int removeDuplicatesK(int[] nums, int k) {
    int n = nums.length;
    if (n <= k)
      return n;

    int slow = k;
    for (int fast = k; fast < n; fast++) {
      if (nums[fast] != nums[slow - k]) {
        nums[slow] = nums[fast];
        slow++;
      }
    }
    return slow;
  }

  // lc 27 双指针: r向前找 !=val
  public int removeElement(int[] nums, int val) {
    int slow = 0;
    for (int fast = 0; fast < nums.length; fast++) {
      if (nums[fast] != val) {
        nums[slow] = nums[fast];
        slow++;
      }
    }
    return slow;
  }

  // lc 283 27题删除所有val值，直接覆盖掉，该题多一步：slow后面补零
  public void moveZeroes(int[] nums) {
    int slow = 0;
    for (int fast = 0; fast < nums.length; fast++) {
      if (nums[fast] != 0) {
        nums[slow] = nums[fast];
        slow++;
      }
    }
    // slow 及后面全部置为0
    while (slow < nums.length)
      nums[slow++] = 0;
  }

  // lc 75 颜色分类
  // left：标记 0 的右边界（[0, left) 均为 0）。
  // right：标记 2 的左边界（(right, n-1] 均为 2）。
  // i：当前遍历指针。
  public void sortColors(int[] nums) {
    int left = 0, right = nums.length - 1;
    int i = 0;
    while (i <= right) {
      if (nums[i] == 0) {
        swap(nums, left, i);
        left++;
        i++; // 很关键！必须保证i走在left前面
      } else if (nums[i] == 2) {
        swap(nums, right, i);
        right--;
      } else {
        i++;
      }
    }
  }

  // lc 905 将偶数移动到
  public int[] sortArrayByParity(int[] nums) {
    for(int i = 0, j = nums.length - 1; i < j; i++) {
      if (nums[i] % 2 == 1) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        j--;  // 将奇数往后丢
        i--;  // 与j交换后的i位置，需要重新监测
      }
    }
    return nums;
  }

  // lc 844
  // #代表删除前面一个字符，删完后的字符对比是不是一样的
  public boolean backspaceCompare(String s, String t) {
    int sp = s.length() - 1;
    int tp = t.length() - 1;
    int sNum = 0, tNum = 0;
    while (sp >= 0 || tp >= 0) {
      // 处理#
      while (sp >= 0) {
        if (s.charAt(sp) == '#') {
          sNum++;
        } else {
          if (sNum > 0) {
            sNum--;
          } else {
            // 处理完多个#
            break;
          }
        }
        sp--;
      }
      while (tp >= 0) {
        if (t.charAt(tp) == '#') {
          tNum++;
        } else {
          if (tNum > 0) {
            tNum--;
          } else {
            // 处理完多个#
            break;
          }
        }
        tp--;
      }

      // 处理完全部#
      if (sp >= 0 && tp >= 0) {
        if (s.charAt(sp) != t.charAt(tp))
          return false;
      } else {
        if (sp >= 0 || tp >= 0)
          return false; // 还存在一种情况，sp、tp都<0，这种就是true
      }
      sp--;
      tp--;
    }
    return true;
  }

  // lc 977
  // 思路：一头一尾比较绝对值，谁大就平方放入新数组“当前最后一位”，
  public int[] sortedSquares(int[] nums) {
    int[] res = new int[nums.length];
    int l = 0, r = nums.length - 1;
    int pos = nums.length - 1;

    while (l <= r) {
      if (Math.abs(nums[l]) > Math.abs(nums[r])) {
        res[pos--] = nums[l] * nums[l];
        l++;
      } else {
        res[pos--] = nums[r] * nums[r];
        r--;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    RemoveEle test = new RemoveEle();
    int[] testArr = new int[] { 2, 0, 2, 1, 1, 0 };
    test.sortColors(testArr);
  }

}
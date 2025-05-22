package array.移除元素;

class RemoveEle {

  private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

  // lc 27
  // 双指针: r向前找 !=val 
  public int removeElement(int[] nums, int val) {
    int l = 0, r = 0;
    while (r < nums.length){
      // != 开始搬运
      if (nums[r] != val){
        swap(nums, l, r);
        l++;
      }
      r++;
    }
    return l;
  }

  // lc 26
  // l = r就移动r，不等就swap
  public int removeDuplicates(int[] nums) {
    int l = 0, r = 0;
    while (r < nums.length){
      if (nums[l] != nums[r]){
        l++;
        swap(nums, l, r);
      }
      r++;
    }
    return l + 1;
  }

  // lc 80
  public int removeDuplicatesK(int[] nums, int k) {
    int l = 0, r = 0;
    while (r < nums.length) {
      if (l < k || nums[l - k] != nums[r]) {
        swap(nums, l, r);
        l++;
      }
      r++;
    }
    return l;
  }

  // lc 283
  public void moveZeroes(int[] nums) {
    int l = 0, r = 0;
    while (r < nums.length){
      // != 开始搬运
      if (nums[r] != 0){
        swap(nums, l, r);
        l++;
      }
      r++;
    }
  }

  // lc 844
  // #代表删除前面一个字符，删完后的字符对比是不是一样的
  public boolean backspaceCompare(String s, String t) {
    int sp = s.length() - 1;
    int tp = t.length() - 1;
    int sNum = 0, tNum = 0;
    while (sp >= 0 || tp >= 0) {
      // 处理#
      while(sp >= 0) {
        if (s.charAt(sp) == '#'){
          sNum++;
        } else {
          if (sNum > 0){
            sNum--;
          } else {
            // 处理完多个#
            break;
          }
        }
        sp--;
      }
      while(tp >= 0) {
        if (t.charAt(tp) == '#'){
          tNum++;
        } else {
          if (tNum > 0){
            tNum--;
          } else {
            // 处理完多个#
            break;
          }
        }
        tp--;
      }

      // 处理完全部#
      if (sp >= 0 && tp >= 0){
        if (s.charAt(sp) != t.charAt(tp)) return false;  
      } else {
        if (sp >= 0 || tp >= 0) return false;  // 还存在一种情况，sp、tp都<0，这种就是true
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
    test.backspaceCompare("bxj##tw", "bxj###tw");
  }

}
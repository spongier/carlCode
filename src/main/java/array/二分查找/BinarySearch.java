package array.二分查找;
class BinarySearch {
    // 二分查找大前提：有序！！数组

    // lc 704
    public int search(int[] nums, int target) {
        // 区间的定义-> 循环进入条件、比较完赋值
        // [left, right) -> <、right = mid
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)    return mid;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // lc 35 (关键点：二分搜索结束时，left = right，任意返回都可以)
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }


    // lc 34 升序
    // 常规二分查找l=r时，表示没找到（但l、r可能是0，n，怎么避免：找到一次就更新标记位
    public int[] searchRange(int[] nums, int target) {
        int left = searchLeft(nums, target);
        if (left == -1) {
            return new int[] {-1, -1};
        }
        int right = searchRight(nums, target);
        return new int[]{left, right};
    }

    private int searchLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        // l出来具有明确含义，l指向第一个>=target 的位置
        if (l < nums.length && nums[l] == target){
            return l;
        }
        return -1;
    }

    private int searchRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= target) {
                l = m + 1;
            } else {
                r = m - 1; 
            }
        }
        // r 指向最后一个 <= target 的位置
        if (r >= 0 && nums[r] == target){
            return r;
        }
        return -1;
    }

    // lc 69
    // 坑：需要考虑溢出、考虑除0
    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (m > x / m){
                r = m - 1;
            } else if (m  < x / m){
                l = m + 1;
            } else {
                return m;
            }
        }
        return r;
    }


    // lc 875 吃香蕉问题，吃的越快，耗时越小
    public int minEatingSpeed(int[] piles, int h) {
        // 题目强调了 h >= piles.length，也就是取数组最大值，就一定能在h小时吃完
        int max = -1;
        for(int x: piles)   max = Math.max(max, x);

        // 这样就定下k值的区间[1, maxH]
        int l = 1, r = max;
        while (l < r) {
            int k = l + (r - l) / 2;

            // 计算时间
            int cost = 0;
            for(int pile : piles)  cost += (pile % k == 0 ? pile / k : pile / k + 1);

            // 缩小范围，寻找左边界
            if (cost > h) {
                l = k + 1;
            } else if (cost <= h){
                r = k;
            }
        }
        return l;
    }

    // lc 33 旋转数组搜索
    // 调整边界
    public int searchRotate(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target)  return m;

            if (nums[m] >= nums[l]) {
                // mid 处于左段升序 (需要二次比较，如果target < mid值，并不代表target会出现在左侧，也可能是右段)
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                // mid 处于右段升序
                if (nums[m] < target && target <= nums[r]){
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args){
        BinarySearch test = new BinarySearch();
        test.mySqrt(12);
    }
}
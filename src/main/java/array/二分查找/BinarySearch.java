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
        int leftIdx = searchLeft(nums, target);
        int rightIdx = searchRight(nums, target);
        return new int[]{leftIdx, rightIdx};
    }

    private int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int leftRes = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                leftRes = mid;
                right = mid;
            }
        }
        return leftRes;
    }

    private int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int rightRes = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                rightRes = mid;
                left = mid + 1;
            }
        }
        return rightRes;
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

    public static void main(String[] args){
        BinarySearch test = new BinarySearch();
        test.mySqrt(12);
    }
}
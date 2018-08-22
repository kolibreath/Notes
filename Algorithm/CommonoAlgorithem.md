# 常见算法以及其坑

算法思想： 
- 从最简单的情况开始分析
## 快速排序
快速排序中在寻找轴的时候记得要nums[high] >= pivot 和 nums[low] <= pivot 不然会有重复计算
```
private void qsort(int nums[],int low,int high){
            if(low < high){
                int partition = partition(nums,low,high);
                qsort(nums,low,partition-1);
                qsort(nums,partition+1,high);
            }
        }

        private void qsort(int nums[]){
            qsort(nums,0,nums.length-1);
        }

        private int partition(int nums[],int low,int high){
            int pivot = nums[low];
            while(low < high){
                while(low < high && nums[high] >= pivot) high--;
                nums[low] = nums[high];
                while(low < high && nums[low] <= pivot) low++;
                nums[high] = nums[low];
            }
            nums[low] = pivot;
            return low;
        }
    }
```

# 非递归的二分查找
```
private boolean binarySearch(int number,int array[]){
            int low = 0, high = array.length - 1;
            int mid = (low + high) /2;

            while(low <= high){
                if(number > array[mid]){
                    low = mid + 1;
                }else if(number < array[mid]){
                    high = mid - 1;

                }else{
                    return true;
                }
                mid = (low + high)/2;
            }
            return false;
        }
```

while(low <= high)
如果low == high 的话就会找不到

# 递归版本的二分查找

# 全排列 permutations
排列的最简单的情况是只有两个数字 1 2 全排列的结果就是这个数列本身和 swap(num[1],num[2]) 
递归全排列的思想是将所有情况编程这样的最简单的情况 都变成最后只有这两个数字的情况;另外 如果一个数组中的两个数字进行变换之后的结果再进行变换一次就是原来的数组。

````
class Solution {
        public List<List<Integer>> permute(int[] nums) {
            if(nums == null || nums.length== 0)
                return new ArrayList<>();
            List<List<Integer>> res  = new ArrayList<>();
            perMutations(res,0,nums);
            return res;
        }

        public void perMutations(List<List<Integer>> res,int start,int nums[]){
            if(start >= nums.length){
                res.add(converter(nums));
            }
            for(int i = start;i<nums.length;i++){
                swap(nums,i,start);
                perMutations(res,start+1,nums);
                swap(nums,i,start);
            }
        }

        public void swap(int num[],int index1,int index2){
            int temp = num[index1];
            num[index1] = num[index2];
            num[index2] = temp;
        }

        public List<Integer> converter(int nums[]){
            List<Integer> list=  new ArrayList<>();
            for(int n : nums){
                list.add(n);
            }
            return list;
        }
    }
````

就是记得在交换完成之后在换回来就好了


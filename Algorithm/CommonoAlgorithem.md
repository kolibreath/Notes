# 常见算法以及其坑


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


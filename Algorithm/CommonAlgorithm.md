# 常见算法以及其坑

算法思想： 
- 从最简单的情况开始分析

# 排序算法

## 快速排序

快速排序kotlin实现：

```
fun <T : Comparable<T>> List<T>.quickSort(): List<T> = 
    if(size < 2) this
    else {
        val pivot = first()
        val (smaller, greater) = drop(1).partition { it <= pivot}
        smaller.quickSort() + pivot + greater.quickSort()
    }
```
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
# 查找算法

# KMP 算法
String : abcabcfafedfafewefae
Pattern : fafewefae 

[KMP 算法视频](https://www.youtube.com/watch?v=GTJr8OvyEVQ)

KMP 算法的基本思想是基于对字符串暴力匹配的一种改进，改进了两个方面：
- 在需要匹配的串中，已经比较过了内容不需要再进行比较
- 在模式串中，如果有相等的前缀和后缀要利用起来：

## 非递归的二分查找
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

## 二分查找小于target数的最近的数字
[Find first and last position](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/)
通过数学方法，可以证明的有两点
- 二分查找中不可能出现的情况是low> high,如果出现low > high的话就会退出循环，可能出现的情况是low == high 和 low high，通过控制这个可以找到最靠近target数字的下界
- 经过有限次的运算(向下取整) low 会最终会和high相等

这道题需要注意 low 会逐渐的趋近于high， 但是一个判断不成立的条件是 low == A.length 所以 需要high 取得 a.length
另外 high = mid 而不是 high = mid - 1，因为当走else路线的时候可能的情况是target == numbers[mid]

````
 public class Solution {
       public int[] searchRange(int[] A, int target) {
           int start = Solution.firstGreaterEqual(A, target);
           if (start == A.length || A[start] != target) {
               return new int[]{-1, -1};
           }
           return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
       }

       public  static int firstGreaterEqual(int numbers[], int target) {
           int low = 0, high = numbers.length;
           while (low < high) {
               int mid = low + ((high - low) >> 1);
               if (numbers[mid] < target) {
                   low = mid + 1;
               } else {
                   high = mid;
               }
           }
           return low;
       }
   }
````

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

# 求子集问题
#  Combination Sum 
这道题是一个求子集的问题，子集中的元素可以重复，这样的话就不应该作为一个排序问题来处理.

````
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
    list.add(new ArrayList<>(tempList));
    for(int i = start; i < nums.length; i++){
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    
}
````

如果是求子集的话有一个这样的固定的套路
[reference](https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

递归的思想是这样的，假设需求的list 为 3 0 7 9， 空集合也是子集，所以在开始的进入递归之前就会被加入集合中。在第一次递归的最深处（我的意思是在开始返回上一层之前），这个时候会将整个list中所有元素都会加入结果的数组集合。

然后通过跳过某些元素的方法 求出所有的集合。 

> 注意：

- 在开始调用递归函数之前可以进行sort sort的目的是在list.add之前减少list判断集合中的元素是否重复耗时，对于这道题，在递归的时候保证remain（剩余值）和重复计算element就ok
- 现在这个题的情况是没有duplicates 如果有呢？ 需要清除重复的数值避免在结果list中返回


# 大数运算

## 大整数乘法
````
 class Solution {
        public String multiply(String num1, String num2) {
            if(num1==null || num2 == null)
                return "";
            int p1 = num1.length(), p2 = num2.length();
            int result[] = new int[p1 + p2];
            for(int i = p1 - 1;i>=0;i--){
                for(int j = p2 - 1;j>=0;j--){
                    int multi = (num1.charAt(i) - '0')*(num2.charAt(j) - '0');
                    int curP = i + j + 1;
                    int curPB = i + j;
                    int sum = result[curP] + multi;

                    result[curP] = sum % 10;
                    result[curPB] = result[curPB] + sum/10;
                }
            }

            String anwser = "";
            boolean flag = true;
            for(int i : result){
                if(i == 0 && flag)
                    continue;
                else{
                    flag = false;
                    anwser += i;
                }
            }
            return anwser.length() == 0? "0":anwser;
        }
    }
````
根据正常的运算思路进行计算就ojbk，注意以下初始化数组的时候把0排除一下

# 大整数加法
也是需要依赖正常思路进行运算，这里有一个tip ，在for循环的时候注意写好循环条件，因为两个数数字的长短可能不同，短的数字先运算完可以+0 让长的数字进行继续运算

````
 class Solution {
        public String addStrings(String num1, String num2) {
            int p1 = num1.length(), p2 = num2.length();
            int max = p1 >= p2 ? p1 :p2;
            int result [] = new int[max + 1];
            int forward = 0;
            for(int i = num1.length() -1 ,j = num2.length() -1 ; i>=0 || j>=0|| forward ==1;i--,j-- ){
                int a = i<0 ? 0 : num1.charAt(i) - '0';
                int b = j<0 ? 0 : num2.charAt(j) - '0';

                result[max--] = (a + b + forward) %10;
                forward = (a + b + forward)/10;
            }

            String str = "";
            boolean flag = true;
            for(int i : result){
                if(i == 0 && flag){
                    continue;
                }else{
                    flag = false;
                    str += i;

                }
            }

            return str.length() == 0? "0" : str;
        }
    }
````



*****
# 链表：
链表删除某个节点：
使用递归解决
```

```
iterative:
```
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curr = head, prev = fakeHead;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }
        return fakeHead.next;
    }
}
```


翻转链表：
递归完成
```
class Solution {
        public ListNode reverseList(ListNode head) {
            
            if(head == null)
                return null;
            ListNode dummy = new ListNode(0);
            reverse(head,dummy,null);

            return dummy.next;
        }

        void reverse(ListNode node,ListNode dummy,ListNode cur){
            if(node.next == null) {
                dummy.next= node;
                return;
            }

            reverse(node.next,dummy,cur);
            ListNode p = dummy;
            while(p.next!=null){
                p = p.next;
            }
            cur = p;
            cur.next = new ListNode(node.val);
        }
    }

```
# Leetcode Best Practices
leetcode 必刷题

- 所有分类下面的easy类型
bfs dfs two-pointers tree backtrack greedy dp ， 其他部分可以选做一点

- 经典图论问题：
考察一个有向图的bfs问题：
- [课程安排](https://leetcode.com/problems/course-schedule/description/)
[详细解答](https://wx3.sinaimg.cn/mw690/d6225d36ly1fwzok3dl7xj21kw23v7wh.jpg)

这个题只需要判断整个是否是一个死锁，所以不需要通过节点的先后顺序构建一个链
```
/***
*  0 -> new ArrayList<>()
*  1 -> 0
*  2 -> 0 
*  3 -> 1 2 
****/
```

通过计算每个节点的入度，最后看看有没有入度为0 的节点（每次发现有一个入度变成 0 了就++）
- [课程安排ii](https://leetcode.com/problems/course-schedule-ii/description/)
一个有向图的遍历的方式 有几种遍历的方式


- 简单递归题：
非常的直白的递归题：
[targetSum](https://leetcode.com/problems/target-sum/description/)




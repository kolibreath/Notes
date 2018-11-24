# Coding tips 


- 格式化字符串 实现在字符串前面补充0
String.format("%03d",s)

- 使用Java list 判断
```
list.getFirst()！= null

可以使用

list.size() != 0
```

进行合理替换

- 不要使用可变长的list 去作为for循环的guard 如图
```
 int order[] = new int[stack.size()];
            for (int i = 0; i < order.length ; i++) {
                order[i] = stack.pop();
            }

```


# 一般oj刷题
- 在PAT上面刷题需要将 class 名称改成Main
- 所有需要import 的包一个都不能少
- 可能需要解决内存和时间问题
- BufferedInputStream read()
读取的是一个char,如果想转换为数字的话需要-48

使用完成之后记得关掉 使用close() 方法，不然的话会造成内存泄漏


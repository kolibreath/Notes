# Java 和 Kotlin中的数据结构

![图](https://images2015.cnblogs.com/blog/418793/201607/418793-20160711200210061-1990371097.gif)
## 常用的接口
- RandomAccess
[RandomAccess 接口的作用 以及 使用 for 和 iterator遍历的速度](https://juejin.im/post/5a26134af265da43085de060)
[下文代码来源：](https://www.cnblogs.com/djoel/p/6016839.html)
RandomAccess 是一个标记接口ArrayList 实现了这个接口 ，表示随机访问速度比较块
````
public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key){
    if(list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
        return Collections.indexedBinarySearch(list, key);
    else
        return Collections.iteratorBinarySearch(list, key);
}
````
- Queue 接口
## 类型擦除
[java 中的泛型](https://blog.csdn.net/briblue/article/details/76736356)
- Java 中的假泛型
泛型-- 类型参数话
```
Cache<String> cache = new Cache<>()
```

List<String> 和 List<Object> 在编译器的眼中都是list.class 他们的类型信息被擦除了
所以可以通过反射的方法找到运行时的这个方法并且加入。
- Kotlin中的泛型
Kotlin JVM基于JVM所以自然也会进行类型擦除
[Kotlin 中的形变 如何实现泛型](https://juejin.im/entry/5962e4796fb9a06bb7522a74)

在java中可以使用这样的方法 
````
addAll(Collections<? extends E>)
````
这样的话如果原来的集合是list = new ArrayList<Number>()
同样也可以添加 ArrayList<Integer>到这个集合中

这个就是协变，在Kotlin中着一点使用out 关键字实现 
逆变通过in关键字实现。

注意最好不要使用泛型参数去进行协变
```
 class MyCollection<out T>{ 
     fun add(t: T){ // ERROR! 
        ... 
     } 
 } 

  var myList: MyCollection<Number> = MyCollection<Int>() 
 myList.add(3.0) 
````

逆变的话最火爆不要将泛型参数作为返回值 因为向上转型是不安全的

## inline 
Kotlin 可以通过inline 和 reified关键字将内敛函数的泛型参数作为真实类型使用
````
 inline fun <reified T> Gson.fromJson(json: String): T{ 
     return fromJson(json, T::class.java) 
 } 
````

## 常用的集合类
### fail-fast 机制
[fail-fast 机制](https://blog.csdn.net/chenssy/article/details/38151189)

# ArrayList
[reference list中的api 解释](https://blog.csdn.net/fighterandknight/article/details/61240861)
[ArrayList 中的常见问题](https://www.cnblogs.com/woshimrf/p/java-arryalist-remove.html)
[ArrayList 详细解释](https://blog.csdn.net/zxt0601/article/details/77281231)

### Substract
 - ArrayList 不是线程安全的，如果在多线程的情形下，建议使用Vector或者CopyOnWriteArrayList
 - 内部通过一个Object 数组存放数据，在无参构造的情况下，数组长度赋值为1。取出元素的时候需要不被强制转型成泛型的类型
 - 在Remove 方发调用的时候 将最后一个元素设置为空 
 - ArrayList 其实效率是很低的因为 如果没有提前指定容量的话扩容的时候是非常浪费效率的
 - 其实在ArrayList内部 使用的是object数组储存泛型， 如果使用了错误的类型报错的话 是在编译期抛出的异常

# ArrayList 是怎样扩容的？
现在add的时候是否需要扩容 如果需要扩容的话 比较原来数组的长度加一是否能够容纳，默认的情况下会在原长度的基础上增加一半，如果扩容一半还是不够的话就会用原来目标的size作为容量

 - Arrays.asList().add() unsurpprtedException： 因为返回的AbstractList 中没有实现add 或者 remove 方法
 - foreach ConcurrentModificationException,因为foreach 内部是
  常见坑：
 - sublist ()方法中如果改变sublist 中 的元素 原list中的元素也会随之改变
 - 如果使用了 iterator() 获取了 iterator 但是之后再改变ArrayList中的内容的话会造成异常，因为这个时候cursor的值和数组的长度不再相等
 - remove()没有改变ArrayList的长度

 在remove元素的时候可以remove空元素（怒ll）
 
 # remove 是使用复制來覆盖原来数组中被删除的元素的位置 
 ````
    Iterator<String> iterator = strings.iterator();
    while (iterator.hasNext()){
        String next = iterator.next();
        iterator.remove();
    }

 ````

 ## Remove 删除list元素可能遇到的问题：
 [可能遇到的问题](https://blog.csdn.net/Sun_flower77/article/details/78008491)
 ArrayList#remove() 是两个方法：一个是remove(Object o) 另外一个是remove(int index) 第一个是移除这个对象，然后将数组的位置移动，覆盖之前被移除的那个元素的位置，第二个方法是移除下标对应的元素，再移动 但是两种方法都可能造成list.size()返回的数值不更新造成NPE

 在remove对象的时候，java通过equals比较两个对象的引用，自定义对象的时候最好重写这个方法

 自动拆箱和装箱是不会搞错的，因为这个发生在编译器 所以两种方法不会错误地调用    

 - 最好不要使用foreach 去remove 
 java 着那个的foreach是iterator的语法糖，其中的next方法，会抛出异常
正确删除的姿势

- ArrayQueue:
[ArrayQueue](http://blog.jrwang.me/2016/java-collections-deque-arraydeque/)
## Substract 
- ArrayQueue 的容量是有要求的 必须是2^n
-在向Queue中添加元素的时候 如果发生了越界操作会自动所以queue[tail] 到 队列的第一个元素，这个是通过容量大小必须是2^n实现的
````
public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        //tail 中保存的是即将加入末尾的元素的索引
        elements[tail] = e;
        //tail 向后移动一位
        //把数组当作环形的，越界后到0索引
        if ( (tail = (tail + 1) & (elements.length - 1)) == head)
            //tail 和 head相遇，空间用尽，需要扩容
            doubleCapacity();
    }

````

在头部添加元素
````public void addFirst(E e) {
    if (e == null) //不支持值为null的元素
        throw new NullPointerException();
    elements[head = (head - 1) & (elements.length - 1)] = e;
    if (head == tail)
        doubleCapacity();
}
````
如果head -1 为 -1 会 在数组的末尾添加新的元素
- 如果head 或者 tail 相同的时候 一定会扩容 为原来的两倍
- PriorityQueue 
[详细的优先队列](https://www.cnblogs.com/CarpenterLee/p/5488070.html)
保证每一次取出的元素都是队列中最小的
## Substract
````
private void siftUp(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;//parentNo = (nodeNo-1)/2
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)//调用比较器的比较方法
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}
````
优先队列内部是通过维护一个小顶堆， 小顶堆放在一个数组中，数组元素满足
leftno = parent*2 +1 
rightno = parent*2 + 2;
parent = left或者right /2   
每次添加之后就会sift


# LinkedList
[LinkedList 源码分析 写的不错！](https://blog.csdn.net/zxt0601/article/details/77341098)

## Substract 
- LinkedList 中list取出代码的时候 会根据index和size的大小简单的进行一个二分折半以提升查询效率
- LinkedList#add(int inde,Node node) 是将node 作为后继节点在这个节点前面插入一个新的节点
- LinkedList#remove(Object o) 会移除和第一个o相等的引用(equals))，如果这个里面有null元素的话会移除第一个null元素(通过引用比较==))
```
 public boolean remove(Object o) {
        if (o == null) {//如果要删除的是null节点(从remove和add 里 可以看出，允许元素为null)
            //遍历每个节点 对比
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
````
其中很多遍历使用的是for循环而不是使用了iterator
# Java 和 Kotlin中的数据结构

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
- ArrayList
[reference list中的api 解释](https://blog.csdn.net/fighterandknight/article/details/61240861)
[ArrayList 中的常见问题](https://www.cnblogs.com/woshimrf/p/java-arryalist-remove.html)
[ArrayList 详细解释](https://blog.csdn.net/zxt0601/article/details/77281231)

### Substract
 - ArrayList 不是线程安全的，如果在多线程的情形下，建议使用Vector或者CopyOnWriteArrayList
 - 内部通过一个Object 数组存放数据，在无参构造的情况下，数组长度赋值为1
 - 在Remove 方发调用的时候 将最后一个元素设置为空 
 - ArrayList 其实效率是很低的因为 如果没有提前指定容量的话扩容的时候是非常浪费效率的
 - 其实在ArrayList内部 使用的是object数组储存泛型， 如果使用了错误的类型报错的话 是在编译期抛出的异常

# ArrayList 是怎样扩容的？

 - Arrays.asList().add() unsurpprtedException： 因为返回的AbstractList 中没有实现add 或者 remove 方法
 - foreach ConcurrentModificationException,因为foreach 内部是
  常见坑：
 - sublist ()方法中如果改变sublist 中 的元素 原list中的元素也会随之改变
 - 如果使用了 iterator() 获取了 iterator 但是之后再改变ArrayList中的内容的话会造成异常，因为这个时候cursor的值和数组的长度不再相等
 - remove()没有改变ArrayList的长度
 ````
    Iterator<String> iterator = strings.iterator();
    while (iterator.hasNext()){
        String next = iterator.next();
        iterator.remove();
    }

 ````
正确删除的姿势

- PriorityQueue 

## 常用的接口
- RandomAccess



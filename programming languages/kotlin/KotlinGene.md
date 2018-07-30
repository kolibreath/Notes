[from this post](https://blog.csdn.net/wanliguodu/article/details/80260224)
[another post](https://www.jianshu.com/p/c5ef8b30d768)
# Java 中的泛型
Java中的泛型是不会形变的 
````
List<String> strs = new ArrayList()<>;
List<Object> objs = strs;
object.add(1);
String newString = str.get(0);
````

也就说虽然obj的引用指向了str但是内部的泛型参数没有

````
void copyAll(Collection<Object> to, Collection<String> from) {
  to.addAll(from); // ！！！对于这种简单声明的 addAll 将不能编译：
                   //       Collection<String> 不是 Collection<Object> 的子类型
}
````

由于不是子类型所以不能编译
# Kotlin 中的 in out

take in , give out
这个和Java中的两个边界符号很像而且更加容易理解， 

如果你的类是将泛型作为内部方法的返回，那么可以用 out：

extends
因此，对于 out 泛型，我们能够将使用子类泛型的对象赋值给使用父类泛型的对象。

````
interface Production<out T> {
    fun produce(): T
}
````

如果你的类是将泛型对象作为函数的参数，那么可以用 in：
因此，对于 in 泛型，我们可以将使用父类泛型的对象赋值给使用子类泛型的对象。

````
interface Consumer<in T> {
    fun consume(item: T)
}
````
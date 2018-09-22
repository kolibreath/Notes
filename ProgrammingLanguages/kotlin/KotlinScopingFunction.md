# Kotlin Lambda


[Post](https://blog.csdn.net/u013064109/article/details/78786646)
[Kotlin 中的空安全](https://mp.weixin.qq.com/s?__biz=MzIxOTU1MDg5Ng%3D%3D&mid=2247484284&idx=1&sn=cf7f887ac7d3903d10c0a756400d9331&chksm=97d8c6a3a0af4fb509649b42ac9f08bc0e1adad364cc0aa2d65a824ca68276cc03ec6a3ad2c9)


## let

````
fun <T, R> T.let(f: (T) -> R): R = f(this)
````

let函数做的就是将调用他的对象转换为一个lambda的参数
[from this post](https://mp.weixin.qq.com/s?__biz=MzIxOTU1MDg5Ng%3D%3D&mid=2247484284&idx=1&sn=cf7f887ac7d3903d10c0a756400d9331&chksm=97d8c6a3a0af4fb509649b42ac9f08bc0e1adad364cc0aa2d65a824ca68276cc03ec6a3ad2c9)

````
fun testLet(): Int {
    // fun <T, R> T.let(f: (T) -> R): R { f(this)}
    "testLet".let {
        println(it)
        println(it)
        println(it)
        return 1
    }
}
````

在闭包中 String 类型作为闭包的参数传入

let 函数可以对可能为空的对象进行统一的处理,let函数的右侧是执行闭包f(this)，也就是就是将调用的类型作为参数 执行闭包的结果，如果f为空可以这样写
````
string2?.let{
    //其中的类容不执行
}
````

而避免了
````
string?.xxxx
string?.xxxxxx
````
这样的繁琐的情况




## run
````
@kotlin.internal.InlineOnly
public inline fun <T, R> T.run(block: T.() -> R): R = block()
````

run 函数的方法参数中有一个是像上面类似的闭包，任何类型都可以调用这个函数 因为是泛型T

````
fun <T> check(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

````
类似于这样的方法也就是说 第二个参数是一个函数/闭包 然后返回值为T，在函数声明的时候定义返回值为T 和函数体中的一致,run 函数的定义就是去执行这个block，所以返回的数值要么为Unit要么为lambda中的结果

# Kotlin 中的委托

by 关键字的使用 实现代理模式：
````
interface Base {  
    fun show()
}

// 定义类实现 Base 接口, 并实现 show 方法
open class BaseImpl : Base {  
    override fun show() {
        print("BaseImpl::show()")
    }
}

// 定义代理类实现 Base 接口, 构造函数参数是一个 Base 对象
// by 后跟 Base 对象, 不需要再实现 show() 
class BaseProxy(base: Base) : Base by base

// main 方法 
fun main(args: Array<String>) {  
    val base = BaseImpl()
    BaseProxy(base).show()
}
````

直接将使用的对象交给 by 后面的参数 ，在java 中就是调用base的实现类去使用show方法，Kt 使用语法糖包装了一下   
## 属性委托：
````
   private var zipCode: Long by DelegatesExt.preference(this, ZIP_CODE, DEFAULT_ZIP)
````
使用by 关键字完成 只要DelegateExt.preference()返回的类中

实现这两个方法，就像官网中写的那样：
````
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}
````

所以上面的zipcode getter 和 setter 通过Delegate 的 getValue 和 setValue 的方法获取

## 内置函数完成的委托
``lazy()``
需要的时候才执行
````
val lazyValue :String by lazy{
    println("fuck")
    "hey yo"
}
````
第一次调用这个属性的get()的时候会执行传递给lazy的lambda表达式并且记录结果，后续调用get()返回初始化之后的结果

[reference](https://www.kotlintc.com/articles/2631)

函数体内只会执行一次 然后类似static final 的 Singleton

``observable``

Delegates.observable(T,{
    R
})

````
   var name = Delegates.observable("fuck"){
            property, oldValue, newValue ->  {
            println("value change!")
            }
        }
````

在数值发生变化的时候进行监听
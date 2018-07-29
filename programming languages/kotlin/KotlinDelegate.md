# Kotlin 中的委托

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
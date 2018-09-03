# Kotlin VS Java 
目录：
- 空安全
- 类型推断
- 延迟初始化
- 不通过类的形式存在
- Kotlin 的流程控制
- 实现接口/匿名内部类
- Class 类型
- Kotlin Anko 线程调度
- Kotlin 中的数据类型
- Kotlin 多继承
- Kotlin 函数
- 自带的"设计模式"
- Kotlin 协程

# 空安全
smart cast
````
val a:Int? = null
if(a!=null)
  a.toString()
````

安全访问符? 只有这个变量不是null 的时候才可以去执行
````
a?.toString()
````

强制执行符!! 当确定一个变量一定不是null的时候可以强制执行这个代码
# 类型推断
````
TextView view = (TextView )findViewById(R.id.xxx);

val string = "fafa"

val textView2 = findViewById<TextView>(R.id.tv_title)
````
类型推断的弊端

````
val textView:TextView = mToolbar.find(R.id.tv_title)
````
通过显示声明告诉编译器

你甚至可以让编译器推断函数的返回值的类型:
```
fun count() = 1
```

# 延迟初始化
reference:
[lazy and lateinit](https://www.kotlintc.com/articles/2631)

````
private TextView mTextView ；
````

````
lateinit private mTextView : TextView 
````
lazy 是一个 库函数 返回一个实现了Lazy接口的类，实例：

````
fun <T: View> Activity.findView(@IdRes res:Int): Lazy<T> {

  @Suppress("UNCHECKED_CAST")
  return lazy {
    this.findViewById<T>(res)
  }
}

private val mToolbar: Toolbar by findView(R.id.toolbar)

````

by 是一个kotlin重载的关键字，然后会返回一个Lazy<T> 个value，这个Value被 Lazy保证只会被初始化一次

## 委托属性 

````
private val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE,
            SettingsActivity.DEFAULT_ZIP)
````

这段代码表示zipCode这个属性被委托给了 DelegateExt.preference 这个函数所返回的类，这个类如果要实现委托的话 不需要实现任何接口，只需要实现 
````
operator fun getValue()

operator fun setValue() 
```

就可以

# Kotlin 类
## 类型修饰符
- private 限制成员的显示仅仅只在这个文件中或者是一个类中
- protected
- internal 在一个module中可见 这个是JB的定义

> 一个 module 应该是一个单独的功能性的单位,它
应该是可以被单独编译、运行、测试、debug的


- public 默认的修饰符

# Kotlin 流程控制
when replace switch 
并且 Kotlin 有一种类似于goto的机制：label label必须声明在使用之前：比如这样，如果不满足条件则跳出这个循环

````
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // 非局部直接返回到 foo() 的调用者
        print(it)
    }
    println("this point is unreachable")
}
````

````
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    print(" done with explicit label")
}
````

遍历一个map
```
for((key,value) in map){
  
}
```

# 实现接口

````
public interface AnalyzeCallback{

        public void onAnalyzeSuccess(Bitmap mBitmap, String result);

        public void onAnalyzeFailed();
    }
````

java 代码源方法

Kotlin 实现类：

````
 object: AnalyzeCallback{
          override fun onAnalyzeSuccess(
            mBitmap: Bitmap?,
            result: String?
          ) {
            TODO(
                "not implemented"
            ) //To change body of created functions use File | Settings | File Templates.
          }

          override fun onAnalyzeFailed() {
            TODO(
                "not implemented"
            ) //To change body of created functions use File | Settings | File Templates.
          }
        }
````

[Kotlin object关键字](https://zhuanlan.zhihu.com/p/27183266)
object关键字 声明单例
单例是延迟初始化的，保存在JVM堆中，之后返回的是同一个对象

在Java 中:

Thread thread = new Thread(){
  @Override 
  public void run(){
    System.out.print("fuck you")
  }
}

val thread = object: Thread(){

}
效果相同 都是声明一个一次性的变量

# Java class Vs Kotlin class
Java 中的Class
````
A.class 获得这个class 类型的变量
````
在 Kotlin中：
````
A::javaClass a.javaClass A::Class.java 
````
A::Class.java 是KClass的扩展属性，返回一个Class类型，根据文档中的描述，之所以这样写，是为了提升和Java 的 introspection (互操作性)
a.javaClass 将会返回一个runtime的A类的Class，所以这个应该使用A的实例a去调用这个,反而A::javaClass 在使用中可能会出现错误
[stackoverflow-What's the difference between Foo::class.java and Foo::javaClass?
](https://stackoverflow.com/questions/46627421/whats-the-difference-between-fooclass-java-and-foojavaclass)


# Kotlin Anko 线程调度
````
async() {
Request(url).run()
uiThread { longToast("Request performed") }
}
````

使用uiThread() 可以调用回到主线程
# Kotlin 的数据类型
- Array
- ..Array
- List 的子类型
Kotlin 为数据类型封装了一些函数：
forEachIndexed 

min max寻找一个list中的最值 可有重复的值
minBy maxBy 通过一个函数寻找最值

````
public inline fun <T, R : Comparable<R>> Iterable<T>.minBy(selector: (T) -> R): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var minElem = iterator.next()
    var minValue = selector(minElem)
    while (iterator.hasNext()) {
        val e = iterator.next()
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}
````

自定义两个一个函数原型

- POJO no more

````
data class Forecast(val date: Date, val temperature: Float, val
details: String)
````

自动初始化所有的setter and getter ,并且Kotlin中的函数是有默认值的，可以通过参数的名称进行注释避免引用错误。

data class 中还有copy函数 ，copy函数进行的是一个浅拷贝，比如
````
data class User(val name:String ="",val age:Int = 0)
val user = User(name="fuck",age = 10)
fun copy(name:String = this.name, age:Int=  this.age)
````


# 多继承
## open 关键字：

# 函数
- first class!!!
- 函数都是有返回值的
- 函数参数默认值
- 闭包作为函数参数
````
view.setOnClickListener { toast("Click") }
````
在lamda中如果函数只接受一个参数可以省略左边的参数表示，使用默认的it表示
````

模仿Kotlin库函数 forEach的一个例子
```
fun main(args:Array<String>){
    val list = arrayListOf(1,2,3)
    list.forEach2 { print(it) }
}

fun <T> ArrayList<T>.forEach2(action: (T)->Unit){
    for(int in this)
        action(int)
}
```

action() 表示调用这个函数
````
- 操作符重载

比如invoke函数
````

public interface OnItemClickListener{
    operator fun invoke(user:User)
}

可以直接通过 listener()调用
````

- 扩展函数
````
val View.ctx : Context 
  get() = context
````

context 是View 中的getContext()方法的映射，get() 是Kotlin 属性方法的getter

# Kotlin 自带的设计模式
使用Kotlin的库函数很容易实现之前需要使用模板代码实现的设计模式:
- Builder模式
```
inline fun <T> with(t: T, body: T.() -> Unit) { t.body() }
```

这个闭包作为传入的一个扩展函数,直接调用闭包中的内容

- 观察者模式
其实这个不是严格意义上的观察着模式Delegates.obserble中的检测希望观察的属性的变化

````
class ViewModel(val db:Database){
  var property by Delegates.observable(""){

  }
}
````
````
   public inline fun <T> observable(initialValue: T, crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit):
        ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
            override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(property, oldValue, newValue)
        }
````

还有Vetoable

函数原型 

property表示后面作为set(value) 中的这个value

# Kotlin 携程库
[refer](https://kymjs.com/code/2017/11/24/01/)
# Kotln 有趣的地方:



## NotImplementedError
````
/**
 * Always throws [NotImplementedError] stating that operation is not implemented.
 *
 * @param reason a string explaining why the implementation is missing.
 */
@kotlin.internal.InlineOnly
public inline fun TODO(reason: String): Nothing = throw NotImplementedError("An operation is not implemented: $reason")

````

## 反引号
在Kotlin 中的关键字和Java 或者是内置的关键字冲突的话可以使用``进行转义：
比如
```
override fun getItemPosition(`object`: Any): Int {
    return super.getItemPosition(`object`)
  }
```
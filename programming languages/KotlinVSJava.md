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

# open 关键字：

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

# Kotlin 的数据类型
- Array
- ..Array
- List 的子类型

# 多继承

# 函数扩展(Kotlin Extension)

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
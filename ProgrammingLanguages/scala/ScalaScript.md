# 学习 Scala

- Scala 函数的最后一行就是返回值 所以可以真的不需要写Return 了
- 更加明显的DSL 特性：
```
(1 to 5 ) foreach { index =>
    val future = Future {
      doWork(index)
    }

    future onSuccess{
      case answer: Int => println(s"Success! returned : $answer")
    }
    future onFailure{
      case th:Throwable => println(s"Success! returned : $th")
    }
}

````

这样子的写法比Kts要好看很多 onSuccess 是属于Future类的一个函数 
啊很开心 这个是我写的第一个Scala脚本哈哈

- Scala 中的偏函数 这里其实是可以使用
``case``来作为一个匿名函数

- Scala中的尾递归和优化
[什么是尾调用](http://www.ruanyifeng.com/blog/2015/04/tail-call.html)
尾调用的意思是在函数的最后的返回值/在函数的尾部又调用了一个新的函数， 然后函数要保存现场的话 就会将这些内容放入栈中。
不管是否是尾递归都会造成很大的内存使用，如果是尾递归的话由于函数还调用了自身所以更需要做一个尾递归优化	
## 非JVM 语言的尾递归优化
在上面的老师的博客中说了 JavaScript这些对尾递归做了优化，在编译器层面，将尾递归变成了在同样的大小的区域内继续给这个函数刷新值就可以

[JVM 上面的语言有没有做尾递归的优化呢？](https://www.zhihu.com/question/22627957)
因为JVM 没有相关的指令阿！ 没有尾递归的指令 但是Scala提供了 @tailRec来 检测是否正确实现了尾递归 ，但是包含计算的递归仍然不是尾递归

[为什么JVM 没有实现尾递归呢？from Java GC aspect](https://blog.csdn.net/yan_chou/article/details/59488871)


- Scala 中的匿名函数 和 函数 字面量


- Scala中的偏函数

- Scala中的嵌套函数

很容易理解 函数中套函数，作用域相关的东西可以类比局部变量和全局变量：

- Scala FunctionX and Turple

- import 作用域隐藏

- import 嵌套

- Scala中为什么没有CheckException：
scala 需要尽可能的避免 [副作用](https://en.wikipedia.org/wiki/Side_effect_(computer_science)) 避免函数修改外部的变量等

scala 取代CheckedExeption的[原因](https://stackoverflow.com/questions/41056731/why-scala-does-not-have-concept-of-checked-and-unchecked-exception)：
- 很多时候并不会对error进行处理 只是抛出而已
- 破坏函数式中的流程控制的感觉
- 避免引用不透明导致的问题


注释：referential transparent
表示如果替换这个术语对应的词，不改变原本的意思：
[原网址](https://stackoverflow.com/questions/210835/what-is-referential-transparency)

> The Scottish Parliament meets in the capital of Scotland.

> The Scottish Parliament meets in Edinburgh.


```
 def failingFn(i: Int): Int = {
       val y: Int = throw new Exception("fail!")
       try {
         val x = 42 + 5
         x + y
       }
       catch { case e: Exception => 43 }
 }
```
但是这样子不是referential transparent 因为 在try block 内部修改了y的值

- Scala 模式匹配 
Scala中的模式匹配有点类似于Java 中的instanceof 和 switch的结合 
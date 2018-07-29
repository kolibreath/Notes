# Kotlin协程


## 递归学习开始 
首先说一下Block是否会造成内存泄漏：

- 内部类 ：天生不会
- 匿名内部类
  + 是没有持有外部类的引用:this@Outter不存在是 不会持有外部类的对象
  + 反之亦然

[from this post](https://www.jianshu.com/p/0ee88812d73e)


## 协程创造器

````
launch(CommonPool){
    //ComomPool is an object defined in the library
    delay(..time..)
}
````

## 在Android中的异步调用
````
async(UI){

}
````

这个函数的原型是这样：
````
public fun <T> async(
    context: CoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    val newContext = newCoroutineContext(context)
    val coroutine = if (start.isLazy)
        LazyDeferredCoroutine(newContext, block) else
        DeferredCoroutine<T>(newContext, active = true)
    coroutine.initParentJob(context[Job])
    start(block, coroutine, coroutine)
    return coroutine
}
````
默认情况下接受一个context的参数，这个参数在协程库中是一个常量，表示Android main thread， 闭包是一个suspend 开启协程 

await & async
````
  async(UI) {
            val result = bg {
                RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute() }
            bindForecast(result.await())
        }

````
异步 等到完成是继续

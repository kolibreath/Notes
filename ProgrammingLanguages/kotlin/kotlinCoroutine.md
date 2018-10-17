# Kotlin Coroutines

Kotlin协程

协程这个idea 源自 可以suspend的函数 挂起函数可以在别的函数或者是协程中被使用

[Kotlin 协程 和 RxJava 比较](https://medium.com/@andrea.bresolin/playing-with-kotlin-in-android-coroutines-and-how-to-get-rid-of-the-callback-hell-a96e817c108b)

````
fun launchAsync(block: suspend CoroutineScope.() -> Unit): Job {
    return launch(UI) { block() }
}
````

感觉协程在Kotlin中被库包裹地很紧 现在还只能看到怎么使用
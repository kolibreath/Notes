# 7.30
Kotlin-android-extension 直接使用id操作会不会有坑！
- Android Studio Kotlin Extension 异常 按照网上说的方法添加kotlin-android-extension 之后会出现找不到R文件的错误 只能在app module中删除kotlin依赖重新配置

- Android Studio 配置： error runnig app, please select Android SDK 需要在settting 中重新edit 一下SDK的路径 是由于AS异常关闭导致。 需要下载的就下载把...
但是我反复尝试了之后发现各种方法都不行 以后再也不在终端里面异常关闭了

- 高德
- 定位sdk 和 地图 sdk 是两个模块...定位sdk 需要运行时permission debug版本的运行时权限真的是失了智i
- 除了官网提供的几个版本之外3dmap location 等 的依赖下载不下来 只能手动导入jar包.....真的是..
- 然后手动导入jar包也会出现问题 ...是IDE的bug
# 7.29
Kotlin 协程自带了很多异步处理的操作，RxJava 怎么搞？
Anko Layout 库 语法糖太多了 不想用....

Kotlin 的函数编程简直太美妙了！

````
var test:((pos:Int)->Unit)? = null

test?.invoke()
````
# 7.28
Kotlin 语法糖太多了

真香！ Kotlin 不用写findViewById 减少了 回调的逻辑真香！
这个需要引用一下Kotlin-android-extension 
```{}``` this in side
 # 7.27 Android 开发 
> 高德地图
- 发现Kotlin使用扩展方法可以被Java方法所接受，我对一个类进行扩展，但后这个kt文件会以xxxxKt的方式被引用，然后定义的参数会+1 多的那个一个参数是receiver。

- 高德debug,keystore 
目录在隐藏文件.android 中 
密码是默认的密码android

- 高德 使用Gradle操作之后不需要添加 so

- 注意要合理管理mapview的生命周期

> Anko 
- 递归学习开始：
  - Kotlin协程 是什么:{
      suspend lamda:
      暂时了解了以下携程可以替代线程做一些很炫酷的操作，相当于线程而言 更廉价而且简单

      - 找到一个很好的例子:
      [例子]](https://github.com/CysionLiu/anko-sample){
          - [mastering kotlin functions](https://medium.com/@elye.project/mastering-kotlin-standard-functions-run-with-let-also-and-apply-9cd334b0ef84)
      }
      Anko 有很多很好的东西 比如attemp 函数 一些转换工具等等
  }
  - Kotlin 数据结构 MutableList：


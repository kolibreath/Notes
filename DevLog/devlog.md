
# 8.4

## 加快速度的方案
- 使用定点重新登录
- 出现错误然后再重新登录 感觉很不好 避免错误的时候重试

- 在我的另外一台电脑上配置jshell 
jshell 的使用

- Rxjava flatmapIterable
查询学生成绩的时候遇到了这样的场景： 需要通过来两个查询参数 xnm xqm 来获取一个学年的某个学习的成绩，但是现在可以选择多个学年 多个学期 需要组合学年学期成为一个list
````
listOf(yearParam,termParam)
````
然后遍历这个list 形成一个List<Observable<List<Scores>>> 这样的一个非常复杂的数据结构，之后在遍历这个list，这样的话，异步线程没有办法确定全部完成的时候加载整个listview，可以使用flatMapIterable操作符

- 之前写的请求重试还是不够优雅
之前使用的是onErrorResumexxxx 就是在出现问题的时候会使用定义在闭包的observale替换当前的observable重新发情求
# 8.2
- Android Stuido配置 这次发现不能打包 并且有
````
java.lang.NullPointerException
````
 解决方法 1.首先看到了匣子的依赖module 有异常 然后invaliate cache
         2. 是Gradle 和 Java 10 不兼容 只能使用Java 8
         3. 修改gradle.properties 提升速度 分配了 2G的内存
         4.在打包完成一次之后出现 set up jdk 错误 升级Android Studio 之后问题解除 3.1.2问题太多了....

- Android Devlog 
# 7.30
Kotlin-android-extension 直接使用id操作会不会有坑！
- Android Studio Kotlin Extension 异常 按照网上说的方法添加kotlin-android-extension 之后会出现找不到R文件的错误 只能在app module中删除kotlin依赖重新配置

- Android Studio 配置： error runnig app, please select Android SDK 需要在settting 中重新edit 一下SDK的路径 是由于AS异常关闭导致。 需要下载的就下载把...
但是我反复尝试了之后发现各种方法都不行 以后再也不在终端里面异常关闭了
这个是AS的一个bug： 解决方法看[SOF](https://stackoverflow.com/questions/34353220/android-studio-please-select-android-sdk#50000408)

- 高德
- 定位sdk 和 地图 sdk 是两个模块...定位sdk 需要运行时permission debug版本的运行时权限真的是失了智，如果不使用运行时权限可能会不准确
- 除了官网提供的几个版本之外3dmap location 等 的依赖下载不下来 只能手动导入jar包.....真的是..
- 然后手动导入jar包也会出现问题 ...是IDE的bug

正式开发：
- 关于MylocationStyle选取的问题：
//选取follow 移动地图时会被回拉到中心点
//如果使用no_center的话 中心点 可能不在视野范围内

- 正式包 记得加以下防混淆
- 限制地图的使用范围将无法旋转


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


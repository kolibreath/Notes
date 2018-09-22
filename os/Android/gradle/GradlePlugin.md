## 自己动手写一个gradle插件

> 目标 希望完成一个可以监听某个task完成的gradle插件，然后把完成打包的apk部署到手机上或者是传送到服务器上面

## groovy review
- groovy 中的闭包
在闭包中可以通过this 拿到一个包含着这个闭包的class
````
class EnclosedInInnerClass {
   class Inner {
       Closure cl = { this }                               
   }
   void run() {
       def inner = new Inner()
       assert inner.cl() == inner                          
   }
}
````
owner可以拿到包含着他的对象：
````
class EnclosedInInnerClass {
  void run() {
     def nestedClosures = {
         def cl = { owner }                               
         cl()
     }
     assert nestedClosures() == nestedClosures            
 }
}
````
grooy 还有的语法特性是command chains可是省略调用的时候的.

gradle的本质是确定task的依赖关系 并且执行这些tasks

doLast()  和 leftshift << 的功能一样，都是将某种任务加入到task的执行list中去

## Java 使用系统命令
java runtime使用ls命令 的时候需要用inputsteam去collect 输出流

使用scp 命令的时候会要求输入密码则呢吗解决？
这个在java 中使用了一个第三方库解决，但是使用很麻烦 之后顺被使用python解决
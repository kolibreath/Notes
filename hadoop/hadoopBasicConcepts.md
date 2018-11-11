# 基本概念：

# map reduce的理解
map进行任务内容的变化 数据的准备阶段
reduce 任务分配然后再合并（规约）
JobTracker TaskTracker
JobTracker 在MapReduce 中有单点故障的情况 ，只有一个JobTracker，压力大不容易扩展 只能支持MapReduce 作业

- MapReduce计算框架
MapReduce 实时流式计算

Yarn 不同框架爱可以共享同一个hdfs集群上面的数据，享受整体的资源调度

1) ResourceManager：RM
管理作业调度 kill start作业
监控NodeManager 
2) ApplicationMaster
通过对应的标准实现各种ApplicationMaster 可以运行不同的框架
每个应用程序对应一个ApplictionMaster应用程序管理
和NodeManager的通信 
3) NodeManager
多个nodeManager 自己本身节点资源管理和使用，定时汇报使用情况
4) Client
客户端 提交作业 kill 作业 等等....

5) Container
封装CPU Memeory的资源的容器 
任务运行环境的抽象

hadoop jar 提交作业到yarn上面运行


### 其实还有一个conbiner函数
combiner函数可以作为mapper的集合，将mapper的输出起来输出
但是有些情况下combiner是不适宜使用的：
```
combiner(max(0,10,20),max(20,20)) 
```
不等于原本的值

****
# 正式的代码相关内容
hadoop使用了内置类进行替代Java中的内嵌类，在正式使用的时候需要将所有东西打包成一个Jar? （这个Jar包括什么内容？）

不需要指定Jar的名称，setJarByClass()指定
- Mapper函数
![](https://wx4.sinaimg.cn/mw690/d6225d36ly1fwdhtgjmm8j23k02o0npj.jpg)
- Reducer函数
![](https://wx3.sinaimg.cn/mw690/d6225d36ly1fwdhte7thfj23k02o0x6x.jpg)
- Combiner函数

整个寻找最高气温的例子：
![](https://wx3.sinaimg.cn/mw690/d6225d36ly1fwdhte7thfj23k02o0x6x.jpg)
上面寻找最高气温的例子中combiner函数可以使用

只需要增加一条setCombinerClass()就可以设置

## map reduce 的执行流程：
- 在运行前作业写入目录应该是不存在的


# 为什么要分成chunck？
并行线程有可能有些任务提前结束，这样的话完成任务地最长时间取决于整个任务中最长者的最长运行时间。
如果分成数据段的话，完成一段数据段的处理任务容易分配下一段处理任务处理

# HDFS 中的概念
- 数据块
很大 一般为128mb 为了减少寻址开销

- 管理节点-工作节点
一个namenode 多种datanode 

## namenode 
namenode在内存中好存每个文件和每个数据块的引用关系
``联邦HDFS  ``通过减价namenode实现扩展，每个namenode管理文件系统命名的一部分

集群中的datanode需要注册到每个namenode，并且存储者来自多个数据块池中的数据块


- 对namenode的容错非常重要
如果没有namenode 不能根据namenode存储的元数据进行datanode的重建（不知道如何通过这些block重建完整的文件系统）
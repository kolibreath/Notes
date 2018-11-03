# 使用的资源：
[introduction to Hadoop](https://classroom.udacity.com/courses/ud617/lessons/308873795/concepts/3092715680923)
[hadoop shell reference](https://hadoop.apache.org/docs/r1.0.4/cn/hdfs_shell.html#copyFromLocal)
~~Hadoop 基础教程~~ 
感觉书有点老了，坑有点多

后来使用了hadoop权威指南


# 使用配置：
使用前需要格式化hadoop 需要用到的HDFS 文件系统：
```
hadoop namenode -format
```

启动hadoop
```
start-dfs.sh
```
****

# 使用hadoop 初次体验
[配置参考](https://wangchangchung.github.io/2017/09/28/Ubuntu-16-04%E4%B8%8A%E5%AE%89%E8%A3%85Hadoop%E5%B9%B6%E6%88%90%E5%8A%9F%E8%BF%90%E8%A1%8C/)


# 如何理解mapReduce

# Hadoop 一些概念

- HDFS 
nameNode and DataNode nameNode 是集群协调者
HDFS 只是一种文件系统？

MapReduce 中  JobTracker 是 TaskTracker 管理者 一样
有三种基本的模式：
![模式](https://wx4.sinaimg.cn/mw690/d6225d36ly1fwaytrs25wj20u01hck64.jpg)
![](https://wx1.sinaimg.cn/mw690/d6225d36ly1fwayts33zpj20u01hcnbz.jpg)
# 概念补充
- Java中的套接字 
[套接字](https://zh.wikipedia.org/wiki/%E7%B6%B2%E8%B7%AF%E6%8F%92%E5%BA%A7)


***
遇到的问题：
- jdk 版本太高 本机上装配的是jdk 10
```
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.apache.hadoop.security.authentication.util.KerberosUtil (file:/usr/local/hadoop-2.6.5/share/hadoop/common/lib/hadoop-auth-2.6.5.jar) to method sun.security.krb5.Config.getInstance()
WARNING: Please consider reporting this to the maintainers of org.apache.hadoop.security.authentication.util.KerberosUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release

```
- start-mapred.sh not found
[answer](https://askubuntu.com/questions/636400/start-mapred-sh-file-not-present-in-hadoop-2-7-0)

mapreduce 直接通过yarn 打开了 
```
4385 NameNode
9474 Jps
6730 SecondaryNameNode
9322 NodeManager
4572 DataNode
8974 ResourceManager

```
直接会打开多个进程

- 书中
```
hadoop -mkdir 

hadoop fs -mkdir
```


- 最好指定copy的路径from and to
```
hadoop fs -copyFromLocal /home/kolibreath/test.txt /user/hadoop
```


- 今天遇到了启动hadoop之后没有启动datanode进程的问题，这个问题后来修改在xml文件中添加属性解决
进行一次mapreduce默认的输出是在out 文件夹下面，使用的名称是：out/part-r-00000 会覆盖之前的输出
使用hadoop进行一个很大量的文章的字数统计是非常快速的

- 
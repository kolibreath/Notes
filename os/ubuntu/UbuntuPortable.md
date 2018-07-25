# Linux系统（推荐）

推荐使用Linux 系统进行开发。现在最流行的linux发行版是Ubuntu系统，而且现在已经推出了很美观而且适配性一流的18版

这里给出制作[Ubuntu启动盘和安装Ubuntu的步骤](https://jingyan.baidu.com/article/3c48dd348bc005e10be358eb.html)
你的电脑的厂家和型号查一下就可以进入对应型号的BIOS系统

设置了从u盘启动之后，重启电脑，出现的界面可能和这个图不太一样但是仍然需要``try install Ubuntu`` 或者是`try ubuntu without any changes`，然后按照这个步骤安装你的Ubuntu/Windows双系统并且设置你的分区大小,上面的这个教程只给出了20G作为Ubuntu的安装空间，实际上你可以根据自己的电脑的空间大小分配一下空间大小，home分区和主分区可以尽可能的大一些

如果是在Windows上使用虚拟机安装的也行，教程如此：

> 我是不会告诉你这篇文章的后面还有一个*翻*墙*的教程的

[Windows虚拟机安装Ubuntu](https://darren2017.github.io/2018/07/19/%E6%9C%A8%E7%8A%80%E6%98%9F%E8%AE%A1%E5%88%92-Linux%E8%99%9A%E6%8B%9F%E6%9C%BA%E5%AE%89%E8%A3%85/)

- Ubuntu 安装jdk

[JDK下载网址](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
需要同意(Accept Licence Agreenment)之后才可以下载,使用浏览器下载的默认路径会下载到``下载``目录下可以使用文件浏览器查看，或者是Download目录（英文安装，其他语言安装不知道）
不知道为什么在我最近的一次安装Ubuntu的时候在官网下载jdk很慢，如果很慢的话大家请耐心一点~
按照[这篇文章](https://www.linuxidc.com/Linux/2015-01/112030.htm)配置JDK就行

其实也可以使用apt 安装open-jdk，但是安装的是一个jdk的子集，有很多包没有，请斟酌安装

- 安装Android Studio
Linux上下载Android Studio：
可以去[官方网址](https://developer.android.google.cn/studio/)下载,下载完成之后解压这个包 然后进入其中``bin``目录，然后运行``./studio.sh``就可以启动Android Studio啦
可以参考这个[文章](https://baijiahao.baidu.com/s?id=1593699721619447275&wfr=spider&for=pc)

# Windows系统（不推荐）

之前Windows上各种操作是非常让人蛋疼的，但是如果使用诸如git bash这样的模拟Linux终端的程序还是可以得到类似于Linux系统的丝滑体验，但是更多好玩的东西就使用不了了。

客观的说Windows上开发Android程序的人也有，但是为了境界~~和逼格~~推荐使用Linux，如果你一心一意的想使用Windows先感受一下Android开发，没问题，请看：

- Windows下安装配置JDK
参考[这篇文章](https://jingyan.baidu.com/article/db55b609fa946e4ba20a2f56.html)

- Windows下面下载安装Android Studio
直接[官网](https://developer.android.google.cn/studio/)下载安装就行

是不是看起来Windows安装比Linux安装简单很多，其实不然，上面很多文字是教会你如何安装一个系统，建议尽早学会Linux系统，不然编程生涯会很凉凉.

# Android Studio 踩到的坑:

- 更新问题 :
在确认了翻墙(代理)配置打开没有问题的情况下,重新sync的时候,GET maven 的gradle异常 ,点击url 之后出现404 ,添加mavenCentral() mavenLocal() 之后没法解决 

尝试方法: 
- 降级Android Studio版本,通过降级到3.0版本,这个版本 降级到这个版本不能解决问题,而且不能够详细地看到为什么出现着这样的bug,在降级之前的版本中是可以看到的,不是版本的问题..    

- gradle 不支持 socks5代理,所以需要通过 [polipo](https://blog.csdn.net/hejunqing14/article/details/52670341) 转变成 http代理 这样就可以成功 connect到youtube等网站

大概配置好了之后就是这个样子：
[配置结果](http://ogbvujd8z.bkt.clouddn.com/2018.png)

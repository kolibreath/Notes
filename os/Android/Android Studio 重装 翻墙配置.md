# Android Studio 踩到的坑:

- 更新问题 :
在确认了翻墙(代理)配置打开没有问题的情况下,重新sync的时候,GET maven 的gradle异常 ,点击url 之后出现404 ,添加mavenCentral() mavenLocal() 之后没法解决 

尝试方法: 
- 降级Android Studio版本,通过降级到3.0版本,这个版本 降级到这个版本不能解决问题,而且不能够详细地看到为什么出现着这样的bug,在降级之前的版本中是可以看到的,不是版本的问题..    

- AS gradle直接配置sock5有点问题,所以需要通过 [polipo](https://blog.csdn.net/hejunqing14/article/details/52670341) 转变成 http代理 这样就可以成功 connect到youtube等网站

大概配置好了之后就是这个样子：
[配置结果](http://ogbvujd8z.bkt.clouddn.com/2018.png)

[终端翻墙配置](https://blog.csdn.net/u010658816/article/details/79344970)
 
在终端配置翻墙之后git 可能会出现问题 可以通过设置 git config 解决：
````
git config --global http.https://github.com.proxy socks5://127.0.0.1:1080
````
参考[这个issue](https://gist.github.com/laispace/666dd7b27e9116faece6)

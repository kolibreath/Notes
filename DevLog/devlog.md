
# 8.10
- 修改MultiStatusView 类 RetroStatusView 
功能 向外暴露 Erroriew EmptyView 接口 并且能修改内部text
获取动画效果的view 增加通知 比如现在进行的情况 

其实multiStatusView 定义了很多不同的提示 如果使用反射的话反而还很慢，如果善用这个会比较好，然后loading的提示需要修改胰腺癌loadingDialog

- [Bug] android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
08-10 18:47:24.650 15523-15879/net.muxi.huashiapp W/System.err:     at android.view.ViewRootImpl.checkThread(ViewRootImpl.java:7390)
        at android.view.ViewRootImpl.requestLayout(ViewRootImpl.java:1191)

- [BUG] flatmap 内部的代码运行在那条线程上？

- 感觉今天对调度器用的不是很好，现在来复盘一下问题
  + 需求需要在loading的时候给出提示，包括现在loading到什么阶段 然后是不是在重试请求这些，重试请求的时候直接丢进去了一个接口，然后修改主县城上面的loadingview。这样会有一个view的问题 ：只能在创建view的线程上做这样的修改，开始的时候是在一整个flatmap里面 做修改，这样的话没有办法切换线程，于是联合了两个flatmap 

````
   return new LoginPresenter()
                                            .login(UserAccountManager.getInstance()
                                                    .getInfoUser())
                                            //让这一段修改的线程运行在UI上
                                            .subscribeOn(AndroidSchedulers.mainThread())
                                            .flatMap(
                                                    (Func1<Boolean, Observable<Boolean>>) aBoolean -> {
                                                if(mListener!=null)
                                                    mListener.onRetry();

                                                return Observable.just(aBoolean);
                                            })
                                            .subscribeOn(Schedulers.io())
                                            .flatMap(aubBoolean ->
                                                    Observable.merge(listObservable, 5)
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribeOn(Schedulers.io())
                                                    .flatMap((Func1<List<Score>, Observable<Score>>)
                                                            scoreList ->
                                                            Observable.from(scoreList))
                                                    .toList());
````

第一个flatma是纯粹的用于主线程上修改view ，之后马上切换线程操作 ，可以解决那个view的问题

- 其二 还遇到了一个NetworkOnMainThread的问题 也是这段代码里面的 login 会有问题，之前的login大概这样子的
````
Observable.defer(something ->{
                        create((Observable.OnSubscribe<Boolean>) subscriber -> {
                        subscriber.onStart();;
                        boolean crawlerResult = false;
                        try {
                            crawlerResult  = CcnuCrawler2.performLogin(user.sid,user.password);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(crawlerResult);
                        subscriber.onCompleted()});
````
使用的是defer 而且没有指定线程，我现在直接使用了create + 线程指定 因为在返回这股Observable对象的时候 就会在这个线程创造好，如果使用defer的话后面的线程指定反而还有影响

- [Hint]我在使用merge operator的时候发现并不能保证一个observable的操作完成再进行下一个操作，为什么？
````
  Observable[] scoreArray = new Observable[mYearParams.size()*mTermParams.size()];
        for(int i=0;i<mYearParams.size();i++) {
            for (int j = 0; j < mTermParams.size(); j++) {
                Observable<List<Score>> o = CampusFactory.getRetrofitService()
                        .getScores(mYearParams.get(i), mTermParams.get(j))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

                scoreArray[i*mTermParams.size() + j] = o.onErrorResumeNext(throwable ->{
                    if(throwable instanceof HttpException){
                        int code = ((HttpException) throwable).code();
                        switch (code){
                            case 500:
                                return o;
                            case 403:
                                //todo implements
                                return Observable.empty(); }
                    }
                    return Observable.error(new Exception());
                });
            }
        }
````

代码是这样子的，很明显每一个Obsevable都有不同的调度器，所以有这种"异步"操作的感觉
````
08-10 11:38:44.492 26090-26194/net.muxi.huashiapp D/OkHttp: --> GET https://ccnubox.muxixyz.com/api/grade/?xnm=2017&xqm=
    --> END GET
08-10 11:38:44.494 26090-26189/net.muxi.huashiapp D/OkHttp: <-- 200 OK https://ccnubox.muxixyz.com/api/start/ (5060ms)
    Server: nginx/1.10.1
    Date: Fri, 10 Aug 2018 03:38:44 GMT
    Content-Type: application/json; charset=utf-8
    Content-Length: 121
08-10 11:38:44.495 26090-26189/net.muxi.huashiapp D/OkHttp: Connection: keep-alive
08-10 11:38:44.496 26090-26189/net.muxi.huashiapp D/OkHttp: {"img": "http://o6leenpcq.bkt.clouddn.com/xueer2018start.png", "url": "https://xueer.muxixyz.com/", "update": 1533799579}
    <-- END HTTP (121-byte body)
08-10 11:38:44.498 26090-26187/net.muxi.huashiapp D/OkHttp: --> GET https://ccnubox.muxixyz.com/api/grade/?xnm=2016&xqm=
    --> END GET
08-10 11:38:44.563 26090-26187/net.muxi.huashiapp D/OkHttp: <-- 500 Internal Server Error https://ccnubox.muxixyz.com/api/grade/?xnm=2016&xqm= (64ms)
    Server: nginx/1.10.1
    Date: Fri, 10 Aug 2018 03:38:44 GMT
    Content-Type: text/html; charset=utf-8
````
很明显 一个操作没有完成 就开始了下一次操作,修改之后完美解决这个问题

- 很奇怪的是 虽然都是对多次查询成绩api发起请求为什么获取学分的不容易出错？ 或者说我都没有见过想查成绩这样这么频繁的500code
经过观察 学校服务器会在一次500 之后清除掉你的cookie，这样的话你需要重新登录才可以继续流程

# 8.9
- 需要解决 查询失败的问题 
- 压力测试网络请求 有没有异常
- 默认情况下拦截有点问题

- !!!!发现之前一直出现的500 internal server error问题， 这个问题的出现是在刚刚进入app的时候

查询成绩的url https://ccnubox.muxixyz.com/api/grade/?xnm=2017&xqm=
            https://ccnubox.muxixyz.com/api/grade/?xnm=2017&xqm=
有时候虽然cookie没有过期但是后台返回的数据是500 需要做一个验证码检测！
t.muxi.huashiapp D/OkHttp: <-- 500 Internal Server Error https://ccnubox.muxixyz.com/api/msg/ 
msg这个api也是500

- 没有显示现在的进度情况 适当修改以下
- credit显示每一个group的credit总和有问题
- ~~score 返回的checkedlist为空 -- coursetype在没有初始化的情况是空~~

- 这个版本很有问题 问题就是 ： 第一次请求会有一个500的报错 然后刷新请求 会有 一个NetWorkOnAndroidMainThreadException
{
     -会滚之前的版本 看看是不是重试逻辑有问题
}
- 课程分类为null的问题需要解决

## 使用RetryWhen() 或者 RepeatWhen() 过滤掉爬虫失败的case
具体可以看这篇文章：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0206/3953.html
只有在IOException的时候 请求重订阅
````
source.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
          @Override public Observable<?> call(Observable<? extends Throwable> errors) {
 
            return errors.flatMap(new Func1<Throwable, Observable<?>>() {
              @Override public Observable<?> call(Throwable error) {
 
                // For IOExceptions, we  retry
                if (error instanceof IOException) {
                  return Observable.just(null);
                }
 
                // For anything else, don't retry
                return Observable.error(error);
              }
            });
          }  
        })
````

retryWhen的官方解释

retryWhen和retry类似，区别是，retryWhen将onError中的Throwable传递给一个函数，这个函数产生另一个Observable，retryWhen观察它的结果再决定是不是要重新订阅原始的Observable。如果这个Observable发射了一项数据，它就重新订阅，如果这个Observable发射的是onError通知，它就将这个通知传递给观察者然后终止。

所以如果是403的话就生成一个登录的Observable 然后逻辑一套走下来 如果是500的话再重新试试

- 我猜这个傻逼 NetWorkonAndroidMainthread是造成时间过长没有办法解决的主要原因


- Rxjava 遇到了networkonAndroidMainThreadException 觉得是因为 defer和create没有注意好导致的,create会立即执行defer会延迟执行Observable的生成
# 8.8
- leetcode 今天在写leetcode的时候又遇到了 java 引用的问题
java一直都是数值传递 ，如果哦传进去一个引用，就会在stack中复制这个值的内存地址，然后在函数中进行的操作就是对这个数值的操作，如果传进去一个基本数值类型就是对这个基本数值进行操作，然后在函数结束之后释放。

- textView 的background 的 drawable 需要 根据字体的长度动态设置
- ~~checkbox 需要全部设置为选择 并且需要初始化所有的我checkbox iew~~
- ~~checkbox 对缓考这样的非常规字段进行规避 ~~
- 检查算平均学分绩的逻辑有没有问题和异常
- todo 修改multiStatusView
- ~~增加全选功能~~




但是如果是一个递归函数，传进去一个引用由于递归函数会保存状态所以这样可能最后会得不到一个好的结果
# 8.6
-  Backpressure trigger, skipping transaction & refresh!
第一次遇到backPressure 相关的异常 哈哈
- onErrorNext只要发现有一个错误就会重试，容错能力不高
- ~~creditFragment增加一个缓存~~
- ~~在没有设置查询参数的时候 给默认参数并且给出提示~~
- ~~需要查询完成绩之后的弹出动画 ！~~
- ~~对选择的课程进行过滤~~
- ~~完成学分绩计算功能~~

# 8.6
- 发现一个很有趣的地方 点击checkbox 是先变成选中状态再去执行onclick逻辑还是正好相反？
哈哈 答案是 先会变成选中状态    
https://ccnubox.muxixyz.com/api/grade/?xnm=2016&xqm=0 


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


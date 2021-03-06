# 如何使用Kotlin 跳过某些不想要的数值？
```
  val queries = query.split(" ")

    //统计查询中的tf
    var step  = 0
    queries.sorted()
            .forEachIndexed { index, s ->
                if(step  !=0 ) {
                    step --
                    return@forEachIndexed
                }
                var counter = 0
                while(queries[step + index] == s){
                    counter ++
                    step++
                }
                val randomDf = Math.random()*10* 1000L
                val word = Word(name = s,tf = counter,df = randomDf ,idf =inversedDocumentFrequency(randomDf) )
                words.add(word)
    }
```

对一个已经排序了的string array 进行 计算重复的word的次数
# 本周使用了 idea + gradle 构建了一个简易的工程项目

- Kotlin break from loop
```
 val results = arrayListOf<Pair<Int,Int>>()
    (1..3).forEach outer@ { x ->
        (1..3).forEach inner@ { y ->
            if (x == 2 && y == 2) return@outer // continue @outer
            if (x == 1 && y == 1) return@inner // continue @inner
            results.add(Pair(x,y))
        }
    }
```
跳出 继续一个循环

- break 的话需要使用break 关键字


leetcode = 4 题

# 准备做一些Java 的简易的爬虫
目标 首先从一些简单的网站开始，比如github 
然后将这个爬虫变成一个分布式爬虫
最后将这个爬虫编程Scala分布式爬虫

请求微博的微博抽奖平台 一定是有一些cookie的
https://www.weibo.com/u/5581785513?is_all=1

# 11.3
解决了访问失败的问题
hadoop connection refused on port 8020
这个问题使用了disable ipv6 解决了！
    

# 11.1
hadoop 是真得尼玛的折腾
我重新配置了一次 
然后现在没有之前的每次使用命令的warning了
临时数据保存在usr目录下面 所以要对所有的脚本加上权限

# 10.25
题目分享：
求两个链表的子集
https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
这里两个链表是尾部相同的 ，是有局限的 
如何对一个链表进行时间复杂度为O(logn)的排序 空间复杂度可以为常量
可以使用常量
https://leetcode.com/problems/sort-list/

quiz：
使用C语言或者Java
手写一个归并排序 
手写一个快速排序
# 10.17
今天在做hadoop
遇到的问题：
- 先使用zsh alias 先将hadoop下载的tar包中的程序命名为 hadoop
- 可以使用hadoop fs -mkdir 创建一个文件夹 /user
hdfs://localhost:9000/user/
可以通过copyToLocal 或者 copyToLocal 和 HDFS 交互
- 现在使用的版本合并了mapreduce 
- 测试用的jar包在 /share/common 
- 使用的时候最好写完整路径 而且tab不能不全hdfs中的路径 有点蛋疼


# 10.14
家校通现在还存在的问题：
- 发一个feed 之后会500 : 原因：后端弄混了数据库item id 和用户id， 进度：解决中
- 登录一个已经注册过了的用户 915 915 会500 错误

# 10.13
埋点
推广率


# 10.11

写一个Kotlin脚本：
[Kotlin 脚本](https://github.com/JetBrains/kotlin/tree/master/compiler/testData/codegen/script)
文件协议
content://
file://
两种协议
contentProvider 和 contentResolver
(可以看这一篇文章)[https://www.jianshu.com/p/ea8bc4aaf057]

通过contentProvider 地工具类 contentResolver去操作具体的uri对应地数据和资源
 两种协议的产生

 问题：
 onActivityResult 的 data : data.data 返回的是一个content://协议的对象
 但是使用的库中的函数需要一个path 文件的绝对路径

 临时保存一个文件 然后获取这个文件的绝对路径

# 10.10

[Kotlin 协程](https://www.kymjs.com/code/2017/11/24/01/)
[AtomicInteger](https://www.cnblogs.com/sharkli/p/5623524.html)
失败
[Kotlin 中的并发原语](https://blog.csdn.net/sergeycao/article/details/53894787)
关于七牛云上传问题 
七牛云上传sdk 只能一次上传一张图片，很难写上传完成之后地回调：
- 方法一使用Rxbus通信 写一个counter 计数 全部上传完成然后发一个event
问题 使用counter的时候会出现并发错误 -> 使用AtomicInteger?
AtomicInteger 为什么会发生失败：
使用run方法可以调用{}闭包中方法   
# 10.7
Kotlin Backing field 
https://medium.com/@nomanr/backing-field-in-kotlin-explained-9f903f27946c

``
class User{
    var firstName : String  
        get() = field
        set(value) {field = value}
    
   var lastName : String  
        get() = field
        set(value) {field = value}
 
                                     
}

    var firstName:String 
        get() = firstName
        set(value)  { firstName = value} 
``

backing field 的作用是避免编译器递归调用，而采取的中间量措施
# 9.27
其实我发现Kotlin 好多语法是照抄Scala的
# 9.14
- 重构注册登录
 - 进度： 将所有的权限请求存放在navigation 模块
# 9.9
如果遇到这样的情况：
需要使用一个hashmap储存一个key-value 但是 又需要在onBindViewHolder中通过position来拿出存贮在set中的item怎么办..
使用Kotlin Pair来解决
# 9.5
选择图片问题解决
# 9.4
leetcode 86 287 -> 142->141
# 9.3

# 9.7
 七牛不允许多个同时上传 我的想法是把这些图片包装为Obsevable，然后在merge 我推测merge判断前面的完成的关键是看有没有发射onComplete（）信号
 这个还需要指定progress的handler 这里直接声明为null 在completeHandler中发射onnext和oncomplete信号
## 从新的角度看消息机制
https://www.jianshu.com/p/d00b010831f3
这篇文章比较有意思

## 一个闭包是序列化的吗？
Java 8 提供cast expression 去序列化
[reference Stack overflow](https://stackoverflow.com/questions/22807912/how-to-serialize-a-lambda)

相关的问题 ，如果想传递一个函数闭包作为intent里面参数这样写法可以吗？
[原问题](https://stackoverflow.com/questions/50948324/kotlin-passing-function-as-parameter-via-intent)

如果一个类A 中的一个闭包被B类实例化出来，这样的话A类是不能被GC的，会有内存泄漏的风险

这个是我原本的代码，因为需要从fragment 中getArgument() 取出被序列化的闭包，这个是不行的 所以最好继承Dialog 使用 Builder Pattern 完成
````
class CancelOrOkDialog:  CenterDialogFragment(){

  fun newInstance():DialogFragment = CancelOrOkDialog()
  
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val view = context!!.createView(R.layout.view_cancel_or_ok_dialog)

    val btnConfirm  = view.findViewById<Button>(R.id.btn_confirm)
    val btnCancel  = view.findViewById<Button>(R.id.btn_cancel)

    btnConfirm.setOnClickListener{

    }

    btnCancel.setOnClickListener {

    }
  }
}
````

# buildScript 和 allProjects 的区别
buildScript 是针对于Gradle的配置，然而allProjects 是针对每个module的 配置,一般情况下，可以在每个module中指定dependencies的闭包
[reference](https://stackoverflow.com/questions/30158971/whats-the-difference-between-buildscript-and-allprojects-in-build-gradle)

#View Pager

View pager 中的view 是通过key-Value的形式储存在ViewPager中的 通过initantItem创建一个Object作为key

# 9.2
重新写一下选择图片的逻辑：
使用RecyclerView 如果是第一张图片的话(position == 1) 使用一个默认的图片src。 所以原来的图片bitmap list 虽然是0，但是界面上会显示一个图片的占位，
如果加入一张图片，bitmap 的 list 会变成2，有一张新加入的图片和一张占位


或者是做一个类似于qq一样的多选或者是一起做

最终定稿的逻辑：

最复杂的情形，如果是选择或者拍摄了一张图片，后面再选择一批图片
选择一张图片之前 mpictures 的大小是1，保存一个占位的大小，横向的宽度是3，选择一个图片之后，整个adapter 被 notify以下，增加一张图片

占位.setListener{
    改变pictures 
}

****
备注： 看一下ArrayList remove元素的坑


# Android lint 
- custom view should override performclick
https://stackoverflow.com/questions/27462468/custom-view-overrides-ontouchevent-but-not-performclick

this is for the visually impaired persons


# 8.28
发现Dialog无法正常设置LoadingDialog上面的提示问题
分析和推测
- 可能是线程的问题？？
- 设置完成之后就无法改变内容？ 是DialogFragment的bug?
- 在BaseAppActivity中初始化dialog之后没有走这个逻辑？？
- 为什么 ScoreDisplayFragment 中可以轻易修改
- 是不是之前的写法有问题 对比以下官方文档？


原因是这样的： 因为Fragment 在 commit 之后会加入到FragmentManager 的一个stack里面，如果这样的话 这个fragment显示的时间是不可以知道的。  
现在有两种解决方法
- 可以使用DialogFragment 中封装的showNow() 内部实现是 commitNow() 进行操作

- 第二种方法是在newInstance() 的时候设置一个string，hiding()的时候remoVe,但是这样的时机也不是很清楚
# 8.26
使用BottomNavigationBar 和 ViewPager可以实现根据不同的栏目加载Fragment的效果
## 剩余的任务
- MainActivity 添加底部导航栏目
- 扫一扫功能 添加二维码保存的内容
- 个人中心
- 通知发送功能
- 忘记密码功能
- 注册界面
 通知发送界面的逻辑：
 - 点击不同的分类之后，通过变幻两个按钮以及字体的颜色提醒用户
 - 如果内容是空的话发表的颜色是灰色 
 - 若果内容是空的话 说点什么存在
 - 通过选择相片的按钮 下面弹出内容： 照相或者是从相册中选择
 - 上传图片

 图片选择这个地方是一个横向的RecyclerView 然后这个View 中,添加不同的item
，因为这一次项目不需要后期维护，所以使用最简单的方法，每一个item其实是一个包含了三个ImageView的layout ，通过数量判断是否要添加多个item
 如果有时间的话做一下移动相片的内容

# Bug 启动Kotlin项目异常
- 启动项目之后会保java.lang.RuntimeException: Unable to instantiate activity 查询之后的结果是 没有apply plugin kotlin-android 但是增加这个plugin 之后爆出找不到错误,https://blog.csdn.net/yukun314/article/details/78395291
# 8.22
- 关于LayoutInflater
如果attachToRoot is true, it will return the inflated view which is attached to the root view(the root view will be returned)

如果 attachToRoot is false 则返回这个inflated View object
# Remaining bugs 
 ## 缺少的图标 和 设计方面的建议 和 

 - 显示在 中间的提示框 平时成绩和期末成绩所占比例不是我们可以知道的
 - score 选择课程呢给种类 缺少一个 惊叹号
 - 我认为不需要杂选择课程类型的时候增加一个其他类型，这个是在我们分类不清楚的时候才分类到这个类型的
 - 如果需要给用户一个统一的体验的话 按钮可以不填加边框吗？
 -  CurFragment上面所有的全部已修学分不太对
 - 如果没有加载好fragment中的数据的话应该先显示loadingView
 - 需要设计给一个没有背景的groupView的图
 - 计算学分绩选择页面的 框的曲线程度不够
 - ~~计算学分绩顺序错乱 ~~
 - ~~计算学分绩结果有问题~~
 - ~~计算学分绩平时成绩和总成绩没法显示~~
 - ~~计算学分绩详情view 有NPE~~ 这个是DialogFragment初始化的问题 
 - ~~计算学分绩title 有...蜜汁缩        进~~ 按照stackOverflow的方法在Toolbar中增加列一个View然后textView.setText("")    
 - ~~如果学年没有到应该主动在选项框拒绝这样的搜索~~ 使用Observable.empty()+ 返回正确的年份减少错误重试，但是返回正确的学期过于麻烦，不这样做 
 - ~~平时学分和看考试最后的学分有问题返回值是""~~
 - ~~请求结果有问题 如果请求的是2016 请求的结果是2016-2017学年的成绩~~

 - ~~取消CurCreditFragment的条显示~~
 - 查询成绩偶尔会有异常出现 在用户重新登录之后
 - splashActivity 跳过会引发异常
 - 使用lint 工具lint 代码检查代码规范
 - 修改跳过变成一个loading

 如果请求没有的学期会有异常
 因为查询某个学期，会自动过滤没有的结果

 [Bug] android listview last item not showing
 解决方法：这个是因为在multiStatusView 在指定Recyclerview的时候应该指定match_parent 然后 指定MultiStatusView above底部的按钮就行 多半的情况因为这个和AppCollapsinglayout 连用，我觉得这个问题只是单纯遮挡住了而已   

 # 8.20
 把String 的 eazy写完 就去写数据结构的题目

# 8.18
- 想将loadingDialog的输出作为一个Observable包装以下，然后可以在UI线程很容易的调用
- expandableListview 是通过设置group indicator 为null 来取下父indicator的显示的
## 及一个笔记
关于这行代码：
````
if(id == R.id.cb_first_term)
            terms[0] = cbFirstTerm.isChecked();
````
这个是一个复选框 点击之后是先变化ui 还是先处理这个点击的逻辑？
如果原来是选中状态，点击之后马上变成为选中状态，然后给term[0]赋值false，所以是先变化ui，再处理逻辑

- 不健壮 如果学年没有开始也会计算这样就有问题，在后面查询的时候就会反复重试,要提前先过滤掉


 - [BUG] 我去 RadioButton的 setChecked()竟然有bug！ 可以直接改成checkbox的样式
 ```
 style="@android:style/Widget.DeviceDefault.Light.CompoundButton.RadioButton"

 ```
 - 使用ViewPager的时候最好使用LinearLayout 这样不会重叠
# 8.16

## Android Studio bug 
[Bug] kotlin文件中无法获取java package
- 剩余的问题 按照 设计那边修改图像
- 课程的alertDialog换成好的 dialog 然后dialog的content有问题
- 成绩结果页面 toolbar和 学习的位置有问题 title的显示有问题
- 测试学分绩计算结果 
- ~~成绩显示平时学分有问题~~

- ~~我将选择学年和选择课程种类的内容过分的暴露出来了 这样逻辑非常复杂~~

# 8.10
- 修改MultiStatusView 类 RetroStatusView 
功能 向外暴露 Erroriew EmptyView 接口 并且能修改内部text
获取动画效果的view 增加通知 比如现在进行的情况 

其实multiStatusView 定义了很多不同的提示 如果使用反射的话反而还很慢，如果善用这个会比较好，然后loading的提示需要修改胰腺癌loadingDialog

- [Bug] android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
08-10 18:47:24.650 15523-15879/net.muxi.huashiapp W/System.err:     at android.view.ViewRootImpl.checkThread(ViewRootImpl.java:7390)
        at android.view.ViewRootImpl.requestLayout(ViewRootImpl.java:1191)

- [BUG] flatmap 内部的代码运行在那条线程上？

observeOn()修改donwStream 的 工作流
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


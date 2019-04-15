# 计算机网络
## 第二章

- 编码
- 组帧
- 差错监测


## 编码方式
- NRZ （不归零）
<br>问题：如果有连续的1在一段时间的信号在链路上保持为高电平，会产生<br>
    - 基线漂移 
[image.png](https://upload-images.jianshu.io/upload_images/4714178-10de7567bf2df75d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    - 时钟恢复  <br>
    接收方和发送的时间信号需要精确同步
- NRZI （不归零反转）<br>
[image.png](https://upload-images.jianshu.io/upload_images/4714178-f08ae414aaa00d07.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 曼彻斯特编码
    - NRZ 编码的数据和始终的一伙纸，将0 作为由低到高的调变，1作为有高到低的调变
- 4B5B编码
![image.png](https://upload-images.jianshu.io/upload_images/4714178-51ab7316061e7288.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
## 组帧
- 面向字节的协议
    - 起止标记法
        - 将一个frame看成一个字符集，有各种不一样的长度和结构
        <br>
        有时候需要进行转义才能正确的翻译格式
        - 循环冗余校验 
    - 字节记数法
- 面向比特协议
- 基于时钟的组frame

## 差错检测
- CRC 加入冗余信息进行检测
- 二维奇偶校验
    ![](./2-dimension.png)
- CRC 详细过程

![image.png](https://upload-images.jianshu.io/upload_images/4714178-b6afb5db5fe1fbf4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/4714178-d8cfc2cec0169f07.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 可靠传输
有些差错·可能由于开销太大，以至于无法处理网络链路上放生的比特错和成组差错
- 使用确认和超时的组合完成上述工作
    - 停止等待
    ![image.png](https://upload-images.jianshu.io/upload_images/4714178-e7eef43842030ba9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
    
    缺点是容量很小,比如：
![image.png](https://upload-images.jianshu.io/upload_images/4714178-69e456218e834042.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
    - 滑动窗口算法
    分为发送方和接收方：``发送方``对每一个帧都会赋予个序号SeqNum，然后维护三个变量，LAR SWS LFS LFS-LAR <= SWS
    ``接收方``维护三个变量RWS LFR LAF，具体的业务逻辑：
![image.png](https://upload-images.jianshu.io/upload_images/4714178-8ebdfd82537f8aa5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 第三章
## 网桥和局域网交换机
### 网桥：
用于在共享介质和局域网中转发分株的一类交换机。
在输入接口上接受局域网的帧并且从其他所有输出端口将这些帧转发出去。
- 与交换机的区别
改进版的网桥，端口数目更多，速率更高，总体功能和性能都优于网桥
- 网桥需要维护转发表
    - 学习型网桥
    记录mac地址和端口的对应<br>
    转发到其他所有端口<br>
    特定的时间内表项中所对应的端口没有在通信，网桥丢弃两项
    - 单点帧 多点帧 广播帧
    1verus1 端口发，多versus多 泛洪到其他端口
    - 实现过程
    ![具体实现过程](https://upload-images.jianshu.io/upload_images/4714178-33b6898bf5cb5596.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
    丢弃数据：自己网段中的内容自己处理

# 可能出现的问题    
- 广播风暴
[广播风暴](https://upload-images.jianshu.io/upload_images/4714178-186a8e0adb96668e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 重复帧
![](https://upload-images.jianshu.io/upload_images/4714178-811850233aa307bc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 解决回路的方法：
生成树算法，<br>
分布式的生成树算法

![](https://upload-images.jianshu.io/upload_images/4714178-1ad8b2e6e1842710.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

<br>
基于配置消息的分布式生成树算法
算法内容<br>
![](https://upload-images.jianshu.io/upload_images/4714178-530371dbcd145afc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
## 配置消息主要包括的内容：
- 桥接网络的中的根网桥
网桥ID是惟一的，由网桥的优先级和其MAC地址组成
![](https://upload-images.jianshu.io/upload_images/4714178-410624c811dfb5bc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 指定网桥ID
- 执行端口ID
- 从指定网桥到根网桥的最小路径开销

假设23是根，4号到根网桥的端口

## 虚拟局域网
通过将局域网内的设备逻辑的，而不是物理的划分成一个个网段 <br>
- 每一个Vlan都有一个id唯一标示<br>
- Vlan隔离不同类型的流量

VLan可以通过三层路由引擎或者路由软件模块来实现

### 优点
- 共用物理的交换机设备
- 虚拟工作组
- 实现端口逻辑上的格里尼，增强网络的安全性
- 限制保温在整个网络中泛洪，限制网络风暴
- 减少网络移动的代价

![image.png](https://upload-images.jianshu.io/upload_images/4714178-e9db448c758fe225.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### VLAN的种类
> 基于端口的VLAN

![image.png](https://upload-images.jianshu.io/upload_images/4714178-c6d940d5e09db76f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
VLAN 名称和端口号对应<br>
- 优点 配置简单<br>
- 但是当链接的网络设备改变是需要对端口进行重新配置

> 基于mac地址
- 优点： 随意改变设备的位置
- 交换机要求能力高

> 基于IP地址
- 优点容易管理<br>
- 缺点：需要检查三层报头

> 基于协议
需要检查数据帧的协议类型域

# ip地址划分
- 同类型网络互连
    - 交换机
- 不同类型网络互连
    - 路由器
    - ip协议
- 服务模型
    - 编址方案：IP地址
    - 传送方式：数据报
        - 无连接
        - 尽力服务
        - 不可靠
## IP数据报的格式
![image.png](https://upload-images.jianshu.io/upload_images/4714178-8d46d599d3bf3c96.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 首部和数据两部分
    - 固定部分
        - 版本：IPV4 IPV6
        - 首部长度 
        字长是32bit<br>
        最小数值是5 最少5个字长的固定部分 最长15个字长的可变部分+固定部分

    - 可变部分

- 分段和重组
    - 标识：计数器 是属于哪个数据包
    - 标志
        - 最低位：
        MF More Fragment MF = 1 还有分片 MF = 0 最后一个分片
        - 中间位DF：Dont fragment
        DF = 0 允许分片 
    - 片偏移
- IP数据包
    - 过大 

## 编址方案 IP地址
- 全世界遵循一个IP地址分配规则

## IP地址编址方式
- 分类的IP地址   
网络号 ： 标志链接到的网络<br>
主机号：标志主机或者路由器
![](https://upload-images.jianshu.io/upload_images/4714178-8336f5ac30870a29.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
各种不同的网络划分 ABC<br>
网络划分的区别，为什么需要网络划分<br>：
A类网络网络id小，为主机数多。分类的元素以是因为组网需求划分和权衡。<br>
- 子网划分
- 构成超网

### IP地址阅读方法
- 点分十进制记法
![](https://upload-images.jianshu.io/upload_images/4714178-b93288ab9d73cac4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- IP地址的使用方位
![](https://upload-images.jianshu.io/upload_images/4714178-0932b6054acfea46.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 内部地址可以重复利用
![](https://upload-images.jianshu.io/upload_images/4714178-b581ed40cfac3302.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)   

- IP地址的重要特点
    - IP 地址管理机构只分配网络号
    - 路由器仅根据目的主机所连接的网络好转发分组
    大大减少路由表中的项目数
- 网桥中的也属于一个网段
- IP数据报转发
只按照主机所在的``网络地址``来制作路由表
- 默认路由
- 划分子网的基本思路
- 子网掩码
![屏幕快照 2019-04-04 11.06.26.png](https://upload-images.jianshu.io/upload_images/4714178-3e0d85ef38805320.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
子网掩码 AND 三级ip地址可以得到子网的网络地址

# 无分类编址
变长子网掩码 <br>
CIDR 消除了传统的A类B类 C类地址以及划分子网的概念<br>
CIDR 使用各种长度的”网络前缀“<br>
无分类的两级编址的记法``` {<网络前缀>,<主机号>}```<br>
CIDR把前缀相同的连续IP地址组成乐意CIDr地址块<br>
![屏幕快照 2019-04-09 10.21.55.png](https://upload-images.jianshu.io/upload_images/4714178-bbd28d0b368decfc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 路由聚合
- 最长前缀匹配
找出最长的前缀的路由
![屏幕快照 2019-04-09 10.25.23.png](https://upload-images.jianshu.io/upload_images/4714178-b06be02af53e9965.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 地址解析协议 ARP
![屏幕快照 2019-04-09 11.07.28.png](https://upload-images.jianshu.io/upload_images/4714178-40bcfebb6e9a1956.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

实际网络的链路上传送数据帧时 最终必须使用硬件地址<br>
## ARP 算法
1. 主机A广播一个查找B主机的物理地址的请求，A所在网络的所有与的主机都会接收这个请求
2. 如果不是目的主机
3. 主机B 应答，向A返回B的ip/mac pair 
![屏幕快照 2019-04-09 11.17.27.png](https://upload-images.jianshu.io/upload_images/4714178-6aadfbbc8d817382.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 需要注意的问题
- ARP 是解决而一个局域网上主机Ip地址和硬件地址的映射问题
- 如果主机和元主机不在一个局域网中 需要使用路由器转发
## ARP 欺骗
- 建立在局域网上的电脑全部信任的基础上
- 黑客可以听到局域网中的ARP request
### 截取网关数据
- 通知路由器一些列的错误的内容的mac地址，是真是的地址不能保存在路由器中，只能发送给错误的mac地址
- 伪造网关 建立假的网关欺骗PC 向假网关发送数据

## 动态主机配置协议
是DHCP 服务器和主机之间签订了一个协议
### 注意
- DHCP服务器对已分配的IP做记录 并且设定租用时间
- 主机必须定期申请延长租用时间


## VPN 虚拟专用网络
使用隧道技术
![屏幕快照 2019-04-09 11.37.51.png](https://upload-images.jianshu.io/upload_images/4714178-b0bc7dc77ce82ee3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 第三章
路由选择
- 转发和路由
- 转发表
网络号 端口号 mac地址
- 路由表
网络号 下一跳<br>
有``路由算法``建立的路由选择方案<br>
- 路由选择
实际的网络可以抽象的用一个图表示
![屏幕快照 2019-04-11 10.12.11.png](https://upload-images.jianshu.io/upload_images/4714178-f4c95dba3da8becb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 因特网量大路由选择协议
- IGP
内部网关协议 
- EGP
外部网关协议
### 自制系统 和 内部网关协议 外部网关协议
![屏幕快照 2019-04-11 10.20.24.png](https://upload-images.jianshu.io/upload_images/4714178-20d3ac98baf9e762.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 距离向量协议
![屏幕快照 2019-04-11 10.21.52.png](https://upload-images.jianshu.io/upload_images/4714178-a2c0c34141893950.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- RIP工作原理 路由表的更新
![](https://upload-images.jianshu.io/upload_images/4714178-d211aa6bf4a84c94.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 路由表的更新
什么时候更新:<br>
1. 触发更新
当路由表发生变化是路由器立即发送更新信息
2. 定时更新

## 路由回环
缓慢的收敛容易造成路由信息的不一致
![屏幕快照 2019-04-11 10.41.59.png](https://upload-images.jianshu.io/upload_images/4714178-74da2bef66362d15.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 解决策略
制定最大跳数为16防止路由回环
- 水平分割
不向邻居发送接收到的路由表项<br>
设置反向距离为最大

## 链路状态协议
每个节点通过彼此之间交换信息，获取一个网络拓扑印象
> 因为频繁交换信息 路游戏情况也能建成一个链路状态数据库
链路状态分组包含：
- 常见LSP的节点表示ID- 直接相邻的节列表和到达这些相邻节点的开销
- 一个序号 ： 标识哪一个是更新的序号
- 分组生存期

## OSPF
直接说那个IP数据包传送，数据报很短减少路由信息的通信量<br>

## 路由器的结构
具有多个输入输出端口的专用计算机<br>

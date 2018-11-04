# Windows 下使用VC 6 进行MFC开发

## 使用VisualStudio 进行MFC开发：
导入项目的dsw文件-> 找不到windows.h等需要打开 工具 ->添加新的工具，下载用于桌面开发的c++ x86 .... sdk，其中的两个选项出了高版本之外没有什么不同的。
我安装了较高的版本

再安装一个windows sdk 8.1 在同样的位置可以安装

- 好的！ 完成上述操作之后就没有代码中的异常了！
按照这个博客可以配置ok
[博客地址](https://blog.csdn.net/shenziheng1/article/details/52816450)

- 但是有这个错误：
```
严重性	代码	说明	项目	文件	行	禁止显示状态
错误	D8016	“/ZI”和“/Gy-”命令行选项不兼容	HelloMsg	C:\Users\73478\windows_mfc\Chap01\HelloMsg\cl	1	
```

## 使用VC 6 进行MFC开发：
- 下载SB霹雳宇宙无敌的VC 
[网盘链接](https://pan.baidu.com/s/1ThdObrfmtYbx2V5F8IxqSQ)

- 打开之后发现不能随便创建文件和打开文件：
需要下载一个额外的dll .因为当前系统和这恶老旧的IDE又不兼容的问题
[FileTool](https://pan.baidu.com/s/1YcCajbhRNvHk-p9d9pOj2A)

- 这个文件下载好了之后需要放入到这个路径中：
```
C:\Program Files (x86)\Microsoft Visual Studio\Common\MSDev98\AddIns
```
也就是放置到你的AddIns目录中 这个目录位于你的VC的安装目录，默认的安装目录是
```
C:\Program Files (x86)\Microsoft Visual Studio\
```

- 使用管理员身份打开cmd注册：
```
$ regsvr32 FileTool.dll
```
最好是先cd 到你的AddIns目录下面才可以。

- 打开SB VC
在工具 ，定制 添加附加宏文件

- 然后就会有个包含着A|O的小窗口
可以从那里操作

- 但是却发现你这样做了还是不能够添加代码
VC， 你妈死了！

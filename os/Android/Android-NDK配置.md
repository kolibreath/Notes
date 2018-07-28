Android ndk配置

``向已有项目中添加c/c++代码``

- 新建cpp文件
新的文件在``/src`` 下面新建一个文件夹,这个文件夹的名字是随意的，比如可以叫做``cpp``，命名的要求只是在CMakeList.txt中制定路径的时候有必要。然后新建一个文件名，文件名的命名也没有特别固定的规则。

- 创建CMake构建脚本

项目不使用``ndk-build``，所以也没有创建一个``Android.mk``文件。
在CMakeList.txt中主要使用了两种命令
````
cmake_minimum_required(VERSION 3.4.1)

add_library()

````

``library``主要分为两种``static``, ``shared``,可以参考stackOverflow上面的[这篇文章](https://stackoverflow.com/questions/3213789/difference-between-static-and-shared-libraries-in-androids-ndk)其中说明了区别


````
Static libraries are an archives of compiled object files. They get bundled in other libraries at build time. Unused portions of code from static libraries are stripped by the NDK to reduce total size.

Dynamic（shared） libraries are loaded at runtime from separate files. They can contain static libraries that they are dependent on or load more dynamic libraries.

So what you actually need for Android development is at least one shared library, that will be called from Java code, and linked with it's dependencies as static libraries preferably.
````

``include_directories()``指定头文件的路径（目前还没有使用过）

- CMake命名规范

````
static {
    System.loadLibrary(“native-lib”);
}
````

现在感觉这个demo就像是写了个一个接口，native的函数，然后其实现方法在，所定义的``native-lib``之中实现。

- 注意：
注：如果您在 CMake 构建脚本中重命名或移除某个库，您需要先清理项目，Gradle 随后才会应用更改或者从 APK 中移除旧版本的库。要清理项目，请从菜单栏中选择 Build > Clean Project。

有点类似于Clion中修改CMakeList.txt之后reload一下。
有关C语言的笔记:
````
#ifndef _Included_Sample
#define _Included_Sample
#endif 
````
为了避免同一个文件被include很多次。

````
#ifdef __cplusplus
extern "C" {
````
这句话的意思是说如果是cpp文件，下面的代码可以使用c语言的方式编译

> JNIEnv *：这是一个指向JNI运行环境的指针，后面我们会看到，我们通过这个指针访问JNI函数 

> jobject：这里指代java中的this对象 

常用的函数：
``GetFieldID(env,thisClass，"field name","type")``

thisClass 是一个Class对象，thisObject是一个指代的this对象
````
jclass thisClass = (*env)->GetObjectClass(env,thisObject)；
````
fieldname是指这个变量的名字，
type有一种专门的命名规则：
```
对于java类而言，描述符是这样的形式：“Lfully-qualified-name;”(注意最后有一个英文半角分号)，其中的包名点号换成斜杠(/)，比如java的Stirng类的描述符就是“Ljava/lang/String;”。对于基本类型而言，I代表int，B代表byte，S代表short，J代表long，F代表float，D代表double，C代表char，Z代表boolean。对于array而言，使用左中括号”[“来表示，比如“[Ljava/lang/Object;”表示Object的array，“[I”表示int型的array。
``` 

> 修改Java类中的方法：
GetFieldID 返回一个 jfieldID 对象，之后在通过
``GetIntField(env,thisObj,fildNumber)``的到一个jint对象（重载方法是有很多的）

对应的Set方法有四个参数：
thisObject是一个 jobject对象，jidNumber是上面的函数得到的一个jField对象
,number 是刚得到的那个jint对象
``SetIntField(env,thisObject,jidNumber,number)``
这个函数的意思就是根据``thisObject``上下文在jidNumber这个域中放置number这个量。
获取并且修改一个量的流程是
获取这个类的jclass类 -> 通过jclass类调用env的GetFieldID方法，得到jFieldID也就是这个类的变量的签名（名称）-> Get_primitive_FieldID/GetObject得到对象 -> 最后Set_primitive_field




# NULL
C语言中空指针的宏定义:
````
#define NULL ((void *)0)
````

C++中具有更加严格的类型检查问题
````
#ifndef NULL
    #ifdef __cplusplus
        #define NULL 0
    #else
        #define NULL ((void *)0)
    #endif
#endif
````

NULL 和 0是等价的,会引发重载问题 指针和数字等价,
C++ 11通过引入nullptr解决这个问题
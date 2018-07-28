# Kotlin Null Safety
[post1](https://mp.weixin.qq.com/s?__biz=MzIxOTU1MDg5Ng%3D%3D&mid=2247484284&idx=1&sn=cf7f887ac7d3903d10c0a756400d9331&chksm=97d8c6a3a0af4fb509649b42ac9f08bc0e1adad364cc0aa2d65a824ca68276cc03ec6a3ad2c9)

- T 泛型默认是可空的  如果要避免可空可以 使用一个非空的上界限定
<T : Any>

- Java 互调性
Java中的String 有两数值 一种是null 一种是String的实例

      @NotNull
java  String    = Kotlin 中String
# Kotlin Types

## Pair
类似于Map中的键值对
````
bindWeather(high to maxTemperature, low to minTemperature)
````

````
public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
````
使用了中缀表达式 to 映射类型

  it.second.text = "${it.first}"

  使用first second 取出对象

储存在Pair中的数值是Val类型,因为Pair在声明的时候就是一个val
````
public data class Pair<out A, out B>(
        public val first: A,
        public val second: B
                                    ) : Serializable {
````
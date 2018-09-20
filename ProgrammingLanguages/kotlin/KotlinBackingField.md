# 幕后字段 
[reference](https://blog.csdn.net/LXX_Believe/article/details/77759122)

````
class Person(val name:String){
    var nameHash:Int = 3{
        get(){
            field = 3;
            return 10
        }

        set(){
            switch(field){
                3 ->{
                    println("field = 3")
                }
            }
        }
    }
}
`````
如果是直接使用getter 和 setter的话 通过getter返回值虽然是10但是，根据他Kotlin的思想，不可以在getter的时候改变nameHash的数值

# 幕后属性
幕后属性只是一种哦功能设计方法
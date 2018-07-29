# Kotlin Inner Class

Kotlin中的内部类 默认为是static 的，如果是非静态类的话需要使用inner声明

如果没有加inner的时候，默认情况是不保存外部的引用的
````
class Parent{
    val a:Int = 0

    inner class Child{
        fun hello(){
            println(this@Parent.a)
        }
    }
}
````

匿名内部类
````
  var view = TextView(this)
        view.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

````
# Kotlin Interpreter
the kotlin language itself has a interpreter like python built-in.

# Kotlin Number Class
the Number class is the superclass for all classes for numberic valus.

the class event contains the method to convert the number to char

the ``val`` keyword is for unchangable values, while the ``var`` keyword is for changable keyword.

in Kotlin, the val key word only makes the reference of the object immutable :
````
val list1 :List<String> = listOf<String>("fish1","fish2")
var list2 = listOf<String>("fish2","fish3")
list1 = list2
error: val cannot be reassigned
````

````

val list3 :List<String> = listOf<String>("fish1","fish2")
list3.reversed()
println(list3)
[fish1, fish2]

var list4 = list3.reversed()
println(list4)
[fish2, fish1]
````

still works

# Boxing
Kotlin supports Boxing 

````
var number : Number = 10
var number  =10
````
the two ways are almost equal


# Arrays
````
var list4 = list3.reversed()
println(list4)
[fish2, fish1]

var intArray: IntArray = intArrayOf(1,2,3,4)
println(intArray)
[I@13967f00

println(intArray.toString())
[I@13967f00

import java.util.*

println(Arrays.toString(intArray))
[1, 2, 3, 4]
````


var s = 10;
s = "string"
````
though kotlin complier can automatically refer the type which the variable refering to but after the refering, the type is fixed and cannot be referred to another type. 


in Kotlin, there is no implicit type conversion because the implicit conversion is the major source of error.
````
var n : Byte  =10;
var n1 : Int = n
var n2 : Int = n.toInt()
````

#  NPE No more 

````
var marble: Int? =null //can be null
var rocks : Int = null //cannot be null
var lotsOfFish :List<String?> = listOf(null,null) //the elements in the list can be null 
var everMoreFish:List<String>? = null //the list itself can be null
````
## Bang
````
var goldFish : Int?  = null
goldFish!!.times(10)
````

but this will still throws an KotlinNullPointerException


````
var fishFoodTreats :Int? = null 
fun check():Int {
    return fishFoodTreats?.dec() ?: 0
}

println(check())
````
the ? operator will first check whether the value of the fishFoodTreats is null, if it's null, it will return the value after ?: operator which is zero

?: is Elvis operator and A?:B is the equivalent of the value of A? A:B

# String 
````
var fish1 : String = "something"
var fish2 : String = "somethingElse"
fish1 == fish2
false

var fish3 = "something"
fish1 == fish3
true
````

the String class in Kotlin seems much alike the one in Java

# when 
````
var numberOfFish = 10;
when(numberOfFish) {
    0 -> {
        println("there is no fish at all")
    }
    1 ->{
        println("some fish is in the water")
    }
    else->{
        println("too many fish!")
    }
}
````
using ``it`` in lambda 
````
var array = Array(5){it*2}
```` 

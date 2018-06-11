# Kotlin Interpreter
the kotlin language itself has a interpreter like python built-in.

# Kotlin Number Class
the Number class is the superclass for all classes for numberic valus.

the class event contains the method to convert the number to char

the ``val`` keyword is for unchangable values, while the ``var`` keyword is for changable keyword.

# Boxing
Kotlin supports Boxing 

````
var number : Number = 10
var number  =10
````
the two ways are almost equal

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


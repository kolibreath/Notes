# Function
the return value of Unit in kotlin doesn't need to specify it explicitly

````
fun main(arg:Array<String>){
	println("hello kotlin")
}
````

Almost everything in Koltin has a value, even something of the values are Unit.

````
println(println("something")) 
````
this will print out 'Kotlin.Unit'

# Function default value:
````
fun swim(speed:String = "fast"){
}

fun swim(){}

fun main(string:Array<String>){
	//this function will call swim() without parameters
}
````

the function can even write this way:

````
swim(speed = "fast")
````

# one line function syntax
````
fun isTooHot(temperature : Int)  = temperture> 20
````



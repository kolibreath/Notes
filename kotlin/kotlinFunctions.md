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

# High Order Function

High Order Function is the function that take another function as a parameter
````
fun highOrder(parameter : Int , parameterFunction:(Int)->Int ):Int{
	return prameterFunction(paramter)
}
````

In Kotlin, a function can be passed to a variable
````
var waterQuality:(Int) -> Int = {waterQuality -> waterQuality/2}
````

Inside the main Function:
````
fun main(args:Array<String>){
	//the waterQuality function takes an Integer as parameter and returns an Integer
	waterQuality(20)
	//the highOrder function takes an Integer and a function as parameter, meanwhile the waterQuality function behaves as a variable
	highOrder(dirt, waterQuality)
}
````

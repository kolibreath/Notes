# Kotlin Class

defining the kotin class doesn't differ to much in the way that we ddid in Java

````
class Aquarim{
	var height = 100
	var length  = 100
	var weigth = 100
	

}
````

these properties must be intialized in the class definition.

````
class Aquarim{
        var height = 100
        var length  = 100
        var weigth = 100

	val volum :Int 
		get() = height val volume :Int  get() = heigth*width*length /1000
}
````

Kotlin helps the code create the getters and setters methods unde the hood. 

And actually, the ``get`` is a key word here, but the volume must be unchangable, which is val.

This behavior can be changed, when defining the volume as var, a setter needs to be added:

````
 var volume: Int
        get() = height * width * length / 1000
        set(value) {
            height = value * 100 / (width * length)
        }
````


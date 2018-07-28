# Java Memory Basics

the heap Memory will contains the Objects and the stack will take hold of the primitive datatypes and reference variables.

````
class Abc{
	void firstMethod(){
		int b;
		Xyz xyz = new Xyz();
	}
}

class Xyx{
	int p,q;
}
````

once the firstMethod is created, the stack will allocate the memory for the method and its local variable.

````
Xyz xyz = new Xyz();
````

the code above will make the heap to create a space for the reference of xyz, but the xyz variable will be stored in the stack.

[link heare](https://www.youtube.com/watch?v=jzJjMefsFKE)

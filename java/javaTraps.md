# the loss of Percision

````
float  a = 12.4;
````
The user shoud explictly convert the float variable to double

````
float a = 12.4f;
````
> take care in auto-boxing and auto-unboxing:
````
//this is wrong:
Float a = 12;
//which should be:
Float a = 12f;
````
# access Modifiers

[Java accessible modifiers](https://img-blog.csdn.net/20170217212148665?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd3hneGdw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## default 

- for class : If the class has the default modifier, the class is only seen from the other classes in the same package.

- for variables: the same way, the modifier treats the class  


## public

- for class and for variables : both are accessiable from the whole project, the whole world.

## protected

the protected and private modifiers cannot be applied to class.
- using the protected modifier, the classes inherit the it will can acess to the protected variables and methods.
- the protected member of class A which is inherited by class B, inside B.java file, the member of A can be accessible. If in another file C, an instance of B, b is created, as long as the C.java and A.java is in the same package, whatever inside B can be accessiable.

as for the protected members inside B.java, the members can be accessible as long as B.java and C.java in the same package.

## private 

- for the class it only
- encapsulating

# import keyword
import other classes(.java) files from another package.
The class must be public class only

# package
the name of the package is corresponding with the name of file directory

# Jar them together:
1. create a Manifest file:
the parameter of Jar

2. jar them up
````
jar cmf <the name of the compiled jar> <the manifest.mf> *.class
````

# inner class and anonymous class 
the anonymous class :
````
Handler handler = new Handler(){
	@Override
	//something
}
````


````
 class PrivateClas {

    public void set(int value){

    }

    public static void main(String args[]){
        
        int shouldBeFinal = 10;
        PrivateClas clas = new PrivateClas();
        PrivateClas testFinal = new PrivateClas(){
            @Override
            public void set(int value) {
                super.set(value);
                shouldBeFinal  =10;
            }
        }
    }

}

````

the variable shouldBeFinal is accessed from the inner class which has to be final.

## Comparable and Comparator
the Comparable interface offers 1 methods with 1 parameter:
````
public interface Comparable<T> {
    public int compareTo(T o);
}
````

````
o1.compareTo(o2) > 0 means o1 > o2
o1.compareTo(o2) ==0 means o1 == o2
````

while the o1.compareTo(o2) == 0 and the result should be the same with the result of o1.equals(o2), cause some containers like Set will reject the objects from inserting while the returned value of compareTo() is the same but the returned value of isEqual() is different.


# try-catch-finally
whatever in the finally block will be executed unless System.exit(0)

# String family
StringBuffer -- the mutable String 

StringBuffer does not implements equals()

````
StringBuffer stringBuffer  = new StringBuffer("fuck");
        StringBuffer stringBuffer1 = new StringBuffer("fuck");

        if(stringBuffer==stringBuffer1)
            System.out.print(true);
        if(stringBuffer.equals(stringBuffer1))
            System.out.print(true);
````

this code snippet gives a false impression that the later true will be printed, but however it's wrong.

the only way to make the later true to be printed is to added following code:

````
  if(stringBuffer.toString().equals(stringBuffer1.toString()))
````

# Containers
Comparable
````
public int compareTo(Object b){
	Student s = (Student)b;
	return this.property - s.property;
}
````

the above code can sort the list in  ascending order.

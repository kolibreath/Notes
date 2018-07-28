# virtual
- virtual inheritence:
````
class A{
    public :
    int value;
}

class B:public A{

}

class C: public A{

}

class D: public B, public C{
    cout<<value;
}
````

thing will become more nasty here, cause the compiler does not know which the value refers to.

````
class A{
    public :
    int value;
    A(){

    }
}

class B:virtual public A{
    public:
    B(){

    }
}

class C: virtual public A{
    public:
    C(){

    }
}

class D: public B, public C{
    public:
    D(){
        
    } 
    cout<<value;
}
````

By changing the inheritance this way, there is only an instance of A, which means the call of constructor of A will be called only once.

- virtual function
the virtual keyword in the function defination means that 
````
class Enemy{
public:
    virtual void attack(){};
}

class Monster:public Enemy{
public:
    void attack(){

    }
}

class Ninja:public Enemy{
public:
    void attack(){

    }
}

int main(){
    Enemy *m ,*n;
    m  = new Monster;
    n = new Ninja;

    m->attack();
    return 0;
}
````

the base class should not find the definition of the function from the it, otherwise, from the specific class;

-pure virtual function 
````
class Bird{
    virtual void fly() = 0;
}
````

the fly() can not be implemented in this class, but the function have to be implemented in all derived class!
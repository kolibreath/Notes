# C++ 构造函数

转换构造函数 和Java 中的不一样,如果不使用构造函数去构造一个对象的时候,贸然将对象类型转变,会抛出cast异常,但是c++中可以写转换构造函数然后在赋值,编译器回去寻找数值,然后再尝试解释

````
public:
    Complex():real(0),imag(0){};
    Complex(double r, double i):real(r),imag(i){};
    Complex(double r):real(r),imag(0){};  // 定义转换构造函数

    void Print(){
        cout<<"real = " << real <<" image = "<<imag<<endl;
    }
    Complex& operator+(Complex c){
        return Complex(this->real + c.real, this->imag + c.imag);
    }
private:
    double real;
    double imag;
};


 c = 1.2;  // 调用转换构造函数将1.2转换为Complex类型    
````
如果使用explicit 则是拒绝编译器的转换 
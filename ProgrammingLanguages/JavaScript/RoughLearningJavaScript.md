# JS中创建对象的三种方式
- 字符串字面量创建：

````
person={firstname:"John",lastname:"Doe",age:50,eyecolor:"blue"};
````

- 直接创建对象的实例

````
person=new Object();
person.firstname="Bill";
person.lastname="Gates";
person.age=56;
person.eyecolor="blue";
```
- 使用对象构造器进行构造
````
function person(firstname,lastname,age,eyecolor)
{
this.firstname=firstname;
this.lastname=lastname;
this.age=age;
this.eyecolor=eyecolor;
}
````
# JavaScript prototype
prototype相当于是java中的static 变量 ，是一个类 的属性

# JS 中的window对象

All variables and functions are considered as properties of the window object.
 And there are some "famous" properties of window object, such as:

````
document frames history location navigator screen
````

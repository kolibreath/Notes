# Python learning :

### about indent 
````
magicians = ['alice','david','carolina']

for magician in  magician :
        print(magician)
print(magincian)
        print('llala')
~                      
````

a line of code without indention singals the end of the for loop, the line ``print('llala')`` will invoke IndentError output.

In the for loop, the last element in the magicians list will be stored in the ``magician`` variable, which seems weird to me.

### 列表

````
>>> list1 = [1,2,3,4,5,6]
>>> list2 = list1
>>> list2.append(10)
>>> list1.append(100)
>>> print(list1)
[1, 2, 3, 4, 5, 6, 10, 100]
````

list1 和 list2 指向了内存中的同一个引用

###　元组
元组中的数值是不能够随便就被修改的
````
>>> dimensions[0] = 100
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: 'tuple' object does not support item assignment

````
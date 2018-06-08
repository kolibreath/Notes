# Python learning :

### about indent 
indent is an interesting thing in Python. I come across the following errors :

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
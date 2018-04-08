
``GO`` 的作用
作为一个``batch``中的结束语言，宣告一个``batch``结束，以及他的生命周期

``GO`` 并不是一个TSQL 中的一种，只是被sqlcmd和osql 还有SQL management Studio code识别

GO的后面可以跟着参数，一个正实数，代表着batch执行的次数



``batch``是什么？
指的是一段时间的一句或者多句T—SQL语句，每个 T-SQL都应该使用分好结尾，但是这个要求不是强制的。 注意，不使用分好结束一个T—SQL语句的功能可能在以后的版本中会被移除。

``batch`` -> (SQL Server) -> ``execute plan``,再一次执行中，所有在``execute plan`` 中的语句都会被执行

````
CREATE TABLE dbo.t3(a int) ;
INSERT INTO dbo.t3 VALUES (1) ;
INSERT INTO dbo.t3 VALUES (1,1) ;
INSERT INTO dbo.t3 VALUES (3) ;
GO

SELECT * FROM dbo.t3 ;
````
GO前面的四句话就是一个batch，然而第二句话出发了一个异常，所以只有第一个插入执行成功。

但是在Server 2000中出触发异常后，这个创建的表中，一行插入都不存在。

在SQL 中 ``result table``指代的可能是一个``multiset``因为其中可能会用重复的行，使用distinct关键字删除重复的行，并且set是无序的，使用order by 可以规定set的顺序，形成一个有顺序的表
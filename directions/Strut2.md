# Strut 整体的业务逻辑流
![处理模式](https://7n.w3cschool.cn/attachments/tuploads/struts_2/struts-mvc.jpg)
MVC模式根据外部事件驱动的应用

# Strut核心是Action
需要你的Action名称和在jsp中的对应的Action名称字段对应<br>

## 默认的web.xml配置
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns="http://java.sun.com/xml/ns/javaee" 
   xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
   xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
   http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
   id="WebApp_ID" version="3.0">
   
   <display-name>Struts 2</display-name>
   <welcome-file-list>
      <welcome-file>index.jsp</welcome-file>
   </welcome-file-list>
   <filter>
      <filter-name>struts2</filter-name>
      <filter-class>
         org.apache.struts2.dispatcher.FilterDispatcher
      </filter-class>
   </filter>

   <filter-mapping>
      <filter-name>struts2</filter-name>
      <url-pattern>/*</url-pattern>
   </filter-mapping>
</web-app>

```
## 首先需要strut.xml配置
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC
   "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
   "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
<constant name="struts.devMode" value="true" />
   <package name="helloworld" extends="struts-default">
     
      <action name="hello" 
            class="cn.w3cschool.struts2.HelloWorldAction" 
            method="execute">
            <result name="success">/HelloWorld.jsp</result>
      </action>
   </package>
</struts>
```
注意几个点：定义的action name 和处理这个action对应的class 
``method``字段对应的是处理这个action请求对应的方法名称，默认情况下是``excute`` 


```
public class HelloWorldAction{
   private String name;

   public String execute() throws Exception {
      return "success";
   }
   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
```

## 在视图中显示：
```
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Hello World</title>
</head>
<body>
   Hello World, <s:property value="name"/>
</body>
</html>
```
Taglib 指令告知Serlet容器将这个页面使用Strut2标签，``s:property``标签显示Action类"name"属性的值，这个值有HelloWorldAction类的``getName()``返回

## Action 接口
```
public interface Action {
   public static final String SUCCESS = "success";
   public static final String NONE = "none";
   public static final String ERROR = "error";
   public static final String INPUT = "input";
   public static final String LOGIN = "login";
   public String execute() throws Exception;
}
```
### 可以再strut.xml中定义多个action

## strut2 拦截器
[可以使用的strut2拦截器列表](https://www.w3cschool.cn/struts_2/struts_interceptors.html)
配置拦截器
```
<action name="hello" 
         class="cn.w3cschool.struts2.HelloWorldAction"
         method="execute">
         <interceptor-ref name="params"/>
         <interceptor-ref name="timer" />
         <result name="success">/HelloWorld.jsp</result>
      </action>
```
在日志中生成文本:
```
INFO: Server startup in 3539 ms
27/08/2011 8:40:53 PM 
com.opensymphony.xwork2.util.logging.commons.CommonsLogger info
INFO: Executed action [//hello!execute] took 109 ms.
```

### 自定义拦截器
原本的Interceptor接口
```
public interface Interceptor extends Serializable{
   void destroy();
   void init();
   String intercept(ActionInvocation invocation)
   throws Exception;
}
```

可以说拦截器相当于在执行Action之前的一个监听：
配置strut.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">

<struts>
   <constant name="struts.devMode" value="true" />
   <package name="helloworld" extends="struts-default">

      <interceptors>
         <interceptor name="myinterceptor"
            class="cn.w3cschool.struts2.MyInterceptor" />
      </interceptors>

      <action name="hello" 
         class="cn.w3cschool.struts2.HelloWorldAction" 
         method="execute">
         <interceptor-ref name="params"/>
         <interceptor-ref name="myinterceptor" />
         <result name="success">/HelloWorld.jsp</result>
      </action>

   </package>
</struts>
```
新定义了一个自定义的interceptor
，并且将这个Interceptor放到Action = hello的定义中去

### 自定义的拦截器
```
public class MyInterceptor extends AbstractInterceptor {

   public String intercept(ActionInvocation invocation)throws Exception{

      /* let us do some pre-processing */
      String output = "Pre-Processing"; 
      System.out.println(output);

      /* let us call action or next interceptor */
      String result = invocation.invoke();

      /* let us do some post-processing */
      output = "Post-Processing"; 
      System.out.println(output);

      return result;
   
```
继承了AbstractIntercpetor

### 拦截器堆栈
使用interceptor-stack标签管理拦截器
这个配置放在``<package>``节点下

````
<interceptor-stack name="basicStack">
   <interceptor-ref name="exception"/>
   <interceptor-ref name="servlet-config"/>
   <interceptor-ref name="prepare"/>
   <interceptor-ref name="checkbox"/>
   <interceptor-ref name="params"/>
   <interceptor-ref name="conversionError"/>
</interceptor-stack>
````

之后容易在action中引入栈的名称
```
<action name="hello" class="com.tutorialspoint.struts2.MyAction">
   <interceptor-ref name="basicStack"/>
   <result>view.jsp</result>
</action
```
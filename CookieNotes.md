# cookie domain

下一个选项是 domain，指定了 cookie 将要被发送至哪个或哪些域中。默认情况下，domain 会被设置为创建该 cookie 的页面所在的域名，所以当给相同域名发送请求时该 cookie 会被发送至服务器。例如，本博中 cookie 的默认值将是 bubkoo.com。domain 选项可用来扩充 cookie 可发送域的数量，例如：

> 	Set-Cookie: name=Nicholas; domain=nczonline.net

像 Yahoo! 这种大型网站，都会有许多 name.yahoo.com 形式的站点（例如：my.yahoo.com, finance.yahoo.com 等等）。将一个 cookie 的 domain 选项设置为 yahoo.com，就可以将该 cookie 的值发送至所有这些站点。浏览器会把 domain 的值与请求的域名做一个尾部比较（即从字符串的尾部开始比较），并将匹配的 cookie 发送至服务器。

domain 选项的值必须是发送 Set-Cookie 消息头的主机名的一部分，例如我不能在 google.com 上设置一个 cookie，因为这会产生安全问题。不合法的 domain 选择将直接被忽略


# cookie path

另一个控制 Cookie 消息头发送时机的选项是 path 选项，和 domain 选项类似，path 选项指定了请求的资源 URL 中必须存在指定的路径时，才会发送Cookie 消息头。这个比较通常是将 path 选项的值与请求的 URL 从头开始逐字符比较完成的。如果字符匹配，则发送 Cookie 消息头，例如：

> 	Set-Cookie:name=Nicholas;path=/blog

在这个例子中，path 选项值会与 /blog，/blogrool 等等相匹配；任何以 /blog 开头的选项都是合法的。需要注意的是，只有在 domain 选项核实完毕之后才会对 path 属性进行比较。path 属性的默认值是发送 Set-Cookie 消息头所对应的 URL 中的 path 部分。

[link](http://bubkoo.com/2014/04/21/http-cookies-explained/)

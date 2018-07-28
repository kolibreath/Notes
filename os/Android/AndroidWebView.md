# WebView 

[Webiew 基本介绍](https://blog.csdn.net/lowprofile_coding/article/details/77928614)


## webViewClient #shouldOverridingUrlLoading 返回false 或者 true的问题
一般情况下返回false 使用系统自带的 逻辑处理，正常情况下返回true 和 返回false的表现是没有区别的，
但是在html 的head中存在location href 会存在使用上的区别
[区别](https://juejin.im/post/5a5d8ef2f265da3e393a6b76)


## 这个方法的使用
是为了[拦截](https://blog.csdn.net/zhyh1986/article/details/42169159)这些链接的跳转在这个webView中进行

#java和 JavaScript 方法互相调用

## Java代码调用Javascript方法：
webView.loadUrl("javascript:callJS()");

callJS is a function of the JavaScript file 

# intercept from Android

from the view of Android, we can use WebViewClient shouldOverringUrlLoading to intercept the url and add some of our own logic

or we can use a set of methods exported by webChromeClient 
``onJsAlert()`` ``onJsConfirm()`` ``onJsPrompt()`` to intercept

````
  mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });
````

And there is another method called 
````
    mWebView.evaluateJavascript（"javascript:callJS()", new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String value) {
            //此处为 js 返回的结果
        }
    });
````

## JavaScript calls Java code:

- object mapping 
mapping Java object to a JavaScript object:

`````
public JavaClass {
	@JavaScriptInterface
	public void say(String msg){
		println(msg);
	}
}
````` 

`````
function callFromJava() {
	test.say("my name");
}
`````

````
// test must be the same name in the Javascript
webView.addJavascriptInterface(new JavaClass(),"test");
````

- WebViewClient 

````
   function callAndroid(){
            /*约定的url协议为：js://webview?arg1=111&arg2=222*/
            document.location = "js://webview?arg1=111&arg2=222";
         }
````

````
// 复写WebViewClient类的shouldOverrideUrlLoading方法
mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                          // 步骤2：根据协议的参数，判断是否是所需要的url
                                          // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                                          //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                                          Uri uri = Uri.parse(url);                                 
                                          // 如果url的协议 = 预先约定的 js 协议
                                          // 就解析往下解析参数
                                          if ( uri.getScheme().equals("js")) {

                                              // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                                              // 所以拦截url,下面JS开始调用Android需要的方法
                                              if (uri.getAuthority().equals("webview")) {

                                                 //  步骤3：
                                                  // 执行JS所需要调用的逻辑
                                                  System.out.println("js调用了Android的方法");
                                                  // 可以在协议上带有参数并传递到Android上
                                                  HashMap<String, String> params = new HashMap<>();
                                                  Set<String> collection = uri.getQueryParameterNames();

                                              }

                                              return true;
                                          }
                                          return super.shouldOverrideUrlLoading(view, url);
                                      }
                                  }
        );
   }
        }
````

This way is not technically to use javascript to run java code, but more like a way to transmit data.

JS may get data from Android using:
``webView.loadUrl("javascript:returnReusult("+result+")")``

````
function returnResult(result){
	alert("result is" + result);
}
````

- use WebChromeClient onJSAlert() get data in alert dialog in JS
[all you need to know about webView ](https://blog.csdn.net/carson_ho/article/details/64904691)



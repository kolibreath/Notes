#  Ajax
http://www.w3school.com.cn/ajax/ajax_xmlhttprequest_response.asp

# JavaScript DOM

var para = document.createElement("p");
var node = document.createTextNode();

JS cannot remove a child element without using its parent.

document.getElementById("p1").innerHTML = "new text";

## respond to Event

### click event
<h1 onclick="changetext(this)">请点击该文本</h1>

### enter event

<script>
	function checkCookie(){
		if(navigatior.cookieEnabled == true){
			alter("cookie enabled");
		}
	}
</script> 

### onChange event
<input type="text" id="ff" onChange="upperCase()">

### onmousedown onmouseup onclick

# JQuery
$('#test') == document.getElementById('test')
$('.test')  == document.getElementByClassName('test')

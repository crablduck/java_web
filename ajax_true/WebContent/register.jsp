<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="#" method="post">
	
		账号：<input id="username" type="text" name="username"/><span></span> <br/>
		密码：<input type="password" name="password"/><br/>
		确认密码：<input type="password"/><br/>
		
		省份：<select id="province" name="province">
					<option>---请选择---</option>
					<option value="guangdong">广东</option>	
					<option value="hunan">湖南</option>	
					<option value="hubei">湖北</option>	
			 </select>
		
		城市：<select id="city">
					<option>---请选择---</option>
			  </select>
			  <br/>
		
		
		<input type="submit" value="注册"/>
	
	
	</form>

	<script type="text/javascript">
	
		//创建XMLHttpRequest对象
		function getXMLhttpRequest(){
			var xmlhttp;
			
			if(window.XMLHttpRequest){//兼容
				xmlhttp = new XMLHttpRequest();
			}else if(window.ActiveXObject){//不兼容
				xmlhttp = new ActiveXObject();
			}
			return xmlhttp;
		}
	
		var username = document.getElementById("username");
		username.onblur = function(){//失去焦点事件
			var xmlhttp = getXMLhttpRequest();
			//在此事件中获取来自服务端的信息
			xmlhttp.onreadystatechange = function(){
				
				if(xmlhttp.readyState==4){//请求以结束，响应就绪
					if(xmlhttp.status == 200){//连接正常
						
						var text = xmlhttp.responseText;
						var span = document.getElementsByTagName("span")[0];
						if(text == "1"){
							span.style.color = "red";
							span.innerText = "抱歉，账号已被注册";
						}else if(text == "0"){
							span.style.color = "green";
							span.innerText = " 恭喜，账号可用";
							
						}
					}
				}
			}
			
			xmlhttp.open("GET","UserServlet?action=isRegister&username="+username.value, true);
			xmlhttp.send();//发送
		}
		
		var province = document.getElementById("province");
		var city = document.getElementById("city");
		province.onchange = function(){
			
			var value = province.value;
			if(value != "---请选择---"){
				var xmlhttp = getXMLhttpRequest();
				//在此事件中获取来自服务端的信息
				xmlhttp.onreadystatechange = function(){
					
					if(xmlhttp.readyState==4){//请求以结束，响应就绪
						if(xmlhttp.status == 200){//连接正常
							
							var text = xmlhttp.responseText;
							var array = eval(text);//json类型的数组，获得到数据
							var str = "";
							
							for(var i =0;i<array.length;i++){
								str += "<option value='"+array[i].id+"'>"+array[i].cityName+"</option>";
							}
						
							city.innerHTML = str;
						}
					}
				}
				xmlhttp.open("GET","UserServlet?action=updateprovince4city&province="+province.value, true);
				xmlhttp.send();//发送
			}else{
				city.innerHTML = "<option>---请选择---</option>";
			}
			
		}
	</script>

</body>
</html>
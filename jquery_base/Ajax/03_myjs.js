$.getJSON("02_myjson.json",function(data){//data指的是JSON文件
						var str = "";
						$.each(data,function(index,stu){//stu指的是json文件中一个个的对象
							str += stu.username + " --- " + stu.email + "<br/>";
						})
						$("#info").html(str);
					})
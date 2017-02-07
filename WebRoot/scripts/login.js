$(function(){
	//给登录按钮增加单击事件
	$("#login").click(function(){
		/*
		*清空之前的提示信息
		*/
		//清除用户名提示
		$("#count_msg").empty();
		//清除密码提示
		$("#password_msg").empty();
		//alert("niahao");
		//获取登录数据
		var name = $("#count").val().trim();
		var pwd = $("#password").val().trim();
		//数据格式检查
		if(name == "") {
			//提示
			$("#count_msg").html("用户名不能为空");
			return;
		} 
		if(pwd == "") {
			//提示
			$("#password_msg").html("密码不能为空");
			return;
		}
		
		
		//alert(name + " " + pwd);
		//发送ajax请求
		$.ajax({
			url:"user/login.do",
			type:"post",
			data:{'name':name,'pwd':pwd},
			dataType:"json",
			success: function(data){//data是服务器返回的json结果
				//如果登录成功
				if(0==data.status) {
					//获取用户id,写入Cookie
					var userId = data.data;
					addCookie("uid", userId, 2);//存储两小时
					window.location.href="edit.html";
				} else if(1==data.status) { 
					//用户名错误，给以提示
					$("#count_msg").html(data.msg);
				} else { 
					//密码错误，给以提示
					$("#password_msg").html(data.msg);
				}
			}
				
		});
	});
});
$(function(){
	$("#regist_button").click(function(){
		//alert("hello");
		/*
		*获取表单信息
		*/
		var name = $("#regist_username").val().trim();
		var nickname = $("#nickname").val().trim();
		var pwd = $("#regist_password").val().trim();
		var final_pwd = $("#final_password").val().trim();
		
		/*
		*检测表单格式省略
		*/
		
		//发送请求
		$.ajax({
			url:"user/regist.do",
			type:"post",
			dataType:"json",
			data:{"name":name,"pwd":pwd,"nickname":nickname},
			success:function(data){
				//如果成功则跳转到登录界面
				if(data.status == 0) {
					alert("注册成功 请登录");
					//返回登录页面
					$("#back").click();//触发返回按钮的单击事件
				} else if(data.status == 1) {//用户名被占用
					//更改提示信息
					$("#warning_1 span").html(data.msg);
					//显示提示
					$("#warning_1").show();
				}
			}
		});
		
	});
});
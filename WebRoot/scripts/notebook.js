function loadNoteBooks() {
	/*
	 * 清空内容区和标题栏
	 */
	//清空标题栏
	$("#input_note_title").val("");
	//清空内容显示区
	um.setContent("");
	//发送请求
	$.ajax({
		url:"notebook/loadbooks.do",
		type:"post",
		data:{"userId":userId},
		success:function(result){
			if(result.status==1) {
				alert(result.msg);
				return;
			}
			//alert(result.msg);
			//获取json对象中的笔记本集合
			var books = result.data;
			//循环笔记本集合，生成<li>列表
			for(var i=0;i<books.length;i++) {
				//获取笔记本集合的名称和id
				var bookName = books[i].cn_notebook_name;
				var bookId = books[i].cn_notebook_id;
				//alert(bookId);
				//构建<li>元素
				var li = '<li  class="online"><a>';
				li += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
				li += '</i> '+bookName+'</a></li>';
				//将字符串转换成Jquery对象
				var $li = $(li);
				//将bookId藏在$li
				$li.data("bookId", bookId);
				//将$li添加到对应的<ul>中
				$("#book_list").append($li);
			}
		}
	});
};
/*
 * 弹出添加笔记本或笔记本对话框
 */
function showAddbooksWindow() {
	/*
	*发出alert/alert_notebook.html请求
	*将请求返回的结果（即html）放入id=can的div中
	*可用$.ajax(),但用load()更方便
	*/
	//显示背景
	$(".opacity_bg").show();
	//显示对话框
	$("#can").load("alert/alert_notebook.html");	
};
/*
 * 关闭对话框
 */
function closeWindow() {
	//清空对话框
	$("#can").empty();
	//隐藏背景
	$(".opacity_bg").hide();
	//清除移动笔记操作对话框上绑定的noteId
	$("#can").data("$li","");
};
/*
 * 创建笔记本
 */
function addBook(){
	//alert("nihao");
	//获取添加笔记本话框的输入框值
	var bookName = $("#input_notebook").val().trim();
	//alert(bookName);
	/*
	*输入为空给以提示
	*/
	if(bookName=="") {
		alert("笔记本名称不能为空");
		return;
	}
	//发送请求
	$.ajax({
		url:"notebook/add.do",
		type:"post",
		data:{"bookName":bookName,"userId":userId,"typeId":"5"},
		dataType:"json",
		success:function(result) {
			//获取服务器传回的bookId
			var bookId = result.data;
			//关闭创建窗口
			closeWindow();
			//构建<li>元素
			var li = '<li  class="online"><a>';
			li += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
			li += '</i> '+bookName+'</a></li>';
			//将字符串转换成Jquery对象
			var $li = $(li);
			//将bookId藏在$li
			$li.data("bookId", bookId);
			/* if($li.data("bookId1")==undefined) {
				alert("帅");
			} */
			//alert($li.data("bookId"));
			//将$li添加到对应的<ul>中
			$("#book_list").prepend($li);
			//alert("创建成功");
		},
		error:function() {
			alert("创建失败");
		}
	});	
}
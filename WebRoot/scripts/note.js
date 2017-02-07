function loadnotes(){
	//alert($(this).data("bookId"));
	//检查内容是否更改，内容已改变(即isChange()返回true)则提示保存，不再往下执行
	if(isChange()) {
		return;
	}
	/*
	 * 清空编辑器标题栏和内容显示区已以及清除标题栏所绑定的noteId
	 * 更新用于检查是否更改，记录之前标题和内容的变量
	 */
	//清空标题栏
	$("#input_note_title").val("");
	//清空内容显示区
	um.setContent("");
	//清除标题栏所绑定的noteId
	$("#input_note_title").data("noteId","");
	//更新全局记录变量
	title_ = "";
	body_ = "";
	
	$()
	//给笔记本列表追加选中样式
	//去除所以<li>的样式
	$("#book_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");//需要先去除所以<li>的样式
	//获取曾经隐藏在<li>里面的id
	var bookId = $(this).data("bookId");
	//发送请求
	$.ajax({
		url:"note/loadnotes.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result) {
			//清除原有的笔记列表
			$("#note_list").empty();
			if(result.status == 1) {
				alert(result.msg);
				return;
			}
			//有数据则遍历(status=0表示有数据)
			if(0==result.status) {
				//获取返回笔记的json对象数组
				var datas = result.data;
				for(var i=0;i<datas.length;i++) {
					//获取笔记标题和id
					var noteId = datas[i].CN_NOTE_ID;
					var noteTitle = datas[i].CN_NOTE_TITLE;
					//alert(noteTitle);
					//拼凑需要的<li>元素
					var li = '<li class="online">' +
						'<a>' +
							'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>' +
						'</a>' +
						'<div class="note_menu" tabindex="-1">' +
							'<dl>' +
								'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>' +
								'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>' +
								'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>' +
							'</dl>' +
						'</div>' +
					'</li>';
					//将字符串转换成jQeury对象
					var $li = $(li);
					//将Id绑定到对应的<li>
					$li.data("noteId",noteId);
					//将<li>(即$li)添加到笔记列表对应的<ul>中
					$("#note_list").append($li);
				}
			}
		}
	});
};
/*
*弹出添加笔记对话框
*/
function showAddnoteWindow(){
	/*
	*发出alert/alert_note.html请求
	*将请求返回的结果（即html）放入id=can的div中
	*可用$.ajax(),但用load()更方便
	*/
	//显示背景
	$(".opacity_bg").show();
	//显示对话框
	$("#can").load("alert/alert_note.html");
}
//保存过程中弹出div阻止用户进行其它操作
function showSaveWindow() {
	//显示背景
	$(".opacity_bg").show();
	//显示对话框alert_savenote.html
	$("#can").load("alert/alert_savenote.html");
}
//去掉保存过程中弹出div恢复用户进行其它操作
function hideSaveWindow() {
	//隐藏背景
	$(".opacity_bg").hide();
	//清空对话框（即去掉对话框）
	$("#can").empty();
}
/*
 *创建笔记
 */
function addNote(){
	//alert("hello");
	//获取输入框的值(笔记名称)
	var noteTitle = $("#input_note").val().trim();
	//如果输入为空则给以提示
	if(noteTitle == "") {
		alert("请输入笔记名称");
		return;
	}
	//获取bookId 重点（checked为<a>的class，<li>为<a>的父元素）
	var $li = $("#book_list a.checked").parent();
	var bookId = $li.data("bookId");
	//测试bookId
	//alert(bookId);
	if(bookId == undefined) {
		alert("请选择要添加的笔记本");
		return;
	}
	//发送请求
	$.ajax({
		url:"note/addnote.do",
		type:"post",
		data:{"noteBookId":bookId,"userId":userId,"statusId":"1","typeId":"1","title":noteTitle},
		dataType:"json",
		success:function(result) {
			if(result.status==0) {
				//关闭显示框
				closeWindow();
				//获取返回的笔记id
				var noteId = result.data;
				//拼凑需要的<li>元素
				var li = '<li class="online">' +
					'<a>' +
						'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>' +
					'</a>' +
					'<div class="note_menu" tabindex="-1">' +
						'<dl>' +
							'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>' +
							'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>' +
							'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>' +
						'</dl>' +
					'</div>' +
				'</li>';
				//将字符串转换成jQeury对象
				var $li = $(li);
				//将Id绑定到对应的<li>
				$li.data("noteId",noteId);
				//将<li>(即$li)添加到笔记列表对应的<ul>中
				$("#note_list").prepend($li);
			} else {
				alert("创建笔记失败 请重新登录");
			}
		},
		error:function() {
			alert("创建笔记失败 请重新登录");
		}
	});
};
//检查内容是否改变，返回true则内容改变
function isChange() {
	//获取当前编辑器的标题和内容
	var now_title = $("#input_note_title").val().trim();
	var now_body = um.getContent().trim();
	//alert("title_:"+title_+"  "+"now_title:"+now_title);
	//alert("body_:"+body_+"  "+"now_body:"+now_body);
	if(title_!=now_title||body_!=now_body) {
		//内容已改变，询问用户是否保存
		if(confirm("是否保存当前修改?")) {
			//用户选择保存则触发“保存笔记”按钮的单击事件
			$("#save_note").click();
		} else {
			/*
			 * 用户选择不保存则将标题框和内容区恢复为全局记录变量的值
			 */
			//恢复标题栏
			$("#input_note_title").val(title_);
			//恢复内容区
			um.setContent(body_);
		}
		return true;
	}
	return false;
};
//给笔记<li>绑定单击处理，即加载笔记内容
function loadNoteContent(){
	//检查内容是否更改，内容已改变(即isChange()返回true)则提示保存，不再往下执行
	if(isChange()) {
		return;
	}
	//alert("hei");
	//移除之前所有选中样式
	$("#note_list li a").removeClass("checked");
	//添加选中样式
	$(this).find("a").addClass("checked");
	//alert($(this).data("noteId"));
	//获取选中的笔记的id
	var noteId = $(this).data("noteId");
	if(noteId == undefined) {
		alert("登录失效");
		return;
	}
	//发送请求
	$.ajax({
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			//如果标题jQuery对象绑定有noteId,且查询到的内容和现有内容不相同则提示用户保存
			var note_flag = $("#input_note_title").data("noteId");
			/* if(note_flag!=null&&note_flag!=undefined&&note_flag!=""){
				
			} */
			//将noteId绑定到标题jQuery对象上,提交笔记时使用
			$("#input_note_title").data("noteId",noteId);
			//获取返回的内容
			var note = result.data;
			//等于1为没有内容
			if(result.status == 1) {
				alert(result.msg);
				//将标题放到标题栏
				$("#input_note_title").val(note.cn_note_title);
				//将标题放到全局变量title_中
				title_ = note.cn_note_title.trim();
				//alert("title_:"+title_);
				//清空内容显示区
				um.setContent("");
				//将内容放到全局变量body_中
				body_ = "";
				return;
			}
			if(result.status == 0) {
				//将标题放到标题栏
				$("#input_note_title").val(note.cn_note_title);
				//将标题放到全局变量title_中
				title_ = note.cn_note_title.trim();
				//alert("title_:"+title_);
				//内容不为空则将内容放到显示框
				um.setContent(note.cn_note_body);
				//将内容放到全局变量body_中
				body_ = note.cn_note_body.trim();
			}
		},
		type:"post",
		url:"note/query.do",
		error:function(){
			alert("笔记加载失败");
		}
	});
};
//给保存笔记按钮添加处理事件,即保存笔记
function saveNoteContent(){
	//alert("sfsfdsd");
	//获取隐藏在标题栏noteId
	var noteId = $("#input_note_title").data("noteId");
	if(noteId==null||noteId==""||noteId==undefined) {
		alert("请选择笔记");
		return;
	}
	//alert(noteId);
	//获取标题的值
	var noteTitle = $("#input_note_title").val().trim();
	if(noteTitle==null||noteTitle==undefined||noteTitle=="") {
		alert("请输入记标题");
		return;
	}
	//获取内容区的值
	var noteBody = um.getContent().trim();
	//alert("先");
	//保存过程中弹出div阻止用户进行其它操作
	showSaveWindow();
	//发送请求
	$.ajax({
		url:"note/update.do",
		type:"post",
		data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
		dataType:"json",
		success:function(result){
			alert(result.msg);
			/*
			 *检查笔记名称是否更改，若更改则更新之
			 */
			//alert("标题");
			 var old_noteTitle = $("#note_list li a.checked").text().trim();
			 //alert(old_noteTitle);
			 if(noteTitle!=old_noteTitle) {
			 	//选择当前笔记标题所在的的jQuery对象更新标题
			 	var html = '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'
			 	//更新html
			 	$("#note_list li a.checked").html(html);
			 	
			 }
			 //更新全局记录变量
			title_ = noteTitle;
			body_ = noteBody;
			 //去掉保存过程中弹出div恢复用户进行其它操作
			 hideSaveWindow();
		},
		error:function(){
			alert("更新失败");
			//去掉保存过程中弹出div恢复用户进行其它操作
			hideSaveWindow();
		}
	});
};


<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/smarteditor/js/HuskyEZCreator.js"></script>
<script>
var oEditors = [];
$(function() {	
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "info", //textarea ID
		sSkinURI: "<%=request.getContextPath()%>/smarteditor/SmartEditor2Skin.html",	
		htParams : {
			bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			fOnBeforeUnload : function(){
				
			}
		}, //boolean
		fOnAppLoad : function(){
			//예제 코드
			oEditors.getById["info"].exec("PASTE_HTML", ["자기소개를 적어주세요"]);			
		},
		fCreator: "createSEditor2"
	});
	
	
});

function InsertInfo(){
	
	oEditors.getById["info"].exec("UPDATE_CONTENTS_FIELD", []);
	document.getElementById('frm').submit();
}

</script>
<h2>Member</h2>
<form id="frm" action="memberInsert.do" method="post" enctype="multipart/form-data">
아이디 : <input type="text" name="id"><br>
비밀번호 : <input type="password" name="password"><br>
이름 : <input type="text" name="name"><br>
파일 : <input type="file" name="file"/><br>
<textarea id="info" name="info"></textarea>
<input type="button" value="저장" onclick="InsertInfo();">
</form>
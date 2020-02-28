<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.uploadResult {
	width: 100vw;
	height: 20vh;
	display: flex;
	background-color: blue;
}
.item {
	width: 150px;
	background-color: white;
}
</style>
</head>
<body>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>

	<input class="fileInput" type="file" name="uploadFile" multiple>
	<button id="uploadBtn">UPLOAD</button>
<div class="uploadResult"></div>
	<script>
		$(document).ready(function() {

			var fileInput = $(".fileInput");
			
			var fileInputCopy = fileInput.clone;
			var uploadResult = $(".uploadResult");

			$("#uploadBtn").on("click", function(e) {
				var formData = new FormData();

				var inputFile = $("input[name='uploadFile']");

				var files = inputFile[0].files;

				console.log(files);

				//add filedate to formdata

				for (var i = 0; i < files.length; i++) {

					formData.append("uploadFile", files[i]);

				}//업로드로 올려진 파일들을 배열로 넣는것 

				$.ajax({
					url : '/upload/uploadAjaxAction',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					success : function(arr) {
						alert("Uploaded");
						
						console.log(arr);
						
						var str = "";
						
						for (var i = 0; i < arr.length; i++) {
							str += "<div class='item'><button><img src='/upload/display?fname=" + arr[i] +"'>DEL</button><div>"
						}
						uploadResult.append(str);
					}
				}); //$.ajax 
				//formData에 배열방식으로 저장된  데이터들은 POST방식으로 url이 맵핑된 컨트롤러에 보내준다. 
			});

		});
	</script>
	
</body>
</html>
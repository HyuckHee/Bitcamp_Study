	//즉시 실행함수 => 한번만 실행됨
	var dataService = (function() {

		var url = "http://192.168.0.19:8080/data/"

		function list(page, callback) {
			console.log("add=" + url)

			$.getJSON(url + "/list?page=" + page, callback)
		}

		function add(obj, callback) {
			console.log("add=" + url)

			$.ajax({
				type : "POST",
				url : url + "new",
				data : JSON.stringify(obj),
				contentType : "application/json; charset=urf-8",
				success : callback
			});

		}

		return {
			add : add,
			list : list
		} //모듈패턴
	})();

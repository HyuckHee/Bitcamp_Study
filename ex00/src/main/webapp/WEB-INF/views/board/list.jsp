
<%@ include file="../includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Tables</h1>
	<p class="mb-4">
		DataTables is a third party plugin that is used to generate the demo
		table below. For more information about DataTables, please visit the <a
			target="_blank" href="https://datatables.net">official DataTables
			documentation</a>.
	</p>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>Bno</th>
							<th>Title</th>
							<th>Writer</th>
							<th>RegDate</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${list }">
							<tr>
								<td>${board.bno }</td>
								<td>${board.title }</td>
								<td>${board.writer }</td>
								<td>${board.regdate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<ul class="pagination justify-content-end">
						<c:if test="${pageUtil.prev }">
							<li class="page-item"><a class="page-link" href="/board/list?page=${pageUtil.start-1}">Previous</a></li>
						</c:if>
						<c:forEach begin="${pageUtil.start }" end="${pageUtil.end }"
							var="pNum">
							<li class="page-item ${pNum==pageUtil.dto.page?'active':"" }"><a class="page-link" href="/board/list?page=${pNum }">${pNum }</a></li>
						</c:forEach>
						<c:if test="${pageUtil.next }">
							<li class="page-item"><a class="page-link" href="/board/list?page=${pageUtil.end+1 }">Next</a>
							</li>
							</c:if>
						
					</ul>
				</div>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@ include file="../includes/footer.jsp"%>

<script>
	/*      $(document).ready(function(){
	 alert($);
	 }) */
</script>

</body>

</html>

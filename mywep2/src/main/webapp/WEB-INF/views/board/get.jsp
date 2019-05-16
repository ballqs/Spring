<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<style type="text/css">
	#pwinput{
		width: 100%;
	}
</style>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Read</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Read Page</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

          <div class="form-group">
          <label>게시물 번호</label> <input class="form-control" name='POST_NO'
            value='<c:out value="${board.POST_NO }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>제목</label> <input class="form-control" name='POST_TITLE'
            value='<c:out value="${board.POST_TITLE }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>내용</label>
          <textarea class="form-control" rows="3" name='POST_CONTENT'
            readonly="readonly"><c:out value="${board.POST_CONTENT}" /></textarea>
        </div>

        <div class="form-group">
          <label>작성자</label> <input class="form-control" name='ID'
            value='<c:out value="${board.ID }"/>' readonly="readonly">
        </div>

<%-- 		<button data-oper='modify' class="btn btn-default">
        <a href="/board/modify?POST_NO=<c:out value="${board.POST_NO}"/>">Modify</a></button>
        <button data-oper='list' class="btn btn-info">
        <a href="/board/list">List</a></button> --%>

<c:choose>
	<c:when test="${USERCHECK eq 'Y'.charAt(0) and id eq board.ID or admin eq 'Y'.charAt(0)}"><!-- null이 아닐때 회원이라서 이 회원의 아이디랑 게시물 번호가 같은 레코드에 있는지? -->
		<button data-oper='modify' class="btn btn-default">수정</button>
		<button data-oper='list' class="btn btn-info">목록</button>	
	</c:when>
	<c:when test="${USERCHECK eq 'N'.charAt(0)}"><!-- null일때? 비회원이라는 것.... 그렇다면 비밀번호의 값도 필요함 -->
		<button data-oper='modify2' data-toggle="modal" data-target= "#deleteCheckModal" data-title="data" class="btn btn-default">수정</button>
		<button data-oper='list' class="btn btn-info">목록</button>	
	</c:when>
	<c:otherwise>
		<button data-oper='list' class="btn btn-info">목록</button>
	</c:otherwise>
</c:choose>

<%-- <form id='operForm' action="/boad/modify" method="get">
  <input type='hidden' id='POST_NO' name='POST_NO' value='<c:out value="${board.POST_NO}"/>'>
</form> --%>


<form id='operForm' method="get">
  <input type='hidden' id='POST_NO' name='POST_NO' value='<c:out value="${board.POST_NO}"/>'>
  <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
  <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
  <input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
  <input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>  
  <input id='npID' type='hidden' name='NONE_PW' />
</form>



      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>

<div class="modal fade" id="deleteCheckModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
		<form>
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">${board.POST_NO}번의 게시물을 수정하실려면 비밀번호를 입력하세요!</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<input id="pwinput" type="password" name="np">
			</div>
			<div class="modal-footer">
				<!--
				replace한 이유는 삭제한 페이지의 기록을 남기고 싶지 않아서이다. (뒤로가기불가)
				replace 참고 url : http://heeestorys.tistory.com/747-->
				<button type="button" class="btn btn-primary" id="MODIFY">수정</button>
				
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</form>
		</div>
	</div>
</div>



<!-- /.row -->


<!-- JQuery 기본 설정 파일의 제일 좋은 위치 -->
<%-- <c:out value="${script_include}"/> --%>
<!-- <script src="/resources/vendor/jquery/jquery.min.js"></script> -->

<script type="text/javascript">
$(document).ready(function() {
  
  var operForm = $("#operForm"); 
  
  
  $("button[data-oper='modify']").on("click", function(e){
	    
	    operForm.attr("action","/boardmodify").attr("method","post").submit();
	    
	  });
  
  $("button[data-oper='modify2']").on('show.bs.modal', function(e){
	    
	  });
  $('#MODIFY').on("click", function(e){
	var np = document.getElementsByName("np")[0].value;
	$("#npID").val(np);
    operForm.attr("action","/boardmodify2").attr("method","post").submit();	  
  });
  
    
  $("button[data-oper='list']").on("click", function(e){
    
    operForm.find("#POST_NO").remove();
    operForm.attr("action","/").submit();
    
  });  
});
</script>

<%@include file="../includes/footer.jsp"%>
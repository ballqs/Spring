<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">등록 화면</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">게시물 작성</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

        <form role="form" action="/postregister" method="post">
          <div class="form-group">
            <label>제목</label> <input class="form-control" name='post_title'>
          </div>

          <div class="form-group">
            <label>내용</label>
            <textarea class="form-control" rows="3" name='post_content'></textarea>
          </div>
			
			<c:choose>
				<c:when test="${id eq null}">					
		          <div class="form-group">
		            <label>닉네임</label> <input type="text" class="form-control" name='id'>
		          </div>
		          <div class="form-group">
		            <label>비밀번호</label> <input type="password" class="form-control" name='password'>
		          </div>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="id" value="${id}">
					<input type="hidden" name='password' value=null>
				</c:otherwise>
			</c:choose>
          <button type="submit" class="btn btn-default">작성</button>
          <button type="reset" class="btn btn-default">취소</button>
        </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>

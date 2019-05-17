<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<style type="text/css">
	#fullsize{
		width: 100%;
		height: 100%;
		padding: 20px 0 0 0;
	}
	#profileHead{
		height: 5%;
		width: 100%;
		border: 1px solid pink;
		background-color: pink;
		box-shadow: 2px 2px 2px gray;
	}
	#profileTitle{
		margin: 25px 10px;
		text-shadow: 1px 1px 1px black;
		font-style: italic;
		color: white;
	}
	#profileBody{
		height: 95%;
		width: 100%;
	}
	#profileImg{
		display: inline-block;
		float:left;
		width: 15%;
		height: 100%;
		margin: 10px;
		background-color: gray;
	}
	#Image{
		height: 200px;
	}
	#profileImpormation{
		margin: 10px;
		padding: 5px;
		display: inline-block;
		width: 80%;
		height: 100%;
		clear:left;
	}
	#profileImpormation hr{
		width: 100%;
	}
	.impormation{
		font-size: 15px;
		font-style: italic;
	}
</style>

<div id="fullsize">
	<div id="profileHead">
		<div id="profileText">
			<h3 id="profileTitle">프로필 정보</h3>
		</div>
	</div>
	<div id="profileBody">
		<div id="profileImg">
			<div id="Image"></div>
		</div>
		<div id="profileImpormation">
			<div class="impormation">
				아이디 : <c:out value="${member.ID }"/>
			</div>
			<hr/>
			<div class="impormation">
				이름 : <c:out value="${member.NAME }"/>
			</div>
			<hr/>
			<div class="impormation">
				이메일 : <c:out value="${member.EMAIL }"/>
			</div>
			<hr/>
			<div class="impormation">
				전화번호 : <c:out value="${member.TEL }"/>
			</div>
			<br/>
		</div>
	</div>
	

</div>


<%@include file="../includes/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
	.fullsize{
		background-color: rgba(220, 220, 220, 0.3);
		width: 250px;
		height: 350px;
		margin: 80px auto;
		padding: 15px;
		border-radius: 5px;
		box-shadow: 1px 2px 2px black;
	}
	legend{
		font-size: 15px;
		font-weight:bolder;
		text-shadow: 1px 2px 2px gray;
		padding: 0 0 15px 0;
		border-bottom: 1px solid black;
	}
	form{
		display: block;
	}
	.span3{
		border-radius: 8px;
		border:1px solid rgba(220, 220, 220, 0.3);
		width: 95%;
		height: 30px;
		margin: 12px 0 0 0;
		padding: 0 0 0 8px;
		outline: none;
	}
	.btn{
		margin: 25px 0 0 0;
		width: 100%;
		border-radius: 5px;
		border: 1px solid orange;
		background-color: orange;
		outline: none;
	}
	.btn:hover {
		background-color: #FF7F50;
		border: 1px solid #FF7F50;
	}
</style>
</head>
<body>
<!------ Include the above in your HEAD tag ---------->
<div class="fullsize">
      <legend>회원 가입 창</legend>
    <form accept-charset="UTF-8" action="singupComplete" method="post">
        <input class="span3" name="id" placeholder="Username" type="text">
        <input class="span3" name="pw" placeholder="Password" type="password"> 
        <input class="span3" name="name" placeholder="Name" type="text">
        <input class="span3" name="email" placeholder="Email" type="email">
        <input class="span3" name="tel" placeholder="Tel" type="text"> 
        <button class="btn btn-warning" type="submit">회원가입</button>
    </form>
</div>
</body>
</html>
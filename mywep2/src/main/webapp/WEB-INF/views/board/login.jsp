<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="/resources/css/login.css" rel="stylesheet">
</head>
<body>
<!------ Include the above in your HEAD tag ---------->
<div>　</div>
<div>　</div>
<div>　</div>
<div>　</div>
<div class="mycontainer">
	<div class="container">
	    <div class="row">
	    	<div class="col-md-4 col-md-offset-4">
	    		<div class="panel panel-default">
				  	<div class="panel-heading">
				    	<h3 class="panel-title">로그인 화면</h3>
				 	</div>
				  	<div class="panel-body">
				    	<form action = "loging" method = "post" accept-charset="UTF-8" role="form">
	                    <fieldset>
				    	  	<div class="form-group">
				    		    <input class="form-control" placeholder="id를 입력해주세요!" name="id" type="text">
				    		</div>
				    		<div class="form-group">
				    			<input class="form-control" placeholder="pw를 입력해주세요!" name="pw" type="password" value="">
				    		</div>
				    		<div class="checkbox">
				    	    	<label>
				    	    		<input name="remember" type="checkbox" value="Remember Me"> Remember Me
				    	    	</label>
				    	    </div>
				    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
				    		<br/>
				    		<a href="signup"><button class="btn btn-lg btn-success btn-block"  type="button">Sing Up</button></a>
				    	</fieldset>
				      	</form>
				    </div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
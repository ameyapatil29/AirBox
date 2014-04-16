
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login &amp; Airbox</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"
	type="text/css">
<link href="bootstrap/bootstrap.css" rel="stylesheet" media="screen"
	type="text/css">
<link href="bootstrap/bootstrap-responsive.css" rel="stylesheet"
	media="screen" type="text/css">
<link href="bootstrap/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen" type="text/css">
<script type="text/javascript" src="jsbootstrap/bootstrap.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.min.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-dropdown.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-alert.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-button.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-carousel.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-scrollspy.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-modal.js" /></script>



<script type="text/javascript">

function uploadFormData(){
	 alert('inside uploadform');
	   var fname = $('#login-firstname').val();
	   var lname = $('#login-lastname').val();
	   var email = $('#login-email').val();
	   var password = $('#password').val();
	 
	 alert('name'+fname);
	   
		$.ajax({
			url : "rest/file/signup",
		    type: "POST",
		    data : "fname=" + fname + "&lname=" + lname + "&email=" + email + "&password=" + password,
		   
		    success:function(data, textStatus, jqXHR){
		    	alert('success');
		    	window.location.href="index.jsp";
		    },
		    error: function(jqXHR, textStatus, errorThrown){
		    	alert('Could not process request.. ' + errorThrown);
		    }
		});
}


</script>
</head>
<body>
	<div class="container"></div>
	<div class="well"></div>
	<div id="loginbox" style="margin-top: 160px"></div>
	<div style="margin-bottom: 100px"></div>
	<div class="panel panel-info"></div>
	<div class="panel-heading">
		<div class="panel-title">Airbox</div>
		<div class="panel-title">Sign Up</div>

	</div>

	<div style="padding-top: 30px" class="panel-body"></div>

	<div style="display: none" id="login-alert"
		class="alert alert-danger col-sm-12"></div>

	<form id="loginform" class="form-horizontal"></form>

	<div style="margin-bottom: 25px" class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-user"></i></span> <input id="login-firstname"
			type="text" class="form-control" name="firstname" value=""
			placeholder="Firstname">
	</div>
	<div style="margin-bottom: 25px" class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-user"></i></span> <input id="login-lastname"
			type="text" class="form-control" name="lastname" value=""
			placeholder="Lastname">
	</div>
	<div style="margin-bottom: 25px" class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-user"></i></span> <input id="login-email"
			type="text" class="form-control" name="email" value=""
			placeholder="Email Address" onChange="EmailVerify();">
	</div>
	<div style="margin-bottom: 25px" class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-user"></i></span> <input id="password"
			type="text" class="form-control" name="password" value=""
			placeholder="Password">
	</div>
	<div style="margin-bottom: 25px" class="input-group"></div>
	<span class="input-group-addon"><i
		class="glyphicon glyphicon-user"></i></span>
	<input id="confirmpassword" type="text" class="form-control"
		name="confirm password" value="" placeholder="Confirm Password"
		onChange="PassVerify();">






	<div class="input-group"></div>


	<!-- Button -->
	<!-- 
                                    <div class="col-sm-12 controls">
                                      <a id="signup"  class="btn btn-info">Register </a>

                                       
                                     
                                    </div>
                                                                       -->
<!-- 
 <button id="signup" value="Register" onclick="uploadFormData()" ></button> 
     -->                          
<button id="signup" class="btn btn-large btn-success" type="button" onclick="uploadFormData()">Register</button>






	 <script>
        function PassVerify(){
            var pass1 = $('#password').val();
            var pass2 = $('#confirmpassword').val();
            var message = document.getElementById('confirmMessage');
            var badColor = "red";
            if(password == confirmpassword){
                message.innerHTML = "";
            }else{
                message.style.color = badColor;
                message.innerHTML = "Passwords Do Not Match..";
            }
        } 
        function EmailVerify() {
            var email = document.getElementById('email').value;
            var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            var message = document.getElementById('emailMessage');
            var badColor = "red";
            message.style.color = badColor;

            if (!filter.test(email)) {   
               $('#emailMessage').html("Please Enter valid Email");
               return false;
            }else{
               $('#emailMessage').html("");
               $.get('/check_email?email=' + email, function(data){
                    if(data == "true")
                        $('#emailMessage').html("Email already exists");
               });
            }
            return true;
        }
        function validateAndSubmit(){
            
        }
        </script>

	

<div id="footer">
		<div class="navbar navbar-inverse navbar-fixed-bottom">
			<div class="navbar-inner" style="min-height: 70px;">
				<div style="margin: 10px 5px 10px 5px; float: left;">
					<span style="color: white;">Copyright &copy; AirBox</span>
				</div>
				<div style="margin: 10px 10px 10px 10px; float: right;">
					<a href="about.htm" style="color: white;">About Us</a> <span
						style="color: white;">|</span> <a data-toggle="modal" href="#" style="color: white;">Contact
						Us</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>




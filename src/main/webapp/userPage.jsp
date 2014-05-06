
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap, from Twitter</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles
    <link href="../assets/css/bootstrap.css" rel="stylesheet">
     -->

<link href="bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"
	type="text/css">
<link href="bootstrap/bootstrap.css" rel="stylesheet" media="screen"
	type="text/css">
<link href="bootstrap/bootstrap-responsive.css" rel="stylesheet"
	media="screen" type="text/css">
<link href="bootstrap/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen" type="text/css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.min.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-dropdown.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-alert.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-button.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-carousel.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-scrollspy.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-modal.js" /></script>



<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<!-- 
    <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">
 -->
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="../assets/ico/favicon.png">
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="#">Project name</a>
				<div class="nav-collapse collapse">

					<p class="navbar-text pull-right">
						Logged in as <a href="#" class="navbar-link">Username</a>
					</p>
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						Space used	</h4>
						<div class="progress progress-info progress-striped">
							<div class="bar" style="width: 83.3%"></div>
						</div>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">
				<div class="well well-large"> <!--  class="hero-unit"-->

					<div class="row">

						<form class="navbar-search pull-left">
							<input type="text" class="search-query" placeholder="Search" style="margin-left:20px">
						</form>
						<button class="btn btn-primary" type="button"style="margin-left:510px">Upload File</button>
					
					<input type="image" id="myimage" src="images/Basic-Upload-2-icon.png" />
					
					</div>
					<div></div>
				</div>

				<table cellpadding="0" cellspacing="0" border="0"
					class="table table-hover" id="rate">
					<thead>
						<tr>
							<th>File Name</th>
							<th>File Content</th>
							<th>Date Modified</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${ratings}">
							<tr>
								<td>CMPE 273 Final Question Answer.pdf</td>
								<!-- ${p.developerName} -->
								<td>PDF</td>
								<!-- ${p.rating} -->
								<td>2014-03-03 07:32</td>
								<!-- ${p.date} -->
								<td><button class="btn btn-primary" type="button">Download</button></td>
								<td><button class="btn btn-success type="button">Share</button></td>
							</tr>
							<!-- second row -->
							<tr>
								<td>CMPE 283 Final Question Answer.pdf</td>
								<!-- ${p.developerName} -->
								<td>PDF</td>
								<!-- ${p.rating} -->
								<td>2014-03-03 05:32</td>
								<!-- ${p.date} -->
								<td><button class="btn btn-primary" type="button">Download</button></td>
								<td><button class="btn btn-success type="button">Share</button></td>
							</tr>
							<!-- Third row -->
							<tr>
								<td>GoldenGate.jpg</td>
								<!-- ${p.developerName} -->
								<td>Image</td>
								<!-- ${p.rating} -->
								<td>2014-04-03 06:27</td>
								<!-- ${p.date} -->
								<td><button class="btn btn-primary" type="button">Download</button></td>
								<td><button class="btn btn-success type="button">Share</button></td>
							</tr>


						</c:forEach>

					</tbody>

				</table>
				<button class="btn btn-primary" type="button">Download All</button>


			</div>
			<!--/span-->
		</div>
		<!--/row-->

	
		
	</div>
<%@include file="layout/footer.jsp"%>
</body>
</html>
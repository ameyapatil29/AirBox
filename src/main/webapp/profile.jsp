<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
 <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>

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
	






</head>
<body>

	<%@include file="layout/header.jsp"%>


   
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
           
           <table>
           <tbody>
           
           <tr>
           <td>First Name </td>
           <td>: ${usersfirstname}</td>
           </tr>
           <tr>
           <td>Last Name </td>
           <td>: ${userslastname}</td>
           </tr>
           <tr>
           <td>Email Id </td>
           <td>: ${username}</td>
           </tr>
           
           </tbody>
           
           
           </table>
           
           
           
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
         
         
          <div class="row-fluid">
            
            <table id="example" class="table table-hover">
					<thead>
						<tr>
							<th>Shared With</th>
							<th>File Name</th>
						
							<th>Date Modified</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${history}">
						<tr>
							<td>${item.getUsername()}</td>							
							<td>${item.getFileName()}</td>
						
							<td>${item.getDateCreated()}</td>
						
						<!-- 
							<td onclick="Files('${item.getFileName()}');"><button
									class="btn btn-primary" type="button">Download</button></td>
							<td onclick="shareFiles('${item.getFileName()}');"><button
									class="btn btn-success" type="button">Share</button></td>
							<td onclick="Files('${item.getFileName()}');"><button
									class="btn btn-danger" type="button">Delete</button></td>
					 -->
						</tr>
						</c:forEach>
					</tbody>
					 
				</table>
            <button class="btn btn-primary" type="button"
							style="margin-left: 380px" onclick="window.location.href='userPage.jsp'">&laquo; Back to User page </button>
				</div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->

    

    

    </div><!--/.fluid-container-->



	<%@include file="layout/footer.jsp"%>

</body>
</html>

















 
package com.AirBox.rest;

import com.AirBox.SES.AmazonSESSample;
import com.AirBox.SES.ConcreteMessage;
import com.AirBox.SES.ConcreteUserInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.AirBox.Dao.DbConnection;
import com.AirBox.Domain.UploadObject;
import com.AirBox.Domain.User;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;



@Path("/file")
public class UploadFileService {
	
	public ConcreteMessage cm;
	public ConcreteUserInfo ui;
	public AmazonSESSample as;
	
	AWSCredentials myCredentials = new BasicAWSCredentials(
			S3Config.getMyAccessId(), S3Config.getMySecretId());
	AmazonS3 newc = new AmazonS3Client(myCredentials);        
	Region usWest1 = Region.getRegion(Regions.US_WEST_1);
	
	
/*
 * Rest API for handling between AWS and files  
 */
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			 @FormDataParam("file") File fileobject,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader,@Context HttpServletRequest req) {
		String username;
		
		HttpSession session= req.getSession(true);
		username=(String) session.getAttribute("username");
		String invalidFile = "Invalid File";
		AWSFacade awsFacade=new AWSFacade();
		String output=awsFacade.addS3BucketObjects(fileobject,contentDispositionHeader.getFileName());
		//If File Uploaded successfully then send EMail to user about the upload
		
		if(output.equalsIgnoreCase("success")){
			
			String msgBody ="File upload successful";
			String msgHeader = "AWS S3 file upload success";
			
			ui = new ConcreteUserInfo("chetan.burande7@gmail.com");
			cm = new ConcreteMessage(msgBody, msgHeader);
			as = new AmazonSESSample();
			/*ui.setReEmail("chetan.burande7@gmail.com");
			cm.setMsgBody(msgBody);
			cm.setMsgSubject(msgHeader);*/
			as.setConnec(ui, cm);
		}
		
		UploadObject uploadobject = new UploadObject();
		uploadobject.setFileName(contentDispositionHeader.getFileName());
		uploadobject.setSize(fileobject.length());
		uploadobject.setUsername(username); 
		
		System.out.println("upload object user is "+ uploadobject.getUsername());
		System.out.println("uploaded object date created is "+uploadobject.getDateCreated());
		System.out.println("upload object size is "+ fileobject.length());//new
		//System.out.println("uploaded object date created is "+ dateFormat.format(date));//new
		DbConnection dbcon = new DbConnection();
		if(dbcon.insertFiledata(uploadobject, username)) 
		{
			System.out.println("file added finally");
			return Response.status(200).entity(output).build();
			}
			
			else
				System.out.println("sorry...Not enough space ...");
				return Response.status(400).entity(invalidFile).build();
			

	}
			
			
			
		

	@GET
    @Path("/download")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response downloadObjects(){
    	
    	String output="Files downloaded at location: C:/Users/Rohit/Desktop/AirBoxRepo/";
    	String downloadLocation = "C:/Users/Bhagyashree/Desktop/AirBoxRepo/";
    	AWSFacade awsFacade=new AWSFacade();
    	output=awsFacade.downloadAllS3BucketObjects(downloadLocation);
    	return Response.status(200).entity(output).build();
    }  
	
	@GET
    @Path("/download/{objectKey}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response downloadOneObject(@PathParam("objectKey") String key){
    	
    	String output="Files downloaded at location: C:/Users/Rohit/Desktop/AirBoxRepo/";
    	String downloadLocation = "/Users/sumantmurke/Desktop/download/";
    	AWSFacade awsFacade=new AWSFacade();
    	output=awsFacade.downloadS3BucketObject(downloadLocation, key);
    	return Response.status(200).entity(output).build();
    }  
	
	@DELETE
    @Path("/delete/{objectkey}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response deleteObject(
    		@PathParam("objectkey") String key){
    	AWSFacade awsFacade=new AWSFacade();
    	System.out.println("key::::::: "+key);
    	String output=awsFacade.deleteS3BucketObjects(key);
    	return Response.status(200).entity(output).build();
    }
	
	/*
	 * REST API for accessing of user data 
	 */
	
	@POST
	@Path("/signup")
	public Response userprofile(@FormParam("fname") String fname,
			@FormParam("lname") String lname,
			@FormParam("email") String email, 
			@FormParam("password") String password ) {
			System.out.println("name is: "+fname);
			User user = new User();
			user.setFirstName(fname);
			user.setLastName(lname);
			user.setUserName(email);
			user.setPassword(password);
			AWSFacade fact= new AWSFacade();
			System.out.println("surname of the user is"+user.getLastName());
			String output = "Thankyou for registring with us you will recieve email shortly "+ user.getFirstName();
			DbConnection dbcon = new DbConnection();
			dbcon.insertUser(user);
			System.out.println("User added");
			
			Bucket bucket = fact.makeNewBucket(user);
			
			String msgBody ="Your account was created on AirBox.";
			String msgHeader = "Registration Confirmation";
			
			ui = new ConcreteUserInfo(email);
			as = new AmazonSESSample();
			cm = new ConcreteMessage(msgBody, msgHeader);
			//ui.setReEmail(email);
			//cm.setMsgBody(msgBody);
			//cm.setMsgSubject(msgHeader);
			as.setConnec(ui, cm);
		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("/login")
	public Response userLogin(@FormParam("email") String email, 
			@FormParam("password") String password, 
			@Context HttpServletRequest req) {
			String output = "";
			String invalidUser = "Invalid User";
			System.out.println("Username is: "+email);
			DbConnection dbcon = new DbConnection();
		
			if(dbcon.loginCheck(email, password))
			{
			output = "Login Successful for "+ email;
			System.out.println("User Validated");
			HttpSession session= req.getSession(true);
			session.setAttribute("username", email);
			session.setAttribute("sessionId", session.getId());
			
			return Response.status(200).entity(output).build();
			}
			
			else
				System.out.println("User Invalid");
				return Response.status(400).entity(invalidUser).build();
			

	}
	
	@POST
	@Path("/sharelink")
	public Response shareEmail(@FormParam("shareemail") String shareemail, 
			@FormParam("filename") String filename, 
			@Context HttpServletRequest req) {
			String shareFilNname = "";
			String owneremail = "";
			String output = "success";
			System.out.println("Link will be shared with : "+shareemail);
			HttpSession session= req.getSession(true);
			owneremail=(String) session.getAttribute("username");
			
			DbConnection dbcon = new DbConnection();
			
			dbcon.shareFile(owneremail, filename, shareemail);
			// Sending email to user
			String ownermsgBody ="You have shared a file - "+filename+" with - "+shareemail;
			String ownermsgHeader = "File sharing successful";
			String fileLink = "";
			ui = new ConcreteUserInfo(owneremail);
			cm = new ConcreteMessage(ownermsgBody, ownermsgHeader);
			as = new AmazonSESSample();
			as.setConnec(ui, cm);
			// Sending email notification to file receiver
			AWSFacade awsFacade=new AWSFacade();
			fileLink = awsFacade.getShareLink(filename);
			String receivermsgBody =owneremail+ " has shared a file - "+filename+" with you. The link for the file is - "+fileLink;
			String receivermsgHeader = "File sharing successful";
			
			ui = new ConcreteUserInfo(shareemail);
			cm = new ConcreteMessage(receivermsgBody, receivermsgHeader);
			as = new AmazonSESSample();
			as.setConnec(ui, cm);
		
			/*if(dbcon.loginCheck(email, password))
			{
			output = "Login Successful for "+ email;
			System.out.println("User Validated");
			HttpSession session= req.getSession(true);
			session.setAttribute("username", email);
			session.setAttribute("sessionId", session.getId());
			
			return Response.status(200).entity(output).build();
			}
			
			else
				System.out.println("Invalid email");
				return Response.status(400).entity(invalidUser).build();
			*/
			return Response.status(200).entity(output).build();

	}	

	
	@GET
	@Path("/logout")
	public Response logout(@Context HttpServletRequest req) {
		HttpSession session= req.getSession(false);
		session.invalidate();
		System.out.println("User has logged out !");
		String output = "User has succesfully logged out";
		
		 return Response.status(200).entity(output).build();
		
	}
	
}



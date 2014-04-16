package com.AirBox.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.AirBox.Dao.DbConnection;
import com.AirBox.Domain.UploadObject;
import com.AirBox.Domain.User;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class UploadFileService {
/*
 * Rest API for handling between AWS and files  
 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			 @FormDataParam("file") File fileobject,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

		AWSFacade awsFacade=new AWSFacade();
		String output=awsFacade.addS3BucketObjects(fileobject,contentDispositionHeader.getFileName());
		UploadObject uploadobject = new UploadObject();
		uploadobject.setFileName(contentDispositionHeader.getFileName());
		uploadobject.setSize(contentDispositionHeader.getSize());
		//uploadobject.setUsername(username); remaining part of taking username dynamically from the session
		uploadobject.setDateCreated(contentDispositionHeader.getCreationDate());
		
		System.out.println("upload object size is "+ uploadobject.getSize());
		System.out.println("uploaded object date created is "+uploadobject.getDateCreated());
		
	
		return Response.status(200).entity(output).build();

	}
	
	@GET
    @Path("/download")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response downloadObjects(){
    	
    	String output="Files downloaded at location: C:/Users/Rohit/Desktop/AirBoxRepo/";
    	String downloadLocation = "C:/Users/Rohit/Desktop/AirBoxRepo/";
    	AWSFacade awsFacade=new AWSFacade();
    	output=awsFacade.downloadAllS3BucketObjects(downloadLocation);
    	return Response.status(200).entity(output).build();
    }  
	
	@GET
    @Path("/download/{objectKey}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response downloadOneObject(@PathParam("objectKey") String key){
    	
    	String output="Files downloaded at location: C:/Users/Rohit/Desktop/AirBoxRepo/";
    	String downloadLocation = "C:/Users/Rohit/Desktop/AirBoxRepo/";
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
			System.out.println("surname of the user is"+user.getLastName());
			String output = "Thankyou for regestring with us you will recieve email shortly "+ user.getFirstName();
			DbConnection dbcon = new DbConnection();
			dbcon.insertUser(user);
			System.out.println("User added");
		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("/login")
	public Response userLogin(@FormParam("email") String email, 
			@FormParam("password") String password ) {
			String output = "";
			System.out.println("Username is: "+email);
			DbConnection dbcon = new DbConnection();
			if(dbcon.loginCheck(email, password))
			{
			output = "Login Successful for "+ email;
				
			System.out.println("User Validated");
			
			}
			return Response.status(200).entity(output).build();

	}
}

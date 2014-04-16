package com.AirBox.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class UploadFileService {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			 @FormDataParam("file") File fileobject,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

		AWSFacade awsFacade=new AWSFacade();
		String output=awsFacade.addS3BucketObjects(fileobject,contentDispositionHeader.getFileName());
		
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

}

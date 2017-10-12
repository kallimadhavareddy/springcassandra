package com.cog.springcassandra.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;

import io.swagger.annotations.Api;

@Component
@Path("/verifyuser")
@Api(value = "File Upload resource", produces = "application/json")
public class FileUploadController {

	
	@Path("/file")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@DefaultValue("") @FormDataParam("tags") String tags, 
				@FormDataParam("file") InputStream file,
				@FormDataParam("file") FormDataContentDisposition fileDisposition) {

		String fileName = fileDisposition.getFileName();
		
		saveFile(file, fileName);
		
		String fileDetails = "File saved at /Volumes/Drive2/temp/file/" + fileName + " with tags "+ tags;

		System.out.println(fileDetails);

		return Response.ok(fileDetails).build();
	}
	
	private void saveFile(InputStream file, String name) {
		/* Change directory path */
		java.nio.file.Path path = FileSystems.getDefault().getPath("/Volumes/Drive2/temp/file/" + name); 
		/* Save InputStream as file */
		//Files.copy(file, path);
	}
}
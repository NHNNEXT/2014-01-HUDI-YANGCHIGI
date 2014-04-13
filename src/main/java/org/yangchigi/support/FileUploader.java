package org.yangchigi.support;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploader {
	// location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "/Users/jehyeok/yangchigi/2014-01-HUDI-YANGCHIGI/webapp/img";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public static ArrayList<String> upload(HttpServletRequest req) {
		ArrayList<String> contentList = new ArrayList<String>();

		if (!ServletFileUpload.isMultipartContent(req)) {
			System.out
					.println("Error: Form must has enctype=multipart/form-data.");
			return null;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory
		// String uploadPath = getServletContext().getRealPath("") +
		// File.separator + UPLOAD_DIRECTORY;
		String uploadPath = UPLOAD_DIRECTORY;

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(req);

			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {
					if (item.getFieldName().equals("contents"))
						contentList.add(item.getString());

					// processes only fields that are not form fields
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator
								+ fileName;
						File storeFile = new File(filePath);

						contentList.add(item.getName());

						// saves the file on disk
						item.write(storeFile);
						req.setAttribute("message",
								"Upload has been done successfully!");
					}
				}
			}
		} catch (Exception ex) {
			req.setAttribute("message",
					"There was an error: " + ex.getMessage());
		}
		// redirects client to message page
		return contentList;
	}
}
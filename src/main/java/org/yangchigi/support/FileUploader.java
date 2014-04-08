package org.yangchigi.support;

import java.io.File;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

public class FileUploader {
	//Local Path
	private static final String ATTACHMENT_ROOT_DIR = "/Users/kimminhyeok/git/2014-01-HUDI-YANGCHIGI/webapp/img";
	//Server Path
//	private static final String ATTACHMENT_ROOT_DIR = "/var/lib/tomcat7/webapps/ROOT/pdf";

	public static String upload(MultipartFile multipartFile) {
		if (multipartFile.isEmpty()) {
			return null;
		}
		transferToAttachmentDir(multipartFile);
		return multipartFile.getOriginalFilename();
	}

	private static File transferToAttachmentDir(MultipartFile multipartFile) {
		File destFile = getDestinationFile(multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(destFile);
		} catch (Exception ex) {
			throw new IllegalArgumentException(destFile + "");
		}
		return destFile;
	}

	public static File getDestinationFile(String fileName) {
		
		return new File(ATTACHMENT_ROOT_DIR + File.separator + fileName.replace(".jpg", MyCalendar.getCurrentDateTime() + ".jpg"));
	}
}

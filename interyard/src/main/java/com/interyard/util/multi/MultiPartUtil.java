package com.interyard.util.multi;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MultiPartUtil {

	private int fileSizeLimit = 100*1024*1024;
	private String encType = "UTF-8";
	private String saveFileName;
	private String seveFilePath;
	private HttpServletRequest request;
	private MultipartRequest multi;
	
	public MultiPartUtil(HttpServletRequest request, String saveFileName) throws IOException {
		this.request = request;
		this.saveFileName = saveFileName;
		seveFilePath = request.getServletContext().getRealPath(saveFileName);
		File realSavePathFile = new File(seveFilePath);
		if(!realSavePathFile.exists()) {
			realSavePathFile.mkdirs();
			System.out.println(saveFileName+" 폴더 생성");
		}
	}

	public int getFileSizeLimit() {
		return fileSizeLimit;
	}

	public void setFileSizeLimit(int fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}
	
	public void initMultipartRequest() throws IOException {
		multi = new MultipartRequest(request, seveFilePath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
	}
	
	public void initMultipartRequest(HttpServletRequest request) throws IOException {
		multi = new MultipartRequest(request, seveFilePath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
	}
	
	public String getParameter(String name) {
		String result = null;
		if(multi.getParameter(name) != null && !multi.getParameter(name).equals("")) {
			result = multi.getParameter(name);
		} else if(multi.getFilesystemName(name) != null && !multi.getFilesystemName(name).equals("")) {
			result = "/"+saveFileName+"/"+multi.getFilesystemName(name);
		}		
		return result;
	}
	
	public String getFilesystemName(String name) {		
		return "/"+saveFileName+"/"+multi.getFilesystemName(name);
	}
	
	public Long getLongParameter(String name) {
		String result = null;
		if(multi.getParameter(name) != null && !multi.getParameter(name).equals("")) {
			result = multi.getParameter(name);
		} else if(multi.getFilesystemName(name) != null && !multi.getFilesystemName(name).equals("")) {
			result = "/"+saveFileName+"/"+multi.getFilesystemName(name);
		}		
		return Long.parseLong(result);
	}
	
	public void deleteFile(String name) {
		String deleteFileName = request.getParameter(name);
		if(deleteFileName != null && deleteFileName.equals("")) {
			deleteFileName = multi.getParameter(name);
		}
		File deleteFile = new File(request.getServletContext().getRealPath(deleteFileName));
		if(deleteFile.exists()) deleteFile.delete();
	}

}

package com.cly.ssi.springmvc.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cly.common.exception.BusinessException;
import com.cly.common.util.StringFunction;

/**
 * 文件上传下载
 */
public class FileOperation {

	/**
	 * 功能：上传一个或多个文件
	 * 
	 * @param inputName
	 * 
	 *            <pre>
	 *            		表单提交示例：
	 *            
	 *            		<form action="http://localhost:8080/xxx.do" method="post" enctype="multipart/form-data">
	    	 				图片：<input type="file" name="inputName"><br>
				        	<input type="submit" value="提交">
				    	</form>
	 *            </pre>
	 * 
	 * @param dir
	 *            文件夹路径
	 */
	public static List<String> upload(HttpServletRequest request, String inputName, String dir) throws Exception {
		if (StringFunction.isEmpty(dir)) {
			throw new BusinessException("文件夹路径不可为空");
		}
		List<String> fileNames = new ArrayList<String>();
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = mRequest.getFiles(inputName);
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			if (fileName.lastIndexOf(".") != -1) {
				fileName = StringFunction.getUuid() + fileName.substring(fileName.lastIndexOf("."));
			} else {
				fileName = StringFunction.getUuid();
			}
			File fileSrc = new File(dir + File.separator + fileName);
			FileUtils.copyInputStreamToFile(file.getInputStream(), fileSrc);
			fileNames.add(fileName);
		}
		return fileNames;
	}

	/**
	 * 根据文件名称下载文件
	 */
	public static ResponseEntity<byte[]> download(String path, String fileName) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		String rspName = path + File.separator + fileName;
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
		File file = new File(rspName);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}

}

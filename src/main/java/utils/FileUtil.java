package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException{
		Part part = req.getPart("ofile");
		String partHeader = part.getHeader("content-disposition");
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"","");
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory + File.separator + originalFileName);
		}
		return originalFileName;
	}
	
	public static String renameFile(String sDirectory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf('.'));
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		return newFileName;
	}
	
	public static void downFile(HttpServletRequest req, HttpServletResponse resp, String sDirectory, String sFileName, String oFileName) {
		try {
			File file = new File(sDirectory, sFileName);
			
			InputStream inStream = new FileInputStream(file);			
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64") == 1) {
				oFileName = new String(oFileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				oFileName = new String(oFileName.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + oFileName + "\"");
			resp.setHeader("Content-Length", ""+ file.length());
			OutputStream outStream = resp.getOutputStream();
			
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = inStream.read(b)) > 0) {
				outStream.write(b, 0, readBuffer);
			}
			
			inStream.close();
			outStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(HttpServletRequest req, String sDirectory, String fileName) {
		File file = new File(sDirectory + File.separator + fileName);
		if(file.exists()) {
			file.delete();
			System.out.println("첨부파일 삭제......");
		}
	}
}

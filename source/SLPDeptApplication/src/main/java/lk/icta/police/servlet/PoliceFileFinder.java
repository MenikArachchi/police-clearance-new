package lk.icta.police.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.utility.CommonUtil;
import lk.icta.police.util.FileUtil;

/**
 * Servlet implementation class ReportFileFinder
 */
public class PoliceFileFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PROP_FILE_NAME = "police";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PoliceFileFinder() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String fileName=request.getParameter("fileName");    	
		
		final int BUFSIZE = 1024*1024;
		final String uploadPath = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.nic.passport.file.upload.location.finder");
		String fullFilePath = uploadPath + "/" + fileName;
		
		BufferedInputStream input = null;
	    BufferedOutputStream output = null;
		
		try {
			File f = new File(fullFilePath);
			if((!(f.exists()))){
				ClassLoader classLoader = getClass().getClassLoader();
				f = new File(classLoader.getResource(PoliceConstant.IMAGE_NOT_FOUND_IMAGE).getFile());
			}
			int length   = 0;
			// Prepare streams.
			ServletContext context  = request.getSession().getServletContext();
			String mimetype = context.getMimeType(fullFilePath);
			System.out.println("mimetype :"+mimetype);

			//
			//  Set the response and go!
			response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
			//response.setContentLength( (int)f.length() );
			response.setHeader( "Content-Disposition", "inline; filename=\"" + fileName + "\"" );

			//
			//  Stream to the requester.
			//
			
			//FileOutputStream outt = new FileOutputStream(uploadPath+"/output.pdf");
			FileUtil.decryptFile(new FileInputStream(f),response.getOutputStream());
			//input = new BufferedInputStream(new FileInputStream(uploadPath+"/output.pdf"), BUFSIZE);
			//FileUtil.decryptFile(new FileInputStream(f),response.getOutputStream());
			
//	        input = new BufferedInputStream(new FileInputStream(f), BUFSIZE);
	        //output = new BufferedOutputStream(response.getOutputStream(), BUFSIZE);
//	        
//	        
	        // Write file contents to response.
	       /* byte[] buffer = new byte[BUFSIZE];
	        while ((length = input.read(buffer)) > 0) {
	            output.write(buffer, 0, length);
	        }*/
//				
//			
//			
       } catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			output.flush();
//	        output.close();
//	        input.close();
		}
	    
    	
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}

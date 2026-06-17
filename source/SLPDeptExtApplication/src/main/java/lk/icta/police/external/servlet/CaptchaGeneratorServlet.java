package lk.icta.police.external.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class CaptchaGeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int height=0;
	private int width=0;
		
	public static final String CAPTCHA_KEY = "captcha_key_name";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
	 height=Integer.parseInt(getServletConfig().getInitParameter("height"));
     width=Integer.parseInt(getServletConfig().getInitParameter("width"));
  }

 
 public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
	  //Expire response
	  response.setHeader("Cache-Control", "no-cache");
	  response.setDateHeader("Expires", 0);
	  response.setHeader("Pragma", "no-cache");
	  response.setDateHeader("Max-Age", 0);
	  response.setContentType("image/jpeg");
		
	  BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	  Graphics2D graphics2D = image.createGraphics();
	  Random r = new Random();
	  String token = Long.toString(Math.abs(r.nextLong()), 36);
	  String ch = token.substring(0,6);
	  Color c = new Color(0f, 0f, 1f);//new Color(0.6662f, 0.4569f, 0.3232f);
	  //GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
	  GradientPaint gp = new GradientPaint(30, 30, Color.white, 15, 25, c , true);
	  graphics2D.setPaint(gp);
	  Font font=new Font("Geneva, Arial, Helvetica, sans-serif, Decker", Font.CENTER_BASELINE , 15);
	  graphics2D.setFont(font);
	  graphics2D.drawString(ch,2,15);
	  graphics2D.dispose();
	  
	  HttpSession session = req.getSession(true);
	  session.setAttribute(CAPTCHA_KEY,ch);

	  OutputStream outputStream = response.getOutputStream();
	  ImageIO.write(image, "jpeg", outputStream);
	  outputStream.close();



 }

}

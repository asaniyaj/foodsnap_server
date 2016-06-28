package foodsnap_server.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import foodsnap_server.model.*;
import foodsnap_server.dto.ComparisonInfo;
import foodsnap_server.dto.ComparisonInfoList;
import foodsnap_server.dto.NutritionalInfo;
import foodsnap_server.dto.NutritionalInfoList;

@Path("/comparisonclass_post")
public class PostNutritionInfo {

	private static final Logger logger = Logger.getLogger(PostNutritionInfo.class);

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public HttpServletResponse getComparison(String randomString) {

		String barcode = "1000000000";
		String height = "100";
		String weight = "100"; 
		int version = 1; 
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getComparison");
			logger.debug("barcodes: '" + barcode + "'");
			logger.debug("height: '" + height + "'");
			logger.debug("weight: '" + weight + "'");
			logger.debug("version: '" + version + "'");
		}
		
		int cal = CalorieIntake.getCalIntake(height, weight);
		System.out.println("Calorie intake is: "+cal);
		String response = null;
		HttpServletResponse response_http = new HttpServletResponse() {
			
			@Override
			public void setLocale(Locale arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentType(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentLengthLong(long arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentLength(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setCharacterEncoding(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBufferSize(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public PrintWriter getWriter() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setStatus(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setStatus(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendRedirect(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendError(int arg0, String arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendError(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getStatus() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Collection<String> getHeaders(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}
		};
        try{			
            switch(version){
	            case 1:
	            	ComparisonInfoList infoList = null;
	            	
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                try {
	                	ComparisonInfo l = new ComparisonInfo();
	        			l = ProductComparison.getProductComparison("barcode", barcode, cal);
	        			
	        			infoList = new ComparisonInfoList();
	        			infoList.setComparisonInfo(l);
	        		}
	        		catch (Exception e) {
	        			System.out.println(e);
	        		}
	        		Gson gson = new Gson();
	        	    Type type = new TypeToken<ComparisonInfoList>() {}.getType();
	        	    String json = gson.toJson(infoList, type);
	        	    System.out.println("JSON: "+json);
	                response = json; //"Response from Jersey Restful Webservice : " + l.get(0).getFood_id();
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getNutrition");
        }
        response_http.addHeader("Access-Control-Allow-Origin", "*");
        return response_http;	
	}
	

	

}

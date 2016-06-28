package foodsnap_server.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import foodsnap_server.model.*;
import foodsnap_server.dto.ComparisonInfo;
import foodsnap_server.dto.ComparisonInfoList;
import foodsnap_server.dto.NutritionalInfo;
import foodsnap_server.dto.NutritionalInfoList;

@Path("/comparisonclass")
public class GetComparisonInformation {

	private static final Logger logger = Logger.getLogger(GetComparisonInformation.class);

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getComparison(@DefaultValue("1000000000") @QueryParam("barcode") String barcode,
			@DefaultValue("100") @QueryParam("height") String height,
			@DefaultValue("100") @QueryParam("weight") String weight, 
			@DefaultValue("1") @QueryParam("version") int version) {

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
        //response.addHeader("Access-Control-Allow-Origin", "*");
        return response;	
	}
	

	

}

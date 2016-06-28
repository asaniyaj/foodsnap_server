package foodsnap_server.dao;

import java.sql.*;
import java.util.*;

import foodsnap_server.constants.foodsnap_environmentvar;
import foodsnap_server.dto.*;

public class DBAccessor {
	Connection dbconn;
	
	public DBAccessor() {
		try {
			dbconn = connectToDatabaseOrDie();
		}
		catch (Exception e) {
			e.printStackTrace();
	    	System.exit(1);
		}
	}
	
	public List<NutritionalInfo> getProductInfo_DB(String columnname, Object columnValue) throws Exception {
		List<NutritionalInfo> nutritionalinfoList = new ArrayList<NutritionalInfo>();
		Statement stmt = dbconn.createStatement();
		//TODO: Fix this for comma separated list! 
		 //Change syntax accordingly.
		String sqlquery = "SELECT nutritionalinfo.food_id, food_name, food_type, brand_name, food_url, serving_id, serving_description, serving_url, metric_serving_amount, metric_serving_unit, number_of_units, measurement_description, calories, carbohydrate, protein, fat, saturated_fat, polyunsaturated_fat, monounsaturated_fat, trans_fat, cholesterol, sodium, potassium, fiber, sugar, vitamin_a, vitamin_c, calcium, iron, review, foodimage_url from nutritionalinfo INNER JOIN barcodefoodidmapping ON nutritionalinfo.food_id=barcodefoodidmapping.food_id LEFT JOIN reviews ON nutritionalinfo.food_id=reviews.food_id and reviews.is_valid = true WHERE " + columnname + " = '" + columnValue + "';";
		System.out.println(sqlquery);
		ResultSet res = stmt.executeQuery(sqlquery);
		while(res.next()) {
			NutritionalInfo ni = new NutritionalInfo();
			ni.setFood_id(res.getLong(1));
			ni.setFood_name(res.getString(2));
			ni.setFood_type(res.getString(3));
			ni.setBrand_name(res.getString(4));
			ni.setFood_url(res.getString(5));
			
			Serving sv = new Serving();
			sv.setServing_id(res.getLong(6));
			sv.setServing_description(res.getString(7));
			sv.setServing_url(res.getString(8));
			sv.setMetric_serving_amount(res.getFloat(9));
			sv.setMetric_serving_unit(res.getString(10));
			sv.setNumber_of_units(res.getFloat(11));
			sv.setMeasurement_description(res.getString(12));
			ni.setServinginfo(sv);
			
			Nutrient n = new Nutrient();
			n.setCalories(res.getDouble(13));
			n.setCarbohydrate(res.getDouble(14));
			n.setProtein(res.getDouble(15));
			n.setFat(res.getDouble(16));
			n.setSaturated_fat(res.getDouble(17));
			n.setPolyunsaturated_fat(res.getDouble(18));
			n.setMonounsaturated_fat(res.getDouble(19));
			n.setTrans_fat(res.getDouble(20));
			n.setCholesterol(res.getDouble(21));
			n.setSodium(res.getDouble(22));
			n.setPotassium(res.getDouble(23));
			n.setFiber(res.getDouble(24));
			n.setSugar(res.getDouble(25));
			n.setVitamin_a(res.getDouble(26));
			n.setVitamin_c(res.getDouble(27));
			n.setCalcium(res.getDouble(28));
			n.setIron(res.getDouble(29));
			ni.setNutrientinfo(n);
			
			if(sv.getNumber_of_units() != 0.0)
				ni.setCalPerServing(n.getCalories()/sv.getNumber_of_units());
			else
				ni.setCalPerServing(0.0);
			ni.setLikepercentage("90%");
			ni.setProcessingstatus("Highly Processed");
			ni.setReviews(res.getString(30));
			ni.setFoodImage_url(res.getString(31));
			
			nutritionalinfoList.add(ni);
		}
		
		stmt.close();
		return nutritionalinfoList;		
	}

	private Connection connectToDatabaseOrDie()
	{
		Connection conn = null;
	    try
	    {
	    	/*Class.forName("org.postgresql.Driver");
	    	String url = "jdbc:postgresql://foodsnap-dbserver.czhc2bsiejpu.us-west-2.rds.amazonaws.com:5432/foodsnap";
	    	conn = DriverManager.getConnection(url,"foodsnap", "password");
	    	*/
	    	Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://"+foodsnap_environmentvar.DBHost + ":" + foodsnap_environmentvar.DBPort + "/foodsnap";
			conn = DriverManager.getConnection(url, foodsnap_environmentvar.DBUser, foodsnap_environmentvar.DBPassword);
	    }
	    catch (ClassNotFoundException e)
	    {
	    	e.printStackTrace();
	    	System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	System.exit(1);
	    }
	    catch (SQLException e)
	    {
	    	e.printStackTrace();
	    	System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	System.exit(2);
	    }
	    System.out.println("yaay, db connection acquired!");
	    return conn;
	  }


}

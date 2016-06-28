/**
 * 
 */
package foodsnap_server.dao;

import java.sql.*;
import foodsnap_server.constants.foodsnap_environmentvar;
import foodsnap_server.dto.*;

/**
 * @author Saniya
 *
 */
public class DBUpdater {
	Connection dbconn;

	public DBUpdater() {
		try {
			dbconn = connectToDatabaseOrDie();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void insertProductInfo_DB(NutritionalInfo ni) throws Exception {
		Statement stmt = dbconn.createStatement();

		String iString = createInsertString(ni);
		String sqlquery = "INSERT INTO nutritionalinfo (food_id, food_name, food_type, brand_name, food_url, serving_id, serving_description, serving_url, metric_serving_amount, metric_serving_unit, number_of_units, measurement_description, calories, carbohydrate, protein, fat, saturated_fat, polyunsaturated_fat, monounsaturated_fat, trans_fat, cholesterol, sodium, potassium, fiber, sugar, vitamin_a, vitamin_c, calcium, iron) VALUES "
				+ iString;
		System.out.println(sqlquery);
		stmt.executeUpdate(sqlquery);
		stmt.close();
	}
	
	public void insertBarcodeFoodIdMapping_DB(BarcodeFoodIdMapping m) throws Exception {
		Statement stmt = dbconn.createStatement();

		String iString = createInsertString_Mapping(m);
		String sqlquery = "INSERT INTO barcodefoodidmapping (barcode, food_id) VALUES "
				+ iString;		
		System.out.println(sqlquery);
		stmt.executeUpdate(sqlquery);
		stmt.close();
	}

	private String createInsertString_Mapping(BarcodeFoodIdMapping m) {
		String retStr = "";
		retStr = retStr + "('" + m.getBarcode() + "', " + m.getFood_id() + "), ";
		return retStr.substring(0, retStr.length() - 2);
	}

	private String createInsertString(NutritionalInfo ni) {
		String retStr = "";
		retStr = retStr + "(" + ni.getFood_id() + ", '" + ni.getFood_name() + "', '" + ni.getFood_type() + "', '"
					+ ni.getBrand_name() + "', '" + ni.getFood_url() + "', " + ni.getServinginfo().getServing_id()
					+ ", '" + ni.getServinginfo().getServing_description() + "', '"
					+ ni.getServinginfo().getServing_url() + "', " + ni.getServinginfo().getMetric_serving_amount()
					+ ", '" + ni.getServinginfo().getMetric_serving_unit() + "', "
					+ ni.getServinginfo().getNumber_of_units() + ", '"
					+ ni.getServinginfo().getMeasurement_description() + "', " + ni.getNutrientinfo().getCalories()
					+ ", " + ni.getNutrientinfo().getCarbohydrate() + ", " + ni.getNutrientinfo().getProtein() + ", "
					+ ni.getNutrientinfo().getFat() + ", " + ni.getNutrientinfo().getSaturated_fat() + ", "
					+ ni.getNutrientinfo().getPolyunsaturated_fat() + ", "
					+ ni.getNutrientinfo().getMonounsaturated_fat() + ", " + ni.getNutrientinfo().getTrans_fat() + ", "
					+ ni.getNutrientinfo().getCholesterol() + ", " + ni.getNutrientinfo().getSodium() + ", "
					+ ni.getNutrientinfo().getPotassium() + ", " + ni.getNutrientinfo().getFiber() + ", "
					+ ni.getNutrientinfo().getSugar() + ", " + ni.getNutrientinfo().getVitamin_a() + ", "
					+ ni.getNutrientinfo().getVitamin_c() + ", " + ni.getNutrientinfo().getCalcium() + ", "
					+ ni.getNutrientinfo().getIron() + "), ";
		String regex1 = "NaN";
		String regex2 = "nan";
		retStr = retStr.replaceAll(regex1, "0");
		retStr = retStr.replaceAll(regex2, "0");
		return retStr.substring(0, retStr.length() - 2);
	}

	private Connection connectToDatabaseOrDie() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://"+foodsnap_environmentvar.DBHost + ":" + foodsnap_environmentvar.DBPort + "/foodsnap";
			conn = DriverManager.getConnection(url, foodsnap_environmentvar.DBUser, foodsnap_environmentvar.DBPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(2);
		}
		System.out.println("yaay, db connection acquired!");
		return conn;
	}

}

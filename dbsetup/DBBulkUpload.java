/**
 * 
 */
package foodsnap_server.dbsetup;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import foodsnap_server.client.FatSecretAPIRestClient;
import foodsnap_server.client.json.JSONObject;
import foodsnap_server.constants.foodsnap_environmentvar;
import foodsnap_server.dao.DBBulkUpdater;
import foodsnap_server.dto.BarcodeFoodIdMapping;
import foodsnap_server.dto.Nutrient;
import foodsnap_server.dto.NutritionalInfo;
import foodsnap_server.dto.Serving;

/**
 * @author Saniya
 *
 */
public class DBBulkUpload {
	public static void uploadBarcodeNutritionInfo() {
		BigInteger barcode_num = new BigInteger("1000000000010");
		BigInteger barcode_max = new BigInteger("1000000001000"); //TODO: change to 9999999999999
		BigInteger barcode_add = new BigInteger("1"); 
		FatSecretAPIRestClient fsclient = new FatSecretAPIRestClient(foodsnap_environmentvar.FSCustomerKey, foodsnap_environmentvar.FSSharedSecret);
		Gson g = new Gson();
		List<BarcodeFoodIdMapping> map_list = new ArrayList<BarcodeFoodIdMapping>();
		List<NutritionalInfo> ni_list = new ArrayList<NutritionalInfo>();
		while(barcode_num.compareTo(barcode_max) < 0) {
			System.out.println(barcode_num);
			try {
				/*
				JSONObject res_nut = fsclient.getFoodItem(4384);
				System.out.println(res_nut);
				*/
				String barcode = barcode_num.toString();
				JSONObject fid1 = fsclient.getBarcodeToFoodid(barcode).getJSONObject("result").optJSONObject("food_id");
				System.out.println(fid1);
				if(fid1 != null) {
					long fid = fid1.optLong("value");
					System.out.println(fid);
					if(fid != 0) {
						BarcodeFoodIdMapping m = new BarcodeFoodIdMapping();
						m.setBarcode(barcode);
						m.setFood_id(fid);
						
						JSONObject res_nut1 = fsclient.getFoodItem(fid).getJSONObject("result");
						System.out.println(res_nut1);
						JSONObject res_nut = res_nut1.optJSONObject("food");
						System.out.println(res_nut);
						if(res_nut != null) {
							NutritionalInfo ni = new NutritionalInfo();
							ni = serializeJSON_manual(res_nut);
							ni_list.add(ni);
							map_list.add(m);
						}
					}
				}
			}
			catch(Exception e) {
				System.out.println("Error in getting data from FatSecret!");
				System.out.println(e);
				break;
			}
			finally {
				barcode_num = barcode_num.add(barcode_add);
			}
		}
		Iterator<BarcodeFoodIdMapping> iBF = map_list.iterator();
		while(iBF.hasNext()) {
			System.out.println(iBF.next().getFood_id());
		}
		Iterator<NutritionalInfo> iNI = ni_list.iterator();
		while(iNI.hasNext()) {
			System.out.println(iNI.next().getFood_id());
		}
		if(!map_list.isEmpty() && !ni_list.isEmpty()) {
			try {
				DBBulkUpdater dbcon = new DBBulkUpdater();
				System.out.println("1");
				dbcon.insertBarcodeFoodIdMapping_DB(map_list);
				System.out.println("2");
				dbcon.insertProductInfo_DB(ni_list);
				System.out.println("3");
			}
			catch(Exception e) {
				System.out.println("Error in updating data to DB!");
				System.out.println(e);
			}
		}
	}

	private static NutritionalInfo serializeJSON_manual(JSONObject res) {
		NutritionalInfo ni = new NutritionalInfo();
		ni.setFood_id(res.optLong("food_id"));
		ni.setFood_name(res.optString("food_name"));
		ni.setFood_type(res.optString("food_type"));
		ni.setBrand_name(res.optString("brand_name"));
		ni.setFood_url(res.optString("food_url"));
		Object servings = res.opt("servings");
		JSONObject res_serving = null;
		if(servings instanceof Collection<?>) {
			servings = (Collection<JSONObject>)servings;
			Iterator itr = ((List<JSONObject>) servings).iterator();
			if(itr.hasNext())
			{
				res_serving = (JSONObject) itr.next();			
			}
		}
		else {
			res_serving= res.getJSONObject("servings");
		}
		Serving s = new Serving();
		s.setServing_id(res_serving.optLong("serving_id"));
		s.setServing_description(res_serving.optString("serving_description"));
		s.setServing_url(res_serving.optString("serving_url"));
		s.setMetric_serving_amount(res_serving.optDouble("metric_serving_amount"));
		s.setMetric_serving_unit(res_serving.optString("metric_serving_unit"));
		s.setNumber_of_units(res_serving.optDouble("number_of_units"));
		s.setMeasurement_description(res_serving.optString("measurement_description"));
		ni.setServinginfo(s);
			
		Nutrient n = new Nutrient();
		n.setCalcium(res_serving.optDouble("calcium"));
		n.setCalories(res_serving.optDouble("calories"));
		n.setCarbohydrate(res_serving.optDouble("carbohydrate"));
		n.setCholesterol(res_serving.optDouble("cholesterol"));
		n.setFat(res_serving.optDouble("fat"));
		n.setFiber(res_serving.optDouble("fiber"));
		n.setIron(res_serving.optDouble("iron"));
		n.setMonounsaturated_fat(res_serving.optDouble("monounsaturated_fat"));
		n.setPolyunsaturated_fat(res_serving.optDouble("polyunsaturated_fat"));
		n.setPotassium(res_serving.optDouble("potassium"));
		n.setProtein(res_serving.optDouble("protein"));
		n.setSaturated_fat(res_serving.optDouble("saturated_fat"));
		n.setSodium(res_serving.optDouble("sodium"));
		n.setSugar(res_serving.optDouble("sugar"));
		n.setTrans_fat(res_serving.optDouble("trans_fat"));
		n.setVitamin_a(res_serving.optDouble("vitamin_a"));
		n.setVitamin_c(res_serving.optDouble("vitamin_c"));
		ni.setNutrientinfo(n);
		return ni;
	}


}

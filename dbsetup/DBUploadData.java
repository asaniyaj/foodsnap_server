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
import foodsnap_server.dao.DBUpdater;
import foodsnap_server.dto.*;

/**
 * @author Saniya
 *
 */
public class DBUploadData {
	
	public static void uploadBarcodeNutritionInfo() {
		BigInteger barcode_num = new BigInteger("0038000565342"); //1000003327177 or 1000002256870
		BigInteger barcode_max = new BigInteger("0038000565343"); //TODO: change to 9999999999999
		BigInteger barcode_add = new BigInteger("1"); 
		FatSecretAPIRestClient fsclient = new FatSecretAPIRestClient(foodsnap_environmentvar.FSCustomerKey, foodsnap_environmentvar.FSSharedSecret);
		//List<String> barcodeList = new ArrayList<String>();
		//barcodeList = createBarcodeList();
		//for(String barcode : barcodeList) {
		while(barcode_num.compareTo(barcode_max) < 0) {
			try {
				String barcode = barcode_num.toString();
				System.out.println(barcode);
				JSONObject fid2 = fsclient.getBarcodeToFoodid(barcode).optJSONObject("result");
				System.out.println(fsclient.getBarcodeToFoodid(barcode));
				if(fid2 != null) {
					System.out.println(fid2);
					JSONObject fid1 = fid2.optJSONObject("food_id");
					if(fid1 != null) {
						long fid = fid1.optLong("value");
						if(fid != 0) {
							BarcodeFoodIdMapping m = new BarcodeFoodIdMapping();
							m.setBarcode(barcode);
							m.setFood_id(fid);
							JSONObject res_nut1 = fsclient.getFoodItem(fid).getJSONObject("result");
							if(res_nut1 != null) {
								JSONObject res_nut = res_nut1.optJSONObject("food");
								if(res_nut != null) {
									System.out.println(barcode +" " + fid);
									NutritionalInfo ni = new NutritionalInfo();
									ni = serializeJSON_manual(res_nut);
									DBUpdater dbcon = new DBUpdater();
									System.out.println("1");
									dbcon.insertBarcodeFoodIdMapping_DB(m);
									System.out.println("2");
									dbcon.insertProductInfo_DB(ni);
									System.out.println("3");
								}
							}
						}
					}
				}
			}
			catch(Exception e) {
				System.out.println("Error!");
				System.out.println(e);
				break;
			}
			finally {
				barcode_num = barcode_num.add(barcode_add);
			}
		}
	}
	
	private static List<String> createBarcodeList() {
		List<String> barcodelist = new ArrayList<String>();
		/*barcodelist.add("5410188802962");
		barcodelist.add("5410188017151");
		barcodelist.add("5410188019124");
		barcodelist.add("5410188006490");
		barcodelist.add("5410188006681");
		barcodelist.add("5410188016826");
		barcodelist.add("5410188020113");
		barcodelist.add("5410188000931");
		barcodelist.add("5410188026054");
		barcodelist.add("5410188022384");
		barcodelist.add("5410188022827");
		barcodelist.add("5410188022841");
		barcodelist.add("5410188015515");
		barcodelist.add("5410188006773");
		barcodelist.add("5410188013085");
		barcodelist.add("5410188006278");
		barcodelist.add("5410188006711");
		barcodelist.add("5410188006308");
		barcodelist.add("5410188026573");
		barcodelist.add("5410188026597");		
		barcodelist.add("5410188802603");
		barcodelist.add("5410188013016");
		barcodelist.add("5449000131836");
		barcodelist.add("5449000134059");
		barcodelist.add("5449000134264");
		//barcodelist.add("5449000133328");
		barcodelist.add("5449000137609");
		barcodelist.add("5449000131843");
		barcodelist.add("5449000059697");
		barcodelist.add("5449000136336");
		barcodelist.add("5449000137517");
		barcodelist.add("5449000138446");
		barcodelist.add("5449000148728");
		barcodelist.add("5449000131812");
		barcodelist.add("5449000134578");
		barcodelist.add("5449000138156");
		barcodelist.add("5449000169327");
		barcodelist.add("5449000169358");
		barcodelist.add("5449000169334");
		barcodelist.add("5449000099389");*/
		
		barcodelist.add("0064100145269");
		barcodelist.add("064100145269");
		barcodelist.add("0064100293038");
		barcodelist.add("064100293038");
		
		barcodelist.add("018627534228");
		barcodelist.add("009800892266");
		barcodelist.add("041500000251");
		barcodelist.add("048500207840");
		barcodelist.add("038000781100");
		barcodelist.add("038000114809");
		barcodelist.add("038000273100");
		barcodelist.add("030000059708");
		//barcodelist.add("012000809941");

		
		return barcodelist;
	}

	private static NutritionalInfo serializeJSON_manual(JSONObject res) {
		NutritionalInfo ni = new NutritionalInfo();
		ni.setFood_id(res.optLong("food_id"));
		ni.setFood_name(res.optString("food_name"));
		ni.setFood_type(res.optString("food_type"));
		ni.setBrand_name(res.optString("brand_name"));
		ni.setFood_url(res.optString("food_url"));
		ni.setFoodImage_url("");
		Object servings = res.opt("servings");
		if (servings != null) {
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
		}
		else {
			Serving s = new Serving();
			s.setServing_id(0);
			s.setServing_description("");
			s.setServing_url("");
			s.setMetric_serving_amount(0);
			s.setMetric_serving_unit("");
			s.setNumber_of_units(0);
			s.setMeasurement_description("");
			ni.setServinginfo(s);
				
			Nutrient n = new Nutrient();
			n.setCalcium(0);
			n.setCalories(0);
			n.setCarbohydrate(0);
			n.setCholesterol(0);
			n.setFat(0);
			n.setFiber(0);
			n.setIron(0);
			n.setMonounsaturated_fat(0);
			n.setPolyunsaturated_fat(0);
			n.setPotassium(0);
			n.setProtein(0);
			n.setSaturated_fat(0);
			n.setSodium(0);
			n.setSugar(0);
			n.setTrans_fat(0);
			n.setVitamin_a(0);
			n.setVitamin_c(0);
			ni.setNutrientinfo(n);
		}
		return ni;
	}

}

/**
 * 
 */
package foodsnap_server.test;

import foodsnap_server.client.*;
import foodsnap_server.client.json.JSONObject;
import foodsnap_server.constants.foodsnap_environmentvar;
import foodsnap_server.dbsetup.DBUploadData;
import foodsnap_server.dto.BarcodeFoodIdMapping;

/**
 * @author Saniya
 *
 */
public class TestCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			/*
			FatSecretAPIRestClient fsclient = new FatSecretAPIRestClient(foodsnap_environmentvar.FSCustomerKey, foodsnap_environmentvar.FSSharedSecret);
			//JSONObject res_nut = fsclient.getFoodItem(4384);
			//System.out.println(res_nut);			
			String barcode = "852684003071";//"8904004402513";
			JSONObject res_foodid = fsclient.getBarcodeToFoodid(barcode);
			BarcodeFoodIdMapping m = new BarcodeFoodIdMapping();
			m.setBarcode(barcode);
			System.out.println(res_foodid);
			*/
			DBUploadData.uploadBarcodeNutritionInfo();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

}

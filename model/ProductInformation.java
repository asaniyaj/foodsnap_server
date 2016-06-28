/**
 * 
 */
package foodsnap_server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import foodsnap_server.dao.DBAccessor;
import foodsnap_server.dto.*;

/**
 * @author Saniya
 *
 */
public class ProductInformation {
	
	private static Map<Long, String> collapseReviews(List<NutritionalInfo> nutritionalinfoList) {
		Map<Long, String> reviewMap = new HashMap<Long, String>();
		for(NutritionalInfo ni : nutritionalinfoList) {
			long food_id = ni.getFood_id();
			String rev_cur = reviewMap.get(food_id);
			String rev = ni.getReviews(); 
			if (rev_cur != null)
				rev = rev + "," + rev_cur;
			reviewMap.remove(food_id);
			reviewMap.put(food_id,  rev);
		}	
		return reviewMap;
	}
	
	public static List<NutritionalInfo> getProductInfo_Model(String columnname, Object columnValue, int cal) throws Exception {
		List<NutritionalInfo> nutritionalinfoList = new ArrayList<NutritionalInfo>();
		List<NutritionalInfo> productinfoList = new ArrayList<NutritionalInfo>();
		List<NutritionalInfo> productinfoList_1 = new ArrayList<NutritionalInfo>();
		DBAccessor acc = new DBAccessor();
		try {
			nutritionalinfoList = acc.getProductInfo_DB(columnname, columnValue);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		//System.out.println("Model: "+ nutritionalinfoList);
		productinfoList = processForReviews(nutritionalinfoList);
		productinfoList_1 = processForRecos(productinfoList, cal);
		return productinfoList_1;
	}

	private static List<NutritionalInfo> processForReviews(List<NutritionalInfo> nutritionalinfoList) {
		Map<Long, String> reviewMap = collapseReviews(nutritionalinfoList);
		Set<Long> foodidSet = new HashSet<Long>();
		List<NutritionalInfo> productinfoList = new ArrayList<NutritionalInfo>();
		for(NutritionalInfo ni: nutritionalinfoList) {
			long foodid = ni.getFood_id();
			if(!foodidSet.contains(foodid)) {
				String review = reviewMap.get(foodid);
				String review_url = ProductReviews.buildWordCloud(foodid, review);
				//TODO: change here!
				//ni.setReviews(review);
				ni.setReviews(review_url);
				foodidSet.add(foodid);
				productinfoList.add(ni);
			}
		}
		return productinfoList;
	} 
	
	private static List<NutritionalInfo> processForRecos(List<NutritionalInfo> nutritionalinfoList, int cal) {
		List<NutritionalInfo> productinfoList = new ArrayList<NutritionalInfo>();
		ProductRecommendations pr = new ProductRecommendations();
		pr.setCal_intake(cal);
		for(NutritionalInfo ni: nutritionalinfoList) {
			List<String> recoList = new ArrayList<String>();
			recoList = pr.generateProductRecommendations(ni);
			ni.setRecommendationList(recoList);
			productinfoList.add(ni);
		}
		return productinfoList;
	} 
}

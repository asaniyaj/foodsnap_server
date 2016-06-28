/**
 * 
 */
package foodsnap_server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import foodsnap_server.dto.ComparisonInfo;
import foodsnap_server.dto.NutritionalInfo;

/**
 * @author Saniya
 *
 */
public class ProductComparison {
	
	public static ComparisonInfo generateProductComparisons(List<NutritionalInfo> l_ni, int cal) {
		ComparisonInfo ci = new ComparisonInfo();
		List<String> compList = new ArrayList<String>();
		System.out.println("l_ni in generateProdcutComparisons is: " + l_ni);
		int maxScore = Integer.MIN_VALUE;
		int maxStar = Integer.MIN_VALUE;
		int minScore = Integer.MAX_VALUE;
		int minStar = Integer.MAX_VALUE;
		NutritionalInfo bestProduct = new NutritionalInfo();
		NutritionalInfo worstProduct = new NutritionalInfo();
		ProductRecommendations pr = new ProductRecommendations();
		pr.setCal_intake(cal);
		for (NutritionalInfo ni: l_ni) {
			int score = pr.getFinalScore(ni);
			System.out.println("Score is: " + score);
			if(score > maxScore) {
				maxScore = score;
				bestProduct = ni;
				System.out.println("bestProduct: " + bestProduct);
			}
			if(score < minScore) {
				minScore = score;
				worstProduct = ni;
				System.out.println(worstProduct);
			}
		}
		minStar = pr.getFinalStar(minScore);
		maxStar = pr.getFinalStar(maxScore);
		System.out.println("bahar!");
		System.out.println(bestProduct);
		System.out.println(worstProduct);
		compList.add("FoodSNAP recommends " + bestProduct.getFood_name() + ", a " + maxStar + " star product");
		compList.add("1 serving of " + bestProduct.getFood_name() + " has " + bestProduct.getCalPerServing() + " calories.");		
		if(l_ni.size() > 1) {
			compList.add("Least recommended product for now is " + worstProduct.getFood_name() + ", a " + minStar + " star product");
			compList.add("1 serving of " + worstProduct.getFood_name() + " has " + worstProduct.getCalPerServing() + " calories.");
		}
		ci.setcomparisonInfoList(compList);
		ci.setBestProduct(bestProduct);
		return ci;
	}

	public static ComparisonInfo getProductComparison(String column, String val, int cal) {
		String delims = ",";
		StringTokenizer st = new StringTokenizer(val, delims);
		List<NutritionalInfo> l_ni = new ArrayList<NutritionalInfo>();
		while(st.hasMoreTokens()) {
			String barcode = (String) st.nextElement();
			System.out.println(barcode);
			List<NutritionalInfo> l_ni_this = new ArrayList<NutritionalInfo>();
			try {
				l_ni_this = ProductInformation.getProductInfo_Model("barcode", barcode, cal);
				System.out.println("l_ni_this: " + l_ni_this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l_ni.addAll(l_ni_this);
		}
		ComparisonInfo l_prodcomp = new ComparisonInfo();
		System.out.println("before l_prodcomp: " + l_ni);
		l_prodcomp = generateProductComparisons(l_ni, cal);		
		System.out.println("after l_prodcomp");
		return l_prodcomp;
	}
}

/**
 * 
 */
package foodsnap_server.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foodsnap_server.dto.NutritionalInfo;

/**
 * @author Saniya
 *
 */
public class ProductRecommendations {
	
	private int cal_intake = 1600;
	private static int pota_intake = 4700;
	
	
	/**
	 * @return the cal_intake
	 */
	public int getCal_intake() {
		return cal_intake;
	}

	/**
	 * @param cal_intake the cal_intake to set
	 */
	public void setCal_intake(int cal_intake) {
		this.cal_intake = cal_intake;
	}

	private float getFatIntake() {
		float fat_intake = (float) (0.25 * getCal_intake()/9);
		return fat_intake;
	}
	
	private float getCarbIntake() {
		float carb_intake = (float) (0.6 * getCal_intake()/9);
		return carb_intake;
	}
	
	private int getTransFatScore(double d) {
		if(d==0.0) 
			return 0;
		if(d<0.5) 
			return -1;
		if(d<=1)
			return -2;
		else
			return -3;
	}
	
	private int getSatFatScore(double x) {
		if(x<=1) 
			return 0;
		if(x<=2) 
			return -1;
		if(x<=3)
			return -2;
		else
			return -3;
	}
	
	private int getCholesterolScore(double x) {
		if(x<=15) 
			return 0;
		if(x<=30) 
			return -1;
		if(x<=45)
			return -2;
		else
			return -3;
	}
	
	private int getSugarScore(double s) {
		float x = (float) (s * 3.87); 
		if(x==0) 
			return 0;
		if(x<=(float)(0.1*getCal_intake())) 
			return -1;
		if(x<=(float)(0.25*getCal_intake())) 
			return -2;
		if(x<=(float)(0.4*getCal_intake())) 
			return -3;
		else
			return -4;
	}
	
	private int getSodiumScore(double x) {
		if(x<=120) 
			return 0;
		if(x<=240) 
			return -1;
		if(x<=360)
			return -2;
		if(x<=600) 
			return -3;
		else
			return -4;
	}
	
	private int getFiberScore(double d) {
		if(d>=3.75) 
			return 3;
		if(d>=2.5) 
			return 2;
		if(d>=1.25)
			return 1;
		else
			return 0;
	}
	
	private int getWholeGrainScore(double x) {
		if(x>=1.5) 
			return 1;
		else
			return 0;
	}
	
	private int check10(double x) {
		if(x>10)
			return 1;
		else
			return 0;
	}
	
	private int check5(double x) {
		if(x>5)
			return 1;
		else
			return 0;
	}
	
	private int getMineralScore(double vit_a, double vit_c, double pot, double cal, double iron) {
		double pota_part = (pot/pota_intake) * 100;
		
		if((check10(vit_c) + check10(vit_a) + check10(pota_part) + check10(cal) + check10(iron))>=2)
			return 3;
		if((check5(vit_c) + check5(vit_a) + check5(pota_part) + check5(cal) + check5(iron))>=2)
			return 2;
		if((check5(vit_c) + check5(vit_a) + check5(pota_part) + check5(cal) + check5(iron))>=1)
			return 1;
		else
			return 0;
	}
	
	private int getFatScore(double x) {
		if(x>getFatIntake())
			return 1;
		else
			return 0;
	}
	
	private int getCarbScore(double x) {
		if(x>getCarbIntake())
			return 1;
		else
			return 0;
	}
	
	
	
	public List<String> generateProductRecommendations(NutritionalInfo ni) {
		List<String> recoList = new ArrayList<String>();
		int finalScore = getFinalScore(ni);
		int star = getFinalStar(finalScore);
		
		
		//System.out.println(finalScore);
		//System.out.println(star);
		
		String reco1 = "FoodSNAP Verdict: " + star + " out of 3 stars.";
		recoList.add(reco1);
		
		if(getTransFatScore(ni.getNutrientinfo().getTrans_fat()) < 0) {
			String r = "Warning: High Trans Fat Content!";
			recoList.add(r);
		}
		if(getSodiumScore(ni.getNutrientinfo().getSodium()) < 0) {
			String r = "Warning: High Sodium Content!";
			recoList.add(r);
		}		
		if(getFiberScore(ni.getNutrientinfo().getFiber()) > 2) {
			String r = "Very High Fiber Content!";
			recoList.add(r);
		}
		if(getFiberScore(ni.getNutrientinfo().getFiber()) > 0) {
			String r = "High Fiber Content!";
			recoList.add(r);
		}
		if(getSugarScore(ni.getNutrientinfo().getSugar()) < 0) {
			String r = "Warning: High Sugar Content!";
			recoList.add(r);
		}
		if(getMineralScore(ni.getNutrientinfo().getVitamin_a(), ni.getNutrientinfo().getVitamin_c(), ni.getNutrientinfo().getPotassium(), ni.getNutrientinfo().getCalories(), ni.getNutrientinfo().getIron()) > 2) {
			String r = "Very High Mineral Content!";
			recoList.add(r);
		}
		if(getMineralScore(ni.getNutrientinfo().getVitamin_a(), ni.getNutrientinfo().getVitamin_c(), ni.getNutrientinfo().getPotassium(), ni.getNutrientinfo().getCalories(), ni.getNutrientinfo().getIron()) > 0) {
			String r = "High Mineral Content!";
			recoList.add(r);
		}
		if(getCholesterolScore(ni.getNutrientinfo().getCholesterol()) < 0) {
			String r = "Warning: High Cholesterol Content!";
			recoList.add(r);
		}
		if(getSatFatScore(ni.getNutrientinfo().getSaturated_fat()) < 0) {
			String r = "Warning: High Saturated Fat Content!";
			recoList.add(r);
		}
		if(getFatScore(ni.getNutrientinfo().getFat()) < 0) {
			String r = "High Fat Content!";
			recoList.add(r);
		}
		if(getCarbScore(ni.getNutrientinfo().getCarbohydrate()) < 0) {
			String r = "High Carb Content!";
			recoList.add(r);
		}
		if(getFatScore(ni.getNutrientinfo().getFat()) < 0) {
			String r = "Warning: Very High Fat Content!";
			recoList.add(r);
		}
		String r1 = "This product has " + ni.getNutrientinfo().getCalories() + " calories per serving.";
		recoList.add(r1);
		double pct = (ni.getNutrientinfo().getCalories()/getCal_intake() * 100);
		DecimalFormat f = new DecimalFormat("##.00");  // this will helps you to always keeps in two decimal places
        String r2 = "This product will be " + f.format(pct) + "% of your daily intake.";
		recoList.add(r2);
		
		//for(String k : recoList) 
		//	System.out.println(k);		
		return recoList;
		
	}

	int getFinalScore(NutritionalInfo ni) {
		int finalScore = getTransFatScore(ni.getNutrientinfo().getTrans_fat()) + getSatFatScore(ni.getNutrientinfo().getSaturated_fat()) + getCholesterolScore(ni.getNutrientinfo().getCholesterol()) + getSugarScore(ni.getNutrientinfo().getSugar()) + getSodiumScore(ni.getNutrientinfo().getSodium()) + getFiberScore(ni.getNutrientinfo().getFiber()) + getMineralScore(ni.getNutrientinfo().getVitamin_a(), ni.getNutrientinfo().getVitamin_c(), ni.getNutrientinfo().getPotassium(), ni.getNutrientinfo().getCalories(), ni.getNutrientinfo().getIron());
		return finalScore;
	}
	
	int getFinalStar(int finalScore) {
		int star = 0;
		if(finalScore <= -4)
			star = 0;
		else if(finalScore <= -1)
			star = 1;
		else if(finalScore <= 2)
			star = 2;
		else 
			star = 3;
		return star;
	}

}

/**
 * 
 */
package foodsnap_server.dto;

import java.util.List;

import foodsnap_server.model.ProductRecommendations;

/**
 * @author Saniya
 *
 */
public class NutritionalInfo {
	private long food_id;
	private String food_name;
	private String food_type;
	private String brand_name;
	private String food_url;
	private Serving servinginfo;
	private Nutrient nutrientinfo;
	private double calperserving;
	private String likepercentage;
	private String processingstatus;
	private String reviews;	//contains reviews wordcloud url now
	private String foodImage_url;
	private List<String> recommendationList;
	
	/**
	 * @return the food_id
	 */
	public long getFood_id() {
		return food_id;
	}
	/**
	 * @param food_id the food_id to set
	 */
	public void setFood_id(long food_id) {
		this.food_id = food_id;
	}
	/**
	 * @return the food_name
	 */
	public String getFood_name() {
		return food_name;
	}
	/**
	 * @param food_name the food_name to set
	 */
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	/**
	 * @return the food_type
	 */
	public String getFood_type() {
		return food_type;
	}
	/**
	 * @param food_type the food_type to set
	 */
	public void setFood_type(String food_type) {
		this.food_type = food_type;
	}
	/**
	 * @return the brand_name
	 */
	public String getBrand_name() {
		return brand_name;
	}
	/**
	 * @param brand_name the brand_name to set
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	/**
	 * @return the food_url
	 */
	public String getFood_url() {
		return food_url;
	}
	/**
	 * @param food_url the food_url to set
	 */
	public void setFood_url(String food_url) {
		this.food_url = food_url;
	}
	/**
	 * @return the servinginfo
	 */
	public Serving getServinginfo() {
		return servinginfo;
	}
	/**
	 * @param servinginfo the servinginfo to set
	 */
	public void setServinginfo(Serving servinginfo) {
		this.servinginfo = servinginfo;
	}
	/**
	 * @return the nutrientinfo
	 */
	public Nutrient getNutrientinfo() {
		return nutrientinfo;
	}
	/**
	 * @param nutrientinfo the nutrientinfo to set
	 */
	public void setNutrientinfo(Nutrient nutrientinfo) {
		this.nutrientinfo = nutrientinfo;
	}
	
	/**
	 * @return the foodImage_url
	 */
	public String getFoodImage_url() {
		return foodImage_url;
	}
	/**
	 * @param nutrientinfo the foodImage_url to set
	 */
	public void setFoodImage_url(String foodImage_url) {
		this.foodImage_url = foodImage_url;
	}
	
	public double getCalPerServing() {
		double cal = getNutrientinfo().getCalories();
		double num = getServinginfo().getNumber_of_units();
		double cps = 0.0;
		if (num != 0.0)
			cps = (cal/num);
		return cps;
	}
	
	public void setCalPerServing(double calperserving) {
		this.calperserving = calperserving;
	}
	
	public void setLikepercentage(String likepercentage) {
		this.likepercentage = likepercentage;
	}
	
	public void setProcessingstatus(String processingstatus) {
		this.processingstatus = processingstatus;
	}
	
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getReviews() {
		// TODO Auto-generated method stub
		return this.reviews;
	}
	public String getProcessingStatus() {
		// TODO Auto-generated method stub
		return "Highly Processed!";
	}
	public String getLikePercentage() {
		// TODO Auto-generated method stub
		return "90%";
	}
	
	
	@Override
	public String toString() {
		//return "NutritionalInfo [food_id="+food_id+", food_name="+food_name+", food_type="+food_type+",brand_name="+brand_name+",food_url="+food_url+", servinginfo=["+servinginfo.toString()+"], nutrientinfo=["+nutrientinfo.toString()+"]";
		String str = "NutritionalInfo [food_id="+food_id+", food_name="+food_name+", food_type="+food_type+",brand_name="+brand_name+",food_url="+food_url+", servinginfo=["+servinginfo.toString()+"], nutrientinfo=["+nutrientinfo.toString()+", calPerServing="+getCalPerServing()+", likepercentage="+getLikePercentage()+", processingstatus="+getProcessingStatus()+", reviews="+getReviews()+"]";
		//System.out.println("In class: "+str);
		return str;
		//CalPerServing, LikePercent, Processed, Sugar, Reviews
	}
	/**
	 * @return the recommendationList
	 */
	public List<String> getRecommendationList() {
		return recommendationList;
	}
	/**
	 * @param recommendationList the recommendationList to set
	 */
	public void setRecommendationList(List<String> recommendationList) {
		this.recommendationList = recommendationList;
	}
	
	public void generateRecommendations() {
		ProductRecommendations pr = new ProductRecommendations();
		List<String> recoList = pr.generateProductRecommendations(this);
		setRecommendationList(recoList);
	}
	
	
}

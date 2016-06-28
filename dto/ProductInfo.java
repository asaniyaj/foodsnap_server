/**
 * 
 */
package foodsnap_server.dto;

import java.util.List;

/**
 * @author Saniya
 *
 */
public class ProductInfo {
	private NutritionalInfo nutritionalInfo;
	private List<String> recommendationList;
	private String likepercentage;
	private String processingstatus;
	private String reviews;
	
	/**
	 * @return the nutritionalInfo
	 */
	public NutritionalInfo getNutritionalInfo() {
		return nutritionalInfo;
	}

	/**
	 * @param nutritionalInfo the nutritionalInfo to set
	 */
	public void setNutritionalInfo(NutritionalInfo nutritionalInfo) {
		this.nutritionalInfo = nutritionalInfo;
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

}

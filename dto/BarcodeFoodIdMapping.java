/**
 * 
 */
package foodsnap_server.dto;

/**
 * @author Saniya
 *
 */
public class BarcodeFoodIdMapping {
	
	private String barcode;
	private long food_id;
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
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}
	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}

/**
 * 
 */
package foodsnap_server.dto;

import java.util.List;

/**
 * @author Saniya
 *
 */
public class ComparisonInfo {
	
	private List<String> comparisonInfoList;
	private NutritionalInfo bestProduct;

	/**
	 * @return the l
	 */
	public List<String> getcomparisonInfoList() {
		return comparisonInfoList;
	}

	/**
	 * @param l the l to set
	 */
	public void setcomparisonInfoList(List<String> comparisonInfoList) {
		this.comparisonInfoList = comparisonInfoList;
	}

	/**
	 * @return the bestProduct
	 */
	public NutritionalInfo getBestProduct() {
		return bestProduct;
	}

	/**
	 * @param bestProduct the bestProduct to set
	 */
	public void setBestProduct(NutritionalInfo bestProduct) {
		this.bestProduct = bestProduct;
	}

}

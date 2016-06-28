/**
 * 
 */
package foodsnap_server.dto;

/**
 * @author Saniya
 *
 */
public class Serving {
	private long serving_id;
	private String serving_description;
	private String serving_url;
	private double metric_serving_amount;
	private String metric_serving_unit;
	private double number_of_units;
	private String measurement_description;
	
	/**
	 * @return the serving_id
	 */
	public long getServing_id() {
		return serving_id;
	}
	/**
	 * @param serving_id the serving_id to set
	 */
	public void setServing_id(long serving_id) {
		this.serving_id = serving_id;
	}
	/**
	 * @return the serving_description
	 */
	public String getServing_description() {
		return serving_description;
	}
	/**
	 * @param serving_description the serving_description to set
	 */
	public void setServing_description(String serving_description) {
		this.serving_description = serving_description;
	}
	/**
	 * @return the serving_url
	 */
	public String getServing_url() {
		return serving_url;
	}
	/**
	 * @param serving_url the serving_url to set
	 */
	public void setServing_url(String serving_url) {
		this.serving_url = serving_url;
	}
	/**
	 * @return the metric_serving_amount
	 */
	public double getMetric_serving_amount() {
		return metric_serving_amount;
	}
	/**
	 * @param metric_serving_amount the metric_serving_amount to set
	 */
	public void setMetric_serving_amount(double metric_serving_amount) {
		this.metric_serving_amount = metric_serving_amount;
	}
	/**
	 * @return the metric_serving_unit
	 */
	public String getMetric_serving_unit() {
		return metric_serving_unit;
	}
	/**
	 * @param metric_serving_unit the metric_serving_unit to set
	 */
	public void setMetric_serving_unit(String metric_serving_unit) {
		this.metric_serving_unit = metric_serving_unit;
	}
	/**
	 * @return the number_of_units
	 */
	public double getNumber_of_units() {
		return number_of_units;
	}
	/**
	 * @param d the number_of_units to set
	 */
	public void setNumber_of_units(double d) {
		this.number_of_units = d;
	}
	/**
	 * @return the measurement_description
	 */
	public String getMeasurement_description() {
		return measurement_description;
	}
	/**
	 * @param measurement_description the measurement_description to set
	 */
	public void setMeasurement_description(String measurement_description) {
		this.measurement_description = measurement_description;
	}
	
	@Override
	public String toString() {
		return "Serving [serving_id="+serving_id+", serving_description="+serving_description+", serving_url="+serving_url+", metric_serving_amount="+metric_serving_amount+", metric_serving_unit="+metric_serving_unit+", number_of_units="+number_of_units+", measurement_description="+measurement_description+"]";
	}
}

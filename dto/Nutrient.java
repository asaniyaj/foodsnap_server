/**
 * 
 */
package foodsnap_server.dto;

/**
 * @author Saniya
 *
 */
public class Nutrient {
	private double calories;
	private double carbohydrate;
	private double protein;
	private double fat;
	private double saturated_fat;
	private double polyunsaturated_fat;
	private double monounsaturated_fat;
	private double trans_fat;
	private double cholesterol;
	private double sodium;
	private double potassium;
	private double fiber;
	private double sugar;
	private double vitamin_a;
	private double vitamin_c;
	private double calcium;
	private double iron;
	
	/**
	 * @return the calories
	 */
	public double getCalories() {
		return calories;
	}
	/**
	 * @param calories the calories to set
	 */
	public void setCalories(double calories) {
		this.calories = calories;
	}
	/**
	 * @return the carbohydrate
	 */
	public double getCarbohydrate() {
		return carbohydrate;
	}
	/**
	 * @param carbohydrate the carbohydrate to set
	 */
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	/**
	 * @return the protein
	 */
	public double getProtein() {
		return protein;
	}
	/**
	 * @param protein the protein to set
	 */
	public void setProtein(double protein) {
		this.protein = protein;
	}
	/**
	 * @return the fat
	 */
	public double getFat() {
		return fat;
	}
	/**
	 * @param fat the fat to set
	 */
	public void setFat(double fat) {
		this.fat = fat;
	}
	/**
	 * @return the saturated_fat
	 */
	public double getSaturated_fat() {
		return saturated_fat;
	}
	/**
	 * @param saturated_fat the saturated_fat to set
	 */
	public void setSaturated_fat(double saturated_fat) {
		this.saturated_fat = saturated_fat;
	}
	/**
	 * @return the polyunsaturated_fat
	 */
	public double getPolyunsaturated_fat() {
		return polyunsaturated_fat;
	}
	/**
	 * @param polyunsaturated_fat the polyunsaturated_fat to set
	 */
	public void setPolyunsaturated_fat(double polyunsaturated_fat) {
		this.polyunsaturated_fat = polyunsaturated_fat;
	}
	/**
	 * @return the monounsaturated_fat
	 */
	public double getMonounsaturated_fat() {
		return monounsaturated_fat;
	}
	/**
	 * @param monounsaturated_fat the monounsaturated_fat to set
	 */
	public void setMonounsaturated_fat(double monounsaturated_fat) {
		this.monounsaturated_fat = monounsaturated_fat;
	}
	/**
	 * @return the trans_fat
	 */
	public double getTrans_fat() {
		return trans_fat;
	}
	/**
	 * @param trans_fat the trans_fat to set
	 */
	public void setTrans_fat(double trans_fat) {
		this.trans_fat = trans_fat;
	}
	/**
	 * @return the cholesterol
	 */
	public double getCholesterol() {
		return cholesterol;
	}
	/**
	 * @param cholesterol the cholesterol to set
	 */
	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}
	/**
	 * @return the sodium
	 */
	public double getSodium() {
		return sodium;
	}
	/**
	 * @param sodium the sodium to set
	 */
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	/**
	 * @return the potassium
	 */
	public double getPotassium() {
		return potassium;
	}
	/**
	 * @param potassium the potassium to set
	 */
	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}
	/**
	 * @return the fiber
	 */
	public double getFiber() {
		return fiber;
	}
	/**
	 * @param fiber the fiber to set
	 */
	public void setFiber(double fiber) {
		this.fiber = fiber;
	}
	/**
	 * @return the sugar
	 */
	public double getSugar() {
		return sugar;
	}
	/**
	 * @param sugar the sugar to set
	 */
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	/**
	 * @return the vitamin_a
	 */
	public double getVitamin_a() {
		return vitamin_a;
	}
	/**
	 * @param vitamin_a the vitamin_a to set
	 */
	public void setVitamin_a(double vitamin_a) {
		this.vitamin_a = vitamin_a;
	}
	/**
	 * @return the vitamin_c
	 */
	public double getVitamin_c() {
		return vitamin_c;
	}
	/**
	 * @param vitamin_c the vitamin_c to set
	 */
	public void setVitamin_c(double vitamin_c) {
		this.vitamin_c = vitamin_c;
	}
	/**
	 * @return the calcium
	 */
	public double getCalcium() {
		return calcium;
	}
	/**
	 * @param calcium the calcium to set
	 */
	public void setCalcium(double calcium) {
		this.calcium = calcium;
	}
	/**
	 * @return the iron
	 */
	public double getIron() {
		return iron;
	}
	/**
	 * @param iron the iron to set
	 */
	public void setIron(double iron) {
		this.iron = iron;
	}
	
	@Override
	public String toString(){
		return "Nutrient [calories="+calories+", carbohydrate="+carbohydrate+", protein="+protein+", fat="+fat+", saturated_fat="+saturated_fat+", polyunsaturated_fat="+polyunsaturated_fat+", monounsaturated_fat="+monounsaturated_fat+", trans_fat="+trans_fat+", cholesterol="+cholesterol+", sodium="+sodium+", potassium="+potassium+", fiber="+fiber+", sugar="+sugar+", vitamin_a="+vitamin_a+", vitamin_c="+vitamin_c+", calcium="+calcium+", iron="+iron+"]";
	}
}

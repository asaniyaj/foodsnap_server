package foodsnap_server.model;

public class CalorieIntake {
	public static int getCalIntake(String height, String weight) {
		int ht = Integer.parseInt(height);
		int wt = Integer.parseInt(weight);
		
		double BMI = ((wt*703)/(ht*ht));
		if(BMI<25)
			return 2200;
		if(BMI<25.5)
			return 2350;
		if(BMI<26)
			return 2400;
		if(BMI<26.5)
			return 2500;
		if(BMI<27)
			return 2600;
		if(BMI<27.5)
			return 2650;
		if(BMI<28)
			return 2750;	
		return 2800;
	}

}

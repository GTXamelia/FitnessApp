package ie.fitnessapp.settings;

// Thses messages are the ones replayed to the user along with 'addon' to inform the user of any successes/failures  errors 
public class OutputMessages {

	// Main menu for logging in or registering
	public static final String MainMenu = 
			"1. Register"
			+ "\n2. Login"
			+ "\nEnter 'Exit' to disconnect";
	
	// Once logged in this menu will display
	public static final String LoginMenu = 
			"1. Add Fitness Record"
			+ "\n2. Add Meal Record"
			+ "\n3. View Last 10 Fitness Records"
			+ "\n4. View Last 10 Meal Records"
			+ "\n5. Delete a Record"
			+ "\nEnter 'Exit' to disconnect";

	// Fitness menu for deciding what exercise they did 
	public static final String FitnessMenu = 
			"1. Walking"
			+ "\n2. Running"
			+ "\n3. Cycling";
	
	// Meal menu for deciding which meal type they had
	public static final String MealMenu = 
			"1. Breakfast"
			+ "\n2. Lunch"
			+ "\n3. Dinner"
			+ "\n4. Snack"
			+ "\n5. Supper";
	
	// Delete menu for deciding which type of record they wish to delete 
	public static final String DeleteMenu = 
			"Delete:"
			+ "\n1. Meal Record"
			+ "\n2. Fitness Record"
			+ "\n3. Back";
	
	// Other data will be added to addon which will then be sent to the user
	public static String Addon = "";
}

package ie.fitnessapp.options;

public class Register {
	private static String name;
	private static String Addess;
	private static String PPSN;
	private static int age;
	private static double weight;
	private static double height;
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public static void setName(String name) {
		Register.name = name;
	}
	public String getAddess() {
		return Addess;
	}
	public static void setAddess(String addess) {
		Register.Addess = addess;
	}
	public String getPPSN() {
		return PPSN;
	}
	public static void setPPSN(String pPSN) {
		Register.PPSN = pPSN;
	}
	public int getAge() {
		return age;
	}
	public static void setAge(int age) {
		Register.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public static void setWeight(double weight) {
		Register.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public static void setHeight(double height) {
		Register.height = height;
	}

}

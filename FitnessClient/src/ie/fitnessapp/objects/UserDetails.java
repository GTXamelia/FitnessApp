package ie.fitnessapp.objects;

public class UserDetails {
	private String name;
	private String Addess;
	private String PPSN;
	private int age;
	private double weight;
	private double height;
	
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddess() {
		return Addess;
	}
	public void setAddess(String addess) {
		Addess = addess;
	}
	public String getPPSN() {
		return PPSN;
	}
	public void setPPSN(String pPSN) {
		PPSN = pPSN;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
}

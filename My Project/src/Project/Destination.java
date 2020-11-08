package Project;

public class Destination {
	
	private String number;
	private String model;
	private String day;
	private String takingOff;
	private String landing;
	private String airlines;

	public Destination(String number, String model, String day, String takingOff, String landing, String airlines) {
		this.number = number;
		this.model = model;
		this.day = day;
		this.takingOff = takingOff;
		this.landing = landing;
		this.airlines = airlines;
	}	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getAirlines() {
		return airlines;
	}
	public String getTakingOff() {
		return takingOff;
	}
	public void setTakingOff(String takingOff) {
		this.takingOff = takingOff;
	}
	public String getLanding() {
		return landing;
	}
	public void setLanding(String landing) {
		this.landing = landing;
	}
	public String getNumber() {
		return number;
	}
	public String getModel() {
		return model;
	}
}

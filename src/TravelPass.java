
public abstract class TravelPass {

	private int travelTime;
	private String zone;
	private double price;
	private String description;// declare only. no assignment. otherwise no value
	private boolean isSuccess = false;

	public TravelPass(int travelTime, String zone, double price) {
		this.travelTime = travelTime;
		this.zone = zone;
		this.price = price;
		String timeString = "";
		switch (travelTime) {
		case 2:
			timeString = "2 Hour";
			break;
		case 24:
			timeString = "All Day";
			break;
		default:
			break;
		}
		description = timeString + " pass for " + zone + ", costing $" + price; // assignment
	}

	public int getTravelTime() {
		return this.travelTime;
	}

	public String getZone() {
		return this.zone;
	}

	public double getPrice() {
		return this.price;
	}

	public String getDescription() {
		return this.description;
	}

	public boolean getIsSuccess() {
		return this.isSuccess;
	}

	public double buyTicket(double balance) {
		double deduct = 0;
		if (balance >= price) {
			deduct = -price;
			balance += deduct;
			System.out.println("Purchase Successful: " + description);
			isSuccess = true;
		} else {
			System.out.println("You don't have enough credit.");
			isSuccess = false;
		}
		return balance;
	}
}
